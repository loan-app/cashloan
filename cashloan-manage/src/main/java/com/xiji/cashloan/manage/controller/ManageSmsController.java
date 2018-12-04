package com.xiji.cashloan.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.xiji.cashloan.core.common.util.JsonUtil;
import com.xiji.cashloan.core.common.util.RdPage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.Sms;
import com.xiji.cashloan.cl.service.ClSmsService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.web.controller.BaseController;

 /**
 * 短信消息
 *
  * @author wnb
  * @version 1.0.0
  * @date 2018/11/27
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Controller
@Scope("prototype")
public class ManageSmsController extends BaseController {

	@Resource
	private ClSmsService clSmsService;
	
	/**
	 * 短信列表
	 * @param searchParams
	 * @param current
	 * @param pageSize
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/modules/manage/sms/list.htm",method={RequestMethod.GET})
	public void listSms(@RequestParam(value="searchParams",required=false) String searchParams,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize){
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		Page<Sms> page = clSmsService.list(params, current, pageSize);
		Map<String,Object> list = new HashMap<String,Object>();
		list.put("list", page);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, list);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE,Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	
}
