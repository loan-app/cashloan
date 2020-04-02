package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.YixinFraudMapper;
import com.xiji.cashloan.cl.domain.YixinFraud;
import com.xiji.cashloan.cl.service.YixinFraudService;


/**
 * 宜信欺诈甄别ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-27 17:12:58
 */
 
@Service("yixinFraudService")
public class YixinFraudServiceImpl extends BaseServiceImpl<YixinFraud, Long> implements YixinFraudService {
	
    private static final Logger logger = LoggerFactory.getLogger(YixinFraudServiceImpl.class);
   
    @Resource
    private YixinFraudMapper yixinFraudMapper;

	@Override
	public BaseMapper<YixinFraud, Long> getMapper() {
		return yixinFraudMapper;
	}
	
}