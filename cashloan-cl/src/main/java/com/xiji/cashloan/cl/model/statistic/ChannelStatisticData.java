package com.xiji.cashloan.cl.model.statistic;


/**
 * @auther : wnb
 * @date : 2019/1/28
 * @describe :渠道数据统计
 */
public class ChannelStatisticData {


    /**
     *
     * T.date,
     *                          T.channelId,
     *                          T.userRegister,
     *                          T.borrowApplyCount,
     *                          T.machineAuditPassCount,
     *                          T.machineAuditNotPassCount,
     *                          T.reviewPassCount,
     *                          T.reviewNotPassCount,
     *                          T.firstLoadCount,
     *                          T.againLoadCount,
     *                          T.overdueCount,
     *                          T.firstOverdueCount
     *
     *                          注册人数，申请订单数，机审通过数，机审通过率，
     *                          机审拒绝数、机审拒绝率，人工通过数、人工复审通过率，
     *                          人工拒绝数、人工复审拒绝率，首贷放款笔数、
     *                          复贷放款笔数、逾期笔数，首逾率，逾期率，放款率
     */

    /**
     * 日期
     */
    private String date ;

    /**
     * 渠道ID
     */
    private Integer channelId;

    /**
     * 当日注册数
     */
    private Integer userRegister;

    /**
     * 当日申请订单数
     */
    private Integer borrowApplyCount;

    /**
     * 当日机审通过数
     */
    private Integer machineAuditPassCount;

    /**
     * 当日机审拒绝数
     */
    private Integer machineAuditNotPassCount;

    /**
     * 当日人工通过数
     *
     */
    private Integer reviewPassCount;

    /**
     * 当日人工拒绝数
     *
     */
    private Integer reviewNotPassCount;

    /**
     * 当日首贷放款笔数
     *
     */
    private Integer firstLoadCount;

    /**
     * 当日复贷放款笔数
     */
    private Integer againLoadCount;

    /**
     * 当日逾期笔数
     */
    private Integer overdueCount;

    /**
     *
     * 当日首逾笔数
     */
    private Integer firstOverdueCount;

    /**
     * 机审通过率
     */
    private Double machineAuditPassRate;

    /**
     * 机审拒绝率
     */
    private Double machineAuditNotPassRate;

    /**
     * 人工复审通过率
     */
    private Double reviewPassRate;

    /**
     * 人工复审拒绝率
     */
    private Double reviewNotPassRate;

    /**
     * 首逾率
     */
    private Double firstOverdueRate;

    /**
     * 逾期率
     */
    private Double  overdueRate;

    /**
     * 放款率
     */
    private Double loadRate;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getUserRegister() {
        return userRegister;
    }

    public void setUserRegister(Integer userRegister) {
        this.userRegister = userRegister;
    }

    public Integer getBorrowApplyCount() {
        return borrowApplyCount;
    }

    public void setBorrowApplyCount(Integer borrowApplyCount) {
        this.borrowApplyCount = borrowApplyCount;
    }

    public Integer getMachineAuditPassCount() {
        return machineAuditPassCount;
    }

    public void setMachineAuditPassCount(Integer machineAuditPassCount) {
        this.machineAuditPassCount = machineAuditPassCount;
    }

    public Integer getMachineAuditNotPassCount() {
        return machineAuditNotPassCount;
    }

    public void setMachineAuditNotPassCount(Integer machineAuditNotPassCount) {
        this.machineAuditNotPassCount = machineAuditNotPassCount;
    }

    public Integer getReviewPassCount() {
        return reviewPassCount;
    }

    public void setReviewPassCount(Integer reviewPassCount) {
        this.reviewPassCount = reviewPassCount;
    }

    public Integer getReviewNotPassCount() {
        return reviewNotPassCount;
    }

    public void setReviewNotPassCount(Integer reviewNotPassCount) {
        this.reviewNotPassCount = reviewNotPassCount;
    }

    public Integer getFirstLoadCount() {
        return firstLoadCount;
    }

    public void setFirstLoadCount(Integer firstLoadCount) {
        this.firstLoadCount = firstLoadCount;
    }

    public Integer getAgainLoadCount() {
        return againLoadCount;
    }

    public void setAgainLoadCount(Integer againLoadCount) {
        this.againLoadCount = againLoadCount;
    }

    public Integer getOverdueCount() {
        return overdueCount;
    }

    public void setOverdueCount(Integer overdueCount) {
        this.overdueCount = overdueCount;
    }

    public Integer getFirstOverdueCount() {
        return firstOverdueCount;
    }

    public void setFirstOverdueCount(Integer firstOverdueCount) {
        this.firstOverdueCount = firstOverdueCount;
    }

    public Double getMachineAuditPassRate() {
        return machineAuditPassRate;
    }

    public void setMachineAuditPassRate(Double machineAuditPassRate) {
        this.machineAuditPassRate = machineAuditPassRate;
    }

    public Double getMachineAuditNotPassRate() {
        return machineAuditNotPassRate;
    }

    public void setMachineAuditNotPassRate(Double machineAuditNotPassRate) {
        this.machineAuditNotPassRate = machineAuditNotPassRate;
    }

    public Double getReviewPassRate() {
        return reviewPassRate;
    }

    public void setReviewPassRate(Double reviewPassRate) {
        this.reviewPassRate = reviewPassRate;
    }

    public Double getReviewNotPassRate() {
        return reviewNotPassRate;
    }

    public void setReviewNotPassRate(Double reviewNotPassRate) {
        this.reviewNotPassRate = reviewNotPassRate;
    }

    public Double getFirstOverdueRate() {
        return firstOverdueRate;
    }

    public void setFirstOverdueRate(Double firstOverdueRate) {
        this.firstOverdueRate = firstOverdueRate;
    }

    public Double getOverdueRate() {
        return overdueRate;
    }

    public void setOverdueRate(Double overdueRate) {
        this.overdueRate = overdueRate;
    }

    public Double getLoadRate() {
        return loadRate;
    }

    public void setLoadRate(Double loadRate) {
        this.loadRate = loadRate;
    }
}
