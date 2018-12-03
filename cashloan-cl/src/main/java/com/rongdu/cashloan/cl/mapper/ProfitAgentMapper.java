package com.rongdu.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.rongdu.cashloan.cl.domain.ProfitAgent;
import com.rongdu.cashloan.cl.model.ManageAgentListModel;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;

/**
 * 代理用户信息Dao
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-02-18 16:24:45
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface ProfitAgentMapper extends BaseMapper<ProfitAgent,Long> {

	/**
	 * 查询代理商
	 * @param map
	 * @return
	 */
	List<ManageAgentListModel> findAgentList(Map<String, Object> map);
	/**
	 * 更新子代理商
	 * @param map
	 * 
	 */
	void updateSelectiveChild(Map<String, Object> map);

}
