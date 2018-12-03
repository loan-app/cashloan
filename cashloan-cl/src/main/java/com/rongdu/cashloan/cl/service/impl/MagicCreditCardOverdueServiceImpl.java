package com.rongdu.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.service.impl.BaseServiceImpl;
import com.rongdu.cashloan.cl.mapper.MagicCreditCardOverdueMapper;
import com.rongdu.cashloan.cl.domain.MagicCreditCardOverdue;
import com.rongdu.cashloan.cl.service.MagicCreditCardOverdueService;


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