package com.rongdu.cashloan.rc.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tool.util.DateUtil;

import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.service.impl.BaseServiceImpl;
import com.rongdu.cashloan.core.common.util.ShardTableUtil;
import com.rongdu.cashloan.rc.domain.SimpleContactCount;
import com.rongdu.cashloan.rc.mapper.SimpleContactCountMapper;
import com.rongdu.cashloan.rc.service.SimpleContactCountService;


/**
 * 风控数据统计-（简）通讯录统计ServiceImpl
 * 
 * @author xx
 * @version 1.0.0
 * @date 2017-07-06 18:12:49
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("simpleContactCountService")
public class SimpleContactCountServiceImpl extends BaseServiceImpl<SimpleContactCount, Long> implements SimpleContactCountService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(SimpleContactCountServiceImpl.class);
   
    @Resource
    private SimpleContactCountMapper simpleContactCountMapper;

	@Override
	public BaseMapper<SimpleContactCount, Long> getMapper() {
		return simpleContactCountMapper;
	}

	@Override
	public int countOne(long userId) {
		String tableName = ShardTableUtil.generateTableNameById("cl_user_contacts", userId, 30000);
		int count = simpleContactCountMapper.countOne(tableName, userId);
		
		SimpleContactCount simpleContactCount = new SimpleContactCount();
		simpleContactCount.setUserId(userId);
		simpleContactCount.setCountOne(count);
		simpleContactCount.setCreateTime(DateUtil.getNow());
		
		return simpleContactCountMapper.save(simpleContactCount);
	}
	
}