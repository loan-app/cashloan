package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.MagicSuspiciousInformationSourceMapper;
import com.xiji.cashloan.cl.domain.MagicSuspiciousInformationSource;
import com.xiji.cashloan.cl.service.MagicSuspiciousInformationSourceService;


/**
 * 魔杖2.0报告-基础信息表ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:04
 */
 
@Service("magicSuspiciousInformationSourceService")
public class MagicSuspiciousInformationSourceServiceImpl extends BaseServiceImpl<MagicSuspiciousInformationSource, Long> implements MagicSuspiciousInformationSourceService {
	
    private static final Logger logger = LoggerFactory.getLogger(MagicSuspiciousInformationSourceServiceImpl.class);
   
    @Resource
    private MagicSuspiciousInformationSourceMapper magicSuspiciousInformationSourceMapper;

	@Override
	public BaseMapper<MagicSuspiciousInformationSource, Long> getMapper() {
		return magicSuspiciousInformationSourceMapper;
	}
	
}