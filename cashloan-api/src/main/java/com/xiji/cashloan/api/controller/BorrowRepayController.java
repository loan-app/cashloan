package com.xiji.cashloan.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiji.cashloan.cl.service.BorrowRepayService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.web.controller.BaseController;

 /**
 * 还款计划Controller
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-02-14 13:42:32
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Scope("prototype")
@Controller
public class BorrowRepayController extends BaseController {

	@Resource
	private BorrowRepayService borrowRepayService;

	/**
	 * 获取平台收款账号信息
	 * @param search
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/act/borrow/repay/collectionInfo.htm", method = RequestMethod.GET)
	public void collectionInfo(
			@RequestParam(value="type") String type) throws Exception {
		Map<String,Object>  data = new HashMap<>();
		data.put("name", Global.getValue("repay_collection_info_name"));
		data.put("bank",  Global.getValue("repay_collection_info_bank"));
		data.put("bankCard", Global.getValue("repay_collection_info_bank_card"));
		data.put("alipayAccount",  Global.getValue("repay_collection_info_alipay_account"));
		data.put("type", type);
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
}
