package com.xiji.cashloan.rc.model;

import com.xiji.cashloan.rc.domain.TppBusiness;

/**
 * 第三方征信接口信息Model
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 *
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public class TppBusinessModel extends TppBusiness {

	private static final long serialVersionUID = 1L;
	
	public static final String BUS_NID_QCRISK = "QcRisk";
	
	public static final String BUS_NID_TONGDUN = "TongdunApply";

	public static final String BUS_NID_XWLD = "XYXWLD";

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