package com.xiji.cashloan.rc.service;

import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.rc.domain.SimpleVoicesCount;

/**
 * 风控数据统计-（简）通话记录统计Service
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface SimpleVoicesCountService extends BaseService<SimpleVoicesCount, Long> {
	
	/**
	 * 通话记录总次数
	 * @param userId
	 * @return
	 */
	int countOne(long userId);

	/**
	 * 根据userId获取最新一条数据
	 * @param userId 用户id
	 * @return
	 */
	SimpleVoicesCount findByUserId(long userId);
}
