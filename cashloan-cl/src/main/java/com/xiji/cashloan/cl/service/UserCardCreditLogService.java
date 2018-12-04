package com.xiji.cashloan.cl.service;

import com.xiji.cashloan.cl.domain.UserCardCreditLog;
import com.xiji.cashloan.core.common.service.BaseService;

/**
 * 人脸识别请求记录Service
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
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
