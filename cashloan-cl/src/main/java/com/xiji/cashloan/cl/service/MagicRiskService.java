package com.xiji.cashloan.cl.service;

import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.rc.domain.TppBusiness;

/**
 * 魔蝎风控数据保存
 * Created by szb on 18/12/1.
 */
public interface MagicRiskService {

    int queryAntiFraud(Borrow borrow, TppBusiness business);

    int queryMultiInfo(Borrow borrow, TppBusiness business);

    int queryBlackGray(Borrow borrow, TppBusiness business);

    int queryPostLoad(Borrow borrow, TppBusiness business);

    int magicReportRequest(Borrow borrow, TppBusiness business);

    void saveMagicRisk(String res, Long userId);
}
