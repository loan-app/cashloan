package com.jiya.cashloan.agreepay;

import com.alibaba.fastjson.JSON;
import com.fuiou.mpay.encrypt.DESCoderFUIOU;
import com.fuiou.mpay.encrypt.RSAUtils;
import com.fuiou.util.MD5;
import com.rongdu.cashloan.cl.model.pay.fuiou.agreement.BankCardReq;
import com.rongdu.cashloan.cl.model.pay.fuiou.agreement.BankCardResp;
import com.rongdu.cashloan.cl.model.pay.fuiou.agreement.BindXmlBeanReq;
import com.rongdu.cashloan.cl.model.pay.fuiou.agreement.BindXmlBeanResp;
import com.rongdu.cashloan.cl.model.pay.fuiou.agreement.OrderQryByMSsn;
import com.rongdu.cashloan.cl.model.pay.fuiou.agreement.OrderQryResp;
import com.rongdu.cashloan.cl.model.pay.fuiou.agreement.OrderXmlBeanReq;
import com.rongdu.cashloan.cl.model.pay.fuiou.agreement.OrderXmlBeanResp;
import com.rongdu.cashloan.cl.model.pay.fuiou.agreement.QryByFuiouOrderReq;
import com.rongdu.cashloan.cl.model.pay.fuiou.agreement.QryByFuiouOrderResp;
import com.rongdu.cashloan.cl.model.pay.fuiou.constant.FuiouConstant;
import com.rongdu.cashloan.cl.util.fuiou.AmtUtil;
import com.rongdu.cashloan.cl.util.fuiou.XMapUtil;
import com.rongdu.cashloan.cl.util.fuiou.XmlBeanUtils;
import com.rongdu.cashloan.core.common.util.HttpsUtil;
import com.rongdu.cashloan.core.common.util.OrderNoUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tool.util.DateUtil;
import tool.util.StringUtil;

/**
 * @Auther: king
 * @Date: 2018/11/27 17:33
 * @Description:
 */
public class AgreePayTest {
    public static final Logger logger = LoggerFactory.getLogger(AgreePayTest.class);
    protected static boolean isException(String resp) {
        return StringUtils.startsWith(resp, "300");
    }
    public static String getSign(String signStr,String signtp,String key) throws  Exception{
        String sign = "";
        if ("md5".equalsIgnoreCase(signtp)) {
            sign = MD5.MD5Encode(signStr);
        } else {
            sign =	RSAUtils.sign(signStr.getBytes("utf-8"), key);
        }
        return sign;
    }
    public static void main(String[] args) {
//        testBindMsg();
//        testBindCommit();
//        testbindQuery();
//        testunBind();
        testRepayment();
//        testQueryOrderId();
//        testCheckResult();
//        testcardBinQuery();
    }

    public static void testBindMsg() {
        BindXmlBeanReq beanReq = new BindXmlBeanReq();
        beanReq.setUserId("8888888");
        beanReq.setTradeDate(DateUtil.dateStr7(new Date()));
        beanReq.setMchntSsn(OrderNoUtil.getSerialNumber());
        beanReq.setAccount("孙悟空");
        beanReq.setCardNo("6225885865354170");
        beanReq.setIdType("0");
        beanReq.setIdCard("420116199001011234");
        beanReq.setMobileNo("15388888888");
        BindXmlBeanResp resp = bindMsg(beanReq);
        if (resp != null) {
            System.out.println(resp.getResponseCode());
        }
    }

