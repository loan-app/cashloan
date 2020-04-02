package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.PxReqLogMapper;
import com.xiji.cashloan.cl.domain.PxReqLog;
import com.xiji.cashloan.cl.service.PxReqLogService;


/**
 * 排序请求记录ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2019-06-04 17:42:11
 */
 
@Service("pxReqLogService")
public class PxReqLogServiceImpl extends BaseServiceImpl<PxReqLog, Long> implements PxReqLogService {
	
    private static final Logger logger = LoggerFactory.getLogger(PxReqLogServiceImpl.class);
   
    @Resource
    private PxReqLogMapper pxReqLogMapper;

	@Override
	public BaseMapper<PxReqLog, Long> getMapper() {
		return pxReqLogMapper;
	}
	
}