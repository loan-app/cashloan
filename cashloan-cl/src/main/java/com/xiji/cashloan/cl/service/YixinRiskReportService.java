package com.xiji.cashloan.cl.service;

import com.xiji.cashloan.cl.domain.YixinRiskReport;
import com.xiji.cashloan.core.common.service.BaseService;

import java.util.Map;

/**
 * 宜信风险评估Service
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-25 16:26:48
 */
public interface YixinRiskReportService extends BaseService<YixinRiskReport, Long>{

    /**
     * 根据用户id 获取最近一份风险评估报告
     * @param userId
     * @return
     */
    Map<String,String> getRecentlyYixinRiskReportMap(Long userId);

}
