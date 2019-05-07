package com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo.request;

/**
 * @auther : wnb
 * @date : 2019/5/7
 * @describe :
 */
public class PciDelReq {
    /**
     * 接口版本号
     */
    private String version;

    /**
     * 商户号
     */
    private String merchantId;

    /**
     * 客户号
     */
    private String customerId;

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
     * 银行卡号
     */
    private String pan;

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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }
}
