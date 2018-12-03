package com.rongdu.cashloan.cl.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.rongdu.cashloan.core.common.service.BaseService;
import com.rongdu.cashloan.core.domain.UserBaseInfo;
import com.rongdu.cashloan.cl.domain.FourElementsLog;

/**
 * 四要素认证记录Service
 * 
 * @author xx
 * @version 1.0.0
 * @date 2017-08-31 17:46:26
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface FourElementsLogService extends BaseService<FourElementsLog, Long>{
	
	/**
	 * 判断四要素认证次数
	 * @param  paramMap
	 * @return
	 */
	int verifyTime(String userId);
	
	/**
	 * 四要素认证
	 * @param  paramMap
	 * @return
	 */
	JSONObject fourElementsVerify(String userId, UserBaseInfo baseInfo, String cardNo);
	
	/**
	 * 查询单个
	 * @param  paramMap
	 * @return
	 */
	FourElementsLog findSelective(Map<String, Object> paramsMap);
}
