package com.rongdu.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.service.impl.BaseServiceImpl;
import com.rongdu.cashloan.cl.mapper.MagicMobileContactMapper;
import com.rongdu.cashloan.cl.domain.MagicMobileContact;
import com.rongdu.cashloan.cl.service.MagicMobileContactService;


/**
 * 魔杖2.0报告-基础信息表ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:03
 */
 
@Service("magicMobileContactService")
public class MagicMobileContactServiceImpl extends BaseServiceImpl<MagicMobileContact, Long> implements MagicMobileContactService {
	
    private static final Logger logger = LoggerFactory.getLogger(MagicMobileContactServiceImpl.class);
   
    @Resource
    private MagicMobileContactMapper magicMobileContactMapper;

	@Override
	public BaseMapper<MagicMobileContact, Long> getMapper() {
		return magicMobileContactMapper;
	}
	
}