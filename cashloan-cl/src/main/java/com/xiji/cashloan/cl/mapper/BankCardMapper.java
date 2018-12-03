package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.cl.domain.BankCard;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

/**
 * 银行卡Dao
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-02-15 14:37:14
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface BankCardMapper extends BaseMapper<BankCard,Long> {
	
	/**
	 * 根据userId查询
	 * @return
	 */
	BankCard findByUserId(long userId);


}
