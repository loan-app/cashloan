package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.cl.model.ManualReviewCountModel;
import com.xiji.cashloan.cl.model.ManualReviewOrderModel;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.cl.domain.ManualReviewOrder;

import java.util.List;
import java.util.Map;

/**
 * 人工审核订单Dao
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-16 17:00:19
 */
@RDBatisDao
public interface ManualReviewOrderMapper extends BaseMapper<ManualReviewOrder, Long> {

    List<ManualReviewCountModel> listSysUserByRole(Map<String, Object> params);


    int countOrder(Map<String, Object> params);

    int countByTime(Map<String, Object> params);

    List<ManualReviewOrderModel> list(Map<String, Object> params);

    int orderAllotUser(Map<String, Object> params);

    int reviewState(Map<String, Object> map);
}
