package com.rongdu.cashloan.rc.service;

import java.util.Map;

import com.rongdu.cashloan.core.common.service.BaseService;
import com.rongdu.cashloan.rc.domain.TppReqLog;

/**
 * 第三方征信请求记录Service
 * 
 * @author zlh
 * @version 1.0.0
 * @date 2017-03-20 13:50:34
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 创新一部：rdc@erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface TppReqLogService extends BaseService<TppReqLog, Long>{

	/**
	 * 根据订单号修改记录
	 * @param params
	 * @return
	 */
	int modifyTppReqLog(TppReqLog log);
	
	/**
	 * 根据参数查询
	 * @param params
	 * @return
	 */
	TppReqLog findSelective(Map<String, Object> params);
	
}
