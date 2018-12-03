package com.rongdu.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.rongdu.cashloan.cl.model.DayNeedAmountModel;
import com.rongdu.cashloan.cl.model.ExpendDetailModel;
import com.rongdu.cashloan.cl.model.IncomeAndExpendModel;
import com.rongdu.cashloan.cl.model.IncomeDetailModel;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;

/**
 * 统计管理
 * @author caitt
 * @version 1.0
 * @date 2017年3月21日下午4:39:37
 * Copyright 杭州融都科技股份有限公司 现金贷  All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface StatisticManageMapper {

	/**
	 * 每日收支记录
	 * @return
	 */
	List<IncomeAndExpendModel> repayIncomeAndExpend(Map<String,Object> params);
	
	/**
	 * 每日未还本金
	 * @return
	 */
	List<DayNeedAmountModel> dayNeedAmount(Map<String,Object> params);
	
	/**
	 * 收入明细
	 * @return
	 */
	List<IncomeDetailModel> incomeDetail(Map<String,Object> params);
	
	/**
	 * 收入总额
	 * @param params
	 * @return
	 */
	Double incomeSum(Map<String,Object> params);
	
	/**
	 * 支出明细
	 * @return
	 */
	List<ExpendDetailModel> expendDetail(Map<String,Object> params);
	
	/**
	 * 支出总额
	 * @param params
	 * @return
	 */
	Double expendSum(Map<String,Object> params);
}
