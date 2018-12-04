package com.xiji.cashloan.rc.mapper;


import java.util.List;

import com.xiji.cashloan.rc.domain.SceneBusinessLog;
import org.apache.ibatis.annotations.Param;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

/**
 * 场景与第三方征信接口执行记录
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 * Copyright 杭州融都科技股份有限公司 现金贷  All Rights Reserved
 * 官方网站：www.xiji.com
 * 研发中心：rdc@xiji.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface SceneBusinessLogMapper extends BaseMapper<SceneBusinessLog,Long> {

	/**
	 * 查询未完成的（失败的/需要重新执行的）记录数
	 * @param borrowId
	 * @return
	 */
	int countUnFinishLog(@Param("borrowId") Long borrowId);
	
	
	/**
	 * 查询未完成的（失败的/需要重新执行的）记录
	 * @param borrowId
	 * @return
	 */
	List<SceneBusinessLog> findSceneLogByBorrowId(@Param("borrowId") Long borrowId);
	
	/**
	 * 
	 * @param userId
	 * @param busId
	 * @return
	 */
	SceneBusinessLog findLastExcute(@Param("userId") Long userId, @Param("busId") Long busId);
}
