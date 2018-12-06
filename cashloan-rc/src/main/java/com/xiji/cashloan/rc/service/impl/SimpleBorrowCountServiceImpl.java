package com.xiji.cashloan.rc.service.impl;

import javax.annotation.Resource;

import com.xiji.cashloan.rc.mapper.SimpleBorrowCountMapper;
import com.xiji.cashloan.rc.service.SimpleBorrowCountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tool.util.DateUtil;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.rc.domain.SimpleBorrowCount;


/**
 * 风控数据统计-（简）借款统计ServiceImpl
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("simpleBorrowCountService")
public class SimpleBorrowCountServiceImpl extends BaseServiceImpl<SimpleBorrowCount, Long> implements SimpleBorrowCountService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(SimpleBorrowCountServiceImpl.class);
   
    @Resource
    private SimpleBorrowCountMapper simpleBorrowCountMapper;

	@Override
	public BaseMapper<SimpleBorrowCount, Long> getMapper() {
		return simpleBorrowCountMapper;
	}

	@Override
	public int countOne(long userId) {
		int count = simpleBorrowCountMapper.countOne(userId);
		
		SimpleBorrowCount simpleBorrowCount = new SimpleBorrowCount();
		simpleBorrowCount.setUserId(userId);
		simpleBorrowCount.setCountOne(count);
		simpleBorrowCount.setCreateTime(DateUtil.getNow());
		
//		count = simpleBorrowCountMapper.countTwo(userId);
//		simpleBorrowCount.setCountTwo(count);
//		
//		count = simpleBorrowCountMapper.countThree(userId);
//		simpleBorrowCount.setCountThree(count);
//		
//		count = simpleBorrowCountMapper.countFour(userId);
//		simpleBorrowCount.setCountFour(count);
		
		return simpleBorrowCountMapper.save(simpleBorrowCount);
	}
}