package com.xiji.cashloan.cl.service;

import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.cl.domain.BorrowOperatorLog;
import com.xiji.cashloan.core.domain.Borrow;

/**
 * 借款订单运营商记录表Service
 * 
 * @author szb
 * @version 1.0.0
 * @date 2019-01-14 11:52:09
 */
public interface BorrowOperatorLogService extends BaseService<BorrowOperatorLog, Long>{

    int saveLog(Borrow borrow);

}
