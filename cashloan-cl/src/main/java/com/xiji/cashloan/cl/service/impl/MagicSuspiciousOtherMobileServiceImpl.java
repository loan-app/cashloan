package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import com.xiji.cashloan.cl.mapper.MagicSuspiciousOtherMobileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.domain.MagicSuspiciousOtherMobile;
import com.xiji.cashloan.cl.service.MagicSuspiciousOtherMobileService;


/**
 * 魔杖2.0报告-基础信息表ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:05
 */
 
@Service("magicSuspiciousOtherMobileService")
public class MagicSuspiciousOtherMobileServiceImpl extends BaseServiceImpl<MagicSuspiciousOtherMobile, Long> implements MagicSuspiciousOtherMobileService {
	
    private static final Logger logger = LoggerFactory.getLogger(MagicSuspiciousOtherMobileServiceImpl.class);
   
    @Resource
    private MagicSuspiciousOtherMobileMapper magicSuspiciousOtherMobileMapper;

	@Override
	public BaseMapper<MagicSuspiciousOtherMobile, Long> getMapper() {
		return magicSuspiciousOtherMobileMapper;
	}
	
}