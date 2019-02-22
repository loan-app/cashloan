package com.xiji.cashloan.cl.domain.statistic;

import java.io.Serializable;
import java.util.Date;

/**
 * 放款统计数据实体
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-02-15 15:38:59
 */
 public class LoadStatisticData implements Serializable {

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
    * 放款笔数
    */
    private Integer loadCount;

    /**
    * 放款金额
    */
    private Double loadAmount;

    /**
    * 放款本金
    */
    private Double loadPrincipal;

    /**
    * 首贷人数
    */
    private Integer firstLoadCount;

    /**
    * 首贷金额
    */
    private Double firstLoadAmount;

    /**
    * 首贷本金
    */
    private Double firstLoadPrincipal;

    /**
    * 复贷人数
    */
    private Integer againLoadCount;

    /**
    * 复贷金额
    */
    private Double againLoadAmount;

    /**
    * 复贷本金
    */
    private Double againLoadPrincipal;


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
    * 获取放款笔数
    *
    * @return 放款笔数
    */
    public Integer getLoadCount(){
        return loadCount;
    }

    /**
    * 设置放款笔数
    * 
    * @param loadCount 要设置的放款笔数
    */
    public void setLoadCount(Integer loadCount){
        this.loadCount = loadCount;
    }

    /**
    * 获取放款金额
    *
    * @return 放款金额
    */
    public Double getLoadAmount(){
        return loadAmount;
    }

    /**
    * 设置放款金额
    * 
    * @param loadAmount 要设置的放款金额
    */
    public void setLoadAmount(Double loadAmount){
        this.loadAmount = loadAmount;
    }

    /**
    * 获取放款本金
    *
    * @return 放款本金
    */
    public Double getLoadPrincipal(){
        return loadPrincipal;
    }

    /**
    * 设置放款本金
    * 
    * @param loadPrincipal 要设置的放款本金
    */
    public void setLoadPrincipal(Double loadPrincipal){
        this.loadPrincipal = loadPrincipal;
    }

    /**
    * 获取首贷人数
    *
    * @return 首贷人数
    */
    public Integer getFirstLoadCount(){
        return firstLoadCount;
    }

    /**
    * 设置首贷人数
    * 
    * @param firstLoadCount 要设置的首贷人数
    */
    public void setFirstLoadCount(Integer firstLoadCount){
        this.firstLoadCount = firstLoadCount;
    }

    /**
    * 获取首贷金额
    *
    * @return 首贷金额
    */
    public Double getFirstLoadAmount(){
        return firstLoadAmount;
    }

    /**
    * 设置首贷金额
    * 
    * @param firstLoadAmount 要设置的首贷金额
    */
    public void setFirstLoadAmount(Double firstLoadAmount){
        this.firstLoadAmount = firstLoadAmount;
    }

    /**
    * 获取首贷本金
    *
    * @return 首贷本金
    */
    public Double getFirstLoadPrincipal(){
        return firstLoadPrincipal;
    }

    /**
    * 设置首贷本金
    * 
    * @param firstLoadPrincipal 要设置的首贷本金
    */
    public void setFirstLoadPrincipal(Double firstLoadPrincipal){
        this.firstLoadPrincipal = firstLoadPrincipal;
    }

    /**
    * 获取复贷人数
    *
    * @return 复贷人数
    */
    public Integer getAgainLoadCount(){
        return againLoadCount;
    }

    /**
    * 设置复贷人数
    * 
    * @param againLoadCount 要设置的复贷人数
    */
    public void setAgainLoadCount(Integer againLoadCount){
        this.againLoadCount = againLoadCount;
    }

    /**
    * 获取复贷金额
    *
    * @return 复贷金额
    */
    public Double getAgainLoadAmount(){
        return againLoadAmount;
    }

    /**
    * 设置复贷金额
    * 
    * @param againLoadAmount 要设置的复贷金额
    */
    public void setAgainLoadAmount(Double againLoadAmount){
        this.againLoadAmount = againLoadAmount;
    }

    /**
    * 获取复贷本金
    *
    * @return 复贷本金
    */
    public Double getAgainLoadPrincipal(){
        return againLoadPrincipal;
    }

    /**
    * 设置复贷本金
    * 
    * @param againLoadPrincipal 要设置的复贷本金
    */
    public void setAgainLoadPrincipal(Double againLoadPrincipal){
        this.againLoadPrincipal = againLoadPrincipal;
    }

}