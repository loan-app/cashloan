package com.xiji.cashloan.cl.mapper;

import java.util.Map;

import com.xiji.cashloan.cl.domain.UrgeRepayOrderLog;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

/**
 * 催款记录表Dao
 * 
 * @author jdd
 * @version 1.0.0
 * @date 2017-03-07 14:28:22
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface UrgeRepayOrderLogMapper extends BaseMapper<UrgeRepayOrderLog,Long> {
	
	/**
	 * 统计数量
	 * @param params
	 * @return
	 */
	int countLog(Map<String, Object> params);

}
