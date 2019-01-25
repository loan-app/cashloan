package com.xiji.cashloan.cl.model.pay.helipay.vo.response;

import com.xiji.cashloan.cl.model.pay.helipay.annotation.SignExclude;

/**
 * @Auther: king
 * @Date: 2019/1/25 18:45
 * @Description:
 */
public class UnBindCardResponseVo {
    private String rt1_bizType;

    private String rt2_retCode;

    @SignExclude
    private String rt3_retMsg;

    private String rt4_customerNumber;

    @SignExclude
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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
