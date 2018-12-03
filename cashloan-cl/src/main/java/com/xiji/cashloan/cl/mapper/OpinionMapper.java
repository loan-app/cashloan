package com.xiji.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.cl.domain.Opinion;
import com.xiji.cashloan.cl.model.OpinionModel;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;


/**
 * 意见反馈表Dao
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-02-14 11:30:57
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface OpinionMapper extends BaseMapper<Opinion,Long> {
	
	List<OpinionModel> listModel(Map<String, Object> searchMap);

}