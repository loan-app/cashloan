package com.xiji.creditrank.cr.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiji.creditrank.cr.domain.CreditLog;
import com.xiji.creditrank.cr.model.CreditLogModel;
import com.xiji.creditrank.cr.service.CreditLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.creditrank.cr.mapper.CreditLogMapper;


/**
 * 授信额度记录ServiceImpl
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("creditLogService")
public class CreditLogServiceImpl extends BaseServiceImpl<CreditLog, Long> implements CreditLogService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CreditLogServiceImpl.class);
   
    @Resource
    private CreditLogMapper creditLogMapper;




	@Override
	public BaseMapper<CreditLog, Long> getMapper() {
		return creditLogMapper;
	}
	
	@Override
	public int save(CreditLog log) {
		return creditLogMapper.save(log);
	}

	@Override
	public Page<CreditLogModel> page(Map<String, Object> searchMap,
									 int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<CreditLogModel> list = creditLogMapper.findLog(searchMap);
		return (Page<CreditLogModel>)list;
	}
	
}