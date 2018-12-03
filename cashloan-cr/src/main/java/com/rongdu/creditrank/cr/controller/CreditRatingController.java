package com.rongdu.creditrank.cr.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rongdu.cashloan.core.common.context.Constant;
import com.rongdu.cashloan.core.common.util.ServletUtils;
import com.rongdu.cashloan.core.common.web.controller.BaseController;
import com.rongdu.creditrank.cr.service.CreditRatingService;

 /**
 * 评分操作
 * @author ctt
 * @version 1.0.0
 * @date 2017-01-05 16:22:54
 * Copyright 杭州融都科技股份有限公司  creditrank All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Controller
public class CreditRatingController extends BaseController {

	@Resource
	private CreditRatingService creditRatingService;
	
	@RequestMapping(value="/modules/manage/cr/result/testCreditRating.htm",method=RequestMethod.POST)
	public void testCreditRating() throws Exception {
		creditRatingService.saveCreditRating("1",7l);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}

}
