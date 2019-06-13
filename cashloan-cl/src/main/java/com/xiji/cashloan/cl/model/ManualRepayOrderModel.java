package com.xiji.cashloan.cl.model;

import com.xiji.cashloan.cl.domain.ManualRepayOrder;
import com.xiji.cashloan.core.model.BorrowModel;
import tool.util.BigDecimalUtil;

import java.util.Date;

/**
 * Created by szb on 19/3/8.
 */
public class ManualRepayOrderModel extends ManualRepayOrder{
    /**
     * 10未分配;20已分配
     */

    /** 未分配*/
    public static final String STATE_ORDER_NO_ALLOT = "10";
    /** 已分配 */
    public static final String STATE_ORDER_ALLOT = "20";

    /**
     * 还款金额
     */
    private Double repayAmount;

    /**
     * 还款时间
     */
    private Date repayTime;

    /**
     * 还款状态 10-已还款 20-未还款
     */
    private String state;

    /**
     * 逾期罚金
     */
    private Double penaltyAmount;

    /**
     * 逾期天数
     */
    private String penaltyDay;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 利息
     */
    private Double fee;

    /**
     * 放款本金
     */
    private Double realAmount;

    /**
     * 还款计划id
     */
    private Long borrowRepayId;

    /**
     * 借款金额
     */
    private Double borrowAmount;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 银行卡号
     */
    private String cardNo;

    /**
     * 是否复借
     */
    private String again;

    /**
     * 借款金额
     */
    private Double amount;

    /**
     * 借款期限(天)
     */
    private String timeLimit;

    /**
     * 借款中文描述
     */
    private String stateStr;

    private Double repayTotal;

    public Double getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(Double repayAmount) {
        this.repayAmount = repayAmount;
    }

    public Date getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public void setState(String state) {
        this.state = state;
    }

    public Double getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(Double penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public String getPenaltyDay() {
        return penaltyDay;
    }

    public void setPenaltyDay(String penaltyDay) {
        this.penaltyDay = penaltyDay;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getBorrowRepayId() {
        return borrowRepayId;
    }

    public void setBorrowRepayId(Long borrowRepayId) {
        this.borrowRepayId = borrowRepayId;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Double getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(Double realAmount) {
        this.realAmount = realAmount;
    }

    public Double getBorrowAmount() {
        return borrowAmount;
    }

    public void setBorrowAmount(Double borrowAmount) {
        this.borrowAmount = borrowAmount;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getAgain() {
        return again;
    }

    public void setAgain(String again) {
        this.again = again;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }
    public String getStateStr() {
        this.stateStr = BorrowModel.manageConvertBorrowState(this.getState());
        return stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }

    public Double getRepayTotal() {
        this.repayTotal =  BigDecimalUtil.add(this.getRepayAmount(),this.getPenaltyAmount());
        return repayTotal;
    }

    public void setRepayTotal(Double repayTotal) {
        this.repayTotal = repayTotal;
    }

}