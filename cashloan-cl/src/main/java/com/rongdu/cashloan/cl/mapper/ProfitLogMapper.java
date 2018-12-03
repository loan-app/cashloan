package com.rongdu.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.rongdu.cashloan.cl.domain.ProfitLog;
import com.rongdu.cashloan.cl.model.ManageCashLogModel;
import com.rongdu.cashloan.cl.model.ProfitLogModel;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;

/**
 * 分润记录Dao
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-02-18 17:04:10
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface ProfitLogMapper extends BaseMapper<ProfitLog,Long> {

	/**
	 * 邀请明细
	 * @param searchMap
	 * @return
	 */
	List<ProfitLogModel> listInfo(Map<String, Object> searchMap);

	/**
	 * 提现记录查询
	 * @param map
	 * @return
	 */
	List<ManageCashLogModel> findCashLog(Map<String, Object> map);

	/**
	 * 管理员提现记录查询
	 * @param map
	 * @return
	 */
	List<ManageCashLogModel> findSysCashLog(Map<String, Object> map);
	
	/**
	 * 数量统计
	 * @param map
	 * @return
	 */
	int count(Map<String, Object> map);

}
