package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.CallsOutSideFeeMapper;
import com.xiji.cashloan.cl.domain.CallsOutSideFee;
import com.xiji.cashloan.cl.service.CallsOutSideFeeService;


/**
 * 调用外部数据收费信息ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-04 19:20:29
 */
 
@Service("callsOutSideFeeService")
public class CallsOutSideFeeServiceImpl extends BaseServiceImpl<CallsOutSideFee, Long> implements CallsOutSideFeeService {
	
    private static final Logger logger = LoggerFactory.getLogger(CallsOutSideFeeServiceImpl.class);
   
    @Resource
    private CallsOutSideFeeMapper callsOutSideFeeMapper;

	@Override
	public BaseMapper<CallsOutSideFee, Long> getMapper() {
		return callsOutSideFeeMapper;
	}

	@Override
	public CallsOutSideFee getByTaskId(String taskId) {
		return callsOutSideFeeMapper.getByTaskId(taskId);
	}
}