package com.xiji.cashloan.cl.service.statistic;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.statistic.OverdueStatisticData;
import com.xiji.cashloan.cl.model.UserOverdueModel;
import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.core.model.UserBaseInfoModel;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 逾期统计数据Service
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-03-04 18:07:35
 */
public interface OverdueStatisticDataService extends BaseService<OverdueStatisticData, Long>{


    /**
     * 最近时间
     * @return
     */
    Date getLateDate();

    /**
     * 查询逾期统计数据
     * @param params
     * @return
     */
    List<OverdueStatisticData> listOverdueStatisticData(Map<String,Object> params);

    /**
     * 查询 逾期统计数据
     * @param params
     * @return
     */
    Page<OverdueStatisticData> listOverdueStatistic(Map<String,Object> params, Integer current, Integer pageSize);

    /**
     * 统计当前逾期率
     * @return
     */
    Page<OverdueStatisticData> queryNowOverdueStatistic(Map<String,Object> params);

    /**
     * 查询逾期用户信息
     * @param params
     * @return
     */
    List<UserOverdueModel> queryOverdueData(Map<String,Object> params);
}
