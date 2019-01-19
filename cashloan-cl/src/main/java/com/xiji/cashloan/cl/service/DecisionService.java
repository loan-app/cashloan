package com.xiji.cashloan.cl.service;

import com.xiji.cashloan.core.domain.Borrow;

/**
 * Created by szb on 19/1/12.
 */
public interface DecisionService {
    /**
     * 决策数据保存
     * @param borrow
     * @return
     */
    int saveBorrowDecision(Borrow borrow);
}
