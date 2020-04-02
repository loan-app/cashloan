package com.xiji.cashloan.cl.service.impl;

import com.xiji.cashloan.cl.domain.MagicFraudulenceInfo;
import com.xiji.cashloan.cl.mapper.MagicFraudulenceInfoMapper;
import com.xiji.cashloan.cl.service.MagicFraudulenceInfoService;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 魔杖2.0-欺诈风险信息ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-05 00:52:58
 */
 
@Service("magicFraudulenceInfoService")
public class MagicFraudulenceInfoServiceImpl extends BaseServiceImpl<MagicFraudulenceInfo, Long> implements MagicFraudulenceInfoService {
	
    private static final Logger logger = LoggerFactory.getLogger(MagicFraudulenceInfoServiceImpl.class);
   
    @Resource
    private MagicFraudulenceInfoMapper magicFraudulenceInfoMapper;

	@Override
	public BaseMapper<MagicFraudulenceInfo, Long> getMapper() {
		return magicFraudulenceInfoMapper;
	}
	
}