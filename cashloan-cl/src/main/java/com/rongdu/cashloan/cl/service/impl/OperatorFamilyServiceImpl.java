package com.rongdu.cashloan.cl.service.impl;

import com.rongdu.cashloan.cl.domain.OperatorFamily;
import com.rongdu.cashloan.cl.mapper.OperatorFamilyMapper;
import com.rongdu.cashloan.cl.service.OperatorFamilyService;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 魔蝎运营商数据-亲情网ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-11-23 16:55:52
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("operatorFamilyService")
public class OperatorFamilyServiceImpl extends BaseServiceImpl<OperatorFamily, Long> implements OperatorFamilyService {
	
    private static final Logger logger = LoggerFactory.getLogger(OperatorFamilyServiceImpl.class);
   
    @Resource
    private OperatorFamilyMapper operatorFamilyMapper;

	@Override
	public BaseMapper<OperatorFamily, Long> getMapper() {
		return operatorFamilyMapper;
	}
	
}