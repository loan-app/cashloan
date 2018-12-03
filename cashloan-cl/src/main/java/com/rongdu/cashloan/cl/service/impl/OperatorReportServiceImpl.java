package com.rongdu.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.service.impl.BaseServiceImpl;
import com.rongdu.cashloan.cl.mapper.OperatorReportMapper;
import com.rongdu.cashloan.cl.domain.OperatorReport;
import com.rongdu.cashloan.cl.service.OperatorReportService;

import java.util.Map;


/**
 * 运营商报告ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-11-27 10:29:41
 */
 
@Service("operatorReportService")
public class OperatorReportServiceImpl extends BaseServiceImpl<OperatorReport, Long> implements OperatorReportService {
	
    private static final Logger logger = LoggerFactory.getLogger(OperatorReportServiceImpl.class);
   
    @Resource
    private OperatorReportMapper operatorReportMapper;

	@Override
	public BaseMapper<OperatorReport, Long> getMapper() {
		return operatorReportMapper;
	}

	@Override
	public OperatorReport getByTaskId(String taskId) {
		return operatorReportMapper.getByTaskId(taskId);
	}

	@Override
	public int updateSelective(Map<String, Object> params) {
		return operatorReportMapper.updateSelective(params);
	}
}