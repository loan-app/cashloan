package com.xiji.cashloan.cl.service;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.OperatorVoice;
import com.xiji.cashloan.core.common.service.BaseService;

import java.util.Map;

/**
 * 魔蝎运营商数据-通话记录Service
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-11-24 14:50:00
 */
public interface OperatorVoiceService extends BaseService<OperatorVoice, Long> {

    /**
     * (分表)查询通话记录
     * @param params
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<OperatorVoice> findShardPage(Map<String, Object> params, int currentPage, int pageSize);
}
