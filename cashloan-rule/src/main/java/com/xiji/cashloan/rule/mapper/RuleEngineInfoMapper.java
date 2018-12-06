package com.xiji.cashloan.rule.mapper;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.rule.domain.RuleEngineInfo;

/**
 * 规则评分设置管理Dao
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface RuleEngineInfoMapper extends BaseMapper<RuleEngineInfo,Long> {

	int insert(RuleEngineInfo info);

	int deleteInfoByRuleId(long id);

    

}
