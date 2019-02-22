package com.xiji.cashloan.cl.service.impl.statistic;

import com.xiji.cashloan.cl.domain.statistic.ChannelStatisticData;
import com.xiji.cashloan.cl.mapper.ChannelStatisticDataMapper;
import com.xiji.cashloan.cl.mapper.SystemCountMapper;
import com.xiji.cashloan.cl.service.statistic.ChannelStatisticDataService;
import com.xiji.cashloan.cl.util.black.CollectionUtil;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tool.util.BigDecimalUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 渠道统计数据ServiceImpl
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-02-15 15:40:18
 */
 
@Service("channelStatisticDataService")
public class ChannelStatisticDataServiceImpl extends BaseServiceImpl<ChannelStatisticData, Long> implements ChannelStatisticDataService {
	
    private static final Logger logger = LoggerFactory.getLogger(ChannelStatisticDataServiceImpl.class);
   
    @Resource
    private ChannelStatisticDataMapper channelStatisticDataMapper;

    @Resource
    private SystemCountMapper systemCountMapper;

	@Override
	public BaseMapper<ChannelStatisticData, Long> getMapper() {
		return channelStatisticDataMapper;
	}

	@Override
	public Date getLateTime(){
		return channelStatisticDataMapper.getLateTime();
	}

//	/**
//	 * 渠道数据统计
//	 * @param params
//	 * @return
//	 */
//	@Override
//	public List<ChannelStatisticData> listChannelStatisticData(Map<String,Object> params){
//		List<com.xiji.cashloan.cl.model.statistic.ChannelStatisticData> channelStatisticData = (List<com.xiji.cashloan.cl.model.statistic.ChannelStatisticData>) systemCountMapper.listChannelStatisticData(params);
//
//		List<ChannelStatisticData> channelStatisticDataList = new ArrayList<>();
//		if (CollectionUtil.isEmpty(channelStatisticData)){
//			return channelStatisticDataList;
//		}
//		for (com.xiji.cashloan.cl.model.statistic.ChannelStatisticData statisticData : channelStatisticData){
//			if (statisticData.getBorrowApplyCount() == null || statisticData.getBorrowApplyCount() <= 0){
//				statisticData.setMachineAuditPassRate(0.00);
//				statisticData.setMachineAuditNotPassRate(0.00);
//				statisticData.setReviewPassRate(0.00);
//				statisticData.setReviewNotPassRate(0.00);
//			} else {
//				statisticData.setMachineAuditPassRate(BigDecimalUtil.decimal((double)statisticData.getMachineAuditPassCount()/(double)statisticData.getBorrowApplyCount()*100,2));
//				statisticData.setMachineAuditNotPassRate(BigDecimalUtil.decimal((double)statisticData.getMachineAuditNotPassCount()/(double)statisticData.getBorrowApplyCount()*100,2));
//				statisticData.setReviewNotPassRate(BigDecimalUtil.decimal((double)statisticData.getReviewNotPassCount()/(double)statisticData.getBorrowApplyCount()*100,2));
//				statisticData.setReviewPassRate(BigDecimalUtil.decimal((double)statisticData.getReviewPassCount()/(double)statisticData.getBorrowApplyCount()*100,2));
//			}
//
//			if (statisticData.getUserRegister() == null || statisticData.getUserRegister() <= 0){
//				statisticData.setLoadRate(0.00);
//			} else {
//				statisticData.setLoadRate(BigDecimalUtil.decimal((double)(statisticData.getFirstLoadCount()+statisticData.getAgainLoadCount())/(double)statisticData.getUserRegister()*100,2));
//			}
//			if (statisticData.getAgainLoadCount() == null){
//				statisticData.setAgainLoadCount(0);
//			}
//			if (statisticData.getFirstLoadCount() == null){
//				statisticData.setFirstLoadCount(0);
//			}
//			if (statisticData.getAgainLoadCount() == 0 && statisticData.getFirstLoadCount() == 0){
//				statisticData.setOverdueRate(0.00);
//			}else {
//				statisticData.setOverdueRate(BigDecimalUtil.decimal((double)statisticData.getOverdueCount()/(double)(statisticData.getAgainLoadCount()+statisticData.getFirstLoadCount())*100,2));
//			}
//			if (statisticData.getFirstLoadCount() == 0){
//				statisticData.setFirstOverdueRate(0.00);
//			}else {
//				statisticData.setFirstOverdueRate(BigDecimalUtil.decimal((double)statisticData.getFirstOverdueCount()/(double)statisticData.getFirstLoadCount()*100,2));
//			}
//			ChannelStatisticData channelStatisticData1 = new ChannelStatisticData();
//			BeanUtil.copyProperties(statisticData,channelStatisticData1);
//			channelStatisticData1.setCountTime(DateUtil.getDate(statisticData.getDate()));
//			channelStatisticData1.setCreateTime(new Date());
//			channelStatisticDataList.add(channelStatisticData1);
//		}
//		return channelStatisticDataList;
//	}

