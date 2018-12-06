package com.xiji.creditrank.cr.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.creditrank.cr.domain.CreditType;
import com.xiji.creditrank.cr.model.CreditRatingModel;
import org.apache.ibatis.annotations.Param;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.creditrank.cr.model.CreditTypeModel;
/**
 * 额度类型管理Dao
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
