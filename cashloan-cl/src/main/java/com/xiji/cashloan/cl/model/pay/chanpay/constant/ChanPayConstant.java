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
     * 畅捷支付平台公钥
     */
    public static String MERCHANT_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDPq3oXX5aFeBQGf3Ag/86zNu0VICXmkof85r+DDL46w3vHcTnkEWVbp9DaDurcF7DMctzJngO0u9OG1cb4mn+Pn/uNC1fp7S4JH4xtwST6jFgHtXcTG9uewWFYWKw/8b3zf4fXyRuI/2ekeLSstftqnMQdenVP7XCxMuEnnmM1RwIDAQAB";//生产环境公钥
    /**
     * 生产环境 测试商户号私钥
     */
    public static String MERCHANT_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANB5cQ5pf+QHF9Z2+DjrAXstdxQHJDHyrni1PHijKVn5VHy/+ONiEUwSd5nx1d/W+mtYKxyc6HiN+5lgWSB5DFimyYCiOInh3tGQtN+pN/AtE0dhMh4J9NXad0XEetLPRgmZ795O/sZZTnA3yo54NBquT19ijYfrvi0JVf3BY9glAgMBAAECgYBFdSCox5eXlpFnn+2lsQ6mRoiVAKgbiBp/FwsVum7NjleK1L8MqyDOMpzsinlSgaKfXxnGB7UgbVW1TTeErS/iQ06zx3r4CNMDeIG1lYwiUUuguIDMedIJxzSNXfk65Bhps37lm129AE/VnIecpKxzelaUuzyGEoFWYGevwc/lQQJBAPO0mGUxOR/0eDzqsf7ehE+Iq9tEr+aztPVacrLsEBAwqOjUEYABvEasJiBVj4tECnbgGxXeZAwyQAJ5YmgseLUCQQDa/dgviW/4UMrY+cQnzXVSZewISKg/bv+nW1rsbnk+NNwdVBxR09j7ifxg9DnQNk1Edardpu3z7ipHDTC+z7exAkAM5llOue1JKLqYlt+3GvYr85MNNzSMZKTGe/QoTmCHStwV/uuyN+VMZF5cRcskVwSqyDAG10+6aYqD1wMDep8lAkBQBoVS0cmOF5AY/CTXWrht1PsNB+gbzic0dCjkz3YU6mIpgYwbxuu69/C3SWg7EyznQIyhFRhNlJH0hvhyMhvxAkEAuf7DNrgmOJjRPcmAXfkbaZUf+F4iK+szpggOZ9XvKAhJ+JGd+3894Y/05uYYRhECmSlPv55CBAPwd8VUsSb/1w==";

    /**
     * 签名
     */
    public static String SIGN_KEY = "kkqC/K3COMiQpugQj1Dtqw7568Y7UDsDTY9aihmurROXSpy+BaFyfz5o8PHyUM1opW3rMXCTIH21spjnsadH1Qsl5IrymJnG1M79nQ4cGgVsOsqoAe714RNHqXIDrGw49vpXuFbxFi7Ey4Yhd2gQDmuBYWaj0hecLWlWtd93slM=";

    /**
     * 测试服务地址
     */
    public static String urlStr="https://pay.chanpay.com/mag-unify/gateway/receiveOrder.do?";

    /**
     * 商户号
     */
    public static String MERCHANT_NO="200001160097";

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
    public static String RESPONSE_CHECK_FAIL="111111";
    /**
     * 同步单笔代付交易码
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
     * 单笔交易查询 交易码 -接口标记
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
    /** 协议支付-接口标记 - 商户支持卡 Bin 查询接口 */
    public static final String PROTOCOL_CARDBINQUERY = "cardBinQuery.pay.cj";
    /** 协议支付-接口标记 - 查询订单 */
    public static final String PROTOCOL_QUERYORDER = "nmg_api_query_trade";
    /** 协议支付-接口标记 - 还款支付 */
    public static final String PROTOCOL_REPAYMENT = "nmg_biz_api_quick_payment";

}
