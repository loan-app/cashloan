package com.xiji.cashloan.rule.mapper;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.rule.domain.BorrowScoreResult;

/**
 * 决策引擎执行记录Dao
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.xiji.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface BorrowScoreResultMapper extends BaseMapper<BorrowScoreResult, Long> {

    

}