	/**
	 * 渠道数据统计
	 * @param params
	 * @return
	 */
	@Override
	public List<ChannelStatisticData> listChannelStatisticData(Map<String,Object> params){

		List<ChannelStatisticData> userRegister = channelStatisticDataMapper.getUserRegister(params);
		List<ChannelStatisticData> machineAuditPassCount = channelStatisticDataMapper.getMachineAuditPassCount(params);
		List<ChannelStatisticData> machineAuditNotPassCount = channelStatisticDataMapper.getMachineAuditNotPassCount(params);
		List<ChannelStatisticData> reviewNotPassCount = channelStatisticDataMapper.getReviewNotPassCount(params);
		List<ChannelStatisticData> reviewPassCount = channelStatisticDataMapper.getReviewPassCount(params);
		List<ChannelStatisticData> againLoadCount = channelStatisticDataMapper.getAgainLoadCount(params);
		List<ChannelStatisticData> firstLoadCount = channelStatisticDataMapper.getFirstLoadCount(params);
		List<ChannelStatisticData> firstExpireOverdueCount = channelStatisticDataMapper.getFirstExpireOverdueCount(params);
		List<ChannelStatisticData> borrowApplyCount = channelStatisticDataMapper.getBorrowApplyCount(params);
		List<ChannelStatisticData> expireOverdueCount = channelStatisticDataMapper.getExpireOverdueCount(params);

		List<ChannelStatisticData> extendOverdueCount = channelStatisticDataMapper.getExtendOverdueCount(params);
		List<ChannelStatisticData> extendCount = channelStatisticDataMapper.getExtendCount(params);
		List<ChannelStatisticData> firstExtendOverdueCount = channelStatisticDataMapper.getFirstExtendOverdueCount(params);
		List<ChannelStatisticData> firstExtendCount = channelStatisticDataMapper.getFirstExtendCount(params);
		List<ChannelStatisticData> firstExpireLoadCount = channelStatisticDataMapper.getFirstExpireLoadCount(params);
		List<ChannelStatisticData> againExpireLoadCount = channelStatisticDataMapper.getAgainExpireLoadCount(params);

		List<ChannelStatisticData> statisticDataList = new ArrayList<>();

		setChannelStatisticDataProperty(userRegister,statisticDataList,"userRegister");
		setChannelStatisticDataProperty(machineAuditPassCount,statisticDataList,"machineAuditPassCount");
		setChannelStatisticDataProperty(machineAuditNotPassCount,statisticDataList,"machineAuditNotPassCount");
		setChannelStatisticDataProperty(reviewNotPassCount,statisticDataList,"reviewNotPassCount");
		setChannelStatisticDataProperty(reviewPassCount,statisticDataList,"reviewPassCount");
		setChannelStatisticDataProperty(againLoadCount,statisticDataList,"againLoadCount");
		setChannelStatisticDataProperty(firstLoadCount,statisticDataList,"firstLoadCount");
		setChannelStatisticDataProperty(firstExpireOverdueCount,statisticDataList,"firstExpireOverdueCount");
		setChannelStatisticDataProperty(borrowApplyCount,statisticDataList,"borrowApplyCount");
		setChannelStatisticDataProperty(expireOverdueCount,statisticDataList,"expireOverdueCount");
		setChannelStatisticDataProperty(extendOverdueCount,statisticDataList,"extendOverdueCount");
		setChannelStatisticDataProperty(extendCount,statisticDataList,"extendCount");
		setChannelStatisticDataProperty(firstExtendOverdueCount,statisticDataList,"firstExtendOverdueCount");
		setChannelStatisticDataProperty(firstExtendCount,statisticDataList,"firstExtendCount");
		setChannelStatisticDataProperty(firstExpireLoadCount,statisticDataList,"firstExpireLoadCount");
		setChannelStatisticDataProperty(againExpireLoadCount,statisticDataList,"againExpireLoadCount");




		if (CollectionUtil.isNotEmpty(statisticDataList)){

			this.setDefaultValue(statisticDataList);
			for (ChannelStatisticData statisticData : statisticDataList){

				if (statisticData.getBorrowApplyCount() == null || statisticData.getBorrowApplyCount() <= 0){
					statisticData.setMachineAuditPassRate(0.00);
					statisticData.setMachineAuditNotPassRate(0.00);
					statisticData.setReviewPassRate(0.00);
					statisticData.setReviewNotPassRate(0.00);
				} else {

					statisticData.setMachineAuditPassRate(BigDecimalUtil.decimal((double)statisticData.getMachineAuditPassCount()/(double)statisticData.getBorrowApplyCount()*100,2));
					statisticData.setMachineAuditNotPassRate(BigDecimalUtil.decimal((double)statisticData.getMachineAuditNotPassCount()/(double)statisticData.getBorrowApplyCount()*100,2));
					statisticData.setReviewNotPassRate(BigDecimalUtil.decimal((double)statisticData.getReviewNotPassCount()/(double)statisticData.getBorrowApplyCount()*100,2));
					statisticData.setReviewPassRate(BigDecimalUtil.decimal((double)statisticData.getReviewPassCount()/(double)statisticData.getBorrowApplyCount()*100,2));
				}

				if (statisticData.getUserRegister() == null || statisticData.getUserRegister() <= 0){
					statisticData.setLoadRate(0.00);
				} else {
					statisticData.setLoadRate(BigDecimalUtil.decimal((double)(statisticData.getFirstLoadCount()+statisticData.getAgainLoadCount())/(double)statisticData.getUserRegister()*100,2));
				}

				if (statisticData.getAgainExpireLoadCount() == 0 && statisticData.getFirstExpireLoadCount() == 0){
					statisticData.setOverdueRate(0.00);
				}else {
					statisticData.setOverdueRate(BigDecimalUtil.decimal((double)statisticData.getExtendOverdueCount()/(double)(statisticData.getAgainExpireLoadCount()+statisticData.getFirstExpireLoadCount())*100,2));
				}
				if (statisticData.getFirstExpireLoadCount() == 0){
					statisticData.setFirstOverdueRate(0.00);
				}else {
					statisticData.setFirstOverdueRate(BigDecimalUtil.decimal((double)(statisticData.getFirstExpireOverdueCount()-statisticData.getFirstExtendOverdueCount())/(double)statisticData.getFirstExpireLoadCount()*100,2));
				}
			}
		}

		/**
		 * 设置默认值
		 */
		this.channelStatisticDataSort(statisticDataList);
		return statisticDataList;
	}

