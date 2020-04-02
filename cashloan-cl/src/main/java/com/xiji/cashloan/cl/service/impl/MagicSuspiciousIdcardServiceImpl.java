package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.MagicSuspiciousIdcardMapper;
import com.xiji.cashloan.cl.domain.MagicSuspiciousIdcard;
import com.xiji.cashloan.cl.service.MagicSuspiciousIdcardService;


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