package com.xiji.creditrank.cr.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.creditrank.cr.domain.Rank;
import com.xiji.creditrank.cr.model.RankModel;

/**
 * 评分等级Dao
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
public interface RankMapper extends BaseMapper<Rank,Long> {

    /**
     * 查询等级
     * @param cardId
     * @param score
     * @return
     */
	List<Rank> findRankLeve(Long cardId,Integer score);

	/**
	 * 评分等级名称列表
	 * @return
	 */
	List<RankModel> countList();

	/**
	 * 删除一行
	 * @param id
	 * @return
	 */
	int deleteSelective(long id);

	List<RankModel> countList(Map<String, Object> search);
	
	/**
	 * 查询所有评分等级
	 * @return
	 */
	List<Rank> findAll();
}
