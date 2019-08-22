package com.xiji.cashloan.cl.model.pay.common.biz;

import com.xiji.cashloan.cl.model.pay.chanpay.ChanPayHelper;

import com.xiji.cashloan.cl.model.pay.chanpay.agreement.vo.response.*;
import com.xiji.cashloan.cl.model.pay.chanpay.constant.ChanPayConstant;
import com.xiji.cashloan.cl.model.pay.chanpay.util.ChanPayUtil;
import com.xiji.cashloan.cl.model.pay.chanpay.util.RSA;
import com.xiji.cashloan.cl.model.pay.common.PayCommon;
import com.xiji.cashloan.cl.model.pay.common.constant.PayConstant;
import com.xiji.cashloan.cl.model.pay.common.vo.request.*;
import com.xiji.cashloan.cl.model.pay.common.vo.response.*;
import com.xiji.cashloan.core.common.util.StringUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;



import java.util.HashMap;
import java.util.Map;

public class ChanPayBiz implements PayCommon {

    private Logger logger = Logger.getLogger(ChanPayHelper.class);

    @Override
    public PaymentResponseVo payment(PaymentReqVo vo) {
        PaymentResponseVo responseVo = new PaymentResponseVo();
        Map<String, String> origMap = new HashMap<String, String>();
        origMap = setCommonMap(origMap);
        origMap.put(ChanPayConstant.SERVICE, ChanPayConstant.PAID_PAYMENT);// 鉴权绑卡的接口名(商户采集方式)
        origMap.put(ChanPayConstant.PARTNER_ID,ChanPayUtil.paidMerchantNumber());//商户号
        origMap.put("TransCode", ChanPayConstant.PAYMENT_TRANS_CODE); // 交易码
        String orderId = ChanPayConstant.getOrderId();
        origMap.put("OutTradeNo", orderId); // 商户网站唯一订单号
        //origMap.put("CorpAcctNo", "");  //企业在代收系统注册备案的企业账户(可空)
        origMap.put("BusinessType", "0"); // 业务类型：0对私 1对公
        origMap.put("BankCommonName", vo.getBankName()); // 通用银行名称
       // origMap.put("BankCode", "CCB");//收款方银行编码(对公必填)
        origMap.put("AccountType", "00"); // 账户类型(00：借记卡 01：贷记卡)
        origMap.put("AcctNo", this.encrypt(vo.getBankCardNo(), ChanPayUtil.chanpayPublicKey(),  ChanPayConstant.ENCODEING)); // 对手人账号(此处需要用真实的账号信息)
        origMap.put("AcctName", this.encrypt(vo.getBankCardName(),ChanPayUtil.chanpayPublicKey(),  ChanPayConstant.ENCODEING)); // 对手人账户名称
        origMap.put("TransAmt", Double.toString(vo.getAmount()));//借款金额
        origMap.put("CorpPushUrl", ChanPayUtil.paymentNotifyAddress());//用户在商户平台下单时候的ip地址(可空)
        origMap.put("PostScript", "用途:代付");
        ChanPayHelper payHelper = new ChanPayHelper();
        logger.info("请求参数------>"+origMap.toString());
        ChanPaymentRespVo paymentResult=payHelper.payment(origMap,ChanPayUtil.chanpayPrivateKey(),  ChanPayConstant.ENCODEING);

        if (ChanPayConstant.PAYFOR_RESPONSE_SUCCESS_CODE.equals(paymentResult.getOriginalRetCode())){
            responseVo.setStatus(PayConstant.RESULT_SUCCESS);
            responseVo.setStatusCode(paymentResult.getOriginalRetCode());
            responseVo.setMessage("成功");
        }else if(ChanPayConstant.RESPONSE_CHECK_FAIL.equals(paymentResult.getOriginalRetCode())){
            responseVo.setStatus(PayConstant.STATUS_NEED_CHECK);
            responseVo.setStatusCode(paymentResult.getOriginalRetCode());
            responseVo.setMessage(paymentResult.getPlatformErrorMessage());
        }else {
            responseVo.setStatus(PayConstant.STATUS_FAIL);
            responseVo.setStatusCode(paymentResult.getOriginalRetCode());
            responseVo.setMessage(paymentResult.getPlatformErrorMessage());
        }
        responseVo.setOrderNo(orderId);
        return responseVo;
    }

