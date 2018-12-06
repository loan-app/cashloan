package com.xiji.creditrank.cr.model;

import com.xiji.creditrank.cr.domain.Rank;


/**
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 * Copyright   All Rights Reserved
 *
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
