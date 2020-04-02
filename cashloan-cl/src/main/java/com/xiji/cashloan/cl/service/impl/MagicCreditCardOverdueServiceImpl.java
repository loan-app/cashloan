package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import com.xiji.cashloan.cl.domain.MagicCreditCardOverdue;
import com.xiji.cashloan.cl.mapper.MagicCreditCardOverdueMapper;
import com.xiji.cashloan.cl.service.MagicCreditCardOverdueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;


/**
 * 魔杖2.0报告-基础信息表ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:04
 */
 
@Service("magicCreditCardOverdueService")
public class MagicCreditCardOverdueServiceImpl extends BaseServiceImpl<MagicCreditCardOverdue, Long> implements MagicCreditCardOverdueService {
	
    private static final Logger logger = LoggerFactory.getLogger(MagicCreditCardOverdueServiceImpl.class);
   
    @Resource
    private MagicCreditCardOverdueMapper magicCreditCardOverdueMapper;

	@Override
	public BaseMapper<MagicCreditCardOverdue, Long> getMapper() {
		return magicCreditCardOverdueMapper;
	}
	
}