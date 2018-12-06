package com.xiji.cashloan.cl.service;

import java.util.Date;

/**
 * 运营商信息保存
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface OperatorService {

	/**
	 * 运营商信息保存
	 * @param res
	 * @param userId
	 * @param createTime
	 * @param mobile
	 * @param reqLogId
	 */
	void saveOperatorInfos(String res, Long userId, Date createTime, String mobile, Long reqLogId);
}
