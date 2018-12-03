package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.MagicBlackGrayMapper;
import com.xiji.cashloan.cl.domain.MagicBlackGray;
import com.xiji.cashloan.cl.service.MagicBlackGrayService;


/**
 * 魔杖2.0-黑灰名单记录表ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-02 15:31:57
 */
 
@Service("magicBlackGrayService")
public class MagicBlackGrayServiceImpl extends BaseServiceImpl<MagicBlackGray, Long> implements MagicBlackGrayService {
	
    private static final Logger logger = LoggerFactory.getLogger(MagicBlackGrayServiceImpl.class);
   
    @Resource
    private MagicBlackGrayMapper magicBlackGrayMapper;

	@Override
	public BaseMapper<MagicBlackGray, Long> getMapper() {
		return magicBlackGrayMapper;
	}
	
}