package com.rongdu.cashloan.cl.service;

import com.rongdu.cashloan.cl.domain.DhbReqLog;
import com.rongdu.cashloan.core.common.service.BaseService;
import com.rongdu.cashloan.core.domain.Borrow;
import com.rongdu.cashloan.rc.domain.TppBusiness;

/**
 * 贷后邦反欺诈请求记录表Service
 * 
 * @author jdd
 * @version 1.0.0
 * @date 2017-06-02 18:20:59
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface DhbReqLogService extends BaseService<DhbReqLog, Long>{
	/**
	 * 贷后邦反欺诈请求
	 * @param userId
	 * @param business
	 * @return
	 */
	int queryDhbSauron(Borrow borrow, TppBusiness business);

}
