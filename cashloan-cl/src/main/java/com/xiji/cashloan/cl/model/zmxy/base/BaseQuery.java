package com.xiji.cashloan.cl.model.zmxy.base;

/**
 * @author wnb
 * @date 2018/11/30
 * @version 1.0.0
 */
public abstract class BaseQuery {

    /**
     * 芝麻信用授权接口地址
     */
    protected static final String gatewayUrl = "https://zmopenapi.zmxy.com.cn/openapi.do";

    /**
     * 你的私钥
     */
    private final String privateKey;

    /**
     * 你的公钥
     */
    private final String zhimaPublicKey;

    /**
     * 用户应用ID
     */
    private final String appId;


    public BaseQuery(String privateKey, String zhimaPublicKey, String appId) {
        this.privateKey = privateKey;
        this.zhimaPublicKey = zhimaPublicKey;
        this.appId = appId;
    }

    public BaseQuery(String privateKey) {
        this(privateKey, null, null);
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getZhimaPublicKey() {
        return zhimaPublicKey;
    }

    public String getAppId() {
        return appId;
    }

}
