package com.xiji.cashloan.cl.service;


import java.util.Map;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.model.DayPassApr;
import com.xiji.cashloan.cl.model.SystemDayData;

/**
 * 平台数据日报
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 * Copyright 杭州融都科技股份有限公司 现金贷  All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface SystemRcService {

	/**
	 * 平台数据日报
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<SystemDayData> findDayData(Map<String,Object> params, Integer current, Integer pageSize);
	
	/**
	 * 每日通过率
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<DayPassApr> findDayApr(Map<String,Object> params, Integer current, Integer pageSize);
}
