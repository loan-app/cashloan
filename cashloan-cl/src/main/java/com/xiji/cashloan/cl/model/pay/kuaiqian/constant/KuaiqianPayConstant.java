package com.xiji.cashloan.cl.model.pay.kuaiqian.constant;

/**
 * @auther : wnb
 * @date : 2019/5/3
 * @describe : 快钱常量
 */
public class KuaiqianPayConstant {

    /** 接口类型 - 代付 */
    public static final String BTYPE_PAY_MOCK = "PayMock";
    /**
     * 表明交易申请成功
     */
    public static final String PAYFOR_RESPONSE_SUCCESS_CODE = "0000";
    /**
     * 验签解密失败
     */
    public static final String RESPONSE_CHECK_FAIL ="0004";
    /** 接口类型 - 代付单查询 */
    public static final String BTYPE_QUERY_MOCK = "QueryMock";
    /**
     * 默认查询目标页数
     */
    public static final String QUERY_TARGETPAGE ="1";
    /**
     * 默认查询每页条数
     */
    public static final String QUERY_PGAE_SIZE = "20";
    /**
     *  策略编码，固定值 F41
      */
    public static String  FETURE_CODE ="F41";
    /**
     * 字符编码
     */
    private static String ENCODEING = "UTF-8";

    /**
     * 交易成功
     */
    public static final String RESPONSE_SUCCESS_CODE = "00";

    /** 协议支付-常量- 版本号 */
    public static final String PROTOCOL_VERSION = "1.0";
    /** 协议支付-常量- 交易类型 */
    public static final String PROTOCOL_TXNTYPE = "PUR";//PUR:消费交易
    /** 协议支付-常量- 消息交互状态 */
    public static final String PROTOCOL_INTERACTIVESTATUS = "TR1";
    /** 协议支付-常量- 特殊交易 标志 QPay02=协议支付 */
    public static final String PROTOCOL_SPFLAG = "QPay02";
    /** 协议支付-接口标记 - 发送验证码 */
    public static final String PROTOCOL_BINDMSG = "bindMsg.pay";
    /** 协议支付-接口标记 - 绑卡 */
    public static final String PROTOCOL_BINDCOMMIT = "bindCommit.pay";
    /** 协议支付-接口标记 - 解绑 */
    public static final String PROTOCOL_UNBIND = "unbind.pay";
    /** 协议支付-接口标记 - 绑卡查询 */
    public static final String PROTOCOL_BINDQUERY = "bindQuery.pay";
    /** 协议支付-接口标记 - 商户支持卡 Bin 查询接口 */
    public static final String PROTOCOL_CARDBINQUERY = "cardBinQuery.pay";

}
