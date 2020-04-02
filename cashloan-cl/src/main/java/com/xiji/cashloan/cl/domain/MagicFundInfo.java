package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 魔杖2.0报告-基础信息表实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:05
 */
 public class MagicFundInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 报告id
    */
    private String transId;

    /**
    * 最近数据更新时间. 格式为yyyy-MM-dd
    */
    private String updateDate;

    /**
    * 最近缴存时间. 格式为yyyy-MM-dd
    */
    private String lastPayDate;

    /**
    * 账户状态
    */
    private String accountStatus;

    /**
    * 开户时间. 格式为yyyy-MM-dd
    */
    private String openDate;

    /**
    * 开户地区
    */
    private String openLocation;

    /**
    * 账户余额
    */
    private String balance;

    /**
    * 公积金邮箱
    */
    private String email;

    /**
    * 缴存基数
    */
    private String baseAmount;

    /**
    * 月缴存金额
    */
    private String monthlyIncome;

    /**
    * 近一年缴纳月数
    */
    private String totalMonths;

    /**
    * 近一年连续缴纳月数
    */
    private Integer continuousMonths;

    /**
    * 近一年补缴次数
    */
    private Integer repayTimes;

    /**
    * 近一年缴纳单位数
    */
    private Integer totalCompanies;

    /**
    * 创建时间
    */
    private Date createTime;


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
    * 获取用户id
    *
    * @return 用户id
    */
    public Long getUserId(){
        return userId;
    }

    /**
    * 设置用户id
    * 
    * @param userId 要设置的用户id
    */
    public void setUserId(Long userId){
        this.userId = userId;
    }

    /**
    * 获取报告id
    *
    * @return 报告id
    */
    public String getTransId(){
        return transId;
    }

    /**
    * 设置报告id
    * 
    * @param transId 要设置的报告id
    */
    public void setTransId(String transId){
        this.transId = transId;
    }

    /**
    * 获取最近数据更新时间. 格式为yyyy-MM-dd
    *
    * @return 最近数据更新时间. 格式为yyyy-MM-dd
    */
    public String getUpdateDate(){
        return updateDate;
    }

    /**
    * 设置最近数据更新时间. 格式为yyyy-MM-dd
    * 
    * @param updateDate 要设置的最近数据更新时间. 格式为yyyy-MM-dd
    */
    public void setUpdateDate(String updateDate){
        this.updateDate = updateDate;
    }

    /**
    * 获取最近缴存时间. 格式为yyyy-MM-dd
    *
    * @return 最近缴存时间. 格式为yyyy-MM-dd
    */
    public String getLastPayDate(){
        return lastPayDate;
    }

    /**
    * 设置最近缴存时间. 格式为yyyy-MM-dd
    * 
    * @param lastPayDate 要设置的最近缴存时间. 格式为yyyy-MM-dd
    */
    public void setLastPayDate(String lastPayDate){
        this.lastPayDate = lastPayDate;
    }

    /**
    * 获取账户状态
    *
    * @return 账户状态
    */
    public String getAccountStatus(){
        return accountStatus;
    }

    /**
    * 设置账户状态
    * 
    * @param accountStatus 要设置的账户状态
    */
    public void setAccountStatus(String accountStatus){
        this.accountStatus = accountStatus;
    }

    /**
    * 获取开户时间. 格式为yyyy-MM-dd
    *
    * @return 开户时间. 格式为yyyy-MM-dd
    */
    public String getOpenDate(){
        return openDate;
    }

    /**
    * 设置开户时间. 格式为yyyy-MM-dd
    * 
    * @param openDate 要设置的开户时间. 格式为yyyy-MM-dd
    */
    public void setOpenDate(String openDate){
        this.openDate = openDate;
    }

    /**
    * 获取开户地区
    *
    * @return 开户地区
    */
    public String getOpenLocation(){
        return openLocation;
    }

    /**
    * 设置开户地区
    * 
    * @param openLocation 要设置的开户地区
    */
    public void setOpenLocation(String openLocation){
        this.openLocation = openLocation;
    }

    /**
    * 获取账户余额
    *
    * @return 账户余额
    */
    public String getBalance(){
        return balance;
    }

    /**
    * 设置账户余额
    * 
    * @param balance 要设置的账户余额
    */
    public void setBalance(String balance){
        this.balance = balance;
    }

    /**
    * 获取公积金邮箱
    *
    * @return 公积金邮箱
    */
    public String getEmail(){
        return email;
    }

    /**
    * 设置公积金邮箱
    * 
    * @param email 要设置的公积金邮箱
    */
    public void setEmail(String email){
        this.email = email;
    }

    /**
    * 获取缴存基数
    *
    * @return 缴存基数
    */
    public String getBaseAmount(){
        return baseAmount;
    }

    /**
    * 设置缴存基数
    * 
    * @param baseAmount 要设置的缴存基数
    */
    public void setBaseAmount(String baseAmount){
        this.baseAmount = baseAmount;
    }

    /**
    * 获取月缴存金额
    *
    * @return 月缴存金额
    */
    public String getMonthlyIncome(){
        return monthlyIncome;
    }

    /**
    * 设置月缴存金额
    * 
    * @param monthlyIncome 要设置的月缴存金额
    */
    public void setMonthlyIncome(String monthlyIncome){
        this.monthlyIncome = monthlyIncome;
    }

    /**
    * 获取近一年缴纳月数
    *
    * @return 近一年缴纳月数
    */
    public String getTotalMonths(){
        return totalMonths;
    }

    /**
    * 设置近一年缴纳月数
    * 
    * @param totalMonths 要设置的近一年缴纳月数
    */
    public void setTotalMonths(String totalMonths){
        this.totalMonths = totalMonths;
    }

    /**
    * 获取近一年连续缴纳月数
    *
    * @return 近一年连续缴纳月数
    */
    public Integer getContinuousMonths(){
        return continuousMonths;
    }

    /**
    * 设置近一年连续缴纳月数
    * 
    * @param continuousMonths 要设置的近一年连续缴纳月数
    */
    public void setContinuousMonths(Integer continuousMonths){
        this.continuousMonths = continuousMonths;
    }

    /**
    * 获取近一年补缴次数
    *
    * @return 近一年补缴次数
    */
    public Integer getRepayTimes(){
        return repayTimes;
    }

    /**
    * 设置近一年补缴次数
    * 
    * @param repayTimes 要设置的近一年补缴次数
    */
    public void setRepayTimes(Integer repayTimes){
        this.repayTimes = repayTimes;
    }

    /**
    * 获取近一年缴纳单位数
    *
    * @return 近一年缴纳单位数
    */
    public Integer getTotalCompanies(){
        return totalCompanies;
    }

    /**
    * 设置近一年缴纳单位数
    * 
    * @param totalCompanies 要设置的近一年缴纳单位数
    */
    public void setTotalCompanies(Integer totalCompanies){
        this.totalCompanies = totalCompanies;
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

}