package com.rongdu.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.rongdu.cashloan.cl.domain.PayLog;
import com.rongdu.cashloan.cl.model.ManagePayLogModel;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;

/**
 * 支付记录Mapper
 * 
 * @author gc
 * @version 1.0.0
 * @date 2017-03-07 16:18:56
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface PayLogMapper extends BaseMapper<PayLog, Long> {

	/**
	 * 列表查询
	 * 
	 * @param searchMap
	 * @return
	 */
	List<ManagePayLogModel> page(Map<String, Object> searchMap);

	/**
	 * 查看详情
	 * 
	 * @param id
	 * @return
	 */
	ManagePayLogModel findDetail(Long id);
	
	
	/**
	 * 更新状态校验订单状态
	 * @return
	 */
	int updateState(Map<String,Object> paramMap);
	
	/**
	 * 据条件查询对账List
	 * 
	 * @param paramMap
	 * @return
	 */
	List<PayLog> findCheckList(Map<String, Object> paramMap);
	
	/**
	 * 查询最新的代扣日志
	 * 
	 * @param paramMap
	 * @return
	 */
	PayLog findLatestOne(Map<String, Object> paramMap);
	
	/**
	 * 查询代扣次数
	 * @param borrowId
	 * @return
	 */
	int doRepaymentCount(long borrowId);
}
