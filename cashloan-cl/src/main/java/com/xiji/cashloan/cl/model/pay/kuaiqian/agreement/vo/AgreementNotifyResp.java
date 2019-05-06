package com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo;

/**
 * @auther : wnb
 * @date : 2019/5/6
 * @describe : 快钱协议支付 回调参数
 */
public class AgreementNotifyResp {

    /**
     * 版本号
     */
    private String version;

    /**
     * 交易类型编码
     */
    private String txnType;

    /**
     * 消息状态
     */
    private String interactiveStatus;

    /**
     * 金额
     */
    private String amount;

    /**
     * 商户编号
     */
    private String merchantId;

    /**
     * 商户编号
     */
    private String settleMerchantId;

    /**
     * 终端编号
     */
    private String terminalId;

    /**
     * 外部检索参考号
     */
    private String externalRefNumber;

    /**
     * 客户号
     */
    private String customerId;

    /**
     * 检索参考号
     */
    private String refNumber;

    /**
     * 应答码
     */
    private String responseCode;

    /**
     * 应答文本信息
     */
    private String responseTextMessage;

    /**
     * 交易传输时间
     */
    private String transTime;

    /**
     * 客户端交易时间
     */
    private String entryTime;

    /**
     * 发卡组织编号
     */
    private String cardOrg;

    /**
     * 发卡银行名称
     */
    private String issuer;

    /**
     * 缩略卡号
     */
    private String storableCardNo;

    /**
     * 授权码
     */
    private String authorizationCode;

    /**
     * 报文数字签名
     */
    private String signature;

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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getSettleMerchantId() {
        return settleMerchantId;
    }

    public void setSettleMerchantId(String settleMerchantId) {
        this.settleMerchantId = settleMerchantId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getExternalRefNumber() {
        return externalRefNumber;
    }

    public void setExternalRefNumber(String externalRefNumber) {
        this.externalRefNumber = externalRefNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseTextMessage() {
        return responseTextMessage;
    }

    public void setResponseTextMessage(String responseTextMessage) {
        this.responseTextMessage = responseTextMessage;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getCardOrg() {
        return cardOrg;
    }

    public void setCardOrg(String cardOrg) {
        this.cardOrg = cardOrg;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getStorableCardNo() {
        return storableCardNo;
    }

    public void setStorableCardNo(String storableCardNo) {
        this.storableCardNo = storableCardNo;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
