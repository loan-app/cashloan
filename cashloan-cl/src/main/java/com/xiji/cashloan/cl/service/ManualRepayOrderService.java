package com.xiji.cashloan.cl.service;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.model.ManualRepayOrderModel;
import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.cl.domain.ManualRepayOrder;

import java.util.Map;

/**
 * 到期订单Service
 *
 * @author szb
 * @version 1.0.0
 * @date 2019-03-07 16:20:42
 */
public interface ManualRepayOrderService extends BaseService<ManualRepayOrder, Long>{

    int orderAllotUser(Map<String, Object> params);

    Page<ManualRepayOrderModel> list(Map<String, Object> params, int current, int pageSize);

}
