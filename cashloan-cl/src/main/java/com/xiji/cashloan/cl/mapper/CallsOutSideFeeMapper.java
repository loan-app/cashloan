package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.cl.domain.CallsOutSideFee;

import java.util.List;
import java.util.Map;

/**
 * 调用外部数据收费信息Dao
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-04 19:20:29
 */
@RDBatisDao
public interface CallsOutSideFeeMapper extends BaseMapper<CallsOutSideFee, Long> {

    CallsOutSideFee getByTaskId(String taskId);

    /**
     * 根据条件查询收据收费列表
     * @param params
     * @return
     */
    List<CallsOutSideFee> listCallsOutSideFee(Map<String, Object> params);

}
