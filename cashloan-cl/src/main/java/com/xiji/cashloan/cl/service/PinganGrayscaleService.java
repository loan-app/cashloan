package com.xiji.cashloan.cl.service;

import com.xiji.cashloan.cl.domain.PinganGrayscale;
import com.xiji.cashloan.core.common.service.BaseService;

/**
 * 凭安染黑度统计Service
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-26 21:28:46
 */
public interface PinganGrayscaleService extends BaseService<PinganGrayscale, Long>{

    /**
     * 查询最近一次凭安报告
     * @return
     */
    PinganGrayscale getPinganGrayscale(Long userId);
}
