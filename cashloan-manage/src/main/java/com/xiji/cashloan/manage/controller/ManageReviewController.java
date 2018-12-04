package com.xiji.cashloan.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiji.cashloan.cl.service.ClBorrowService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.ServletUtils;

/**
* 代理用户信息Controller
*
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
* Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
* 官方网站：www.xiji.com
* 
* 未经授权不得进行修改、复制、出售及商业使用
*/
@Scope("prototype")
@Controller
public class ManageReviewController extends ManageBaseController{

	@Resource
	private ClBorrowService clBorrowService;
	
	/**
	 * 人工复审信息查询
	 * @param borrowId
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/review/findResult.htm")
	public void findResult(
			@RequestParam(value="borrowId") long borrowId
			) throws Exception{
		Map<String,Object> data =  clBorrowService.findResult(borrowId);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
}
