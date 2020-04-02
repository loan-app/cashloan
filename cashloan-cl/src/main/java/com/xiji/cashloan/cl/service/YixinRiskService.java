package com.xiji.cashloan.cl.service;

import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.core.domain.Borrow;

/**
 * 宜信群星
 * Created by szb on 18/12/25.
 */
public interface YixinRiskService {

    int queryRisk(Borrow borrow);

    int queryFraud(Borrow borrow);

    /**
     * 查询综合决策报告小额评分
     * @param borrow
     * @return
     */
    double queryScore(Borrow borrow);

    int saveScore(JSONObject jsonData, Long userId, String flowId, Long borrowId);
}
