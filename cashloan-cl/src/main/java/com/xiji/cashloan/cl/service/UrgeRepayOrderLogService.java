package com.xiji.cashloan.cl.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.UrgeRepayOrder;
import com.xiji.cashloan.cl.domain.UrgeRepayOrderLog;
import com.xiji.cashloan.core.common.service.BaseService;

/**
 * 催款记录表Service
 * 
 * @author jdd
 * @version 1.0.0
 * @date 2017-03-07 14:28:22
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface UrgeRepayOrderLogService extends BaseService<UrgeRepayOrderLog, Long>{
	/**
	 * 催款记录信息
	 * @param params
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<UrgeRepayOrderLog> list(Map<String, Object> params, int current,
			int pageSize);
	/**
	 * 根据条件查询催款记录信息
	 * @param id
	 * @return
	 */
	List<UrgeRepayOrderLog> getLogByParam(Map<String, Object> params);
	
	/**
	 * 保存催款记录信息
	 * @param params
	 * @return
	 */
	int saveOrderInfo(UrgeRepayOrderLog  orderLog,UrgeRepayOrder order);

}
