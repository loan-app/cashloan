package com.rongdu.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.service.impl.BaseServiceImpl;
import com.rongdu.cashloan.cl.mapper.MagicMultipointQueriedAnalyzeMapper;
import com.rongdu.cashloan.cl.domain.MagicMultipointQueriedAnalyze;
import com.rongdu.cashloan.cl.service.MagicMultipointQueriedAnalyzeService;


/**
 * 魔杖2.0报告-基础信息表ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:05
 */
 
@Service("magicMultipointQueriedAnalyzeService")
public class MagicMultipointQueriedAnalyzeServiceImpl extends BaseServiceImpl<MagicMultipointQueriedAnalyze, Long> implements MagicMultipointQueriedAnalyzeService {
	
    private static final Logger logger = LoggerFactory.getLogger(MagicMultipointQueriedAnalyzeServiceImpl.class);
   
    @Resource
    private MagicMultipointQueriedAnalyzeMapper magicMultipointQueriedAnalyzeMapper;

	@Override
	public BaseMapper<MagicMultipointQueriedAnalyze, Long> getMapper() {
		return magicMultipointQueriedAnalyzeMapper;
	}
	
}