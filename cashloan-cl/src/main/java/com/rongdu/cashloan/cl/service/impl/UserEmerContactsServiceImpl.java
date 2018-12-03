package com.rongdu.cashloan.cl.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rongdu.cashloan.cl.domain.UserEmerContacts;
import com.rongdu.cashloan.cl.mapper.UserEmerContactsMapper;
import com.rongdu.cashloan.cl.service.UserEmerContactsService;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.service.impl.BaseServiceImpl;

/**
 * 紧急联系人表ServiceImpl
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-02-14 11:24:05
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("userEmerContactsService")
public class UserEmerContactsServiceImpl extends BaseServiceImpl<UserEmerContacts, Long> implements UserEmerContactsService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(UserEmerContactsServiceImpl.class);
   
    @Resource
    private UserEmerContactsMapper userEmerContactsMapper;

	@Override
	public BaseMapper<UserEmerContacts, Long> getMapper() {
		return userEmerContactsMapper;
	}
	
	@Override
	public List<UserEmerContacts> getUserEmerContactsByUserId(Map<String,Object> paramMap) {
		return userEmerContactsMapper.listSelective(paramMap);
	}
}