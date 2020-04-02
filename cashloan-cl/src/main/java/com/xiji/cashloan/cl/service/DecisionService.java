package com.xiji.cashloan.cl.service;

import com.xiji.cashloan.cl.domain.Decision;
import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.core.domain.Borrow;

/**
 * Created by szb on 19/1/12.
 */
public interface DecisionService extends BaseService<Decision, Long> {
    /**
     * 决策数据保存
     * @param borrow
     * @return
     */
    int saveBorrowDecision(Borrow borrow);
}
