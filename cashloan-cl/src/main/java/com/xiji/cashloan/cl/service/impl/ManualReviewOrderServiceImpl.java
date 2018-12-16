package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.model.ManualReviewCountModel;
import com.xiji.cashloan.cl.model.ManualReviewOrderModel;
import com.xiji.cashloan.core.common.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.ManualReviewOrderMapper;
import com.xiji.cashloan.cl.domain.ManualReviewOrder;
import com.xiji.cashloan.cl.service.ManualReviewOrderService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
}