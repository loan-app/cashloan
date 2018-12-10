package com.xiji.cashloan.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.xiji.cashloan.api.util.StringSplit;
import com.xiji.cashloan.cl.domain.QianchengReqlog;
import com.xiji.cashloan.cl.model.ClBorrowModel;
import com.xiji.cashloan.cl.model.IndexModel;
import com.xiji.cashloan.cl.model.QianchengReqlogModel;
import com.xiji.cashloan.cl.model.RepayModel;
import com.xiji.cashloan.cl.service.ClBorrowService;
import com.xiji.cashloan.cl.service.MagicRiskService;
import com.xiji.cashloan.cl.service.OperatorReqLogService;
import com.xiji.cashloan.cl.service.QianchengReqlogService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.exception.BussinessException;
import com.xiji.cashloan.core.common.util.MoneyUtil;
import com.xiji.cashloan.core.common.util.RdPage;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.model.BorrowModel;
import com.xiji.cashloan.core.service.CloanUserService;
import com.xiji.cashloan.core.service.UserBaseInfoService;
import com.xiji.cashloan.rc.model.TppBusinessModel;
import com.xiji.cashloan.rc.service.SceneBusinessLogService;
import credit.ApiSignUtil;
import java.io.File;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import tool.util.DateUtil;
import tool.util.NumberUtil;
import tool.util.StringUtil;

