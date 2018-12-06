package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.cl.domain.Zhima;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

/**
 * 芝麻信用Dao
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/12/03
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface ZhimaMapper extends BaseMapper<Zhima,Long> {
	
	Zhima findByUserId(long userId);

}
