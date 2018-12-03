package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import com.xiji.cashloan.cl.domain.MagicFundInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.MagicFundInfoMapper;
import com.xiji.cashloan.cl.service.MagicFundInfoService;


/**
 * 魔杖2.0报告-基础信息表ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:05
 */
 
@Service("magicFundInfoService")
public class MagicFundInfoServiceImpl extends BaseServiceImpl<MagicFundInfo, Long> implements MagicFundInfoService {
	
    private static final Logger logger = LoggerFactory.getLogger(MagicFundInfoServiceImpl.class);
   
    @Resource
    private MagicFundInfoMapper magicFundInfoMapper;

	@Override
	public BaseMapper<MagicFundInfo, Long> getMapper() {
		return magicFundInfoMapper;
	}
	
}