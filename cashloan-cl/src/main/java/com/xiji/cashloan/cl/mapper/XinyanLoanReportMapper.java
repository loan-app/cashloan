package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.cl.model.XinyanLoanUserModel;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.cl.domain.XinyanLoanReport;

import java.util.List;
import java.util.Map;

/**
 * 新颜小额网贷报告Dao
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-10 19:16:55
 */
@RDBatisDao
public interface XinyanLoanReportMapper extends BaseMapper<XinyanLoanReport, Long> {


    List<XinyanLoanUserModel> listModel(Map<String, Object> params);

    XinyanLoanReport getLastRecord(Long userId);
}
