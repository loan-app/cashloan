package com.xiji.cashloan.cl.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.model.OverdueAnalisisModel;
import com.xiji.cashloan.cl.model.RepayAnalisisModel;

/**
 * 运营分析
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface SystemAnalysisService {

	/**
	 * 每月还款统计
	 * @param params
	 * @return
	 */
	List<RepayAnalisisModel> monthRepayAnalisis(Map<String,Object> params);
	
	/**
	 * 每日还款统计
	 * @param params
	 * @return
	 */
	List<RepayAnalisisModel> dayRepayAnalisis(Map<String,Object> params);

	/**
	 * 每月逾期统计
	 * @param params
	 * @return
	 */
	Page<OverdueAnalisisModel> overdueAnalisis(Map<String,Object> params,Integer current,Integer pageSize);
}
