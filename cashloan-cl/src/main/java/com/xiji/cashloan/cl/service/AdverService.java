package com.xiji.cashloan.cl.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.Adver;
import com.xiji.cashloan.core.common.service.BaseService;

/**
 * 广告Service
 * 
 * @author wmc
 * @version 1.0.0
 * @date 2017-06-21 14:33:20
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.xiji.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface AdverService extends BaseService<Adver, Long>{
	
	/**
	 * 更新
	 * @param params
	 * @return
	 */
	public boolean updateSelective(Map<String, Object> params);
	
	/**
	 * 分页查询
	 * @param current
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public Page<Adver> page(int current, int pageSize, Map<String, Object> params);
	
	/**
	 * 获取banner
	 * @return
	 */
	public List<Adver> getBanner();

}
