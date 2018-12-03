package com.rongdu.cashloan.rule.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.rongdu.cashloan.core.common.context.Constant;
import com.rongdu.cashloan.core.common.util.RdPage;
import com.rongdu.cashloan.core.common.util.ServletUtils;
import com.rongdu.cashloan.core.common.web.controller.BaseController;
import com.rongdu.cashloan.rule.domain.BorrowRuleResult;
import com.rongdu.cashloan.rule.service.BorrowRuleResultService;

 /**
 * 规则匹配结果Controller
 * 
 * @author ctt
 * @version 1.0.0
 * @date 2016-12-21 15:04:28
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Controller
@Scope("prototype")
public class BorrowRuleResultController extends BaseController {

	@Resource
	private BorrowRuleResultService borrowRuleResultService;
	
	/**
	 * 获取借款申请规则匹配结果
	 * @param borrowId
	 * @param currentPage
	 * @param pageSize
	 */
	@RequestMapping(value="/modules/manage/borrow/borrowRuleResult.htm")
	public void borrowRuleResult(@RequestParam(value="borrowId",required=false) String borrowId,
			@RequestParam(value = "currentPage") int currentPage,
			@RequestParam(value = "pageSize") int pageSize){
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("borrowId", borrowId);
		Page<BorrowRuleResult> page = borrowRuleResultService.borrowRuleResult(params, currentPage, pageSize);
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	

}
