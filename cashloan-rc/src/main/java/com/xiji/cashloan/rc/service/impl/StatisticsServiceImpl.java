package com.xiji.cashloan.rc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.rc.domain.Statistics;
import com.xiji.cashloan.rc.service.StatisticsService;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.rc.mapper.StatisticsMapper;


/**
 * 风控数据统计分类ServiceImpl
 * 
 * @author caitt
 * @version 1.0.0
 * @date 2017-04-13 17:52:52
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 创新一部：rdc@xiji.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("statisticsService")
public class StatisticsServiceImpl extends BaseServiceImpl<Statistics, Long> implements StatisticsService {
	
    @Resource
    private StatisticsMapper statisticsMapper;

	@Override
	public BaseMapper<Statistics, Long> getMapper() {
		return statisticsMapper;
	}

	@Override
	public Page<Statistics> page(Map<String, Object> params, int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		Page<Statistics> page = (Page<Statistics>) statisticsMapper.listSelective(params);
		return page;
	}

	@Override
	public List<Statistics> listAll() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("state", "10");
		return statisticsMapper.listSelective(params);
	}
	
}