package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.cl.domain.TongdunRespDetail;
import com.xiji.cashloan.cl.mapper.TongdunRespDetailMapper;
import com.xiji.cashloan.cl.service.TongdunRespDetailService;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;


@Service("tongdunRespDetailService")
public class TongdunRespDetailServiceImpl extends BaseServiceImpl<TongdunRespDetail, Long> implements TongdunRespDetailService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(TongdunRespDetailServiceImpl.class);
   
    @Resource
    private TongdunRespDetailMapper tongdunRespDetailMapper;

	@Override
	public BaseMapper<TongdunRespDetail, Long> getMapper() {
		return tongdunRespDetailMapper;
	}
	
}