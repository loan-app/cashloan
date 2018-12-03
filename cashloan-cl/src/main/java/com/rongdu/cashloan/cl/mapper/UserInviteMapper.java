package com.rongdu.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.rongdu.cashloan.cl.domain.UserInvite;
import com.rongdu.cashloan.cl.model.InviteBorrowModel;
import com.rongdu.cashloan.cl.model.ManageAgentModel;
import com.rongdu.cashloan.cl.model.ManageProfitModel;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;

/**
 * 邀请记录Dao
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-02-18 15:54:41
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface UserInviteMapper extends BaseMapper<UserInvite,Long> {

	/**
	 * 代理商查询
	 * @param map
	 * @return
	 */
	List<ManageProfitModel> findAgent(Map<String, Object> map);

	/**
	 * 管理员代理商查询
	 * @param map
	 * @return
	 */
	List<ManageAgentModel> findSysAgent(Map<String, Object> map);

	/**
	 * 统计邀请的二级代理数量
	 * @param userId
	 * @return
	 */
	long count(Long userId);

	/**
	 * 查询邀请明细
	 * @param map
	 * @return
	 */
	List<InviteBorrowModel> listInviteBorrow(Map<String, Object> map);

}
