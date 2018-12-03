package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.cl.domain.OperatorRespDetail;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

/**
 * 运营商认证通知详情表Dao
 * 
 * @author xx
 * @version 1.0.0
 * @date 2017-05-17 12:38:22
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.xiji.com
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
