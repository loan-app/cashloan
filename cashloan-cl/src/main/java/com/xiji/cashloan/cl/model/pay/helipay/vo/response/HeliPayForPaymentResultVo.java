package com.xiji.cashloan.cl.model.pay.helipay.vo.response;

/**
 * @Auther: king
 * @Date: 2019/1/23 20:11
 * @Description:
 */
public class HeliPayForPaymentResultVo {
    private String rt1_bizType;
    private String rt2_retCode;
    private String rt3_retMsg;
    private String rt4_customerNumber;
    private String rt5_orderId;
    /**
     * 平台流水号
     */
    private String rt6_serialNumber;
    private String rt7_orderStatus;
    private String rt8_notifyType;
    private String rt9_reason;
    private String rt10_createDate;
    private String rt11_completeDate;
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

    public String getRt6_serialNumber() {
        return rt6_serialNumber;
    }

    public void setRt6_serialNumber(String rt6_serialNumber) {
        this.rt6_serialNumber = rt6_serialNumber;
    }

    public String getRt7_orderStatus() {
        return rt7_orderStatus;
    }

    public void setRt7_orderStatus(String rt7_orderStatus) {
        this.rt7_orderStatus = rt7_orderStatus;
    }

    public String getRt8_notifyType() {
        return rt8_notifyType;
    }

    public void setRt8_notifyType(String rt8_notifyType) {
        this.rt8_notifyType = rt8_notifyType;
    }

    public String getRt9_reason() {
        return rt9_reason;
    }

    public void setRt9_reason(String rt9_reason) {
        this.rt9_reason = rt9_reason;
    }

    public String getRt10_createDate() {
        return rt10_createDate;
    }

    public void setRt10_createDate(String rt10_createDate) {
        this.rt10_createDate = rt10_createDate;
    }

    public String getRt11_completeDate() {
        return rt11_completeDate;
    }

    public void setRt11_completeDate(String rt11_completeDate) {
        this.rt11_completeDate = rt11_completeDate;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
