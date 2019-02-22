package com.xiji.cashloan.cl.service.impl.statistic;

import com.xiji.cashloan.cl.domain.statistic.AuditorStatisticData;
import com.xiji.cashloan.cl.mapper.AuditorStatisticDataMapper;
import com.xiji.cashloan.cl.service.statistic.AuditorStatisticDataService;
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
 * 审核人员统计数据ServiceImpl
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-02-15 15:35:23
 */
 
@Service("auditorStatisticDataService")
public class AuditorStatisticDataServiceImpl extends BaseServiceImpl<AuditorStatisticData, Long> implements AuditorStatisticDataService {

	private static final Logger logger = LoggerFactory.getLogger(AuditorStatisticDataServiceImpl.class);

	@Resource
	private AuditorStatisticDataMapper auditorStatisticDataMapper;

	@Override
	public BaseMapper<AuditorStatisticData, Long> getMapper() {
		return auditorStatisticDataMapper;
	}

	/**
	 * 获取最近时间
	 *
	 * @return
	 */
	@Override
	public Date getLateDate() {
		return auditorStatisticDataMapper.getLateDate();
	}

	/**
	 * 查询 审核人员统计数据
	 *
	 * @param params
	 * @return
	 */
	@Override
	public List<AuditorStatisticData> listAuditorStatisticData(Map<String, Object> params) {

		List<AuditorStatisticData> borrowApplyCount = auditorStatisticDataMapper.borrowApplyCount(params);
		List<AuditorStatisticData> passOrder = auditorStatisticDataMapper.passOrder(params);
		List<AuditorStatisticData> firstOverdue = auditorStatisticDataMapper.firstOverdue(params);
		List<AuditorStatisticData> firstLoadCount = auditorStatisticDataMapper.firstLoadCount(params);
		List<AuditorStatisticData> currentOverdue = auditorStatisticDataMapper.currentOverdue(params);
		List<AuditorStatisticData> loadCount = auditorStatisticDataMapper.loadCount(params);
		List<AuditorStatisticData> firstExtendOverdueCount = auditorStatisticDataMapper.firstExtendOverdueCount(params);


		List<AuditorStatisticData> statisticDataList = new ArrayList<>();
		setAuditorStatisticDataProperty(borrowApplyCount,statisticDataList,"borrowApplyCount");
		setAuditorStatisticDataProperty(passOrder,statisticDataList,"passOrder");
		setAuditorStatisticDataProperty(firstOverdue,statisticDataList,"firstOverdue");
		setAuditorStatisticDataProperty(firstLoadCount,statisticDataList,"firstLoadCount");
		setAuditorStatisticDataProperty(currentOverdue,statisticDataList,"currentOverdue");
		setAuditorStatisticDataProperty(loadCount,statisticDataList,"loadCount");
		setAuditorStatisticDataProperty(firstExtendOverdueCount,statisticDataList,"firstExtendOverdueCount");



		if (CollectionUtil.isNotEmpty(statisticDataList)){

			// 设置默认值
			this.setDefaultValue(statisticDataList);

			for(AuditorStatisticData auditorStatisticData :statisticDataList){
				if (auditorStatisticData.getFirstLoadCount() == 0){
					auditorStatisticData.setFirstOverdueRate(0.00);
					auditorStatisticData.setCurrentOverdueRate(0.00);
				}else {
					auditorStatisticData.setFirstOverdueRate(BigDecimalUtil.decimal((double) (auditorStatisticData.getFirstOverdue()-auditorStatisticData.getFirstExtendOverdueCount())/(double)auditorStatisticData.getFirstLoadCount()*100,2));
				    auditorStatisticData.setCurrentOverdueRate(BigDecimalUtil.decimal((double)auditorStatisticData.getCurrentOverdue()/(double)auditorStatisticData.getFirstLoadCount()*100,2));
				}
				if (auditorStatisticData.getBorrowApplyCount() == 0){
					auditorStatisticData.setPassRate(0.00);
				}else {
					auditorStatisticData.setPassRate(BigDecimalUtil.decimal((double)auditorStatisticData.getPassOrder()/(double)auditorStatisticData.getBorrowApplyCount()*100,2));
				}
			}
		}

		// 按统计时间排序
		this.auditorStatisticDataSort(statisticDataList);
		return statisticDataList;

	}


