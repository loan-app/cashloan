package com.xiji.cashloan.cl.service.impl.statistic;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.domain.statistic.AuditingStatisticData;
import com.xiji.cashloan.cl.mapper.statistic.AuditingStatisticDataMapper;
import com.xiji.cashloan.cl.service.statistic.AuditingStatisticDataService;
import com.xiji.cashloan.cl.util.black.CollectionUtil;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tool.util.BigDecimalUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 审核统计数据ServiceImpl
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-02-15 15:37:50
 */
 
@Service("auditingStatisticDataService")
public class AuditingStatisticDataServiceImpl extends BaseServiceImpl<AuditingStatisticData, Long> implements AuditingStatisticDataService {
	
    private static final Logger logger = LoggerFactory.getLogger(AuditingStatisticDataServiceImpl.class);
   
    @Resource
    private AuditingStatisticDataMapper auditingStatisticDataMapper;

	@Override
	public BaseMapper<AuditingStatisticData, Long> getMapper() {
		return auditingStatisticDataMapper;
	}


	/**
	 * 最近时间
	 * @return
	 */
	@Override
	public Date getLateDate(){
		return auditingStatisticDataMapper.getLateDate();
	}

	/**
	 * 查询 审核统计数据
	 * @param params
	 * @return
	 */
	@Override
	public List<AuditingStatisticData> listAuditingStatisticData(Map<String,Object> params){

		List<AuditingStatisticData> auditingStatisticDataList = auditingStatisticDataMapper.listAuditingStatisticData(params);

		if (CollectionUtil.isNotEmpty(auditingStatisticDataList)){
			for(AuditingStatisticData auditingStatisticData : auditingStatisticDataList){

				if (auditingStatisticData.getBorrowApplyCount() == 0){
					auditingStatisticData.setMachineAuditNotPassRate(0.00);
					auditingStatisticData.setMachineAuditPassRate(0.00);
					auditingStatisticData.setReviewNotPassRate(0.00);
					auditingStatisticData.setReviewPassRate(0.00);
				}else {
					auditingStatisticData.setMachineAuditNotPassRate(BigDecimalUtil.decimal((double)auditingStatisticData.getMachineAuditNotPassCount()/(double)auditingStatisticData.getBorrowApplyCount()*100,2));
				    auditingStatisticData.setMachineAuditPassRate(BigDecimalUtil.decimal((double)auditingStatisticData.getMachineAuditPassCount()/(double)auditingStatisticData.getBorrowApplyCount()*100,2));
				    auditingStatisticData.setReviewNotPassRate(BigDecimalUtil.decimal((double)auditingStatisticData.getReviewNotPassCount()/(double)auditingStatisticData.getBorrowApplyCount()*100,2));
				    auditingStatisticData.setReviewPassRate(BigDecimalUtil.decimal((double)auditingStatisticData.getReviewCount()/(double)auditingStatisticData.getBorrowApplyCount()*100,2));
				}
				auditingStatisticData.setCreateTime(new Date());
			}
		}

		return auditingStatisticDataList;
	}


	/**
	 * 查询 审核统计数据
	 * @param params
	 * @return
	 */
	@Override
	public Page<AuditingStatisticData> listAuditingStatistic(Map<String,Object> params,Integer current,Integer pageSize){

		PageHelper.startPage(current, pageSize);

		Page<AuditingStatisticData> auditingStatisticData = (Page<AuditingStatisticData>) auditingStatisticDataMapper.listAuditingStatistic(params);

		return auditingStatisticData;
	}
	
}