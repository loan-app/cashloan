package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.YixinScoreMapper;
import com.xiji.cashloan.cl.domain.YixinScore;
import com.xiji.cashloan.cl.service.YixinScoreService;


/**
 * 宜信综合决策报告评分ServiceImpl
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-07-23 21:46:38
 */
 
@Service("yixinScoreService")
public class YixinScoreServiceImpl extends BaseServiceImpl<YixinScore, Long> implements YixinScoreService {
	
    private static final Logger logger = LoggerFactory.getLogger(YixinScoreServiceImpl.class);
   
    @Resource
    private YixinScoreMapper yixinScoreMapper;

	@Override
	public BaseMapper<YixinScore, Long> getMapper() {
		return yixinScoreMapper;
	}
	
}