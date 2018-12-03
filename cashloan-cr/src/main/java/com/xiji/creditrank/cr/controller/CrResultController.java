package com.xiji.creditrank.cr.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiji.creditrank.cr.service.CrResultService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import com.xiji.creditrank.cr.domain.CrResult;

/**
 * 评分结果Controller
 * 
 * @author ctt
 * @version 1.0.0
 * @date 2017-01-05 16:22:54
 * Copyright 杭州融都科技股份有限公司  creditrank All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Scope("prototype")
@Controller
public class CrResultController extends BaseController {

	@Resource
	private CrResultService crResultService;

	
	@RequestMapping(value="/modules/manage/cr/result/findUserResult.htm",method=RequestMethod.POST)
	public void findUserResult(@RequestParam(value = "userId")Long userId){
		crResultService.findUserResult(userId);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	@RequestMapping(value="/modules/manage/cr/result/findAllBorrowTypeResult.htm",method=RequestMethod.POST)
	public void findAllBorrowTypeResult(@RequestParam(value = "userId")Long userId){
		List<CrResult> resultList = crResultService.findAllBorrowTypeResult(userId);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, resultList);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	@RequestMapping(value="/modules/manage/cr/result/findBorrowTypeResult.htm",method=RequestMethod.POST)
	public void findBorrowTypeResult(@RequestParam(value = "userId")Long userId,
			@RequestParam(value = "borrowTypeId")Long borrowTypeId){
		List<CrResult> resultList = crResultService.findAllBorrowTypeResult(userId);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, resultList);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}
	
}
