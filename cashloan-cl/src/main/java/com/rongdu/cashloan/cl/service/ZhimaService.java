package com.rongdu.cashloan.cl.service;

import java.util.Map;

import com.rongdu.cashloan.cl.domain.Zhima;
import com.rongdu.cashloan.core.common.service.BaseService;
import com.rongdu.cashloan.core.domain.User;

/**
 * 芝麻信用Service
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-02-14 11:35:27
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface ZhimaService extends BaseService<Zhima, Long>{

	Zhima getZhima(Map<String, Object> paramMap);

	Map<String, Object> authCallBack(String params,User user) throws Exception;

	/**
	 * 获取用户芝麻积分
	 * @param userId
	 */
	int updateZhimaScore(Long userId);

	/**
	 * 根据用户查询芝麻信息
	 * @param userId
	 * @return
	 */
	Zhima findByUserId(Long userId);
}