	/**
	 * 设置审核人员统计数据属性
	 * @param source
	 * @param target
	 * @param type
	 */
	void setAuditorStatisticDataProperty(List<AuditorStatisticData> source, List<AuditorStatisticData> target, String type) {

		for (AuditorStatisticData auditorStatisticData : source) {
			boolean flag = false;
			for (AuditorStatisticData statisticData : target) {
				if (auditorStatisticData.getCountTime().equals(statisticData.getCountTime()) && auditorStatisticData.getAuditorId().equals(statisticData.getAuditorId())) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				target.add(auditorStatisticData);
			}
		}

		for (AuditorStatisticData auditorStatisticData : source) {
			for (AuditorStatisticData statisticData : target) {
				if (auditorStatisticData.getCountTime().equals(statisticData.getCountTime()) && auditorStatisticData.getAuditorId().equals(statisticData.getAuditorId())) {
					switch (type) {
						case "borrowApplyCount":
							statisticData.setBorrowApplyCount(auditorStatisticData.getBorrowApplyCount());
							break;
						case "passOrder":
							statisticData.setPassOrder(auditorStatisticData.getPassOrder());
							break;
						case "firstOverdue":
							statisticData.setFirstOverdue(auditorStatisticData.getFirstOverdue());
							break;
						case "firstLoadCount":
							statisticData.setFirstLoadCount(auditorStatisticData.getFirstLoadCount());
							break;
						case "currentOverdue":
							statisticData.setCurrentOverdue(auditorStatisticData.getCurrentOverdue());
							break;
						case "loadCount":
							statisticData.setLoadCount(auditorStatisticData.getLoadCount());
							break;
						case "firstExtendOverdueCount":
							statisticData.setFirstExtendOverdueCount(auditorStatisticData.getFirstExtendOverdueCount());
							break;
						default:
							break;
					}
				}
			}
		}

	}

	/**
	 * 设置默认值
	 * @param auditorStatisticDataList
	 */
	void setDefaultValue(List<AuditorStatisticData> auditorStatisticDataList) {

		for (AuditorStatisticData auditorStatisticData : auditorStatisticDataList) {
			if (auditorStatisticData.getBorrowApplyCount() == null) {
				auditorStatisticData.setBorrowApplyCount(0);
			}
			if (auditorStatisticData.getFirstOverdue() == null) {
				auditorStatisticData.setFirstOverdue(0);
			}

			if (auditorStatisticData.getFirstLoadCount() == null) {
				auditorStatisticData.setFirstLoadCount(0);
			}
			if (auditorStatisticData.getPassOrder() == null) {
				auditorStatisticData.setPassOrder(0);
			}

			if (auditorStatisticData.getFirstExtendOverdueCount() == null){
				auditorStatisticData.setFirstExtendOverdueCount(0);
			}

			if (auditorStatisticData.getCurrentOverdue() == null){
				auditorStatisticData.setCurrentOverdue(0);
			}

			if (auditorStatisticData.getLoadCount() == null){
				auditorStatisticData.setLoadCount(0);
			}
		}
	}


	/**
	 * 统计时间排序
	 *
	 * @param auditorStatisticDataList
	 */
	void auditorStatisticDataSort(List<AuditorStatisticData> auditorStatisticDataList){

		if (CollectionUtil.isNotEmpty(auditorStatisticDataList)){
			for (int i = 0 ; i< auditorStatisticDataList.size();i++){
				for(int j = 0 ; j< auditorStatisticDataList.size()-1-i;j++){
					if (auditorStatisticDataList.get(j+1).getCountTime().before(auditorStatisticDataList.get(j).getCountTime())){
						AuditorStatisticData auditorStatisticData = auditorStatisticDataList.get(j);

						auditorStatisticDataList.set(j,auditorStatisticDataList.get(j+1));
						auditorStatisticDataList.set(j+1,auditorStatisticData);
					}
				}
			}
		}
	}

}