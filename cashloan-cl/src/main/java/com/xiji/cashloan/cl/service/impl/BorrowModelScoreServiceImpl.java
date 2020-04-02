package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.BorrowModelScoreMapper;
import com.xiji.cashloan.cl.domain.BorrowModelScore;
import com.xiji.cashloan.cl.service.BorrowModelScoreService;


/**
 * 借款订单模型评分ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2019-04-18 09:49:39
 */
 
@Service("borrowModelScoreService")
public class BorrowModelScoreServiceImpl extends BaseServiceImpl<BorrowModelScore, Long> implements BorrowModelScoreService {
	
    private static final Logger logger = LoggerFactory.getLogger(BorrowModelScoreServiceImpl.class);
   
    @Resource
    private BorrowModelScoreMapper borrowModelScoreMapper;

	@Override
	public BaseMapper<BorrowModelScore, Long> getMapper() {
		return borrowModelScoreMapper;
	}
	
}