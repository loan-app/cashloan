package com.rongdu.cashloan.rc.service;

import com.rongdu.cashloan.core.common.service.BaseService;
import com.rongdu.cashloan.rc.domain.SceneBusinessLog;

/**
 * 场景与第三方征信接口执行记录
 * @author caitt
 * @version 1.0
 * @date 2017年4月11日上午11:51:09
 * Copyright 杭州融都科技股份有限公司 现金贷  All Rights Reserved
 * 官方网站：www.erongdu.com
 * 研发中心：rdc@erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface SceneBusinessLogService extends BaseService<SceneBusinessLog, Long> {

	
	/**
	 * 是否有未执行完的接口
	 * @return
	 */
	boolean haveNeedExcuteService(Long borrowId);
	
	/**
	 * 是否需要执行
	 * @param userId
	 * @param info
	 * @return true  有未完成的接口，false 没有未完成的接口
	 * @throws Exception
	 */
	boolean needExcute(Long userId, Long busId, String getWay, String period);
}
