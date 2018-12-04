package com.xiji.creditrank.cr.model;


import java.util.List;

import com.xiji.creditrank.cr.domain.CrResultDetail;

/**
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 *
 */
@SuppressWarnings("serial")
public class CrResultDetailModel extends CrResultDetail {
	
	
	public List<CrResultItemDetail> itemList;

	/** 
	 * 获取itemList
	 * @return itemList
	 */
	public List<CrResultItemDetail> getItemList() {
		return itemList;
	}

	/** 
	 * 设置itemList
	 * @param itemList
	 */
	public void setItemList(List<CrResultItemDetail> itemList) {
		this.itemList = itemList;
	}



	
}
