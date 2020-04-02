package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import com.xiji.cashloan.cl.domain.OperatorReqLog;
import com.xiji.cashloan.cl.mapper.OperatorReqLogMapper;
import com.xiji.cashloan.core.common.util.DateUtil;
import com.xiji.cashloan.core.domain.Borrow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.BorrowOperatorLogMapper;
import com.xiji.cashloan.cl.domain.BorrowOperatorLog;
import com.xiji.cashloan.cl.service.BorrowOperatorLogService;

import java.util.HashMap;
import java.util.Map;


/**
 * 借款订单运营商记录表ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2019-01-14 11:52:09
 */
 
@Service("borrowOperatorLogService")
public class BorrowOperatorLogServiceImpl extends BaseServiceImpl<BorrowOperatorLog, Long> implements BorrowOperatorLogService {
	
    private static final Logger logger = LoggerFactory.getLogger(BorrowOperatorLogServiceImpl.class);
   
    @Resource
    private BorrowOperatorLogMapper borrowOperatorLogMapper;
	@Resource
	private OperatorReqLogMapper operatorReqLogMapper;

	@Override
	public BaseMapper<BorrowOperatorLog, Long> getMapper() {
		return borrowOperatorLogMapper;
	}

	@Override
	public int saveLog(Borrow borrow) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", borrow.getUserId());
		OperatorReqLog lastRecord = operatorReqLogMapper.findLastRecord(map);
		BorrowOperatorLog borrowOperatorLog = new BorrowOperatorLog();
		borrowOperatorLog.setBorrowId(borrow.getId());
		borrowOperatorLog.setReqLogId(lastRecord.getId());
		borrowOperatorLog.setCreateTime(DateUtil.getNow());
		borrowOperatorLog.setLastModifyTime(DateUtil.getNow());
		return borrowOperatorLogMapper.save(borrowOperatorLog);
	}
}