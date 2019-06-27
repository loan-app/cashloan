package com.xiji.cashloan.cl.model.pay.chanpay.agreement.vo.response;

public class QueryPaymentRespVo {
    /**
     * 企业账号
     */
    private String corpAcctNo;
    /**
     * 企业名称
     */
    private String corpAcctName;
    /**
     * 收款方银行卡或存折号码
     */
    private String acctNo;
    /**
     * 收款人姓名
     */
    private String acctName;
    /**
     * 交易金额
     */
    private String transAmt;
    /**
     * 原交易返回代码
     */
    private String originalRetCode;
    /**
     * 手续费
     */
    private String fee;
    /**
     * 备注
     */
    private String summary;
    /**
     * 用途
     */
    private String postScript;
    /**
     * 原交易错误信息描述
     */
    private String originalErrorMessage;
    /**
     * 应用返回码
     */
    private String appRetcode;
    /**
     * 应用返回信息
     */
    private String appRetMsg;

    public String getCorpAcctNo() {
        return corpAcctNo;
    }

    public void setCorpAcctNo(String corpAcctNo) {
        this.corpAcctNo = corpAcctNo;
    }

    public String getCorpAcctName() {
        return corpAcctName;
    }

    public void setCorpAcctName(String corpAcctName) {
        this.corpAcctName = corpAcctName;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public String getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }

    public String getOriginalRetCode() {
        return originalRetCode;
    }

    public void setOriginalRetCode(String originalRetCode) {
        this.originalRetCode = originalRetCode;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPostScript() {
        return postScript;
    }

    public void setPostScript(String postScript) {
        this.postScript = postScript;
    }

    public String getOriginalErrorMessage() {
        return originalErrorMessage;
    }

    public void setOriginalErrorMessage(String originalErrorMessage) {
        this.originalErrorMessage = originalErrorMessage;
    }

    public String getAppRetcode() {
        return appRetcode;
    }

    public void setAppRetcode(String appRetcode) {
        this.appRetcode = appRetcode;
    }

    public String getAppRetMsg() {
        return appRetMsg;
    }

    public void setAppRetMsg(String appRetMsg) {
        this.appRetMsg = appRetMsg;
    }
}
