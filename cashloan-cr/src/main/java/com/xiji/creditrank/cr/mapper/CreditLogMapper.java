package com.xiji.creditrank.cr.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.creditrank.cr.domain.CreditLog;
import com.xiji.creditrank.cr.model.CreditLogModel;

/**
 * 授信额度记录Dao
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2016-12-16 10:31:23
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface CreditLogMapper extends BaseMapper<CreditLog,Long> {

	/**
	 * 分页查询
	 * @param searchMap
	 * @return
	 */
	List<CreditLogModel> findLog(Map<String, Object> searchMap);

}
