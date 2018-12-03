package com.rongdu.cashloan.cl.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.rongdu.cashloan.cl.domain.BorrowRepay;
import com.rongdu.cashloan.cl.domain.PayLog;
import com.rongdu.cashloan.cl.model.ManageBRepayModel;
import com.rongdu.cashloan.cl.model.ManageBorrowModel;
import com.rongdu.cashloan.core.common.service.BaseService;
import com.rongdu.cashloan.core.domain.Borrow;

/**
 * 还款计划Service
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-02-14 13:42:32
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface BorrowRepayService extends BaseService<BorrowRepay, Long>{

	/**
	 * 保存还款计划
	 * @param borrowRepay
	 * @return
	 */
	int save(BorrowRepay borrowRepay);
	
	/**
	 * 生成还款计划
	 * @param borrow
	 * @return
	 */
	boolean genRepayPlan(Borrow borrow);
	
	/**
	 * 还款计划 放款 成功之后 银行卡授权
	 * 
	 * @param userId
	 */
	void authSignApply(Long userId);
	
	
	 /**
	  * 后台列表
	  * @param params
	  * @param currentPage
	  * @param pageSize
	  * @return
	  */
	Page<ManageBRepayModel> listModel(Map<String, Object> params, int currentPage,
			int pageSize);

	/**
	 * 确认还款生产还款记录
	 * @param param
	 * @return
	 */
	Map<String,Object> confirmRepay(Map<String, Object> param);

	/**
	 * 查询所有
	 * @param paramMap
	 * @return
	 */
	List<BorrowRepay> listSelective(Map<String, Object> paramMap);

	/**
	 * 逾期更新信息
	 * @param data
	 * @return
	 */
	int updateLate(BorrowRepay data);
	
	/**
	 * 条件更新还款计划数据
	 * @param br
	 * @return
	 */
	int updateSelective(Map<String, Object> paramMap);
	

	/**
	 * 催收借款信息接口
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<ManageBorrowModel> listRepayModel(Map<String, Object> params,
			int currentPage, int pageSize);

	/**
	 * 逾期未入催
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<ManageBorrowModel> listModelNotUrge(Map<String, Object> params,
			int currentPage, int pageSize);

	/**
	 * 查询未还款列表
	 * 
	 * @param params
	 * @return
	 */
	List<BorrowRepay> findUnRepay(Map<String, Object> paramMap);
	
	
	/**
	 * 查询还款计划
	 * 
	 * @param paramMap
	 * @return
	 */
	BorrowRepay findSelective(Map<String, Object> paramMap);

	/**
	 * 查询所有还款信息
	 * @param params
	 * @return
	 */
	List<ManageBRepayModel> listAllModel(Map<String, Object> params);
	/**
	 * 文件解析批量还款
	 * @param repayFile
	 * @param type
	 * @throws IOException 
	 */
	List<List<String>> fileBatchRepay(MultipartFile repayFile, String type) throws Exception;
	
	Map<String, String> paySdkParams(Long userId, String agreeNo, double amount, String orderNo);
	
	/**
	 * 主动还款
	 * @param payType
	 * @param borrowId
	 * @param userId
	 * @param ip
	 * @return
	 */
	Map<String, String> repayment(Long borrowId, Long userId, String ip);

	void repaymentReturn(String payResult, String payOrderNo);

	void repaymentNotify(PayLog payLog, String statePaymentSuccess,
			String null1, String repayWayCertified, String cardNo);

	/**
	 * 还款中检查
	 * @param borrowId
	 * @return 
	 */
	void repayCheck(long borrowId);
	Map<String, String> confirmPay(Long borrowId,Long userId, String ip,String type);
}
