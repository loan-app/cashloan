package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.cl.domain.UserRemark;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

import java.util.List;
import java.util.Map;

/**
 * 用户备注Dao
 * 
 * @author szb
 * @version 1.0.0
 * @date 2019-01-17 14:29:29
 */
@RDBatisDao
public interface UserRemarkMapper extends BaseMapper<UserRemark, Long> {


    /**
     * 查询备注列表
     * @param paramMap
     * @return
     */
    List<UserRemark> listUserRemark(Map<String, Object> paramMap);
}
