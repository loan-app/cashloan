package com.xiji.cashloan.cl.service.impl.statistic;

import com.xiji.cashloan.cl.domain.statistic.OverdueStatisticData;
import com.xiji.cashloan.cl.mapper.statistic.OverdueStatisticDataMapper;
import com.xiji.cashloan.cl.service.OverdueStatisticDataService;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 逾期统计数据ServiceImpl
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-03-04 18:07:35
 */
 
@Service("overdueStatisticDataService")
public class OverdueStatisticDataServiceImpl extends BaseServiceImpl<OverdueStatisticData, Long> implements OverdueStatisticDataService {
	
    private static final Logger logger = LoggerFactory.getLogger(OverdueStatisticDataServiceImpl.class);
   
    @Resource
    private OverdueStatisticDataMapper overdueStatisticDataMapper;

	@Override
	public BaseMapper<OverdueStatisticData, Long> getMapper() {
		return overdueStatisticDataMapper;
	}
	
}