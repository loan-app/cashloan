package com.xiji.cashloan.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.model.statistic.ChannelStatisticData;
import com.xiji.cashloan.cl.model.statistic.UserStatisticData;
import com.xiji.cashloan.cl.service.SystemCountService;
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
 * 后台登陆，首页统计数据
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 *
 *
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


	/**
	 * 用户数据统计 -----
	 * 注册人数、实名认证人数、通讯录认证人数、当日绑卡人数、
	 * 当日运营商认证人数、申请总数、新客借款，老客借款，
	 * 放款笔数，当日新客放款，当日老客放款
	 * @param response
	 * @param current
	 * @param pageSize
	 * @param search
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/statistic/listUserStatisticData.htm")
	public void listUserStatisticData(HttpServletResponse response,
						@RequestParam(value="current")Integer current,
						@RequestParam(value="pageSize")Integer pageSize,
						@RequestParam("search")String search) throws Exception {
		Map<String,Object> params = JSONObject.parseObject(search);
		Page<UserStatisticData> page = systemCountService.listUserStatisticData(params,current,pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}


	/**
	 * 渠道统计
	 *
	 *注册人数，申请订单数，机审通过数，机审通过率，机审拒绝数、拒绝率，
	 * 人工通过数、人工复审通过率，人工拒绝数、人工复审拒绝率，
	 * 首贷放款笔数、复贷放款笔数、逾期笔数，首逾率，逾期率，放款率
	 * @param response
	 * @param current
	 * @param pageSize
	 * @param search
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/statistic/listChannelStatisticData.htm")
	public void listChannelStatisticData(HttpServletResponse response,
								  @RequestParam(value="current")Integer current,
								  @RequestParam(value="pageSize")Integer pageSize,
								  @RequestParam("search")String search) throws Exception {
		Map<String,Object> params = JSONObject.parseObject(search);
		Page<ChannelStatisticData> page = systemCountService.listChannelStatisticData(params,current,pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}


}
