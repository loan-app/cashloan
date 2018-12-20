package com.xiji.cashloan.cl.service;

import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.cl.domain.BlacklistTask;
import java.util.List;
import java.util.Map;

/**
 * 黑名单任务Service
 * 
 * @author king
 * @version 1.0.0
 * @date 2018-12-20 15:44:09
 */
public interface BlacklistTaskService extends BaseService<BlacklistTask, Long>{
    List<BlacklistTask> listSelective(Map<String, Object> paramMap);
}
