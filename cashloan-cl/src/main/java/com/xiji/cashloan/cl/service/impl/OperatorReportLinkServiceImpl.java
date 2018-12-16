package com.xiji.cashloan.cl.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.domain.OperatorReportLink;
import com.xiji.cashloan.cl.mapper.OperatorReportLinkMapper;
import com.xiji.cashloan.cl.model.ManageOperatorReportLinkModel;
import com.xiji.cashloan.cl.service.OperatorReportLinkService;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


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

	@Override
	public Page<ManageOperatorReportLinkModel> page(int current, int pageSize,
		Map<String, Object> searchMap) {
		PageHelper.startPage(current, pageSize);
		Page<ManageOperatorReportLinkModel> page = (Page<ManageOperatorReportLinkModel>) operatorReportLinkMapper
			.page(searchMap);
		return page;
	}

	@Override
	public ManageOperatorReportLinkModel getLastRecord(Long userId) {
		return operatorReportLinkMapper.getLastRecord(userId);
	}

}