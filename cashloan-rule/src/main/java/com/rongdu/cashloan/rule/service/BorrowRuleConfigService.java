package com.rongdu.cashloan.rule.service;

import java.util.List;
import java.util.Map;

import com.rongdu.cashloan.core.common.service.BaseService;
import com.rongdu.cashloan.rule.domain.BorrowRuleConfig;
import com.rongdu.cashloan.rule.model.BorrowRuleConfigModel;

/**
 * 借款规则详细配置表Service
 * 
 * @author jdd
 * @version 1.0.0
 * @date 2017-04-21 15:23:19
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 创新一部：rdc@erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface BorrowRuleConfigService extends BaseService<BorrowRuleConfig, Long>{

	List<BorrowRuleConfigModel> findConfig(Map<String, Object> params);

	void deleteByMap(Map<String, Object> map);

	List<BorrowRuleConfig> findBorrowRuleId(Map<String, Object> paramMap);

}
