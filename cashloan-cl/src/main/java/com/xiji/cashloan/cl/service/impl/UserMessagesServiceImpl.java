package com.xiji.cashloan.cl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.domain.UserMessages;
import com.xiji.cashloan.cl.mapper.UserMessagesMapper;
import com.xiji.cashloan.cl.service.UserMessagesService;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;


/**
 * 用户资料--联系人ServiceImpl
 * 
 * @author chenxy
 * @version 1.0.0
 * @date 2017-03-04 11:54:57
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("clUserMessagesService")
public class UserMessagesServiceImpl extends BaseServiceImpl<UserMessages, Long> implements UserMessagesService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(UserMessagesServiceImpl.class);
   
    @Resource
    private UserMessagesMapper clUserMessagesMapper;


	@Override
	public BaseMapper<UserMessages, Long> getMapper() {
		return clUserMessagesMapper;
	}

	@Override
	public Page<UserMessages> listMessages(long userId, int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		Map<String,Object> searchMap = new HashMap<>();
		searchMap.put("userId", userId);
		List<UserMessages> list = clUserMessagesMapper.listSelective(searchMap);
		for (UserMessages clUserMessages : list) {
			if ("10".equals(clUserMessages.getType())) {
				clUserMessages.setType("发送");
			}else {
				clUserMessages.setType("接收");
			}
		}
		return (Page<UserMessages>)list;
	}
	
}