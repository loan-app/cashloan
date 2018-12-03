package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import com.xiji.cashloan.cl.domain.MagicSuspiciousDevice;
import com.xiji.cashloan.cl.mapper.MagicSuspiciousDeviceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.service.MagicSuspiciousDeviceService;


/**
 * 魔杖2.0报告-基础信息表ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:04
 */
 
@Service("magicSuspiciousDeviceService")
public class MagicSuspiciousDeviceServiceImpl extends BaseServiceImpl<MagicSuspiciousDevice, Long> implements MagicSuspiciousDeviceService {
	
    private static final Logger logger = LoggerFactory.getLogger(MagicSuspiciousDeviceServiceImpl.class);
   
    @Resource
    private MagicSuspiciousDeviceMapper magicSuspiciousDeviceMapper;

	@Override
	public BaseMapper<MagicSuspiciousDevice, Long> getMapper() {
		return magicSuspiciousDeviceMapper;
	}
	
}