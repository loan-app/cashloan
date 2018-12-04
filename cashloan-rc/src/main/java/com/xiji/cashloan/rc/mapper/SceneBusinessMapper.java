package com.xiji.cashloan.rc.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.rc.domain.SceneBusiness;
import com.xiji.cashloan.rc.model.ManageSceneBusinessModel;
import com.xiji.cashloan.rc.model.TppServiceInfoModel;

/**
 * 场景与第三方征信接口关联关系Dao
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 创新一部：rdc@xiji.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface SceneBusinessMapper extends BaseMapper<SceneBusiness,Long> {

	/**
	 * 条件查询List
	 * 
	 * @param paramMap
	 * @return
	 */
	List<ManageSceneBusinessModel> list(Map<String, Object> paramMap);

	/**
	 * 详情查询
	 * 
	 * @param id
	 * @return
	 */
	ManageSceneBusinessModel findDetail(Long id);
	
	/**
	 * 查询第三方接口信息
	 * @return
	 */
	List<TppServiceInfoModel> findTppServiceInfo();

}
