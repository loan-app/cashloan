package com.xiji.cashloan.cl.model.pay.lianlian;

/**
 * 连连支付 分期付 - 还款计划
 * 
 * @author gc
 * @version 1.0.0
 * @date 2017年3月12日 下午3:36:46
 *
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public class RepaymentPlan {
	/**
	 * 还款时间
	 */
	private String date;

	/**
	 * 还款金额
	 */
	private String amount;

	/**
	 * 获取还款时间
	 * 
	 * @return date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * 设置还款时间
	 * 
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * 获取还款金额
	 * 
	 * @return amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * 设置还款金额
	 * 
	 * @param amount
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

}
