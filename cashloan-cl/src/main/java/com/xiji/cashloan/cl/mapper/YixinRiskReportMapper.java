package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.cl.domain.YixinRiskReport;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

/**
 * 宜信风险评估Dao
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-25 16:26:48
 */
@RDBatisDao
public interface YixinRiskReportMapper extends BaseMapper<YixinRiskReport, Long> {


    /**
     * 根据用户id 获取最近一条风险评估
     * @param userId
     * @return
     */
    YixinRiskReport getRecentlyYixinRiskReport(Long userId);

}
