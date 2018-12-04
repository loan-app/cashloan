package com.xiji.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.cl.domain.ProfitAmount;
import com.xiji.cashloan.cl.model.ManageProfitAmountModel;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

/**
 * 分润资金Dao
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
public interface ProfitAmountMapper extends BaseMapper<ProfitAmount,Long> {

	/**
	 * 可提现查询
	 * @param map
	 * @return
	 */
	List<ManageProfitAmountModel> findAmount(Map<String, Object> map);

	/**
	 * 管理员可提现查看
	 * @param map
	 * @return
	 */
	List<ManageProfitAmountModel> findSysAmount(Map<String, Object> map);

	List<ProfitAmount> listNoCash();
	
	/**
	 * 增加未打款金额
	 * @param map
	 * @return
	 */
	int addNocashedAmount(Map<String, Object> map);
	
	/**
	 * 修改分润
	 * @param map
	 * @return
	 */
	int updateCash(Map<String, Object> map);
}
