package com.xiji.cashloan.cl.service;

import com.xiji.cashloan.core.domain.Borrow;

/**
 * 宜信群星
 * Created by szb on 18/12/25.
 */
public interface YixinRiskService {

    int queryRisk(Borrow borrow);

    int queryFraud(Borrow borrow);
}
