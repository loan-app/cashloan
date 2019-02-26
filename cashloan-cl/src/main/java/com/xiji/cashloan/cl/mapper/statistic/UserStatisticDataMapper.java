package com.xiji.cashloan.cl.mapper.statistic;

import com.xiji.cashloan.cl.domain.statistic.UserStatisticData;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 用户统计数据Dao
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-02-15 15:41:39
 */
@RDBatisDao
public interface UserStatisticDataMapper extends BaseMapper<UserStatisticData, Long> {


    /**
     * 最近时间
     * @return
     */
    Date getLateDate();

    /**
     * 用户数据统计
     * @return
     */
    List<UserStatisticData> listUserStatisticData(Map<String,Object> params);


    /**
     * 用户数据统计
     * @return
     */
    List<UserStatisticData> listUserStatistic(Map<String,Object> params);
}
