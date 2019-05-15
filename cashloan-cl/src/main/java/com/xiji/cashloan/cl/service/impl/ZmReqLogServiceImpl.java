package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.ZmReqLogMapper;
import com.xiji.cashloan.cl.domain.ZmReqLog;
import com.xiji.cashloan.cl.service.ZmReqLogService;


/**
 * 指迷请求记录ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2019-05-05 15:40:01
 */
 
@Service("zmReqLogService")
public class ZmReqLogServiceImpl extends BaseServiceImpl<ZmReqLog, Long> implements ZmReqLogService {
	
    private static final Logger logger = LoggerFactory.getLogger(ZmReqLogServiceImpl.class);
   
    @Resource
    private ZmReqLogMapper zmReqLogMapper;

	@Override
	public BaseMapper<ZmReqLog, Long> getMapper() {
		return zmReqLogMapper;
	}
	
}