package com.xiji.cashloan.cl.service;

        import com.xiji.cashloan.core.common.service.BaseService;
        import com.xiji.cashloan.cl.domain.HelipayUser;

/**
 * 合利宝用户注册信息Service
 *
 * @author wnb
 * @version 1.0.0
 * @date 2019-07-30 15:31:02
 */
public interface HelipayUserService extends BaseService<HelipayUser, Long>{



    /**
     * 合利宝用户资质上传
     * @param userId
     * @param helipayUserId
     */
    boolean heliPayUpload(Long userId,String helipayUserId);

}
