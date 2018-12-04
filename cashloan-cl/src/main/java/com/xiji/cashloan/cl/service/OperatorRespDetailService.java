package com.xiji.cashloan.cl.service;

import com.xiji.cashloan.cl.domain.OperatorRespDetail;
import com.xiji.cashloan.core.common.service.BaseService;

import java.util.Map;

/**
 * 运营商认证通知详情表Service
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.xiji.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface OperatorRespDetailService extends BaseService<OperatorRespDetail, Long>{
    /**
     * 根据taskId查询详情
     * @param taskId
     * @return
     */
    public OperatorRespDetail getByTaskId(String taskId);

    /**
     * 更新
     * @param params
     * @return
     */
    public int updateSelective(Map<String, Object> params);
}
