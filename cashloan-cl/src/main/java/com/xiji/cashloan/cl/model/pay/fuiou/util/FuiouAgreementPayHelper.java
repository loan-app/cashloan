package com.xiji.cashloan.cl.model.pay.fuiou.util;

import com.alibaba.fastjson.JSON;
import com.fuiou.mpay.encrypt.DESCoderFUIOU;
import com.fuiou.mpay.encrypt.RSAUtils;
import com.fuiou.util.MD5;
import com.xiji.cashloan.cl.model.pay.BasePay;
import com.xiji.cashloan.cl.model.pay.common.constant.PayConstant;
import com.xiji.cashloan.cl.model.pay.common.vo.request.RepaymentQueryVo;
import com.xiji.cashloan.cl.model.pay.fuiou.agreement.BankCardReq;
import com.xiji.cashloan.cl.model.pay.fuiou.agreement.BankCardResp;
import com.xiji.cashloan.cl.model.pay.fuiou.agreement.BindXmlBeanReq;
import com.xiji.cashloan.cl.model.pay.fuiou.agreement.BindXmlBeanResp;
import com.xiji.cashloan.cl.model.pay.fuiou.agreement.OrderQryByMSsn;
import com.xiji.cashloan.cl.model.pay.fuiou.agreement.OrderQryResp;
import com.xiji.cashloan.cl.model.pay.fuiou.agreement.OrderXmlBeanReq;
import com.xiji.cashloan.cl.model.pay.fuiou.agreement.OrderXmlBeanResp;
import com.xiji.cashloan.cl.model.pay.fuiou.agreement.QryByFuiouOrderReq;
import com.xiji.cashloan.cl.model.pay.fuiou.agreement.QryByFuiouOrderResp;
import com.xiji.cashloan.cl.model.pay.fuiou.agreement.QueryPayOrderInfo;
import com.xiji.cashloan.cl.model.pay.fuiou.constant.FuiouConstant;
import com.xiji.cashloan.cl.util.fuiou.XMapUtil;
import com.xiji.cashloan.cl.util.fuiou.XmlBeanUtils;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.HttpsUtil;
import com.xiji.cashloan.core.common.util.OrderNoUtil;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tool.util.StringUtil;

/**
 * @Auther: king
 * @Date: 2018/11/23 17:06 协议支付帮助类
 * @Description:
 */
public class FuiouAgreementPayHelper extends BasePay {

    public static final Logger logger = LoggerFactory.getLogger(FuiouAgreementPayHelper.class);

    /**
     * 发送绑卡短信，验证码，同时验证四要素，只要能发送验证码，那么四要素就验证通过。
     */
    public BindXmlBeanResp bindMsg(BindXmlBeanReq beanReq) {
        beanReq.setVersion(FuiouConstant.PROTOCOL_VERSION);
        BindXmlBeanResp bindResult = new BindXmlBeanResp();
        String key = Global.getValue("fuiou_protocol_mchntcd_key");
        String privatekey = Global.getValue("fuiou_protocol_privatekey");
        String payUrl = Global.getValue("fuiou_protocol_bindmsg_url");
        String mchntcd = Global.getValue("fuiou_protocol_mchntcd");
        beanReq.setMchntCd(mchntcd);
        String signStr = beanReq.sendMsgSignStr(key);
        String resp = null;//返回值
        // 获取系统参数中支付启用状态
        String fuiouSwitch = Global.getValue("fuiou_switch");

        try {
            beanReq.setSign(getSign(signStr, FuiouConstant.SIGNTP, privatekey));
            saveReqLog(FuiouConstant.PROTOCOL_BINDMSG, beanReq.getMchntSsn(), signStr, JSON.toJSONString(beanReq));

            Map<String, String> param = new HashMap<String, String>();
            String APIFMS = XMapUtil.toXML(beanReq, FuiouConstant.charset);
            ;
            APIFMS = DESCoderFUIOU.desEncrypt(APIFMS, DESCoderFUIOU.getKeyLength8(key));
            param.put("MCHNTCD", mchntcd);
            param.put("APIFMS", APIFMS);
            if (StringUtil.isNotBlank(fuiouSwitch) && "1".equals(fuiouSwitch)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("请求地址：" + payUrl);
                }
                resp = HttpsUtil.postClient(payUrl, param);
                if (!isException(resp)) {
                    //解密数据
                    resp = DESCoderFUIOU.desDecrypt(resp, DESCoderFUIOU.getKeyLength8(key));
                }
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("关闭连连支付,模拟返回结果");
                }
                resp = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><RESPONSE> <VERSION>1.0</VERSION> <RESPONSECODE>0000</RESPONSECODE> <RESPONSEMSG>成功</RESPONSEMSG> <MCHNTSSN>20170630009</MCHNTSSN> </RESPONSE>";
            }

