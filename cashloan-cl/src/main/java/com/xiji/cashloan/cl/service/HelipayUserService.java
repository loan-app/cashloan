package com.xiji.cashloan.cl.service;

import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.cl.domain.HelipayUser;
import com.xiji.cashloan.core.domain.UserBaseInfo;

import java.util.Map;

/**
 * 合利宝用户注册信息Service
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-07-30 15:31:02
 */
public interface HelipayUserService extends BaseService<HelipayUser, Long>{


    /**
     * 查询合利宝用户注册信息
     * @param params
     * @return
     */
    HelipayUser getHelipayUser(Map<String,Object> params);


    /**
     * 更新合利宝用户注册信息
     *
     * @param paramMap
     *            更新条件
     */
    int updateSelective(Map<String, Object> paramMap);


    /**
     * 合利宝用户资质上传
     * @param userId
     * @param helipayUserId
     */
    boolean heliPayUpload(Long userId, Long helipayId,String helipayUserId);


    /**
     * 合利宝用户注册
     * @param userBaseInfo
     */
    void helipayRegister(final UserBaseInfo userBaseInfo);

}
