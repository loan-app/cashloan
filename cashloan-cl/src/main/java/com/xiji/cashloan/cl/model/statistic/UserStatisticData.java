package com.xiji.cashloan.cl.model.statistic;

/**
 * @auther : wnb
 * @date : 2019/1/28
 * @describe :用户统计
 */
public class UserStatisticData {


    /**
     * 日期
     */
    private String date;

    /**
     * 当日注册数
     */
    private Integer userRegist;

    /**
     * 实名认证人数
     */
    private Integer authCount;

    /**
     * 通讯录认证数
     */
    private Integer contactCount;

    /**
     * 银行卡绑定数
     */
    private Integer bankCount;

    /**
     * 手机运营商认证数
     */
    private Integer phoneCount;

    /**
     * 当日申请总数
     */
    private Integer borrowApplyCount;

    /**
     * 当日新客借款
     */
    private Integer newBorrowCount;

    /**
     * 当日老客借款数
     */
    private Integer oldBorrowCount;

    /**
     * 当日新客下款数
     */
    private Integer newLoadCount;

    /**
     * 当日老客下款数
     */
    private Integer oldLoadCount;

    /**
     * 当日下款总数
     */
    private Integer loadCount;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getUserRegist() {
        return userRegist;
    }

    public void setUserRegist(Integer userRegist) {
        this.userRegist = userRegist;
    }

    public Integer getAuthCount() {
        return authCount;
    }

    public void setAuthCount(Integer authCount) {
        this.authCount = authCount;
    }

    public Integer getContactCount() {
        return contactCount;
    }

    public void setContactCount(Integer contactCount) {
        this.contactCount = contactCount;
    }

    public Integer getBankCount() {
        return bankCount;
    }

    public void setBankCount(Integer bankCount) {
        this.bankCount = bankCount;
    }

    public Integer getPhoneCount() {
        return phoneCount;
    }

    public void setPhoneCount(Integer phoneCount) {
        this.phoneCount = phoneCount;
    }

    public Integer getBorrowApplyCount() {
        return borrowApplyCount;
    }

    public void setBorrowApplyCount(Integer borrowApplyCount) {
        this.borrowApplyCount = borrowApplyCount;
    }

    public Integer getNewBorrowCount() {
        return newBorrowCount;
    }

    public void setNewBorrowCount(Integer newBorrowCount) {
        this.newBorrowCount = newBorrowCount;
    }

    public Integer getOldBorrowCount() {
        return oldBorrowCount;
    }

    public void setOldBorrowCount(Integer oldBorrowCount) {
        this.oldBorrowCount = oldBorrowCount;
    }

    public Integer getNewLoadCount() {
        return newLoadCount;
    }

    public void setNewLoadCount(Integer newLoadCount) {
        this.newLoadCount = newLoadCount;
    }

    public Integer getOldLoadCount() {
        return oldLoadCount;
    }

    public void setOldLoadCount(Integer oldLoadCount) {
        this.oldLoadCount = oldLoadCount;
    }

    public Integer getLoadCount() {
        return loadCount;
    }

    public void setLoadCount(Integer loadCount) {
        this.loadCount = loadCount;
    }
}
