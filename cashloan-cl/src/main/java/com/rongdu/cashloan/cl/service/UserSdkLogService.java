package com.rongdu.cashloan.cl.service;

import java.util.Map;

import com.rongdu.cashloan.cl.domain.UserSdkLog;
import com.rongdu.cashloan.core.common.service.BaseService;


/**
 * sdk识别记录表Service
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-04-20 09:47:04
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 创新一部：rdc@erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface UserSdkLogService extends BaseService<UserSdkLog, Long>{

	/**
	 * 查询当日可识别次数
	 * @param searchMap
	 * @return
	 */
	Map<String, Object> countDayTime(Map<String, Object> searchMap);

	/**
	 * 保存
	 * @param usl
	 * @return
	 */
	int save(UserSdkLog usl);

}
