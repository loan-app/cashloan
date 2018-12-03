package com.rongdu.cashloan.cl.model.pay.fuiou.constant;

/**
 * @Auther: king
 * @Date: 2018/11/21 20:29
 * @Description:
 */
public class FuiouConstant {
    /** 处理结果 */
    /** 处理结果 - 成功 */
    public static final String RESULT_SUCCESS = "success";
    /** 处理结果 - 受理成功 */
    public static final String RESULT_ACCEPTSUCCESS = "acceptSuccess";
    /** 处理结果 - 富友失败 */
    public static final String RESULT_INTERNALFAIL = "internalFail";
    /** 处理结果 - 卡信息错误 */
    public static final String RESULT_CHANNELFAIL = "channelFail";
    /** 处理结果 - 交易结果未知 */
    public static final String RESULT_UNKNOWREASONS = "unknowReasons";

    //交易状态码说明 代收付接口
    /** 代收付-交易状态码 - 交易未发送 */
    public static final String DAIFU_STATE_Unsent = "0";
    /** 代收付-交易状态码 - 交易已发送且成功 */
    public static final String DAIFU_STATE_SEND_SUCCESS = "1";
    /** 代收付-交易状态码 - 交易已发送且失败 */
    public static final String DAIFU_STATE_SEND_FAIL = "2";
    /** 代收付-交易状态码 - 交易发送中 */
    public static final String DAIFU_STATE_SENDING = "3";
    /** 代收付-交易状态码 - 交易已发送且超时 */
    public static final String DAIFU_STATE_SEND_TIMEOUT = "7";

    //支付接口标记
    /** 代收付-接口标记 - 代付 */
    public static final String DAIFU_INTERFACE_REQ = "payforreq";
    /** 代收付-接口标记 - 查询交易(条件可组合) */
    public static final String DAIFU_INTERFACE_QUERYREQ = "qrytransreq";

    /** 代收付-常量- 版本号 */
    public static final String DAIFU_PAYFOR_VERSION = "1.0";
    /** 代收付-常量- 值为 1，其他值或不填则认为不需要返回交易状态 类别且响应参数无状态类别节点 */
    public static final String DAIFU_PAYFOR_ADDDESC = "1";
    /** 代收付-常量- 代付 */
    public static final String DAIFU_PAYFOR_DAIFU_TYPE = "AP01";
    /** 代收付-常量- 代收 */
    public static final String DAIFU_PAYFOR_DAISHOU_TYPE = "AC01";
    /**
     *P000 支付接口，处理中状态
     */
    public static final String RESPONSE_PAY_PROCESSING = "P000";
    /**
     *只有 AP01 交易有 交易成功
     */
    public static final String RESPONSE_SUCCESS_CODE = "0000";
    /**
     * 只有 AP01 交易有，代表富友受理成功
     */
    public static final String DAIFU_RESPONSE_ACCEPTSUCCESS_CODE = "000000";

    public static String charset = "UTF-8";
    public static String SIGNTP = "MD5";

    /** 协议支付-常量- 版本号 */
    public static final String PROTOCOL_VERSION = "1.0";
    /** 协议支付-接口标记 - 发送验证码 */
    public static final String PROTOCOL_BINDMSG = "bindMsg.pay";
    /** 协议支付-接口标记 - 绑卡提交 */
    public static final String PROTOCOL_BINDCOMMIT = "bindCommit.pay";
    /** 协议支付-接口标记 - 解绑 */
    public static final String PROTOCOL_UNBIND = "unbind.pay";
    /** 协议支付-接口标记 - 绑卡查询 */
    public static final String PROTOCOL_BINDQUERY = "bindQuery.pay";
    /** 协议支付-接口标记 - 代扣-付款 */
    public static final String PROTOCOL_ORDER = "order.pay";
    /** 协议支付-接口标记 - 订单结果查询接口(富友订单号) */
    public static final String PROTOCOL_QUERYORDERID = "queryOrderId.pay";
    /** 协议支付-接口标记 - 订单结果查询接口(商户订单号) */
    public static final String PROTOCOL_CHECKRESULT = "checkResult.pay";
    /** 协议支付-接口标记 - 商户支持卡 Bin 查询接口 */
    public static final String PROTOCOL_CARDBINQUERY = "cardBinQuery.pay";
}
