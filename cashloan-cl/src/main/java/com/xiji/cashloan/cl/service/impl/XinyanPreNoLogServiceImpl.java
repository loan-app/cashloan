package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.XinyanPreNoLogMapper;
import com.xiji.cashloan.cl.domain.XinyanPreNoLog;
import com.xiji.cashloan.cl.service.XinyanPreNoLogService;


/**
 * 新颜获取预订单号记录ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2019-01-16 20:56:41
 */
 
@Service("xinyanPreNoLogService")
public class XinyanPreNoLogServiceImpl extends BaseServiceImpl<XinyanPreNoLog, Long> implements XinyanPreNoLogService {
	
    private static final Logger logger = LoggerFactory.getLogger(XinyanPreNoLogServiceImpl.class);
   
    @Resource
    private XinyanPreNoLogMapper xinyanPreNoLogMapper;

	@Override
	public BaseMapper<XinyanPreNoLog, Long> getMapper() {
		return xinyanPreNoLogMapper;
	}
	
}