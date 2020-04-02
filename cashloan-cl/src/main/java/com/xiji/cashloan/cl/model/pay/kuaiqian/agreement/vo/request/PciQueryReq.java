package com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo.request;

/**
 * @auther : wnb
 * @date : 2019/5/7
 * @describe :绑卡信息查询请求参数类型
 */
public class PciQueryReq {

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
}
