package com.rongdu.cashloan.cl.service;


import java.util.Map;

import com.github.pagehelper.Page;
import com.rongdu.cashloan.cl.model.DayPassApr;
import com.rongdu.cashloan.cl.model.SystemDayData;

/**
 * 平台数据日报
 * @author caitt
 * @version 1.0
 * @date 2017年3月20日下午4:56:21
 * Copyright 杭州融都科技股份有限公司 现金贷  All Rights Reserved
 * 官方网站：www.erongdu.com
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
	Page<SystemDayData> findDayData(Map<String,Object> params,Integer current,Integer pageSize);
	
	/**
	 * 每日通过率
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<DayPassApr> findDayApr(Map<String,Object> params,Integer current,Integer pageSize);
}
