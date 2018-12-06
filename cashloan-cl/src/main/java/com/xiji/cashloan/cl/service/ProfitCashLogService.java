package com.xiji.cashloan.cl.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.ProfitCashLog;
import com.xiji.cashloan.cl.model.ManageProfitLogModel;
import com.xiji.cashloan.core.common.service.BaseService;

/**
 * 分润提现记录Service
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface ProfitCashLogService extends BaseService<ProfitCashLog, Long>{

	/**
	 * 提现记录查看
	 * @param searchMap
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<ProfitCashLog> page(Map<String, Object> searchMap, int current,
			int pageSize);
//
//	/**
//	 * 平台查询可提现用户
//	 * @param userName
//	 * @param current
//	 * @param pageSize
//	 * @return
//	 */
//	Page<ManageProfitAmountModel> findAmount(String userName, int current,
//			int pageSize);

	/**
	 * 查询分润明细
	 * @param phone
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<ManageProfitLogModel> findLog(String phone,String userName, int current, int pageSize);

	/**
	 * 管理员查询分润明细
	 * @param userName
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<ManageProfitLogModel> findLog(String userName, int current,
			int pageSize);

}
