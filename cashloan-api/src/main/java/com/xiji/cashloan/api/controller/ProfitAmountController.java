package com.xiji.cashloan.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiji.cashloan.cl.domain.ProfitAmount;
import com.xiji.cashloan.cl.service.ProfitAmountService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.web.controller.BaseController;

 /**
 * 分润资金Controller
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2018/12/03
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Scope("prototype")
@Controller
public class ProfitAmountController extends BaseController {

	@Resource
	private ProfitAmountService profitAmountService;

	/**
	 * 平台打款
	 * @param userId
	 * @param money
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/act/mine/profitAmount/cash.htm", method = RequestMethod.POST)
	public void cash(
			@RequestParam(value="userId") long userId,
			@RequestParam(value="money") double money) throws Exception {
		int msg = profitAmountService.cash(userId,money);
		Map<String,Object> result = new HashMap<String,Object>();
		if (msg>0) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "修改成功");
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "修改失败");
		}
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 我的奖金
	 */
	@RequestMapping(value = "/api/act/mine/profitAmount/find.htm", method = RequestMethod.POST)
	public void find(
			@RequestParam(value="userId") long userId) throws Exception {
		ProfitAmount data = profitAmountService.find(userId);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "修改成功");
		ServletUtils.writeToResponse(response,result);
	}
}