    @Override
    public PaymentQueryResponseVo queryPayment(PaymentQueryVo vo) {

        Map<String, String> origMap = new HashMap<String, String>();
        origMap = setCommonMap(origMap);
        origMap.put(ChanPayConstant.SERVICE, ChanPayConstant.PAID_PAYMENT_QUERY);// 鉴权绑卡的接口名(商户采集方式)
        origMap.put(ChanPayConstant.PARTNER_ID, ChanPayUtil.paidMerchantNumber());//商户号
        origMap.put("TransCode", ChanPayConstant.PAYMENT_QUERY_TRANS_CODE);
        String trxId = ChanPayConstant.getOrderId();
        origMap.put("OutTradeNo", trxId);
        origMap.put("OriOutTradeNo",vo.getOrderNo());
        logger.info("请求参数--------->"+origMap.toString());
        ChanPayHelper payHelper = new ChanPayHelper();
        QueryPaymentRespVo result =payHelper.queryPayment(origMap,ChanPayConstant.ENCODEING,ChanPayUtil.chanpayPrivateKey());
        PaymentQueryResponseVo responseVo = new PaymentQueryResponseVo();
        if (result != null) {
            if (ChanPayConstant.PAYFOR_RESPONSE_SUCCESS_CODE.equals(result.getOriginalRetCode())) {
                responseVo.setStatus(PayConstant.RESULT_SUCCESS);
            }else {
                responseVo.setStatus(PayConstant.STATUS_PAYQUERY_NO_REQ);
            }
        }
        return responseVo;
    }

    @Override
    public BindCardMsgResponseVo bindMsg(BindCardMsgVo vo) {
        BindCardMsgResponseVo responseVo = new BindCardMsgResponseVo();
        Map<String, String> origMap = new HashMap<String, String>();
        origMap = setCommonMap(origMap);
        // 2.1 鉴权绑卡 api 业务参数
        origMap.put(ChanPayConstant.SERVICE, ChanPayConstant.PROTOCOL_BINDMSG);// 鉴权绑卡的接口名(商户采集方式)
        origMap.put(ChanPayConstant.PARTNER_ID, ChanPayUtil.agreementMerchantNumber());//商户号

        String trxId = ChanPayConstant.getOrderId();
        origMap.put("TrxId", trxId);// 订单号
        origMap.put("MerUserId", vo.getUserId());// 用户标识（测试时需要替换一个新的meruserid）
        origMap.put("BkAcctNo", this.encrypt(vo.getBankCardNo(), ChanPayUtil.chanpayPublicKey(), ChanPayConstant.ENCODEING));// 卡号
        origMap.put("IDNo", this.encrypt(vo.getIdCard(), ChanPayUtil.chanpayPublicKey(), ChanPayConstant.ENCODEING));// 证件号
        origMap.put("CstmrNm", this.encrypt(vo.getBankCardName(),ChanPayUtil.chanpayPublicKey(), ChanPayConstant.ENCODEING));// 持卡人姓名
        origMap.put("MobNo", this.encrypt(vo.getMobile(), ChanPayUtil.chanpayPublicKey(), ChanPayConstant.ENCODEING));// 银行预留手机号
      //  origMap.put("NotifyUrl", "");// 异步通知url
        origMap.put("SmsFlag", "1");//0：不发送短信 1：发送短信
        origMap.put("Extension", "");
        logger.info("请求参数--------->"+origMap.toString());
        ChanPayHelper payHelper = new ChanPayHelper();
        SendValidateCodeRespVo result= payHelper.bindMsg(origMap,ChanPayConstant.ENCODEING,ChanPayUtil.chanpayPrivateKey());

        if (result.checkReturn()) {
            responseVo.setStatus(PayConstant.RESULT_SUCCESS);
            responseVo.setMessage(result.getRetMsg());
        }else {
            responseVo.setStatus(PayConstant.STATUS_FAIL);
            responseVo.setMessage(result.getRetMsg());
        }
        responseVo.setOrderNo(origMap.get("TrxId"));
        responseVo.setProtocolNo(result.getOrderTrxid());
        return responseVo;
    }

