package com.xiji.cashloan.cl.service;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.model.statistic.ChannelStatisticData;
import com.xiji.cashloan.cl.model.statistic.UserStatisticData;

import java.util.Map;

/**
 * 首页数据统计
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface SystemCountService {
	
	/**
	 * 今日数据
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> todayInfo()throws Exception;
	
	/**
	 * 累计数据
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> cumulativeInfo()throws Exception;
	
	/**
	 * 实时数据
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> realTimeInfo()throws Exception;

	/**
	 * 地域数据
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> areaInfo()throws Exception;

	/**
	 * 还款方式数据
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> repayWayInfo()throws Exception;

	/**
	 * 放款量、还款量数据
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> loanAndRepayInfo()throws Exception;

//	/**
//	 * 单日用户统计
//	 * @return
//	 */
//	Map<String,Object> todayUserStatistics();

	/**
	 * 用户数据统计
	 * @param params
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<UserStatisticData> listUserStatisticData(Map<String,Object> params, Integer current, Integer pageSize);

	/**
	 * 渠道数据统计
	 * @param params
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<ChannelStatisticData> listChannelStatisticData(Map<String,Object> params,Integer current,Integer pageSize);

}
