package com.xiji.creditrank.cr.mapper;

import org.apache.ibatis.annotations.Param;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.creditrank.cr.domain.RankDetail;

/**
 * 评分卡等级详情表Dao
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-02-06 11:27:25
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface RankDetailMapper extends BaseMapper<RankDetail,Long> {

	/**
	 * 根据等级父ID和评分找到对应的等级详情信息
	 * @param id
	 * @param score
	 * @return
	 */
    RankDetail findByParentIdAndScore(@Param("id") Long id, @Param("score") Integer score);

}
