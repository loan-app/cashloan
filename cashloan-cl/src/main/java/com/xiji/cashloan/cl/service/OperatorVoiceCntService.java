package com.xiji.cashloan.cl.service;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.OperatorVoiceCnt;
import com.xiji.cashloan.core.common.service.BaseService;
import java.util.Date;

/**
 * 通话详情统计Service
 * 
 * @author king
 * @version 1.0.0
 * @date 2018-12-17 11:31:50
 */
public interface OperatorVoiceCntService extends BaseService<OperatorVoiceCnt, Long>{
    void paserReportDetail(String res, Long userId, Date createTime, Long reqLogId);
    Page<OperatorVoiceCnt> page(long userId, int current, int pageSize);
}
