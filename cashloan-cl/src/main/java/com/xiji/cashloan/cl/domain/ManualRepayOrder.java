package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

public class ManualRepayOrder implements Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    private Long id;

    /**
     * 到期人id
     */
    private Long userId;

    /**
     * 借款人id
     */
    private Long borrowUserId;

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
     * 分配状态   10未分配，20已分配
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
     * 分配时间
     */
    private Date reviewTime;
    /**
     * 还款计划id
     */
    private Long borrowRepayId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBorrowUserId() {
        return borrowUserId;
    }

    public void setBorrowUserId(Long borrowUserId) {
        this.borrowUserId = borrowUserId;
    }

    public String getBorrowName() {
        return borrowName;
    }

    public void setBorrowName(String borrowName) {
        this.borrowName = borrowName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Long getBorrowRepayId() {
        return borrowRepayId;
    }

    public void setBorrowRepayId(Long borrowRepayId) {
        this.borrowRepayId = borrowRepayId;
    }
}
