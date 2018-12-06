package com.xiji.cashloan.cl.model;

/**
 * 收入明细
 * @author wnb
 * @date 2018/11/30
 * @version 1.0.0
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public class IncomeDetailModel {

	/**
	 * 姓名
	 */
	private String realName;
	
	/**
	 * 金额
	 */
	private String amount;
	
	/**
	 * 日期
	 */
	private String date;

	/** 
	 * 获取姓名
	 * @return realName
	 */
	public String getRealName() {
		return realName;
	}

	/** 
	 * 设置姓名
	 * @param realName
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/** 
	 * 获取金额
	 * @return amount
	 */
	public String getAmount() {
		return amount;
	}

	/** 
	 * 设置金额
	 * @param amount
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/** 
	 * 获取日期
	 * @return date
	 */
	public String getDate() {
		return date;
	}

	/** 
	 * 设置日期
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
