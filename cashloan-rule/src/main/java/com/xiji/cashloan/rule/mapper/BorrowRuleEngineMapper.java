package com.xiji.cashloan.rule.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.rule.domain.BorrowRuleEngine;

/**
 * 借款规则管理Dao
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2016-12-20 10:22:30
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface BorrowRuleEngineMapper extends BaseMapper<BorrowRuleEngine,Long> {

	int deleteById(long id);
	
	List<BorrowRuleEngine> listByBorrowType(@Param("borrowType") String borrowType);

}
