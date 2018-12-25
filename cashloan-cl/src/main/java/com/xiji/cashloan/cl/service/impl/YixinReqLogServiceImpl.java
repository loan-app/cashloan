package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.YixinReqLogMapper;
import com.xiji.cashloan.cl.domain.YixinReqLog;
import com.xiji.cashloan.cl.service.YixinReqLogService;


/**
 * 宜信请求记录ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-25 16:25:46
 */
 
@Service("yixinReqLogService")
public class YixinReqLogServiceImpl extends BaseServiceImpl<YixinReqLog, Long> implements YixinReqLogService {
	
    private static final Logger logger = LoggerFactory.getLogger(YixinReqLogServiceImpl.class);
   
    @Resource
    private YixinReqLogMapper yixinReqLogMapper;

	@Override
	public BaseMapper<YixinReqLog, Long> getMapper() {
		return yixinReqLogMapper;
	}
	
}