package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.MagicSuspiciousMobileMapper;
import com.xiji.cashloan.cl.domain.MagicSuspiciousMobile;
import com.xiji.cashloan.cl.service.MagicSuspiciousMobileService;


/**
 * 魔杖2.0报告-基础信息表ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:04
 */
 
@Service("magicSuspiciousMobileService")
public class MagicSuspiciousMobileServiceImpl extends BaseServiceImpl<MagicSuspiciousMobile, Long> implements MagicSuspiciousMobileService {
	
    private static final Logger logger = LoggerFactory.getLogger(MagicSuspiciousMobileServiceImpl.class);
   
    @Resource
    private MagicSuspiciousMobileMapper magicSuspiciousMobileMapper;

	@Override
	public BaseMapper<MagicSuspiciousMobile, Long> getMapper() {
		return magicSuspiciousMobileMapper;
	}
	
}