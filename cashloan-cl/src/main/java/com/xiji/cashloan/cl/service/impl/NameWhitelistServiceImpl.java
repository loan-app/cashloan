package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.NameWhitelistMapper;
import com.xiji.cashloan.cl.domain.NameWhitelist;
import com.xiji.cashloan.cl.service.NameWhitelistService;


/**
 * 白名单ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-14 20:55:28
 */
 
@Service("nameWhitelistService")
public class NameWhitelistServiceImpl extends BaseServiceImpl<NameWhitelist, Long> implements NameWhitelistService {
	
    private static final Logger logger = LoggerFactory.getLogger(NameWhitelistServiceImpl.class);
   
    @Resource
    private NameWhitelistMapper nameWhitelistMapper;

	@Override
	public BaseMapper<NameWhitelist, Long> getMapper() {
		return nameWhitelistMapper;
	}
	
}