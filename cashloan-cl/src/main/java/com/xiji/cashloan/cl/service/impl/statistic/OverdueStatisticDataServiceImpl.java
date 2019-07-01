package com.xiji.cashloan.cl.service.impl.statistic;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.domain.statistic.OverdueStatisticData;
import com.xiji.cashloan.cl.mapper.statistic.OverdueStatisticDataMapper;
import com.xiji.cashloan.cl.service.statistic.OverdueStatisticDataService;
import com.xiji.cashloan.cl.util.black.CollectionUtil;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.core.common.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tool.util.BigDecimalUtil;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


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

	/**
	 * 最近时间
	 * @return
	 */
	@Override
	public Date getLateDate(){
		return overdueStatisticDataMapper.getLateDate();
	}
	/**
	 * 查询逾期统计数据
	 * @param params
	 * @return
	 */
	@Override
	public List<OverdueStatisticData> listOverdueStatisticData(Map<String,Object> params){

		List<OverdueStatisticData>  newOverdue = overdueStatisticDataMapper.newOverdue(params);
		List<OverdueStatisticData>  againOverdue = overdueStatisticDataMapper.againOverdue(params);
		List<OverdueStatisticData>  extendOverdue = overdueStatisticDataMapper.extendOverdue(params);
		List<OverdueStatisticData>  newExpire = overdueStatisticDataMapper.newExpire(params);
		List<OverdueStatisticData>  againExpire = overdueStatisticDataMapper.againExpire(params);
		List<OverdueStatisticData>  extendExpire = overdueStatisticDataMapper.extendExpire(params);
		List<OverdueStatisticData>  newRepayment = overdueStatisticDataMapper.newRepayment(params);
		List<OverdueStatisticData>  againRepayment = overdueStatisticDataMapper.againRepayment(params);
		List<OverdueStatisticData>  extendRepayment = overdueStatisticDataMapper.extendRepayment(params);

		List<OverdueStatisticData> statisticDataList = new ArrayList<>();
		setOverdueStatisticDataProperty(newOverdue,statisticDataList,"newOverdue");
		setOverdueStatisticDataProperty(againOverdue,statisticDataList,"againOverdue");
		setOverdueStatisticDataProperty(extendOverdue,statisticDataList,"extendOverdue");
		setOverdueStatisticDataProperty(newExpire,statisticDataList,"newExpire");
		setOverdueStatisticDataProperty(againExpire,statisticDataList,"againExpire");
		setOverdueStatisticDataProperty(extendExpire,statisticDataList,"extendExpire");
		setOverdueStatisticDataProperty(newRepayment,statisticDataList,"newRepayment");
		setOverdueStatisticDataProperty(againRepayment,statisticDataList,"againRepayment");
		setOverdueStatisticDataProperty(extendRepayment,statisticDataList,"extendRepayment");


		if (CollectionUtil.isNotEmpty(statisticDataList)){
			setDefaultValue(statisticDataList);
		}
		this.calculationRatio(statisticDataList);

		this.overdueStatisticDataSort(statisticDataList);
		return statisticDataList;
	}


	/**
	 * 设置默认值
	 * @param overdueStatisticDataList
	 */
	private void setDefaultValue(List<OverdueStatisticData> overdueStatisticDataList){

		for(OverdueStatisticData overdueStatisticData :overdueStatisticDataList){
			if (overdueStatisticData.getNewOverdue() == null){
				overdueStatisticData.setNewOverdue(0);
			}
			if (overdueStatisticData.getAgainOverdue() == null){
				overdueStatisticData.setAgainOverdue(0);
			}
			if (overdueStatisticData.getExtendOverdue() == null){
				overdueStatisticData.setExtendOverdue(0);
			}
			if (overdueStatisticData.getNewExpire() == null){
				overdueStatisticData.setNewExpire(0);
			}

			if (overdueStatisticData.getAgainExpire() == null){
				overdueStatisticData.setAgainExpire(0);
			}
			if (overdueStatisticData.getExtendExpire() == null){
				overdueStatisticData.setExtendExpire(0);
			}

			if (overdueStatisticData.getNewRepayment() == null){
				overdueStatisticData.setNewRepayment(0);
			}
			if (overdueStatisticData.getAgainRepayment() == null){
				overdueStatisticData.setAgainRepayment(0);
			}
			if (overdueStatisticData.getExtendRepayment() == null){
				overdueStatisticData.setExtendRepayment(0);
			}
		}
	}

	/**
	 * 设置逾期统计数据属性
	 * @param source
	 * @param target
	 * @param type
	 */
	private void setOverdueStatisticDataProperty(List<OverdueStatisticData> source, List<OverdueStatisticData> target, String type) {

		for (OverdueStatisticData auditorStatisticData : source) {
			boolean flag = false;
			for (OverdueStatisticData statisticData : target) {
				if(auditorStatisticData.compareTo(statisticData) == 0) {
					switch (type) {
						case "newOverdue":
							statisticData.setNewOverdue(auditorStatisticData.getNewOverdue());
							break;
						case "againOverdue":
							statisticData.setAgainOverdue(auditorStatisticData.getAgainOverdue());
							break;
						case "extendOverdue":
							statisticData.setExtendOverdue(auditorStatisticData.getExtendOverdue());
							break;
						case "newExpire":
							statisticData.setNewExpire(auditorStatisticData.getNewExpire());
							break;
						case "againExpire":
							statisticData.setAgainExpire(auditorStatisticData.getAgainExpire());
							break;
						case "extendExpire":
							statisticData.setExtendExpire(auditorStatisticData.getExtendExpire());
							break;
						case "newRepayment":
							statisticData.setNewRepayment(auditorStatisticData.getNewRepayment());
							break;
						case "againRepayment":
							statisticData.setAgainRepayment(auditorStatisticData.getAgainRepayment());
							break;
						case "extendRepayment":
							statisticData.setExtendRepayment(auditorStatisticData.getExtendRepayment());
							break;
						default:
							break;
					}
					flag = true;
					break;
				}
			}
			if (!flag) {
				if(null != auditorStatisticData && !target.contains(auditorStatisticData)) {
					target.add(auditorStatisticData);
				}
			}
		}

	}


	/**
	 * 计算比率
	 * @param statisticDataList
	 */
	private void calculationRatio(List<OverdueStatisticData> statisticDataList){

		if (CollectionUtil.isEmpty(statisticDataList)){
			return;
		}
		for(OverdueStatisticData overdueStatisticData : statisticDataList){
			if (overdueStatisticData.getNewExpire() == 0){
				overdueStatisticData.setNewOverdueRate(0.00);
			}else {
				overdueStatisticData.setNewOverdueRate(BigDecimalUtil.decimal((double)overdueStatisticData.getNewOverdue()/(double)overdueStatisticData.getNewExpire()*100,2));
			}

			if (overdueStatisticData.getAgainExpire() == 0){
				overdueStatisticData.setAgainOverdueRate(0.00);
			}else {
				overdueStatisticData.setAgainOverdueRate(BigDecimalUtil.decimal((double)overdueStatisticData.getAgainOverdue()/(double)overdueStatisticData.getAgainExpire()*100,2));
			}

			if (overdueStatisticData.getExtendExpire() == 0){
				overdueStatisticData.setExtendOverdueRate(0.00);
			}else {
				overdueStatisticData.setExtendOverdueRate(BigDecimalUtil.decimal((double)overdueStatisticData.getExtendOverdue()/(double)overdueStatisticData.getExtendExpire()*100,2));
			}

			if (overdueStatisticData.getNewExpire() == 0 && overdueStatisticData.getAgainExpire() ==0 && overdueStatisticData.getExtendExpire() ==0){
				overdueStatisticData.setOverdueRate(0.00);
			} else {
				overdueStatisticData.setOverdueRate(BigDecimalUtil.decimal((double)(overdueStatisticData.getNewOverdue()+overdueStatisticData.getAgainOverdue()+overdueStatisticData.getExtendOverdue())/(double)(overdueStatisticData.getNewExpire()+overdueStatisticData.getAgainExpire()+overdueStatisticData.getExtendExpire())*100,2));
			}
		}
	}

	/**
	 * 统计时间排序
	 *
	 * @param overdueStatisticDataList
	 */
	void overdueStatisticDataSort(List<OverdueStatisticData> overdueStatisticDataList){

		if (CollectionUtil.isNotEmpty(overdueStatisticDataList)){
			for (int i = 0 ; i< overdueStatisticDataList.size();i++){
				for(int j = 0 ; j< overdueStatisticDataList.size()-1-i;j++){
					if (overdueStatisticDataList.get(j+1).getCountTime().before(overdueStatisticDataList.get(j).getCountTime())){
						OverdueStatisticData overdueStatisticData = overdueStatisticDataList.get(j);

						overdueStatisticDataList.set(j,overdueStatisticDataList.get(j+1));
						overdueStatisticDataList.set(j+1,overdueStatisticData);
					}
				}
			}
		}
	}




	/**
	 * 查询 逾期统计数据
	 * @param params
	 * @return
	 */
	@Override
	public Page<OverdueStatisticData> listOverdueStatistic(Map<String,Object> params, Integer current, Integer pageSize){

		PageHelper.startPage(current, pageSize);

		Page<OverdueStatisticData> overdueStatisticData = (Page<OverdueStatisticData>) overdueStatisticDataMapper.listOverdueStatistic(params);
		if (CollectionUtil.isNotEmpty(overdueStatisticData)){
			for(OverdueStatisticData statisticData :overdueStatisticData){
				statisticData.setCountTimeStr(DateUtil.dateStr(statisticData.getCountTime(),DateUtil.DATEFORMAT_STR_002));
			}
		}
		return overdueStatisticData;
	}

	@Override
	public Page<OverdueStatisticData> queryNowOverdueStatistic(Map<String,Object> params) {
		Page<OverdueStatisticData> overdueStatisticData = PageHelper.startPage(1, 20);

		List<OverdueStatisticData>  newOverdue = overdueStatisticDataMapper.newOverdueNow(params);
		List<OverdueStatisticData>  againOverdue = overdueStatisticDataMapper.againOverdueNow(params);
		List<OverdueStatisticData>  extendOverdue = overdueStatisticDataMapper.extendOverdueNow(params);
		List<OverdueStatisticData>  newExpire = overdueStatisticDataMapper.newExpire(params);
		List<OverdueStatisticData>  againExpire = overdueStatisticDataMapper.againExpire(params);
		List<OverdueStatisticData>  extendExpire = overdueStatisticDataMapper.extendExpire(params);
		List<OverdueStatisticData>  newRepayment = overdueStatisticDataMapper.newRepayment(params);
		List<OverdueStatisticData>  againRepayment = overdueStatisticDataMapper.againRepayment(params);
		List<OverdueStatisticData>  extendRepayment = overdueStatisticDataMapper.extendRepayment(params);

		List<OverdueStatisticData> statisticDataList = new ArrayList<>();

		setOverdueStatisticDataProperty(newOverdue,statisticDataList,"newOverdue");
		setOverdueStatisticDataProperty(againOverdue,statisticDataList,"againOverdue");
		setOverdueStatisticDataProperty(extendOverdue,statisticDataList,"extendOverdue");
		setOverdueStatisticDataProperty(newExpire,statisticDataList,"newExpire");
		setOverdueStatisticDataProperty(againExpire,statisticDataList,"againExpire");
		setOverdueStatisticDataProperty(extendExpire,statisticDataList,"extendExpire");
		setOverdueStatisticDataProperty(newRepayment,statisticDataList,"newRepayment");
		setOverdueStatisticDataProperty(againRepayment,statisticDataList,"againRepayment");
		setOverdueStatisticDataProperty(extendRepayment,statisticDataList,"extendRepayment");


		if (CollectionUtil.isNotEmpty(statisticDataList)){
			setDefaultValue(statisticDataList);
		}
		this.calculationRatio(statisticDataList);

		if(statisticDataList != null && statisticDataList.size() > 0) {
			for(OverdueStatisticData statisticData:statisticDataList){
				statisticData.setCountTimeStr(DateUtil.dateStr(statisticData.getCountTime(),DateUtil.DATEFORMAT_STR_002));
			}
		}

		Collections.sort(overdueStatisticData);
		return overdueStatisticData;
	}
}


