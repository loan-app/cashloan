package com.xiji.cashloan.cl.mapper;

import java.util.Map;

import com.xiji.cashloan.cl.domain.UrgeRepayOrderLog;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

/**
 * 催款记录表Dao
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
public interface UrgeRepayOrderLogMapper extends BaseMapper<UrgeRepayOrderLog,Long> {
	
	/**
	 * 统计数量
	 * @param params
	 * @return
	 */
	int countLog(Map<String, Object> params);

}
