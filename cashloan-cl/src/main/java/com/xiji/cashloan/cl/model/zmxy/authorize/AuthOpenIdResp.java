package com.xiji.cashloan.cl.model.zmxy.authorize;

import com.antgroup.zmxy.openplatform.api.response.ZhimaAuthInfoAuthqueryResponse;
import com.xiji.cashloan.cl.model.zmxy.base.BaseResp;


/**
 * @author wnb
 * @date 2018/11/30
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class AuthOpenIdResp extends BaseResp {

    /**
     * 最终授权状态
     */
    private boolean authorized;

    /**
     * 用户授权后的openId
     */
    private String openId;


    public AuthOpenIdResp(ZhimaAuthInfoAuthqueryResponse response) {
        super(response);
        this.authorized = response.getAuthorized();
        this.openId = response.getOpenId();
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public String getOpenId() {
        return openId;
    }
}
