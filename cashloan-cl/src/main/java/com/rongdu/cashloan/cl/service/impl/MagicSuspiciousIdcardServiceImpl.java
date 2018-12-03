package com.rongdu.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.service.impl.BaseServiceImpl;
import com.rongdu.cashloan.cl.mapper.MagicSuspiciousIdcardMapper;
import com.rongdu.cashloan.cl.domain.MagicSuspiciousIdcard;
import com.rongdu.cashloan.cl.service.MagicSuspiciousIdcardService;


/**
 * 魔杖2.0报告-基础信息表ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:04
 */
 
@Service("magicSuspiciousIdcardService")
public class MagicSuspiciousIdcardServiceImpl extends BaseServiceImpl<MagicSuspiciousIdcard, Long> implements MagicSuspiciousIdcardService {
	
    private static final Logger logger = LoggerFactory.getLogger(MagicSuspiciousIdcardServiceImpl.class);
   
    @Resource
    private MagicSuspiciousIdcardMapper magicSuspiciousIdcardMapper;

	@Override
	public BaseMapper<MagicSuspiciousIdcard, Long> getMapper() {
		return magicSuspiciousIdcardMapper;
	}
	
}