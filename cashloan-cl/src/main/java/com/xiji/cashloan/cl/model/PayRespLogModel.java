package com.xiji.cashloan.cl.model;

import com.xiji.cashloan.cl.domain.PayRespLog;
/**
 * @author wnb
 * @date 2018/11/30
 * @version 1.0.0
 */
public class PayRespLogModel extends PayRespLog{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 同步返回
	 */
	public static final int RESP_LOG_TYPE_RETURN = 1;
	
	/**
	 * 异步响应
	 */
	public static final int RESP_LOG_TYPE_NOTIFY = 2;
	
	public PayRespLogModel(String orderNo, Integer type, String params) {
		super(orderNo, type, params);
	}
	
	
	
	
}
