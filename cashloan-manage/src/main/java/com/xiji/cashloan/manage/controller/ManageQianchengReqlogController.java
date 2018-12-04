package com.xiji.cashloan.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.JsonUtil;
import com.xiji.cashloan.core.common.util.RdPage;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.system.permission.annotation.RequiresPermission;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.QianchengReqlog;
import com.xiji.cashloan.cl.mapper.QianchengReqlogMapper;
import com.xiji.cashloan.cl.model.QianchengReqlogModel;
import com.xiji.cashloan.cl.service.QianchengReqlogService;

/**
 * 浅橙请求记录表Controller
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
public class ManageQianchengReqlogController extends ManageBaseController{
	@Resource
	private QianchengReqlogService qianchengReqlogService;
	
	@Resource
	private QianchengReqlogMapper qianchengReqlogMapper;
	
	/**
	 * 浅橙机审请求记录列表
	 * @param searchParams
	 * @param current
	 * @param pageSize
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/modules/manage/borrow/qianchengReqLog/list.htm",method={RequestMethod.GET,RequestMethod.POST})
	@RequiresPermission(code = "modules:manage:borrow:list",name = "机审请求记录列表")
	public void list(@RequestParam(value="searchParams",required=false) String searchParams,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize){
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		Page<QianchengReqlogModel> page = qianchengReqlogService.listQianchengReqlog(params, current, pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 通过借款查询审批日志
	 * @param searchParams
	 * @param current
	 * @param pageSize
	 */
	@RequestMapping(value="/modules/manage/borrow/qianchengReqLog/listByBorrow.htm",method={RequestMethod.GET,RequestMethod.POST})
	@RequiresPermission(code = "modules:manage:borrow:qianchengReqLog:listByBorrow",name = "机审请求记录列表")
	public void listByBorrow(@RequestParam(value = "borrowId") Long borrowId){
		QianchengReqlog log = qianchengReqlogService.findByBorrowId(borrowId);
		Map<String,Object> result = new HashMap<String,Object>();
		if(log!=null){
			
			result.put(Constant.RESPONSE_DATA, log);
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
			ServletUtils.writeToResponse(response,result);
		}else{
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "获取失败");
			ServletUtils.writeToResponse(response,result);
		}
		
	}
	
	
}
