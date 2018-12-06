package com.xiji.cashloan.rule.service;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.rule.domain.RuleEngineInfo;

/**
 * 规则评分设置管理Service
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface RuleEngineInfoService extends BaseService<RuleEngineInfo, Long> {
	/**
	 * 保存
	 * @param rule
	 * @param list
	 * @return
	 */
	int saveIntegralInfo(Map<String, Object> rule, List list);
	/**
	 * 查询
	 * @param search
	 * @return
	 */
	List<RuleEngineInfo> findByMap(Map<String, Object> search);
}
