package com.xiji.cashloan.cl.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiji.cashloan.cl.domain.BorrowRepay;
import com.xiji.cashloan.cl.domain.UrgeRepayOrder;
import com.xiji.cashloan.cl.mapper.BorrowRepayMapper;
import com.xiji.cashloan.cl.mapper.ClBorrowMapper;
import com.xiji.cashloan.cl.mapper.UrgeRepayOrderLogMapper;
import com.xiji.cashloan.cl.mapper.UrgeRepayOrderMapper;
import com.xiji.cashloan.cl.model.UrgeRepayCountModel;
import com.xiji.cashloan.cl.model.UrgeRepayOrderModel;
import com.xiji.cashloan.cl.service.UrgeRepayOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tool.util.BigDecimalUtil;
import tool.util.StringUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.core.common.util.DateUtil;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.mapper.UserBaseInfoMapper;
import com.xiji.cashloan.core.model.BorrowModel;
import com.xiji.cashloan.system.mapper.SysUserMapper;


/**
 * 催款计划表ServiceImpl
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("urgeRepayOrderService")
public class UrgeRepayOrderServiceImpl extends BaseServiceImpl<UrgeRepayOrder, Long> implements UrgeRepayOrderService {
	
	private static final Logger logger = LoggerFactory.getLogger(UrgeRepayOrderServiceImpl.class);
    
	@Resource
    private UrgeRepayOrderMapper urgeRepayOrderMapper;
	@Resource
	private UrgeRepayOrderLogMapper urgeRepayOrderLogMapper;
    @Resource
    private ClBorrowMapper clBorrowMapper;
    @Resource
    private BorrowRepayMapper borrowRepayMapper;
    @Resource
    private UserBaseInfoMapper userBaseinfoMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    
	@Override
	public BaseMapper<UrgeRepayOrder, Long> getMapper() {
		return urgeRepayOrderMapper;
	}

	/**
	 * 将查询条件中level转换为penaltyDay区间
	 * @param params
	 * @return
	 */
	private Map<String, Object> levelAsPenaltyDay(Map<String, Object> params){
		String level = StringUtil.isNull(params.get("level"));
		if(StringUtil.isBlank(level)){
			return params;
		}
		
		String penaltyDayLevel = Global.getValue("penalty_day_level");
		String[] penaltyDays = penaltyDayLevel.split(",");
		int penaltyDayOne = Integer.parseInt(penaltyDays[0]);
		int penaltyDayTwo = Integer.parseInt(penaltyDays[1]);
		if (StringUtil.isNotBlank(level) && "M1".equals(level)) {
			params.put("penaltyDayOne", penaltyDayOne);
		} else if (StringUtil.isNotBlank(level) && "M2".equals(level)) {
			params.put("penaltyDayOne", penaltyDayOne);
			params.put("penaltyDayTwo", penaltyDayTwo);
		} else if (StringUtil.isNotBlank(level) && "M3".equals(level)) {
			params.put("penaltyDayTwo", penaltyDayTwo);
		}
		return params;
	}
	
	@Override
	public Page<UrgeRepayOrder> list(Map<String, Object> params, int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<UrgeRepayOrder> list = urgeRepayOrderMapper.listSelective(levelAsPenaltyDay(params));
		return (Page<UrgeRepayOrder>)list;
	}
	
 
	public Page<UrgeRepayOrderModel> listModel(Map<String, Object> params, int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<UrgeRepayOrderModel> list = urgeRepayOrderMapper.listModel(levelAsPenaltyDay(params));
		return (Page<UrgeRepayOrderModel>) list;
	}


	@Override
	public int orderAllotUser(Map<String, Object> params) {
		return urgeRepayOrderMapper.updateSuccess(params);
	}


	@Override
	public Map<String, Object> saveOrder(Long id) {
		Map<String, Object> result=new HashMap<String, Object>();
		Borrow b=clBorrowMapper.findByPrimary(id);
		if(b!=null){
			//是否逾期标判断
			if(b.getState().equals(BorrowModel.STATE_DELAY)){
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("borrowId", b.getId());
				List<UrgeRepayOrder> list = urgeRepayOrderMapper.listSelective(params);
				if(list.size()<=0){
					UrgeRepayOrder order=new UrgeRepayOrder();
					order.setBorrowId(b.getId());
					order.setOrderNo(b.getOrderNo());
					order.setBorrowTime(b.getCreateTime());
					order.setTimeLimit(b.getTimeLimit());
				 
				    params = new HashMap<String, Object>();
					params.put("userId", b.getUserId());
					UserBaseInfo user=userBaseinfoMapper.findSelective(params);
					if(user!=null){
					order.setPhone(user.getPhone());
					order.setBorrowName(user.getRealName());
					}
					params = new HashMap<String, Object>();
					params.put("borrowId", b.getId());
				    BorrowRepay br=borrowRepayMapper.findByBorrowIdState(params);
				    if(br!=null){
				    order.setAmount(br.getAmount());
				    order.setRepayTime(br.getRepayTime());
					order.setPenaltyDay(Long.valueOf(br.getPenaltyDay()));
					order.setPenaltyAmout(br.getPenaltyAmout());
				    }
				    
					order.setState(UrgeRepayOrderModel.STATE_ORDER_PRE); 
					order.setCreateTime(new Date());
					order.setCount(Long.valueOf(0));
			  
					urgeRepayOrderMapper.save(order);
					
					result.put("code",  Constant.SUCCEED_CODE_VALUE);
					result.put("msg", "提交成功");
					return result;
				}else{
					result.put("code",  Constant.FAIL_CODE_VALUE);
					result.put("msg", "已存在催收订单中，请勿重复添加");	
				}
			}else{
				result.put("code",  Constant.FAIL_CODE_VALUE);
				result.put("msg", "借款信息未逾期");	
			}
		}else{
			result.put("code",  Constant.FAIL_CODE_VALUE);
			result.put("msg", "借款信息不存在");	
		}
		return result;
	}

	@Override
	public List<UrgeRepayOrder> listAll(HashMap<String, Object> hashMap) {
		List<UrgeRepayOrder> list = urgeRepayOrderMapper.listSelective(hashMap);
		return list;
	}

	@Override
	public Page<UrgeRepayCountModel> memberCount(Map<String, Object> params, int current, int pageSize) {
		params = params == null ? new HashMap<String, Object>() : params;
		params.put("roleNid", "collectionSpecialist");
		
		PageHelper.startPage(current, pageSize);
		List<UrgeRepayCountModel> modelList = urgeRepayOrderMapper.listSysUserByRole(params);
		for (UrgeRepayCountModel model : modelList) {
			long userId = model.getUserId();
			
			params.clear();
			params.put("userId", userId);
			model.setCount(urgeRepayOrderMapper.countOrder(params));
			
			params.put("state", UrgeRepayOrderModel.STATE_ORDER_WAIT);
			model.setWaitCount(urgeRepayOrderMapper.countOrder(params));
			
			params.put("state", UrgeRepayOrderModel.STATE_ORDER_SUCCESS);
			model.setSuccessCount(urgeRepayOrderMapper.countOrder(params));
			
			Date yester = DateUtil.rollDay(DateUtil.getNow(), -1);
			params.put("startTime", DateUtil.getDayStartTime(yester));
			params.put("endTime", DateUtil.getDayEndTime(yester));
			model.setYesterdayCount(urgeRepayOrderLogMapper.countLog(params));
			
		}
		
		return (Page<UrgeRepayCountModel>)modelList;
	}


	@Override
	public List<UrgeRepayCountModel> orderCount(Map<String, Object> params) {
		List<UrgeRepayCountModel> list = null;
		try {
			list = new ArrayList<>();
			Date startTime = new Date();
			Date endTime = new Date();
			List<Date> dateList = null;
			if (params==null) {
				startTime = DateUtil.getDateBefore(-10, endTime);
				dateList = dateList(startTime, endTime);
			}else {
				endTime = DateUtil.valueOf(params.get("afterTime").toString());
				startTime = DateUtil.valueOf(params.get("beforeTime").toString());
				dateList = dateList(startTime, endTime);
			}
			for (Date date: dateList) {
				Map<String,Object> map = new HashMap<>();
				map.put("date", date);
				UrgeRepayCountModel urcModel = new UrgeRepayCountModel();
				urcModel.setCreateTime(date);
				//累计统计
				String allOrder = urgeRepayOrderMapper.allOrderSum(map);
				urcModel.setAllOrderCount(allOrder!=null?Integer.parseInt(allOrder):0);
				
				String allSuccess = urgeRepayOrderMapper.allSuccessSum(map);
				urcModel.setAllSuccessCount(allSuccess!=null?Integer.parseInt(allSuccess):0);
				
				String allFail = urgeRepayOrderMapper.allFailSum(map);
				urcModel.setAllFailCount(allFail!=null?Integer.parseInt(allFail):0);
				
				if (urcModel.getAllSuccessCount()>urcModel.getAllOrderCount()) {
					urcModel.setAllBackRate(100.00);
				}else if(urcModel.getAllOrderCount()>0){
					double allBackRate = urcModel.getAllSuccessCount()/urcModel.getAllOrderCount();
					if (allBackRate*100>100) {
						urcModel.setAllBackRate(100.00);
					}else {
						urcModel.setAllBackRate(BigDecimalUtil.decimal((allBackRate*100), 2));
					}
				}
				//今日统计
				String newOrder = urgeRepayOrderMapper.newOrderByUser(map);
				urcModel.setOrderCount(newOrder!=null?Integer.parseInt(newOrder):0);
				String repayOrder = urgeRepayOrderMapper.repayOrderByUser(map);
				urcModel.setPromisCount(repayOrder!=null?Integer.parseInt(repayOrder):0);
				String successOrder = urgeRepayOrderMapper.successOrderByUser(map);
				urcModel.setSuccessCount(successOrder!=null?Integer.parseInt(successOrder):0);
				String failOrder = urgeRepayOrderMapper.failOrderByUser(map);
				urcModel.setFailCount(failOrder!=null?Integer.parseInt(failOrder):0);
				String count = urgeRepayOrderMapper.countByUser(map);
				urcModel.setCount(count!=null?Integer.parseInt(count):0);
				urcModel.setBackRate(0.00);
				if (urcModel.getSuccessCount()>urcModel.getOrderCount()) {
					urcModel.setBackRate(100.00);
				}else if(urcModel.getOrderCount()>0){
					double backRate = urcModel.getSuccessCount()/urcModel.getOrderCount();
					if (backRate>100) {
						urcModel.setBackRate(100.00);
					}else {
						urcModel.setBackRate(BigDecimalUtil.decimal((backRate*100), 2));
					}
				}
				
				list.add(urcModel);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return list;
	}


	@Override
	public List<UrgeRepayCountModel> urgeCount(Map<String, Object> params) {
		List<UrgeRepayCountModel> list = null;
		try {
			list = new ArrayList<>();
			Date startTime = new Date();
			Date endTime = new Date();
			List<Date> dateList = null;
			if (params==null) {
				startTime = DateUtil.getDateBefore(-10, endTime);
				dateList = dateList(startTime, endTime);
			}else {
				endTime = DateUtil.valueOf(params.get("afterTime").toString());
				startTime = DateUtil.valueOf(params.get("beforeTime").toString());
				dateList = dateList(startTime, endTime);
			}
			for (int i = 0; i < dateList.size(); i++) {
				Map<String,Object> map = new HashMap<>();
				map.put("date", dateList.get(i));
				UrgeRepayCountModel urcModel = new UrgeRepayCountModel();
				urcModel.setCreateTime(dateList.get(i));
				String allOrderCount = urgeRepayOrderMapper.allOrderCount(map);
				urcModel.setOrderCount(Integer.valueOf(allOrderCount==null?"0":allOrderCount));
				String successCount = urgeRepayOrderMapper.successCount(map);
				urcModel.setSuccessCount(Integer.valueOf(successCount==null?"0":successCount));
				String Count = urgeRepayOrderMapper.count(map);
				urcModel.setCount(Integer.valueOf(Count==null?"0":Count));
				if(urcModel.getOrderCount()==0){
					urcModel.setBackRate(0);
				}else{
					urcModel.setBackRate(BigDecimalUtil.decimal(
					Double.valueOf(urcModel.getSuccessCount())/Double.valueOf(urcModel.getOrderCount())*100, 2));
				}
				
				list.add(urcModel);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return list;
	}


	@Override
	public List<UrgeRepayCountModel> memberDayCount(Map<String, Object> params) {
		List<UrgeRepayCountModel> list = null;
		try {
			list = new ArrayList<>();
			Date startTime = new Date();
			Date endTime = new Date();
			List<Date> dateList = null;
			if (params==null) {
				startTime = DateUtil.getDateBefore(-10, endTime);
				dateList = dateList(startTime, endTime);
			}else {
				endTime = DateUtil.valueOf(params.get("afterTime").toString());
				startTime = DateUtil.valueOf(params.get("beforeTime").toString());
				dateList = dateList(startTime, endTime);
			}
			for (Date date : dateList) {
				Map<String,Object> map = new HashMap<>();
				List<UrgeRepayOrder> uroList = urgeRepayOrderMapper.listOrder(map);
				for (UrgeRepayOrder urgeRepayOrder : uroList) {
					if (StringUtil.isNull(urgeRepayOrder.getUserName())!=null) {
						UrgeRepayCountModel urcModel = new UrgeRepayCountModel();
						map.put("date", date);
						map.put("userId", urgeRepayOrder.getUserId());
						urcModel.setName(urgeRepayOrder.getUserName());
						String newOrder = urgeRepayOrderMapper.newOrderByUser(map);
						urcModel.setOrderCount(newOrder!=null?Integer.parseInt(newOrder):0);
						String repayOrder = urgeRepayOrderMapper.repayOrderByUser(map);
						urcModel.setPromisCount(repayOrder!=null?Integer.parseInt(repayOrder):0);
						String successOrder = urgeRepayOrderMapper.successOrderByUser(map);
						urcModel.setSuccessCount(successOrder!=null?Integer.parseInt(successOrder):0);
						String failOrder = urgeRepayOrderMapper.failOrderByUser(map);
						urcModel.setFailCount(failOrder!=null?Integer.parseInt(failOrder):0);
						String count = urgeRepayOrderMapper.countByUser(map);
						urcModel.setCount(count!=null?Integer.parseInt(count):0);
						urcModel.setBackRate(0.00);
						if (urcModel.getSuccessCount()>urcModel.getOrderCount()) {
							urcModel.setBackRate(100.00);
						}else if(urcModel.getOrderCount()>0){
							double backRate = urcModel.getSuccessCount()/urcModel.getOrderCount();
							if (backRate>100) {
								urcModel.setBackRate(100.00);
							}else {
								urcModel.setBackRate(BigDecimalUtil.decimal((backRate*100), 2));
							}
						}
						urcModel.setCreateTime(date);
						list.add(urcModel);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return list;
	}


	@Override
	public UrgeRepayOrder findByBorrowId(long borrowId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("borrowId", borrowId);
		return urgeRepayOrderMapper.findSelective(map);
	}


	@Override
	public int updateLate(Map<String, Object> uroMap) {
		return urgeRepayOrderMapper.updateSelective(uroMap);
	}


	@Override
	public UrgeRepayOrder findOrderByMap(Map<String, Object> orderMap) {
		// TODO Auto-generated method stub
		return urgeRepayOrderMapper.findSelective(orderMap);
	}


	@Override
	public List<?> listUrgeRepayOrder(Map<String, Object> params) {
		List<UrgeRepayOrder> list = urgeRepayOrderMapper.listSelective(params);
		for (UrgeRepayOrder uro : list) {
			uro.setState(UrgeRepayOrderModel.change(uro.getState()));
		}
		return list;
	}


	@Override
	public List<?> listUrgeLog(Map<String, Object> params) {
		List<UrgeRepayOrderModel> list = urgeRepayOrderMapper
				.listModel(params);
		for (UrgeRepayOrderModel uroModel : list) {
			uroModel.setState(UrgeRepayOrderModel.change(uroModel.getState()));
			switch (uroModel.getWay()) {
			case "10":
				uroModel.setWay("电话");
				break;
			case "20":
				uroModel.setWay("邮件");
				break;
			case "30":
				uroModel.setWay("短信");
				break;
			case "40":
				uroModel.setWay("现场沟通");
				break;
			case "50":
				uroModel.setWay("其他");
				break;
			}
		}
		return list;
	}
	
	/**
	 * 返回时间集合
	 * @return
	 * @throws Exception
	 */
	private List<Date> dateList(Date startTime,Date endTime) throws Exception{
		List<Date> lists=new ArrayList<Date>();
		if(startTime.getTime()==endTime.getTime()){
			lists.add(endTime);
		}else{
			lists = DateUtil.dateSplit(startTime, endTime);
		}
		if (!lists.isEmpty()) {
		    return lists;
		}
		return null;
	}
	
}