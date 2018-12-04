package com.xiji.cashloan.cl.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.UserEducationInfo;
import com.xiji.cashloan.cl.model.UserEducationInfoModel;
import com.xiji.cashloan.core.common.service.BaseService;


/**
 * 学信查询记录表Service
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 创新一部：rdc@xiji.com
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
