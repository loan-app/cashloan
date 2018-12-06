package com.xiji.cashloan.rc.model;

import com.xiji.cashloan.rc.domain.Tpp;

/**
 * 第三方征信信息Model - 后台管理  
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 *
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public class ManageTppModel extends Tpp {

	private static final long serialVersionUID = 1L;

	/**
	 * 状态中文描述
	 */
	private String stateStr;

	/**
	 * 获取状态中文描述
	 * 
	 * @return stateStr
	 */
	public String getStateStr() {
		return stateStr;
	}

	/**
	 * 设置状态中文描述
	 * 
	 * @param stateStr
	 */
	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}

}
