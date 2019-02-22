package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.cl.domain.statistic.AuditorStatisticData;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 审核人员统计数据Dao
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-02-15 15:35:23
 */
@RDBatisDao
public interface AuditorStatisticDataMapper extends BaseMapper<AuditorStatisticData, Long> {

    /**
     * 获取最近时间
     * @return
     */
    Date getLateDate();

    /**
     * 获取申请订单数
     * @param params
     * @return
     */
    List<AuditorStatisticData> borrowApplyCount(Map<String,Object> params);


    /**
     * 获取通过订单量
     * @param params
     * @return
     */
    List<AuditorStatisticData> passOrder(Map<String,Object> params);


    /**
     * 获取当日到期首贷逾期数
     * @param params
     * @return
     */
    List<AuditorStatisticData> firstOverdue(Map<String,Object> params);
    /**
     * 获取当日首贷放款数
     * @param params
     * @return
     */
    List<AuditorStatisticData> firstLoadCount(Map<String,Object> params);


    /**
     * 获取当日到期逾期数
     * @param params
     * @return
     */
    List<AuditorStatisticData> currentOverdue(Map<String,Object> params);


    /**
     * 获取当日到期放款数
     * @param params
     * @return
     */
    List<AuditorStatisticData> loadCount(Map<String,Object> params);

    /**
     * 获取当日到期展期逾期数
     * @param params
     * @return
     */
    List<AuditorStatisticData> firstExtendOverdueCount(Map<String,Object> params);
}
