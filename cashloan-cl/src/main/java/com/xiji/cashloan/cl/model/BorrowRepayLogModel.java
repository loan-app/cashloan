package com.xiji.cashloan.cl.model;

import tool.util.DateUtil;

import com.xiji.cashloan.cl.domain.BorrowRepayLog;
/**
 * @author wnb
 * @date 2018/11/30
 * @version 1.0.0
 */
public class BorrowRepayLogModel extends BorrowRepayLog {

	private static final long serialVersionUID = 1L;

	/** 还款方式 - 代扣 */
	public static final String REPAY_WAY_CHARGE = "10";

	/** 还款方式 - 银行卡转账 */
	public static final String REPAY_WAY_TRANSFER = "20";

	/** 还款方式 - 支付宝转账 */
	public static final String REPAY_WAY_ALIPAY_TRANSFER = "30";
	
	/** 还款方式 - 认证支付 */
	public static final String  REPAY_WAY_CERTIFIED = "40";

	
	/**
	 * 还款时间
	 */
	private String repayTimeStr;
	
	/**
	 * 获取还款时间
	 * 
	 * @return repayTimeStr
	 */
	public String getRepayTimeStr() {
		this.repayTimeStr = DateUtil.dateStr(this.getRepayTime(),"yyyy-MM-dd HH:mm");
		return repayTimeStr;
	}

	/**
	 * 设置还款时间
	 * 
	 * @param repayTimeStr
	 */
	public void setRepayTimeStr(String repayTimeStr) {
		this.repayTimeStr = repayTimeStr;
	}
}
