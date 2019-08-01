package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.HelipayUserMapper;
import com.xiji.cashloan.cl.domain.HelipayUser;
import com.xiji.cashloan.cl.service.HelipayUserService;


/**
 * 合利宝用户注册信息ServiceImpl
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-07-30 15:31:02
 */
 
@Service("helipayUserService")
public class HelipayUserServiceImpl extends BaseServiceImpl<HelipayUser, Long> implements HelipayUserService {
	
    private static final Logger logger = LoggerFactory.getLogger(HelipayUserServiceImpl.class);
   
    @Resource
    private HelipayUserMapper helipayUserMapper;

	@Override
	public BaseMapper<HelipayUser, Long> getMapper() {
		return helipayUserMapper;
	}
	
}