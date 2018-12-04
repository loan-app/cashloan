package com.xiji.cashloan.rule.model;

import com.xiji.cashloan.rule.domain.BorrowRuleResult;

/**
 *
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 *
 *
 */
public class ManageReviewModel extends BorrowRuleResult{

	private static final long serialVersionUID = 1L;
	
	private String ruleName;

	/**
	 * @return the ruleName
	 */
	public String getRuleName() {
		return ruleName;
	}

	/**
	 * @param ruleName the ruleName to set
	 */
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

}
