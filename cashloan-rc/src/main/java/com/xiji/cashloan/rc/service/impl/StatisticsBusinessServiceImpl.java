package com.xiji.cashloan.rc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.rc.domain.StatisticsBusiness;
import com.xiji.cashloan.rc.mapper.StatisticsBusinessMapper;
import com.xiji.cashloan.rc.service.StatisticsBusinessService;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


/**
 * 风控数据统计接口ServiceImpl
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 创新一部：rdc@xiji.com
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
	public Page<StatisticsBusiness> page(Map<String, Object> params, int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		Page<StatisticsBusiness> page = (Page<StatisticsBusiness>) statisticsBusinessMapper.listSelective(params);
		return page;
	}

	@Override
	public List<StatisticsBusiness> listSelective(Map<String, Object> params) {
		return statisticsBusinessMapper.listSelective(params);
	}
	
}