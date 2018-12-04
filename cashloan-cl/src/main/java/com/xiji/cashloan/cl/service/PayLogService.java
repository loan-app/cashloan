package com.xiji.cashloan.cl.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.PayLog;
import com.xiji.cashloan.cl.model.ManagePayLogModel;
import com.xiji.cashloan.core.common.service.BaseService;

/**
 * 支付记录Service
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface PayLogService extends BaseService<PayLog, Long>{

	/**
	 * 保存支付记录
	 * 
	 * @param payLog
	 * @return
	 */
	boolean save(PayLog payLog);

	/**
	 * 分页查询支付记录
	 * 
	 * @param current
	 * @param pageSize
	 * @param searchMap
	 * @return
	 */
	Page<ManagePayLogModel> page(int current, int pageSize,
								 Map<String, Object> searchMap);

	/**
	 * 查看详情
	 * 
	 * @param id
	 * @return
	 */
	ManagePayLogModel findDetail(Long id);

	/**
	 * 审核
	 * 
	 * @param id
	 * @return
	 */
	boolean auditPay(Long id,String state);

	/**
	 * 校验付款状态是否指定状态
	 * 
	 * @param id
	 * @param state 审核状态
	 * @return
	 */
	Map<String, Object> checkPayLogState(Long id,String state);
	
	/**
	 * 订单号查询支付记录
	 * 
	 * @param orderNo
	 * @return
	 */
	PayLog findByOrderNo(String orderNo);
	
	/**
	 * 更新
	 * 
	 * @param paramMap
	 * @return
	 */
	boolean updateSelective(Map<String, Object> paramMap);
	
	/**
	 * 条件查询
	 * 
	 * @param paramMap
	 * @return
	 */
	PayLog findSelective(Map<String, Object> paramMap);
	
	/**
	 * 条件查询对账记录
	 * 
	 * @param paramMap
	 * @return
	 */
	List<PayLog> findCheckList(Map<String, Object> paramMap);

	/**
	 * 判断审核状态
	 * @param borrowId
	 * @return
	 */
	boolean judge(long borrowId);

	/**
	 * 导出查询
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List listPayLog(String params);

	/**
	 * 查询最新的代扣日志
	 * 
	 * @param paramMap
	 * @return
	 */
	PayLog findLatestOne(Map<String, Object> paramMap);
	
	/**
	 * 查询用户代扣次数
	 * @param userId
	 * @return
	 */
	int doRepaymentNum(long borrowId);

	int savePayLog(String orderNo, Long userId, Long borrowId, double amount,
			String cardNo, String bank, String scenes);

	/**
	 * 查询所有
	 * @param pmap
	 * @return
	 */
	List<PayLog> listSelective(Map<String, Object> pmap);

}
