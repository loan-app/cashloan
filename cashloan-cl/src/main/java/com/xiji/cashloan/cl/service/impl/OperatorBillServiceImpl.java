package com.xiji.cashloan.cl.service.impl;

import com.xiji.cashloan.cl.domain.OperatorBill;
import com.xiji.cashloan.cl.mapper.OperatorBillMapper;
import com.xiji.cashloan.cl.service.OperatorBillService;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 魔蝎运营商数据-月账单ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-11-23 16:00:44
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("operatorBillService")
public class OperatorBillServiceImpl extends BaseServiceImpl<OperatorBill, Long> implements OperatorBillService {
	
    private static final Logger logger = LoggerFactory.getLogger(OperatorBillServiceImpl.class);
   
    @Resource
    private OperatorBillMapper operatorBillMapper;

	@Override
	public BaseMapper<OperatorBill, Long> getMapper() {
		return operatorBillMapper;
	}
	
}