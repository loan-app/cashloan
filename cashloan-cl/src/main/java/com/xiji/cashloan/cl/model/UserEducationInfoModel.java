package com.xiji.cashloan.cl.model;

import com.xiji.cashloan.cl.domain.UserEducationInfo;
/**
 * @author wnb
 * @date 2018/11/30
 * @version 1.0.0
 */
public class UserEducationInfoModel extends UserEducationInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String phone;
	
	private String stateStr;

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the stateStr
	 */
	public String getStateStr() {
		return stateStr;
	}

	/**
	 * @param stateStr the stateStr to set
	 */
	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}

}
