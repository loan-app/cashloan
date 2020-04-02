package com.xiji.cashloan.cl.domain.statistic;

import java.util.Date;

/**
 * @auther : wnb
 * @date : 2019/5/27
 * @describe : 实时到期还款统计
 */
public class RealTimeMaturityStatistic {

    /**
     * 到期时间
     */
    private Date expireTime;

    /**
     * 到期数
     */
    private Integer expireCount;

    /**
     * 还款数
     */
    private Integer reimbursementCount;

    /**
     * 展期数
     */
    private Integer extendCount;

    /**
     * 还款总额
     */
    private Double totalRepayment;

    /**
     * 展期总额
     */
    private Double extendAmount;

    /**
     * 还款率
     */
    private Double repaymentRate;


    /**
     * 到期时间Str
     */
    private String expireTimeStr;

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getExpireCount() {
        return expireCount;
    }

    public void setExpireCount(Integer expireCount) {
        this.expireCount = expireCount;
    }

    public Integer getReimbursementCount() {
        return reimbursementCount;
    }

    public void setReimbursementCount(Integer reimbursementCount) {
        this.reimbursementCount = reimbursementCount;
    }

    public Integer getExtendCount() {
        return extendCount;
    }

    public void setExtendCount(Integer extendCount) {
        this.extendCount = extendCount;
    }

    public Double getTotalRepayment() {
        return totalRepayment;
    }

    public void setTotalRepayment(Double totalRepayment) {
        this.totalRepayment = totalRepayment;
    }

    public Double getExtendAmount() {
        return extendAmount;
    }

    public void setExtendAmount(Double extendAmount) {
        this.extendAmount = extendAmount;
    }

    public Double getRepaymentRate() {
        return repaymentRate;
    }

    public void setRepaymentRate(Double repaymentRate) {
        this.repaymentRate = repaymentRate;
    }

    public String getExpireTimeStr() {
        return expireTimeStr;
    }

    public void setExpireTimeStr(String expireTimeStr) {
        this.expireTimeStr = expireTimeStr;
    }
}
