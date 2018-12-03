package com.rongdu.creditrank.cr.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.service.impl.BaseServiceImpl;
import com.rongdu.creditrank.cr.domain.CrResult;
import com.rongdu.creditrank.cr.mapper.CrResultMapper;
import com.rongdu.creditrank.cr.service.CrResultService;


/**
 * 评分结果ServiceImpl
 * 
 * @author ctt
 * @version 1.0.0
 * @date 2017-01-05 16:22:54
 * Copyright 杭州融都科技股份有限公司  creditrank All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("crResultService")
public class CrResultServiceImpl extends BaseServiceImpl<CrResult, Long> implements CrResultService {
	
    @Resource
    private CrResultMapper crResultMapper;

	@Override
	public BaseMapper<CrResult, Long> getMapper() {
		return crResultMapper;
	}

	@Override
	public Map<String, Object> findUserResult(Long userId) {
		return crResultMapper.findUserResult(userId);
	}

	@Override
	public List<CrResult> findAllBorrowTypeResult(Long userId) {
		return crResultMapper.findAllBorrowTypeResult(userId);
	}
}