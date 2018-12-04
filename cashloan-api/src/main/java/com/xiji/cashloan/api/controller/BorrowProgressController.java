package com.xiji.cashloan.api.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiji.cashloan.cl.model.BorrowProgressModel;
import com.xiji.cashloan.cl.service.BorrowProgressService;
import com.xiji.cashloan.cl.service.ClBorrowService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.core.model.BorrowModel;

import tool.util.DateUtil;

 /**
 * 借款进度表Controller
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2018/12/03
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Scope("prototype")
@Controller
public class BorrowProgressController extends BaseController {

	@Resource
	private BorrowProgressService borrowProgressService;
	@Resource
	private ClBorrowService clBorrowService;

	/**
	 * 借款进度查询
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/act/mine/borrow/findProgress.htm", method = RequestMethod.POST)
	public void findProgress(
			@RequestParam(value="borrowId") long borrowId) throws Exception {
		
		Map<String,Object> borrowMap = new HashMap<>();
		borrowMap.put("id", borrowId);
		Borrow borrow = clBorrowService.getById(borrowId);
		Map<String,Object> data = borrowProgressService.result(borrow);
		List<BorrowProgressModel> list = clBorrowService.borrowProgress(borrow, "detail");
		data.put("list", list);
		if(list != null && !list.isEmpty()){
			data.put("isBorrow", true);
		}
		
		String isShow = "0";
		if (BorrowModel.STATE_REPAY.equals(borrow.getState()) || BorrowModel.STATE_DELAY.equals(borrow.getState()) ||
				BorrowModel.STATE_FINISH.equals(borrow.getState()) || BorrowModel.STATE_DELAY_PAY.equals(borrow.getState()) ) {
			isShow = "1";
		}
		String s = File.separator;
		String rootFile = Global.getValue("server_host") + "/" + "readFile.htm?path=";
		String filePath = s + "data" + s + "protocol" + s + "borrow" + s + DateUtil.dateStr(borrow.getCreateTime(), DateUtil.DATEFORMAT_STR_013) + s + borrow.getOrderNo() + "-contract.pdf";
		if (s.equals("\\")) {
			filePath = "F:" + filePath;
			filePath = filePath.replace("\\", "/");
        }
		String protocolPath = rootFile + filePath;
		data.put("protocolPath", protocolPath);
		data.put("isShow", isShow);
		
		Map<String,Object> result = new HashMap<>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
}