            if (!isException(resp)) {
                bindResult = XMapUtil.parseStr2Obj(BindXmlBeanResp.class, resp);
            } else {
                bindResult.setErrorCode(resp);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            bindResult.setErrorCode("3003");
        }
        modifyReqLog(beanReq.getMchntSsn(), resp);
        return bindResult;
    }

    /**
     * 协议绑卡（银行卡），签约
     */
    public BindXmlBeanResp bindCommit(BindXmlBeanReq beanReq) {
        beanReq.setVersion(FuiouConstant.PROTOCOL_VERSION);
        BindXmlBeanResp bindResult = new BindXmlBeanResp();
        String key = Global.getValue("fuiou_protocol_mchntcd_key");
        String privatekey = Global.getValue("fuiou_protocol_privatekey");
        String payUrl = Global.getValue("fuiou_protocol_bindcommit_url");
        String mchntcd = Global.getValue("fuiou_protocol_mchntcd");
        beanReq.setMchntCd(mchntcd);
        String signStr = beanReq.proBindSignStr(key);
        String resp = null;//返回值
        String orderNo = OrderNoUtil.getSerialNumber();
        // 获取系统参数中支付启用状态
        String fuiouSwitch = Global.getValue("fuiou_switch");

        try {
            beanReq.setSign(getSign(signStr, FuiouConstant.SIGNTP, privatekey));
            saveReqLog(FuiouConstant.PROTOCOL_BINDCOMMIT, orderNo, signStr, JSON.toJSONString(beanReq));

            Map<String, String> param = new HashMap<String, String>();
            String APIFMS = XMapUtil.toXML(beanReq, FuiouConstant.charset);
            ;
            APIFMS = DESCoderFUIOU.desEncrypt(APIFMS, DESCoderFUIOU.getKeyLength8(key));
            param.put("MCHNTCD", mchntcd);
            param.put("APIFMS", APIFMS);
            if (StringUtil.isNotBlank(fuiouSwitch) && "1".equals(fuiouSwitch)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("请求地址：" + payUrl);
                }
                resp = HttpsUtil.postClient(payUrl, param);
                if (!isException(resp)) {
                    //解密数据
                    resp = DESCoderFUIOU.desDecrypt(resp, DESCoderFUIOU.getKeyLength8(key));
                }
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("关闭连连支付,模拟返回结果");
                }
                resp = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><RESPONSE>\n"
                    + "<VERSION>1.0</VERSION> <RESPONSECODE>0000</RESPONSECODE> <RESPONSEMSG>协议绑卡成功!</RESPONSEMSG> <PROTOCOLNO>14987890970543651558</PROTOCOLNO> <MCHNTSSN>20170630010</MCHNTSSN>\n"
                    + "</RESPONSE>";
            }

            if (!isException(resp)) {
                bindResult = XMapUtil.parseStr2Obj(BindXmlBeanResp.class, resp);
            } else {
                bindResult.setErrorCode(resp);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            bindResult.setErrorCode("3003");
        }
        modifyReqLog(orderNo, resp);
        return bindResult;
    }

    /**
     * 绑卡解约，取消绑卡
     */
    public BindXmlBeanResp unbind(BindXmlBeanReq beanReq) {
        beanReq.setVersion(FuiouConstant.PROTOCOL_VERSION);
        BindXmlBeanResp bindResult = new BindXmlBeanResp();
        String key = Global.getValue("fuiou_protocol_mchntcd_key");
        String privatekey = Global.getValue("fuiou_protocol_privatekey");
        String payUrl = Global.getValue("fuiou_protocol_unbind_url");
        String mchntcd = Global.getValue("fuiou_protocol_mchntcd");
        beanReq.setMchntCd(mchntcd);
        String signStr = beanReq.proUnBindSignStr(key);
        String orderNo = OrderNoUtil.getSerialNumber();
        // 获取系统参数中支付启用状态
        String fuiouSwitch = Global.getValue("fuiou_switch");
        String resp = null;//返回值
        try {
            beanReq.setSign(getSign(signStr, FuiouConstant.SIGNTP, privatekey));
            saveReqLog(FuiouConstant.PROTOCOL_UNBIND, orderNo, signStr, JSON.toJSONString(beanReq));

            Map<String, String> param = new HashMap<String, String>();
            String APIFMS = XMapUtil.toXML(beanReq, FuiouConstant.charset);
            ;
            APIFMS = DESCoderFUIOU.desEncrypt(APIFMS, DESCoderFUIOU.getKeyLength8(key));
            param.put("MCHNTCD", mchntcd);
            param.put("APIFMS", APIFMS);
            if (StringUtil.isNotBlank(fuiouSwitch) && "1".equals(fuiouSwitch)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("请求地址：" + payUrl);
                }
                resp = HttpsUtil.postClient(payUrl, param);
                if (!isException(resp)) {
                    //解密数据
                    resp = DESCoderFUIOU.desDecrypt(resp, DESCoderFUIOU.getKeyLength8(key));
                }
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("关闭连连支付,模拟返回结果");
                }
                resp = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><RESPONSE>"
                    + "<VERSION>1.0</VERSION> <RESPONSECODE>0000</RESPONSECODE> <RESPONSEMSG>解绑成功!</RESPONSEMSG> <MCHNTCD>0002900F0096235</MCHNTCD> <USERID>1236985478</USERID> <PROTOCOLNO>14908601655875030001</PROTOCOLNO> </RESPONSE>";
            }

            if (!isException(resp)) {
                bindResult = XMapUtil.parseStr2Obj(BindXmlBeanResp.class, resp);
            } else {
                bindResult.setErrorCode(resp);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            bindResult.setErrorCode("3003");
        }
        modifyReqLog(orderNo, resp);
        return bindResult;
    }

    /**
     * 查询签约结果
     */
    public BindXmlBeanResp bindQuery(BindXmlBeanReq beanReq) {
        beanReq.setVersion(FuiouConstant.PROTOCOL_VERSION);
        BindXmlBeanResp bindResult = new BindXmlBeanResp();
        String key = Global.getValue("fuiou_protocol_mchntcd_key");
        String privatekey = Global.getValue("fuiou_protocol_privatekey");
        String payUrl = Global.getValue("fuiou_protocol_bindquery_url");
        String mchntcd = Global.getValue("fuiou_protocol_mchntcd");
        beanReq.setMchntCd(mchntcd);
        String signStr = beanReq.querySignStr(key);
        String orderNo = OrderNoUtil.getSerialNumber();
        String resp = null;//返回值
        // 获取系统参数中支付启用状态
        String fuiouSwitch = Global.getValue("fuiou_switch");

        try {
            beanReq.setSign(getSign(signStr, FuiouConstant.SIGNTP, privatekey));
            saveReqLog(FuiouConstant.PROTOCOL_BINDQUERY, orderNo, signStr, JSON.toJSONString(beanReq));

            Map<String, String> param = new HashMap<String, String>();
            String APIFMS = XMapUtil.toXML(beanReq, FuiouConstant.charset);
            ;
            APIFMS = DESCoderFUIOU.desEncrypt(APIFMS, DESCoderFUIOU.getKeyLength8(key));
            param.put("MCHNTCD", mchntcd);
            param.put("APIFMS", APIFMS);
            if (StringUtil.isNotBlank(fuiouSwitch) && "1".equals(fuiouSwitch)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("请求地址：" + payUrl);
                }
                resp = HttpsUtil.postClient(payUrl, param);
                if (!isException(resp)) {
                    //解密数据
                    resp = DESCoderFUIOU.desDecrypt(resp, DESCoderFUIOU.getKeyLength8(key));
                }
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("关闭连连支付,模拟返回结果");
                }
                resp = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><RESPONSE>"
                    + "<VERSION>1.0</VERSION> <RESPONSECODE>0000</RESPONSECODE> <RESPONSEMSG>解绑成功!</RESPONSEMSG> <MCHNTCD>0002900F0096235</MCHNTCD> <USERID>1236985478</USERID> <PROTOCOLNO>14908601655875030001</PROTOCOLNO> </RESPONSE>";
            }

            if (!isException(resp)) {
                bindResult = XMapUtil.parseStr2Obj(BindXmlBeanResp.class, resp);
            } else {
                bindResult.setErrorCode(resp);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            bindResult.setErrorCode("3003");
        }
        modifyReqLog(orderNo, resp);
        return bindResult;
    }

    /**
     * 扣款
     * 按还款计划进行代扣
     *
     * @Tips 商户请把返回的富友订单号和商户订单号关联好，后续进行查询以富友订单号为准
     */
    public OrderXmlBeanResp repayment(OrderXmlBeanReq beanReq) {
        beanReq.setVersion(FuiouConstant.PROTOCOL_VERSION);
        OrderXmlBeanResp bindResult = new OrderXmlBeanResp();
        String key = Global.getValue("fuiou_protocol_mchntcd_key");
        String privatekey = Global.getValue("fuiou_protocol_privatekey");
        String payUrl = Global.getValue("fuiou_protocol_order_url");
        String mchntcd = Global.getValue("fuiou_protocol_mchntcd");
        beanReq.setMchntCd(mchntcd);
        String signStr = beanReq.signStr(key);
        // 获取系统参数中支付启用状态
        String fuiouSwitch = Global.getValue("fuiou_switch");
        String resp = null;//返回值
        try {
            beanReq.setSignTp(FuiouConstant.SIGNTP);
            beanReq.setSign(getSign(signStr, FuiouConstant.SIGNTP, privatekey));
            saveReqLog(FuiouConstant.PROTOCOL_ORDER, beanReq.getMchntOrderId(), signStr, JSON.toJSONString(beanReq));

            Map<String, String> param = new HashMap<String, String>();
            String APIFMS = XMapUtil.toXML(beanReq, FuiouConstant.charset);
            ;
            APIFMS = DESCoderFUIOU.desEncrypt(APIFMS, DESCoderFUIOU.getKeyLength8(key));
            param.put("MCHNTCD", mchntcd);
            param.put("APIFMS", APIFMS);
            if (StringUtil.isNotBlank(fuiouSwitch) && "1".equals(fuiouSwitch)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("请求地址：" + payUrl);
                }
                resp = HttpsUtil.postClient(payUrl, param);
                if (!isException(resp)) {
                    //解密数据
                    resp = DESCoderFUIOU.desDecrypt(resp, DESCoderFUIOU.getKeyLength8(key));
                }
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("关闭连连支付,模拟返回结果");
                }
                resp = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><RESPONSE>\n"
                    + "<VERSION>1.0</VERSION>\n"
                    + "<TYPE>03</TYPE>\n"
                    + "<RESPONSECODE>0000</RESPONSECODE> <RESPONSEMSG>成功</RESPONSEMSG> <MCHNTCD>0002900F0096235</MCHNTCD> <USERID>1236985478</USERID> <MCHNTORDERID>14909408631788350725</MCHNTORDERID> <ORDERID>000033454458</ORDERID> <BANKCARD>6226090217436936</BANKCARD> <AMT>200</AMT>\n"
                    + "<REM1>rem1</REM1>\n"
                    + "<REM2>rem2</REM2>\n"
                    + "<REM3>rem3</REM3>\n"
                    + "<SIGNTP>MD5</SIGNTP> <SIGN>1ceeb01310f53356100d38d409a7f640</SIGN> <PROTOCOLNO>14907763938986631634</PROTOCOLNO> </RESPONSE>";
            }

            if (!isException(resp)) {
                bindResult = XMapUtil.parseStr2Obj(OrderXmlBeanResp.class, resp);
            } else {
                bindResult.setErrorCode(resp);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            bindResult.setErrorCode("3003");
        }
        modifyReqLog(beanReq.getMchntOrderId(), resp);
        return bindResult;
    }

    /**
     * 协议支付 - 银行还款扣款查询（by富友订单号）
     */
    public QryByFuiouOrderResp queryOrderId(QryByFuiouOrderReq beanReq) {
        QryByFuiouOrderResp bindResult = new QryByFuiouOrderResp();
        String key = Global.getValue("fuiou_protocol_mchntcd_key");
        String mchntcd = Global.getValue("fuiou_protocol_mchntcd");
        String payUrl = Global.getValue("fuiou_protocol_queryorderid_url");
        beanReq.setMchntCd(mchntcd);
        String signStr = beanReq.querySignStr(key);
        String orderNo = OrderNoUtil.getSerialNumber();
        String resp = null;//返回值
        // 获取系统参数中支付启用状态
        String fuiouSwitch = Global.getValue("fuiou_switch");

        try {
            beanReq.setSign(MD5.MD5Encode(signStr));
            saveReqLog(FuiouConstant.PROTOCOL_QUERYORDERID, orderNo, signStr, JSON.toJSONString(beanReq));

            Map<String, String> param = new HashMap<String, String>();
            String fm = XMapUtil.toXML(beanReq, FuiouConstant.charset);
            ;
            param.put("FM", fm);
            if (StringUtil.isNotBlank(fuiouSwitch) && "1".equals(fuiouSwitch)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("请求地址：" + payUrl);
                }
                resp = HttpsUtil.postClient(payUrl, param);
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("关闭连连支付,模拟返回结果");
                }
                resp = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><FM>\n"
                    + "<Rcd>5185</Rcd> <RDesc>订单已支付</RDesc>\n"
                    + "<Sign>4nsdi123basd9asdn91f</Sign> </FM>";
            }

            if (!isException(resp)) {
                bindResult = XMapUtil.parseStr2Obj(QryByFuiouOrderResp.class, resp);
            } else {
                bindResult.setErrorCode(resp);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            bindResult.setErrorCode("3003");
        }
        modifyReqLog(orderNo, resp);
        return bindResult;
    }

    /**
     * 协议支付 - 银行还款扣款查询（by商户订单号）
     */
    public OrderQryResp checkResult(OrderQryByMSsn beanReq) {
        OrderQryResp bindResult = new OrderQryResp();
        String key = Global.getValue("fuiou_protocol_mchntcd_key");
        String mchntcd = Global.getValue("fuiou_protocol_mchntcd");
        String payUrl = Global.getValue("fuiou_protocol_checkresult_url");
        beanReq.setMchntCd(mchntcd);
        beanReq.setVersion("3.0");
        String signStr = beanReq.querySign(key);
        String orderNo = OrderNoUtil.getSerialNumber();
        String resp = null;//返回值
        // 获取系统参数中支付启用状态
        String fuiouSwitch = Global.getValue("fuiou_switch");

        try {
            beanReq.setSign(MD5.MD5Encode(signStr));
            saveReqLog(FuiouConstant.PROTOCOL_CHECKRESULT, orderNo, signStr, JSON.toJSONString(beanReq));

            Map<String, String> param = new HashMap<String, String>();
            String fm = XmlBeanUtils.convertBean2Xml(beanReq, "UTF-8", false);
            param.put("FM", fm);
            if (StringUtil.isNotBlank(fuiouSwitch) && "1".equals(fuiouSwitch)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("请求地址：" + payUrl);
                }
                resp = HttpsUtil.postClient(payUrl, param);
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("关闭连连支付,模拟返回结果");
                }
                resp = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><RESPONSE>\n"
                    + "<VERSION>3.0</VERSION> <RESPONSECODE>0000</RESPONSECODE> <RESPONSEMSG>支付成功</RESPONSEMSG> <MCHNTORDERID>20171030010008223</MCHNTORDERID> <AMT>200</AMT> <BANKCARD>622202*********5810</BANKCARD> <SIGN>c24cb41b80db7556eb1aa163b1733c8e</SIGN> <ORDERDATE>20171030</ORDERDATE>\n"
                    + "</RESPONSE>";
            }

            if (!isException(resp)) {
                bindResult = XMapUtil.parseStr2Obj(OrderQryResp.class, resp);
            } else {
                bindResult.setErrorCode(resp);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            bindResult.setErrorCode("3003");
        }
        modifyReqLog(orderNo, resp);
        return bindResult;
    }

    /**
     * - 卡Bin查询
     */
    public BankCardResp cardBinQuery(BankCardReq beanReq) {
        BankCardResp bindResult = new BankCardResp();
        String key = Global.getValue("fuiou_protocol_mchntcd_key");
        String mchntcd = Global.getValue("fuiou_protocol_mchntcd");
        String payUrl = Global.getValue("fuiou_protocol_cardbinquery_url");

        beanReq.setMchntCd(mchntcd);
        String signStr = beanReq.querySign(key);
        String orderNo = OrderNoUtil.getSerialNumber();
        String resp = null;//返回值
        // 获取系统参数中支付启用状态
        String fuiouSwitch = Global.getValue("fuiou_switch");

        try {
            beanReq.setSign(MD5.MD5Encode(signStr));
            saveReqLog(FuiouConstant.PROTOCOL_CARDBINQUERY, orderNo, signStr, JSON.toJSONString(beanReq));

            Map<String, String> param = new HashMap<String, String>();
            String fm = XMapUtil.toXML(beanReq, FuiouConstant.charset);
            ;
            param.put("FM", fm);
            if (StringUtil.isNotBlank(fuiouSwitch) && "1".equals(fuiouSwitch)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("请求地址：" + payUrl);
                }
                resp = HttpsUtil.postClient(payUrl, param);
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("关闭连连支付,模拟返回结果");
                }
                resp = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><FM>\n"
                    + "<Rcd>0000</Rcd> <RDesc>成功</RDesc> <Ctp>02</Ctp>\n"
                    + "<Cnm>中信银行</Cnm> <InsCd>0863020000</InsCd> <Sign>4nsdi123basd9asdn91f</Sign>\n"
                    + "</FM>";
            }

            if (!isException(resp)) {
                bindResult = XMapUtil.parseStr2Obj(BankCardResp.class, resp);
            } else {
                bindResult.setErrorCode(resp);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            bindResult.setErrorCode("3003");
        }
        modifyReqLog(orderNo, resp);
        return bindResult;
    }

    /**
     * 获取签名
     *
     * @param signStr 签名串
     * @param signtp 签名类型
     * @param key 密钥
     */
    public static String getSign(String signStr, String signtp, String key) throws Exception {
        String sign = "";
        if ("md5".equalsIgnoreCase(signtp)) {
            sign = MD5.MD5Encode(signStr);
        } else {
            sign = RSAUtils.sign(signStr.getBytes("utf-8"), key);
        }
        return sign;
    }

    public QueryPayOrderInfo queryPayInfo(RepaymentQueryVo vo) {
        QueryPayOrderInfo orderInfo = new QueryPayOrderInfo();
        if (vo == null) {
            return orderInfo;
        }
        String key = Global.getValue("fuiou_protocol_mchntcd_key");
        //优先使用支付号查询
        if (StringUtil.isNotEmpty(vo.getPayPlatNo())) {
            QryByFuiouOrderReq beanReq = new QryByFuiouOrderReq();
            beanReq.setOrderId(vo.getPayPlatNo());
            QryByFuiouOrderResp resp = queryOrderId(beanReq);

            if (resp.checkSign(key)) {
                if (StringUtil.equals(FuiouConstant.PROTOCOL_QUERYORDERID_PAYSUCCESS, resp.getRcd())) {
                    orderInfo.setCode(PayConstant.QUERY_PAY_SUCCESS);
                } else if (StringUtil.equals(FuiouConstant.PROTOCOL_QUERYORDERID_PAYING, resp.getRcd())) {
                    orderInfo.setCode(PayConstant.QUERY_PAY_PROCESSING);
                } else {
                    orderInfo.setCode(PayConstant.QUERY_PAY_FAIL);
                }
                orderInfo.setMsg(resp.getrDesc());
            } else {
                orderInfo.setCode(PayConstant.QUERY_PAY_ERROR);
            }
        }else  if (StringUtil.isNotEmpty(vo.getOrderNo())) {
            OrderQryByMSsn beanreq = new OrderQryByMSsn();
            beanreq.setMchntOrderId(vo.getOrderNo());

            OrderQryResp resp = checkResult(beanreq);

            if (resp.checkSign(key)){
                if (StringUtil.equals(FuiouConstant.RESPONSE_SUCCESS_CODE, resp.getResponseCode())) {
                    orderInfo.setCode(PayConstant.QUERY_PAY_SUCCESS);
                } else if (StringUtil.equals(FuiouConstant.RESPONSE_PAY_PROCESSING, resp.getResponseCode())) {
                    orderInfo.setCode(PayConstant.QUERY_PAY_PROCESSING);
                } else {
                    orderInfo.setCode(PayConstant.QUERY_PAY_FAIL);
                }
                orderInfo.setMsg(resp.getResponseMsg());
            }else {
                orderInfo.setCode(PayConstant.QUERY_PAY_ERROR);
            }
        }
        return orderInfo;
    }

}
