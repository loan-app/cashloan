package com.xiji.cashloan.cl.service.statistic;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.statistic.AuditorStatisticData;
import com.xiji.cashloan.core.common.service.BaseService;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 审核人员统计数据Service
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-02-15 15:35:23
 */
public interface AuditorStatisticDataService extends BaseService<AuditorStatisticData, Long>{

    /**
     * 获取最近时间
     * @return
     */
    Date getLateDate();

    /**
     * 查询 审核人员统计数据
     * @param params
     * @return
     */
    List<AuditorStatisticData> listAuditorStatisticData(Map<String,Object> params);

    /**
     * 查询 审核人员数据统计
     * @param params
     * @return
     */
    Page<AuditorStatisticData> listAuditorStatistic(Map<String,Object> params, Integer current, Integer pageSize);
}
