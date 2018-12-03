package com.rongdu.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.service.impl.BaseServiceImpl;
import com.rongdu.cashloan.cl.mapper.MagicRiskDeviceMapper;
import com.rongdu.cashloan.cl.domain.MagicRiskDevice;
import com.rongdu.cashloan.cl.service.MagicRiskDeviceService;


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