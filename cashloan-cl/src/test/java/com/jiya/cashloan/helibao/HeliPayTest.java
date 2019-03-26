package com.jiya.cashloan.helibao;

import com.alibaba.fastjson.JSON;
import com.xiji.cashloan.cl.model.pay.common.constant.PayConstant;
import com.xiji.cashloan.cl.model.pay.helipay.HelipayHelper;
import com.xiji.cashloan.cl.model.pay.helipay.constant.HelipayConstant;
import com.xiji.cashloan.cl.model.pay.helipay.util.HelipayUtil;
import com.xiji.cashloan.cl.model.pay.helipay.util.MessageHandle;
import com.xiji.cashloan.cl.model.pay.helipay.vo.request.AgreementBindCardValidateCodeVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.request.BindCardPayVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.request.BindCardVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.request.PayForReqVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.request.QueryOrderVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.request.UnBindCardVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.AgreementSendValidateCodeResponseVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.BindCardPayResponseVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.BindCardResponseVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.HeliPayForPaymentQueryResponseVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.HeliPayForPaymentResultVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.QueryOrderResponseVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.UnBindCardResponseVo;
import com.xiji.cashloan.core.common.util.StringUtil;
import java.io.File;
import org.apache.log4j.Logger;

/**
 * @Auther: king
 * @Date: 2019/1/23 16:46
 * @Description:
 */
public class HeliPayTest {
    private static final Logger logger = Logger.getLogger(HeliPayTest.class);

    /**
     * 商户编号：C1800000002 快捷接口测试证书
     *
     * C1800000002.pfx 证书密码：qwer1234
     *
     * 代付接口：
     *
     * MD5签名密钥：Vx977zHtKzoMkb3vZjxLzVvNHg1469cO
     *
     * RSA私钥：MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAM+PcQN9JmrYJ+sKwI9kmBLAsBS6HHDT2Sgkh8BYbyTKovSMQat1II6l42HhUH6lr7bXwnRUw8I4qrNBZjz4cWZBi+vgkSL/1f1M/erW27t61DobvpgDpZQTtmQ7IDsiLuY7C7We+WwaBcddnju74ij3FPWCpgYBHGwdv5wwzRxdAgMBAAECgYBAzSreiPsujm/gDQpTeneUGz6eKgDpJOr+gnEzlyiUFwPLT+LM0hOpFZepHnxQHhB/CFu4kCJSB/kbYAa4cGSOlPo8zBLCfNajClZMLaKMAIb+0TmYNAnVcadC/4fXibzAW0zRS2/OK4H7wWUVEYyC66m+ieBaH5Jt/72+e6aYTQJBAPjjhGanLk22ml8i5+MzN94RBQStbGNxI6xtBXoKEIB2W/INPddZ877e7tknh+fVvctTZlE4Q5V1TT2ZL4wzke8CQQDVfaE9Cbc+aeg3Mb+Ap64tCK4WTHhWzHySN7VGTLdeF41ZjqTrIS7SSQyZOPOt/lMfFgXO0EnSdCqL+aexXFJzAkBeHyxi5bZNDVEzyS+IbEYkZKtRKYRj1tV2z4PSsxuqeRgsYXWRiyLye7w3wwtSUTKFQfTfojdsvf+H2/ZvPtFhAkAMygfctjZKAOIuXEaSmHjwrbJwF4il+n4D7F5ppbLeah7HnKn4g/ZgFowwqZ6/b5rfI9yZNRUXDGp4FC6di2BNAkB572zRbBT5Ot9mx9xVg6g/t0s3+LLEs1LBFEWQatRR9oC6qUzGNKTnZ/d5254ngnYXSRaQEZT698cJQV7kvmg4
     */

    public static void main(String[] args) {
//        String property = System.getProperty("user.dir");
//        String pfxpath = property + File.separator + "8000013189_pri.pfx";
//
//        File pfxfile = new File(pfxpath);
//        if (!pfxfile.exists()) {
//            System.out.println("私钥文件不存在！");
//            throw new RuntimeException("私钥文件不存在！");
//        }
//        System.out.printf(HelipayUtil.getOrderId());
//        testPayment();
        testPayQuery();
//        testBingMsg();
//        testBindCommit();
//        repayment();
//        queryOrder();
//        unbind();
    }

