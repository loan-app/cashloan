package com.xiji.cashloan.cl.model.xindedata;

/**
 * @Auther: king
 * @Date: 2018/12/19 12:06
 * @Description:
 */
public class XdBlackResult {
    private boolean isLastLoanRefused;
    private String firstLoanTime;
    private String longestOverDueUnpaid;
    private String lastLoanRefuseReason;
    private String lastLoanRefuseTime;
    private String longestOverDuePaid;
    private int totalLoanCount;
    private int totalOverDueCount;
    private int currentOverDueCount;
    private int currentOverDueAmount;

    public boolean isLastLoanRefused() {
        return isLastLoanRefused;
    }

    public void setLastLoanRefused(boolean lastLoanRefused) {
        isLastLoanRefused = lastLoanRefused;
    }

    public String getFirstLoanTime() {
        return firstLoanTime;
    }

    public void setFirstLoanTime(String firstLoanTime) {
        this.firstLoanTime = firstLoanTime;
    }

    public String getLongestOverDueUnpaid() {
        return longestOverDueUnpaid;
    }

    public void setLongestOverDueUnpaid(String longestOverDueUnpaid) {
        this.longestOverDueUnpaid = longestOverDueUnpaid;
    }

    public String getLastLoanRefuseReason() {
        return lastLoanRefuseReason;
    }

    public void setLastLoanRefuseReason(String lastLoanRefuseReason) {
        this.lastLoanRefuseReason = lastLoanRefuseReason;
    }

    public String getLastLoanRefuseTime() {
        return lastLoanRefuseTime;
    }

    public void setLastLoanRefuseTime(String lastLoanRefuseTime) {
        this.lastLoanRefuseTime = lastLoanRefuseTime;
    }

    public String getLongestOverDuePaid() {
        return longestOverDuePaid;
    }

    public void setLongestOverDuePaid(String longestOverDuePaid) {
        this.longestOverDuePaid = longestOverDuePaid;
    }

    public int getTotalLoanCount() {
        return totalLoanCount;
    }

    public void setTotalLoanCount(int totalLoanCount) {
        this.totalLoanCount = totalLoanCount;
    }

    public int getTotalOverDueCount() {
        return totalOverDueCount;
    }

    public void setTotalOverDueCount(int totalOverDueCount) {
        this.totalOverDueCount = totalOverDueCount;
    }

    public int getCurrentOverDueCount() {
        return currentOverDueCount;
    }

    public void setCurrentOverDueCount(int currentOverDueCount) {
        this.currentOverDueCount = currentOverDueCount;
    }

    public int getCurrentOverDueAmount() {
        return currentOverDueAmount;
    }

    public void setCurrentOverDueAmount(int currentOverDueAmount) {
        this.currentOverDueAmount = currentOverDueAmount;
    }
}