	/**
	 * 设置默认值
	 * @param channelStatisticDataList
	 */
  void setDefaultValue(List<ChannelStatisticData> channelStatisticDataList){

		for(ChannelStatisticData statisticData : channelStatisticDataList){
			if (statisticData.getMachineAuditPassCount() == null){
				statisticData.setMachineAuditPassCount(0);
			}
			if (statisticData.getMachineAuditNotPassCount() == null){
				statisticData.setMachineAuditNotPassCount(0);
			}
			if (statisticData.getReviewNotPassCount() == null){
				statisticData.setReviewNotPassCount(0);
			}
			if (statisticData.getReviewPassCount() == null){
				statisticData.setReviewPassCount(0);
			}

			if (statisticData.getExpireOverdueCount() == null){
				statisticData.setExpireOverdueCount(0);
			}

			if (statisticData.getFirstExpireOverdueCount() == null){
				statisticData.setFirstExpireOverdueCount(0);
			}

			if (statisticData.getFirstLoadCount() == null){
				statisticData.setFirstLoadCount(0);
			}
			if (statisticData.getAgainLoadCount() == null){
				statisticData.setAgainLoadCount(0);
			}

			if (statisticData.getUserRegister() == null){
				statisticData.setUserRegister(0);
			}

			if (statisticData.getBorrowApplyCount() == null){
				statisticData.setBorrowApplyCount(0);
			}

			if (statisticData.getExtendCount() == null){
				statisticData.setExtendCount(0);
			}

			if (statisticData.getFirstExtendCount() == null){
				statisticData.setFirstExtendCount(0);
			}

			if (statisticData.getFirstExpireLoadCount() == null){
				statisticData.setFirstExpireLoadCount(0);
			}

			if (statisticData.getAgainExpireLoadCount() == null){
				statisticData.setAgainExpireLoadCount(0);
			}

			if (statisticData.getExtendOverdueCount() == null){
				statisticData.setExtendOverdueCount(0);
			}

			if (statisticData.getFirstExtendOverdueCount() == null){
				statisticData.setFirstExtendOverdueCount(0);
			}
		}



  }

