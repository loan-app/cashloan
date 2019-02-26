package com.xiji.cashloan.cl.service.impl.statistic;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.domain.statistic.ChannelStatisticData;
import com.xiji.cashloan.cl.mapper.SystemCountMapper;
import com.xiji.cashloan.cl.mapper.statistic.ChannelStatisticDataMapper;
import com.xiji.cashloan.cl.model.statistic.ChannelStatisticModel;
import com.xiji.cashloan.cl.service.statistic.ChannelStatisticDataService;
import com.xiji.cashloan.cl.util.black.CollectionUtil;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tool.util.BeanUtil;
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

		List<ChannelStatisticData> newMachineAuditPassCount = channelStatisticDataMapper.getNewMachineAuditPassCount(params);
		List<ChannelStatisticData> newMachineAuditNotPassCount = channelStatisticDataMapper.getNewMachineAuditNotPassCount(params);
		List<ChannelStatisticData> newReviewNotPassCount = channelStatisticDataMapper.getNewReviewNotPassCount(params);
		List<ChannelStatisticData> newReviewPassCount = channelStatisticDataMapper.getNewReviewPassCount(params);
		List<ChannelStatisticData> newBorrowApplyCount = channelStatisticDataMapper.getNewBorrowApplyCount(params);
		List<ChannelStatisticData> againExpireOverdueCount = channelStatisticDataMapper.getAgainExpireOverdueCount(params);



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

		setChannelStatisticDataProperty(newBorrowApplyCount,statisticDataList,"newBorrowApplyCount");
		setChannelStatisticDataProperty(newMachineAuditNotPassCount,statisticDataList,"newMachineAuditNotPassCount");
		setChannelStatisticDataProperty(newMachineAuditPassCount,statisticDataList,"newMachineAuditPassCount");
		setChannelStatisticDataProperty(newReviewNotPassCount,statisticDataList,"newReviewNotPassCount");
		setChannelStatisticDataProperty(newReviewPassCount,statisticDataList,"newReviewPassCount");
		setChannelStatisticDataProperty(againExpireOverdueCount,statisticDataList,"againExpireOverdueCount");


		// 设置默认值
		if (CollectionUtil.isNotEmpty(statisticDataList)){
			this.setDefaultValue(statisticDataList);
		}
		// 计算逾期率、下款率、通过率
		this.calculationRatio(statisticDataList);


		// 按时间排序
		this.channelStatisticDataSort(statisticDataList);
		return statisticDataList;
	}

	void calculationRatio(List<ChannelStatisticData> statisticDataList){

		if (CollectionUtil.isEmpty(statisticDataList)){
			return;
		}

		for (ChannelStatisticData statisticData : statisticDataList) {

			if (statisticData.getBorrowApplyCount() == null || statisticData.getBorrowApplyCount() <= 0) {
				statisticData.setMachineAuditPassRate(0.00);
				statisticData.setMachineAuditNotPassRate(0.00);
				statisticData.setReviewPassRate(0.00);
				statisticData.setReviewNotPassRate(0.00);
			} else {

				statisticData.setMachineAuditPassRate(BigDecimalUtil.decimal((double) statisticData.getMachineAuditPassCount() / (double) statisticData.getBorrowApplyCount() * 100, 2));
				statisticData.setMachineAuditNotPassRate(BigDecimalUtil.decimal((double) statisticData.getMachineAuditNotPassCount() / (double) statisticData.getBorrowApplyCount() * 100, 2));
				statisticData.setReviewNotPassRate(BigDecimalUtil.decimal((double) statisticData.getReviewNotPassCount() / (double) statisticData.getBorrowApplyCount() * 100, 2));
				statisticData.setReviewPassRate(BigDecimalUtil.decimal((double) statisticData.getReviewPassCount() / (double) statisticData.getBorrowApplyCount() * 100, 2));
			}

			if (statisticData.getUserRegister() == null || statisticData.getUserRegister() <= 0) {
				statisticData.setLoadRate(0.00);
			} else {
				statisticData.setLoadRate(BigDecimalUtil.decimal((double) statisticData.getFirstLoadCount() / (double) statisticData.getUserRegister() * 100, 2));
			}

			if (statisticData.getAgainExpireLoadCount() == 0 && statisticData.getFirstExpireLoadCount() == 0) {
				statisticData.setOverdueRate(0.00);
			} else {
				statisticData.setOverdueRate(BigDecimalUtil.decimal((double) statisticData.getExtendOverdueCount() / (double) (statisticData.getAgainExpireLoadCount() + statisticData.getFirstExpireLoadCount()) * 100, 2));
			}
			if (statisticData.getFirstExpireLoadCount() == 0) {
				statisticData.setFirstOverdueRate(0.00);
			} else {
				statisticData.setFirstOverdueRate(BigDecimalUtil.decimal((double) (statisticData.getFirstExpireOverdueCount() - statisticData.getFirstExtendOverdueCount()) / (double) statisticData.getFirstExpireLoadCount() * 100, 2));
			}

			if (statisticData.getAgainExpireLoadCount() == 0 && statisticData.getExtendCount() == 0) {
				statisticData.setAgainOverdueRate(0.00);
			} else {
				statisticData.setAgainOverdueRate(BigDecimalUtil.decimal((double) (statisticData.getAgainExpireOverdueCount()+statisticData.getExtendOverdueCount()) / (double) (statisticData.getAgainExpireLoadCount()+ statisticData.getExtendCount() )* 100, 2));
			}
		}
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

            if (statisticData.getNewBorrowApplyCount() == null){
            	statisticData.setNewBorrowApplyCount(0);
			}

			if (statisticData.getNewMachineAuditNotPassCount() == null){
				statisticData.setNewMachineAuditNotPassCount(0);
			}

			if (statisticData.getNewMachineAuditPassCount() == null){
				statisticData.setNewMachineAuditPassCount(0);
			}

			if (statisticData.getNewReviewNotPassCount() == null){
				statisticData.setNewReviewNotPassCount(0);
			}
			if (statisticData.getNewReviewPassCount() == null){
				statisticData.setNewReviewPassCount(0);
			}

			if (statisticData.getAgainExpireOverdueCount() == null){
				statisticData.setAgainExpireOverdueCount(0);
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

						case "newBorrowApplyCount" : statisticData.setNewBorrowApplyCount(channelStatisticData.getNewBorrowApplyCount());break;
						case "newMachineAuditNotPassCount" : statisticData.setMachineAuditNotPassCount(channelStatisticData.getNewMachineAuditNotPassCount());break;
						case "newMachineAuditPassCount" : statisticData.setNewMachineAuditPassCount(channelStatisticData.getNewMachineAuditPassCount());break;
						case "newReviewNotPassCount" : statisticData.setNewReviewNotPassCount(channelStatisticData.getNewReviewNotPassCount());break;
						case "newReviewPassCount" : statisticData.setReviewPassCount(channelStatisticData.getReviewPassCount());break;
						case "againExpireOverdueCount" : statisticData.setAgainExpireOverdueCount(channelStatisticData.getAgainExpireOverdueCount());break;

						default:break;
					}
				}
			}
		}
	}


	/**
	 * 查询 渠道统计列表
	 * @param params
	 * @return
	 */
	public Page<ChannelStatisticData> listChannelStatistic(Map<String,Object> params,Integer current,Integer pageSize){
		PageHelper.startPage(current, pageSize);

		Page<ChannelStatisticData> channelStatisticData = (Page<ChannelStatisticData>) channelStatisticDataMapper.listChannelStatistic(params);
		if (CollectionUtil.isNotEmpty(channelStatisticData)) {
			// 设置默认值
			setDefaultValue(channelStatisticData);
			this.calculationRatio(channelStatisticData);
		}
		return channelStatisticData;
	}

	/**
	 * 计算老客申请数、老客机审通过数、老客机审拒绝数、老客人工审核通过数、老客人工拒绝数
	 * @param channelStatisticDataList
	 * @return
	 */
	public Page<ChannelStatisticModel> listChannelStatisticModel(Page<ChannelStatisticData> channelStatisticDataList){

		Page<ChannelStatisticModel> channelStatisticModels = new Page<>();
		if (CollectionUtil.isNotEmpty(channelStatisticDataList)){



			for(ChannelStatisticData channelStatisticData : channelStatisticDataList){
				ChannelStatisticModel channelStatisticModel = new ChannelStatisticModel();
				BeanUtil.copyProperties(channelStatisticData,channelStatisticModel);
				channelStatisticModel.setOldBorrowApplyCount(channelStatisticData.getBorrowApplyCount() - channelStatisticData.getNewBorrowApplyCount());
				channelStatisticModel.setOldMachineAuditNotPassCount(channelStatisticData.getMachineAuditNotPassCount() -channelStatisticData.getNewMachineAuditNotPassCount());
			    channelStatisticModel.setOldMachineAuditPassCount(channelStatisticData.getMachineAuditPassCount()- channelStatisticData.getNewMachineAuditPassCount());
				channelStatisticModel.setOldReviewNotPassCount(channelStatisticData.getReviewNotPassCount() - channelStatisticData.getNewReviewNotPassCount());
			    channelStatisticModel.setOldReviewPassCount(channelStatisticData.getReviewPassCount() - channelStatisticData.getNewReviewPassCount());
				channelStatisticModel.setAgainExpireOverdueCount(channelStatisticData.getAgainExpireOverdueCount() + channelStatisticData.getExtendOverdueCount());

				if (channelStatisticData.getNewReviewPassCount() == 0 && channelStatisticData.getNewReviewNotPassCount() == 0){
					channelStatisticModel.setNewReviewPassRate(0.00);
					channelStatisticModel.setNewReviewNotPassRate(0.00);
				}else {
					channelStatisticModel.setNewReviewPassRate(BigDecimalUtil.decimal((double)channelStatisticData.getNewReviewPassCount()/(double) (channelStatisticData.getNewReviewPassCount()+channelStatisticData.getNewReviewNotPassCount())*100,2));
					channelStatisticModel.setNewReviewNotPassRate(BigDecimalUtil.decimal((double)channelStatisticData.getNewReviewNotPassCount()/(double) (channelStatisticData.getNewReviewPassCount()+channelStatisticData.getNewReviewNotPassCount())*100,2));
				}

				if((channelStatisticData.getReviewPassCount() - channelStatisticData.getNewReviewPassCount()) <= 0 && (channelStatisticData.getReviewNotPassCount() -channelStatisticData.getNewReviewNotPassCount()) <= 0){
					channelStatisticModel.setOldReviewNotPassRate(0.00);
					channelStatisticModel.setOldReviewPassRate(0.00);
				}else {
					channelStatisticModel.setOldReviewNotPassRate(BigDecimalUtil.decimal((double)(channelStatisticData.getReviewNotPassCount() -channelStatisticData.getNewReviewNotPassCount())/(channelStatisticData.getReviewPassCount() - channelStatisticData.getNewReviewPassCount() + channelStatisticData.getReviewNotPassCount() -channelStatisticData.getNewReviewNotPassCount())*100,2));
					channelStatisticModel.setOldReviewPassRate(BigDecimalUtil.decimal((double)(channelStatisticData.getReviewPassCount() -channelStatisticData.getNewReviewPassCount())/(channelStatisticData.getReviewPassCount() - channelStatisticData.getNewReviewPassCount() + channelStatisticData.getReviewNotPassCount() -channelStatisticData.getNewReviewNotPassCount())*100,2));
				}

			    channelStatisticModels.add(channelStatisticModel);
			}
		}

		return channelStatisticModels;
	}

}