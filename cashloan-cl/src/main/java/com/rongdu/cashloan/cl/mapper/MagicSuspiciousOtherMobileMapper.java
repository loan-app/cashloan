package com.rongdu.cashloan.cl.mapper;

import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;
import com.rongdu.cashloan.cl.domain.MagicSuspiciousOtherMobile;

import java.util.List;

/**
 * 魔杖2.0报告-基础信息表Dao
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:05
 */
@RDBatisDao
public interface MagicSuspiciousOtherMobileMapper extends BaseMapper<MagicSuspiciousOtherMobile, Long> {

    int saveBatch(List<MagicSuspiciousOtherMobile> magicSuspiciousOtherMobileList);

}
