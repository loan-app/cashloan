package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.PinganReqLogMapper;
import com.xiji.cashloan.cl.domain.PinganReqLog;
import com.xiji.cashloan.cl.service.PinganReqLogService;


/**
 * 凭安请求记录ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-26 20:51:43
 */
 
@Service("pinganReqLogService")
public class PinganReqLogServiceImpl extends BaseServiceImpl<PinganReqLog, Long> implements PinganReqLogService {
	
    private static final Logger logger = LoggerFactory.getLogger(PinganReqLogServiceImpl.class);
   
    @Resource
    private PinganReqLogMapper pinganReqLogMapper;

	@Override
	public BaseMapper<PinganReqLog, Long> getMapper() {
		return pinganReqLogMapper;
	}
	
}