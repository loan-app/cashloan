package com.xiji.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.cl.domain.UrgeRepayOrder;
import com.xiji.cashloan.cl.model.UrgeRepayCountModel;
import com.xiji.cashloan.cl.model.UrgeRepayOrderModel;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;


/**
 * 催款计划表Dao
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/12/03
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
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
