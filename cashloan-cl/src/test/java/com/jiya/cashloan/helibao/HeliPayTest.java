package com.jiya.cashloan.helibao;

import com.alibaba.fastjson.JSON;
import com.xiji.cashloan.cl.model.pay.helipay.HelipayHelper;
import com.xiji.cashloan.cl.model.pay.helipay.constant.HelipayConstant;
import com.xiji.cashloan.cl.model.pay.helipay.util.HelipayUtil;
import com.xiji.cashloan.cl.model.pay.helipay.vo.request.PayForReqVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.HeliPayForPaymentResultVo;
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
    }

    public static void testPayment() {
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
        System.out.println(JSON.toJSON(resultVo));
    }

    /**
     * {"rt2_retCode":"0000","sign":"a40c19b8bc15cc974779dcd75ef181df","rt1_bizType":"Transfer","rt5_orderId":"xjhlb1901240018634301","rt4_customerNumber":"C1800000002","rt3_retMsg":"接收成功","rt6_serialNumber":"156768406"}
     */

    public static void testPayQuery() {
        HelipayHelper helipayHelper = new HelipayHelper();
        PayForReqVo reqVo = new PayForReqVo();
        reqVo.setOrderId("xjhlb1901240018634301");
        HeliPayForPaymentResultVo resultVo = helipayHelper.queryPayment(reqVo);
        System.out.println(JSON.toJSON(resultVo));
    }
}
