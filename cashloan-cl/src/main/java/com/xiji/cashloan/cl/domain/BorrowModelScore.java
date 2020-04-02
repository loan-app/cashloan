package com.xiji.cashloan.cl.domain;

import com.xiji.cashloan.core.common.util.DateUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * 借款订单模型评分实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2019-04-18 09:49:39
 */
 public class BorrowModelScore implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 借款订单id
    */
    private Long borrowId;

    /**
    * 模型分数
    */
    private Float score;

    /**
    * 创建时间
    */
    private Date gmtCreate;

    /**
    * 修改时间
    */
    private Date gmtModified;

    public BorrowModelScore(Long borrowId, Float score) {
        super();
        this.borrowId = borrowId;
        this.score = score;
        this.gmtCreate = DateUtil.getNow();
        this.gmtModified = DateUtil.getNow();
    }


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
    * 获取模型分数
    *
    * @return 模型分数
    */
    public Float getScore(){
        return score;
    }

    /**
    * 设置模型分数
    * 
    * @param score 要设置的模型分数
    */
    public void setScore(Float score){
        this.score = score;
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