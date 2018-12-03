package com.rongdu.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.rongdu.cashloan.cl.domain.ProfitCashLog;
import com.rongdu.cashloan.cl.model.ManageProfitAmountModel;
import com.rongdu.cashloan.cl.model.ManageProfitLogModel;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;

/**
 * 分润提现记录Dao
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-02-18 16:51:48
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface ProfitCashLogMapper extends BaseMapper<ProfitCashLog,Long> {

	/**
	 * 查询分润明细
	 * @return
	 */
	List<ManageProfitLogModel> findLog(Map<String, Object> map);
	
	/**
	 * 提现记录查询
	 * @param map
	 * @return
	 */
	List<ManageProfitAmountModel> findAmount(Map<String, Object> map);

	/**
	 * 管理员查询分润明细
	 * @param map
	 * @return
	 */
	List<ManageProfitLogModel> findSysLog(Map<String, Object> map);

	/**
	 * 管理员查看提现
	 * @param map
	 * @return
	 */
	List<ManageProfitAmountModel> findSysAmount(Map<String, Object> map);
}
