package com.xiji.cashloan.rc.service.impl;

import javax.annotation.Resource;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.rc.mapper.Zx91ReqLogMapper;
import com.xiji.cashloan.rc.service.Zx91ReqLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.rc.domain.Zx91ReqLog;


/**
 * 风控数据统计-（简）通话记录统计ServiceImpl
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.xiji.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("zx91ReqLogService")
public class Zx91ReqLogServiceImpl extends BaseServiceImpl<Zx91ReqLog, Long> implements Zx91ReqLogService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(Zx91ReqLogServiceImpl.class);
   
    @Resource
    private Zx91ReqLogMapper zx91ReqLogMapper;

	@Override
	public BaseMapper<Zx91ReqLog, Long> getMapper() {
		return zx91ReqLogMapper;
	}
	
}