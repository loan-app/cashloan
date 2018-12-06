package com.xiji.cashloan.cl.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.ProfitAmount;
import com.xiji.cashloan.cl.model.ManageProfitAmountModel;
import com.xiji.cashloan.core.common.service.BaseService;

/**
 * 分润资金Service
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface ProfitAmountService extends BaseService<ProfitAmount, Long>{

	/**
	 * 提现修改资金
	 * @param userId
	 * @param money
	 * @return
	 */
	int cash(long userId, double money);

	/**
	 * 查看我的奖金
	 * @param userId
	 * @return
	 */
	ProfitAmount find(long userId);

	/**
	 * 查询可提现奖金
	 * @param phone
	 * @param userName 
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<ManageProfitAmountModel> findAmount(String phone, String userName, int current, int pageSize);

	/**
	 * 管理员查询可提现奖金
	 * @param phone
	 * @param userName 
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<ManageProfitAmountModel> findSysAmount(String userName, int current,
			int pageSize);
	
	/**
	 * 查询未取现金额用户
	 * @param map
	 * @return
	 */
	List<ProfitAmount> listNoCash();

}
