package com.xiji.cashloan.cl.service;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.OperatorReportLink;
import com.xiji.cashloan.cl.domain.XinyanLoanReport;
import com.xiji.cashloan.cl.model.ManageOperatorReportLinkModel;
import com.xiji.cashloan.core.common.service.BaseService;
import java.util.Map;

/**
 * 运营商报告链接Service
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-04 20:18:44
 */
public interface OperatorReportLinkService extends BaseService<OperatorReportLink, Long>{
    Page<ManageOperatorReportLinkModel> page(int current, int pageSize, Map<String, Object> searchMap);

    ManageOperatorReportLinkModel getLastRecord(Long userId);
}
