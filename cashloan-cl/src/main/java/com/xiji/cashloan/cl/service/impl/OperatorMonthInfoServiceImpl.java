package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import com.xiji.cashloan.cl.domain.OperatorMonthInfo;
import com.xiji.cashloan.cl.mapper.OperatorMonthInfoMapper;
import com.xiji.cashloan.cl.service.OperatorMonthInfoService;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * 魔蝎运营商数据-流量详情ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-11-24 15:17:56
 */
 
@Service("operatorMonthInfoService")
public class OperatorMonthInfoServiceImpl extends BaseServiceImpl<OperatorMonthInfo, Long> implements OperatorMonthInfoService {
	
    private static final Logger logger = LoggerFactory.getLogger(OperatorMonthInfoServiceImpl.class);
   
    @Resource
    private OperatorMonthInfoMapper operatorMonthInfoMapper;

	@Override
	public BaseMapper<OperatorMonthInfo, Long> getMapper() {
		return operatorMonthInfoMapper;
	}
	
}