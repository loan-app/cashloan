package com.xiji.cashloan.cl.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.domain.ManualReviewOrder;
import com.xiji.cashloan.cl.mapper.ManualReviewOrderMapper;
import com.xiji.cashloan.cl.model.ManualReviewCountModel;
import com.xiji.cashloan.cl.model.ManualReviewOrderModel;
import com.xiji.cashloan.cl.model.UserOrderCountModel;
import com.xiji.cashloan.cl.service.ManualReviewOrderService;
import com.xiji.cashloan.cl.util.black.CollectionUtil;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.core.common.util.DateUtil;
import com.xiji.cashloan.system.mapper.SysUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


/**
 * 人工审核订单ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-16 17:00:19
 */
 
@Service("manualReviewOrderService")
public class ManualReviewOrderServiceImpl extends BaseServiceImpl<ManualReviewOrder, Long> implements ManualReviewOrderService {
	
    private static final Logger logger = LoggerFactory.getLogger(ManualReviewOrderServiceImpl.class);
   
    @Resource
    private ManualReviewOrderMapper manualReviewOrderMapper;

    @Resource
    private SysUserMapper sysUserMapper;

	@Override
	public BaseMapper<ManualReviewOrder, Long> getMapper() {
		return manualReviewOrderMapper;
	}

	@Override
	public Page<ManualReviewCountModel> memberCount(Map<String, Object> params, int current, int pageSize) {
		params = params == null ? new HashMap<String, Object>() : params;
		params.put("roleNid", "reviewSpecialist");

		PageHelper.startPage(current, pageSize);
		List<ManualReviewCountModel> modelList = manualReviewOrderMapper.listSysUserByRole(params);
		for (ManualReviewCountModel model : modelList) {
			long userId = model.getUserId();

			params.clear();
			params.put("userId", userId);
			model.setOrderCount(manualReviewOrderMapper.countOrder(params));

			params.put("state", ManualReviewOrderModel.STATE_ORDER_WAIT);
			model.setWaitCount(manualReviewOrderMapper.countOrder(params));

			params.put("state", ManualReviewOrderModel.STATE_ORDER_PASS);
			model.setPassCount(manualReviewOrderMapper.countOrder(params));

			params.put("state", ManualReviewOrderModel.STATE_ORDER_REFUSED);
			model.setRefusedCount(manualReviewOrderMapper.countOrder(params));

			model.setTodayPassOrderCount(manualReviewOrderMapper.countTodayPassOrder(params));

			model.setTodayLoanOrderCount(manualReviewOrderMapper.countTodayLoanOrder(params));


			Date yester = DateUtil.rollDay(DateUtil.getNow(), -1);
			params.put("startTime", DateUtil.getDayStartTime(yester));
			params.put("endTime", DateUtil.getDayEndTime(yester));
			model.setYesterdayCount(manualReviewOrderMapper.countByTime(params));

		}

		return (Page<ManualReviewCountModel>)modelList;
	}

	@Override
	public Page<ManualReviewOrderModel> list(Map<String, Object> params, int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<ManualReviewOrderModel> list = manualReviewOrderMapper.list(params);
		return (Page<ManualReviewOrderModel>)list;
	}

	@Override
	public int orderAllotUser(Map<String, Object> params) {
		return manualReviewOrderMapper.orderAllotUser(params);
	}

	/**
	 * 订单自动分配给审核员
	 * @return
	 */
	@Override
	public synchronized int automaticDistributionOrder(){

		int count = 0;
		List<ManualReviewOrder> manualReviewOrders = manualReviewOrderMapper.listAllocated();
		if (CollectionUtil.isEmpty(manualReviewOrders)){
			return count;
		}
		Map<String,Object> params = new HashMap<>();
		params.put("status","0");
		params.put("roleName","reviewSpecialist");
		// 审核专员信息
		List<Map<String, Object>> userInfos = sysUserMapper.getUserInfo(params);
		if (CollectionUtil.isEmpty(userInfos)){
			return count;
		}

		// 审核专员分配前拥有待分配 订单数
		List<Map<String,Object>>  listToBeAssignedCount = manualReviewOrderMapper.listToBeAssignedCount();
		List<UserOrderCountModel> userOrderCountModels = new ArrayList<>();
		for(Map<String, Object> userInfo : userInfos){
			UserOrderCountModel orderCountModel = new UserOrderCountModel();
			orderCountModel.setUserId((Long) userInfo.get("id"));
			orderCountModel.setUserName((String)userInfo.get("name"));
			orderCountModel.setReviewCount(0);
			for (Map<String,Object> assignedCount: listToBeAssignedCount){
				if (userInfo.get("id").equals(assignedCount.get("userId")) ){
					Long reviewCount = (Long)assignedCount.get("reviewCount");
					orderCountModel.setReviewCount(reviewCount.intValue());
					break;
				}
			}
			userOrderCountModels.add(orderCountModel);
		}

		boolean flag = false;
		Integer manualAuditMax = Global.getInt("manual_audit_max");
		for (UserOrderCountModel orderCountModel:userOrderCountModels){
			if (orderCountModel.getReviewCount() != null && orderCountModel.getReviewCount() < manualAuditMax){
				flag = true;
				break;
			}
		}
		if (!flag){
			return count;
		}

		// 降序排列
		Collections.sort(userOrderCountModels, new Comparator<UserOrderCountModel>() {
			@Override
			public int compare(UserOrderCountModel h1, UserOrderCountModel h2) {
				return h1.getReviewCount() - h2.getReviewCount();
			}
		});

		// 将订单逐一分配给拥有待审核数量最少的审核员
		List<ManualReviewOrder> reviewOrders = new ArrayList<>();
		for (ManualReviewOrder reviewOrder : manualReviewOrders){
			for(UserOrderCountModel model : userOrderCountModels){
				if (model.getReviewCount() < manualAuditMax){
					model.setReviewCount(model.getReviewCount() +1);
					reviewOrder.setUserId(model.getUserId());
					reviewOrder.setUserName(model.getUserName());
					reviewOrder.setState("11");
					reviewOrders.add(reviewOrder);
					break;
				}
			}
			Collections.sort(userOrderCountModels, new Comparator<UserOrderCountModel>() {
				@Override
				public int compare(UserOrderCountModel h1, UserOrderCountModel h2) {
					return h1.getReviewCount() - h2.getReviewCount();
				}
			});
		}

		if (CollectionUtil.isNotEmpty(reviewOrders)){
			count = manualReviewOrderMapper.batchUpdate(reviewOrders);
			if (count != reviewOrders.size()){
				logger.error("自动分配订单更新失败");
				return count;
			}
		}
		return count;
	}

    /**
     * 查询人工审核订单list
     * @param params
     * @return
     */
    @Override
    public List<ManualReviewOrder> listManualReviewOrder(Map<String,Object> params){
        return manualReviewOrderMapper.listSelective(params);
    }


    /**
     * 批量更新审核订单
     * @param manualReviewOrders
     * @return
     */
    @Override
    public int batchUpdate(List<ManualReviewOrder> manualReviewOrders){
        return manualReviewOrderMapper.batchUpdate(manualReviewOrders);
    }
}