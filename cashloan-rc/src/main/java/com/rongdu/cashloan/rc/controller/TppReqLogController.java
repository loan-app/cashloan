package com.rongdu.cashloan.rc.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.rongdu.cashloan.core.common.web.controller.BaseController;
import com.rongdu.cashloan.rc.service.TppReqLogService;

/**
 * 第三方征信请求记录Controller
 * 
 * @author zlh
 * @version 1.0.0
 * @date 2017-03-20 13:50:34
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 创新一部：rdc@erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Controller
@Scope("prototype")
public class TppReqLogController extends BaseController {

	@Resource
	private TppReqLogService tppReqLogService;

}