    public static void testBindCommit() {
        BindXmlBeanReq beanReq = new BindXmlBeanReq();
        beanReq.setUserId("8888888");
        beanReq.setTradeDate(DateUtil.dateStr7(new Date()));
        beanReq.setMchntSsn(OrderNoUtil.getSerialNumber());
        beanReq.setAccount("孙悟空");
        beanReq.setCardNo("6225885865354170");
        beanReq.setIdType("0");
        beanReq.setIdCard("420116199001011234");
        beanReq.setMobileNo("15388888888");
        BindXmlBeanResp resp = bindMsg(beanReq);
        if (resp != null) {
            System.out.println(resp.getResponseCode());
        }
        beanReq.setMsgCode("000000");
//        beanReq.setMchntSsn(OrderNoUtil.getSerialNumber());
        BindXmlBeanResp bindCommit = bindCommit(beanReq);
        System.out.println(JSON.toJSON(bindCommit));
    }
    public static void testbindQuery() {
        BindXmlBeanReq beanReq = new BindXmlBeanReq();
        beanReq.setUserId("8888888");
        BindXmlBeanResp resp = bindQuery(beanReq);
        System.out.println(JSON.toJSON(resp));
    }
    public static void testunBind() {
        BindXmlBeanReq beanReq = new BindXmlBeanReq();
        beanReq.setUserId("8888888");
        beanReq.setProtocolNo("TJ41R1100000000296GD8N");
        BindXmlBeanResp resp = unbind(beanReq);
        System.out.println(JSON.toJSON(resp));
    }

    public static void testRepayment() {
        OrderXmlBeanReq beanReq = new OrderXmlBeanReq();
        beanReq.setUserId("8888888");
        beanReq.setUserIp("192.168.0.108");
        beanReq.setType("03");
        beanReq.setMchntOrderId(OrderNoUtil.getSerialNumber());
        beanReq.setAmt(AmtUtil.convertAmtToBranch("123.06"));
        beanReq.setProtocolNo("TJ41R1100000000296GD8N");
        beanReq.setNeedSendMsg("0");
        beanReq.setBackUrl("http://www-1.fuiou.com:18670/mobile_pay/newpropay/bindMsg.pay");
        beanReq.setRem1("rem1Test1");
        beanReq.setRem2("rem1Test2");
        beanReq.setRem3("rem1Test3");
        String key = "5old71wihg2tqjug9kkpxnhx9hiujoqj";
        OrderXmlBeanResp resp = repayment(beanReq);
        if (resp != null && StringUtils.equalsIgnoreCase(resp.getSignTp(),FuiouConstant.SIGNTP)
            && MD5.MD5Encode(resp.signReturnMsg(key)).equals(resp.getSign())) {
            System.out.println("sign check success!");
        }
        System.out.println(JSON.toJSON(resp));
    }
    public static void testQueryOrderId() {
        QryByFuiouOrderReq byFuiouOrderReq = new QryByFuiouOrderReq();
        byFuiouOrderReq.setOrderId("000070051802");
        QryByFuiouOrderResp resp = queryOrderId(byFuiouOrderReq);
        if (resp != null) {
            String key = "5old71wihg2tqjug9kkpxnhx9hiujoqj";
            if ( MD5.MD5Encode(resp.signReturnMsg(key)).equals(resp.getSign())){
                System.out.println("sign check success!");
            }
        }
        System.out.println(JSON.toJSON(resp));
    }
    public static void testCheckResult() {
        OrderQryByMSsn beanreq = new OrderQryByMSsn();
        beanreq.setMchntOrderId("1811271722883788");
        beanreq.setRem1("rem1Test");
        OrderQryResp resp = checkResult(beanreq);
        String key = "5old71wihg2tqjug9kkpxnhx9hiujoqj";
        if (resp.checkReturn() && resp.checkSign(key)) {
            System.out.println("sign check success!");
        }
        System.out.println(JSON.toJSON(resp));
    }
    public static void testcardBinQuery() {
        BankCardReq beanreq = new BankCardReq();
        beanreq.setOno("6225880158386678");
        BankCardResp resp = cardBinQuery(beanreq);
        String key = "5old71wihg2tqjug9kkpxnhx9hiujoqj";
        if (resp.checkReturn() && resp.checkSign(key)) {
            System.out.println("sign check success!");
        }
        System.out.println(JSON.toJSON(resp));
    }

