package com.rongdu.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rongdu.cashloan.cl.domain.OperatorRespDetail;
import com.rongdu.cashloan.cl.mapper.OperatorRespDetailMapper;
import com.rongdu.cashloan.cl.service.OperatorRespDetailService;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.service.impl.BaseServiceImpl;

import java.util.Map;


/**
 * 运营商认证通知详情表ServiceImpl
 * 
 * @author xx
 * @version 1.0.0
 * @date 2017-05-17 12:38:22
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("operatorRespDetailService")
public class OperatorRespDetailServiceImpl extends BaseServiceImpl<OperatorRespDetail, Long> implements OperatorRespDetailService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(OperatorRespDetailServiceImpl.class);
   
    @Resource
    private OperatorRespDetailMapper operatorRespDetailMapper;


	@Override
	public BaseMapper<OperatorRespDetail, Long> getMapper() {
		return operatorRespDetailMapper;
	}

	@Override
	public OperatorRespDetail getByTaskId(String taskId) {
		return operatorRespDetailMapper.getByTaskId(taskId);
	}

	@Override
	public int updateSelective(Map<String, Object> params) {
		return operatorRespDetailMapper.updateSelective(params);
	}
}