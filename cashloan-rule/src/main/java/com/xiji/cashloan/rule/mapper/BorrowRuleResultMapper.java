package com.xiji.cashloan.rule.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.rule.model.ManageReviewModel;
import org.apache.ibatis.annotations.Param;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.rule.domain.BorrowRuleResult;
import com.xiji.cashloan.rule.model.ManageRuleResultModel;

/**
 * 规则匹配结果Dao
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface BorrowRuleResultMapper extends BaseMapper<BorrowRuleResult,Long> {

	/**
	 * 查询规则名称
	 * @param borrowId
	 * @return
	 */
	List<ManageReviewModel> findRuleResult(long borrowId);

	/**
	 * 查询审核信息
	 * @param borrowId
	 * @return
	 */
	List<ManageRuleResultModel> findResult(long borrowId);

	/**
	 * 查询审核明细
	 * @param borrowId
	 * @param id 
	 * @return
	 */
	List<BorrowRuleResult> findRule(long borrowId, long id);

	List<BorrowRuleResult> findRule(Map<String, Object> search);
	
	/**
	 * 根据规则ID统计规则得分
	 * @param ruleId
	 * @return
	 */
	Integer sumScoreByRuleId(@Param("ruleId") Long ruleId, @Param("borrowId") Long borrowId);


}
