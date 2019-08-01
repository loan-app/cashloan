package com.xiji.cashloan.cl.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.xiji.cashloan.cl.domain.PayReqLog;
import com.xiji.cashloan.cl.model.ManagePayReqLogModel;
import org.springframework.stereotype.Service;

import tool.util.DateUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.mapper.PayReqLogMapper;
import com.xiji.cashloan.cl.service.PayReqLogService;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;


/**
 * 支付请求记录ServiceImpl
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("payReqLogService")
public class PayReqLogServiceImpl extends BaseServiceImpl<PayReqLog, Long> implements PayReqLogService {
	
    @Resource
    private PayReqLogMapper payReqLogMapper;

	@Override
	public boolean save(PayReqLog log) {
		log.setCreateTime(DateUtil.getNow());
		int result = payReqLogMapper.save(log);
		if (result > 0L) {
			return true;
		}
		return false;
	}

	@Override
	public PayReqLog findByOrderNo(String orderNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orderNo", orderNo);
		return payReqLogMapper.findSelective(paramMap);
	}

	/**
	 * 根据订单号查询请求记录
	 *
	 * @param
	 * @return
	 */
	@Override
	public PayReqLog findByHelipayUserId(String helipayUserId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("helipayUserId", helipayUserId);
		return payReqLogMapper.findSelective(paramMap);
	}



	@Override
	public Page<ManagePayReqLogModel> page(int current, int pageSize, Map<String, Object> searchMap) {
		PageHelper.startPage(current,pageSize);
		Page<ManagePayReqLogModel> page = (Page<ManagePayReqLogModel>) payReqLogMapper.page(searchMap);
		return page;
	}
	
	@Override
	public ManagePayReqLogModel findDetail(Long id){
		return payReqLogMapper.findDetail(id);
	}

	@Override
	public BaseMapper<PayReqLog, Long> getMapper() {
		return payReqLogMapper;
	}
	
}