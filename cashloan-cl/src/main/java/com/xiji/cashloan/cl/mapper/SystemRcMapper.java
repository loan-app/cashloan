package com.xiji.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.cl.model.DayPassApr;
import com.xiji.cashloan.cl.model.SystemDayData;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

/**
 * 平台数据日报
 * @author wnb
 * @version 1.0.0
 * @date 2018/12/03
 * Copyright 杭州融都科技股份有限公司 现金贷  All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface SystemRcMapper {

	/**
	 * 平台数据日报
	 * @return
	 */
	List<SystemDayData> dayData(Map<String,Object> params);
	
	/**
	 * 每日通过率
	 */
	List<DayPassApr> dayApr(Map<String,Object> params);
}
