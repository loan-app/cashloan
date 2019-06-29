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

    //公共字段
    private String partnerId; //商户号
    private String inputCharset; //参数编码字符集
    private String acceptStatus; //网关返回码(S：受理成功F：受理失败 表示接口调用是否成功，并不表明业务处理结果)
    private String tradeDate; //请求的日期
    private String tradeTime; //请求的时间
    private String sign; //签名
    private String signType; //签名方式
    private String memo; //备注

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

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getInputCharset() {
        return inputCharset;
    }

    public void setInputCharset(String inputCharset) {
        this.inputCharset = inputCharset;
    }

    public String getAcceptStatus() {
        return acceptStatus;
    }

    public void setAcceptStatus(String acceptStatus) {
        this.acceptStatus = acceptStatus;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "QueryPaymentRespVo{" +
                "corpAcctNo='" + corpAcctNo + '\'' +
                ", corpAcctName='" + corpAcctName + '\'' +
                ", acctNo='" + acctNo + '\'' +
                ", acctName='" + acctName + '\'' +
                ", transAmt='" + transAmt + '\'' +
                ", originalRetCode='" + originalRetCode + '\'' +
                ", fee='" + fee + '\'' +
                ", summary='" + summary + '\'' +
                ", postScript='" + postScript + '\'' +
                ", originalErrorMessage='" + originalErrorMessage + '\'' +
                ", appRetcode='" + appRetcode + '\'' +
                ", appRetMsg='" + appRetMsg + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", inputCharset='" + inputCharset + '\'' +
                ", acceptStatus='" + acceptStatus + '\'' +
                ", tradeDate='" + tradeDate + '\'' +
                ", tradeTime='" + tradeTime + '\'' +
                ", sign='" + sign + '\'' +
                ", signType='" + signType + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}
