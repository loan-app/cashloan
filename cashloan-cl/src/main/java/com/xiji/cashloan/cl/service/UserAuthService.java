package com.xiji.cashloan.cl.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.UserAuth;
import com.xiji.cashloan.cl.model.UserAuthModel;
import com.xiji.cashloan.core.common.service.BaseService;


/**
 * 用户认证信息表Service
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-02-14 11:18:17
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface UserAuthService extends BaseService<UserAuth, Long>{

	public UserAuth getUserAuth(Map<String,Object> paramMap);
	
	public Integer updateByUserId(Map<String,Object> paramMap);
	
	Page<UserAuthModel> listUserAuth(Map<String, Object> params, int currentPage,
			int pageSize);

	/**
	 * 查询认证状态
	 * @param userId
	 * @return
	 */
	public UserAuth findSelective(long userId);
	
	public Map<String, Object> getAuthState(Map<String, Object> paramMap);

	public int updatePhoneState(Map<String, Object> userAuth);
}
