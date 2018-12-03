package com.rongdu.cashloan.rc.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.rongdu.cashloan.core.common.web.controller.BaseController;


 /**
 * 场景与第三方征信接口关联关系Controller
 * 
 * @author zlh
 * @version 1.0.0
 * @date 2017-03-14 13:42:36
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 创新一部：rdc@erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Controller
@Scope("prototype")
public class SceneBusinessLogController extends BaseController {
	
	public static final Logger logger = LoggerFactory.getLogger(SceneBusinessLogController.class);

}
