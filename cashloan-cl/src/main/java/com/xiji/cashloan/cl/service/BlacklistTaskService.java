package com.xiji.cashloan.cl.service;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.BlacklistTask;
import com.xiji.cashloan.core.common.service.BaseService;
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
    Page<BlacklistTask> page(Map<String, Object> paramMap,int current, int pageSize);
    boolean update(Map<String, Object> search);
    boolean save(BlacklistTask blacklistTask);
    public BlacklistTask findSelective(Map<String, Object> paramMap);
}
