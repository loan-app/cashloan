package com.xiji.cashloan.cl.domain.statistic;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户统计数据实体
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-02-15 15:41:39
 */
 public class UserStatisticData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 更新时间
    */
    private Date updateTime;

    /**
    * 统计时间
    */
    private Date countTime;

    /**
    * 当日注册数
    */
    private Integer userRegister;

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
    * 当日下款数
    */
    private Integer loadCount;

    /**
     * 统计时间Str
     */
    private String countTimeStr;

    /**
    * 获取主键Id
    *
    * @return id
    */
    public Long getId(){
        return id;
    }

    /**
    * 设置主键Id
    * 
    * @param 要设置的主键Id
    */
    public void setId(Long id){
        this.id = id;
    }

    /**
    * 获取创建时间
    *
    * @return 创建时间
    */
    public Date getCreateTime(){
        return createTime;
    }

    /**
    * 设置创建时间
    * 
    * @param createTime 要设置的创建时间
    */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    /**
    * 获取更新时间
    *
    * @return 更新时间
    */
    public Date getUpdateTime(){
        return updateTime;
    }

    /**
    * 设置更新时间
    * 
    * @param updateTime 要设置的更新时间
    */
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    /**
    * 获取统计时间
    *
    * @return 统计时间
    */
    public Date getCountTime(){
        return countTime;
    }

    /**
    * 设置统计时间
    * 
    * @param countTime 要设置的统计时间
    */
    public void setCountTime(Date countTime){
        this.countTime = countTime;
    }

    /**
    * 获取当日注册数
    *
    * @return 当日注册数
    */
    public Integer getUserRegister(){
        return userRegister;
    }

    /**
    * 设置当日注册数
    * 
    * @param userRegister 要设置的当日注册数
    */
    public void setUserRegister(Integer userRegister){
        this.userRegister = userRegister;
    }

    /**
    * 获取实名认证人数
    *
    * @return 实名认证人数
    */
    public Integer getAuthCount(){
        return authCount;
    }

    /**
    * 设置实名认证人数
    * 
    * @param authCount 要设置的实名认证人数
    */
    public void setAuthCount(Integer authCount){
        this.authCount = authCount;
    }

    /**
    * 获取通讯录认证数
    *
    * @return 通讯录认证数
    */
    public Integer getContactCount(){
        return contactCount;
    }

    /**
    * 设置通讯录认证数
    * 
    * @param contactCount 要设置的通讯录认证数
    */
    public void setContactCount(Integer contactCount){
        this.contactCount = contactCount;
    }

    /**
    * 获取银行卡绑定数
    *
    * @return 银行卡绑定数
    */
    public Integer getBankCount(){
        return bankCount;
    }

    /**
    * 设置银行卡绑定数
    * 
    * @param bankCount 要设置的银行卡绑定数
    */
    public void setBankCount(Integer bankCount){
        this.bankCount = bankCount;
    }

    /**
    * 获取手机运营商认证数
    *
    * @return 手机运营商认证数
    */
    public Integer getPhoneCount(){
        return phoneCount;
    }

    /**
    * 设置手机运营商认证数
    * 
    * @param phoneCount 要设置的手机运营商认证数
    */
    public void setPhoneCount(Integer phoneCount){
        this.phoneCount = phoneCount;
    }

    /**
    * 获取当日申请总数
    *
    * @return 当日申请总数
    */
    public Integer getBorrowApplyCount(){
        return borrowApplyCount;
    }

    /**
    * 设置当日申请总数
    * 
    * @param borrowApplyCount 要设置的当日申请总数
    */
    public void setBorrowApplyCount(Integer borrowApplyCount){
        this.borrowApplyCount = borrowApplyCount;
    }

    /**
    * 获取当日新客借款
    *
    * @return 当日新客借款
    */
    public Integer getNewBorrowCount(){
        return newBorrowCount;
    }

    /**
    * 设置当日新客借款
    * 
    * @param newBorrowCount 要设置的当日新客借款
    */
    public void setNewBorrowCount(Integer newBorrowCount){
        this.newBorrowCount = newBorrowCount;
    }

    /**
    * 获取当日老客借款数
    *
    * @return 当日老客借款数
    */
    public Integer getOldBorrowCount(){
        return oldBorrowCount;
    }

    /**
    * 设置当日老客借款数
    * 
    * @param oldBorrowCount 要设置的当日老客借款数
    */
    public void setOldBorrowCount(Integer oldBorrowCount){
        this.oldBorrowCount = oldBorrowCount;
    }

    /**
    * 获取当日新客下款数
    *
    * @return 当日新客下款数
    */
    public Integer getNewLoadCount(){
        return newLoadCount;
    }

    /**
    * 设置当日新客下款数
    * 
    * @param newLoadCount 要设置的当日新客下款数
    */
    public void setNewLoadCount(Integer newLoadCount){
        this.newLoadCount = newLoadCount;
    }

    /**
    * 获取当日老客下款数
    *
    * @return 当日老客下款数
    */
    public Integer getOldLoadCount(){
        return oldLoadCount;
    }

    /**
    * 设置当日老客下款数
    * 
    * @param oldLoadCount 要设置的当日老客下款数
    */
    public void setOldLoadCount(Integer oldLoadCount){
        this.oldLoadCount = oldLoadCount;
    }

    /**
    * 获取当日下款数
    *
    * @return 当日下款数
    */
    public Integer getLoadCount(){
        return loadCount;
    }

    /**
    * 设置当日下款数
    * 
    * @param loadCount 要设置的当日下款数
    */
    public void setLoadCount(Integer loadCount){
        this.loadCount = loadCount;
    }

    public String getCountTimeStr() {
        return countTimeStr;
    }

    public void setCountTimeStr(String countTimeStr) {
        this.countTimeStr = countTimeStr;
    }
}