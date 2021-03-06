package com.xiji.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.cl.domain.ProfitCashLog;
import com.xiji.cashloan.cl.model.ManageProfitAmountModel;
import com.xiji.cashloan.cl.model.ManageProfitLogModel;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

/**
 * 分润提现记录Dao
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/12/03
 *
 *
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