	/**
	 * 统计时间排序
	 * @param channelStatisticDataList
	 */
	void channelStatisticDataSort(List<ChannelStatisticData> channelStatisticDataList){

		if (CollectionUtil.isNotEmpty(channelStatisticDataList)){
			for (int i = 0 ; i< channelStatisticDataList.size();i++){
				for(int j = 0 ; j< channelStatisticDataList.size()-1-i;j++){
                  if (channelStatisticDataList.get(j+1).getCountTime().before(channelStatisticDataList.get(j).getCountTime())){
					  ChannelStatisticData channelStatisticData = channelStatisticDataList.get(j);

					  channelStatisticDataList.set(j,channelStatisticDataList.get(j+1));
                  	  channelStatisticDataList.set(j+1,channelStatisticData);
				  }
				}
			}
		}
	}
	/**
	 * 设置渠道统计数据属性
	 * @param source
	 * @param target
	 * @param type
	 * @return
	 */
	void setChannelStatisticDataProperty(List<ChannelStatisticData> source,List<ChannelStatisticData> target,String type){

		for(ChannelStatisticData channelStatisticData : source){
			boolean flag = false;
			for(ChannelStatisticData statisticData : target){
				if (channelStatisticData.getCountTime().equals(statisticData.getCountTime()) && channelStatisticData.getChannelId().equals(statisticData.getChannelId())){
					flag = true;
					break;
				}
			}
			if (!flag) {
				target.add(channelStatisticData);
			}
		}

		for(ChannelStatisticData channelStatisticData : source){
			for(ChannelStatisticData statisticData : target){
				if (channelStatisticData.getCountTime().equals(statisticData.getCountTime()) && channelStatisticData.getChannelId().equals(statisticData.getChannelId())){
					switch (type){
						case "userRegister" : statisticData.setUserRegister(channelStatisticData.getUserRegister());break;
						case "machineAuditNotPassCount" : statisticData.setMachineAuditNotPassCount(channelStatisticData.getMachineAuditNotPassCount());break;
						case "machineAuditPassCount" : statisticData.setMachineAuditPassCount(channelStatisticData.getMachineAuditPassCount());break;
						case "againLoadCount" : statisticData.setAgainLoadCount(channelStatisticData.getAgainLoadCount());break;
						case "firstLoadCount" : statisticData.setFirstLoadCount(channelStatisticData.getFirstLoadCount());break;
						case "firstExpireOverdueCount" : statisticData.setFirstExpireOverdueCount(channelStatisticData.getFirstExpireOverdueCount());break;
						case "borrowApplyCount" : statisticData.setBorrowApplyCount(channelStatisticData.getBorrowApplyCount());break;
						case "extendOverdueCount" : statisticData.setExtendOverdueCount(channelStatisticData.getExtendOverdueCount());break;
						case "reviewNotPassCount" : statisticData.setReviewNotPassCount(channelStatisticData.getReviewNotPassCount());break;
						case "reviewPassCount" : statisticData.setReviewPassCount(channelStatisticData.getReviewPassCount());break;

						case "extendCount" : statisticData.setExtendCount(channelStatisticData.getExtendCount());break;
						case "firstExtendOverdueCount" : statisticData.setFirstExtendOverdueCount(channelStatisticData.getFirstExtendOverdueCount());break;
						case "firstExtendCount" : statisticData.setFirstExtendCount(channelStatisticData.getFirstExtendCount());break;
						case "firstExpireLoadCount" : statisticData.setFirstExpireLoadCount(channelStatisticData.getFirstExpireLoadCount());break;
						case "againExpireLoadCount" : statisticData.setAgainExpireLoadCount(channelStatisticData.getAgainExpireLoadCount());break;

						default:break;
					}
				}
			}
		}
	}

}