package com.rongdu.cashloan.cl.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.rongdu.cashloan.cl.domain.TongdunReqLog;
import com.rongdu.cashloan.cl.model.TongdunReqLogModel;
import com.rongdu.cashloan.core.common.service.BaseService;
import com.rongdu.cashloan.core.domain.Borrow;
import com.rongdu.cashloan.rc.domain.TppBusiness;

/**
 * 同盾第三方请求记录Service
 * 
 * @author jdd
 * @version 1.0.0
 * @date 2017-04-26 15:26:56
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 创新一部：rdc@erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface TongdunReqLogService extends BaseService<TongdunReqLog, Long>{
	/**
	 * 请求同盾返回结果
	 * @param userId
	 * @param borrow
	 * @return
	 */
	int preloanApplyRequest(Long userId, Borrow borrow,TppBusiness bussiness,String mobileType);
	/**
	 * 根据订单号查询同盾审核报告
	 * @param orderId
	 * @param bussiness
	 * @return
	 */
	String preloanReportRequest(String orderId,TppBusiness bussiness,String mobileType);
	/**
	 * 同盾请求记录列表
	 */
	Page<TongdunReqLogModel> pageListModel(Map<String, Object> params, int current,
			int pageSize);
	/**
	 * 同盾请求记录详细信息
	 * @param id
	 * @return
	 */
	TongdunReqLogModel getModelById(long id);
	/**
	 * 同盾请求记录查询
	 * @param params
	 * @return
	 */
	List<TongdunReqLogModel> listByMap(Map<String, Object> params);

}
