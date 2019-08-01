package com.xiji.cashloan.cl.model.pay.helipay.vo.delegation;

/**
 * @auther : wnb
 * @date : 2019/7/31
 * @describe : 合利宝借款信息
 */
public class HelipayLoanConInfo {

    /**
     * 借款时间
     */
    private String loanTime;

    /**
     * 借款时间单位
     */
    private String loanTimeUnit;

    /**
     * 借款年利率
     */
    private String loanInterestRate;

    /**
     * 分期数
     */
    private String periodization;

    /**
     * 每期天/月/年数
     */
    private String periodizationDays;

    /**
     * 每期金额
     */
    private String periodizationFee;

    /**
     * 商品标题
     */
    private String subject;

    /**
     * 商品描述
     */
    private String body;

    /**
     * 借款服务费费率
     */
    private String charge;

    /**
     * 罚息年利率
     */
    private String penaltyInterestrate;

    /**
     * 借款结束日期
     */
    private String loanEndDate;

    /**
     * 用户电子签名图片 url
     */
    private String userSignature;

    /**
     * 合同模板编号
     */
    private String contractTemplateNo;

    /**
     *滞纳金年利率
     */
    private String lateFeeInterestrate;

    /**
     * 提前还款通知日
     */
    private String prepaymentNotifyDay;

    /**
     * 提前还款违约金
     */
    private String prepaymentPenalty;

    /**
     * 提前到期逾期天数
     */
    private String preExpirationOverdueDays;

    /**
     * 提前到期逾期期数
     */
    private String preExpirationOverdueNo;

    public String getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(String loanTime) {
        this.loanTime = loanTime;
    }

    public String getLoanTimeUnit() {
        return loanTimeUnit;
    }

    public void setLoanTimeUnit(String loanTimeUnit) {
        this.loanTimeUnit = loanTimeUnit;
    }

    public String getLoanInterestRate() {
        return loanInterestRate;
    }

    public void setLoanInterestRate(String loanInterestRate) {
        this.loanInterestRate = loanInterestRate;
    }

    public String getPeriodization() {
        return periodization;
    }

    public void setPeriodization(String periodization) {
        this.periodization = periodization;
    }

    public String getPeriodizationDays() {
        return periodizationDays;
    }

    public void setPeriodizationDays(String periodizationDays) {
        this.periodizationDays = periodizationDays;
    }

    public String getPeriodizationFee() {
        return periodizationFee;
    }

    public void setPeriodizationFee(String periodizationFee) {
        this.periodizationFee = periodizationFee;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getPenaltyInterestrate() {
        return penaltyInterestrate;
    }

    public void setPenaltyInterestrate(String penaltyInterestrate) {
        this.penaltyInterestrate = penaltyInterestrate;
    }

    public String getLoanEndDate() {
        return loanEndDate;
    }

    public void setLoanEndDate(String loanEndDate) {
        this.loanEndDate = loanEndDate;
    }

    public String getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(String userSignature) {
        this.userSignature = userSignature;
    }

    public String getContractTemplateNo() {
        return contractTemplateNo;
    }

    public void setContractTemplateNo(String contractTemplateNo) {
        this.contractTemplateNo = contractTemplateNo;
    }

    public String getLateFeeInterestrate() {
        return lateFeeInterestrate;
    }

    public void setLateFeeInterestrate(String lateFeeInterestrate) {
        this.lateFeeInterestrate = lateFeeInterestrate;
    }

    public String getPrepaymentNotifyDay() {
        return prepaymentNotifyDay;
    }

    public void setPrepaymentNotifyDay(String prepaymentNotifyDay) {
        this.prepaymentNotifyDay = prepaymentNotifyDay;
    }

    public String getPrepaymentPenalty() {
        return prepaymentPenalty;
    }

    public void setPrepaymentPenalty(String prepaymentPenalty) {
        this.prepaymentPenalty = prepaymentPenalty;
    }

    public String getPreExpirationOverdueDays() {
        return preExpirationOverdueDays;
    }

    public void setPreExpirationOverdueDays(String preExpirationOverdueDays) {
        this.preExpirationOverdueDays = preExpirationOverdueDays;
    }

    public String getPreExpirationOverdueNo() {
        return preExpirationOverdueNo;
    }

    public void setPreExpirationOverdueNo(String preExpirationOverdueNo) {
        this.preExpirationOverdueNo = preExpirationOverdueNo;
    }
}
