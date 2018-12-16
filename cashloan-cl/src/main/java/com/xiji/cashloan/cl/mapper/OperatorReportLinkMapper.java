package com.xiji.cashloan.cl.mapper;

import com.xiji.cashloan.cl.domain.OperatorReportLink;
import com.xiji.cashloan.cl.model.ManageOperatorReportLinkModel;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import java.util.List;
import java.util.Map;

/**
 * 运营商报告链接Dao
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-04 20:18:44
 */
@RDBatisDao
public interface OperatorReportLinkMapper extends BaseMapper<OperatorReportLink, Long> {

    /**
     * 列表查询
     *
     * @param searchMap
     * @return
     */
    List<ManageOperatorReportLinkModel> page(Map<String, Object> searchMap);

    OperatorReportLink getLastRecord(Long userId);
}
