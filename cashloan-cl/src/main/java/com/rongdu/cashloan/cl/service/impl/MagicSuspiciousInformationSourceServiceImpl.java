package com.rongdu.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.service.impl.BaseServiceImpl;
import com.rongdu.cashloan.cl.mapper.MagicSuspiciousInformationSourceMapper;
import com.rongdu.cashloan.cl.domain.MagicSuspiciousInformationSource;
import com.rongdu.cashloan.cl.service.MagicSuspiciousInformationSourceService;


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