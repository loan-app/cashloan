package com.xiji.cashloan.cl.model.pay.chanpay.agreement.vo;

public class ChanPaymentNotifyModel {
    private String notifyId;    //通知ID
    private String notifyType;//通知类型
    private String notifyTime;//通知时间
    private String inputCharset;//字符集
    private String version;//版本号
    private String outerTradeNo;//商户网站唯一订单号outer_trade_no
    private String innerTradeNo;//支付平台支付订单号
    private String withdrawalAmount;//提现金额
    private String withdrawalStatus;//提现状态
    private String uid;//用户id
    private String returnCode;//返回码
    private String failReason;//失败原因
    private String gmtWithdrawal;//提现时间


    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(String notifyTime) {
        this.notifyTime = notifyTime;
    }

    public String getInputCharset() {
        return inputCharset;
    }

    public void setInputCharset(String inputCharset) {
        this.inputCharset = inputCharset;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOuterTradeNo() {
        return outerTradeNo;
    }

    public void setOuterTradeNo(String outerTradeNo) {
        this.outerTradeNo = outerTradeNo;
    }

    public String getInnerTradeNo() {
        return innerTradeNo;
    }

    public void setInnerTradeNo(String innerTradeNo) {
        this.innerTradeNo = innerTradeNo;
    }

    public String getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(String withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public String getWithdrawalStatus() {
        return withdrawalStatus;
    }

    public void setWithdrawalStatus(String withdrawalStatus) {
        this.withdrawalStatus = withdrawalStatus;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getGmtWithdrawal() {
        return gmtWithdrawal;
    }

    public void setGmtWithdrawal(String gmtWithdrawal) {
        this.gmtWithdrawal = gmtWithdrawal;
    }
}
