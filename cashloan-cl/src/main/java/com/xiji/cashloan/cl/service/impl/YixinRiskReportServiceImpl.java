package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.YixinRiskReportMapper;
import com.xiji.cashloan.cl.domain.YixinRiskReport;
import com.xiji.cashloan.cl.service.YixinRiskReportService;


/**
 * 宜信风险评估ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-25 16:26:48
 */
 
@Service("yixinRiskReportService")
public class YixinRiskReportServiceImpl extends BaseServiceImpl<YixinRiskReport, Long> implements YixinRiskReportService {
	
    private static final Logger logger = LoggerFactory.getLogger(YixinRiskReportServiceImpl.class);
   
    @Resource
    private YixinRiskReportMapper yixinRiskReportMapper;

	@Override
	public BaseMapper<YixinRiskReport, Long> getMapper() {
		return yixinRiskReportMapper;
	}
	
}