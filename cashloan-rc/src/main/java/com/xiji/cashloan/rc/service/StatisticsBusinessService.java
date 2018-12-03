package com.xiji.cashloan.rc.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.rc.domain.StatisticsBusiness;

/**
 * 风控数据统计接口Service
 * 
 * @author caitt
 * @version 1.0.0
 * @date 2017-04-13 17:57:55
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 创新一部：rdc@xiji.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface StatisticsBusinessService extends BaseService<StatisticsBusiness, Long> {

	/**
	 * 风控数据统计接口分页查询
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<StatisticsBusiness> page(Map<String, Object> params, int currentPage, int pageSize);
	
	/**
	 * 查询风控数据统计接口
	 * @param params
	 * @return
	 */
	List<StatisticsBusiness> listSelective(Map<String, Object> params);
}
