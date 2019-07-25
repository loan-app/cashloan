package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 宜信综合决策报告评分实体
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-07-23 21:46:38
 */
 public class YixinScore implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 用户标识
    */
    private Long userId;

    /**
    * 借款订单id
    */
    private Long borrowId;

    /**
    * 流水号
    */
    private String flowId;

    /**
    * 综合评分
    */
    private Double compositeScore;

    /**
    * 决策建议
    */
    private String decisionSuggest;

    /**
    * 创建时间
    */
    private Date gmtCreate;

    /**
    * 修改时间
    */
    private Date gmtModified;


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
    * 获取用户标识
    *
    * @return 用户标识
    */
    public Long getUserId(){
        return userId;
    }

    /**
    * 设置用户标识
    * 
    * @param userId 要设置的用户标识
    */
    public void setUserId(Long userId){
        this.userId = userId;
    }

    /**
    * 获取借款订单id
    *
    * @return 借款订单id
    */
    public Long getBorrowId(){
        return borrowId;
    }

    /**
    * 设置借款订单id
    * 
    * @param borrowId 要设置的借款订单id
    */
    public void setBorrowId(Long borrowId){
        this.borrowId = borrowId;
    }

    /**
    * 获取流水号
    *
    * @return 流水号
    */
    public String getFlowId(){
        return flowId;
    }

    /**
    * 设置流水号
    * 
    * @param flowId 要设置的流水号
    */
    public void setFlowId(String flowId){
        this.flowId = flowId;
    }

    /**
    * 获取综合评分
    *
    * @return 综合评分
    */
    public Double getCompositeScore(){
        return compositeScore;
    }

    /**
    * 设置综合评分
    * 
    * @param compositeScore 要设置的综合评分
    */
    public void setCompositeScore(Double compositeScore){
        this.compositeScore = compositeScore;
    }

    /**
    * 获取决策建议
    *
    * @return 决策建议
    */
    public String getDecisionSuggest(){
        return decisionSuggest;
    }

    /**
    * 设置决策建议
    * 
    * @param decisionSuggest 要设置的决策建议
    */
    public void setDecisionSuggest(String decisionSuggest){
        this.decisionSuggest = decisionSuggest;
    }

    /**
    * 获取创建时间
    *
    * @return 创建时间
    */
    public Date getGmtCreate(){
        return gmtCreate;
    }

    /**
    * 设置创建时间
    * 
    * @param gmtCreate 要设置的创建时间
    */
    public void setGmtCreate(Date gmtCreate){
        this.gmtCreate = gmtCreate;
    }

    /**
    * 获取修改时间
    *
    * @return 修改时间
    */
    public Date getGmtModified(){
        return gmtModified;
    }

    /**
    * 设置修改时间
    * 
    * @param gmtModified 要设置的修改时间
    */
    public void setGmtModified(Date gmtModified){
        this.gmtModified = gmtModified;
    }

}