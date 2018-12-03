package com.xiji.cashloan.rule.mapper;

import java.util.Map;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.rule.domain.RuleInfo;

/**
 * 规则信息Dao
 * 
 * @author ctt
 * @version 1.0.0
 * @date 2016-12-20 13:58:48
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface RuleInfoMapper extends BaseMapper<RuleInfo,Long> {

	int delInfoById(Long id);

	int updateSelective(Map<String, Object> paramMap);


}
