package com.xiji.creditrank.cr.service;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.creditrank.cr.domain.CreditType;
import com.xiji.creditrank.cr.model.CreditRatingModel;
import com.xiji.creditrank.cr.model.CreditTypeModel;

/**
 * 额度类型管理Service
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface CreditTypeService extends BaseService<CreditType, Long>{
	
	/**
	 * 根据条件查询额度类型
	 * @param params
	 * @return
	 */
	List<CreditType> findCreditType(Map<String,Object> params);

	/**
	 * 查询所有额度类型
	 * @return
	 */
	List<CreditTypeModel> findAllCreditType();
	
	/**
     * 查询详情信息，必须包含ID
     * @param creditType
     * @return
     */
	CreditTypeModel findCreditTypeInfo(CreditType creditType);
	
	/**
	 * 查询未被额度类型关联的借款类型
	 * @return
	 */
	List<Map<Long, String>> findUnUsedBorrowType();
	
	/**
	 * 查询额度类型编辑页面可以显示的借款类型
	 * @param typeId
	 * @return
	 */
	List<CreditRatingModel> findEditBorrowType(Long typeId);
}
