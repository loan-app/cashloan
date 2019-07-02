package com.xiji.cashloan.cl.model.pay.chanpay.constant;

import com.xiji.cashloan.core.common.util.OrderNoUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChanPayConstant {

    // 基础参数
    public static final String SERVICE = "Service";
    public static final String VERSION = "Version";
    public static final String PARTNER_ID = "PartnerId";
    // 日期
    public static final String TRADE_DATE = "TradeDate";
    public static final String TRADE_TIME = "TradeTime";
    public static final String INPUT_CHARSET = "InputCharset";
    public static final String SIGN = "Sign";
    public static final String SIGN_TYPE = "SignType";
    public static final String MEMO = "Memo";

    public static final String MD5 = "MD5";
    public static final String RSA = "RSA";
    public final static String GATEWAY_URL = "https://pay.chanpay.com/mag-unify/gateway/receiveOrder.do";
    public final static String BATCH_FILE_GATEWAY_URL = "https:/pay.chanpay.com/mag-unify/gateway/batchOrder.do";
    public static String DATE = new SimpleDateFormat("yyyyMMdd").format(new Date());
    public static String TIME = new SimpleDateFormat("HHmmss").format(new Date());
    /** 代收付-接口标记 - 代付 */
    public static final String DAIFU_INTERFACE_REQ = "payforreq";


    /**
     * 订单有效期
     */
    public static String EXPIREDTIME="90m";
    /**
     * 卡类型（00 – 银行贷记卡;01 – 银行借记卡;）
     */
    public static String BKACCTTP="01";
    /**
     * 证件类型 （目前只支持身份证 01：身份证）
     */
    public static String IDTP= "01";
    /**
     * 字符编码
     */
    public static String ENCODEING = "UTF-8";
    /**
     * 交易成功
     */
    public static String RESPONSE_SUCCESS_CODE="S0001";
    /**
     * 代付交易成功
     */
    public static String PAYFOR_RESPONSE_SUCCESS_CODE="000000";
    /**
     * 代付交易失败
     */
    public static String RESPONSE_CHECK_FAIL="000001";
    /**
     * 同步单笔代付 交易码
     */
    public static String PAYMENT_TRANS_CODE="T10000";


    /**
     * 卡bin查询交易码
     */
    public static String CARDBINQUERY_TRANS_CODE="C00016";

    /**
     * 单笔交易查询 交易码
     */
    public static String PAYMENT_QUERY_TRANS_CODE="C00000";

    /**
     * 单笔交易查询 -接口标记
     */
    public static String PAYMENT_QUERY="payment.query.cj";

    /**
     * 查询订单状态--成功
     */
    public static String QUERYORDERID_PAYSUCCESS ="S";

    public static String QUERYORDERID_PAYING ="P";

    /**
     *
     * 提现成功状态
     */
    public static String WITHDRAWAL_STATUS="WITHDRAWAL_SUCCESS";

    public static String getOrderId() {
        return "xjcj" + OrderNoUtil.getSerialNumber();
    }

    /** 协议支付-常量- 版本号 */
    public static final String PROTOCOL_VERSION = "1.0";
    /** 协议支付-常量- 交易类型 */
    public static final String PROTOCOL_TXNTYPE = "PUR";//PUR:消费交易
    /** 协议支付-常量- 消息交互状态 */
    public static final String PROTOCOL_INTERACTIVESTATUS = "TR1";
    /** 协议支付-常量- 特殊交易 标志 QPay02=协议支付 */
    public static final String PROTOCOL_SPFLAG = "QPay02";
    /** 协议支付-接口标记 - 发送验证码   鉴权绑卡 api 业务参数 */
    public static final String PROTOCOL_BINDMSG = "nmg_biz_api_auth_req";
    /** 协议支付-接口标记 -  短信验证码验证  鉴权绑卡确认的接口名 */
    public static final String PROTOCOL_BINDCOMMIT = "nmg_api_auth_sms";
    /** 协议支付-接口标记 - 解绑 */
    public static final String PROTOCOL_UNBIND = "nmg_api_auth_unbind";
    /** 协议支付-接口标记 - 绑卡查询 */
    public static final String PROTOCOL_BINDQUERY = "nmg_api_auth_info_qry";

    /** 协议支付-接口标记 - 查询订单 */
    public static final String PROTOCOL_QUERYORDER = "nmg_api_query_trade";
    /** 协议支付-接口标记 - 还款支付 */
    public static final String PROTOCOL_REPAYMENT = "nmg_biz_api_quick_payment";



    /** 代支付-接口标记 - 商户支持卡 Bin 查询接口 */
    public static final String PROTOCOL_CARDBINQUERY = "cjt_dsf";
    /** 代支付-接口标记 - 同步代付接口 */
    public static final String PAID_PAYMENT = "cjt_dsf";
    /** 代支付-接口标记 - 同步代付查询接口 */
    public static final String PAID_PAYMENT_QUERY = "cjt_dsf";



}
