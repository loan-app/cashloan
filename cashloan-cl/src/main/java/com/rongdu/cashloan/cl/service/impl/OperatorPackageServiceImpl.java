package com.rongdu.cashloan.cl.service.impl;


import com.rongdu.cashloan.cl.domain.OperatorPackage;
import com.rongdu.cashloan.cl.mapper.OperatorPackageMapper;
import com.rongdu.cashloan.cl.service.OperatorPackageService;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 魔蝎运营商数据-套餐信息ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-11-23 16:15:44
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("operatorPackageService")
public class OperatorPackageServiceImpl extends BaseServiceImpl<OperatorPackage, Long> implements OperatorPackageService {
	
    private static final Logger logger = LoggerFactory.getLogger(OperatorPackageServiceImpl.class);
   
    @Resource
    private OperatorPackageMapper operatorPackageMapper;

	@Override
	public BaseMapper<OperatorPackage, Long> getMapper() {
		return operatorPackageMapper;
	}
	
}