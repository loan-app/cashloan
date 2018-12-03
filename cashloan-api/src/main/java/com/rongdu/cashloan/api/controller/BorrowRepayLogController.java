package com.rongdu.cashloan.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.rongdu.cashloan.cl.model.BorrowRepayLogModel;
import com.rongdu.cashloan.cl.service.BorrowRepayLogService;
import com.rongdu.cashloan.core.common.context.Constant;
import com.rongdu.cashloan.core.common.util.RdPage;
import com.rongdu.cashloan.core.common.util.ServletUtils;
import com.rongdu.cashloan.core.common.web.controller.BaseController;

 /**
 * 还款计录Controller
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-02-14 13:46:12
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Scope("prototype")
@Controller
public class BorrowRepayLogController extends BaseController {

	@Resource
	private BorrowRepayLogService borrowRepayLogService;

	/**
	 * 还款计录查询
	 * @param userId
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/act/mine/borrowRepayLog/page.htm", method = RequestMethod.POST)
	public void page(
			@RequestParam(value="userId") long userId,
			@RequestParam(value="state") String state,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String,Object> searchMap = new HashMap<>();
		searchMap.put("userId", userId);
		searchMap.put("state", state);
		Page<BorrowRepayLogModel> page = borrowRepayLogService.page(searchMap,current, pageSize);
		Map<String, Object> data = new HashMap<>();
		data.put("list", page.getResult());
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
}
