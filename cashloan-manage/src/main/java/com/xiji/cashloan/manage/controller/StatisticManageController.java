package com.xiji.cashloan.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.model.DayNeedAmountModel;
import com.xiji.cashloan.cl.model.ExpendDetailModel;
import com.xiji.cashloan.cl.model.IncomeAndExpendModel;
import com.xiji.cashloan.cl.model.IncomeDetailModel;
import com.xiji.cashloan.cl.service.StatisticManageService;
import com.xiji.cashloan.cl.service.statistic.*;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.RdPage;
import com.xiji.cashloan.core.common.util.ServletUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 统计管理
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Controller
@Scope("prototype")
public class StatisticManageController extends ManageBaseController {
	
	@Resource
	private StatisticManageService statisticManageService;

	@Resource
	private UserStatisticDataService userStatisticDataService;

	@Resource
	private ChannelStatisticDataService channelStatisticDataService;

	@Resource
	private RepaymentStatisticDataService repaymentStatisticDataService;

	@Resource
	private AuditorStatisticDataService auditorStatisticDataService;

	@Resource
	private AuditingStatisticDataService auditingStatisticDataService;

	@Resource
	private LoadStatisticDataService loadStatisticDataService;

	/**
	 * 每日未还本金
	 * @param response
	 * @param search
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/statistic/dayNeedAmount.htm")
	public void dayNeedAmount(HttpServletResponse response,
			@RequestParam("search")String search,
			@RequestParam("current")Integer current,
			@RequestParam("pageSize")Integer pageSize) throws Exception {
		Map<String, Object> params = JSONObject.parseObject(search);
		Page<DayNeedAmountModel> page = statisticManageService.dayNeedAmount(params,current, pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 每日收入支出
	 * @param response
	 * @param search
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/statistic/incomeAndExpend.htm")
	public void incomeAndExpend(HttpServletResponse response,
			@RequestParam("search")String search,
			@RequestParam("current")Integer current,
			@RequestParam("pageSize")Integer pageSize) throws Exception {
		Map<String, Object> params = JSONObject.parseObject(search);
		Page<IncomeAndExpendModel> page = statisticManageService.repayIncomeAndExpend(params,current, pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 收入明细
	 * @param response
	 * @param search
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/statistic/incomeDetail.htm")
	public void incomeDetail(HttpServletResponse response,
			@RequestParam("search")String search,
			@RequestParam("current")Integer current,
			@RequestParam("pageSize")Integer pageSize) throws Exception {
		Map<String, Object> params = JSONObject.parseObject(search);
		Page<IncomeDetailModel> page = statisticManageService.incomeDetail(params, current, pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		Double incomeSum = statisticManageService.incomeSum(params);
		result.put("incomeSum", incomeSum);
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 支出明细
	 * @param response
	 * @param search
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/statistic/expendDetail.htm")
	public void expendDetail(HttpServletResponse response,
			@RequestParam("search")String search,
			@RequestParam("current")Integer current,
			@RequestParam("pageSize")Integer pageSize) throws Exception {
		Map<String, Object> params = JSONObject.parseObject(search);
		Page<ExpendDetailModel> page = statisticManageService.expendDetail(params, current, pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		Double expendSum = statisticManageService.expendSum(params);
		result.put("expendSum", expendSum);
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}




}
