package com.rongdu.cashloan.cl.service;

import com.rongdu.cashloan.cl.domain.OperatorRespDetail;
import com.rongdu.cashloan.core.common.service.BaseService;

import java.util.Map;

/**
 * 运营商认证通知详情表Service
 * 
 * @author xx
 * @version 1.0.0
 * @date 2017-05-17 12:38:22
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
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
