package com.rongdu.cashloan.cl.model;

import com.rongdu.cashloan.cl.domain.UserContacts;

/**
 * 通讯录
 * @author yhjiang
 * @version 1.0
 * @date 2017年9月19日
 * Copyright 杭州融都科技股份有限公司 erongdu All Rights Reserved
 * 官方网站：www.erongdu.com
 * 创新一部：rdc@erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public class UserContactsModel extends UserContacts {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 通话次数
	 */
	private Integer totalCount;
	
	/**
	 * 通话总时长（秒）
	 */
	private Integer sumDuration;

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getSumDuration() {
		return sumDuration;
	}

	public void setSumDuration(Integer sumDuration) {
		this.sumDuration = sumDuration;
	}
	
}
