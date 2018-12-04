package com.xiji.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.cl.domain.OperatorReqLog;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

/**
 * 运营商认证中间表Dao
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/12/03
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
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
