package com.rongdu.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.rongdu.cashloan.cl.domain.PayCheck;
import com.rongdu.cashloan.cl.model.ManagePayCheckModel;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;


/**
 * 资金对账记录Dao
 * 
 * @author gc
 * @version 1.0.0
 * @date 2017-04-13 17:12:20
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface PayCheckMapper extends BaseMapper<PayCheck,Long> {

    
	/**
	 *
	 * @param searchMap
	 * @return
	 */
	 List<ManagePayCheckModel> page(Map<String, Object> searchMap);

}
