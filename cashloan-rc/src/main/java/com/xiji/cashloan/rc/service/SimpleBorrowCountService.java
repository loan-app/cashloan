package com.xiji.cashloan.rc.service;

import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.rc.domain.SimpleBorrowCount;

/**
 * 风控数据统计-（简）借款统计Service
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface SimpleBorrowCountService extends BaseService<SimpleBorrowCount, Long> {
	
	/**
     * 借款人有逾期30天以上已还借款数
     * @param userId
     * @return
     */
    int countOne(long userId);
    
}
