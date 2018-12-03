package com.rongdu.cashloan.cl.service;

import com.rongdu.cashloan.core.common.service.BaseService;
import com.rongdu.cashloan.cl.domain.OperatorReport;

import java.util.Map;

/**
 * 运营商报告Service
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-11-27 10:29:41
 */
public interface OperatorReportService extends BaseService<OperatorReport, Long>{

    /**
     * 根据taskId查询
     * @param taskId
     * @return
     */
    OperatorReport getByTaskId(String taskId);

    /**
     * 更新
     * @param params
     * @return
     */
    public int updateSelective(Map<String, Object> params);
}
