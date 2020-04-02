package com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo.request;

public class PayForReqVo {

    /**
     * 接口版本 号,
     */
    private String version;

    /**
     * 交易类型
     */
    private String txnType;
    /**
     * 消息状态
     */
    private String interactiveStatus;
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
     * 特殊交易 标志
     */
    private String spFlag;
    /**
     * 外部跟踪编号
     */
    private String externalRefNumber;
    /**
     * 商户端交 易时间
     */
    private String entryTime;
    /**
     * 金额
     */
    private Double amount;
    /**
     * 支付协议 号
     */
    private String payToken;
    /**
     * 结算商户 号
     */
    private String settleMerchantId;
    /**
     * 异步通知 地址
     */
    private String tr3Url;
    /**
     * 银行简称
     */
    private String bankId;
    /**
     * 扩展字段 1
     */
    private String ext1;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public String getInteractiveStatus() {
        return interactiveStatus;
    }

    public void setInteractiveStatus(String interactiveStatus) {
        this.interactiveStatus = interactiveStatus;
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

    public String getSpFlag() {
        return spFlag;
    }

    public void setSpFlag(String spFlag) {
        this.spFlag = spFlag;
    }

    public String getExternalRefNumber() {
        return externalRefNumber;
    }

    public void setExternalRefNumber(String externalRefNumber) {
        this.externalRefNumber = externalRefNumber;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPayToken() {
        return payToken;
    }

    public void setPayToken(String payToken) {
        this.payToken = payToken;
    }

    public String getSettleMerchantId() {
        return settleMerchantId;
    }

    public void setSettleMerchantId(String settleMerchantId) {
        this.settleMerchantId = settleMerchantId;
    }

    public String getTr3Url() {
        return tr3Url;
    }

    public void setTr3Url(String tr3Url) {
        this.tr3Url = tr3Url;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }
}
