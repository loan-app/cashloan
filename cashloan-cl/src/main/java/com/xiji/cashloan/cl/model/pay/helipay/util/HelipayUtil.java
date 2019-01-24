package com.xiji.cashloan.cl.model.pay.helipay.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.HeliPayForPaymentResultVo;
import com.xiji.cashloan.core.common.util.OrderNoUtil;
import tool.util.StringUtil;

/**
 * @Auther: king
 * @Date: 2019/1/24 17:19
 * @Description:
 */
public class HelipayUtil {
    public static final String split="&";
    public static String transferUrl() {
//        return Global.getValue("helibao_transfer_url");
        return "http://test.trx.helipay.com/trx/transfer/interface.action";
    }
    public static String customerNumber() {
//        return Global.getValue("helibao_customer_no");
        return "C1800000002";
    }
    public static String transferKey() {
//        return Global.getValue("helibao_transfer_sign_key");
        return "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAM+PcQN9JmrYJ+sKwI9kmBLAsBS6HHDT2Sgkh8BYbyTKovSMQat1II6l42HhUH6lr7bXwnRUw8I4qrNBZjz4cWZBi+vgkSL/1f1M/erW27t61DobvpgDpZQTtmQ7IDsiLuY7C7We+WwaBcddnju74ij3FPWCpgYBHGwdv5wwzRxdAgMBAAECgYBAzSreiPsujm/gDQpTeneUGz6eKgDpJOr+gnEzlyiUFwPLT+LM0hOpFZepHnxQHhB/CFu4kCJSB/kbYAa4cGSOlPo8zBLCfNajClZMLaKMAIb+0TmYNAnVcadC/4fXibzAW0zRS2/OK4H7wWUVEYyC66m+ieBaH5Jt/72+e6aYTQJBAPjjhGanLk22ml8i5+MzN94RBQStbGNxI6xtBXoKEIB2W/INPddZ877e7tknh+fVvctTZlE4Q5V1TT2ZL4wzke8CQQDVfaE9Cbc+aeg3Mb+Ap64tCK4WTHhWzHySN7VGTLdeF41ZjqTrIS7SSQyZOPOt/lMfFgXO0EnSdCqL+aexXFJzAkBeHyxi5bZNDVEzyS+IbEYkZKtRKYRj1tV2z4PSsxuqeRgsYXWRiyLye7w3wwtSUTKFQfTfojdsvf+H2/ZvPtFhAkAMygfctjZKAOIuXEaSmHjwrbJwF4il+n4D7F5ppbLeah7HnKn4g/ZgFowwqZ6/b5rfI9yZNRUXDGp4FC6di2BNAkB572zRbBT5Ot9mx9xVg6g/t0s3+LLEs1LBFEWQatRR9oC6qUzGNKTnZ/d5254ngnYXSRaQEZT698cJQV7kvmg4";
    }
    public static boolean switchHelipay() {
//        String paySwitch = Global.getValue("helibao_switch");
        String paySwitch = "1";
        return StringUtil.isNotBlank(paySwitch) && "1".equals(paySwitch);
    }

    public static HeliPayForPaymentResultVo convertPaymentResultVo(String source) {
       return JSON.parseObject(source, new TypeReference<HeliPayForPaymentResultVo>(){});
    }

    public static String getOrderId() {
        return "xjhlb" + OrderNoUtil.getSerialNumber();
    }

}
