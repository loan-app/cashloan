package com.xiji.cashloan.rc.service;

import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.rc.domain.SimpleContactCount;

/**
 * 风控数据统计-（简）通讯录统计Service
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface SimpleContactCountService extends BaseService<SimpleContactCount, Long> {
	
	/**
	 * 通讯录总条数
	 * @param userId
	 * @return
	 */
	int countOne(long userId);

}
