package com.xiji.cashloan.rc.model;

import com.xiji.cashloan.rc.domain.TppBusiness;

/**
 * 第三方征信接口信息Model
 * 
 * @author zlh
 * @version 1.0.0
 * @date 2017-03-14 13:41:57
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 创新一部：rdc@xiji.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public class TppBusinessModel extends TppBusiness {

	private static final long serialVersionUID = 1L;
	
	public static final String BUS_NID_QCRISK = "QcRisk";
	
	public static final String BUS_NID_TONGDUN = "TongdunApply";

	/**
	 * 第三方Nid
	 */
	private String tppNid;

	/**
	 * 获取第三方Nid
	 * 
	 * @return tppNid
	 */
	public String getTppNid() {
		return tppNid;
	}

	/**
	 * 设置第三方Nid
	 * 
	 * @param tppNid
	 */
	public void setTppNid(String tppNid) {
		this.tppNid = tppNid;
	}

}