    @Override
    public UnbindCardResponseVo unbind(UnbindCardVo vo) {
        UnbindCardResponseVo responseVo = new UnbindCardResponseVo();

        Map<String, String> origMap = new HashMap<String, String>();
        // 2.1 基本参数
        origMap = setCommonMap(origMap);
        origMap.put(ChanPayConstant.SERVICE,ChanPayConstant.PROTOCOL_UNBIND);// 用户鉴权解绑接口名
        // 2.2 业务参数
       // String trxId = Long.toString(System.currentTimeMillis());
        String trxId = ChanPayConstant.getOrderId();
        origMap.put("TrxId", trxId);// 商户网站唯一订单号
        origMap.put("MerchantNo", ChanPayUtil.agreementMerchantNumber());// 子商户号
        origMap.put("MerUserId", vo.getUserId()); // 用户标识（测试时需要替换一个新的meruserid）
        origMap.put("UnbindType", "1"); // 解绑模式。0为物理解绑，1为逻辑解绑
        String cardNo = vo.getCardNo();
        String CardBegin="";
        String CardEnd="";
        if (StringUtil.isNotEmpty(cardNo)){
            CardBegin = cardNo.substring(0, 6);
            CardEnd = cardNo.substring(cardNo.length()-4);
        }
        origMap.put("CardBegin", CardBegin);// 卡号前6位
        origMap.put("CardEnd", CardEnd);// 卡号后4位
        origMap.put("Extension", "");// 扩展字段

        ChanPayHelper payHelper = new ChanPayHelper();
        ChanPayUnbindRespVo rssult=payHelper.unbind(origMap,ChanPayConstant.ENCODEING,ChanPayUtil.chanpayPrivateKey());


        if (ChanPayConstant.RESPONSE_SUCCESS_CODE.equals(rssult.getRetCode())){
            responseVo.setStatus(PayConstant.RESULT_SUCCESS);
            responseVo.setMessage(rssult.getRetMsg());
        } else {
            responseVo.setStatus(PayConstant.STATUS_FAIL);
            responseVo.setMessage(rssult.getRetMsg());
        }

        return responseVo;
    }

    @Override
    public BindCardMsgResponseVo bindCommit(BindCardMsgVo vo) {
        BindCardMsgResponseVo responseVo = new BindCardMsgResponseVo();

        Map<String, String> origMap = new HashMap<String, String>();
        // 2.1 基本参数
        origMap = setCommonMap(origMap);
        origMap.put("Service",ChanPayConstant.PROTOCOL_BINDCOMMIT);// 鉴权绑卡确认的接口名
        origMap.put(ChanPayConstant.PARTNER_ID, ChanPayUtil.agreementMerchantNumber());//商户号
        // 2.1 鉴权绑卡  业务参数
        String trxId = ChanPayConstant.getOrderId();
        //String trxId = "201756796897";
        origMap.put("TrxId", trxId);// 订单号
        origMap.put("OriAuthTrxId", vo.getOrderNo());// 原鉴权绑卡订单号
        origMap.put("SmsCode", vo.getMsgCode());// 鉴权短信验证码
        //origMap.put("NotifyUrl", "");// 异步通知地址
        logger.info("请求参数--------->"+origMap.toString());
        ChanPayHelper payHelper = new ChanPayHelper();
        BindCardVerifyRespVo result=payHelper.bindCommit(origMap, ChanPayConstant.ENCODEING,ChanPayUtil.chanpayPrivateKey());

        if (result.checkReturn()) {
            responseVo.setStatus(PayConstant.RESULT_SUCCESS);
            responseVo.setProtocolNo(result.getOrderTrxid());
            responseVo.setMessage(result.getAppRetMsg());
        }else {
            responseVo.setStatus(PayConstant.STATUS_FAIL);
            responseVo.setMessage(result.getAppRetMsg());
        }

        return responseVo;
    }

