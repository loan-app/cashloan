package com.rongdu.cashloan.rule.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;
import com.rongdu.cashloan.rule.domain.BorrowRuleEngine;

/**
 * 借款规则管理Dao
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2016-12-20 10:22:30
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface BorrowRuleEngineMapper extends BaseMapper<BorrowRuleEngine,Long> {

	int deleteById(long id);
	
	List<BorrowRuleEngine> listByBorrowType(@Param("borrowType") String borrowType);

}
