package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.cl.domain.MagicSuspiciousOtherIdcard;

import java.util.List;

/**
 * 魔杖2.0报告-身份证存疑Dao
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 11:56:46
 */
@RDBatisDao
public interface MagicSuspiciousOtherIdcardMapper extends BaseMapper<MagicSuspiciousOtherIdcard, Long> {

    int saveBatch(List<MagicSuspiciousOtherIdcard> magicSuspiciousOtherIdcards);

}
