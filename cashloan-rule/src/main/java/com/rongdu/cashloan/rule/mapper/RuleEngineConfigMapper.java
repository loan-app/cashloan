package com.rongdu.cashloan.rule.mapper;

import java.util.List;
import java.util.Map;

import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;
import com.rongdu.cashloan.rule.domain.RuleEngineConfig;

/**
 * 规则引擎管理Dao
 * 
 * @author jdd
 * @version 1.0.0
 * @date 2016-12-12 17:25:31
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface RuleEngineConfigMapper extends BaseMapper<RuleEngineConfig,Long> {

	List<Map<String, Object>> findTable();

	List<Map<String, Object>> findColumnByName(Map<String, Object> map);

	int updateSelective(Map<String, Object> map);
	
	List<RuleEngineConfig> listSelective(Map<String, Object> search);

	int deleteByRuleId(Long id);

	int insert(RuleEngineConfig config);

	int updateByRuleEnginId(Map<String, Object> configMap);
	
	List<RuleEngineConfig> findRuleEnginConfigForBorrow(Map<String, Object> params);

	int deleteById(Long id);
	
	/**
     * 根据表名查询数据表信息
     */
	List<Map<String, Object>> findTableByName(Map<String, Object> paramMap);
	
}