    /**
     * 还款支付
     * @param vo
     * @return
     */
    @Override
    public RepaymentResponseVo repayment(RepaymentReqVo vo) {
        RepaymentResponseVo responseVo = new RepaymentResponseVo();
        Map<String, String> origMap = new HashMap<String, String>();
        // 2.1 基本参数
        origMap = setCommonMap(origMap);
        origMap.put(ChanPayConstant.SERVICE, ChanPayConstant.PROTOCOL_REPAYMENT);// 支付的接口名
        origMap.put(ChanPayConstant.PARTNER_ID,ChanPayUtil.agreementMerchantNumber());//商户号
        // 2.2 业务参数
        String trxId = vo.getOrderNo();
        origMap.put("TrxId", trxId);// 订单号
        origMap.put("OrdrName", "畅捷支付");// 商户网站商品名称
        origMap.put("MerUserId", vo.getUserId());// 用户标识（测试时需要替换一个新的meruserid）
        origMap.put("SellerId",ChanPayUtil.agreementMerchantNumber());// 子账户号  商户号
        origMap.put("SubMerchantNo", "");// 子商户号(可空)
        origMap.put("ExpiredTime", ChanPayConstant.EXPIREDTIME);// 订单有效期

        String cardNo = vo.getCardNo();
        String CardBegin="";
        String CardEnd="";
        //获取银行卡的前6位,和后4位
        if (StringUtil.isNotEmpty(cardNo)){
           CardBegin = cardNo.substring(0, 6);
           CardEnd = cardNo.substring(cardNo.length()-4);
        }
        origMap.put("CardBegin", CardBegin);// 卡号前6位
        origMap.put("CardEnd", CardEnd);// 卡号后4位
        origMap.put("TrxAmt", Double.toString(vo.getAmount()));// 交易金额
        origMap.put("TradeType", "11");// 交易类型（即时 11 担保 12）
        origMap.put("SmsFlag", "0"); //  0：不发送短信 1：发送短信
        origMap.put("NotifyUrl", ChanPayUtil.rePaymentNotifyAddress());//异步通知地址
        ChanPayHelper payHelper = new ChanPayHelper();
        RepaymentRespVo result=payHelper.repayment(origMap, ChanPayConstant.ENCODEING,ChanPayUtil.chanpayPrivateKey());

        //订单号
        responseVo.setOrderNo(origMap.get("TrxId"));
        responseVo.setStatus(PayConstant.STATUS_ERROR);
        String payMsg = result.getAppRetMsg();
        if (result.checkReturn() && StringUtil.isNotEmpty(result.getOrderTrxid())) {
            payMsg = result.getOrderTrxid() +"|"+payMsg;
            responseVo.setStatus(PayConstant.RESULT_SUCCESS);
            responseVo.setMessage(payMsg);
            //设置畅捷流水号
            responseVo.setPayPlatNo(result.getOrderTrxid());
        }else {
            responseVo.setStatus(PayConstant.STATUS_FAIL);
            responseVo.setMessage(result.getAppRetMsg());
        }
        responseVo.setStatusCode(result.getAppRetcode());
        return responseVo;
    }


    /**
     * 代付款 -- 查询交易
     * @param vo
     * @return
     */
    @Override
    public RepaymentQueryResponseVo queryOrder(RepaymentQueryVo vo) {

        RepaymentQueryResponseVo responseVo = new RepaymentQueryResponseVo();
        if (vo == null) {
            return responseVo;
        }
        Map<String, String> origMap = new HashMap<String, String>();
        // 2.1 基本参数
        origMap = setCommonMap(origMap);
        origMap.put(ChanPayConstant.SERVICE, ChanPayConstant.PROTOCOL_QUERYORDER);// 请求的接口名称
        origMap.put(ChanPayConstant.PARTNER_ID, ChanPayUtil.agreementMerchantNumber());//商户号
        // 2.2 业务参数
        String trxId = ChanPayConstant.getOrderId();
        origMap.put("TrxId", trxId);// 订单号
        origMap.put("TradeType", "pay_order");// 原业务订单类型 auth_order：鉴权订单 pay_order：支付订单 refund_order：退款订单
        ChanPayHelper payHelper = new ChanPayHelper();
        QueryOrderRespVo result=new QueryOrderRespVo();
        if (StringUtil.isNotEmpty(vo.getPayPlatNo())){
            origMap.put("OrderTrxId",vo.getPayPlatNo());// 原有支付请求订单号
           result=payHelper.queryOrder(origMap, ChanPayConstant.ENCODEING,ChanPayUtil.chanpayPrivateKey());

        }else if (StringUtil.isNotEmpty(vo.getOrderNo())){
            origMap.put("OrderTrxId",vo.getOrderNo());// 原有支付请求订单号
            result=payHelper.queryOrder(origMap, ChanPayConstant.ENCODEING, ChanPayUtil.chanpayPrivateKey());

        }
        responseVo.setCode(PayConstant.QUERY_PAY_ERROR);
        //订单已支付
        if (StringUtil.equals(ChanPayConstant.QUERYORDERID_PAYSUCCESS, result.getStatus())) {
            responseVo.setCode(PayConstant.QUERY_PAY_SUCCESS);

        }else if (StringUtil.equals(ChanPayConstant.QUERYORDERID_PAYING, result.getStatus())){
            responseVo.setCode(PayConstant.QUERY_PAY_PROCESSING);
        }else {
            responseVo.setCode(PayConstant.QUERY_PAY_FAIL);
        }
        responseVo.setMsg(result.getAppRetMsg());

        return responseVo;
    }