    public static BindXmlBeanResp bindMsg(BindXmlBeanReq beanReq) {
        beanReq.setVersion(FuiouConstant.PROTOCOL_VERSION);
        BindXmlBeanResp bindResult = new BindXmlBeanResp();
        String key = "5old71wihg2tqjug9kkpxnhx9hiujoqj";
        String privatekey =  "";
//        String payUrl = "http://www-1.fuiou.com:18670/mobile_pay/newpropay/bindMsg.pay";
        String payUrl = "http://www-1.fuiou.com:18670/mobile_pay/newpropay/bindMsg.pay";
        String mchntcd = "0002900F0096235";
        beanReq.setMchntCd(mchntcd);
        String signStr = beanReq.sendMsgSignStr(key);
        String resp = null;//返回值
        try {
            beanReq.setSign(MD5.MD5Encode(signStr));
//            saveReqLog(FuiouConstant.PROTOCOL_BINDMSG,beanReq.getMchntSsn(), signStr, JSON.toJSONString(beanReq));

            // 获取系统参数中支付启用状态
            String fuiouSwitch = "1";

            Map<String,String> param = new HashMap<String, String>();
            String APIFMS = XMapUtil.toXML(beanReq, FuiouConstant.charset);;
            APIFMS = DESCoderFUIOU.desEncrypt(APIFMS, DESCoderFUIOU.getKeyLength8(key));
            param.put("MCHNTCD",mchntcd);
            param.put("APIFMS", APIFMS);
            if (StringUtil.isNotBlank(fuiouSwitch) && "1".equals(fuiouSwitch)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("请求地址：" + payUrl);
                }
                resp = HttpsUtil.postClient(payUrl, param);
                if (!isException(resp)) {
                    //解密数据
                    resp = DESCoderFUIOU.desDecrypt(resp,DESCoderFUIOU.getKeyLength8(key));
                }
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("关闭连连支付,模拟返回结果");
                }
                resp = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><RESPONSE> <VERSION>1.0</VERSION> <RESPONSECODE>0000</RESPONSECODE> <RESPONSEMSG>成功</RESPONSEMSG> <MCHNTSSN>20170630009</MCHNTSSN> </RESPONSE>";
            }

