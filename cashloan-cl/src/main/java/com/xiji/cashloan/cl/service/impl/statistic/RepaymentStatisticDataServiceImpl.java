package com.xiji.cashloan.cl.service.impl.statistic;

import com.xiji.cashloan.cl.domain.statistic.RepaymentStatisticData;
import com.xiji.cashloan.cl.mapper.statistic.RepaymentStatisticDataMapper;
import com.xiji.cashloan.cl.service.statistic.RepaymentStatisticDataService;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 还款统计数据ServiceImpl
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-02-15 15:36:37
 */
 
@Service("repaymentStatisticDataService")
public class RepaymentStatisticDataServiceImpl extends BaseServiceImpl<RepaymentStatisticData, Long> implements RepaymentStatisticDataService {
	
    private static final Logger logger = LoggerFactory.getLogger(RepaymentStatisticDataServiceImpl.class);
   
    @Resource
    private RepaymentStatisticDataMapper repaymentStatisticDataMapper;

	@Override
	public BaseMapper<RepaymentStatisticData, Long> getMapper() {
		return repaymentStatisticDataMapper;
	}


	/**
	 * 获取最近时间
	 * @return
	 */
	@Override
	public Date getLateDate(){
		return repaymentStatisticDataMapper.getLateDate();
	}

	/**
	 * 查询 还款统计数据
	 * @param params
	 * @return
	 */
	@Override
	public List<RepaymentStatisticData> listRepaymentStatisticData(Map<String,Object> params){
		return repaymentStatisticDataMapper.listRepaymentStatisticData(params);
	}
}