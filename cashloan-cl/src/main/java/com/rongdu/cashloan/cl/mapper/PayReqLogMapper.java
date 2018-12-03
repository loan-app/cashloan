package com.rongdu.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.rongdu.cashloan.cl.domain.PayReqLog;
import com.rongdu.cashloan.cl.model.ManagePayReqLogModel;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;

/**
 * 支付请求记录Mapper
 * 
 * @author gc
 * @version 1.0.0
 * @date 2017-03-07 16:18:30
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface PayReqLogMapper extends BaseMapper<PayReqLog,Long> {

	/**
	 * 分页查询
	 * 
	 * @param searchMap
	 * @return
	 */
	List<ManagePayReqLogModel> page(Map<String, Object> searchMap);

	/**
	 * 查询详情
	 * 
	 * @param id
	 * @return
	 */
	ManagePayReqLogModel findDetail(Long id);
}
