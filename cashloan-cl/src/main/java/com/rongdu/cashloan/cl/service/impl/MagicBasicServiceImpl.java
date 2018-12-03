package com.rongdu.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.service.impl.BaseServiceImpl;
import com.rongdu.cashloan.cl.mapper.MagicBasicMapper;
import com.rongdu.cashloan.cl.domain.MagicBasic;
import com.rongdu.cashloan.cl.service.MagicBasicService;


/**
 * 魔杖2.0报告-基础信息ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:26:56
 */
 
@Service("magicBasicService")
public class MagicBasicServiceImpl extends BaseServiceImpl<MagicBasic, Long> implements MagicBasicService {
	
    private static final Logger logger = LoggerFactory.getLogger(MagicBasicServiceImpl.class);
   
    @Resource
    private MagicBasicMapper magicBasicMapper;

	@Override
	public BaseMapper<MagicBasic, Long> getMapper() {
		return magicBasicMapper;
	}
	
}