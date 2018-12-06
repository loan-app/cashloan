package com.xiji.cashloan.cl.service;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.model.CreditLoanUserModel;
import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.cl.domain.MagicReqDetail;

import java.util.Map;

/**
 * 魔杖2.0-请求详情Service
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-05 17:23:11
 */
public interface MagicReqDetailService extends BaseService<MagicReqDetail, Long>{

    Page<CreditLoanUserModel> listUser(Map<String, Object> params, int currentPage, int pageSize);

    MagicReqDetail getLastRecord(Long userId, int type);
}
