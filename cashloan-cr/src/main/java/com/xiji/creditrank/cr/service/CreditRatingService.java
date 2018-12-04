package com.xiji.creditrank.cr.service;

import com.xiji.cashloan.core.common.exception.CreditException;
import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.creditrank.cr.domain.CrResult;

/**
 * 信用评级及结果操作
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 * Copyright 杭州融都科技股份有限公司 creditrank  All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface CreditRatingService extends BaseService<CrResult, Long> {

	/**
	 * 信用自动评分并保存记录
	 * @param userId
	 */
	CrResult saveCreditRating(String consumerNo,Long borrowTypeId) throws CreditException;
}
