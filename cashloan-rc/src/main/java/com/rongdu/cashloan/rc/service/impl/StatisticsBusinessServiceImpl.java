package com.rongdu.cashloan.rc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.service.impl.BaseServiceImpl;
import com.rongdu.cashloan.rc.domain.StatisticsBusiness;
import com.rongdu.cashloan.rc.mapper.StatisticsBusinessMapper;
import com.rongdu.cashloan.rc.service.StatisticsBusinessService;


/**
 * 风控数据统计接口ServiceImpl
 * 
 * @author caitt
 * @version 1.0.0
 * @date 2017-04-13 17:57:55
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 创新一部：rdc@erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("statisticsBusinessService")
public class StatisticsBusinessServiceImpl extends BaseServiceImpl<StatisticsBusiness, Long> implements StatisticsBusinessService {
	
    @Resource
    private StatisticsBusinessMapper statisticsBusinessMapper;

	@Override
	public BaseMapper<StatisticsBusiness, Long> getMapper() {
		return statisticsBusinessMapper;
	}

	@Override
	public Page<StatisticsBusiness> page(Map<String, Object> params,int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		Page<StatisticsBusiness> page = (Page<StatisticsBusiness>) statisticsBusinessMapper.listSelective(params);
		return page;
	}

	@Override
	public List<StatisticsBusiness> listSelective(Map<String, Object> params) {
		return statisticsBusinessMapper.listSelective(params);
	}
	
}