package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.cl.domain.NameBlackWhiteUser;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.cl.domain.NameBlacklist;

import java.util.List;
import java.util.Map;

/**
 * 黑名单Dao
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-14 17:22:38
 */
@RDBatisDao
public interface NameBlacklistMapper extends BaseMapper<NameBlacklist, Long> {

    List<NameBlackWhiteUser> queryByParams(Map<String, Object> params);

}
