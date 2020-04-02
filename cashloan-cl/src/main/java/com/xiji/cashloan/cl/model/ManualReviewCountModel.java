package com.xiji.cashloan.cl.model;

/**
 * 信审人员统计列表字段
 * Created by szb on 18/12/16.
 */
public class ManualReviewCountModel {
    /**
     * 信审员id
     */
    private Long userId;

    /**
     * 信审员名称
     */
    private String name;

    /**
     * 信审员登录名
     */
    private String userName;

    /**
     * 信审员工作号
     */
    private String jobNumber;

    /**
     * 信审员状态
     */
    private String status;

    /**
     * 审核订单总数
     */
    private int orderCount;

    /**r
     * 待审核数
     */
    private int waitCount;
    /**
     * 审核通过数
     */
    private int passCount;
    /**
     * 审核拒绝数
     */
    private int refusedCount;
    /**
     * 累计审核通过率
     */
    private double allPassRatio;

    /**
     * 今日审核通过订单数
     */
    private double todayPassOrderCount;

    /**
     * 今日放款订单数
     */
    private double todayLoanOrderCount;

    /**
     *
     */
    private int yesterdayCount;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public int getWaitCount() {
        return waitCount;
    }

    public void setWaitCount(int waitCount) {
        this.waitCount = waitCount;
    }

    public int getPassCount() {
        return passCount;
    }

    public void setPassCount(int passCount) {
        this.passCount = passCount;
    }

    public int getRefusedCount() {
        return refusedCount;
    }

    public void setRefusedCount(int refusedCount) {
        this.refusedCount = refusedCount;
    }

    public double getAllPassRatio() {
        return allPassRatio;
    }

    public void setAllPassRatio(double allPassRatio) {
        this.allPassRatio = allPassRatio;
    }

    public int getYesterdayCount() {
        return yesterdayCount;
    }

    public void setYesterdayCount(int yesterdayCount) {
        this.yesterdayCount = yesterdayCount;
    }

    public double getTodayPassOrderCount() {
        return todayPassOrderCount;
    }

    public void setTodayPassOrderCount(double todayPassOrderCount) {
        this.todayPassOrderCount = todayPassOrderCount;
    }

    public double getTodayLoanOrderCount() {
        return todayLoanOrderCount;
    }

    public void setTodayLoanOrderCount(double todayLoanOrderCount) {
        this.todayLoanOrderCount = todayLoanOrderCount;
    }
}
