package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.cl.domain.OperatorReport;

/**
 * 运营商报告Dao
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-11-27 10:29:41
 */
@RDBatisDao
public interface OperatorReportMapper extends BaseMapper<OperatorReport, Long> {
    /**
     * 根据taskId查询
     * @param taskId
     * @return
     */
    OperatorReport getByTaskId(String taskId);

    OperatorReport getLastRecord(Long userId);
}