            if (!isException(resp)) {
                bindResult = XMapUtil.parseStr2Obj(BindXmlBeanResp.class,resp);
            } else {
                bindResult.setErrorCode(resp);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            bindResult.setErrorCode("3003");
        }
//        modifyReqLog(beanReq.getMchntSsn(),resp);
        return bindResult;
    }

    public static BindXmlBeanResp bindCommit(BindXmlBeanReq beanReq) {
        beanReq.setVersion(FuiouConstant.PROTOCOL_VERSION);
        BindXmlBeanResp bindResult = new BindXmlBeanResp();
        String key = "5old71wihg2tqjug9kkpxnhx9hiujoqj";
        String privatekey =  "";
        String payUrl = "http://www-1.fuiou.com:18670/mobile_pay/newpropay/bindCommit.pay";
        String mchntcd = "0002900F0096235";
        beanReq.setMchntCd(mchntcd);
        String signStr = beanReq.proBindSignStr(key);
        String resp = null;//返回值
        String orderNo = OrderNoUtil.getSerialNumber();
        try {
            beanReq.setSign(getSign(signStr, FuiouConstant.SIGNTP, privatekey));
//            saveReqLog(FuiouConstant.PROTOCOL_BINDCOMMIT,orderNo, signStr, JSON.toJSONString(beanReq));

            // 获取系统参数中支付启用状态
            String fuiouSwitch = "1";

            Map<String,String> param = new HashMap<String, String>();
            String APIFMS = XMapUtil.toXML(beanReq, FuiouConstant.charset);;
            APIFMS = DESCoderFUIOU.desEncrypt(APIFMS, DESCoderFUIOU.getKeyLength8(key));
            param.put("MCHNTCD",mchntcd);
            param.put("APIFMS", APIFMS);
            if (StringUtil.isNotBlank(fuiouSwitch) && "1".equals(fuiouSwitch)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("请求地址：" + payUrl);
                }
                resp = HttpsUtil.postClient(payUrl, param);
                if (!isException(resp)) {
                    //解密数据
                    resp = DESCoderFUIOU.desDecrypt(resp,DESCoderFUIOU.getKeyLength8(key));
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
                bindResult = XMapUtil.parseStr2Obj(BindXmlBeanResp.class,resp);
            } else {
                bindResult.setErrorCode(resp);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            bindResult.setErrorCode("3003");
        }
//        modifyReqLog(orderNo,resp);
        return bindResult;
    }

    /**
     * 查询签约结果
     *
     * @return
     */
    public static BindXmlBeanResp bindQuery(BindXmlBeanReq beanReq) {
        beanReq.setVersion(FuiouConstant.PROTOCOL_VERSION);
        BindXmlBeanResp bindResult = new BindXmlBeanResp();
        String key = "5old71wihg2tqjug9kkpxnhx9hiujoqj";
        String privatekey =  "";
        String payUrl = "http://www-1.fuiou.com:18670/mobile_pay/newpropay/bindQuery.pay";
        String mchntcd = "0002900F0096235";
        // 获取系统参数中支付启用状态
        String fuiouSwitch = "1";
        beanReq.setMchntCd(mchntcd);

        String signStr = beanReq.querySignStr(key);
        String orderNo = OrderNoUtil.getSerialNumber();
        String resp = null;//返回值
        try {
            beanReq.setSign(getSign(signStr, FuiouConstant.SIGNTP, privatekey));
//            saveReqLog(FuiouConstant.PROTOCOL_BINDQUERY,orderNo, signStr, JSON.toJSONString(beanReq));
            Map<String,String> param = new HashMap<String, String>();
            String APIFMS = XMapUtil.toXML(beanReq, FuiouConstant.charset);;
            APIFMS = DESCoderFUIOU.desEncrypt(APIFMS, DESCoderFUIOU.getKeyLength8(key));
            param.put("MCHNTCD",mchntcd);
            param.put("APIFMS", APIFMS);
            if (StringUtil.isNotBlank(fuiouSwitch) && "1".equals(fuiouSwitch)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("请求地址：" + payUrl);
                }
                resp = HttpsUtil.postClient(payUrl, param);
                if (!isException(resp)) {
                    //解密数据
                    resp = DESCoderFUIOU.desDecrypt(resp,DESCoderFUIOU.getKeyLength8(key));
                }
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("关闭连连支付,模拟返回结果");
                }
                resp = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><RESPONSE>"
                    + "<VERSION>1.0</VERSION> <RESPONSECODE>0000</RESPONSECODE> <RESPONSEMSG>解绑成功!</RESPONSEMSG> <MCHNTCD>0002900F0096235</MCHNTCD> <USERID>1236985478</USERID> <PROTOCOLNO>14908601655875030001</PROTOCOLNO> </RESPONSE>";
            }

            if (!isException(resp)) {
                bindResult = XMapUtil.parseStr2Obj(BindXmlBeanResp.class,resp);
            } else {
                bindResult.setErrorCode(resp);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            bindResult.setErrorCode("3003");
        }
//        modifyReqLog(orderNo,resp);
        return bindResult;
    }


    public static BindXmlBeanResp unbind(BindXmlBeanReq beanReq) {
        beanReq.setVersion(FuiouConstant.PROTOCOL_VERSION);
        BindXmlBeanResp bindResult = new BindXmlBeanResp();
        String key = "5old71wihg2tqjug9kkpxnhx9hiujoqj";
        String privatekey =  "";
        String payUrl = "http://www-1.fuiou.com:18670/mobile_pay/newpropay/unbind.pay";
        String mchntcd = "0002900F0096235";
        beanReq.setMchntCd(mchntcd);
        String signStr = beanReq.proUnBindSignStr(key);
        String orderNo = OrderNoUtil.getSerialNumber();
        // 获取系统参数中支付启用状态
        String fuiouSwitch = "1";
        String resp = null;//返回值
        try {
            beanReq.setSign(getSign(signStr, FuiouConstant.SIGNTP, privatekey));
//            saveReqLog(FuiouConstant.PROTOCOL_UNBIND,orderNo, signStr, JSON.toJSONString(beanReq));

            Map<String,String> param = new HashMap<String, String>();
            String APIFMS = XMapUtil.toXML(beanReq, FuiouConstant.charset);;
            APIFMS = DESCoderFUIOU.desEncrypt(APIFMS, DESCoderFUIOU.getKeyLength8(key));
            param.put("MCHNTCD",mchntcd);
            param.put("APIFMS", APIFMS);
            if (StringUtil.isNotBlank(fuiouSwitch) && "1".equals(fuiouSwitch)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("请求地址：" + payUrl);
                }
                resp = HttpsUtil.postClient(payUrl, param);
                if (!isException(resp)) {
                    //解密数据
                    resp = DESCoderFUIOU.desDecrypt(resp,DESCoderFUIOU.getKeyLength8(key));
                }
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("关闭连连支付,模拟返回结果");
                }
                resp = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><RESPONSE>"
                    + "<VERSION>1.0</VERSION> <RESPONSECODE>0000</RESPONSECODE> <RESPONSEMSG>解绑成功!</RESPONSEMSG> <MCHNTCD>0002900F0096235</MCHNTCD> <USERID>1236985478</USERID> <PROTOCOLNO>14908601655875030001</PROTOCOLNO> </RESPONSE>";
            }

            if (!isException(resp)) {
                bindResult = XMapUtil.parseStr2Obj(BindXmlBeanResp.class,resp);
            } else {
                bindResult.setErrorCode(resp);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            bindResult.setErrorCode("3003");
        }
