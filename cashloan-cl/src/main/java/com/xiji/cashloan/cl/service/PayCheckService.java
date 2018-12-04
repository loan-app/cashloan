package com.xiji.cashloan.cl.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.PayCheck;
import com.xiji.cashloan.cl.model.ManagePayCheckModel;
import com.xiji.cashloan.core.common.service.BaseService;

/**
 * 资金对账记录Service
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface PayCheckService extends BaseService<PayCheck, Long>{

	/**
	 * 保存对账记录
	 * @param payCheck
	 * @return
	 */
	boolean save(PayCheck payCheck);

	/**
	 * 列表搜索资金对账记录
	 * @param current
	 * @param pageSize
	 * @param searchMap
	 * @return
	 */
	Page<ManagePayCheckModel> page(int current, int pageSize,
			Map<String, Object> searchMap);

	/**
	 * 条件查询单条对账记录
	 * 
	 * @param searchMap
	 * @return
	 */
	PayCheck findSelective(Map<String, Object> searchMap);

	/**
	 * 导出查询
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List listPayCheck(Map<String, Object> params);
}
