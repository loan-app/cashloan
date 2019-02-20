package com.xiji.cashloan.cl.service;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.UserAuth;
import com.xiji.cashloan.cl.model.UserAuthModel;
import com.xiji.cashloan.core.common.service.BaseService;

import java.util.Map;


/**
 * 用户认证信息表Service
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
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

	/**
	 * 根据时间更新认证状态
	 * @param userAuth
	 * @return
	 */
	int updateAuthByTime(Map<String, Object> userAuth);
}
