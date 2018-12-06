package com.xiji.creditrank.cr.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.creditrank.cr.domain.CrResult;
import com.xiji.creditrank.cr.model.CreditRatingModel;

/**
 * 评分结果Dao
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 * Copyright  All Rights Reserved
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface CrResultMapper extends BaseMapper<CrResult,Long> {

    List<CreditRatingModel> queryCreditRating(@Param("borrowTypeId") Long borrowTypeId,@Param("type") Integer type);

    CrResult findByConsumerNo(@Param("consumerNo")String consumerNo);
    
    /**
	 * 统计用户的总评分和总额度
	 * @param userId
	 * @return
	 */
	public Map<String,Object> findUserResult(Long userId);
	
	/**
	 * 查询用户各借款类型的总额度
	 * @param userId
	 * @return
	 */
	public List<CrResult> findAllBorrowTypeResult(Long userId);
	
	/**
	 * 查询指定类型的
	 * @param consumerNo
	 * @param borrowTypeId
	 * @return
	 */
	public CrResult findCreditTypeResult(@Param("consumerNo")String consumerNo,@Param("creditTypeId")Long creditTypeId);
	
	/**
     * 自动审批查找需要比对的值
     * @param sql
     * @return
     */
    String findValidValue(@Param("statement")String statement);
    
}
