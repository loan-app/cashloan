package com.xiji.cashloan.cl.service;

import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.cl.domain.OperatorReport;

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

    /**
     * 给管理后台封装运营商数据-通过userId查询
     * @param userId
     * @return
     */
    JSONObject getReportByUserId(Long userId);

    /**
     * 给管理后台封装运营商数据-通过borrowId查询
     * @param borrowId
     * @return
     */
    JSONObject getReportByBorrowId(Long borrowId);
}
