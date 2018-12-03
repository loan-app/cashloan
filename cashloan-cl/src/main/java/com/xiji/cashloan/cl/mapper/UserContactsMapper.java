package com.xiji.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xiji.cashloan.cl.domain.UserContacts;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

/**
 * 用户资料--联系人Dao
 * 
 * @author chenxy
 * @version 1.0.0
 * @date 2017-03-04 11:52:26
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface UserContactsMapper extends BaseMapper<UserContacts,Long> {
	
	/**
	 * 根据表名查询表数量
	 * @param tableName
	 * @return
	 */
	int countTable(String tableName);
	
	/**
	 * 根据表名创建表
	 * @param tableName
	 */
	void createTable(@Param("tableName") String tableName);
	
	/**
	 * (分表)新增
	 * @param tableName
	 * @param userContacts
	 * @return
	 */
	int saveShard(@Param("tableName")String tableName, @Param("item")UserContacts userContacts);
	
	/**
	 * 删除原有记录
	 * @param tableName
	 * @param userId
	 * @return
	 */
	int deleteShardByUserId(@Param("tableName")String tableName, @Param("userId")long userId);
	
	/**
	 * 根据参数(分表)查询
	 * @param tableName
	 * @param beginRow
	 * @param pageSize
	 * @param params
	 * @return
	 */
	List<UserContacts> listShardSelective(
			@Param("tableName") String tableName,
			@Param("params") Map<String, Object> params);
	
	List<UserContacts> listShardSelective1(
			@Param("tableName1") String tableName1,
			@Param("params") Map<String, Object> params);
	/**
	 * 通讯统计
	 * @param contacts 表
	 * @param operator 表
	 * @param params
	 * @return
	 */
	List<UserContacts> listOperatorCount(
			@Param("contacts") String contacts,
			@Param("operator") String operator,
			@Param("params") Map<String, Object> params);

	/**
	 * 通讯修改
	 */
	void updateCount(Map<String, Object> params);
}
