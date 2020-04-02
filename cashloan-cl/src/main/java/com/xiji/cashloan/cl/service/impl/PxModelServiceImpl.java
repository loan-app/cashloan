package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.PxModelMapper;
import com.xiji.cashloan.cl.domain.PxModel;
import com.xiji.cashloan.cl.service.PxModelService;


/**
 * 排序模型分ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2019-06-04 17:42:52
 */
 
@Service("pxModelService")
public class PxModelServiceImpl extends BaseServiceImpl<PxModel, Long> implements PxModelService {
	
    private static final Logger logger = LoggerFactory.getLogger(PxModelServiceImpl.class);
   
    @Resource
    private PxModelMapper pxModelMapper;

	@Override
	public BaseMapper<PxModel, Long> getMapper() {
		return pxModelMapper;
	}
	
}