package com.xiji.cashloan.manage.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.manage.domain.QuartzLog;
import org.apache.ibatis.annotations.Param;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.manage.model.QuartzLogModel;

/**
 * 定时任务记录Dao
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface QuartzLogMapper extends BaseMapper<QuartzLog,Long> {

	/**
	 * 据quartId查询最后执行时间
	 * @param id
	 * @return
	 */
	String findLastTimeByQzInfoId(@Param("quartzId") Long quartId);

	/**
	 * 日志查询
	 * @param searchMap
	 * @return
	 */
	List<QuartzLogModel> page(Map<String, Object> searchMap);

    

}
