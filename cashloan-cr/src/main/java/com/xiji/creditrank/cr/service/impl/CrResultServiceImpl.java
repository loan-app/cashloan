package com.xiji.creditrank.cr.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiji.creditrank.cr.mapper.CrResultMapper;
import com.xiji.creditrank.cr.service.CrResultService;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.creditrank.cr.domain.CrResult;


/**
 * 评分结果ServiceImpl
 * 
 * @author ctt
 * @version 1.0.0
 * @date 2017-01-05 16:22:54
 * Copyright 杭州融都科技股份有限公司  creditrank All Rights Reserved
 * 官方网站：www.xiji.com
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