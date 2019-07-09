package com.xiji.cashloan.cl.mapper.statistic;

import com.xiji.cashloan.cl.domain.statistic.OverdueStatisticData;
import com.xiji.cashloan.cl.model.UserOverdueModel;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 逾期统计数据Dao
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-03-04 18:07:35
 */
@RDBatisDao
public interface OverdueStatisticDataMapper extends BaseMapper<OverdueStatisticData, Long> {

    /**
     * 最近时间
     * @return
     */
    Date getLateDate();

    /**
     * 新客逾期
     * @param params
     * @return
     */
    List<OverdueStatisticData> newOverdue(Map<String,Object> params);

    /**
     * 最终新客逾期
     * @param params
     * @return
     */
    List<OverdueStatisticData> newOverdueNow(Map<String,Object> params);

    /**
     * 复借逾期
     * @param params
     * @return
     */
    List<OverdueStatisticData> againOverdue(Map<String,Object> params);

    /**
     * 最终复借逾期
     * @param params
     * @return
     */
    List<OverdueStatisticData> againOverdueNow(Map<String,Object> params);

    /**
     * 展期逾期
     * @param params
     * @return
     */
    List<OverdueStatisticData> extendOverdue(Map<String,Object> params);

    /**
     * 最终展期逾期
     * @param params
     * @return
     */
    List<OverdueStatisticData> extendOverdueNow(Map<String,Object> params);

    /**
     * 新客首借到期
     * @param params
     * @return
     */
    List<OverdueStatisticData> newExpire(Map<String,Object> params);

    /**
     * 复借到期
     * @param params
     * @return
     */
    List<OverdueStatisticData> againExpire(Map<String,Object> params);

    /**
     * 展期到期
     * @param params
     * @return
     */
    List<OverdueStatisticData> extendExpire(Map<String,Object> params);

    /**
     * 新客首借已还
     * @param params
     * @return
     */
    List<OverdueStatisticData> newRepayment(Map<String,Object> params);

    /**
     * 复借已还
     * @param params
     * @return
     */
    List<OverdueStatisticData> againRepayment(Map<String,Object> params);

    /**
     * 展期已还
     * @param params
     * @return
     */
    List<OverdueStatisticData> extendRepayment(Map<String,Object> params);


    /**
     * 查询 逾期统计数据
     * @param params
     * @return
     */
    List<OverdueStatisticData> listOverdueStatistic(Map<String,Object> params);

    /**
     * 查询逾期用户信息
     * @param params
     * @return
     */
    List<UserOverdueModel> queryOverdueData(Map<String, Object> params);
}
