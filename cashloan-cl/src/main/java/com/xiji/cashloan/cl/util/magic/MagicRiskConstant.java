package com.xiji.cashloan.cl.util.magic;

/**
 * Created by szb on 18/12/3.
 */
public class MagicRiskConstant {

    public static String REQ_METHOD = "method";
    public static String REQ_APP_ID = "app_id";
    public static String REQ_VERSION = "version";
    public static String REQ_FORMAT = "format";
    public static String REQ_SIGN_TYPE = "sign_type";
    public static String REQ_TIMESTAMP = "timestamp";
    public static String REQ_BIZ_CONTENT = "biz_content";
    public static String REQ_SIGN = "sign";

    public static String VERSION = "1.0";
    public static String FORMAT = "JSON";
    public static String SIGN_TYPE = "RSA";

    //魔杖2.0
    public static String METHOD_MAGICWAND2 = "moxie.api.risk.magicwand2";
    //魔杖反欺诈
    public static String METHOD_MAGICWAND2_ANTI_FRAUD = "moxie.api.risk.magicwand2.anti-fraud";
    //魔杖黑灰名单
    public static String METHOD_MAGICWAND2_BLACK_GRAY = "moxie.api.risk.magicwand2.black-gray";
    //魔杖多头
    public static String METHOD_MAGICWAND2_MULTI_INFO = "moxie.api.risk.magicwand2.multi-info";
    //魔杖贷后行为
    public static String METHOD_MAGICWAND2_POST_LOAD = "moxie.api.risk.magicwand2.post-load";
    //魔杖申请注入
    public static String METHOD_MAGICWAND2_APPLY = "moxie.api.risk.magicwand2.application";
}
