package com.xiji.cashloan.cl.service;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.model.XinyanLoanUserModel;
import com.xiji.cashloan.core.common.service.BaseService;
import com.xiji.cashloan.cl.domain.XinyanLoanReport;

import java.util.Map;

/**
 * 新颜小额网贷报告Service
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-10 19:16:55
 */
public interface XinyanLoanReportService extends BaseService<XinyanLoanReport, Long>{

    Page<XinyanLoanUserModel> listUser(Map<String, Object> params, int currentPage, int pageSize);

    XinyanLoanReport getLastRecord(Long userId);
}
