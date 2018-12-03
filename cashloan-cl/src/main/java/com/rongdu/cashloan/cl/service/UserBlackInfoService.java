package com.rongdu.cashloan.cl.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.rongdu.cashloan.cl.domain.UserBlackInfo;
import com.rongdu.cashloan.core.common.service.BaseService;
import com.rongdu.cashloan.core.domain.UserBaseInfo;

/**
 * 黑名单Service
 * 
 * @author caitt
 * @version 1.0.0
 * @date 2017-07-12 15:23:07
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface UserBlackInfoService extends BaseService<UserBlackInfo, Long>{

	/**
	 * 查询用户详细信息列表
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<UserBlackInfo> listInfo(Map<String, Object> params, int currentPage,int pageSize);
	
	/**
	 * 查询列表
	 * @param params
	 * @return
	 */
	List<UserBlackInfo> listSelective(Map<String, Object> params);
	
	/**
	 * 导入黑名单
	 * @param userInfoFile
	 */
	List<List<String>> importUserInfo(MultipartFile userInfoFile,String type) throws Exception;
	
	/**
	 * 根据条件查询
	 * @param params
	 * @return
	 */
	UserBlackInfo findByIdNo(String idNo);
	
	/**
	 * 删除
	 * @param Id
	 * @return
	 */
	int deleteByID(Long id);
	
	/**
	 * 黑名单操作
	 * @param Id
	 * @return
	 */
	void validUserBlackInfo(UserBaseInfo baseInfo);
	

	/**
	 * 删除黑白名单信息
	 * @param userId
	 * @param state
	 */
	void deleteBlackOrWhiteInfo(Long userId,String state);
}
