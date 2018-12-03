package com.rongdu.creditrank.cr.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;
import com.rongdu.creditrank.cr.domain.CreditType;
import com.rongdu.creditrank.cr.model.CreditRatingModel;
import com.rongdu.creditrank.cr.model.CreditTypeModel;
/**
 * 额度类型管理Dao
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-01-18 16:43:13
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface CreditTypeMapper extends BaseMapper<CreditType,Long> {

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
	List<CreditRatingModel> findEditBorrowType(@Param("typeId")Long typeId);
	
	/**
	 * 根据借款类型查询额度类型记录
	 * @param borrowTypeId
	 * @return
	 */
	CreditTypeModel findByBorrowTypeId(@Param("borrowTypeId")Long borrowTypeId);
}
