package com.xiji.cashloan.cl.service;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.ManualReviewOrder;
import com.xiji.cashloan.cl.model.ManualReviewCountModel;
import com.xiji.cashloan.cl.model.ManualReviewOrderModel;
import com.xiji.cashloan.core.common.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 人工审核订单Service
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-16 17:00:19
 */
public interface ManualReviewOrderService extends BaseService<ManualReviewOrder, Long>{

    Page<ManualReviewCountModel> memberCount(Map<String, Object> params, int current, int pageSize);

    Page<ManualReviewOrderModel> list(Map<String, Object> params, int current, int pageSize);

    int orderAllotUser(Map<String, Object> params);

    /**
     * 待分配订单自动分配
     * @return
     */
    int automaticDistributionOrder();

    /**
     * 查询人工审核订单list
     * @param params
     * @return
     */
    List<ManualReviewOrder> listManualReviewOrder(Map<String,Object> params);

    /**
     * 批量更新审核订单
     * @param manualReviewOrders
     * @return
     */
    int batchUpdate(List<ManualReviewOrder> manualReviewOrders);

    /**
     * 复借人工审核订单
     * @return
     */
    List<ManualReviewOrder> listAgainBorrowOrder(Map<String, Object> map);
}
