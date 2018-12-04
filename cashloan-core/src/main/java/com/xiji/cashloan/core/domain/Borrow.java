package com.xiji.cashloan.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 借款信息表实体
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 public class Borrow implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	private Long id;

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 订单号
	 */
	protected String orderNo;

	/**
	 * 借款金额
	 */
	private Double amount;

	/**
	 * 实际到账金额
	 */
	private Double realAmount;

	/**
	 * 综合费用(借款利息+服务费+信息认证费)
	 */
	private Double fee;

	/**
	 * 订单生成时间
	 */
	protected Date createTime;

	/**
	 * 借款期限(天)
	 */
	private String timeLimit;

	/**
	 * 订单状态 10 申请成功待审核 20自动审核通过 21自动审核不通过 22自动审核未决待人工复审 26人工复审通过 27人工复审不通过
	 * 30放款成功 40还款成功 50逾期 90坏账
	 */
	private String state;

	/**
	 * 收款银行卡关联id
	 */
	private Long cardId;

	/**
	 * 服务费
	 */
	private Double serviceFee;

	/**
	 * 信息认证费
	 */
	private Double infoAuthFee;

	/**
	 * 借款利息
	 */
	private Double interest;

	/**
	 * 客户端 默认10-移动app
	 */
	private String client;

	/**
	 * 借款地址
	 */
	private String address;

	/**
	 * 借款坐标
	 */
	private String coordinate;
	
	/**
	 * 备注、审核说明
	 */
	private String remark;
	
	/**
	 * 借款ip
	 */
	private String ip;
	
	/**
	 * 首再贷标识  10首贷  20再贷
	 */
	private String again;
	
	/**
	 * 电子签章签署记录ID
	 */
	private String signServiceId;
	
	/**
	 * 附属状态
	 */
	private String subState;
	
	public String getSubState() {
		return subState;
	}

	public void setSubState(String subState) {
		this.subState = subState;
	}

	public Borrow() {
		super();
	}

	public Borrow(Long userId, Double amount, String timeLimit, Long cardId, String client, String address,
			String coordinate,String ip) {
		super();
		this.userId = userId;
		this.amount = amount;
		this.timeLimit = timeLimit;
		this.cardId = cardId;
		this.client = client;
		this.address = address;
		this.coordinate = coordinate;
		this.ip = ip;
	}

	/**
	 * 获取主键Id
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置主键Id
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取用户id
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置用户id
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取订单号
	 * @return orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * 设置订单号
	 * @param orderNo
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 获取借款金额
	 * @return amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * 设置借款金额
	 * @param amount
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * 获取实际到账金额
	 * @return realAmount
	 */
	public Double getRealAmount() {
		return realAmount;
	}

	/**
	 * 设置实际到账金额
	 * @param realAmount
	 */
	public void setRealAmount(Double realAmount) {
		this.realAmount = realAmount;
	}

	/**
	 * 获取综合费用(借款利息+服务费+信息认证费)
	 * @return fee
	 */
	public Double getFee() {
		return fee;
	}

	/**
	 * 设置综合费用(借款利息+服务费+信息认证费)
	 * @param fee
	 */
	public void setFee(Double fee) {
		this.fee = fee;
	}

	/**
	 * 获取订单生成时间
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置订单生成时间
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取借款期限(天)
	 * @return timeLimit
	 */
	public String getTimeLimit() {
		return timeLimit;
	}

	/**
	 * 设置借款期限(天)
	 * @param timeLimit
	 */
	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	/**
	 * 获取订单状态10申请成功待审核20自动审核通过21自动审核不通过22自动审核未决待人工复审26人工复审通过27人工复审不通过30放款成功40还款成功50逾期90坏账
	 * @return state
	 */
	public String getState() {
		return state;
	}

	/**
	 * 设置订单状态10申请成功待审核20自动审核通过21自动审核不通过22自动审核未决待人工复审26人工复审通过27人工复审不通过30放款成功40还款成功50逾期90坏账
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 获取收款银行卡关联id
	 * @return cardId
	 */
	public Long getCardId() {
		return cardId;
	}

	/**
	 * 设置收款银行卡关联id
	 * @param cardId
	 */
	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	/**
	 * 获取服务费
	 * @return serviceFee
	 */
	public Double getServiceFee() {
		return serviceFee;
	}

	/**
	 * 设置服务费
	 * @param serviceFee
	 */
	public void setServiceFee(Double serviceFee) {
		this.serviceFee = serviceFee;
	}

	/**
	 * 获取信息认证费
	 * @return infoAuthFee
	 */
	public Double getInfoAuthFee() {
		return infoAuthFee;
	}

	/**
	 * 设置信息认证费
	 * @param infoAuthFee
	 */
	public void setInfoAuthFee(Double infoAuthFee) {
		this.infoAuthFee = infoAuthFee;
	}

	/**
	 * 获取借款利息
	 * @return interest
	 */
	public Double getInterest() {
		return interest;
	}

	/**
	 * 设置借款利息
	 * @param interest
	 */
	public void setInterest(Double interest) {
		this.interest = interest;
	}

	/**
	 * 获取客户端默认10-移动app
	 * @return client
	 */
	public String getClient() {
		return client;
	}

	/**
	 * 设置客户端默认10-移动app
	 * @param client
	 */
	public void setClient(String client) {
		this.client = client;
	}

	/**
	 * 获取借款地址
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置借款地址
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取借款坐标
	 * @return coordinate
	 */
	public String getCoordinate() {
		return coordinate;
	}

	/**
	 * 设置借款坐标
	 * @param coordinate
	 */
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * 获取备注、审核说明
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置备注、审核说明
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取借款ip
	 * @return 借款ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * 设置借款ip
	 * @param 借款ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 获取电子签章签署记录id
	 * @return signServiceId
	 */
	public String getSignServiceId() {
		return signServiceId;
	}

	/**
	 * 设置电子签章签署记录id
	 * @param signServiceId
	 */
	public void setSignServiceId(String signServiceId) {
		this.signServiceId = signServiceId;
	}

	/**
	 * 获取首再贷标识10首贷20再贷
	 * @return again
	 */
	public String getAgain() {
		return again;
	}

	/**
	 * 设置首再贷标识10首贷20再贷
	 * @param again
	 */
	public void setAgain(String again) {
		this.again = again;
	}

}