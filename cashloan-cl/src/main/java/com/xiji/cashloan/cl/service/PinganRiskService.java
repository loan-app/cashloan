package com.xiji.cashloan.cl.service;

import com.xiji.cashloan.core.domain.Borrow;

/**
 * 凭安风控
 * Created by szb on 18/12/26.
 */
public interface PinganRiskService {
    int queryGrayscaleStat(Borrow borrow);
}
