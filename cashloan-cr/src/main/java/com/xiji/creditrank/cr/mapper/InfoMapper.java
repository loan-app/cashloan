package com.xiji.creditrank.cr.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.creditrank.cr.domain.Info;
import com.xiji.creditrank.cr.model.InfoModel;

/**
 * 评分关联表Dao
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 * Copyright 杭州融都科技股份有限公司  creditrank All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface InfoMapper extends BaseMapper<Info,Long> {

	/**
	 * 根据tbNid返回数据
	 * @param tbNid
	 * @return
	 */
    Info findByTbNid(String tbNid);

    /**
     * 列表查询返回InfoModel
     * @param secreditrankhMap
     * @return
     */
	List<InfoModel> listSelect(Map<String, Object> secreditrankhMap);
	
	/**
     * 查询数据表信息
     */
	List<Map<String, Object>> findTable();

	/**
	 * 查询数据库表字段信息
	 */
	List<Map<String, Object>> findColumnByName(Map<String, Object> map);

}
