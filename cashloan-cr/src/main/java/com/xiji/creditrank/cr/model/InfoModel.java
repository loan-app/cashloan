package com.xiji.creditrank.cr.model;

import java.util.List;

import com.xiji.creditrank.cr.domain.Info;

/**
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 * Copyright 杭州融都科技股份有限公司 资产风控系统  All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@SuppressWarnings("serial")
public class InfoModel extends Info {

	@SuppressWarnings("rawtypes")
	private List children;

	/**
	 * 获取children
	 * @return children
	 */
	@SuppressWarnings("rawtypes")
	public List getChildren() {
		return children;
	}

	/**
	 * 设置children
	 * @param children
	 */
	@SuppressWarnings("rawtypes")
	public void setChildren(List children) {
		this.children = children;
	}

}
