package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import com.xiji.cashloan.cl.domain.MagicBasic;
import com.xiji.cashloan.cl.mapper.MagicBasicMapper;
import com.xiji.cashloan.cl.service.MagicBasicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;


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