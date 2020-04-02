package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.XinyanReqLogMapper;
import com.xiji.cashloan.cl.domain.XinyanReqLog;
import com.xiji.cashloan.cl.service.XinyanReqLogService;


/**
 * 新颜请求记录ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-10 17:45:00
 */
 
@Service("xinyanReqLogService")
public class XinyanReqLogServiceImpl extends BaseServiceImpl<XinyanReqLog, Long> implements XinyanReqLogService {
	
    private static final Logger logger = LoggerFactory.getLogger(XinyanReqLogServiceImpl.class);
   
    @Resource
    private XinyanReqLogMapper xinyanReqLogMapper;

	@Override
	public BaseMapper<XinyanReqLog, Long> getMapper() {
		return xinyanReqLogMapper;
	}
	
}