package com.xiji.cashloan.cl.model;

import com.xiji.cashloan.cl.domain.ProfitCashLog;
/**
 * @author wnb
 * @date 2018/11/30
 * @version 1.0.0
 */
public class ManageCashLogModel extends ProfitCashLog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userName;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
