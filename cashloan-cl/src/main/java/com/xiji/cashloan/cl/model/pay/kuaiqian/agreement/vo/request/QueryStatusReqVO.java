package com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo.request;

public class QueryStatusReqVO {

    /**
     * 接口版本 号,
     */
    private String version;
    /**
     * 外部跟踪编号
     */
    private String externalRefNumber;
    /**
     * 系统参考 号
     */
    private String refNumber;

    /**
     * 交易类型
     */
    private String txnType;
    /**
     * 商户号
     */
    private String merchantId;
    /**
     * 终端号
     */
    private String terminalId;
    /**
     * 交易状态
     */
    private String txnStatus;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getExternalRefNumber() {
        return externalRefNumber;
    }

    public void setExternalRefNumber(String externalRefNumber) {
        this.externalRefNumber = externalRefNumber;
    }

    public String getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
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

    public String getTxnStatus() {
        return txnStatus;
    }

    public void setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
    }
}
