package com.rongdu.cashloan.rc.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;
import com.rongdu.cashloan.rc.domain.TppBusiness;
import com.rongdu.cashloan.rc.model.ManageTppBusinessModel;
import com.rongdu.cashloan.rc.model.TppBusinessModel;

/**
 * 第三方征信接口信息Dao
 * 
 * @author zlh
 * @version 1.0.0
 * @date 2017-03-14 13:41:57
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 创新一部：rdc@erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface TppBusinessMapper extends BaseMapper<TppBusiness,Long> {

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<TppBusinessModel> listAll();

	/**
	 * 条件查询第三方征信接口信息
	 * 
	 * @param paramMap
	 * @return
	 */
	List<ManageTppBusinessModel> list(Map<String, Object> paramMap);

	/**
	 * 据tppId查询第三方征信接口信息
	 * 
	 * @param tppId
	 * @return
	 */
	ManageTppBusinessModel findByTppId(long tppId);
	
	/**
	 * 根据Nid查找
	 * @param nid
	 * @return
	 */
	TppBusiness findByNid(@Param("nid") String nid, @Param("tppId") Long tppId);
}
