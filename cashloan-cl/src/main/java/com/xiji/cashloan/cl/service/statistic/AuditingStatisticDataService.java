package com.xiji.cashloan.cl.service.statistic;


import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.statistic.AuditingStatisticData;
import com.xiji.cashloan.core.common.service.BaseService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 审核统计数据Service
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-02-15 15:37:50
 */
public interface AuditingStatisticDataService extends BaseService<AuditingStatisticData, Long> {


    /**
     * 最近时间
     * @return
     */
    Date getLateDate();

    /**
     * 查询 审核统计数据
     * @param params
     * @return
     */
    List<AuditingStatisticData> listAuditingStatisticData(Map<String,Object> params);

    /**
     * 查询 审核统计数据
     * @param params
     * @return
     */
    Page<AuditingStatisticData> listAuditingStatistic(Map<String,Object> params, Integer current, Integer pageSize);
}
