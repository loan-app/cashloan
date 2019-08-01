package com.xiji.cashloan.cl.model.pay.helipay.vo.delegation;

/**
 * Created by heli50 on 2017/4/14.
 */
public class OrderContractSignatureQueryResVo {
    private String rt1_bizType;
    private String rt2_retCode;
    private String rt3_retMsg;
    private String rt4_customerNumber;
    private String rt5_orderId;
    private String rt6_userId;
    private String rt7_contractUrl;
    private String rt8_timestamp;
    private String sign;

    public String getRt1_bizType() {
        return rt1_bizType;
    }

    public void setRt1_bizType(String rt1_bizType) {
        this.rt1_bizType = rt1_bizType;
    }

    public String getRt2_retCode() {
        return rt2_retCode;
    }

    public void setRt2_retCode(String rt2_retCode) {
        this.rt2_retCode = rt2_retCode;
    }

    public String getRt3_retMsg() {
        return rt3_retMsg;
    }

    public void setRt3_retMsg(String rt3_retMsg) {
        this.rt3_retMsg = rt3_retMsg;
    }

    public String getRt4_customerNumber() {
        return rt4_customerNumber;
    }

    public void setRt4_customerNumber(String rt4_customerNumber) {
        this.rt4_customerNumber = rt4_customerNumber;
    }

    public String getRt5_orderId() {
        return rt5_orderId;
    }

    public void setRt5_orderId(String rt5_orderId) {
        this.rt5_orderId = rt5_orderId;
    }

    public String getRt6_userId() {
        return rt6_userId;
    }

    public void setRt6_userId(String rt6_userId) {
        this.rt6_userId = rt6_userId;
    }

    public String getRt7_contractUrl() {
        return rt7_contractUrl;
    }

    public void setRt7_contractUrl(String rt7_contractUrl) {
        this.rt7_contractUrl = rt7_contractUrl;
    }

    public String getRt8_timestamp() {
        return rt8_timestamp;
    }

    public void setRt8_timestamp(String rt8_timestamp) {
        this.rt8_timestamp = rt8_timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
