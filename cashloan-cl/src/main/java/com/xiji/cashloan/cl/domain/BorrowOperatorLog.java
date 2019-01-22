package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 借款订单运营商记录表实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2019-01-14 11:52:09
 */
 public class BorrowOperatorLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 请求记录id
    */
    private Long reqLogId;

    /**
    * 借款标识
    */
    private Long borrowId;

    /**
    * 
    */
    private Date createTime;

    /**
    * 
    */
    private Date lastModifyTime;


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
    * 获取请求记录id
    *
    * @return 请求记录id
    */
    public Long getReqLogId(){
        return reqLogId;
    }

    /**
    * 设置请求记录id
    * 
    * @param reqLogId 要设置的请求记录id
    */
    public void setReqLogId(Long reqLogId){
        this.reqLogId = reqLogId;
    }

    /**
    * 获取借款标识
    *
    * @return 借款标识
    */
    public Long getBorrowId(){
        return borrowId;
    }

    /**
    * 设置借款标识
    * 
    * @param borrowId 要设置的借款标识
    */
    public void setBorrowId(Long borrowId){
        this.borrowId = borrowId;
    }

    /**
    * 获取
    *
    * @return 
    */
    public Date getCreateTime(){
        return createTime;
    }

    /**
    * 设置
    * 
    * @param createTime 要设置的
    */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    /**
    * 获取
    *
    * @return 
    */
    public Date getLastModifyTime(){
        return lastModifyTime;
    }

    /**
    * 设置
    * 
    * @param lastModifyTime 要设置的
    */
    public void setLastModifyTime(Date lastModifyTime){
        this.lastModifyTime = lastModifyTime;
    }

}