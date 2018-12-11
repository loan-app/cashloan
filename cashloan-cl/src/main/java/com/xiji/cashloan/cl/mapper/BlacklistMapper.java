package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.cl.domain.Blacklist;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

/**
 * 黑名单Dao
 * 
 * @author king
 * @version 1.0.0
 * @date 2018-12-10 16:29:50
 */
@RDBatisDao
public interface BlacklistMapper extends BaseMapper<Blacklist, Long> {

    

}
