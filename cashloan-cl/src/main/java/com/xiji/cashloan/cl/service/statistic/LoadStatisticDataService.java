package com.xiji.cashloan.cl.service.statistic;

import com.xiji.cashloan.cl.domain.statistic.LoadStatisticData;
import com.xiji.cashloan.core.common.service.BaseService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 放款统计数据Service
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-02-15 15:38:59
 */
public interface LoadStatisticDataService extends BaseService<LoadStatisticData, Long>{


    /**
     * 最近时间
     * @return
     */
    Date getLateDate();

    /**
     * 查询 放款统计数据
     * @param params
     * @return
     */
    List<LoadStatisticData> listLoadStatisticData(Map<String,Object> params);
}
