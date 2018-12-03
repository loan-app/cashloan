package com.rongdu.cashloan.cl.service;

import com.rongdu.cashloan.cl.domain.UserCardCreditLog;
import com.rongdu.cashloan.core.common.service.BaseService;

/**
 * 人脸识别请求记录Service
 * 
 * @author jdd
 * @version 1.0.0
 * @date 2017-04-10 14:37:56
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface UserCardCreditLogService extends BaseService<UserCardCreditLog, Long>{
	/**
	 * 判断用户是否可以人脸识别认证
	 * @param userId
	 * @return
	 */
	boolean isCanCredit(Long userId);

}
