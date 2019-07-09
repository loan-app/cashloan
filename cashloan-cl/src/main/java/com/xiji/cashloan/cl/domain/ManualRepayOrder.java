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
     * 获取到期人id
     *
     * @return 到期人id
     */
    public Long getUserId(){
        return userId;
    }

    /**
     * 设置到期人id
     *
     * @param userId 要设置的到期人id
     */
    public void setUserId(Long userId){
        this.userId = userId;
    }

    /**
     * 获取借款人id
     *
     * @return 借款人id
     */
    public Long getBorrowUserId(){
        return borrowUserId;
    }

    /**
     * 设置借款人id
     *
     * @param borrowUserId 要设置的借款人id
     */
    public void setBorrowUserId(Long borrowUserId){
        this.borrowUserId = borrowUserId;
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
     * 获取分配状态   10未分配，20已分配
     *
     * @return 分配状态   10未分配，20已分配
     */
    public String getState(){
        return state;
    }

    /**
     * 设置分配状态   10未分配，20已分配
     *
     * @param state 要设置的分配状态   10未分配，20已分配
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
     * 获取分配时间
     *
     * @return 分配时间
     */
    public Date getReviewTime(){
        return reviewTime;
    }

    /**
     * 设置分配时间
     *
     * @param reviewTime 要设置的分配时间
     */
    public void setReviewTime(Date reviewTime){
        this.reviewTime = reviewTime;
    }

    public Long getBorrowRepayId() {
        return borrowRepayId;
    }

    public void setBorrowRepayId(Long borrowRepayId) {
        this.borrowRepayId = borrowRepayId;
    }

}
