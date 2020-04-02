package com.xiji.cashloan.cl.service.impl;

import com.xiji.cashloan.cl.domain.Blacklist;
import com.xiji.cashloan.cl.mapper.BlacklistMapper;
import com.xiji.cashloan.cl.service.BlacklistService;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * 黑名单ServiceImpl
 * 
 * @author king
 * @version 1.0.0
 * @date 2018-12-10 16:29:50
 */
 
@Service("blacklistService")
public class BlacklistServiceImpl extends BaseServiceImpl<Blacklist, Long> implements BlacklistService {
	
    private static final Logger logger = LoggerFactory.getLogger(BlacklistServiceImpl.class);
   
    @Resource
    private BlacklistMapper blacklistMapper;

	@Override
	public BaseMapper<Blacklist, Long> getMapper() {
		return blacklistMapper;
	}
	
}