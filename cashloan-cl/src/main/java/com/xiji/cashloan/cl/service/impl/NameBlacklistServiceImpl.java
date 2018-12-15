package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.NameBlacklistMapper;
import com.xiji.cashloan.cl.domain.NameBlacklist;
import com.xiji.cashloan.cl.service.NameBlacklistService;


/**
 * 黑名单ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-14 17:22:38
 */
 
@Service("nameBlacklistService")
public class NameBlacklistServiceImpl extends BaseServiceImpl<NameBlacklist, Long> implements NameBlacklistService {
	
    private static final Logger logger = LoggerFactory.getLogger(NameBlacklistServiceImpl.class);
   
    @Resource
    private NameBlacklistMapper nameBlacklistMapper;

	@Override
	public BaseMapper<NameBlacklist, Long> getMapper() {
		return nameBlacklistMapper;
	}
	
}