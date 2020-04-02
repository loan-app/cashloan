package com.xiji.cashloan.cl.service;

import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.cl.domain.XinyanXwld;

/**
 * 行为雷达Service
 * 
 * @author szb
 * @version 1.0.0
 * @date 2019-01-16 20:25:23
 */
public interface XinyanXwldService extends BaseService<XinyanXwld, Long>{

    XinyanXwld getByBorrowId(Long borrowId);
}