    /**
     * 绑卡查询
     * @param vo
     * @return
     */
    @Override
    public BindCardQueryResponseVo bindCardQuery(BindCardQueryVo vo) {
        Map<String, String> origMap = new HashMap<String, String>();
        // 2.1 基本参数
        origMap = setCommonMap(origMap);
        origMap.put(ChanPayConstant.SERVICE, ChanPayConstant.PROTOCOL_BINDQUERY);// 用户鉴权绑卡信息查询接口名
        origMap.put(ChanPayConstant.PARTNER_ID, ChanPayUtil.agreementMerchantNumber());//商户号
        // 2.2 业务参数
        String trxId = ChanPayConstant.getOrderId();
        origMap.put("TrxId", trxId);// 商户网站唯一订单号
        origMap.put("MerUserId", vo.getUserId()); // 用户标识（测试时需要替换一个新的meruserid）
        //origMap.put("CardBegin", "430000");// 卡号前6位
        //origMap.put("CardEnd", "4700");// 卡号后4位
        origMap.put("BkAcctTp", ChanPayConstant.BKACCTTP);// 卡类型（00 – 银行贷记卡;01 – 银行借记卡）
        ChanPayHelper payHelper = new ChanPayHelper();
        BindCardQueryRespVo result =payHelper.bindCardQuery(origMap, ChanPayConstant.ENCODEING, ChanPayUtil.chanpayPrivateKey());

        BindCardQueryResponseVo responseVo = new BindCardQueryResponseVo();
        if (ChanPayConstant.RESPONSE_SUCCESS_CODE.equals(result.getRetCode())){
            responseVo.setStatus(PayConstant.RESULT_SUCCESS);
            responseVo.setProtocolNo(result.getMerUserId());
            responseVo.setMessage(result.getAppRetMsg());
        }else {
            responseVo.setStatus(PayConstant.STATUS_FAIL);
            responseVo.setMessage(result.getAppRetMsg());
        }
        return responseVo;
    }

    @Override
    public CardBinQueryResponseVo queryCardBin(CardBinQueryVo vo) {
        CardBinQueryResponseVo responseVo = new CardBinQueryResponseVo();
        Map<String, String> origMap = new HashMap<String, String>();

        origMap.put(ChanPayConstant.SERVICE, ChanPayConstant.PROTOCOL_CARDBINQUERY);// 接口名
        origMap.put("TransCode", ChanPayConstant.CARDBINQUERY_TRANS_CODE);// 查询卡bin编号
        origMap.put(ChanPayConstant.PARTNER_ID, ChanPayUtil.paidMerchantNumber()); //生产环境测试商户号
        // 2.1 基本参数
        origMap = setCommonMap(origMap);
        String orderId = ChanPayConstant.getOrderId();
        origMap.put("OutTradeNo", orderId);
        origMap.put("AcctNo", this.encrypt(vo.getBankCardNo(),ChanPayUtil.chanpayPublicKey(), ChanPayConstant.ENCODEING));
        ChanPayHelper payHelper = new ChanPayHelper();
        QueryCardBinResp queryCardBinResp=payHelper.queryCardBin(origMap, ChanPayConstant.ENCODEING, ChanPayUtil.chanpayPrivateKey());

        if ("是".equals(queryCardBinResp.getIsValid())){//卡号是否有效
            responseVo.setStatus(PayConstant.RESULT_SUCCESS);
            responseVo.setBank(queryCardBinResp.getBankCommonName());
        }else {
            responseVo.setStatus(PayConstant.STATUS_FAIL);
        }
        return responseVo;
    }



    /**
     * 公共请求参数设置
     */
    public Map<String, String> setCommonMap(Map<String, String> origMap) {
        // 2.1 基本参数
        origMap.put(ChanPayConstant.VERSION, ChanPayConstant.PROTOCOL_VERSION);
     //   origMap.put(ChanPayConstant.PARTNER_ID, ChanPayConstant.MERCHANT_NO);//生产环境测试商户号

        origMap.put(ChanPayConstant.INPUT_CHARSET, ChanPayConstant.ENCODEING);// 字符集
        origMap.put(ChanPayConstant.TRADE_DATE, ChanPayConstant.DATE);// 商户请求时间
        origMap.put(ChanPayConstant.TRADE_TIME, ChanPayConstant.TIME);// 商户请求时间
        origMap.put(ChanPayConstant.MEMO, "");//备注
        return origMap;
    }

    /**
     * 加密，部分接口，有参数需要加密
     *
     * @param src
     *            原值
     * @param publicKey
     *            畅捷支付发送的平台公钥
     * @param charset
     *            UTF-8
     * @return RSA加密后的密文
     */
    private String encrypt(String src, String publicKey, String charset) {
        try {
            byte[] bytes = RSA.encryptByPublicKey(src.getBytes(charset), publicKey);
            return Base64.encodeBase64String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
