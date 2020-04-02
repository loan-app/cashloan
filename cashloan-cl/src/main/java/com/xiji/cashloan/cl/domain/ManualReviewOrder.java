package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 人工审核订单实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-16 17:00:19
 */
 public class ManualReviewOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 审核人id
    */
    private Long userId;

    /**
    * 审核人姓名
    */
    private String userName;

    /**
    * 借款人姓名
    */
    private String borrowName;

    /**
    * 借款人手机号
    */
    private String phone;

    /**
    * 借款id
    */
    private Long borrowId;

    /**
    * 订单状态   10未分配，11待审核，20审核通过，30审核拒绝
    */
    private String state;

    /**
    * 备注说明
    */
    private String remark;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 审核时间
    */
    private Date reviewTime;

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
    * 获取审核人id
    *
    * @return 审核人id
    */
    public Long getUserId(){
        return userId;
    }

    /**
    * 设置审核人id
    * 
    * @param userId 要设置的审核人id
    */
    public void setUserId(Long userId){
        this.userId = userId;
    }

    /**
    * 获取审核人姓名
    *
    * @return 审核人姓名
    */
    public String getUserName(){
        return userName;
    }

    /**
    * 设置审核人姓名
    * 
    * @param userName 要设置的审核人姓名
    */
    public void setUserName(String userName){
        this.userName = userName;
    }

    /**
    * 获取借款人姓名
    *
    * @return 借款人姓名
    */
    public String getBorrowName(){
        return borrowName;
    }

    /**
    * 设置借款人姓名
    * 
    * @param borrowName 要设置的借款人姓名
    */
    public void setBorrowName(String borrowName){
        this.borrowName = borrowName;
    }

    /**
    * 获取借款人手机号
    *
    * @return 借款人手机号
    */
    public String getPhone(){
        return phone;
    }

    /**
    * 设置借款人手机号
    * 
    * @param phone 要设置的借款人手机号
    */
    public void setPhone(String phone){
        this.phone = phone;
    }

    /**
    * 获取借款id
    *
    * @return 借款id
    */
    public Long getBorrowId(){
        return borrowId;
    }

    /**
    * 设置借款id
    * 
    * @param borrowId 要设置的借款id
    */
    public void setBorrowId(Long borrowId){
        this.borrowId = borrowId;
    }

    /**
    * 获取订单状态   10未分配，11待审核，20审核通过，30审核拒绝
    *
    * @return 订单状态   10未分配，11待审核，20审核通过，30审核拒绝
    */
    public String getState(){
        return state;
    }

    /**
    * 设置订单状态   10未分配，11待审核，20审核通过，30审核拒绝
    * 
    * @param state 要设置的订单状态   10未分配，11待审核，20审核通过，30审核拒绝
    */
    public void setState(String state){
        this.state = state;
    }

    /**
    * 获取备注说明
    *
    * @return 备注说明
    */
    public String getRemark(){
        return remark;
    }

    /**
    * 设置备注说明
    * 
    * @param remark 要设置的备注说明
    */
    public void setRemark(String remark){
        this.remark = remark;
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
    * 获取审核时间
    *
    * @return 审核时间
    */
    public Date getReviewTime(){
        return reviewTime;
    }

    /**
    * 设置审核时间
    * 
    * @param reviewTime 要设置的审核时间
    */
    public void setReviewTime(Date reviewTime){
        this.reviewTime = reviewTime;
    }
}