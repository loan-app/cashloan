package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.cl.model.ManualRepayOrderModel;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.cl.domain.ManualRepayOrder;

import java.util.List;
import java.util.Map;

/**
 * 到期订单Dao
 *
 * @author szb
 * @version 1.0.0
 * @date 2019-03-07 16:20:42
 */
@RDBatisDao
public interface ManualRepayOrderMapper extends BaseMapper<ManualRepayOrder, Long> {


    /**
     * 分配到期订单
     * @param params
     * @return
     */
    int orderAllotUser(Map<String, Object> params);

    /**
     *
     */
    List<ManualRepayOrderModel> listModel(Map<String, Object> params);


}
