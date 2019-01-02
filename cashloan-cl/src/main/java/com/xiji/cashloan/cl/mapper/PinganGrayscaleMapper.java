package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.cl.domain.PinganGrayscale;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

/**
 * 凭安染黑度统计Dao
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-26 21:28:46
 */
@RDBatisDao
public interface PinganGrayscaleMapper extends BaseMapper<PinganGrayscale, Long> {


    /**
     * 查询最近一次凭安报告
     * @return
     */
    PinganGrayscale getPinganGrayscale(Long userId);
}
