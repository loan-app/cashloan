package com.xiji.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.cl.domain.PayRespLog;
import com.xiji.cashloan.cl.model.ManagePayRespLogModel;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

/**
 * 支付响应记录Mapper
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
public interface PayRespLogMapper extends BaseMapper<PayRespLog,Long> {

	/**
	 * 分页查询
	 * 
	 * @param searchMap
	 * @return
	 */
	List<ManagePayRespLogModel> page(Map<String, Object> searchMap);

	/**
	 * 查询详情
	 * 
	 * @param id
	 * @return
	 */
	ManagePayRespLogModel findDetail(Long id);
}
