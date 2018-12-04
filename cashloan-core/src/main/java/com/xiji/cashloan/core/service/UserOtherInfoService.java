package com.xiji.cashloan.core.service;

import java.util.Map;

import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.core.domain.UserOtherInfo;

/**
 * 用户更多信息Service
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface UserOtherInfoService extends BaseService<UserOtherInfo, Long> {
	
	/**
	 * 据用户主键获取用户其他信息
	 * 
	 * @param userId
	 * @return
	 */
	UserOtherInfo getInfoByUserId(Long userId);

	/**
	 * 保存用户其他信息
	 * 
	 * @param otherInfo
	 * @return
	 */
	boolean save(UserOtherInfo otherInfo);

	/**
	 * 修改用户其他信息
	 * 
	 * @param otherInfo
	 * @return
	 */
	boolean update(UserOtherInfo otherInfo);

	/**
	 * 修改用户其他信息
	 * 
	 * @param paramMap
	 * @return
	 */
	boolean updateSelective(Map<String, Object> paramMap);
	
}
