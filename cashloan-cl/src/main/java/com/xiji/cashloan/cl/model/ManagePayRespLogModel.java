package com.xiji.cashloan.cl.model;

import com.xiji.cashloan.cl.domain.PayRespLog;

/**
 * @author wnb
 * @date 2018/11/30
 * @version 1.0.0
 */
public class ManagePayRespLogModel extends PayRespLog {

	private static final long serialVersionUID = 1L;

	public ManagePayRespLogModel(String orderNo, Integer type, String params) {
		super(orderNo, type, params);
	}
	
}
