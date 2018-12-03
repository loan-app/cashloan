package com.xiji.cashloan.rule.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.rule.domain.RuleEngine;
import org.apache.ibatis.annotations.Param;

/**
 * 规则引擎管理Dao
 * 
 * @author jdd
 * @version 1.0.0
 * @date 2016-12-12 17:24:27
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 * 
 * 
 */
@RDBatisDao
public interface RuleEngineMapper extends BaseMapper<RuleEngine,Long> {
	/**
	 *  查询信息
	 */
    List<RuleEngine> listSelective(Map<String, Object> map);

	RuleEngine selectByPrimary(Long id);

	int insertId(RuleEngine rule);
	
	int updateSelective(Map<String, Object> map);
	
	/**
     * 自动审批查找需要比对的值
     * @param sql
     * @return
     */
    String findValidValue(@Param("statement") String statement);

	List<RuleEngine> listByPage(Map<String, Object> params);
}
