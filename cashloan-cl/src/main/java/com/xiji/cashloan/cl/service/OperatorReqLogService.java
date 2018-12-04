package com.xiji.cashloan.cl.service;

import java.util.Map;

import com.xiji.cashloan.cl.domain.OperatorReqLog;
import com.xiji.cashloan.core.common.service.BaseService;

/**
 * 运营商认证中间表Service
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface OperatorReqLogService extends BaseService<OperatorReqLog, Long>{

	public OperatorReqLog findSelective(Map<String, Object> paramMap);
	
	/**
	 * 查询用户是否可以进行运营商认证
	 * @param userId
	 * @return
	 */
	public boolean checkUserOperator(long userId);

	/**
	 * 查询最后一条符合条件的记录
	 * @param paramMap
	 * @return
	 */
	public OperatorReqLog findLastRecord(Map<String, Object> paramMap);
}
