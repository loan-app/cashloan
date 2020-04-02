package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 有盾风险评估实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-25 16:26:48
 */
 public class YouDunRiskReport implements Serializable {

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
    * 返回内容
    */
    private String data;

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
    * 获取返回内容
    *
    * @return 返回内容
    */
    public String getData(){
        return data;
    }

    /**
    * 设置返回内容
    * 
    * @param data 要设置的返回内容
    */
    public void setData(String data){
        this.data = data;
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