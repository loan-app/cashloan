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
 public class MagicRiskQqGroup implements Serializable {

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
    * 命中借贷群数量
    */
    private Integer loanGroupcnt;

    /**
    * 命中分期群数量
    */
    private Integer installmentGroupcnt;

    /**
    * 命中理财群数量
    */
    private Integer financialManagementGroupcnt;

    /**
    * 命中薅羊毛群数量
    */
    private Integer woolenGroupcnt;

    /**
    * 命中赌博彩票群数量
    */
    private Integer gamblingGroupcnt;

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
    * 获取命中借贷群数量
    *
    * @return 命中借贷群数量
    */
    public Integer getLoanGroupcnt(){
        return loanGroupcnt;
    }

    /**
    * 设置命中借贷群数量
    * 
    * @param loanGroupcnt 要设置的命中借贷群数量
    */
    public void setLoanGroupcnt(Integer loanGroupcnt){
        this.loanGroupcnt = loanGroupcnt;
    }

    /**
    * 获取命中分期群数量
    *
    * @return 命中分期群数量
    */
    public Integer getInstallmentGroupcnt(){
        return installmentGroupcnt;
    }

    /**
    * 设置命中分期群数量
    * 
    * @param installmentGroupcnt 要设置的命中分期群数量
    */
    public void setInstallmentGroupcnt(Integer installmentGroupcnt){
        this.installmentGroupcnt = installmentGroupcnt;
    }

    /**
    * 获取命中理财群数量
    *
    * @return 命中理财群数量
    */
    public Integer getFinancialManagementGroupcnt(){
        return financialManagementGroupcnt;
    }

    /**
    * 设置命中理财群数量
    * 
    * @param financialManagementGroupcnt 要设置的命中理财群数量
    */
    public void setFinancialManagementGroupcnt(Integer financialManagementGroupcnt){
        this.financialManagementGroupcnt = financialManagementGroupcnt;
    }

    /**
    * 获取命中薅羊毛群数量
    *
    * @return 命中薅羊毛群数量
    */
    public Integer getWoolenGroupcnt(){
        return woolenGroupcnt;
    }

    /**
    * 设置命中薅羊毛群数量
    * 
    * @param woolenGroupcnt 要设置的命中薅羊毛群数量
    */
    public void setWoolenGroupcnt(Integer woolenGroupcnt){
        this.woolenGroupcnt = woolenGroupcnt;
    }

    /**
    * 获取命中赌博彩票群数量
    *
    * @return 命中赌博彩票群数量
    */
    public Integer getGamblingGroupcnt(){
        return gamblingGroupcnt;
    }

    /**
    * 设置命中赌博彩票群数量
    * 
    * @param gamblingGroupcnt 要设置的命中赌博彩票群数量
    */
    public void setGamblingGroupcnt(Integer gamblingGroupcnt){
        this.gamblingGroupcnt = gamblingGroupcnt;
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