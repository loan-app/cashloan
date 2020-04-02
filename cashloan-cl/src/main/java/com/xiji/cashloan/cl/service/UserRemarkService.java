package com.xiji.cashloan.cl.service;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.UserRemark;
import com.xiji.cashloan.core.common.service.BaseService;

import java.util.Map;

/**
 * 用户备注Service
 * 
 * @author szb
 * @version 1.0.0
 * @date 2019-01-17 14:29:29
 */
public interface UserRemarkService extends BaseService<UserRemark, Long>{

    /**
     * 查询备注列表
     * @param searchParams
     * @param current
     * @param pageSize
     * @return
     */
    Page<UserRemark> listUserRemark(Map<String, Object> searchParams, int current, int pageSize);

    /**
     * 保存备注信息
     * @param userRemark
     * @return
     */
    int saveUserRemark(UserRemark userRemark);
}
