package com.xiji.cashloan.cl.model;

import com.xiji.cashloan.cl.domain.BorrowRepay;
import tool.util.BigDecimalUtil;

import java.util.Date;
/**
 * @author wnb
 * @date 2018/11/30
 * @version 1.0.0
 */
public class ManageBRepayModel extends BorrowRepay {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 真实姓名
	 */
	private String realName;
 
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 订单号
	 */
	private String orderNo;

	/**
	 * 借款金额
	 */
	private Double borrowAmount;

	/**
	 * 还款金额
	 */
	private Double repayAmount;
	
	/**
	 * 还款总额
	 */
	private Double repayTotal;
	
	/**
	 * 借款时间
	 */
	private Date borrowTime;

	/**
	 * 放款本金
	 */
	private Double realAmount;

	/**
	 *
	 * 利息
	 *
	 */
	private Double fee;

	/**
	 *
	 * 渠道名称
	 */
	private String channelName;

	/**
	 * 还款状态
	 */
	private String stateStr;
	/**
	 * 分配状态 10-未分配 20-已分配
	 */
	private String allotState;


	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Double getBorrowAmount() {
		return borrowAmount;
	}

	public void setBorrowAmount(Double borrowAmount) {
		this.borrowAmount = borrowAmount;
	}

	public Double getRepayAmount() {
		return repayAmount;
	}

	public void setRepayAmount(Double repayAmount) {
		this.repayAmount = repayAmount;
	}

	public Double getRepayTotal() {
		this.repayTotal =  BigDecimalUtil.add(this.getRepayAmount(),this.getPenaltyAmout());
		return repayTotal;
	}

	public void setRepayTotal(Double repayTotal) {
		this.repayTotal = repayTotal;
	}

	public Date getBorrowTime() {
		return borrowTime;
	}

	public void setBorrowTime(Date borrowTime) {
		this.borrowTime = borrowTime;
	}


	public Double getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(Double realAmount) {
		this.realAmount = realAmount;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getStateStr() {
		return stateStr;
	}

	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}

	public String getAllotState() {
		return allotState;
	}

	public void setAllotState(String allotState) {
		this.allotState = allotState;
	}
}
