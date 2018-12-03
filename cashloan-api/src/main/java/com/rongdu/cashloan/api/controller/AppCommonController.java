package com.rongdu.cashloan.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rongdu.cashloan.core.common.context.Constant;
import com.rongdu.cashloan.core.common.context.Global;
import com.rongdu.cashloan.core.common.exception.ServiceException;
import com.rongdu.cashloan.core.common.util.ServletUtils;
import com.rongdu.cashloan.core.common.web.controller.BaseController;

/**
 * app公用接口
 * @author jdd
 * @version 1.0
 * @date 2017年7月31日
 * Copyright 杭州融都科技股份有限公司 cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 创新一部：rdc@erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Controller
@Scope("prototype")
public class AppCommonController extends BaseController {
	
	/**
	 * 功能开关
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/api/common/isLocation.htm")
	public void isLocation() {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("isShow", Global.getValue("is_location"));
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
}
