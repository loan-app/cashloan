package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import com.xiji.cashloan.cl.mapper.MagicBankInfoMapper;
import com.xiji.cashloan.cl.service.MagicBankInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.domain.MagicBankInfo;


/**
 * 魔杖2.0报告-基础信息表ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:03
 */
 
@Service("magicBankInfoService")
public class MagicBankInfoServiceImpl extends BaseServiceImpl<MagicBankInfo, Long> implements MagicBankInfoService {
	
    private static final Logger logger = LoggerFactory.getLogger(MagicBankInfoServiceImpl.class);
   
    @Resource
    private MagicBankInfoMapper magicBankInfoMapper;

	@Override
	public BaseMapper<MagicBankInfo, Long> getMapper() {
		return magicBankInfoMapper;
	}
	
}