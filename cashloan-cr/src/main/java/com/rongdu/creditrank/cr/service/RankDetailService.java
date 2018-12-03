package com.rongdu.creditrank.cr.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.rongdu.cashloan.core.common.service.BaseService;
import com.rongdu.creditrank.cr.domain.RankDetail;
/**
 * 评分卡等级详情表Service
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-02-06 11:27:25
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface RankDetailService extends BaseService<RankDetail, Long>{

	/**
	 * 保存数据
	 * @param rankDetail
	 * @return
	 */
	int save(RankDetail rankDetail);
	
	/**
	 * 修改数据
	 * @param rankDetailMap
	 * @return
	 */
	int updateSelective(Map<String, Object> rankDetailMap);

	/**
	 * 查询
	 * @param searchMap
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<RankDetail> page(Map<String, Object> searchMap, int current, int pageSize);

}
