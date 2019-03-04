package com.xiji.cashloan.cl.mapper.statistic;

import com.xiji.cashloan.cl.domain.statistic.RepaymentStatisticData;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 还款统计数据Dao
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-02-15 15:36:37
 */
@RDBatisDao
public interface RepaymentStatisticDataMapper extends BaseMapper<RepaymentStatisticData, Long> {

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
    List<RepaymentStatisticData> listRepaymentStatistic(Map<String,Object> params);
}
