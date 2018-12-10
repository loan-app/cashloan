package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.XinyanLoanReportMapper;
import com.xiji.cashloan.cl.domain.XinyanLoanReport;
import com.xiji.cashloan.cl.service.XinyanLoanReportService;


/**
 * 新颜小额网贷报告ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-10 19:16:55
 */
 
@Service("xinyanLoanReportService")
public class XinyanLoanReportServiceImpl extends BaseServiceImpl<XinyanLoanReport, Long> implements XinyanLoanReportService {
	
    private static final Logger logger = LoggerFactory.getLogger(XinyanLoanReportServiceImpl.class);
   
    @Resource
    private XinyanLoanReportMapper xinyanLoanReportMapper;

	@Override
	public BaseMapper<XinyanLoanReport, Long> getMapper() {
		return xinyanLoanReportMapper;
	}
	
}