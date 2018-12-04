package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.mapper.OperatorReportLinkMapper;
import com.xiji.cashloan.cl.domain.OperatorReportLink;
import com.xiji.cashloan.cl.service.OperatorReportLinkService;


/**
 * 运营商报告链接ServiceImpl
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-04 20:18:44
 */
 
@Service("operatorReportLinkService")
public class OperatorReportLinkServiceImpl extends BaseServiceImpl<OperatorReportLink, Long> implements OperatorReportLinkService {
	
    private static final Logger logger = LoggerFactory.getLogger(OperatorReportLinkServiceImpl.class);
   
    @Resource
    private OperatorReportLinkMapper operatorReportLinkMapper;

	@Override
	public BaseMapper<OperatorReportLink, Long> getMapper() {
		return operatorReportLinkMapper;
	}
	
}