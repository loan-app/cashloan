package com.rongdu.cashloan.cl.service;

import com.rongdu.cashloan.cl.domain.FireeyesBlackLog;
import com.rongdu.cashloan.core.common.service.BaseService;
import com.rongdu.cashloan.core.domain.Borrow;
import com.rongdu.cashloan.rc.domain.TppBusiness;

/**
 * 火眼黑名单Service
 * 
 * @author jchen
 * @version 1.0.0
 * @date 2017-07-05 14:53:05
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface FireeyesBlackLogService extends BaseService<FireeyesBlackLog, Long>{
	
	int queryFireeyesBlack(Borrow borrow, TppBusiness business);

}
