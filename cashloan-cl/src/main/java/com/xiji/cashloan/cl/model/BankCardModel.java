package com.xiji.cashloan.cl.model;

import com.xiji.cashloan.cl.domain.BankCard;
/**
 * @author wnb
 * @date 2018/11/30
 * @version 1.0.0
 */
public class BankCardModel extends BankCard{

	private static final long serialVersionUID = 1L;
	/**
	 * 能替换绑卡
	 */
	public static final String STATE_ENABLE = "10";
	/**
	 * 不能替换绑卡
	 */
	public static final String STATE_DISABLE = "20";
	
	/**
	 * 能否替换绑卡（是否存在未完成借款）
	 */
	private String changeAble;

	public String getChangeAble() {
		return changeAble;
	}

	public void setChangeAble(String changeAble) {
		this.changeAble = changeAble;
	}

}
