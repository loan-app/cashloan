package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import com.xiji.cashloan.cl.domain.MagicUntrustedDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.MagicUntrustedDetailMapper;
import com.xiji.cashloan.cl.service.MagicUntrustedDetailService;


/**
 * 魔杖2.0报告-基础信息表ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:03
 */
 
@Service("magicUntrustedDetailService")
public class MagicUntrustedDetailServiceImpl extends BaseServiceImpl<MagicUntrustedDetail, Long> implements MagicUntrustedDetailService {
	
    private static final Logger logger = LoggerFactory.getLogger(MagicUntrustedDetailServiceImpl.class);
   
    @Resource
    private MagicUntrustedDetailMapper magicUntrustedDetailMapper;

	@Override
	public BaseMapper<MagicUntrustedDetail, Long> getMapper() {
		return magicUntrustedDetailMapper;
	}
	
}