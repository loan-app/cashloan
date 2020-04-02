package com.xiji.cashloan.cl.service;

import com.xiji.cashloan.core.domain.Borrow;

/**
 * Created by szb on 19/6/4.
 */
public interface PxRiskService {
    /**
     * 微积分
     *
     * @param borrow
     * @return
     */
    double getWjfScore(Borrow borrow);

    /**
     *
     *贷后数据放回给微积分
     * */
    void wjfhistory(Borrow borrow);

    /**
     * 排序
     *
     * @param borrow
     * @return
     */
    double getPxScore(Borrow borrow);
}
