package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.cl.domain.Decision;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

/**
 * 决策数据Dao
 * Created by szb on 19/1/19.
 */
@RDBatisDao
public interface DecisionMapper extends BaseMapper<Decision,Long> {
}
