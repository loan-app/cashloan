package com.xiji.creditrank.cr.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.creditrank.cr.domain.Credit;
import com.xiji.creditrank.cr.model.CreditModel;

/**
 * 授信额度管理Dao
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
public interface CreditMapper extends BaseMapper<Credit,Long> {
	
	/**
	 * 更新额度
	 * @param map
	 * @return
	 */
	int updateAmount(Map<String, Object> map);

	int addNum(Map<String, Object> map);

	int subtractNum(Map<String, Object> map);
    /**
     * 更新总额度
     * @param map
     * @return
     */
	int updateTotal(Map<String, Object> map);

	/**
	 * 列表查询返回CreditModel
	 * @param searchMap
	 * @return
	 */
	List<CreditModel> page(Map<String,Object> searchMap);

	/**
	 * 根据consumerNo查询
	 * @param consumerNo
	 * @return
	 */
	Credit findByConsumerNo(String consumerNo);
	
	/**
	 * 查询用户所有额度类型
	 * @param searchMap
	 * @return
	 */
	List<CreditModel> listAll(Map<String, Object> searchMap);
	
	
	/**
	 * 用户信用额度查询
	 * @param searchMap
	 * @return
	 */
	List<CreditModel> creditList(Map<String, Object> searchMap);
	
	/**
	 * 根据userId修改额度，提额/逾期恢复到原额度
	 * @param map
	 * @return
	 */
	int updateByUserId(Map<String, Object> map);

	/**
	 * 减少总额度与可用额度
	 * @param change
	 * @param d 
	 * @return
	 */
	void reduceUpdate(Map<String, Double> map);
	
	/**
	 * 增加总额度和可用额度
	 * @param change
	 */
	void addUpdate(Map<String, Double> map);
	
	/**
	 * 平衡额度
	 */
	void balance();

	/**
	 * 修改最大额度
	 * @param map
	 * @return
	 */
	int updateByConsumerNo(Map<String, Object> map);
}
