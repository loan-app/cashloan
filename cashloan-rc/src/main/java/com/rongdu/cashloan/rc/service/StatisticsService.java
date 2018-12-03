package com.rongdu.cashloan.rc.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.rongdu.cashloan.core.common.service.BaseService;
import com.rongdu.cashloan.rc.domain.Statistics;

/**
 * 风控数据统计分类Service
 * 
 * @author caitt
 * @version 1.0.0
 * @date 2017-04-13 17:52:52
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 创新一部：rdc@erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface StatisticsService extends BaseService<Statistics, Long>{

	/**
	 * 风控数据统计分类分页查询
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<Statistics> page(Map<String, Object> params, int currentPage, int pageSize);
	
	/**
	 * 查询有效数据分类--下拉框使用
	 * @param params
	 * @return
	 */
	List<Statistics> listAll();
}
