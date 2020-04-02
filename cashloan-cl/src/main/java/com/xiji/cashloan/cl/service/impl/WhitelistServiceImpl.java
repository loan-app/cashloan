package com.xiji.cashloan.cl.service.impl;

import com.xiji.cashloan.cl.domain.Whitelist;
import com.xiji.cashloan.cl.mapper.WhitelistMapper;
import com.xiji.cashloan.cl.service.WhitelistService;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * 白名单ServiceImpl
 * 
 * @author king
 * @version 1.0.0
 * @date 2018-12-10 19:12:43
 */
 
@Service("whitelistService")
public class WhitelistServiceImpl extends BaseServiceImpl<Whitelist, Long> implements WhitelistService {
	
    private static final Logger logger = LoggerFactory.getLogger(WhitelistServiceImpl.class);
   
    @Resource
    private WhitelistMapper whitelistMapper;

	@Override
	public BaseMapper<Whitelist, Long> getMapper() {
		return whitelistMapper;
	}
	
}