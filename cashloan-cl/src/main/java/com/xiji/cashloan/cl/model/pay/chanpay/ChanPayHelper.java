package com.xiji.cashloan.cl.model.pay.chanpay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.xiji.cashloan.cl.model.pay.BasePay;
import com.xiji.cashloan.cl.model.pay.chanpay.agreement.vo.response.*;
import com.xiji.cashloan.cl.model.pay.chanpay.constant.ChanPayConstant;
import com.xiji.cashloan.cl.model.pay.chanpay.util.*;


import org.apache.commons.httpclient.NameValuePair;
import org.apache.log4j.Logger;



import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class ChanPayHelper extends BasePay {
    private Logger logger = Logger.getLogger(ChanPayHelper.class);

    /**
     * 放款支付
     * @param origMap
     * @param MERCHANT_PRIVATE_KEY
     * @param charset
     * @return
     */
    public ChanPaymentRespVo payment(Map<String, String> origMap, String MERCHANT_PRIVATE_KEY, String charset) {
        ChanPaymentRespVo paymentRespVo = new ChanPaymentRespVo();
        //保存请求log
        saveReqLog(ChanPayConstant.DAIFU_INTERFACE_REQ, origMap.get("TrxId"),"", JSON.toJSONString(origMap));
        Object respObj = this.gatewayPosts(origMap, charset, MERCHANT_PRIVATE_KEY);
        if (respObj!=null){
            paymentRespVo = JSONObject.parseObject(respObj.toString(), ChanPaymentRespVo.class);
            logger.info("放款支付返回结果paymentRespVo----->"+paymentRespVo);
            //如果获取的应答码OriginalRetCode的值为000000时，成功
            if("000000".equals((String)paymentRespVo.getOriginalRetCode())) {
                logger.info("放款支付成功");
            }
            modifyReqLog(origMap.get("OutTradeNo"),paymentRespVo.toString());
        }
        return paymentRespVo;
    }

    /**
     * 单笔交易查询
     * @param origMap
     * @param charset
     * @param MERCHANT_PRIVATE_KEY
     * @return
     */
    public QueryPaymentRespVo queryPayment(Map<String, String> origMap, String charset, String MERCHANT_PRIVATE_KEY) {
        //保存请求log
        saveReqLog(ChanPayConstant.PAYMENT_QUERY, origMap.get("OutTradeNo"),"", JSON.toJSONString(origMap));
        Object obj = this.gatewayPosts(origMap, charset, MERCHANT_PRIVATE_KEY);
        QueryPaymentRespVo queryPaymentResult = JSONObject.parseObject(obj.toString(), QueryPaymentRespVo.class);
        logger.info("queryPaymentResult------->"+queryPaymentResult);
        //如果获取的应答码OriginalRetCode的值为000000时，成功
        if("000000".equals((String)queryPaymentResult.getOriginalRetCode())) {
            logger.info("单笔交易查询成功");
        }
        //更新返回数据
        modifyReqLog(origMap.get("OutTradeNo"),queryPaymentResult.toString());

        return queryPaymentResult;
    }

    /**
     * 发送验证码
     * @param origMap
     * @param charset
     * @param MERCHANT_PRIVATE_KEY
     * @return
     */
    public SendValidateCodeRespVo bindMsg(Map<String, String> origMap, String charset, String MERCHANT_PRIVATE_KEY) {
        SendValidateCodeRespVo sendCodeResult = new SendValidateCodeRespVo();

        origMap.put("ExpiredTime", ChanPayConstant.EXPIREDTIME);// 订单有效期
        origMap.put("BkAcctTp", ChanPayConstant.BKACCTTP);// 卡类型（00 – 银行贷记卡;01 – 银行借记卡;）
        origMap.put("IDTp", ChanPayConstant.IDTP);// 证件类型 （目前只支持身份证 01：身份证）
        origMap.put("SmsFlag", "1"); // 短信发送标识 1：发送短信
        origMap.put("Extension", "");

        //保存请求log
        saveReqLog(ChanPayConstant.PROTOCOL_BINDMSG, origMap.get("TrxId"),"", JSON.toJSONString(origMap));
        Object respObj = this.gatewayPosts(origMap, charset, MERCHANT_PRIVATE_KEY);
        if (respObj!=null){
            sendCodeResult = JSONObject.parseObject(respObj.toString(), SendValidateCodeRespVo.class);
            logger.info("发送短信返回结果sendCodeResult----->"+sendCodeResult.toString());
            //如果获取的应答码RetCode的值为S0001时，成功
            if("S0001".equals((String)sendCodeResult.getRetCode())) {
                logger.info("验证码发送成功");
            }
            modifyReqLog(origMap.get("TrxId"),sendCodeResult.toString());
        }

        return sendCodeResult;
    }


    /**
     * 绑卡信息验证
     * @param origMap
     * @param charset
     * @param MERCHANT_PRIVATE_KEY
     * @return
     */
    public BindCardVerifyRespVo bindCommit(Map<String, String> origMap, String charset, String MERCHANT_PRIVATE_KEY) {
        //保存请求log
        saveReqLog(ChanPayConstant.PROTOCOL_BINDCOMMIT, origMap.get("TrxId"),"", JSON.toJSONString(origMap));
        Object obj = this.gatewayPosts(origMap, charset, MERCHANT_PRIVATE_KEY);
        BindCardVerifyRespVo bindCardResult = JSONObject.parseObject(obj.toString(), BindCardVerifyRespVo.class);
        logger.info("bindCardResult------->"+bindCardResult);
        //如果获取的应答码RetCode的值为S0001时，成功
        if("S0001".equals((String)bindCardResult.getRetCode())) {
            //更新返回数据
            logger.info("绑卡信息验证交易成功");
        }
        modifyReqLog(origMap.get("TrxId"),bindCardResult.toString());
        return bindCardResult;
    }


    /**
     * 解绑
     * @param origMap
     * @param charset
     * @param MERCHANT_PRIVATE_KEY
     * @return
     */
    public ChanPayUnbindRespVo unbind(Map<String, String> origMap, String charset, String MERCHANT_PRIVATE_KEY) {
        //保存请求log
        saveReqLog(ChanPayConstant.PROTOCOL_UNBIND, origMap.get("TrxId"),"", JSON.toJSONString(origMap));
        Object obj = this.gatewayPosts(origMap, charset, MERCHANT_PRIVATE_KEY);
        ChanPayUnbindRespVo unBindResult = JSONObject.parseObject(obj.toString(), ChanPayUnbindRespVo.class);
        logger.info("unBindResult------->"+unBindResult);
        //如果获取的应答码RetCode的值为S0001时，成功
        if("S0001".equals((String)unBindResult.getRetCode())) {
            logger.info("解绑成功");
        }
        //更新返回数据
        modifyReqLog(origMap.get("TrxId"),unBindResult.toString());
        return unBindResult;
    }
    /**
     * 还款支付
     * @param origMap
     * @param charset
     * @param MERCHANT_PRIVATE_KEY
     * @return
     */
    public RepaymentRespVo repayment(Map<String, String> origMap, String charset, String MERCHANT_PRIVATE_KEY) {
        //保存请求log
        saveReqLog(ChanPayConstant.PROTOCOL_REPAYMENT, origMap.get("TrxId"),"", JSON.toJSONString(origMap));
        Object obj = this.gatewayPosts(origMap, charset, MERCHANT_PRIVATE_KEY);
        RepaymentRespVo repaymentResult = JSONObject.parseObject(obj.toString(), RepaymentRespVo.class);
        logger.info("repaymentResult------->"+repaymentResult);
        //如果获取的应答码RetCode的值为S0001时，成功
        if("S0001".equals((String)repaymentResult.getRetCode())) {
            logger.info("还款成功");
        }
        //更新返回数据
        modifyReqLog(origMap.get("TrxId"),repaymentResult.toString());

        return repaymentResult;
    }

    /**
     * 代付款 -- 查询交易
     * @param origMap
     * @param charset
     * @param MERCHANT_PRIVATE_KEY
     * @return
     */
    public QueryOrderRespVo queryOrder(Map<String, String> origMap, String charset, String MERCHANT_PRIVATE_KEY) {

        //保存请求log
        saveReqLog(ChanPayConstant.PROTOCOL_QUERYORDER, origMap.get("TrxId"),"", JSON.toJSONString(origMap));
        Object obj = this.gatewayPosts(origMap, charset, MERCHANT_PRIVATE_KEY);
        QueryOrderRespVo queryOrderResult = JSONObject.parseObject(obj.toString(), QueryOrderRespVo.class);
        logger.info("queryOrderResult------->"+queryOrderResult);
        //如果获取的应答码RetCode的值为S0001时，成功
        if("S0001".equals((String)queryOrderResult.getRetCode())) {
            logger.info("查询交易成功");
        }
        //更新返回数据
        modifyReqLog(origMap.get("TrxId"),queryOrderResult.toString());

        return queryOrderResult;
    }

    /**
     * 绑卡查询
     * @param origMap
     * @param charset
     * @param MERCHANT_PRIVATE_KEY
     * @return
     */
    public BindCardQueryRespVo bindCardQuery(Map<String, String> origMap, String charset, String MERCHANT_PRIVATE_KEY) {
        //保存请求log
        saveReqLog(ChanPayConstant.PROTOCOL_BINDQUERY, origMap.get("TrxId"),"", JSON.toJSONString(origMap));
        Object obj = this.gatewayPosts(origMap, charset, MERCHANT_PRIVATE_KEY);
        BindCardQueryRespVo bindCardQueryResult = JSONObject.parseObject(obj.toString(), BindCardQueryRespVo.class);
        logger.info("bindCardQueryResult------->"+bindCardQueryResult);
        //如果获取的应答码RetCode的值为S0001时，成功
        if("S0001".equals((String)bindCardQueryResult.getRetCode())) {
            logger.info("绑卡查询");
        }
        //更新返回数据
        modifyReqLog(origMap.get("TrxId"),bindCardQueryResult.toString());

        return bindCardQueryResult;
    }

    /**
     * 查询卡bin
     * @param origMap
     * @param charset
     * @param MERCHANT_PRIVATE_KEY
     * @return
     */
    public QueryCardBinResp queryCardBin(Map<String, String> origMap, String charset, String MERCHANT_PRIVATE_KEY) {

        //保存请求log
        saveReqLog(ChanPayConstant.PROTOCOL_CARDBINQUERY, origMap.get("OutTradeNo"),"", JSON.toJSONString(origMap));
        Object obj = this.gatewayPosts(origMap, charset, MERCHANT_PRIVATE_KEY);
        QueryCardBinResp queryCardBinResult = JSONObject.parseObject(obj.toString(), QueryCardBinResp.class);
        //更新返回数据
        modifyReqLog(origMap.get("OutTradeNo"),queryCardBinResult.toString());
        logger.info("queryCardBinResult------->"+queryCardBinResult);

        return queryCardBinResult;
    }



    /**
     * 向测试服务器发送post请求
     *
     * @param origMap
     *            参数map
     * @param charset
     *            编码字符集
     * @param MERCHANT_PRIVATE_KEY
     *            私钥
     */
    public Object gatewayPosts(Map<String, String> origMap, String charset,
                               String MERCHANT_PRIVATE_KEY) {

        try {
            //生成要请求参数数组
            Map<String, String> sPara = buildRequestPara(origMap, "RSA",
                    MERCHANT_PRIVATE_KEY, charset);
            //请求路径+请求参数   createLinkString(sPara, true)把数组所有元素排序
            logger.info(ChanPayUtil.transferUrl() + createLinkString(sPara, true));
            Object obj = buildRequests(origMap, "RSA", MERCHANT_PRIVATE_KEY,
                    charset, ChanPayUtil.transferUrl() );
            logger.info("obj----->"+obj);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 生成要请求参数数组
     *
     * @param sParaTemp
     *            请求前的参数数组
     * @param signType
     *            RSA
     * @param key
     *            商户自己生成的商户私钥
     * @param inputCharset
     *            UTF-8
     * @return 要请求的参数数组
     * @throws Exception
     */
    public static Map<String, String> buildRequestPara(Map<String, String> sParaTemp, String signType, String key,
                                                       String inputCharset) throws Exception {
        // 除去数组中的空值和签名参数
        Map<String, String> sPara = paraFilter(sParaTemp);
        // 生成签名结果
        String mysign = "";
        if ("MD5".equalsIgnoreCase(signType)) {
            mysign = buildRequestByMD5(sPara, key, inputCharset);
        } else if ("RSA".equalsIgnoreCase(signType)) {
            mysign = buildRequestByRSA(sPara, key, inputCharset);
        }
        // 签名结果与签名方式加入请求提交参数组中
        System.out.println("Sign:" + mysign);
        sPara.put("Sign", mysign);
        sPara.put("SignType", signType);

        return sPara;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params
     *            需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static Map<String, String> createLinkRequestParas(Map<String, String> params) {
        Map<String, String> encodeParamsValueMap = new HashMap<String, String>();
        List<String> keys = new ArrayList<String>(params.keySet());
        String charset = params.get("InputCharset");
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value;
            try {
                value = URLEncoder.encode(params.get(key), charset);
                encodeParamsValueMap.put(key, value);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return encodeParamsValueMap;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params
     *            需要排序并参与字符拼接的参数组
     * @param encode
     *            是否需要urlEncode
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params, boolean encode) {

        params = paraFilter(params);

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        String charset = params.get("InputCharset");
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (encode) {
                try {
                    value = URLEncoder.encode(value, charset);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }
    /**
     * 建立请求，以模拟远程HTTP的POST请求方式构造并获取钱包的处理结果
     * 如果接口中没有上传文件参数，那么strParaFileName与strFilePath设置为空值 如：buildRequest("",
     * "",sParaTemp)
     *
     * @param sParaTemp
     *            请求参数数组
     * @return 钱包处理结果
     * @throws Exception
     */
    public static Object buildRequests(Map<String, String> sParaTemp,
                                       String signType, String key, String inputCharset, String gatewayUrl)
            throws Exception {
        // 待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp, signType, key,
                inputCharset);
        HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler
                .getInstance();
        HttpRequest request = new HttpRequest(HttpResultType.BYTES);
        // 设置编码集
        request.setCharset(inputCharset);
        request.setMethod(HttpRequest.METHOD_POST);
        request.setParameters(generatNameValuePair(
                createLinkRequestParas(sPara), inputCharset));
        request.setUrl(gatewayUrl);
        HttpResponse response = httpProtocolHandler
                .execute(request, null, null);
        if (response == null) {
           // System.out.println("收到返回信息为null");
            return null;
        }

        byte[] byteResult = response.getByteResult();
        if (byteResult.length > 1024) {
                return byteResult;
        } else {
            return response.getStringResult();
        }

    }


    /**
     * MAP类型数组转换成NameValuePair类型
     *
     * @param properties
     *            MAP类型数组
     * @return NameValuePair类型数组
     */
    private static NameValuePair[] generatNameValuePair(Map<String, String> properties, String charset)
            throws Exception {
        NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            // nameValuePair[i++] = new NameValuePair(entry.getKey(),
            // URLEncoder.encode(entry.getValue(),charset));
            nameValuePair[i++] = new NameValuePair(entry.getKey(), entry.getValue());
        }
        return nameValuePair;
    }

    /**
     * 除去数组中的空值和签名参数
     *
     * @param sArray
     *            签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("Sign") || key.equalsIgnoreCase("SignType") || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }

    /**
     * 生成MD5签名结果
     *
     * @param sPara
     *            要签名的数组
     * @return 签名结果字符串
     */
    public static String buildRequestByMD5(Map<String, String> sPara, String key, String inputCharset)
            throws Exception {
        String prestr = createLinkString(sPara, false); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String mysign = "";
        mysign = MD5.sign(prestr, key, inputCharset);
        return mysign;
    }

    /**
     * 生成RSA签名结果
     *
     * @param sPara
     *            要签名的数组
     * @return 签名结果字符串
     */
    public static String buildRequestByRSA(Map<String, String> sPara, String privateKey, String inputCharset)
            throws Exception {
        String prestr = createLinkString(sPara, false); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String mysign = "";
        mysign = RSA.sign(prestr, privateKey, inputCharset);
        return mysign;
    }



}
