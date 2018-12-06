package com.xiji.cashloan.cl.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.PayRespLog;
import com.xiji.cashloan.cl.model.ManagePayRespLogModel;
import com.xiji.cashloan.core.common.service.BaseService;

/**
 * 支付响应记录Service
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface PayRespLogService extends BaseService<PayRespLog, Long>{

	/**
	 * 保存响应记录
	 * 
	 * @param log
	 * @return
	 */
	boolean save(PayRespLog log);

	/**
	 * 分页查询
	 * 
	 * @param current
	 * @param pageSize
	 * @param searchMap
	 * @return
	 */
	Page<ManagePayRespLogModel> page(int current, int pageSize,
									 Map<String, Object> searchMap);

	/**
	 * 查询详情
	 * 
	 * @param id
	 * @return
	 */
	ManagePayRespLogModel findDetail(Long id);
	
	
}
