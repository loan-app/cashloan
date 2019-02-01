package com.xiji.cashloan.cl.model.pay.helipay.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.HeliPayForPaymentNotifyVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.HeliPayForPaymentQueryResponseVo;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.HeliPayForPaymentResultVo;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.OrderNoUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.util.StringUtils;
import tool.util.StringUtil;

/**
 * @Auther: king
 * @Date: 2019/1/24 17:19
 * @Description:
 */
public class HelipayUtil {
    public static final String split="&";
    public static final String IDCARD="IDCARD";
    public static String transferUrl() {
        return Global.getValue("helipay_transfer_url");
//        return "http://test.trx.helipay.com/trx/transfer/interface.action";
    }
    public static String quickPayUrl() {
        return Global.getValue("helipay_quickpay_api_url");
//        return "http://test.trx.helipay.com/trx/quickPayApi/interface.action";
    }
    public static String customerNumber() {
        return Global.getValue("helipay_customer_no");
//        return "C1800000002";
    }
    public static String transferKey() {
        return Global.getValue("helipay_transfer_sign_key");
//        return "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAM+PcQN9JmrYJ+sKwI9kmBLAsBS6HHDT2Sgkh8BYbyTKovSMQat1II6l42HhUH6lr7bXwnRUw8I4qrNBZjz4cWZBi+vgkSL/1f1M/erW27t61DobvpgDpZQTtmQ7IDsiLuY7C7We+WwaBcddnju74ij3FPWCpgYBHGwdv5wwzRxdAgMBAAECgYBAzSreiPsujm/gDQpTeneUGz6eKgDpJOr+gnEzlyiUFwPLT+LM0hOpFZepHnxQHhB/CFu4kCJSB/kbYAa4cGSOlPo8zBLCfNajClZMLaKMAIb+0TmYNAnVcadC/4fXibzAW0zRS2/OK4H7wWUVEYyC66m+ieBaH5Jt/72+e6aYTQJBAPjjhGanLk22ml8i5+MzN94RBQStbGNxI6xtBXoKEIB2W/INPddZ877e7tknh+fVvctTZlE4Q5V1TT2ZL4wzke8CQQDVfaE9Cbc+aeg3Mb+Ap64tCK4WTHhWzHySN7VGTLdeF41ZjqTrIS7SSQyZOPOt/lMfFgXO0EnSdCqL+aexXFJzAkBeHyxi5bZNDVEzyS+IbEYkZKtRKYRj1tV2z4PSsxuqeRgsYXWRiyLye7w3wwtSUTKFQfTfojdsvf+H2/ZvPtFhAkAMygfctjZKAOIuXEaSmHjwrbJwF4il+n4D7F5ppbLeah7HnKn4g/ZgFowwqZ6/b5rfI9yZNRUXDGp4FC6di2BNAkB572zRbBT5Ot9mx9xVg6g/t0s3+LLEs1LBFEWQatRR9oC6qUzGNKTnZ/d5254ngnYXSRaQEZT698cJQV7kvmg4";
    }
    public static boolean switchHelipay() {
        String paySwitch = Global.getValue("helipay_switch");
//        String paySwitch = "1";
        return StringUtil.isNotBlank(paySwitch) && "1".equals(paySwitch);
    }

    public static HeliPayForPaymentResultVo convertPaymentResultVo(String source) {
       return JSON.parseObject(source, new TypeReference<HeliPayForPaymentResultVo>(){});
    }

    public static String getOrderId() {
        return "xjhlb" + OrderNoUtil.getSerialNumber();
    }

    public static String paymentNotifyAddress() {
        return Global.getValue("server_host")+ "/pay/helipay/paymentNotify.htm";
    }

    public static String rePaymentNotifyAddress() {
        return Global.getValue("server_host")+ "/pay/helipay/repaymentNotify.htm";
    }
    public static String getMD5Key() {
//        return "Vx977zHtKzoMkb3vZjxLzVvNHg1469cO";
        return Global.getValue("helipay_daifu_md5_key");
    }
    public static String getCertPathName() {
        return Global.getValue("helipay_cert_name");
    }
    public static String getPfxPathName() {
        return Global.getValue("helipay_pfx_name");
    }
    public static String getPfxPwd() {
        return Global.getValue("helipay_pfx_pwd");
    }
    public static String getTimeStamp() {
        SimpleDateFormat STRING_FORMAT_TIMESTAMP = new SimpleDateFormat("yyyyMMddHHmmss");
        return STRING_FORMAT_TIMESTAMP.format(new Date());
    }

