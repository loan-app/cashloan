package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.ZmModelMapper;
import com.xiji.cashloan.cl.domain.ZmModel;
import com.xiji.cashloan.cl.service.ZmModelService;


/**
 * 指迷模型分ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2019-05-05 20:09:28
 */
 
@Service("zmModelService")
public class ZmModelServiceImpl extends BaseServiceImpl<ZmModel, Long> implements ZmModelService {
	
    private static final Logger logger = LoggerFactory.getLogger(ZmModelServiceImpl.class);
   
    @Resource
    private ZmModelMapper zmModelMapper;

	@Override
	public BaseMapper<ZmModel, Long> getMapper() {
		return zmModelMapper;
	}
	
}