package com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.constant;

public class KuaiqianPayConstant {




    /**
     * 交易成功
     */
    public static final String RESPONSE_SUCCESS_CODE = "00";

    /** 协议支付-常量- 版本号 */
    public static final String PROTOCOL_VERSION = "1.0";
    /** 协议支付-接口标记 - 发送验证码 */
    public static final String PROTOCOL_BINDMSG = "bindMsg.pay";

    /** 协议支付-接口标记 - 解绑 */
    public static final String PROTOCOL_UNBIND = "unbind.pay";
    /** 协议支付-接口标记 - 绑卡查询 */
    public static final String PROTOCOL_BINDQUERY = "bindQuery.pay";

    /** 协议支付-接口标记 - 商户支持卡 Bin 查询接口 */
    public static final String PROTOCOL_CARDBINQUERY = "cardBinQuery.pay";
    public static final String TXN_TYPE = "PUR";




}
