package com.rongdu.cashloan.cl.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tool.util.DateUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rongdu.cashloan.cl.domain.PayRespLog;
import com.rongdu.cashloan.cl.mapper.PayRespLogMapper;
import com.rongdu.cashloan.cl.model.ManagePayRespLogModel;
import com.rongdu.cashloan.cl.service.PayRespLogService;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.service.impl.BaseServiceImpl;


/**
 * 支付响应记录ServiceImpl
 * 
 * @author gc
 * @version 1.0.0
 * @date 2017-03-07 16:18:10
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("payRespLogService")
public class PayRespLogServiceImpl extends BaseServiceImpl<PayRespLog, Long> implements PayRespLogService {
	
    @Resource
    private PayRespLogMapper payRespLogMapper;

    
	@Override
	public boolean save(PayRespLog log) {
		log.setCreateTime(DateUtil.getNow());
		int result = payRespLogMapper.save(log);
		if (result > 0L) {
			return true;
		}
		return false;
	}

	
	@Override
	public Page<ManagePayRespLogModel> page(int current, int pageSize,
			Map<String, Object> searchMap) {
		PageHelper.startPage(current,pageSize);
		Page<ManagePayRespLogModel>  page = (Page<ManagePayRespLogModel>) payRespLogMapper.page(searchMap);
		return page;
	}

	@Override
	public ManagePayRespLogModel findDetail(Long id) {
		return payRespLogMapper.findDetail(id);
	}
	
	@Override
	public BaseMapper<PayRespLog, Long> getMapper() {
		return payRespLogMapper;
	}
}