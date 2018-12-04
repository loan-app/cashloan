package com.xiji.cashloan.cl.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.mapper.StatisticManageMapper;
import com.xiji.cashloan.cl.model.DayNeedAmountModel;
import com.xiji.cashloan.cl.model.ExpendDetailModel;
import com.xiji.cashloan.cl.model.IncomeAndExpendModel;
import com.xiji.cashloan.cl.model.IncomeDetailModel;
import com.xiji.cashloan.cl.service.StatisticManageService;

/**
 * 统计管理
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 * Copyright 杭州融都科技股份有限公司 现金贷  All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Service("statisticManageService")
public class StatisticManageServiceImpl implements StatisticManageService {

	@Resource
	private StatisticManageMapper statisticManageMapper;

	@Override
	public Page<IncomeAndExpendModel> repayIncomeAndExpend(Map<String, Object> params,Integer current,Integer pageSize) {
		PageHelper.startPage(current, pageSize);
		Page<IncomeAndExpendModel> page = (Page<IncomeAndExpendModel>) statisticManageMapper.repayIncomeAndExpend(params);
		return page;
	}

	@Override
	public Page<DayNeedAmountModel> dayNeedAmount(Map<String, Object> params,Integer current,Integer pageSize) {
		PageHelper.startPage(current, pageSize);
		Page<DayNeedAmountModel> page = (Page<DayNeedAmountModel>) statisticManageMapper.dayNeedAmount(params);
		return page;
	}

	@Override
	public Page<IncomeDetailModel> incomeDetail(Map<String, Object> params,Integer current, Integer pageSize) {
		PageHelper.startPage(current, pageSize);
		Page<IncomeDetailModel> page = (Page<IncomeDetailModel>) statisticManageMapper.incomeDetail(params);
		return page;
	}

	@Override
	public Page<ExpendDetailModel> expendDetail(Map<String, Object> params,Integer current, Integer pageSize) {
		PageHelper.startPage(current, pageSize);
		Page<ExpendDetailModel> page = (Page<ExpendDetailModel>) statisticManageMapper.expendDetail(params);
		return page;
	}

	@Override
	public Double incomeSum(Map<String, Object> params) {
		return statisticManageMapper.incomeSum(params);
	}

	@Override
	public Double expendSum(Map<String, Object> params) {
		return statisticManageMapper.expendSum(params);
	}
	
	
}
