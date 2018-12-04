package com.xiji.cashloan.cl.model;

import com.xiji.cashloan.cl.domain.ProfitAgent;

/**
 * @author wnb
 * @date 2018/11/28
 * @version 1.0.0
 */
public class ManageAgentListModel extends ProfitAgent {

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
