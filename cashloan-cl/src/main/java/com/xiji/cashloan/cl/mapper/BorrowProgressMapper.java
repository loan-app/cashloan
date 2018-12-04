package com.xiji.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.cl.domain.BorrowProgress;
import com.xiji.cashloan.cl.model.BorrowProgressModel;
import com.xiji.cashloan.cl.model.ManageBorrowProgressModel;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

/**
 * 借款进度表Dao
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/12/03
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface BorrowProgressMapper extends BaseMapper<BorrowProgress,Long> {
	
	/**
	 * 根据条件查询第一条
	 * @param params
	 * @return
	 */
	BorrowProgress findFirst(Map<String, Object> params);

	/**
	 * 首页查询进度
	 * 
	 * @param bpMap
	 * @return
	 */
	List<BorrowProgressModel> listIndex(Map<String, Object> bpMap);

	/**
	 * 后台借款进度列表
	 * 
	 * @param params
	 * @return
	 */
	List<ManageBorrowProgressModel> listModel(Map<String, Object> params);

	/**
	 * 借款进度查询
	 * 
	 * @param bpMap
	 * @return
	 */
	List<BorrowProgressModel> listProgress(Map<String, Object> bpMap);

	/**
	 * 查询借款进度是否有逾期和坏账进度数
	 * 
	 * @param borrowId
	 * @return
	 */
	int isNormalBorrowProgress(long borrowId);

}
