package com.xiji.cashloan.cl.model;

import com.xiji.cashloan.cl.domain.ManualReviewOrder;
import com.xiji.cashloan.core.model.BorrowModel;

import java.util.Date;

/**
 * 审核订单实体
 * Created by szb on 18/12/16.
 */
public class ManualReviewOrderModel extends ManualReviewOrder {

    /**
     * 10未分配;11待审核;20审核通过;30审核拒绝
     */

    /** 未分配审核人员*/
    public static final String STATE_ORDER_PRE = "10";
    /** 待审核 */
    public static final String STATE_ORDER_WAIT = "11";
    /** 审核通过*/
    public static final String STATE_ORDER_PASS = "20";
    /** 审核拒绝*/
    public static final String STATE_ORDER_REFUSED = "30";

    /**
     * 实际到账金额
     */
    private Double realAmount;

    /**
     * 利息
     */
    private Double interest;

    /**
     * 信息认证费
     */
    private Double infoAuthFee;

    /**
     * 服务费
     */
    private Double serviceFee;
    /**
     * 综合费用
     */
    private Double fee;

    /**
     * 借款订单状态
     */
    private String borrowState;

    /**
     * 借款用户id
     */
    private Long borrowUserId;

    /**
     * 借款订单号
     */
    private String orderNo;

    /**
     * 借款金额
     */
    private Double amount;

    /**
     * 借款期限
     */
    private String timeLimit;

    /**
     * 借款时间
     */
    private Date borrowTime;

    /**
     * 借款状态描述
     *
     */
    private String stateStr;

    /**
     * 渠道名称
     */
    private String channelName;

    public Double getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(Double realAmount) {
        this.realAmount = realAmount;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Double getInfoAuthFee() {
        return infoAuthFee;
    }

    public void setInfoAuthFee(Double infoAuthFee) {
        this.infoAuthFee = infoAuthFee;
    }

    public Double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public String getBorrowState() {
        return borrowState;
    }

    public void setBorrowState(String borrowState) {
        this.borrowState = borrowState;
    }

    public Long getBorrowUserId() {
        return borrowUserId;
    }

    public void setBorrowUserId(Long borrowUserId) {
        this.borrowUserId = borrowUserId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public Date getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
    }

    public String getStateStr() {
        this.stateStr = BorrowModel.manageConvertBorrowState(this.getBorrowState());
        return stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
