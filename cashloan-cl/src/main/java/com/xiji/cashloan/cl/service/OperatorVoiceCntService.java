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

    /**
     * 根据user_id获取最新的通话详情统计
     * @param userId
     * @param current
     * @param pageSize
     * @return
     */
    Page<OperatorVoiceCnt> page(long userId, int current, int pageSize);

    /**
     * 根据borrow_id获取订单对应通话详情
     * @param borrowId
     * @param current
     * @param pageSize
     * @return
     */
    Page<OperatorVoiceCnt> pageByBorrowId(long borrowId, int current, int pageSize);

    /**
     * 计算最近通话时间
     * @param userId
     * @param reqLogId
     */
    void lastContactTime(Long userId, Long reqLogId);
}
