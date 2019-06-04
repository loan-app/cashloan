package com.xiji.cashloan.cl.service;

import com.xiji.cashloan.core.domain.Borrow;

/**
 * Created by szb on 19/6/4.
 */
public interface PxRiskService {
    /**
     *
     * @param borrow
     * @return
     */
    double getScore(Borrow borrow);
}
