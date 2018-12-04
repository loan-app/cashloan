package com.xiji.cashloan.rule.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.rule.domain.BorrowRuleConfig;

/**
 * 借款规则详细配置表Dao
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 */
@RDBatisDao
public interface BorrowRuleConfigMapper extends BaseMapper<BorrowRuleConfig,Long> {

	int deleteByBorrowRuleId(Map<String, Object> params);

	void deleteById(Long id);

	void deleteByMap(Map<String, Object> params);

	List<BorrowRuleConfig> findBorrowRuleId(Map<String, Object> paramMap);

    

}