//        modifyReqLog(orderNo,resp);
        return bindResult;
    }

    public static OrderXmlBeanResp repayment(OrderXmlBeanReq beanReq){
        beanReq.setVersion(FuiouConstant.PROTOCOL_VERSION);
        OrderXmlBeanResp bindResult = new OrderXmlBeanResp();
        String key = "5old71wihg2tqjug9kkpxnhx9hiujoqj";
        String privatekey =  "";
        String payUrl = "http://www-1.fuiou.com:18670/mobile_pay/newpropay/order.pay";
        String mchntcd = "0002900F0096235";
        beanReq.setMchntCd(mchntcd);
        String signStr = beanReq.signStr(key);
        String resp = null;//返回值
        // 获取系统参数中支付启用状态
        String fuiouSwitch = "1";
        try {
            beanReq.setSignTp(FuiouConstant.SIGNTP);
            beanReq.setSign(getSign(signStr, FuiouConstant.SIGNTP, privatekey));
//            saveReqLog(FuiouConstant.PROTOCOL_ORDER,beanReq.getMchntOrderId(), signStr, JSON.toJSONString(beanReq));

            Map<String,String> param = new HashMap<String, String>();
            String APIFMS = XMapUtil.toXML(beanReq, FuiouConstant.charset);;
            APIFMS = DESCoderFUIOU.desEncrypt(APIFMS, DESCoderFUIOU.getKeyLength8(key));
            param.put("MCHNTCD",mchntcd);
            param.put("APIFMS", APIFMS);
            if (StringUtil.isNotBlank(fuiouSwitch) && "1".equals(fuiouSwitch)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("请求地址：" + payUrl);
                }
                resp = HttpsUtil.postClient(payUrl, param);
                if (!isException(resp)) {
                    //解密数据
                    resp = DESCoderFUIOU.desDecrypt(resp,DESCoderFUIOU.getKeyLength8(key));
                }
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("关闭连连支付,模拟返回结果");
                }
                resp = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><RESPONSE>\n"
                    + "<VERSION>1.0</VERSION>\n"
                    + "<TYPE>03</TYPE>\n"
                    + "<RESPONSECODE>0000</RESPONSECODE> <RESPONSEMSG>成功</RESPONSEMSG> <MCHNTCD>0002900F0096235</MCHNTCD> <USERID>1236985478</USERID> <MCHNTORDERID>14909408631788350725</MCHNTORDERID> <ORDERID>000033454458</ORDERID> <BANKCARD>6226090217436936</BANKCARD> <AMT>200</AMT>"
                    + "<REM1>rem1</REM1>"
                    + "<REM2>rem2\n</REM2>\n"
                    + "<REM3>rem3</REM3>"
                    + "<SIGNTP>MD5</SIGNTP> <SIGN>1ceeb01310f53356100d38d409a7f640</SIGN> <PROTOCOLNO>14907763938986631634</PROTOCOLNO> </RESPONSE>";
            }

            if (!isException(resp)) {
                bindResult = XMapUtil.parseStr2Obj(OrderXmlBeanResp.class,resp);
            } else {
                bindResult.setErrorCode(resp);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            bindResult.setErrorCode("3003");
        }
