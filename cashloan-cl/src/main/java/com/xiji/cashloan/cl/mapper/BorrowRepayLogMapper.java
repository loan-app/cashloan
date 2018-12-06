package com.xiji.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.cl.domain.BorrowRepayLog;
import com.xiji.cashloan.cl.model.BorrowRepayLogModel;
import com.xiji.cashloan.cl.model.ManageBRepayLogModel;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

/**
 * 还款计录Dao
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
public interface BorrowRepayLogMapper extends BaseMapper<BorrowRepayLog,Long> {
	
	/**
	 * 根据借款id查询
	 * @param borrowId
	 * @return
	 */
	BorrowRepayLog findByBorrowId(long borrowId);

	List<ManageBRepayLogModel> listModel(Map<String, Object> params);

	/**
	 * 查询所有
	 * @param searchMap
	 * @return
	 */
	List<BorrowRepayLogModel> listSelModel(Map<String, Object> searchMap);

    /**
     * 退还补扣修改还款记录
     * @param paramMap
     * @return
     */
	int refundDeduction(Map<String, Object> paramMap);
}
