package com.xiji.creditrank.cr.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiji.creditrank.cr.domain.CreditType;
import com.xiji.creditrank.cr.mapper.CreditTypeMapper;
import com.xiji.creditrank.cr.model.CreditRatingModel;
import com.xiji.creditrank.cr.service.CreditTypeService;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.creditrank.cr.model.CreditTypeModel;


/**
 * 额度类型管理ServiceImpl
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("creditTypeService")
public class CreditTypeServiceImpl extends BaseServiceImpl<CreditType, Long> implements CreditTypeService {
	
   
    @Resource
    private CreditTypeMapper creditTypeMapper;


	@Override
	public BaseMapper<CreditType, Long> getMapper() {
		return creditTypeMapper;
	}

	@Override
	public List<CreditTypeModel> findAllCreditType() {
		
		List<CreditType> list = creditTypeMapper.listSelective(null);
		List<CreditTypeModel> rtList = new ArrayList<CreditTypeModel>();
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				CreditTypeModel info = creditTypeMapper.findCreditTypeInfo(list.get(i));
				rtList.add(info);
			}
		}
		return rtList;
	}

	@Override
	public CreditTypeModel findCreditTypeInfo(CreditType creditType) {
		if(creditType!=null && creditType.getId()!=null){
			return creditTypeMapper.findCreditTypeInfo(creditType);
		}else{
			return null;
		}
		
	}

	@Override
	public List<Map<Long, String>> findUnUsedBorrowType() {
		return creditTypeMapper.findUnUsedBorrowType();
	}

	@Override
	public List<CreditRatingModel> findEditBorrowType(Long typeId) {
		return creditTypeMapper.findEditBorrowType(typeId);
	}

	@Override
	public List<CreditType> findCreditType(Map<String, Object> params) {
		return creditTypeMapper.listSelective(params);
	}
	
}