/**
 * 借款申请风控接口执行记录
 * @author wnb
 * @version 1.0
 * @date 2018/12/03
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Scope("prototype")
@Controller
public class ClBorrowController extends BaseController {
	public static final Logger logger = LoggerFactory.getLogger(ClBorrowController.class);
	@Resource
	private CloanUserService cloanUserService;
	@Resource
	private ClBorrowService clBorrowService;
	@Resource
	private QianchengReqlogService qianchengReqlogService;
	@Resource
	private OperatorReqLogService operatorReqLogService;
	@Resource
	private CloanUserService userService;
	@Resource
	private SceneBusinessLogService sceneBusinessLogService;
	@Resource
	private UserBaseInfoService userBaseInfoService;
	@Resource
	private MagicRiskService magicRiskService;
	
	/**
	 * 查询借款列表
	 * @param userId
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/act/borrow/findAll.htm")
	public void findAll(
			@RequestParam(value="userId") long userId) throws Exception {
		Map<String,Object> searchMap = new HashMap<>();
		searchMap.put("userId", userId);
		List<RepayModel> list = clBorrowService.findRepay(searchMap);
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("list", list);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data); 
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 查询借款
	 * @param userId
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/act/mine/borrow/page.htm", method = RequestMethod.POST)
	public void page(
			@RequestParam(value="userId") long userId,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String,Object> searchMap = new HashMap<>();
		searchMap.put("userId", userId);
		Page<ClBorrowModel> page = clBorrowService.page(searchMap,current, pageSize);
		Map<String, Object> data = new HashMap<>();
		data.put("list", page.getResult());
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 查询订单
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/act/borrow/findBorrow.htm", method = RequestMethod.POST)
	public void findBorrow(
			@RequestParam(value="borrowId",required=false) Long borrowId) throws Exception {
		Borrow borrow = clBorrowService.getById(borrowId);
		ClBorrowModel data = new ClBorrowModel();
		BeanUtils.copyProperties(borrow, data);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 首页信息查询
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/borrow/findIndex.htm")
	public void findIndex() throws Exception {
		String userId = request.getParameter("userId");
		Map<String,Object> data = clBorrowService.findIndex(userId);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 首页轮播信息
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/borrow/listIndex.htm")
	public void listIndex() throws Exception {
		List<IndexModel> list = clBorrowService.listIndex();
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("list", list);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 查询所有借款费用信息
	 */
	@RequestMapping(value = "/api/borrow/choices.htm")
	public void choicesList() {
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("list", clBorrowService.choices());
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response, result);
	}

	/**
	 * 查询借款费用信息
	 */
	@RequestMapping(value = "/api/borrow/choice.htm")
	public void choice(@RequestParam(value="amount") double amount,
					   @RequestParam(value="timeLimit") String timeLimit) {
		Map<String, Object> data = clBorrowService.choice(amount, timeLimit);

		data.put("amount", amount);
		data.put("timeLimit", timeLimit);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response, result);
	}
	
	/**
	 * 借款申请  新
	 * @param amount
	 * @param timeLimit
	 * @param tradePwd
	 * @param cardId
	 * @param client
	 * @param address
	 * @param coordinate
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/act/borrow/save.htm")
	public void save(
			@RequestParam(value="amount") double amount,
			@RequestParam(value="timeLimit") String timeLimit,
			@RequestParam(value="tradePwd") String tradePwd,
			@RequestParam(value="cardId") long cardId,
			@RequestParam(value="client") String client,
			@RequestParam(value="address",required=false) String address,
			@RequestParam(value="coordinate",required=false) String coordinate,
			@RequestParam(value="ip",required=false) String ip,
			@RequestParam(value="mobileType",required=false) String mobileType
			) throws Exception {
		long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		Map<String,Object> result = new HashMap<String,Object>();
		Borrow borrow = new Borrow(userId, amount, timeLimit, cardId, client, address, coordinate,ip);
		ClBorrowModel borrowModel = clBorrowService.rcBorrowApply(borrow, tradePwd, mobileType);
		if(borrowModel.isNeedApprove()){
			clBorrowService.verifyBorrowData(borrow.getId(), mobileType);
		}
		
		if (borrowModel != null && borrowModel.getId() > 0) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "申请成功");
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "申请失败");
		}
		
		ServletUtils.writeToResponse(response,result);
	}
	

	/**
	 * 浅橙借款申请异步回调接口
	 * @param request
	 */
	@RequestMapping(value = "/api/qianchenNotify.htm",method=RequestMethod.POST)
	public void qianchenNotify(HttpServletRequest request,HttpServletResponse response) throws Exception{
		final Map<String,Object> params = getRequestFormMap(request);
		String sign = String.valueOf(params.get("sign"));
		String res = String.valueOf(params.get("res"));
		String orderNo = String.valueOf(params.get("orderNo"));
		
		Map<String, Object> signMap = new HashMap<>();/*验签map*/
        signMap.put("res", res);
        signMap.put("orderNo", orderNo);
        signMap.put("timestamp", params.get("timestamp"));
		String tempSign = ApiSignUtil.sign(Global.getValue("apikey"), Global.getValue("secretkey"), signMap);
		
		String result = "FAIL";
		if(!tempSign.equals(sign)){
			logger.info("orderNo：" + orderNo 
					+"浅橙回调验签失败，回调参数："+JSONObject.toJSONString(params)
					+"返回签名参数"+sign
					+"本地签名参数:"+tempSign);
		} else {
			QianchengReqlog reqLog = null;
			if(StringUtil.isNotBlank(orderNo)){
				reqLog = qianchengReqlogService.findByOrderNo(String.valueOf(params.get("orderNo")));
			}
			if (reqLog != null) {
				result = "SUCCESS";
				if(QianchengReqlogModel.STATE_SUBMIT.equals(reqLog.getState())){
					reqLog.setNotifyParams(JSONObject.toJSONString(params));
					reqLog.setUpdateTime(DateUtil.getNow());
					reqLog.setState("30");
					final JSONObject resObj = JSONObject.parseObject(res);
					if(resObj != null){
						reqLog.setRsState(resObj.getString("state"));
						reqLog.setRsDesc(resObj.getString("msg"));
						// 20 审核通过 30 审核不通过
						if (reqLog.getRsState().equals("0")) {
							reqLog.setState("20");
						} else {
							reqLog.setState("30");
						}
					}
					qianchengReqlogService.update(reqLog);
					
					final long borrowId = reqLog.getBorrowId();
					Thread handleBorrow = new Thread(new Runnable() {
						@Override
						public void run() {
							clBorrowService.syncSceneBusinessLog("1", "成功", borrowId, TppBusinessModel.BUS_NID_QCRISK);
						}
					});
					handleBorrow.start();
				}
			} else {
				logger.info("orderNo：" + orderNo + "浅橙回调，未找到对应的浅橙请求订单，回调参数："+JSONObject.toJSONString(params));
			}
		}
		PrintWriter writer=  response.getWriter();
		writer.print(result);
		writer.flush();
		writer.close();
	}
	
	/**
	 * 借款协议预览
	 * 
	 * @param amount
	 * @param timeLimit
	 * @return
	 */
	@RequestMapping(value = "/api/act/borrow/protocolPreview.htm")
	public String protocolPreview(@RequestParam(value = "amount") double amount,
			@RequestParam(value = "timeLimit") String timeLimit) {
		String userId = request.getParameter("userId");
		if(StringUtil.isBlank(userId)  || NumberUtil.getLong(userId) == 0 ){
			throw new BussinessException(StringUtil.isNull(Constant.FAIL_CODE_VALUE),"请先登录");
		}
		
		UserBaseInfo userBaseInfo = userBaseInfoService.findByUserId(NumberUtil.getLong(userId));
		// 根据借款金额和借款期限，返回其余费用明细
		Map<String, Object> map = clBorrowService.choice(amount, timeLimit);
		
		Date now = DateUtil.getNow(); // 当前时间
		Date repayTime = DateUtil.rollDay(now, Integer.parseInt(timeLimit) - 1); // 还款日期
		
		Map<String, Object> result  = new HashMap<>();
		result.put("customerName",userBaseInfo.getRealName());
		result.put("idNo",userBaseInfo.getIdNo());
		result.put("capitalization",MoneyUtil.convert(StringUtil.isNull(amount)));
		result.put("amount",amount);
		result.put("timeLimit",timeLimit);
		result.put("createTime", DateUtil.dateStr(now, "yyyy年MM月dd日"));
		result.put("repayTime",DateUtil.dateStr(repayTime, "yyyy年MM月dd日"));
		result.put("platformServiceFee",map.get("serviceFee"));
		result.put("rentalFees",map.get("infoAuthFee"));
		result.put("guaranteeServiceFee",map.get("interest"));
		result.put("serviceFee",StringUtil.isNull(map.get("fee")));
		
		request.setAttribute("datas", result);
		return "protocolBorrow";
	}

	/**
	 * 借款协议预览
	 *
	 * @param amount
	 * @param timeLimit
	 * @return
	 */
	@RequestMapping(value = "/api/act/borrow/protocolPreview1.htm")
	public String protocolPreview1(@RequestParam(value = "amount") double amount,
		@RequestParam(value = "timeLimit") String timeLimit) {
		String userId = request.getParameter("userId");
		if(StringUtil.isBlank(userId)  || NumberUtil.getLong(userId) == 0 ){
			throw new BussinessException(StringUtil.isNull(Constant.FAIL_CODE_VALUE),"请先登录");
		}

		UserBaseInfo userBaseInfo = userBaseInfoService.findByUserId(NumberUtil.getLong(userId));
		// 根据借款金额和借款期限，返回其余费用明细
		Map<String, Object> map = clBorrowService.choice(amount, timeLimit);

		String penaltyFee = Global.getValue("penalty_fee");
		String[] penaltyFees = penaltyFee.split(",");

		String penalty = "2";
		for (String string : penaltyFees) {
			String[] penaltyParams = string.split("-");
			if (penaltyParams.length==2) {
				if (Integer.parseInt(penaltyParams[0])==(Integer.parseInt(timeLimit))) {
					penalty = penaltyParams[1];
				}
			}
		}
		String appName = Global.getValue("appName");
		Date now = DateUtil.getNow(); // 当前时间
		Date repayTime = DateUtil.rollDay(now, Integer.parseInt(timeLimit) - 1); // 还款日期
		Map<String, Object> result  = new HashMap<>();
		result.put("customerName",userBaseInfo.getRealName());
		result.put("sfz",userBaseInfo.getIdNo());
		result.put("mobile",userBaseInfo.getPhone());
		result.put("amount", StringSplit.stirngSplitZero(Double.toString(amount)));
		result.put("dayCnt",timeLimit);
		result.put("fromDate", DateUtil.dateStr(now, "yyyy年MM月dd日"));
		result.put("endDate",DateUtil.dateStr(repayTime, "yyyy年MM月dd日"));
		result.put("penalty",Double.parseDouble(penalty)*100);
		result.put("fee",StringSplit.stirngSplitZero(StringUtil.isNull(map.get("fee"))));
		result.put("appName",appName);

		request.setAttribute("datas", result);
		return "protocolBorrow1";
	}

	/**
	 * 借款详情—合同信息
	 * @param borrowId
	 */
	@RequestMapping(value = "/api/borrow/protocolDetail.htm")
	public void protocolDetail(@RequestParam(value="borrowId") long borrowId) {
		Map<String,Object> data = new HashMap<String,Object>();
		Borrow borrow = clBorrowService.findByPrimary(borrowId);
		String isShow = "0";
		if (BorrowModel.STATE_REPAY.equals(borrow.getState()) || BorrowModel.STATE_DELAY.equals(borrow.getState()) ||
				BorrowModel.STATE_FINISH.equals(borrow.getState())|| BorrowModel.STATE_DELAY_PAY.equals(borrow.getState()) ) {
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
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response, result);
	}

	/**
	 * 测试魔杖2.0数据保存
	 */
	@RequestMapping(value = "/api/act/borrow/testMagic.htm")
	public void testMagic() {
		Borrow borrow = new Borrow();
		borrow.setUserId(1L);
		borrow.setId(1L);
		int i = magicRiskService.queryAntiFraud(borrow);
		int i2 = magicRiskService.queryApply(borrow);
		int i3 = magicRiskService.queryPostLoad(borrow);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response, result);
	}
}
