package com.xiji.cashloan.cl.service;

import java.util.Map;

import com.xiji.cashloan.cl.domain.Zhima;
import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.core.domain.User;

/**
 * 芝麻信用Service
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface ZhimaService extends BaseService<Zhima, Long>{

	Zhima getZhima(Map<String, Object> paramMap);

	Map<String, Object> authCallBack(String params,User user) throws Exception;

	/**
	 * 获取用户芝麻积分
	 * @param userId
	 */
	int updateZhimaScore(Long userId);

	/**
	 * 根据用户查询芝麻信息
	 * @param userId
	 * @return
	 */
	Zhima findByUserId(Long userId);
}
