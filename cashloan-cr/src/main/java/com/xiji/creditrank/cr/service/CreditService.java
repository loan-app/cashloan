package com.xiji.creditrank.cr.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.system.domain.SysUser;
import com.xiji.creditrank.cr.domain.Credit;
import com.xiji.creditrank.cr.model.CreditModel;

/**
 * 授信额度管理Service
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface CreditService extends BaseService<Credit, Long>{

	/**
	 * 分页查询
	 * @param searchMap
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<CreditModel> page(Map<String,Object> searchMap, int current, int pageSize);
	
	/**
	 * 修改数据
	 * @param sysUser 
	 * @param remark 
	 * @param Param
	 * @return
	 */
	int updateSelective(long id, double unuse, SysUser sysUser, String remark);
	
	/**
	 * 主键查询
	 * @param id
	 * @return
	 */
	Credit findByPrimary(long id);

	/**
	 * 修改
	 * @param param
	 * @return
	 */
	int updateSelective(Map<String, Object> param);

	/**
	 * 查询所有
	 * @param searchMap
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<CreditModel> listAll(Map<String, Object> searchMap, int current,
			int pageSize);

	/**
	 * 查询用户
	 * @param consumerNo
	 * @return
	 */
	List<Credit> findByConsumerNo(String consumerNo);

	/**
	 * 冻结解冻账户
	 * @param id
	 * @param state
	 * @param sysUser 
	 * @return
	 */
	int updateState(long id, String state, SysUser sysUser);
	
	/**
	 * 查询用户所有额度类型
	 * @param paramMap
	 * @return
	 */
	Credit findSelective(Map<String, Object> paramMap);
}
