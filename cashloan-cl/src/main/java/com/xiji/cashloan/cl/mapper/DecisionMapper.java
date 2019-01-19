package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.cl.domain.Decision;

/**
 * 决策数据Dao
 * 
 * @author szb
 * @version 1.0.0
 * @date 2019-01-19 15:53:20
 */
@RDBatisDao
public interface DecisionMapper extends BaseMapper<Decision, Long> {

    int saveSelective(Decision decision);
    

}
