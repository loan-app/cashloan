package com.rongdu.cashloan.cl.service;

import com.rongdu.cashloan.core.domain.Borrow;
import com.rongdu.cashloan.rc.domain.TppBusiness;

/**
 * 魔蝎风控数据保存
 * Created by szb on 18/12/1.
 */
public interface MagicRiskService {

    int magicReportRequest(Borrow borrow, TppBusiness business);

    void saveMagicRisk(String res, Long userId);
}
