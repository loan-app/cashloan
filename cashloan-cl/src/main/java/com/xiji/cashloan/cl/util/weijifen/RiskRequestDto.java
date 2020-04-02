package com.xiji.cashloan.cl.util.weijifen;

public class RiskRequestDto {
    private String appId;
    private String userName;
    private String applyId;
    private String idcard;
    private String mobile;
    private String applyTime;
    private long timestamp;

    // 不参与验参
    private String data;
    private String callback;
    private String sign;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public RiskRequestDto() {
    }

    public RiskRequestDto(String appId, String userName, String applyId, String idcard, String mobile, String applyTime, long timestamp, String data, String callback, String sign) {

        this.appId = appId;
        this.userName = userName;
        this.applyId = applyId;
        this.idcard = idcard;
        this.mobile = mobile;
        this.applyTime = applyTime;
        this.timestamp = timestamp;
        this.data = data;
        this.callback = callback;
        this.sign = sign;
    }
}
