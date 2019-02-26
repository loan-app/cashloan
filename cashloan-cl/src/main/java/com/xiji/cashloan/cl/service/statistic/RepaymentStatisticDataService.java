package com.xiji.cashloan.cl.service.statistic;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.statistic.RepaymentStatisticData;
import com.xiji.cashloan.core.common.service.BaseService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 还款统计数据Service
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-02-15 15:36:37
 */
public interface RepaymentStatisticDataService extends BaseService<RepaymentStatisticData, Long>{


    /**
     * 获取最近时间
     * @return
     */
    Date getLateDate();

    /**
     * 查询 还款统计数据
     * @param params
     * @return
     */
    List<RepaymentStatisticData> listRepaymentStatisticData(Map<String,Object> params);

    /**
     * 查询 还款统计数据
     * @param params
     * @return
     */
    Page<RepaymentStatisticData> listRepaymentStatistic(Map<String,Object> params, Integer current, Integer pageSize);
}
