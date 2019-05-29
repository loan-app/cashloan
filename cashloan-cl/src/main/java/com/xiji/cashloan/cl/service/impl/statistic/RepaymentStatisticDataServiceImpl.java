package com.xiji.cashloan.cl.service.impl.statistic;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.domain.statistic.RealTimeMaturityStatistic;
import com.xiji.cashloan.cl.domain.statistic.RepaymentStatisticData;
import com.xiji.cashloan.cl.mapper.statistic.RepaymentStatisticDataMapper;
import com.xiji.cashloan.cl.service.statistic.RepaymentStatisticDataService;
import com.xiji.cashloan.cl.util.black.CollectionUtil;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.core.common.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tool.util.BigDecimalUtil;

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


	/**
	 * 查询 还款统计数据
	 * @param params
	 * @return
	 */
	@Override
	public Page<RepaymentStatisticData> listRepaymentStatistic(Map<String,Object> params,Integer current,Integer pageSize){

		PageHelper.startPage(current, pageSize);
		Page<RepaymentStatisticData> repaymentStatisticData = (Page<RepaymentStatisticData>) repaymentStatisticDataMapper.listRepaymentStatistic(params);

		if (CollectionUtil.isNotEmpty(repaymentStatisticData)){
			for (RepaymentStatisticData statisticData:repaymentStatisticData){
				statisticData.setCountTimeStr(DateUtil.dateStr(statisticData.getCountTime(),DateUtil.DATEFORMAT_STR_002));
			}
		}
		return repaymentStatisticData;
	}


	/**
	 * 分页获取实时还款统计
	 * @param params
	 * @param current
	 * @param pageSize
	 * @return
	 */
	@Override
	public Page<RealTimeMaturityStatistic> listRealTimeRepayment(Map<String,Object> params,Integer current,Integer pageSize){
		PageHelper.startPage(current, pageSize);
		Page<RealTimeMaturityStatistic>  realTimeMaturityStatistics = (Page<RealTimeMaturityStatistic>)repaymentStatisticDataMapper.realTimeRepayment(params);

		if (CollectionUtil.isEmpty(realTimeMaturityStatistics)){
			return realTimeMaturityStatistics;
		}

		for (RealTimeMaturityStatistic maturityStatistic :realTimeMaturityStatistics){
			if (maturityStatistic.getExtendCount() == null){
				maturityStatistic.setExtendCount(0);
			}
			if (maturityStatistic.getReimbursementCount() == null){
                maturityStatistic.setReimbursementCount(0);
			}
			if (maturityStatistic.getExpireCount() == null || maturityStatistic.getExpireCount() == 0){
				maturityStatistic.setExpireCount(0);
				maturityStatistic.setRepaymentRate(0.00);
			}else {
				maturityStatistic.setRepaymentRate(BigDecimalUtil.decimal(((double)maturityStatistic.getReimbursementCount()+maturityStatistic.getExtendCount())/(double)maturityStatistic.getExpireCount()*100,2));
			}
			maturityStatistic.setExpireTimeStr(DateUtil.dateStr(maturityStatistic.getExpireTime(),DateUtil.DATEFORMAT_STR_002));
		}
		return realTimeMaturityStatistics;
	}
}