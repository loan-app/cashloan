package com.rongdu.cashloan.cl.service;

import java.util.Date;
import java.util.Map;

import com.github.pagehelper.Page;
import com.rongdu.cashloan.cl.domain.ProfitLog;
import com.rongdu.cashloan.cl.model.ManageCashLogModel;
import com.rongdu.cashloan.cl.model.ProfitLogModel;
import com.rongdu.cashloan.core.common.service.BaseService;

/**
 * 分润记录Service
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-02-18 17:04:10
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface ProfitLogService extends BaseService<ProfitLog, Long>{

	/**
	 * 邀请明细
	 * @param searchMap
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<ProfitLogModel> page(Map<String, Object> searchMap, int current,
			int pageSize);

	/**
	 * 查询提现记录
	 * @param phone
	 * @param userName 
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<ManageCashLogModel> findCashLog(String phone, String userName, int current, int pageSize);
	
	/**
	 * 管理员查询提现记录
	 * @param userId
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<ManageCashLogModel> findCashLog(String userName, int current, int pageSize);

	int save(long borrowId,Date date);
}
