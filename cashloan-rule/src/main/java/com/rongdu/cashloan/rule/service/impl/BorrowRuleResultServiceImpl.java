package com.rongdu.cashloan.rule.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.service.impl.BaseServiceImpl;
import com.rongdu.cashloan.rule.domain.BorrowRuleResult;
import com.rongdu.cashloan.rule.mapper.BorrowRuleResultMapper;
import com.rongdu.cashloan.rule.service.BorrowRuleResultService;


/**
 * 规则匹配结果ServiceImpl
 * 
 * @author ctt
 * @version 1.0.0
 * @date 2016-12-21 15:04:28
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("borrowRuleResultService")
public class BorrowRuleResultServiceImpl extends BaseServiceImpl<BorrowRuleResult, Long> implements BorrowRuleResultService {
	
    private static final Logger logger = LoggerFactory.getLogger(BorrowRuleResultServiceImpl.class);
   
    @Resource
    private BorrowRuleResultMapper borrowRuleResultMapper;

	@Override
	public BaseMapper<BorrowRuleResult, Long> getMapper() {
		return borrowRuleResultMapper;
	}

	@Override
	public Page<BorrowRuleResult> borrowRuleResult(Map<String, Object> params, int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<BorrowRuleResult> list = borrowRuleResultMapper.listSelective(params);
		return (Page<BorrowRuleResult>)list;
	}
	
}