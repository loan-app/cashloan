package com.xiji.cashloan.cl.model;

import com.xiji.cashloan.cl.domain.UserContacts;

/**
 * 通讯录
 *
 * @author wnb
 * @date 2018/11/30
 * @version 1.0.0
 * Copyright 杭州融都科技股份有限公司 xiji All Rights Reserved
 * 官方网站：www.xiji.com
 * 创新一部：rdc@xiji.com
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
