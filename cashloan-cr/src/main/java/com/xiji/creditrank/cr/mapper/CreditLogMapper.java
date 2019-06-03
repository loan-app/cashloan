package com.xiji.creditrank.cr.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.creditrank.cr.domain.CreditLog;
import com.xiji.creditrank.cr.model.CreditLogModel;

/**
 * 授信额度记录Dao
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface CreditLogMapper extends BaseMapper<CreditLog,Long> {

	/**
	 * 分页查询
	 * @param searchMap
	 * @return
	 */
	List<CreditLogModel> findLog(Map<String, Object> searchMap);

	CreditLog findByConsumerno(Map<String, Object> paramMap);

}
