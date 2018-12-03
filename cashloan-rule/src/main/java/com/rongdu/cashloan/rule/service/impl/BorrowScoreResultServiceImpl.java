package com.rongdu.cashloan.rule.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.service.impl.BaseServiceImpl;
import com.rongdu.cashloan.rule.mapper.BorrowScoreResultMapper;
import com.rongdu.cashloan.rule.domain.BorrowScoreResult;
import com.rongdu.cashloan.rule.service.BorrowScoreResultService;

/**
 * 决策引擎执行记录ServiceImpl
 * 
 * @author caitt
 * @version 1.0.0
 * @date 2017-06-23 15:47:53
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("borrowScoreResultService")
public class BorrowScoreResultServiceImpl extends BaseServiceImpl<BorrowScoreResult, Long> implements BorrowScoreResultService {
	
    private static final Logger logger = LoggerFactory.getLogger(BorrowScoreResultServiceImpl.class);
   
    @Resource
    private BorrowScoreResultMapper borrowScoreResultMapper;

	@Override
	public BaseMapper<BorrowScoreResult, Long> getMapper() {
		return borrowScoreResultMapper;
	}
	
}