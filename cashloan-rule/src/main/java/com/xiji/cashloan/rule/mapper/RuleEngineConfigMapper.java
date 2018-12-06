package com.xiji.cashloan.rule.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.rule.domain.RuleEngineConfig;

/**
 * 规则引擎管理Dao
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
public interface RuleEngineConfigMapper extends BaseMapper<RuleEngineConfig,Long> {

	List<Map<String, Object>> findTable();

	List<Map<String, Object>> findColumnByName(Map<String, Object> map);

	int updateSelective(Map<String, Object> map);
	
	List<RuleEngineConfig> listSelective(Map<String, Object> search);

	int deleteByRuleId(Long id);

	int insert(RuleEngineConfig config);

	int updateByRuleEnginId(Map<String, Object> configMap);
	
	List<RuleEngineConfig> findRuleEnginConfigForBorrow(Map<String, Object> params);

	int deleteById(Long id);
	
	/**
     * 根据表名查询数据表信息
     */
	List<Map<String, Object>> findTableByName(Map<String, Object> paramMap);
	
}
