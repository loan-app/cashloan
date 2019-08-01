package com.xiji.cashloan.cl.model.pay.helipay.vo.delegation;

/**
 * @auther : wnb
 * @date : 2019/7/31
 * @describe : 合利宝用户注册回调参数
 */
public class UserRegisterNotifyVo {

    /**
     * 交易类型
     */
    private String rt1_bizType;

    /**
     * 返回码
     */
    private String rt2_retCode;

    /**
     * 返回信息
     */
    private String rt3_retMsg;

    /**
     * 商户编号
     */
    private String rt4_customerNumber;

    /**
     * 用户编号
     */
    private String rt5_userId;

    /**
     * 用户状态
     */
    private String rt6_userStatus;

    /**
     * 返回信息
     */
    private String rt7_desc;

    /**
     * 签名
     */
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

    public String getRt5_userId() {
        return rt5_userId;
    }

    public void setRt5_userId(String rt5_userId) {
        this.rt5_userId = rt5_userId;
    }

    public String getRt6_userStatus() {
        return rt6_userStatus;
    }

    public void setRt6_userStatus(String rt6_userStatus) {
        this.rt6_userStatus = rt6_userStatus;
    }

    public String getRt7_desc() {
        return rt7_desc;
    }

    public void setRt7_desc(String rt7_desc) {
        this.rt7_desc = rt7_desc;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