    public static void testPayment() {
//        HeliPayBiz biz = new HeliPayBiz();
//        PaymentReqVo reqVo = new PaymentReqVo();
//        reqVo.setBankCardNo("6225880158386129");
//        reqVo.setAmount(0.01);
//        reqVo.setRemark("支付");
//        reqVo.setBankCardName("王金生");
//        PaymentResponseVo resultVo = biz.payment(reqVo);
//        logger.info("success");
////        System.out.println(HelipayUtil.checkPaymentResultSign(resultVo));
//        System.out.println(JSON.toJSON(resultVo));
        HelipayHelper helipayHelper = new HelipayHelper();
        PayForReqVo reqVo = new PayForReqVo();
        reqVo.setOrderId(HelipayUtil.getOrderId());
        reqVo.setAmount("0.01");
        reqVo.setBankCode("CMBCHINA");
        reqVo.setBankAccountName("王金生");
        reqVo.setBankAccountNo("6225880158386129");
        reqVo.setBiz(HelipayConstant.BIZ_TYPE_B2C);
        reqVo.setFeeType(HelipayConstant.PAYTYPE_PAYER);
        reqVo.setUrgency(HelipayConstant.PAY_URGENCY);
        reqVo.setSummary("remark");
        reqVo.setNotifyUrl("https://www.baidu.com/");

        HeliPayForPaymentResultVo resultVo = helipayHelper.payment(reqVo);
        logger.info("success");
        System.out.println(HelipayUtil.checkPaymentResultSign(resultVo));
        System.out.println(JSON.toJSON(resultVo));
    }

    /**
     * {"rt2_retCode":"0000","sign":"a40c19b8bc15cc974779dcd75ef181df","rt1_bizType":"Transfer","rt5_orderId":"xjhlb1901240018634301","rt4_customerNumber":"C1800000002","rt3_retMsg":"接收成功","rt6_serialNumber":"156768406"}
     */

    public static void testPayQuery() {
        HelipayHelper helipayHelper = new HelipayHelper();
        PayForReqVo reqVo = new PayForReqVo();
        reqVo.setOrderId("xjhlb1901240018634301");
        HeliPayForPaymentQueryResponseVo resultVo = helipayHelper.queryPayment(reqVo);
        System.out.println(HelipayUtil.checkQueryPaymentResultSign(resultVo));
        System.out.println(JSON.toJSON(resultVo));
    }

