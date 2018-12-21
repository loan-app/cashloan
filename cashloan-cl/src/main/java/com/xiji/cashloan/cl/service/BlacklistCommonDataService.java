package com.xiji.cashloan.cl.service;

import com.xiji.cashloan.cl.domain.BlacklistCommonData;
import com.xiji.cashloan.core.common.service.BaseService;

/**
 * 黑名单通用处理模型实体Service
 * 
 * @author king
 * @version 1.0.0
 * @date 2018-12-20 14:29:13
 */
public interface BlacklistCommonDataService extends BaseService<BlacklistCommonData, Long>{

    BlacklistCommonData findByBorrowId(Long borrowId,String source);

    int saveShard(BlacklistCommonData data);
}
