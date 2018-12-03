package com.rongdu.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.service.impl.BaseServiceImpl;
import com.rongdu.cashloan.cl.mapper.MagicLoanBehaviorAnalysisMapper;
import com.rongdu.cashloan.cl.domain.MagicLoanBehaviorAnalysis;
import com.rongdu.cashloan.cl.service.MagicLoanBehaviorAnalysisService;


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