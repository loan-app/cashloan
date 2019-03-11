package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.core.model.CloanUserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.CallsOutSideFeeMapper;
import com.xiji.cashloan.cl.domain.CallsOutSideFee;
import com.xiji.cashloan.cl.service.CallsOutSideFeeService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


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

	/**
	 * 查询收据收费列表
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	@Override
	public Page<CallsOutSideFee> listCallsOutSideFee(Map<String, Object> params, int currentPage,
								   int pageSize){
		PageHelper.startPage(currentPage, pageSize);
		List<CallsOutSideFee> list = callsOutSideFeeMapper.listCallsOutSideFee(params);

		BigDecimal totalFee = this.getTotalFee(params);
		//获取余额
		BigDecimal balance=this.getBalance(params);
		for (CallsOutSideFee outSideFee : list){
			outSideFee.setTotalFee(totalFee);
			outSideFee.setBalance(balance);
		}
		return (Page<CallsOutSideFee>) list;
	}

	/**
	 * 获取总消费
	 * @param params
	 * @return
	 */
	@Override
	public BigDecimal getTotalFee(Map<String, Object> params){
		return callsOutSideFeeMapper.getTotalFee(params);
	}

	/**
	 * 获取余额
	 * @param params
	 * @return
	 */
	@Override
	public BigDecimal getBalance(Map<String, Object> params) {
		return callsOutSideFeeMapper.getBalance(params);
	}

	/**
	 * 保存外部数据
	 * @param callsOutSideFee
	 * @return
	 */
	@Override
	public int save(CallsOutSideFee callsOutSideFee) {
		int i=callsOutSideFeeMapper.save(callsOutSideFee);
		return i;
	}


}