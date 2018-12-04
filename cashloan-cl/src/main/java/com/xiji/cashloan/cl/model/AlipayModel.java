package com.xiji.cashloan.cl.model;

import java.util.Date;


 /**
 * 支付宝查询解析后的信息实体
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/28
 * Copyright 杭州融都科技股份有限公司 金融创新事业部 cashloan  All Rights Reserved
 * 官方网站：www.xiji.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public class AlipayModel {
	 
	/**
	 * 转账账户
	 */
	private String account;
	/**
	 * 转账金额
	 */
	private String amount;
	/**
	 * 转账备注
	 */
	private String remark;
	/**
	 * 转账时间
	 */
	private Date repayTime;
	/**
	 * 流水号
	 */
	private String serialNumber;
 
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public Date getRepayTime() {
		return repayTime;
	}
	public void setRepayTime(Date repayTime) {
		this.repayTime = repayTime;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	 
}
