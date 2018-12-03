package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import com.xiji.cashloan.cl.domain.MagicMultipointQueriedInfo;
import com.xiji.cashloan.cl.mapper.MagicMultipointQueriedInfoMapper;
import com.xiji.cashloan.cl.service.MagicMultipointQueriedInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;


/**
 * 魔杖2.0报告-基础信息表ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:03
 */
 
@Service("magicMultipointQueriedInfoService")
public class MagicMultipointQueriedInfoServiceImpl extends BaseServiceImpl<MagicMultipointQueriedInfo, Long> implements MagicMultipointQueriedInfoService {
	
    private static final Logger logger = LoggerFactory.getLogger(MagicMultipointQueriedInfoServiceImpl.class);
   
    @Resource
    private MagicMultipointQueriedInfoMapper magicMultipointQueriedInfoMapper;

	@Override
	public BaseMapper<MagicMultipointQueriedInfo, Long> getMapper() {
		return magicMultipointQueriedInfoMapper;
	}
	
}