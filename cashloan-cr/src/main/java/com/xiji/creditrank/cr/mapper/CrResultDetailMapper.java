package com.xiji.creditrank.cr.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.creditrank.cr.domain.CrResultDetail;
import com.xiji.creditrank.cr.model.CrResultDetailModel;
import com.xiji.creditrank.cr.model.CrResultFactorDetail;
import com.xiji.creditrank.cr.model.CrResultItemDetail;
import org.apache.ibatis.annotations.Param;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

/**
 * 评分结果Dao
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
public interface CrResultDetailMapper extends BaseMapper<CrResultDetail,Long> {

	List<CrResultDetail> listResultDetail(Map<String, Object> secreditrankhMap);

	/**
	 * 统计因子得分
	 * @param resultId
	 * @return
	 */
	List<CrResultDetail> countFactorScore(@Param("resultId")Long resultId);
	
	/**
	 * 统计项目得分
	 * @param resultId
	 * @return
	 */
	List<CrResultDetail> countItemScore(@Param("resultId")Long resultId);
	
	/**
	 * 统计评分卡得分
	 * @param resultId
	 * @return
	 */
	List<CrResultDetail> countCardScore(@Param("resultId")Long resultId);
	
	/**
	 * 保存统计结果
	 * @param list
	 */
	void saveCountScore(List<CrResultDetail> list);
	
	/**
	 * 根据级别查询评分结果
	 * @param resultId
	 * @param level
	 * @return
	 */
	List<CrResultDetailModel> findDetail(@Param("resultId")Long resultId, @Param("level")String level);
	List<CrResultFactorDetail> findFactorDetail(@Param("resultId")Long resultId, @Param("level")String level);
	List<CrResultItemDetail> findItemDetail(@Param("resultId")Long resultId, @Param("level")String level);
}
