package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import com.xiji.cashloan.cl.domain.MagicUntrusted;
import com.xiji.cashloan.cl.mapper.MagicUntrustedMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.service.MagicUntrustedService;


/**
 * 魔杖2.0报告-基础信息表ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:04
 */
 
@Service("magicUntrustedService")
public class MagicUntrustedServiceImpl extends BaseServiceImpl<MagicUntrusted, Long> implements MagicUntrustedService {
	
    private static final Logger logger = LoggerFactory.getLogger(MagicUntrustedServiceImpl.class);
   
    @Resource
    private MagicUntrustedMapper magicUntrustedMapper;

	@Override
	public BaseMapper<MagicUntrusted, Long> getMapper() {
		return magicUntrustedMapper;
	}
	
}