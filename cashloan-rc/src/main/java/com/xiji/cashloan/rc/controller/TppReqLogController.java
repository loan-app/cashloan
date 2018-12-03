package com.xiji.cashloan.rc.controller;

import javax.annotation.Resource;

import com.xiji.cashloan.core.common.web.controller.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xiji.cashloan.rc.service.TppReqLogService;

/**
 * 第三方征信请求记录Controller
 * 
 * @author zlh
 * @version 1.0.0
 * @date 2017-03-20 13:50:34
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 创新一部：rdc@xiji.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Controller
@Scope("prototype")
public class TppReqLogController extends BaseController {

	@Resource
	private TppReqLogService tppReqLogService;

}
