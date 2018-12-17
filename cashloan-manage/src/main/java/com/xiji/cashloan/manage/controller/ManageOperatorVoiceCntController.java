package com.xiji.cashloan.manage.controller;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.OperatorVoiceCnt;
import com.xiji.cashloan.cl.service.OperatorVoiceCntService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.RdPage;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 通话详情统计Controller
 * 
 * @author king
 * @version 1.0.0
 * @date 2018-12-17 11:31:50
 */
@Controller
public class ManageOperatorVoiceCntController extends BaseController {

	@Resource
	private OperatorVoiceCntService operatorVoiceCntService;

	@RequestMapping(value = "/modules/manage/operatorVoiceCnt/listVoiceCnt.htm")
	public void listInviteBorrow(
		@RequestParam(value="userId") long userId,
		@RequestParam(value = "current") int current,
		@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Page<OperatorVoiceCnt> page = operatorVoiceCntService.page(userId,current,pageSize);
		Map<String, Object> data = new HashMap<>();
		data.put("list", page.getResult());
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
}