//        modifyReqLog(beanReq.getMchntOrderId(),resp);
        return bindResult;
    }


    public static QryByFuiouOrderResp queryOrderId(QryByFuiouOrderReq beanReq){
        QryByFuiouOrderResp bindResult = new QryByFuiouOrderResp();
        String key = "5old71wihg2tqjug9kkpxnhx9hiujoqj";
        String payUrl = "http://www-1.fuiou.com:18670/mobile_pay/findPay/queryOrderId.pay";
        String mchntcd = "0002900F0096235";
        beanReq.setMchntCd(mchntcd);
        String signStr = beanReq.querySignStr(key);
        String orderNo = OrderNoUtil.getSerialNumber();
        String resp = null;//返回值
        // 获取系统参数中支付启用状态
        String fuiouSwitch = "1";

        try {
            beanReq.setSign(MD5.MD5Encode(signStr));
//            saveReqLog(FuiouConstant.PROTOCOL_QUERYORDERID,orderNo, signStr, JSON.toJSONString(beanReq));

            Map<String,String> param = new HashMap<String, String>();
            String fm = XMapUtil.toXML(beanReq, FuiouConstant.charset);;
            param.put("FM",fm);
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
                bindResult = XMapUtil.parseStr2Obj(QryByFuiouOrderResp.class,resp);
            } else {
                bindResult.setErrorCode(resp);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            bindResult.setErrorCode("3003");
        }
//        modifyReqLog(orderNo,resp);
        return bindResult;
    }


    public static OrderQryResp checkResult(OrderQryByMSsn beanReq){
        OrderQryResp bindResult = new OrderQryResp();
        String key = "5old71wihg2tqjug9kkpxnhx9hiujoqj";
        String payUrl = "http://www-1.fuiou.com:18670/mobile_pay/checkInfo/checkResult.pay";
        String mchntcd = "0002900F0096235";
        beanReq.setMchntCd(mchntcd);
        beanReq.setVersion("3.0");
        String signStr = beanReq.querySign(key);
        String orderNo = OrderNoUtil.getSerialNumber();
        String resp = null;//返回值

        // 获取系统参数中支付启用状态
        String fuiouSwitch = "1";

        try {
            beanReq.setSign(MD5.MD5Encode(signStr));
//            saveReqLog(FuiouConstant.PROTOCOL_CHECKRESULT,orderNo, signStr, JSON.toJSONString(beanReq));

            Map<String,String> param = new HashMap<String, String>();
            String fm = XmlBeanUtils.convertBean2Xml(beanReq, "UTF-8", false);
            param.put("FM",fm);
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
                bindResult = XMapUtil.parseStr2Obj(OrderQryResp.class,resp);
            } else {
                bindResult.setErrorCode(resp);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            bindResult.setErrorCode("3003");
        }
//        modifyReqLog(orderNo,resp);
        return bindResult;
    }
    public static BankCardResp cardBinQuery(BankCardReq beanReq) {
        BankCardResp bindResult = new BankCardResp();
        String key = "5old71wihg2tqjug9kkpxnhx9hiujoqj";
        String payUrl = "http://www-1.fuiou.com:18670/mobile_pay/findPay/cardBinQuery.pay";
        String mchntcd = "0002900F0096235";

        beanReq.setMchntCd(mchntcd);
        String signStr = beanReq.querySign(key);
        String orderNo = OrderNoUtil.getSerialNumber();
        String resp = null;//返回值
        // 获取系统参数中支付启用状态
        String fuiouSwitch = "1";

        try {
            beanReq.setSign(MD5.MD5Encode(signStr));
//            saveReqLog(FuiouConstant.PROTOCOL_CARDBINQUERY,orderNo, signStr, JSON.toJSONString(beanReq));

            Map<String,String> param = new HashMap<String, String>();
            String fm = XMapUtil.toXML(beanReq, FuiouConstant.charset);;
            param.put("FM",fm);
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
                bindResult = XMapUtil.parseStr2Obj(BankCardResp.class,resp);
            } else {
                bindResult.setErrorCode(resp);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            bindResult.setErrorCode("3003");
        }
//        modifyReqLog(orderNo,resp);
        return bindResult;
    }
}
