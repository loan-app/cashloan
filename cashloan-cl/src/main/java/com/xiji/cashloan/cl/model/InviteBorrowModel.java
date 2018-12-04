package com.xiji.cashloan.cl.model;

import com.xiji.cashloan.cl.domain.UserInvite;
/**
 * @author wnb
 * @date 2018/11/30
 * @version 1.0.0
 */
public class InviteBorrowModel extends UserInvite {

	private static final long serialVersionUID = 1L;

	private double borrowAmout;
	
	private double penaltyAmout;
	
	private double repayAmount;
	
	private double agentAmount;

	/**
	 * @return the borrowAmout
	 */
	public double getBorrowAmout() {
		return borrowAmout;
	}

	/**
	 * @param borrowAmout the borrowAmout to set
	 */
	public void setBorrowAmout(double borrowAmout) {
		this.borrowAmout = borrowAmout;
	}

	/**
	 * @return the penaltyAmout
	 */
	public double getPenaltyAmout() {
		return penaltyAmout;
	}

	/**
	 * @param penaltyAmout the penaltyAmout to set
	 */
	public void setPenaltyAmout(double penaltyAmout) {
		this.penaltyAmout = penaltyAmout;
	}

	/**
	 * @return the repayAmount
	 */
	public double getRepayAmount() {
		return repayAmount;
	}

	/**
	 * @param repayAmount the repayAmount to set
	 */
	public void setRepayAmount(double repayAmount) {
		this.repayAmount = repayAmount;
	}

	/**
	 * @return the agentAmount
	 */
	public double getAgentAmount() {
		return agentAmount;
	}

	/**
	 * @param agentAmount the agentAmount to set
	 */
	public void setAgentAmount(double agentAmount) {
		this.agentAmount = agentAmount;
	}
	
	
}
