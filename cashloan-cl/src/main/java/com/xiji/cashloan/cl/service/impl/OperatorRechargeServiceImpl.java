package com.xiji.cashloan.cl.service.impl;

import com.xiji.cashloan.cl.domain.OperatorRecharge;
import com.xiji.cashloan.cl.mapper.OperatorRechargeMapper;
import com.xiji.cashloan.cl.service.OperatorRechargeService;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 魔蝎运营商数据-充值记录ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-11-24 14:30:35
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("operatorRechargeService")
public class OperatorRechargeServiceImpl extends BaseServiceImpl<OperatorRecharge, Long> implements OperatorRechargeService {
	
    private static final Logger logger = LoggerFactory.getLogger(OperatorRechargeServiceImpl.class);
   
    @Resource
    private OperatorRechargeMapper operatorRechargeMapper;

	@Override
	public BaseMapper<OperatorRecharge, Long> getMapper() {
		return operatorRechargeMapper;
	}
	
}