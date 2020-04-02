package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.MagicRiskQqGroupMapper;
import com.xiji.cashloan.cl.domain.MagicRiskQqGroup;
import com.xiji.cashloan.cl.service.MagicRiskQqGroupService;


/**
 * 魔杖2.0报告-基础信息表ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:05
 */
 
@Service("magicRiskQqGroupService")
public class MagicRiskQqGroupServiceImpl extends BaseServiceImpl<MagicRiskQqGroup, Long> implements MagicRiskQqGroupService {
	
    private static final Logger logger = LoggerFactory.getLogger(MagicRiskQqGroupServiceImpl.class);
   
    @Resource
    private MagicRiskQqGroupMapper magicRiskQqGroupMapper;

	@Override
	public BaseMapper<MagicRiskQqGroup, Long> getMapper() {
		return magicRiskQqGroupMapper;
	}
	
}