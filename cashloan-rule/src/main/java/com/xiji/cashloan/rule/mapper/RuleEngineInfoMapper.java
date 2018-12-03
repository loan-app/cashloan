package com.xiji.cashloan.rule.mapper;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.rule.domain.RuleEngineInfo;

/**
 * 规则评分设置管理Dao
 * 
 * @author jdd
 * @version 1.0.0
 * @date 2017-01-03 17:28:16
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface RuleEngineInfoMapper extends BaseMapper<RuleEngineInfo,Long> {

	int insert(RuleEngineInfo info);

	int deleteInfoByRuleId(long id);

    

}