    public static boolean isLogSign() {
//        String value = Global.getValue("helibao_log_open");
//        return StringUtil.equals("open",value);
        return true;
    }

    /**
     * 根据合利宝通知的参数生成用于签名的源串
     */
    public static boolean checkNotifySign(HeliPayForPaymentNotifyVo vo) {
        return StringUtil.equals(vo.getSign(),Disguiser.disguiseMD5(generateSignatureSource(vo)));
    }
    private static String generateSignatureSource(HeliPayForPaymentNotifyVo vo) {
        return "&" + (StringUtils.isEmpty(vo.getRt1_bizType()) ? "" : vo.getRt1_bizType()) + "&"
            + (StringUtils.isEmpty(vo.getRt2_retCode()) ? "" : vo.getRt2_retCode()) + "&"
            + (StringUtils.isEmpty(vo.getRt3_retMsg()) ? "" : vo.getRt3_retMsg()) + "&"
            + (StringUtils.isEmpty(vo.getRt4_customerNumber()) ? "" : vo.getRt4_customerNumber()) + "&"
            + (StringUtils.isEmpty(vo.getRt5_orderId()) ? "" : vo.getRt5_orderId()) + "&"
            + (StringUtils.isEmpty(vo.getRt6_serialNumber()) ? "" : vo.getRt6_serialNumber()) + "&"
            + (StringUtils.isEmpty(vo.getRt7_orderStatus()) ? "" : vo.getRt7_orderStatus()) + "&"
            + (StringUtils.isEmpty(vo.getRt8_notifyType()) ? "" : vo.getRt8_notifyType()) + "&"
            + (StringUtils.isEmpty(vo.getRt9_reason()) ? "" : vo.getRt9_reason()) + "&"
            + (StringUtils.isEmpty(vo.getRt10_createDate()) ? "" : vo.getRt10_createDate()) + "&"
            + (StringUtils.isEmpty(vo.getRt11_completeDate()) ? "" : vo.getRt11_completeDate()) + "&" + HelipayUtil.getMD5Key();
    }
    public static boolean checkPaymentResultSign(HeliPayForPaymentResultVo vo) {
        return StringUtil.equals(vo.getSign(),Disguiser.disguiseMD5(genPaymentResultSource(vo)));
    }
    private static String genPaymentResultSource(HeliPayForPaymentResultVo vo) {
        return "&" + (StringUtils.isEmpty(vo.getRt1_bizType()) ? "" : vo.getRt1_bizType()) + "&"
            + (StringUtils.isEmpty(vo.getRt2_retCode()) ? "" : vo.getRt2_retCode()) + "&"
//            + (StringUtils.isEmpty(vo.getRt3_retMsg()) ? "" : vo.getRt3_retMsg()) + "&"
            + (StringUtils.isEmpty(vo.getRt4_customerNumber()) ? "" : vo.getRt4_customerNumber()) + "&"
            + (StringUtils.isEmpty(vo.getRt5_orderId()) ? "" : vo.getRt5_orderId()) + "&"
            + (StringUtils.isEmpty(vo.getRt6_serialNumber()) ? "" : vo.getRt6_serialNumber()) + "&" + HelipayUtil.getMD5Key();
    }

    public static boolean checkQueryPaymentResultSign(HeliPayForPaymentQueryResponseVo vo) {
        return StringUtil.equals(vo.getSign(),Disguiser.disguiseMD5(genQueryPaymentResultSource(vo)));
    }
    private static String genQueryPaymentResultSource(HeliPayForPaymentQueryResponseVo vo) {
        return "&" + (StringUtils.isEmpty(vo.getRt1_bizType()) ? "" : vo.getRt1_bizType()) + "&"
            + (StringUtils.isEmpty(vo.getRt2_retCode()) ? "" : vo.getRt2_retCode()) + "&"
//            + (StringUtils.isEmpty(vo.getRt3_retMsg()) ? "" : vo.getRt3_retMsg()) + "&"
            + (StringUtils.isEmpty(vo.getRt4_customerNumber()) ? "" : vo.getRt4_customerNumber()) + "&"
            + (StringUtils.isEmpty(vo.getRt5_orderId()) ? "" : vo.getRt5_orderId()) + "&"
            + (StringUtils.isEmpty(vo.getRt6_serialNumber()) ? "" : vo.getRt6_serialNumber()) + "&"
            + (StringUtils.isEmpty(vo.getRt7_orderStatus()) ? "" : vo.getRt7_orderStatus()) + "&"
            + HelipayUtil.getMD5Key();
    }
}
