package com.xiji.cashloan.cl.service;


import com.xiji.cashloan.cl.domain.YouDunRiskReport;
import com.xiji.cashloan.core.common.service.BaseService;

import java.util.Map;

/**
 * 有盾风险评估Service
 */
public interface YouDunRiskReportService extends BaseService<YouDunRiskReport, Long>{

    /**
     * 根据用户id 获取最近一份风险评估报告
     * @param borrowId
     * @return
     */
    Map<String,Object> getYouDunRiskReportMap(Long borrowId);

}
