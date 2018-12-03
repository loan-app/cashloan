package com.rongdu.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rongdu.cashloan.cl.domain.OperatorReqLog;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;

/**
 * 运营商认证中间表Dao
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-03-01 16:27:59
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface OperatorReqLogMapper extends BaseMapper<OperatorReqLog,Long> {

	/**findRecentOrderNo
	 * 根据用户查找当天认证记录
	 * @param paramMap
	 * @return
	 */
	List<OperatorReqLog> listByUserId(Map<String, Object> paramMap);
	/**
	 * 查询最后一条符合条件的记录
	 * @param paramMap
	 * @return
	 */
	OperatorReqLog findLastRecord(Map<String, Object> paramMap);

}
