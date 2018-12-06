package com.xiji.cashloan.cl.mapper;

import java.util.Map;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.cl.domain.FourElementsLog;

/**
 * 四要素认证记录Dao
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/12/03
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface FourElementsLogMapper extends BaseMapper<FourElementsLog, Long> {
	/**
	 * 统计
	 * @param  paramMap
	 * @return
	 */
	int count(Map<String, Object> paramMap);

}
