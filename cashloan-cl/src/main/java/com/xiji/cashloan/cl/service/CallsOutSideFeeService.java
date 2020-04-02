package com.xiji.cashloan.cl.service;

import com.github.pagehelper.Page;
import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.cl.domain.CallsOutSideFee;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 调用外部数据收费信息Service
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-04 19:20:29
 */
public interface CallsOutSideFeeService extends BaseService<CallsOutSideFee, Long>{

    CallsOutSideFee getByTaskId(String taskId);


    /**
     * 查询收据收费列表
     * @param params
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<CallsOutSideFee> listCallsOutSideFee(Map<String, Object> params, int currentPage,
                                   int pageSize);

    /**
     * 获取总消费
     * @param params
     * @return
     */
    BigDecimal getTotalFee(Map<String, Object> params);

    /**
     * 获取余额
     * @param params
     * @return
     */
    BigDecimal getBalance(Map<String, Object> params);

    /**
     * 保存
     * @param callsOutSideFee
     * @return
     */
   int save(CallsOutSideFee callsOutSideFee);
}
