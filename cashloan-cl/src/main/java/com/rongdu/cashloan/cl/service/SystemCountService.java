package com.rongdu.cashloan.cl.service;

import java.util.Map;

/**
 * 首页数据统计
 * @author caitt
 * @version 1.0
 * @date 2017年3月16日上午10:15:38
 * Copyright 杭州融都科技股份有限公司 现金贷  All Rights Reserved
 * 官方网站：www.erongdu.com
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

}
