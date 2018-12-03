package com.rongdu.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.rongdu.cashloan.cl.domain.BorrowRepay;
import com.rongdu.cashloan.cl.model.BorrowRepayModel;
import com.rongdu.cashloan.cl.model.ManageBRepayModel;
import com.rongdu.cashloan.cl.model.ManageBorrowModel;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;

/**
 * 还款计划Dao
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-02-14 13:42:32
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface BorrowRepayMapper extends BaseMapper<BorrowRepay,Long> {

	int updateByBorrowId(Map<String, Object> repayMap);
	/**
	 * 后台还款记录列表 
	 * @param params
	 * @return
	 */
	List<ManageBRepayModel> listModel(Map<String, Object> params);
	
	/**
	 * 逾期更新数据
	 * @param data
	 * @return
	 */
	int updateLate(BorrowRepay data);
	/**
	 * 
	 * @param paramMap
	 */
	int updateParam(Map<String, Object> paramMap);
	/**
	 * 催款管理
	 * @param params
	 * @return
	 */
	List<ManageBorrowModel> listRepayModel(Map<String, Object> params);
	/**
	 * 逾期未入催
	 * @param params
	 * @return
	 */
	List<ManageBorrowModel> listModelNotUrge(Map<String, Object> params);
	/**
	 * 查询所有
	 * @param searchMap
	 * @return
	 */
	List<BorrowRepayModel> listSelModel(Map<String, Object> searchMap);

	/**
	 * 查询借款成功未还款还款已过还款时间记录(放款成功及逾期但未还款的)
	 * 
	 * @param paramMap
	 * @return
	 */
	List<BorrowRepay> findUnRepay(Map<String, Object> paramMap);
	/**
	 * 查询还款
	 * @param borrowId
	 * @return
	 */
	BorrowRepayModel findOverdue(long borrowId);
    
	/**
	 * 查询当天还款计划总额
	 * 
	 * @param map
	 * @return
	 */
	double findRepayTotal();
	
}
