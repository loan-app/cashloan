package com.rongdu.cashloan.rule.service;

import java.util.List;
import java.util.Map;

import com.rongdu.cashloan.core.common.service.BaseService;
import com.rongdu.cashloan.rule.domain.RuleEngineInfo;

/**
 * 规则评分设置管理Service
 * 
 * @author jdd
 * @version 1.0.0
 * @date 2017-01-03 17:28:16
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface RuleEngineInfoService extends BaseService<RuleEngineInfo, Long>{
	/**
	 * 保存
	 * @param rule
	 * @param list
	 * @return
	 */
	int saveIntegralInfo(Map<String, Object> rule, List list);
	/**
	 * 查询
	 * @param search
	 * @return
	 */
	List<RuleEngineInfo> findByMap(Map<String, Object> search);
}
