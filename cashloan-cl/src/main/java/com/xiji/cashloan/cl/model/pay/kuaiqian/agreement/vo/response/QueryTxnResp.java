package com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo.response;

/**
 * @auther : wnb
 * @date : 2019/5/7
 * @describe : 卡bin 查询参数类
 */
public class QueryTxnResp {

    /**
     * 接口版本 号
     */
    private String version;

    /**
     * 交易类型 编码
     */
    private String txnType;

    /**
     * 卡号
     */
    private String cardNo;
    /**
     * CNP 标志位
     */
    private String validFlag;

    /**
     * 卡组织编 号
     */
    private String cardOrg;

    /**
     * 卡类型
     */
    private String cardType;

    /**
     * 发卡银行 名称
     */
    private String issuer;

    /**
     * 交易能力
     */
    private String validateType;

    /**
     * 银行简称
     */
    private String bankId;

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

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public String getCardOrg() {
        return cardOrg;
    }

    public void setCardOrg(String cardOrg) {
        this.cardOrg = cardOrg;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getValidateType() {
        return validateType;
    }

    public void setValidateType(String validateType) {
        this.validateType = validateType;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
}
