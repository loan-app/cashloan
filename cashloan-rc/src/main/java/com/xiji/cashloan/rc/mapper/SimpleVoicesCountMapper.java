package com.xiji.cashloan.rc.mapper;

import com.xiji.cashloan.rc.domain.SimpleVoicesCount;
import org.apache.ibatis.annotations.Param;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

/**
 * 风控数据统计-（简）通话记录统计Dao
 * 
 * @author xx
 * @version 1.0.0
 * @date 2017-07-06 18:11:18
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.xiji.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface SimpleVoicesCountMapper extends BaseMapper<SimpleVoicesCount, Long> {
	
	/**
	 * 通话记录总次数
	 * @param tableName
	 * @param userId
	 * @return
	 */
	int countOne(@Param("tableName") String tableName, @Param("userId") long userId);

	/**
	 * 查询近6个月均话费
	 * @param tableName
	 * @param userId
	 * @return
	 */
	SimpleVoicesCount findByUserId(@Param("userId") long userId);
}
