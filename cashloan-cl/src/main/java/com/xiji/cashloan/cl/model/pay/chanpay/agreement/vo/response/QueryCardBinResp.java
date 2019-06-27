package com.xiji.cashloan.cl.model.pay.chanpay.agreement.vo.response;

public class QueryCardBinResp {

    /**
     * 原交易返回代码
     */
    private String originalRetCode;

    /**
     * 商户号
     */
    private String corpNo;

    /**
     * 商户名称
     */
    private String corpName;
    /**
     * 卡号是否有效
     */
    private String isValid;
    /**
     * 卡BIN
     */
    private String cardBin;
    /**
     * 卡名称
     */
    private String cardName;
    /**
     * 卡类型
     */
    private String cardType;
    /**
     * 开户行行号
     */
    private String branchBankCode;
    /**
     * 对手行行名
     */
    private String branchBankName;
    /**
     * 通用银行名称
     */
    private String bankCommonName;
    /**
     * 对手人账号
     */
    private String acctNo;
    /**
     * 原交易错误信息描述
     */
    private String originalErrorMessage;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;

    public String getOriginalRetCode() {
        return originalRetCode;
    }

    public void setOriginalRetCode(String originalRetCode) {
        this.originalRetCode = originalRetCode;
    }

    public String getCorpNo() {
        return corpNo;
    }

    public void setCorpNo(String corpNo) {
        this.corpNo = corpNo;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getCardBin() {
        return cardBin;
    }

    public void setCardBin(String cardBin) {
        this.cardBin = cardBin;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getBranchBankCode() {
        return branchBankCode;
    }

    public void setBranchBankCode(String branchBankCode) {
        this.branchBankCode = branchBankCode;
    }

    public String getBranchBankName() {
        return branchBankName;
    }

    public void setBranchBankName(String branchBankName) {
        this.branchBankName = branchBankName;
    }

    public String getBankCommonName() {
        return bankCommonName;
    }

    public void setBankCommonName(String bankCommonName) {
        this.bankCommonName = bankCommonName;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public String getOriginalErrorMessage() {
        return originalErrorMessage;
    }

    public void setOriginalErrorMessage(String originalErrorMessage) {
        this.originalErrorMessage = originalErrorMessage;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
