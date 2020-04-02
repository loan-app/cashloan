package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.cl.mapper.MagicReqLogMapper;
import com.xiji.cashloan.cl.domain.MagicReqLog;
import com.xiji.cashloan.cl.service.MagicReqLogService;


/**
 * 魔杖2.0请求记录ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-06 15:35:04
 */
 
@Service("magicReqLogService")
public class MagicReqLogServiceImpl extends BaseServiceImpl<MagicReqLog, Long> implements MagicReqLogService {
	
    private static final Logger logger = LoggerFactory.getLogger(MagicReqLogServiceImpl.class);
   
    @Resource
    private MagicReqLogMapper magicReqLogMapper;

	@Override
	public BaseMapper<MagicReqLog, Long> getMapper() {
		return magicReqLogMapper;
	}
	
}