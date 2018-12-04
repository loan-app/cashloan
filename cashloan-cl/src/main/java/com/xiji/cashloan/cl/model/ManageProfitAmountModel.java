package com.xiji.cashloan.cl.model;

import com.xiji.cashloan.cl.domain.ProfitAmount;

/**
 * @author wnb
 * @date 2018/11/30
 * @version 1.0.0
 */
public class ManageProfitAmountModel extends ProfitAmount{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userName;
	
	private String realName;

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

	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * @param realName the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
}
