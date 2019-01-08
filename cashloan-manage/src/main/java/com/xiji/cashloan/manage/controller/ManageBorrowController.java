package com.xiji.cashloan.manage.controller;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.model.ManageBorrowModel;
import com.xiji.cashloan.cl.model.ManageBorrowProgressModel;
import com.xiji.cashloan.cl.service.*;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.JsonUtil;
import com.xiji.cashloan.core.common.util.RdPage;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.core.model.BorrowModel;
import com.xiji.cashloan.system.domain.SysUser;
import com.xiji.cashloan.system.permission.annotation.RequiresPermission;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import tool.util.DateUtil;
import tool.util.StringUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 借款信息表Controller
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Controller
@Scope("prototype")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ManageBorrowController extends ManageBaseController {

	private static final Logger logger = Logger.getLogger(ManageBorrowController.class);
	
	@Resource
	private ClBorrowService clBorrowService;
	@Resource
	private BorrowProgressService borrowProgressService;
	@Resource
	private BorrowRepayLogService borrowRepayLogService;
	@Resource
	private PayLogService payLogService;
	@Resource
	private ClSmsService clSmsService;
	
	/**
	 *借款信息列表
	 * @param search
	 * @param currentPage
	 * @param pageSize
	 */
	@RequestMapping(value="/modules/manage/borrow/list.htm",method={RequestMethod.GET,RequestMethod.POST})
	@RequiresPermission(code = "modules:manage:borrow:list",name = "借款信息列表")
	public void list(@RequestParam(value="searchParams",required=false) String searchParams,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize){
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		Page<ManageBorrowModel> page =clBorrowService.listModel(params,current,pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}

	/**
	 *借款进度列表
	 * @param search
	 * @param currentPage
	 * @param pageSize
	 */
	@RequestMapping(value="/modules/manage/borrow/progress/list.htm",method={RequestMethod.GET,RequestMethod.POST})
	@RequiresPermission(code = "modules:manage:borrow:progress:list",name = "借款进度列表")
	public void progresslist(@RequestParam(value="searchParams",required=false) String searchParams,
			@RequestParam(value = "current") int currentPage,
			@RequestParam(value = "pageSize") int pageSize){
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		Page<ManageBorrowProgressModel> page =borrowProgressService.listModel(params,currentPage,pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 人工复审查询
	 * @param searchParams
	 * @param currentPage
	 * @param pageSize
	 */
	@RequestMapping(value="/modules/manage/borrow/reviewList.htm",method={RequestMethod.GET,RequestMethod.POST})
	@RequiresPermission(code = "modules:manage:borrow:reviewList",name = "人工复审通过列表")
	public void reviewList(@RequestParam(value="searchParams",required=false) String searchParams,
			@RequestParam(value = "current") int currentPage,
			@RequestParam(value = "pageSize") int pageSize){
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		List stateList;
		Page<ManageBorrowModel> page = null;
		if (params != null) {
			String state = StringUtil.isNull(params.get("state"));
			if (null != state &&!StringUtil.isBlank(state)) {
				if (StringUtil.equals(state, BorrowModel.STATE_PASS)) {
					page = clBorrowService.listReview(params,currentPage,pageSize);
				}else {
					stateList = Arrays.asList(state);
					params.put("stateList", stateList);
					params.put("state", "");
					page = clBorrowService.listModel(params,currentPage,pageSize);
				}
			}else {
				stateList = Arrays.asList(BorrowModel.STATE_NEED_REVIEW,
						BorrowModel.STATE_REFUSED,BorrowModel.STATE_PASS);
				params.put("stateList", stateList);
				params.put("state", "");
				page = clBorrowService.listModel(params,currentPage,pageSize);
			}
		}
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}
 
	/**
	 * 借款审核列表     
	 * @param search
	 * @param currentPage
	 * @param pageSize
	 */
	@RequestMapping(value="/modules/manage/borrow/borrowList.htm",method={RequestMethod.GET,RequestMethod.POST})
	@RequiresPermission(code = "modules:manage:borrow:borrowList",name = "借款审核状态列表")
	public void borrowList(@RequestParam(value="searchParams",required=false) String searchParams,
			@RequestParam(value = "current") int currentPage,
			@RequestParam(value = "pageSize") int pageSize){
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		List stateList;
		if (params != null) {
			String state = StringUtil.isNull(params.get("state"));
			if (null != state &&!StringUtil.isBlank(state)) {
				//待自动审核
				if(state.equals(BorrowModel.STATE_PRE)){//10
					stateList = Arrays.asList(BorrowModel.STATE_PRE);
					params.put("stateList", stateList);
					params.put("state", "");
				}
				//自动审核失败
				if(state.equals(BorrowModel.STATE_AUTO_REFUSED)){//21
					stateList = Arrays.asList(BorrowModel.STATE_AUTO_REFUSED);
					params.put("stateList", stateList);
					params.put("state", "");
				}
				//人工复审  
				if(state.equals(BorrowModel.STATE_NEED_REVIEW)){//22
					stateList = Arrays.asList(BorrowModel.STATE_NEED_REVIEW,
							BorrowModel.STATE_REFUSED,BorrowModel.STATE_PASS);
				    params.put("stateList", stateList);
					params.put("state", "");
				}
				//自动审核通过
				if(state.equals(BorrowModel.STATE_AUTO_PASS)){//20
					stateList = Arrays.asList(BorrowModel.STATE_AUTO_PASS,
							BorrowModel.STATE_NEED_REVIEW,
							BorrowModel.STATE_PASS, BorrowModel.STATE_REFUSED,
							BorrowModel.STATE_REPAY,
							BorrowModel.WAIT_AUDIT_LOAN,
							BorrowModel.AUDIT_LOAN_PASS,
							BorrowModel.AUDIT_LOAN_FAIL,
							BorrowModel.STATE_REPAY_FAIL,
							BorrowModel.STATE_FINISH,
							BorrowModel.STATE_REMISSION_FINISH,
							BorrowModel.STATE_REPAY_PROCESSING,
							BorrowModel.STATE_DELAY, BorrowModel.STATE_BAD,BorrowModel.STATE_DELAY_PAY);
				    params.put("stateList", stateList);
					params.put("state", "");
				}
			}
		}
		Page<ManageBorrowModel> page = clBorrowService.listModel(params,currentPage,pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 借款还款信息列表     
	 * @param search
	 * @param currentPage
	 * @param pageSize
	 */
	@RequestMapping(value="/modules/manage/borrow/borrowRepayList.htm",method={RequestMethod.GET,RequestMethod.POST})
	@RequiresPermission(code = "modules:manage:borrow:borrowRepayList",name = "借款还款信息列表 ")
	public void borrowRepayList(@RequestParam(value="searchParams",required=false) String searchParams,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize){
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		List stateList;
		if (params != null) {
			//放款列表
			String type= StringUtil.isNull(params.get("type"));
			if("repay".equals(type)){
				stateList = Arrays.asList(
						BorrowModel.STATE_AUTO_PASS,
						BorrowModel.STATE_PASS,
						BorrowModel.WAIT_AUDIT_LOAN,
						BorrowModel.AUDIT_LOAN_PASS,
						BorrowModel.AUDIT_LOAN_FAIL,
						BorrowModel.STATE_REPAY_FAIL,
						BorrowModel.STATE_REPAY,
						BorrowModel.STATE_FINISH,
						BorrowModel.STATE_REMISSION_FINISH,
						BorrowModel.STATE_DELAY, 
						BorrowModel.STATE_BAD);
			    params.put("stateList", stateList);
				String state = StringUtil.isNull(params.get("state"));
				if (null != state &&!StringUtil.isBlank(state)) {
					params.put("state", state);
				}
			}
			String state = StringUtil.isNull(params.get("state"));
			if (null != state &&!StringUtil.isBlank(state)) {
				//还款列表
				if(state.equals(BorrowModel.STATE_FINISH)){//40
					stateList = Arrays.asList(
							BorrowModel.STATE_FINISH,
							BorrowModel.STATE_REMISSION_FINISH);
					params.put("stateList", stateList);
					params.put("state", "");
				}
				//逾期中列表  
				if(state.equals(BorrowModel.STATE_DELAY)){//50
					stateList = Arrays.asList(BorrowModel.STATE_DELAY);
					params.put("stateList", stateList);
					params.put("state", "");
				}
				//坏账列表  
				if(state.equals(BorrowModel.STATE_BAD)){//90
					stateList = Arrays.asList(BorrowModel.STATE_BAD);
					params.put("stateList", stateList);
					params.put("state", "");
				}
				
			}
		}
		Page<ManageBorrowModel> page = clBorrowService.listBorrowModel(params,current,pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 重新支付
	 * @param borrowId
	 */
	@RequestMapping(value="/modules/manage/borrow/payAgain.htm",method={RequestMethod.POST})
	@RequiresPermission(code = "modules:manage:borrow:payAgain",name = "借款还款信息详细页面    ")
	public void payAgain(@RequestParam(value="borrowId") long borrowId){
		Borrow borrow  = clBorrowService.getById(borrowId);
		boolean flag  = payLogService.judge(borrowId);
		Map<String, Object> result = new HashMap<String, Object>();
		
		if (null != borrow && flag
				&& (BorrowModel.STATE_AUTO_PASS.equals(borrow.getState()) || 
					BorrowModel.STATE_PASS.equals(borrow.getState()) ||
					BorrowModel.STATE_REPAY_FAIL.equals(borrow.getState()))) {
			clBorrowService.borrowLoan(borrow, DateUtil.getNow());
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);

		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "此借款状态不允许再次支付");
		}
	
		ServletUtils.writeToResponse(response,result);
	}

	/**
	 * 借款还款信息详细页面      
	 * @param search
	 * @param currentPage
	 * @param pageSize
	 */
	@RequestMapping(value="/modules/manage/borrow/borrowRepayContent.htm",method={RequestMethod.GET,RequestMethod.POST})
	@RequiresPermission(code = "modules:manage:borrow:borrowRepayContent",name = "借款还款信息详细页面    ")
	public void borrowRepayContent(@RequestParam(value="borrowId") long borrowId){
		ManageBorrowModel model = clBorrowService.getModelByBorrowId(borrowId);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, model);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 查询借款记录 
	 * @param userId
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/borrow/listBorrowLog.htm")
	public void listBorrowLog(
			@RequestParam(value="userId") long userId,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String,Object> params = new HashMap<>();
		params.put("userId", userId);
		Page<ManageBorrowModel> page = clBorrowService.listBorrowModel(params, current, pageSize);
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("list", page.getResult());
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	
	/**
	 * 后台人工复审功能
	 * @param borrowId
	 * @throws Exception
	 */
	@RequestMapping(value="/modules/manage/borrow/verifyBorrow.htm")
	public void verifyBorrow(HttpServletRequest request, @RequestParam(value = "borrowId") Long borrowId,
							 @RequestParam(value = "state") String state,
							 @RequestParam(value = "remark") String remark,
							 @RequestParam(value = "isBlack")String isBlack) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		SysUser curUser = null;
		Object obj = request.getSession().getAttribute("SysUser");
		if(obj != null){
			curUser = (SysUser) obj;
		}
		try{
		    int msg =clBorrowService.manualVerifyBorrow(borrowId, state, remark, curUser.getId(),isBlack);
			if(msg==1){
				result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "复审成功");
			}else{
				result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "复审失败");
			}
		} catch (Exception e) {
			logger.error(e);
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
		}
		ServletUtils.writeToResponse(response,result);
	}

	/**
	 * 重新发起审核
	 * @param request
	 * @param response
	 * @param borrowId
	 */
	@RequestMapping(value = "/modules/manage/borrow/reVerifyBorrowData.htm",method=RequestMethod.POST)
	public void reVerifyBorrowData(@RequestParam(value="borrowId") String borrowId){
		long[] ids = StringUtil.toLongs(borrowId.split(","));
		Map<String,Object> result = new HashMap<String,Object>();
		for (int i = 0; i < ids.length; i++) {
			clBorrowService.reVerifyBorrowData(ids[i]);
		}
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "提交成功，请等待处理结果");
		
		ServletUtils.writeToResponse(response,result);
	} 
	
	/**
	 * 群发短信
	 */
	@RequestMapping(value = "/api/smsBatch.htm")
	public void smsBatch(){
		String id = request.getParameter("ids");
		Map<String,Object> result = new HashMap<String,Object>();
		int r = clSmsService.smsBatch(id);
		if(r == 1){
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "处理结束");
		}
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 审核放款
	 * @param borrowId
	 * @throws Exception
	 */
	@RequestMapping(value="/modules/manage/borrow/auditBorrowLoan.htm")
	public void auditBorrowLoan(@RequestParam(value = "borrowId") Long borrowId,
			@RequestParam(value = "state") String state,
			@RequestParam(value = "remark") String remark) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>(); 
		try{
			SysUser loginUser = getLoginUser(request);
			Long userId=loginUser.getId();
			
		    int msg =clBorrowService.auditBorrowLoan(borrowId, state, remark,userId);
			if(msg==1){
				result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "操作完成");
			}else{
				result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "操作失败");
			}
		} catch (Exception e) {
			logger.error(e);
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
		}
		ServletUtils.writeToResponse(response,result);
	}
	
}
