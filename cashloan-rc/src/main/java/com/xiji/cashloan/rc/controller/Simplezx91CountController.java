package com.xiji.cashloan.rc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import com.xiji.cashloan.rc.domain.TppBusiness;
import com.xiji.cashloan.rc.service.Simplezx91CountService;
import com.xiji.cashloan.rc.service.TppBusinessService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 征信数据-91征信
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Controller
@Scope("prototype")
public class Simplezx91CountController extends BaseController {

	@Resource
	private Simplezx91CountService simplezx91CountService;
	@Resource
	private com.xiji.cashloan.rc.service.Zx91DetailService Zx91DetailService;
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
