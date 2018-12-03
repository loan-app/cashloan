package com.xiji.creditrank.cr.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.creditrank.cr.domain.Info;
import com.xiji.creditrank.cr.model.InfoModel;

/**
 * 评分关联表Service
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-01-04 15:05:09
 * Copyright 杭州融都科技股份有限公司  creditrank All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface InfoService extends BaseService<Info, Long>{

	/**
	 * 根据tbNid查询
	 * @param tbNid
	 * @return
	 */
	Info findByTbNid(String tbNid);
	
	/**
	 * 保存数据
	 * @param tbNid
	 * @param tbName
	 * @param detail
	 * @return
	 */
	int save(String tbNid, String tbName, String detail);

	/**
	 * 分页查询
	 * @param secreditrankhMap
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<InfoModel> page(Map<String, Object> secreditrankhMap, int current, int pageSize);

	/**
	 * 修改数据
	 * @param updateMap
	 * @return
	 */
	int updateSelective(Map<String, Object> updateMap);

	/**
	 * 查询所有
	 * @param map
	 * @return
	 */
	List<Info> listSelective(Map<String, Object> map);

	List<Map<String, Object>> findTable();

	List<Map<String, Object>> findColumnByName(Map<String, Object> map);

	/**
	 * 主键查询
	 * @param id
	 * @return
	 */
	Info findByPrimary(long id);

}
