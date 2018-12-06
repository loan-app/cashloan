package com.xiji.cashloan.cl.service;


import java.util.Map;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.model.DayNeedAmountModel;
import com.xiji.cashloan.cl.model.ExpendDetailModel;
import com.xiji.cashloan.cl.model.IncomeAndExpendModel;
import com.xiji.cashloan.cl.model.IncomeDetailModel;

/**
 * 统计管理
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface StatisticManageService {

	/**
	 * 每日收支记录
	 * @return
	 */
	Page<IncomeAndExpendModel> repayIncomeAndExpend(Map<String, Object> params,Integer current,Integer pageSize);
	
	/**
	 * 每日未还本金
	 * @return
	 */
	Page<DayNeedAmountModel> dayNeedAmount(Map<String, Object> params,Integer current,Integer pageSize);
	
	/**
	 * 收入明细
	 * @return
	 */
	Page<IncomeDetailModel> incomeDetail(Map<String, Object> params,Integer current,Integer pageSize);
	
	/**
	 * 支出明细
	 * @return
	 */
	Page<ExpendDetailModel> expendDetail(Map<String, Object> params,Integer current,Integer pageSize);
	
	/**
	 * 收入总额
	 * @param params
	 * @return
	 */
	Double incomeSum(Map<String,Object> params);
	
	/**
	 * 支出总额
	 * @param params
	 * @return
	 */
	Double expendSum(Map<String,Object> params);
}
