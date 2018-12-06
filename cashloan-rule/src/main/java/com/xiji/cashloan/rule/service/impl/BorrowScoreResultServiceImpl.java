package com.xiji.cashloan.rule.service.impl;

import javax.annotation.Resource;

import com.xiji.cashloan.rule.domain.BorrowScoreResult;
import com.xiji.cashloan.rule.mapper.BorrowScoreResultMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.rule.service.BorrowScoreResultService;

/**
 * 决策引擎执行记录ServiceImpl
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("borrowScoreResultService")
public class BorrowScoreResultServiceImpl extends BaseServiceImpl<BorrowScoreResult, Long> implements BorrowScoreResultService {
	
    private static final Logger logger = LoggerFactory.getLogger(BorrowScoreResultServiceImpl.class);
   
    @Resource
    private BorrowScoreResultMapper borrowScoreResultMapper;

	@Override
	public BaseMapper<BorrowScoreResult, Long> getMapper() {
		return borrowScoreResultMapper;
	}
	
}