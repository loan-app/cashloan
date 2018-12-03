package com.rongdu.cashloan.cl.service.impl;

import com.rongdu.cashloan.cl.domain.OperatorNet;
import com.rongdu.cashloan.cl.mapper.OperatorNetMapper;
import com.rongdu.cashloan.cl.service.OperatorNetService;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 魔蝎运营商数据-流量详情ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-11-24 15:06:22
 */
 
@Service("operatorNetService")
public class OperatorNetServiceImpl extends BaseServiceImpl<OperatorNet, Long> implements OperatorNetService {
	
    private static final Logger logger = LoggerFactory.getLogger(OperatorNetServiceImpl.class);
   
    @Resource
    private OperatorNetMapper operatorNetMapper;

	@Override
	public BaseMapper<OperatorNet, Long> getMapper() {
		return operatorNetMapper;
	}
	
}