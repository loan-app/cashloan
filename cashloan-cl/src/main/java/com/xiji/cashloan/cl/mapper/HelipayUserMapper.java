package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.cl.domain.HelipayUser;

import java.util.Map;

/**
 * 合利宝用户注册信息Dao
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-07-30 15:31:02
 */
@RDBatisDao
public interface HelipayUserMapper extends BaseMapper<HelipayUser, Long> {


    /**
     * 查询合利宝用户注册信息
     * @param params
     * @return
     */
    HelipayUser getHelipayUser(Map<String,Object> params);
}
