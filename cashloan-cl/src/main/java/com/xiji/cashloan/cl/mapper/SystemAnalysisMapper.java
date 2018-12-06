package com.xiji.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.cl.model.OverdueAnalisisModel;
import com.xiji.cashloan.cl.model.RepayAnalisisModel;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

/**
 * 运营分析
 * @author wnb
 * @version 1.0.0
 * @date 2018/12/03
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface SystemAnalysisMapper {
	
	/**
	 * 还款统计
	 * @param params
	 * @return
	 */
	List<RepayAnalisisModel> repayAnalisis(Map<String,Object> params);

	/**
	 * 每月逾期统计
	 * @param params
	 * @return
	 */
	List<OverdueAnalisisModel> overdueAnalisis(Map<String,Object> params);

	/**
	 * 还款笔数
	 * @param map 
	 */
	String repayCount(Map<String, Object> map);

	/**
	 * 逾期还款笔数
	 * @param map
	 * @return
	 */
	String overdueCount(Map<String, Object> map);

	/**
	 * 还款金额
	 * @param map
	 * @return
	 */
	String repayAmt(Map<String, Object> map);

	/**
	 * 逾期还款金额
	 * @param map
	 * @return
	 */
	String penaltyRepayAmt(Map<String, Object> map);

	/**
	 * 得到有数据的月份
	 * @return
	 */
	List<String> mouthList();
}
