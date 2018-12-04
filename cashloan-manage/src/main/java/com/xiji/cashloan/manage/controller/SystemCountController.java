package com.xiji.cashloan.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiji.cashloan.cl.service.SystemCountService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.ServletUtils;

/**
 * 后台登陆，首页统计数据
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 * Copyright 杭州融都科技股份有限公司 现金贷  All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Scope("prototype")
@Controller
public class SystemCountController extends ManageBaseController {
	
	@Resource
	private SystemCountService systemCountService;

	/**
	 * 工作台-今日数据
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/workbench/todayInfo.htm")
	public void todayInfo(HttpServletResponse response) throws Exception {
		Map<String,Object> data = systemCountService.todayInfo();
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 工作台-累计数据
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/workbench/cumulativeInfo.htm")
	public void cumulativeInfo(HttpServletResponse response) throws Exception {
		Map<String,Object> data = systemCountService.cumulativeInfo();
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 工作台-实时数据
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/workbench/realTimeInfo.htm")
	public void realTimeInfo(HttpServletResponse response) throws Exception {
		Map<String,Object> data = systemCountService.realTimeInfo();
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 工作台-地域数据
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/workbench/areaInfo.htm")
	public void areaInfo(HttpServletResponse response) throws Exception {
		Map<String,Object> data = systemCountService.areaInfo();
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 工作台-还款方式数据
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/workbench/repayWayInfo.htm")
	public void repayWayInfo(HttpServletResponse response) throws Exception {
		Map<String,Object> data = systemCountService.repayWayInfo();
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 工作台-放款量、还款量数据
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/workbench/loanAndRepayInfo.htm")
	public void loanAndRepayInfo(HttpServletResponse response) throws Exception {
		Map<String,Object> data = systemCountService.loanAndRepayInfo();
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
}
