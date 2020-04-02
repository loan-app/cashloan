package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.MagicLoanBehaviorAnalysisMapper;
import com.xiji.cashloan.cl.domain.MagicLoanBehaviorAnalysis;
import com.xiji.cashloan.cl.service.MagicLoanBehaviorAnalysisService;


/**
 * 魔杖2.0报告-基础信息表ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:04
 */
 
@Service("magicLoanBehaviorAnalysisService")
public class MagicLoanBehaviorAnalysisServiceImpl extends BaseServiceImpl<MagicLoanBehaviorAnalysis, Long> implements MagicLoanBehaviorAnalysisService {
	
    private static final Logger logger = LoggerFactory.getLogger(MagicLoanBehaviorAnalysisServiceImpl.class);
   
    @Resource
    private MagicLoanBehaviorAnalysisMapper magicLoanBehaviorAnalysisMapper;

	@Override
	public BaseMapper<MagicLoanBehaviorAnalysis, Long> getMapper() {
		return magicLoanBehaviorAnalysisMapper;
	}
	
}