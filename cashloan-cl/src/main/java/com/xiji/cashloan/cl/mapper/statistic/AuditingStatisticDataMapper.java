package com.xiji.cashloan.cl.mapper.statistic;

import com.xiji.cashloan.cl.domain.statistic.AuditingStatisticData;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 审核统计数据Dao
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-02-15 15:37:50
 */
@RDBatisDao
public interface AuditingStatisticDataMapper extends BaseMapper<AuditingStatisticData, Long> {

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
    List<AuditingStatisticData> listAuditingStatistic(Map<String,Object> params);
}
