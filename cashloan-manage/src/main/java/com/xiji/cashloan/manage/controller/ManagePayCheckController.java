package com.xiji.cashloan.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.model.ManagePayCheckModel;
import com.xiji.cashloan.cl.service.PayCheckService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.JsonUtil;
import com.xiji.cashloan.core.common.util.RdPage;
import com.xiji.cashloan.core.common.util.ServletUtils;

/**
 * 资金对账记录Controller
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
public class ManagePayCheckController extends ManageBaseController {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ManagePayCheckController.class);
	
	@Resource
	private PayCheckService payCheckService;

	/**
	 * 支付记录列表页查看
	 * 
	 * @param searchParams
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modules/manage/pay/check/page.htm")
	public void page(
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {

		Map<String, Object> searchMap = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(search)) {
			searchMap = JsonUtil.parse(search, Map.class);
		}
		
		Page<ManagePayCheckModel> page = payCheckService.page(current,pageSize, searchMap);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response, result);
	}

}
