package com.rongdu.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.rongdu.cashloan.cl.domain.UrgeRepayOrder;
import com.rongdu.cashloan.cl.model.UrgeRepayCountModel;
import com.rongdu.cashloan.cl.model.UrgeRepayOrderModel;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;
 
 

/**
 * 催款计划表Dao
 * 
 * @author jdd
 * @version 1.0.0
 * @date 2017-03-07 14:21:58
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface UrgeRepayOrderMapper extends BaseMapper<UrgeRepayOrder,Long> {

	List<UrgeRepayOrder> listSelective(Map<String, Object> paramMap);

	List<UrgeRepayOrderModel> listModel(Map<String, Object> params);

	List<UrgeRepayOrder> listAll(Map<String, Object> params);

	String allOrderCount(Map<String, Object> map);

	String successCount(Map<String, Object> map);

	String count(Map<String, Object> map);

	List<UrgeRepayOrder> listOrder(Map<String, Object> map);

	String newOrderByUser(Map<String, Object> map);

	String repayOrderByUser(Map<String, Object> map);

	String successOrderByUser(Map<String, Object> map);

	String failOrderByUser(Map<String, Object> map);

	String countByUser(Map<String, Object> map);

	String allOrderSum(Map<String, Object> map);

	String allSuccessSum(Map<String, Object> map);

	String allFailSum(Map<String, Object> map);
	
	List<UrgeRepayCountModel> listSysUserByRole(Map<String, Object> params);
	
	int countOrder(Map<String, Object> params);

	int updateSuccess(Map<String, Object> map);
	
}
