package com.rongdu.cashloan.rc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rongdu.cashloan.core.common.context.Constant;
import com.rongdu.cashloan.core.common.util.ServletUtils;
import com.rongdu.cashloan.core.common.web.controller.BaseController;
import com.rongdu.cashloan.rc.domain.TppBusiness;
import com.rongdu.cashloan.rc.service.Simplezx91CountService;
import com.rongdu.cashloan.rc.service.TppBusinessService;
import com.rongdu.cashloan.rc.service.Zx91DetailService;

 /**
 * 征信数据-91征信
 * 
 * @author ctt
 * @version 1.0.0
 * @date 2017-09-08 15:53:59
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Controller
@Scope("prototype")
public class Simplezx91CountController extends BaseController {

	@Resource
	private Simplezx91CountService simplezx91CountService;
	@Resource
	private Zx91DetailService Zx91DetailService;
	@Resource
	private TppBusinessService tppBusinessService;

	
	@RequestMapping("/modules/manage/rc/simplezxquery/testQuery.htm")
	public void page(@RequestParam(value = "idNo") String idNo,
			@RequestParam(value = "realName") String realName) {
		TppBusiness business = tppBusinessService.findByNid("Zx91Query",4l);
		Zx91DetailService.query91zx1003(idNo, realName, 1l, business);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response, result);
	}
}
