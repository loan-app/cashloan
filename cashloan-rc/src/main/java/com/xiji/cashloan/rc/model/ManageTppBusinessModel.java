package com.xiji.cashloan.rc.model;

import com.xiji.cashloan.rc.domain.TppBusiness;

/**
 * 第三方征信接口信息Model - 后台管理  
 * 
 * @author gc
 * @version 1.0.0
 * @date 2017年3月18日 上午11:24:23
 * Copyright 杭州融都科技股份有限公司 Arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 创新一部：rdc@xiji.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public class ManageTppBusinessModel extends TppBusiness {

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