    public static void testBingMsg() {
        AgreementBindCardValidateCodeVo reqVo = new AgreementBindCardValidateCodeVo();
        HelipayHelper helipayHelper = new HelipayHelper();
        setPath();
//        beanReq.setUserId("8888888");
//        beanReq.setTradeDate(DateUtil.dateStr7(new Date()));
//        beanReq.setMchntSsn(OrderNoUtil.getSerialNumber());
//        beanReq.setAccount("孙悟空");
//        beanReq.setCardNo("6225885865354170");
//        beanReq.setIdType("0");
//        beanReq.setIdCard("420116199001011234");
//        beanReq.setMobileNo("15388888888");

        reqVo.setP3_userId("wjs8888888666");
        reqVo.setP5_timestamp(HelipayUtil.getTimeStamp());
        reqVo.setP6_cardNo("6225880158386129");
        reqVo.setP7_phone("15801640229");
        reqVo.setP8_idCardNo("360732198805300914");
        reqVo.setP9_idCardType(HelipayUtil.IDCARD);
        reqVo.setP10_payerName("王金生");
        reqVo.setP4_orderId(HelipayUtil.getOrderId());
        reqVo.setSignatureType(HelipayConstant.SIGNATURE_TYPE);
        AgreementSendValidateCodeResponseVo vo = helipayHelper.bindMsg(reqVo);
        try {
            if (MessageHandle.checkSign(vo)) {
                System.out.println(JSON.toJSON(vo));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void testBindCommit() {
        BindCardVo reqVo = new BindCardVo();
        HelipayHelper helipayHelper = new HelipayHelper();
        setPath();
        reqVo.setP3_userId("wjs8888888666");
        reqVo.setP4_orderId("xjhlb1901260016667992");
        reqVo.setP5_timestamp(HelipayUtil.getTimeStamp());
        reqVo.setP6_payerName("王金生");
        reqVo.setP7_idCardType(HelipayUtil.IDCARD);
        reqVo.setP8_idCardNo("360732198805300914");
        reqVo.setP9_cardNo("6225880158386129");
        reqVo.setP13_phone("15801640229");
        reqVo.setP14_validateCode("");
        reqVo.setP15_isEncrypt("TRUE");
        reqVo.setSignatureType(HelipayConstant.SIGNATURE_TYPE);
        BindCardResponseVo vo = helipayHelper.bindCommit(reqVo);
        try {
            if (MessageHandle.checkSign(vo)) {
                System.out.println(JSON.toJSON(vo));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void repayment() {
        BindCardPayVo reqVo = new BindCardPayVo();
        HelipayHelper helipayHelper = new HelipayHelper();
        setPath();
        reqVo.setP3_bindId("xyz");
        reqVo.setP4_userId("wjs8888888666");
        reqVo.setP5_orderId(HelipayUtil.getOrderId());
        reqVo.setP6_timestamp(HelipayUtil.getTimeStamp());
        reqVo.setP7_currency(HelipayConstant.CURRENCY_CNY);
        reqVo.setP8_orderAmount("0.12");
        reqVo.setP9_goodsName("还款支付");
        reqVo.setP11_terminalType("OTHER");
        reqVo.setP12_terminalId("aa");
        reqVo.setP13_orderIp("127.0.0.1");
        reqVo.setP16_serverCallbackUrl("http://localhost:8011/dev/index.jsp");
        reqVo.setSignatureType(HelipayConstant.SIGNATURE_TYPE);
        BindCardPayResponseVo vo = helipayHelper.repayment(reqVo);
        try {
            if (StringUtil.equals(vo.getRt2_retCode(), PayConstant.REQ_ERROR_CODE_10)){
                System.out.println("出现异常 ==> "+JSON.toJSON(vo));
            } else if (MessageHandle.checkSign(vo)) {
                System.out.println(JSON.toJSON(vo));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void queryOrder() {
        QueryOrderVo reqVo = new QueryOrderVo();
        HelipayHelper helipayHelper = new HelipayHelper();
        setPath();
        reqVo.setP2_orderId("orderId");
        reqVo.setSignatureType(HelipayConstant.SIGNATURE_TYPE);
        QueryOrderResponseVo vo = helipayHelper.queryOrder(reqVo);
        try {
            if (StringUtil.equals(vo.getRt2_retCode(), PayConstant.REQ_ERROR_CODE_10)){
                System.out.println("出现异常 ==> "+JSON.toJSON(vo));
            } else if (MessageHandle.checkSign(vo)) {
                System.out.println(JSON.toJSON(vo));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unbind() {
        UnBindCardVo reqVo = new UnBindCardVo();
        HelipayHelper helipayHelper = new HelipayHelper();
        setPath();
        reqVo.setP3_userId("wjs8888888666");
        reqVo.setP4_bindId("bindid");
        reqVo.setP5_orderId(HelipayUtil.getOrderId());
        reqVo.setP6_timestamp(HelipayUtil.getTimeStamp());
        reqVo.setSignatureType(HelipayConstant.SIGNATURE_TYPE);
        UnBindCardResponseVo vo = helipayHelper.unbind(reqVo);
        try {
            if (StringUtil.equals(vo.getRt2_retCode(), PayConstant.REQ_ERROR_CODE_10)){
                System.out.println("出现异常 ==> "+JSON.toJSON(vo));
            } else if (MessageHandle.checkSign(vo)) {
                System.out.println(JSON.toJSON(vo));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setPath() {
        String property = System.getProperty("user.dir");
        String pfxpath = property + File.separator + "sign" + File.separator + "C1800000002.pfx";
        String cerPath = property + File.separator + "sign" + File.separator + "helipay.cer";
//        MessageHandle.setCertPath(cerPath);
        MessageHandle.setPfxPath(pfxpath);
        MessageHandle.setPfxPwd("qwer1234");
    }
}
