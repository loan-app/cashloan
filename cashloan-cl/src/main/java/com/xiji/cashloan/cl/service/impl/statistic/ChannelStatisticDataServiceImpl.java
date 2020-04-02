package com.xiji.cashloan.cl.service.impl.statistic;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.domain.statistic.ChannelStatisticData;
import com.xiji.cashloan.cl.mapper.statistic.ChannelStatisticDataMapper;
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
		List<ChannelStatisticData> expireLoadCount = channelStatisticDataMapper.getExpireLoadCount(params);




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
		setChannelStatisticDataProperty(expireLoadCount,statisticDataList,"expireLoadCount");



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

	/**
	 * 计算比率
	 * @param statisticDataList
	 */
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
				statisticData.setNewLoadApplyRate(0.00);
			} else {

				statisticData.setMachineAuditPassRate(BigDecimalUtil.decimal((double) statisticData.getMachineAuditPassCount() / (double) statisticData.getBorrowApplyCount() * 100, 2));
				statisticData.setMachineAuditNotPassRate(BigDecimalUtil.decimal((double) statisticData.getMachineAuditNotPassCount() / (double) statisticData.getBorrowApplyCount() * 100, 2));
				statisticData.setReviewNotPassRate(BigDecimalUtil.decimal((double) statisticData.getReviewNotPassCount() / (double) statisticData.getBorrowApplyCount() * 100, 2));
				statisticData.setReviewPassRate(BigDecimalUtil.decimal((double) statisticData.getReviewPassCount() / (double) statisticData.getBorrowApplyCount() * 100, 2));
			}

			if (statisticData.getUserRegister() == null || statisticData.getUserRegister() <= 0) {
				statisticData.setLoadRate(0.00);
				statisticData.setNewLoadRegisterRate(0.00);
				statisticData.setNewTransformRate(0.00);
			} else {
				statisticData.setLoadRate(BigDecimalUtil.decimal((double) statisticData.getFirstLoadCount() / (double) statisticData.getUserRegister() * 100, 2));
			    statisticData.setNewLoadRegisterRate(BigDecimalUtil.decimal((double)statisticData.getFirstLoadCount()/(double)statisticData.getUserRegister()*100,2));
			    statisticData.setNewTransformRate(BigDecimalUtil.decimal((double)statisticData.getNewBorrowApplyCount()/(double)statisticData.getUserRegister()*100,2));
			}

			if (statisticData.getExpireLoadCount() == 0) {
				statisticData.setOverdueRate(0.00);
			} else {
				statisticData.setOverdueRate(BigDecimalUtil.decimal((double) statisticData.getExpireOverdueCount() / (double) (statisticData.getExpireLoadCount()) * 100, 2));
			}
			if (statisticData.getFirstExpireLoadCount() == 0) {
				statisticData.setFirstOverdueRate(0.00);
			} else {
				statisticData.setFirstOverdueRate(BigDecimalUtil.decimal((double) (statisticData.getFirstExpireOverdueCount()) / (double) statisticData.getFirstExpireLoadCount() * 100, 2));
			}

			if (statisticData.getAgainExpireLoadCount() == 0) {
				statisticData.setAgainOverdueRate(0.00);
			} else {
				statisticData.setAgainOverdueRate(BigDecimalUtil.decimal((double) (statisticData.getAgainExpireOverdueCount()) / (double) (statisticData.getAgainExpireLoadCount())* 100, 2));
			}

			if (statisticData.getNewBorrowApplyCount() == null || statisticData.getNewBorrowApplyCount() == 0){
				statisticData.setNewLoadApplyRate(0.00);
			} else {
				statisticData.setNewLoadApplyRate(BigDecimalUtil.decimal((double)statisticData.getFirstLoadCount()/(double)statisticData.getNewBorrowApplyCount()*100,2));
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

			if (statisticData.getExpireLoadCount() == null){
				statisticData.setExpireLoadCount(0);
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

						case "expireOverdueCount" : statisticData.setExpireOverdueCount(channelStatisticData.getExpireOverdueCount());break;
						case "expireLoadCount" : statisticData.setExpireLoadCount(channelStatisticData.getExpireLoadCount());break;
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
	 * 更新渠道下款数
	 * @param params
	 * @return
	 */
	@Override
	public int updateChannelLoan(Map<String,Object> params) {

		List<ChannelStatisticData> channelStatisticDataList = channelStatisticDataMapper.listChannelLoan(params);

		int count = 0;
		if (CollectionUtil.isEmpty(channelStatisticDataList)) {
			return count;
		}
		List<ChannelStatisticData> againLoadCount = channelStatisticDataMapper.againLoadCount(params);
		List<ChannelStatisticData> firstLoadCount = channelStatisticDataMapper.firstLoadCount(params);

		for (ChannelStatisticData statisticData : channelStatisticDataList) {

			boolean flag = false;
			for (ChannelStatisticData againLoad : againLoadCount) {
				if (statisticData.getChannelId().equals(againLoad.getChannelId()) && statisticData.getCountTime().equals(againLoad.getCountTime())) {
					statisticData.setAgainLoadCount(againLoad.getAgainLoadCount());
					flag = true;
				}
			}

			for (ChannelStatisticData firstLoad : firstLoadCount) {
				if (statisticData.getChannelId().equals(firstLoad.getChannelId()) && statisticData.getCountTime().equals(firstLoad.getCountTime())) {
					statisticData.setFirstLoadCount(firstLoad.getFirstLoadCount());
					flag = true;
				}
			}
			if (flag){
				statisticData.setUpdateTime(new Date());
				count = count+channelStatisticDataMapper.updateChannelStatistic(statisticData);
			}
		}
		return count;
	}
}