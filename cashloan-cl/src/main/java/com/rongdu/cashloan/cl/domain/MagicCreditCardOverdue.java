package com.rongdu.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 魔杖2.0报告-基础信息表实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:04
 */
 public class MagicCreditCardOverdue implements Serializable {

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
    * 最近数据更新时间
    */
    private String updateDate;

    /**
    * 信用卡开户银行数
    */
    private Integer bankNums;

    /**
    * 卡片数目
    */
    private Integer cardAmount;

    /**
    * 总信用额
    */
    private String totalCreditLimit;

    /**
    * 单一银行最高信用额
    */
    private String maxCreditLimit;

    /**
    * 有过逾期的卡片数
    */
    private Integer overdueCard;

    /**
    * 账单总数
    */
    private Integer billNums;

    /**
    * 近一年逾期账单数
    */
    private Integer overdueTimes12m;

    /**
    * 近一年逾期月数
    */
    private Integer overdueMonths12m;

    /**
    * 近6个月逾期账单数
    */
    private Integer overdueTimes6m;

    /**
    * 近6个月逾期月数
    */
    private Integer overdueMonths6m;

    /**
    * 近3个月逾期账单数
    */
    private Integer overdueTimes3m;

    /**
    * 近3个月逾期月数
    */
    private Integer overdueMonths3m;

    /**
    * 最大逾期金额
    */
    private String maxOverdueMoney;

    /**
    * 单卡最大连续逾期账单数
    */
    private Integer continueOverdueTimes;

    /**
    * 最近一次逾期时间
    */
    private String lastOverdueDate;

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
    * 获取最近数据更新时间
    *
    * @return 最近数据更新时间
    */
    public String getUpdateDate(){
        return updateDate;
    }

    /**
    * 设置最近数据更新时间
    * 
    * @param updateDate 要设置的最近数据更新时间
    */
    public void setUpdateDate(String updateDate){
        this.updateDate = updateDate;
    }

    /**
    * 获取信用卡开户银行数
    *
    * @return 信用卡开户银行数
    */
    public Integer getBankNums(){
        return bankNums;
    }

    /**
    * 设置信用卡开户银行数
    * 
    * @param bankNums 要设置的信用卡开户银行数
    */
    public void setBankNums(Integer bankNums){
        this.bankNums = bankNums;
    }

    /**
    * 获取卡片数目
    *
    * @return 卡片数目
    */
    public Integer getCardAmount(){
        return cardAmount;
    }

    /**
    * 设置卡片数目
    * 
    * @param cardAmount 要设置的卡片数目
    */
    public void setCardAmount(Integer cardAmount){
        this.cardAmount = cardAmount;
    }

    /**
    * 获取总信用额
    *
    * @return 总信用额
    */
    public String getTotalCreditLimit(){
        return totalCreditLimit;
    }

    /**
    * 设置总信用额
    * 
    * @param totalCreditLimit 要设置的总信用额
    */
    public void setTotalCreditLimit(String totalCreditLimit){
        this.totalCreditLimit = totalCreditLimit;
    }

    /**
    * 获取单一银行最高信用额
    *
    * @return 单一银行最高信用额
    */
    public String getMaxCreditLimit(){
        return maxCreditLimit;
    }

    /**
    * 设置单一银行最高信用额
    * 
    * @param maxCreditLimit 要设置的单一银行最高信用额
    */
    public void setMaxCreditLimit(String maxCreditLimit){
        this.maxCreditLimit = maxCreditLimit;
    }

    /**
    * 获取有过逾期的卡片数
    *
    * @return 有过逾期的卡片数
    */
    public Integer getOverdueCard(){
        return overdueCard;
    }

    /**
    * 设置有过逾期的卡片数
    * 
    * @param overdueCard 要设置的有过逾期的卡片数
    */
    public void setOverdueCard(Integer overdueCard){
        this.overdueCard = overdueCard;
    }

    /**
    * 获取账单总数
    *
    * @return 账单总数
    */
    public Integer getBillNums(){
        return billNums;
    }

    /**
    * 设置账单总数
    * 
    * @param billNums 要设置的账单总数
    */
    public void setBillNums(Integer billNums){
        this.billNums = billNums;
    }

    /**
    * 获取近一年逾期账单数
    *
    * @return 近一年逾期账单数
    */
    public Integer getOverdueTimes12m(){
        return overdueTimes12m;
    }

    /**
    * 设置近一年逾期账单数
    * 
    * @param overdueTimes12m 要设置的近一年逾期账单数
    */
    public void setOverdueTimes12m(Integer overdueTimes12m){
        this.overdueTimes12m = overdueTimes12m;
    }

    /**
    * 获取近一年逾期月数
    *
    * @return 近一年逾期月数
    */
    public Integer getOverdueMonths12m(){
        return overdueMonths12m;
    }

    /**
    * 设置近一年逾期月数
    * 
    * @param overdueMonths12m 要设置的近一年逾期月数
    */
    public void setOverdueMonths12m(Integer overdueMonths12m){
        this.overdueMonths12m = overdueMonths12m;
    }

    /**
    * 获取近6个月逾期账单数
    *
    * @return 近6个月逾期账单数
    */
    public Integer getOverdueTimes6m(){
        return overdueTimes6m;
    }

    /**
    * 设置近6个月逾期账单数
    * 
    * @param overdueTimes6m 要设置的近6个月逾期账单数
    */
    public void setOverdueTimes6m(Integer overdueTimes6m){
        this.overdueTimes6m = overdueTimes6m;
    }

    /**
    * 获取近6个月逾期月数
    *
    * @return 近6个月逾期月数
    */
    public Integer getOverdueMonths6m(){
        return overdueMonths6m;
    }

    /**
    * 设置近6个月逾期月数
    * 
    * @param overdueMonths6m 要设置的近6个月逾期月数
    */
    public void setOverdueMonths6m(Integer overdueMonths6m){
        this.overdueMonths6m = overdueMonths6m;
    }

    /**
    * 获取近3个月逾期账单数
    *
    * @return 近3个月逾期账单数
    */
    public Integer getOverdueTimes3m(){
        return overdueTimes3m;
    }

    /**
    * 设置近3个月逾期账单数
    * 
    * @param overdueTimes3m 要设置的近3个月逾期账单数
    */
    public void setOverdueTimes3m(Integer overdueTimes3m){
        this.overdueTimes3m = overdueTimes3m;
    }

    /**
    * 获取近3个月逾期月数
    *
    * @return 近3个月逾期月数
    */
    public Integer getOverdueMonths3m(){
        return overdueMonths3m;
    }

    /**
    * 设置近3个月逾期月数
    * 
    * @param overdueMonths3m 要设置的近3个月逾期月数
    */
    public void setOverdueMonths3m(Integer overdueMonths3m){
        this.overdueMonths3m = overdueMonths3m;
    }

    /**
    * 获取最大逾期金额
    *
    * @return 最大逾期金额
    */
    public String getMaxOverdueMoney(){
        return maxOverdueMoney;
    }

    /**
    * 设置最大逾期金额
    * 
    * @param maxOverdueMoney 要设置的最大逾期金额
    */
    public void setMaxOverdueMoney(String maxOverdueMoney){
        this.maxOverdueMoney = maxOverdueMoney;
    }

    /**
    * 获取单卡最大连续逾期账单数
    *
    * @return 单卡最大连续逾期账单数
    */
    public Integer getContinueOverdueTimes(){
        return continueOverdueTimes;
    }

    /**
    * 设置单卡最大连续逾期账单数
    * 
    * @param continueOverdueTimes 要设置的单卡最大连续逾期账单数
    */
    public void setContinueOverdueTimes(Integer continueOverdueTimes){
        this.continueOverdueTimes = continueOverdueTimes;
    }

    /**
    * 获取最近一次逾期时间
    *
    * @return 最近一次逾期时间
    */
    public String getLastOverdueDate(){
        return lastOverdueDate;
    }

    /**
    * 设置最近一次逾期时间
    * 
    * @param lastOverdueDate 要设置的最近一次逾期时间
    */
    public void setLastOverdueDate(String lastOverdueDate){
        this.lastOverdueDate = lastOverdueDate;
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