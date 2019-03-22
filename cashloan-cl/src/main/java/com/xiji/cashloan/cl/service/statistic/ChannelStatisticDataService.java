package com.xiji.cashloan.cl.service.statistic;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.statistic.ChannelStatisticData;
import com.xiji.cashloan.core.common.service.BaseService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 渠道统计数据Service
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-02-15 15:40:18
 */
public interface ChannelStatisticDataService extends BaseService<ChannelStatisticData, Long>{


    /**
     * 查询最近时间
     * @return
     */
    Date getLateTime();

    /**
     * 渠道数据统计
     * @param params
     * @return
     */
    List<ChannelStatisticData> listChannelStatisticData(Map<String,Object> params);


    /**
     * 查询 渠道统计列表
     * @param params
     * @return
     */
    Page<ChannelStatisticData> listChannelStatistic(Map<String,Object> params,Integer current,Integer pageSize);

    /**
     * 更新渠道下款数
     * @param params
     * @return
     */
    int updateChannelLoan(Map<String,Object> params);
}
