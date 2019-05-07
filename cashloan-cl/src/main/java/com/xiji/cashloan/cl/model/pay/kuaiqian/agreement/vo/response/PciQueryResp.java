package com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo.response;

/**
 * @auther : wnb
 * @date : 2019/5/7
 * @describe :绑卡信息查询响应参数
 */
public class PciQueryResp {

    /**
     * 接口版本号
     */
    private String version;

    /**
     * 商户号
     */
    private String merchantId;

    /**
     * 终端号
     */
    private String terminalId;

    /**
     * 客户号
     */
    private String customerId;

    /**
     * 卡类型
     */
    private String cardType;

    /**
     * 接入方式
     */
    private String bindType;

    /**
     * 缩略卡号
     */
    private String storablePan;

    /**
     * 支付标记
     */
    private String payToken;

    /**
     * 银行代码
     */
    private String bankId;

    /**
     * 错误代码
     */
    private String errorCode;

    /**
     * 错误消息
     */
    private String errorMessage;

    /**
     * 应答码
     */
    private String responseCode;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getBindType() {
        return bindType;
    }

    public void setBindType(String bindType) {
        this.bindType = bindType;
    }

    public String getStorablePan() {
        return storablePan;
    }

    public void setStorablePan(String storablePan) {
        this.storablePan = storablePan;
    }

    public String getPayToken() {
        return payToken;
    }

    public void setPayToken(String payToken) {
        this.payToken = payToken;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
}
