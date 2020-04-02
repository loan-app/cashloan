package com.xiji.cashloan.core.service;

import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.core.domain.BankCardBin;

import java.util.List;
import java.util.Map;

/**
 * 卡binService
 * 
 * @author wjs
 * @version 1.0.0
 * @date 2019-01-25 17:50:22
 */
public interface BankCardBinService extends BaseService<BankCardBin, Long>{
    List<BankCardBin> listSelective(Map<String, Object> paramMap);
}
