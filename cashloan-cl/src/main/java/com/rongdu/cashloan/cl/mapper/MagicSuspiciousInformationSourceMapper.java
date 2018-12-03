package com.rongdu.cashloan.cl.mapper;

import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;
import com.rongdu.cashloan.cl.domain.MagicSuspiciousInformationSource;

import java.util.List;

/**
 * 魔杖2.0报告-基础信息表Dao
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:04
 */
@RDBatisDao
public interface MagicSuspiciousInformationSourceMapper extends BaseMapper<MagicSuspiciousInformationSource, Long> {

    int saveBatch(List<MagicSuspiciousInformationSource> magicSuspiciousInformationSources);

}
