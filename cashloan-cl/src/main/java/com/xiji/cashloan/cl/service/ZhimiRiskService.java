package com.xiji.cashloan.cl.service;

import com.xiji.cashloan.core.domain.Borrow;

/**
 * Created by szb on 19/5/5.
 */
public interface ZhimiRiskService {
    /**
     *
     * @param borrow
     * @param isAgain 是否复借 true-复借 false-新客
     * @return
     */
    int getScore(Borrow borrow, boolean isAgain);
}
