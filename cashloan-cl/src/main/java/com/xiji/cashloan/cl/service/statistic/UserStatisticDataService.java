package com.xiji.cashloan.cl.service.statistic;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.statistic.UserStatisticData;
import com.xiji.cashloan.core.common.service.BaseService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户统计数据Service
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-02-15 15:41:39
 */
public interface UserStatisticDataService extends BaseService<UserStatisticData, Long>{


    Date getLateDate();


    /**
     * 查询 用户统计数据
     * @param params
     * @return
     */
    List<UserStatisticData> listUserStatisticData(Map<String,Object> params);


    /**
     * 用户数据统计
     * @return
     */
    Page<UserStatisticData> listUserStatistic(Map<String,Object> params, Integer current, Integer pageSize);

    /**
     * 更新用户下款数
     * @return
     */
    int updateUserLoanStatistic(Map<String,Object> params);
}
