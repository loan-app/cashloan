package com.xiji.creditrank.cr.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.creditrank.cr.domain.FactorParam;
import com.xiji.creditrank.cr.model.FactorParamModel;

/**
 * 评分因子参数Dao
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 * Copyright 杭州融都科技股份有限公司  creditrank All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface FactorParamMapper extends BaseMapper<FactorParam,Long> {

	/**
	 * 查询列表返回FactorParamModel
	 * @param param
	 * @return
	 */
	List<FactorParamModel> listSelect(Map<String, Object> param);

	/**
	 * 根据主键删除
	 * @param parseLong
	 * @return
	 */
	int deleteSelective(long id);

	/**
	 * 计算最高因子参数分值
	 * @param factorId 
	 * @return
	 */
	int findMaxScore(long factorId);

}
