package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.MagicSuspiciousOtherNameMapper;
import com.xiji.cashloan.cl.domain.MagicSuspiciousOtherName;
import com.xiji.cashloan.cl.service.MagicSuspiciousOtherNameService;


/**
 * 魔杖2.0报告-基础信息表ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:05
 */
 
@Service("magicSuspiciousOtherNameService")
public class MagicSuspiciousOtherNameServiceImpl extends BaseServiceImpl<MagicSuspiciousOtherName, Long> implements MagicSuspiciousOtherNameService {
	
    private static final Logger logger = LoggerFactory.getLogger(MagicSuspiciousOtherNameServiceImpl.class);
   
    @Resource
    private MagicSuspiciousOtherNameMapper magicSuspiciousOtherNameMapper;

	@Override
	public BaseMapper<MagicSuspiciousOtherName, Long> getMapper() {
		return magicSuspiciousOtherNameMapper;
	}
	
}