package com.xiji.creditrank.cr.model;

import com.xiji.creditrank.cr.domain.Rank;


/** 
 * @author lyang
 * @version 1.0
 * @date 2017-1-16 下午4:35:03
 * Copyright 杭州融都科技股份有限公司 资产风控系统  All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@SuppressWarnings("serial")
public class RankModel extends Rank{

	private String num;

	/**
	 * 获取num
	 * @return num
	 */
	public String getNum() {
		return num;
	}

	/**
	 * 设置num
	 * @param num
	 */
	public void setNum(String num) {
		this.num = num;
	}
}
