package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.cl.domain.MagicMultipointQueriedInfo;

import java.util.List;

/**
 * 魔杖2.0报告-基础信息表Dao
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:03
 */
@RDBatisDao
public interface MagicMultipointQueriedInfoMapper extends BaseMapper<MagicMultipointQueriedInfo, Long> {

    int saveBatch(List<MagicMultipointQueriedInfo> queriedInfos);

}
