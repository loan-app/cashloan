package com.xiji.creditrank.cr.service;


import java.util.List;
import java.util.Map;

import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.creditrank.cr.domain.CrResult;

/**
 * 评分结果Service
 * 
 * @author ctt
 * @version 1.0.0
 * @date 2017-01-05 16:22:54
 * Copyright 杭州融都科技股份有限公司  creditrank All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface CrResultService extends BaseService<CrResult, Long>{

	/**
	 * 统计用户的总评分和总额度
	 * @param userId
	 * @return
	 */
	public Map<String,Object> findUserResult(Long userId);
	
	/**
	 * 查询用户各借款类型的总额度
	 * @param userId
	 * @return
	 */
	public List<CrResult> findAllBorrowTypeResult(Long userId);
	
}
