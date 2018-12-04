package com.xiji.cashloan.cl.service;

import java.util.Date;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.ProfitAgent;
import com.xiji.cashloan.cl.model.ManageAgentListModel;
import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.core.domain.User;

/**
 * 代理用户信息Service
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface ProfitAgentService extends BaseService<ProfitAgent, Long>{

	/**
	 * 代理商信息保存
	 * @param userId
	 * @param level
	 * @param rate 
	 * @param rate 
	 */
//	int save(long userId, int level, String leaderId, String rate);

	/**
	 * 代理商审核
	 * @param isUse
	 * @param  
	 */
	int pass(int isUse, long id);

	/**
	 * 二级代理升级
	 * @param id
	 * @param rate
	 * @param oldRate
	 * @return
	 */
	int rankUp(long id,long userId);

	/**
	 * 代理商查询
	 * @param userName
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<ManageAgentListModel> findAgentList(String userName, int current, int pageSize);

	/**
	 * 查询用户等级
	 * @param loginName
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<User> findUserLevel(String userName,int current,int pageSize);

	/**
	 * 取消一级代理
	 * @param id
	 * @return
	 */
	int freezeAgent(long userId,Date updateTime);

	/**
	 * 一级代理处理
	 * @param parseLong
	 * @return
	 */
	int saveOne(long parseLong,Date updateTime);

	/**
	 * 二级代理处理
	 * @param parseLong
	 * @param leaderId
	 * @param rate
	 * @return
	 */
	int saveTwo(long parseLong, String leaderId, String rate,Date updateTime);

}
