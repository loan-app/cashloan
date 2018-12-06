package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.cl.domain.OperatorRespDetail;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

/**
 * 运营商认证通知详情表Dao
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/12/03
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface OperatorRespDetailMapper extends BaseMapper<OperatorRespDetail,Long> {

    /**
     * 根据taskId查询
     * @param taskId
     * @return
     */
    OperatorRespDetail getByTaskId(String taskId);

}
