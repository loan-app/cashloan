package com.xiji.cashloan.cl.domain.statistic;

import java.io.Serializable;
import java.util.Date;

/**
 * 审核统计数据实体
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-02-15 15:37:50
 */
 public class AuditingStatisticData implements Serializable {

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
    * 申请笔数
    */
    private Integer borrowApplyCount;

    /**
    * 机审拒绝数
    */
    private Integer machineAuditNotPassCount;

    /**
    * 当日机审通过数
    */
    private Integer machineAuditPassCount;

    /**
    * 人工审核笔数
    */
    private Integer reviewCount;

    /**
    * 人工审核通过笔数
    */
    private Integer reviewPassCount;

    /**
    * 人工审核拒绝笔数
    */
    private Integer reviewNotPassCount;

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
    * 放款笔数
    */
    private Integer loadCount;


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
    * 获取申请笔数
    *
    * @return 申请笔数
    */
    public Integer getBorrowApplyCount(){
        return borrowApplyCount;
    }

    /**
    * 设置申请笔数
    * 
    * @param borrowApplyCount 要设置的申请笔数
    */
    public void setBorrowApplyCount(Integer borrowApplyCount){
        this.borrowApplyCount = borrowApplyCount;
    }

    /**
    * 获取机审拒绝数
    *
    * @return 机审拒绝数
    */
    public Integer getMachineAuditNotPassCount(){
        return machineAuditNotPassCount;
    }

    /**
    * 设置机审拒绝数
    * 
    * @param machineAuditNotPassCount 要设置的机审拒绝数
    */
    public void setMachineAuditNotPassCount(Integer machineAuditNotPassCount){
        this.machineAuditNotPassCount = machineAuditNotPassCount;
    }

    /**
    * 获取当日机审通过数
    *
    * @return 当日机审通过数
    */
    public Integer getMachineAuditPassCount(){
        return machineAuditPassCount;
    }

    /**
    * 设置当日机审通过数
    * 
    * @param machineAuditPassCount 要设置的当日机审通过数
    */
    public void setMachineAuditPassCount(Integer machineAuditPassCount){
        this.machineAuditPassCount = machineAuditPassCount;
    }

    /**
    * 获取人工审核笔数
    *
    * @return 人工审核笔数
    */
    public Integer getReviewCount(){
        return reviewCount;
    }

    /**
    * 设置人工审核笔数
    * 
    * @param reviewCount 要设置的人工审核笔数
    */
    public void setReviewCount(Integer reviewCount){
        this.reviewCount = reviewCount;
    }

    /**
    * 获取人工审核通过笔数
    *
    * @return 人工审核通过笔数
    */
    public Integer getReviewPassCount(){
        return reviewPassCount;
    }

    /**
    * 设置人工审核通过笔数
    * 
    * @param reviewPassCount 要设置的人工审核通过笔数
    */
    public void setReviewPassCount(Integer reviewPassCount){
        this.reviewPassCount = reviewPassCount;
    }

    /**
    * 获取人工审核拒绝笔数
    *
    * @return 人工审核拒绝笔数
    */
    public Integer getReviewNotPassCount(){
        return reviewNotPassCount;
    }

    /**
    * 设置人工审核拒绝笔数
    * 
    * @param reviewNotPassCount 要设置的人工审核拒绝笔数
    */
    public void setReviewNotPassCount(Integer reviewNotPassCount){
        this.reviewNotPassCount = reviewNotPassCount;
    }

    /**
    * 获取机审通过率
    *
    * @return 机审通过率
    */
    public Double getMachineAuditPassRate(){
        return machineAuditPassRate;
    }

    /**
    * 设置机审通过率
    * 
    * @param machineAuditPassRate 要设置的机审通过率
    */
    public void setMachineAuditPassRate(Double machineAuditPassRate){
        this.machineAuditPassRate = machineAuditPassRate;
    }

    /**
    * 获取机审拒绝率
    *
    * @return 机审拒绝率
    */
    public Double getMachineAuditNotPassRate(){
        return machineAuditNotPassRate;
    }

    /**
    * 设置机审拒绝率
    * 
    * @param machineAuditNotPassRate 要设置的机审拒绝率
    */
    public void setMachineAuditNotPassRate(Double machineAuditNotPassRate){
        this.machineAuditNotPassRate = machineAuditNotPassRate;
    }

    /**
    * 获取人工复审通过率
    *
    * @return 人工复审通过率
    */
    public Double getReviewPassRate(){
        return reviewPassRate;
    }

    /**
    * 设置人工复审通过率
    * 
    * @param reviewPassRate 要设置的人工复审通过率
    */
    public void setReviewPassRate(Double reviewPassRate){
        this.reviewPassRate = reviewPassRate;
    }

    /**
    * 获取人工复审拒绝率
    *
    * @return 人工复审拒绝率
    */
    public Double getReviewNotPassRate(){
        return reviewNotPassRate;
    }

    /**
    * 设置人工复审拒绝率
    * 
    * @param reviewNotPassRate 要设置的人工复审拒绝率
    */
    public void setReviewNotPassRate(Double reviewNotPassRate){
        this.reviewNotPassRate = reviewNotPassRate;
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

}