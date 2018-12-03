package com.rongdu.cashloan.cl.mapper;

import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;
import com.rongdu.cashloan.cl.domain.MagicUntrustedDetail;

import java.util.List;

/**
 * 魔杖2.0报告-基础信息表Dao
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:03
 */
@RDBatisDao
public interface MagicUntrustedDetailMapper extends BaseMapper<MagicUntrustedDetail, Long> {

    int saveBatch(List<MagicUntrustedDetail> magicUntrustedDetails);

}
