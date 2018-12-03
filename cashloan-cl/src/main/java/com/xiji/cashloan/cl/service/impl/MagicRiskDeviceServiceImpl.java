package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import com.xiji.cashloan.cl.service.MagicRiskDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.MagicRiskDeviceMapper;
import com.xiji.cashloan.cl.domain.MagicRiskDevice;


/**
 * 魔杖2.0报告-基础信息表ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:05
 */
 
@Service("magicRiskDeviceService")
public class MagicRiskDeviceServiceImpl extends BaseServiceImpl<MagicRiskDevice, Long> implements MagicRiskDeviceService {
	
    private static final Logger logger = LoggerFactory.getLogger(MagicRiskDeviceServiceImpl.class);
   
    @Resource
    private MagicRiskDeviceMapper magicRiskDeviceMapper;

	@Override
	public BaseMapper<MagicRiskDevice, Long> getMapper() {
		return magicRiskDeviceMapper;
	}
	
}