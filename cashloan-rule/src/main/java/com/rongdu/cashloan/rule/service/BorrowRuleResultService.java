package com.rongdu.cashloan.rule.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.rongdu.cashloan.core.common.service.BaseService;
import com.rongdu.cashloan.rule.domain.BorrowRuleResult;

/**
 * 规则匹配结果Service
 * 
 * @author ctt
 * @version 1.0.0
 * @date 2016-12-21 15:04:28
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface BorrowRuleResultService extends BaseService<BorrowRuleResult, Long>{

	/**
	 * 查询匹配结果分页列表
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<BorrowRuleResult> borrowRuleResult(Map<String, Object> params, int currentPage, int pageSize);
}
