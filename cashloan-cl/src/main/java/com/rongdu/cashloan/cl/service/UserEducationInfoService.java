package com.rongdu.cashloan.cl.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.rongdu.cashloan.cl.domain.UserEducationInfo;
import com.rongdu.cashloan.cl.model.UserEducationInfoModel;
import com.rongdu.cashloan.core.common.service.BaseService;


/**
 * 学信查询记录表Service
 * 
 * @author xx
 * @version 1.0.0
 * @date 2017-04-18 16:30:45
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 创新一部：rdc@erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface UserEducationInfoService extends BaseService<UserEducationInfo, Long>{

	/**
	 * 信息入库
	 * @param ue
	 * @return
	 */
	int save(UserEducationInfo ue);

	/**
	 * 信息修改
	 * @param uei
	 * @return
	 */
	int update(UserEducationInfo uei);

	/**
	 * 列表查询
	 * @param searchMap
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<UserEducationInfoModel> list(Map<String, Object> searchMap,
			int current, int pageSize);

}
