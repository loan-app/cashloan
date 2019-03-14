package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.cl.domain.YouDunRiskReport;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

/**
 * 有盾风险评估Dao
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-25 16:26:48
 */
@RDBatisDao
public interface YouDunRiskReportMapper extends BaseMapper<YouDunRiskReport, Long> {


    /**
     * 根据用户id 获取最近一条风险评估
     * @param userId  getRecentlyYoudunRiskReport
     * @return
     */
    YouDunRiskReport getRecentlyYouDunRiskReport(Long userId);

}
