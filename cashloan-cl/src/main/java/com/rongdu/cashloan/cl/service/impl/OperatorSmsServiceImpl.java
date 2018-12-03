package com.rongdu.cashloan.cl.service.impl;

import javax.annotation.Resource;

import com.rongdu.cashloan.cl.domain.OperatorSms;
import com.rongdu.cashloan.cl.mapper.OperatorSmsMapper;
import com.rongdu.cashloan.cl.service.OperatorSmsService;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * 魔蝎运营商数据-短信详情ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-11-24 14:59:54
 */
 
@Service("operatorSmsService")
public class OperatorSmsServiceImpl extends BaseServiceImpl<OperatorSms, Long> implements OperatorSmsService {
	
    private static final Logger logger = LoggerFactory.getLogger(OperatorSmsServiceImpl.class);
   
    @Resource
    private OperatorSmsMapper operatorSmsMapper;

	@Override
	public BaseMapper<OperatorSms, Long> getMapper() {
		return operatorSmsMapper;
	}
	
}