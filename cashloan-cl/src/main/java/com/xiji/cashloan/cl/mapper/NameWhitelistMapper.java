package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.cl.domain.NameBlackWhiteUser;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.cl.domain.NameWhitelist;

import java.util.List;
import java.util.Map;

/**
 * 白名单Dao
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-14 20:55:28
 */
@RDBatisDao
public interface NameWhitelistMapper extends BaseMapper<NameWhitelist, Long> {


    List<NameBlackWhiteUser> queryByParams(Map<String, Object> params);
}
