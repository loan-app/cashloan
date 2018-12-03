package com.rongdu.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.rongdu.cashloan.cl.domain.BorrowRepayLog;
import com.rongdu.cashloan.cl.model.BorrowRepayLogModel;
import com.rongdu.cashloan.cl.model.ManageBRepayLogModel;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;

/**
 * 还款计录Dao
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-02-14 13:46:12
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface BorrowRepayLogMapper extends BaseMapper<BorrowRepayLog,Long> {
	
	/**
	 * 根据借款id查询
	 * @param borrowId
	 * @return
	 */
	BorrowRepayLog findByBorrowId(long borrowId);

	List<ManageBRepayLogModel> listModel(Map<String, Object> params);

	/**
	 * 查询所有
	 * @param searchMap
	 * @return
	 */
	List<BorrowRepayLogModel> listSelModel(Map<String, Object> searchMap);

    /**
     * 退还补扣修改还款记录
     * @param paramMap
     * @return
     */
	int refundDeduction(Map<String, Object> paramMap);
}
