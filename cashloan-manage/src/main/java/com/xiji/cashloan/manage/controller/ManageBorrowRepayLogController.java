package com.xiji.cashloan.manage.controller;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.BankCard;
import com.xiji.cashloan.cl.domain.BorrowRepay;
import com.xiji.cashloan.cl.domain.BorrowRepayLog;
import com.xiji.cashloan.cl.domain.PayLog;
import com.xiji.cashloan.cl.model.ManageBRepayLogModel;
import com.xiji.cashloan.cl.model.PayLogModel;
import com.xiji.cashloan.cl.model.pay.common.PayCommonUtil;
import com.xiji.cashloan.cl.model.pay.common.constant.PayConstant;
import com.xiji.cashloan.cl.model.pay.common.vo.request.PaymentReqVo;
import com.xiji.cashloan.cl.model.pay.common.vo.request.RepaymentQueryVo;
import com.xiji.cashloan.cl.model.pay.common.vo.request.RepaymentReqVo;
import com.xiji.cashloan.cl.model.pay.common.vo.response.PaymentResponseVo;
import com.xiji.cashloan.cl.model.pay.common.vo.response.RepaymentQueryResponseVo;
import com.xiji.cashloan.cl.model.pay.common.vo.response.RepaymentResponseVo;
import com.xiji.cashloan.cl.monitor.BusinessExceptionMonitor;
import com.xiji.cashloan.cl.service.BankCardService;
import com.xiji.cashloan.cl.service.BorrowRepayLogService;
import com.xiji.cashloan.cl.service.BorrowRepayService;
import com.xiji.cashloan.cl.service.ClBorrowService;
import com.xiji.cashloan.cl.service.PayLogService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.DateUtil;
import com.xiji.cashloan.core.common.util.IpUtil;
import com.xiji.cashloan.core.common.util.JsonUtil;
import com.xiji.cashloan.core.common.util.RdPage;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.core.domain.User;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.service.CloanUserService;
import com.xiji.cashloan.core.service.UserBaseInfoService;
import com.xiji.cashloan.system.permission.annotation.RequiresPermission;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import tool.util.NumberUtil;

/**
 * 还款记录后台管理Controller
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
public class ManageBorrowRepayLogController extends ManageBaseController{

	private static final Logger logger  = LoggerFactory.getLogger(ManageBorrowRepayLogController.class);
	
	@Resource
	private CloanUserService cloanUserService;
	@Resource
	private UserBaseInfoService userBaseInfoService;
	@Resource
	private BankCardService bankCardService;
	@Resource
	private ClBorrowService clBorrowService;
	@Resource
	private BorrowRepayService borrowRepayService;
	@Resource
	private BorrowRepayLogService borrowRepayLogService;
	@Resource
	private PayLogService payLogService;
	

	/**
	 * 还款记录列表
	 *
	 * @param currentPage
	 * @param pageSize
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modules/manage/borrow/repay/log/list.htm")
	@RequiresPermission(code = "modules:manage:borrow:repay:log:list", name = "还款记录列表")
	public void page(
			@RequestParam(value = "searchParams", required = false) String searchParams,
			@RequestParam(value = "current") int currentPage,
			@RequestParam(value = "pageSize") int pageSize) {
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		Page<ManageBRepayLogModel> page = borrowRepayLogService.listModel(params, currentPage, pageSize);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response, result);
	}
	
	/**
	 * 退还 还款金额
	 * @param id
	 * @param amount
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/borrow/repayLog/refund.htm", method = { RequestMethod.POST })
	public void refund(@RequestParam(value = "id") Long id,
			@RequestParam(value = "amount") String amount) throws Exception {

		BorrowRepayLog borrowRepayLog = borrowRepayLogService.getById(id);
		BankCard bankCard = bankCardService.getBankCardByUserId(borrowRepayLog.getUserId());
		UserBaseInfo baseInfo = userBaseInfoService.findByUserId(borrowRepayLog.getUserId());
		Borrow borrow = clBorrowService.getById(borrowRepayLog.getBorrowId());

		Date date = DateUtil.getNow();
		if (borrow.getFee() < NumberUtils.toDouble(amount)) {
			Map<String,Object> result = new HashMap<String, Object>();
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "付款金额超过综合服务费了，请调整支付金额！");
			ServletUtils.writeToResponse(response, result);
		}

		PaymentReqVo vo = new PaymentReqVo();
		if ("dev".equals(Global.getValue("app_environment"))) {
			vo.setAmount(3.0);
		} else {
			vo.setAmount(NumberUtils.toDouble(amount));
		}
		vo.setBankCardName(baseInfo.getRealName());
		vo.setBankCardNo(bankCard.getCardNo());
		vo.setBorrowId(borrow.getId());
		vo.setBorrowOrderNo(borrow.getOrderNo());
		vo.setMobile(bankCard.getPhone());
		vo.setShareKey(bankCard.getUserId());
		PaymentResponseVo result = PayCommonUtil.payment(vo);

		PayLog payLog = new PayLog();
		payLog.setOrderNo(result.getOrderNo());
		payLog.setUserId(borrow.getUserId());
		payLog.setBorrowId(borrow.getId());
		payLog.setAmount(NumberUtil.getDouble(amount));
		payLog.setCardNo(bankCard.getCardNo());
		payLog.setBank(bankCard.getBank());
		payLog.setSource(PayLogModel.SOURCE_FUNDS_OWN);
		payLog.setType(PayLogModel.TYPE_PAYMENT);
		payLog.setScenes(PayLogModel.SCENES_REFUND);

		if (PayCommonUtil.success(result.getStatus())) { //受理成功
			payLog.setState(PayLogModel.STATE_PAYMENT_WAIT);
		} else if (PayCommonUtil.needCheck(result.getStatus())) {  // 接口调用异常，待人工审核
			payLog.setState(PayLogModel.STATE_PENDING_REVIEW);
//					payLog.setConfirmCode(payment.getConfirm_code());
			payLog.setUpdateTime(DateUtil.getNow());
		} else {
			BusinessExceptionMonitor.add(BusinessExceptionMonitor.TYPE_11, payLog.getOrderNo(), result.getMessage());
			payLog.setState(PayLogModel.STATE_PAYMENT_FAILED);
			payLog.setUpdateTime(DateUtil.getNow());
		}

		payLog.setCode(result.getStatusCode());
		payLog.setRemark(result.getMessage());
		payLog.setPayReqTime(date);
		payLog.setCreateTime(DateUtil.getNow());
		payLogService.save(payLog);
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		resultMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, resultMap);
	}
	
	
	/**
	 * 补扣-还款金额
	 *
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/borrow/repayLog/deduction.htm", method = { RequestMethod.POST })
	public void deduction(@RequestParam(value = "id") Long id,
			@RequestParam(value = "amount") String amount) throws Exception {
		BorrowRepayLog borrowRepayLog = borrowRepayLogService.getById(id);
		BorrowRepay borrowRepay = borrowRepayService.getById(borrowRepayLog.getRepayId());
		User user = cloanUserService.getById(borrowRepayLog.getUserId());
		UserBaseInfo baseInfo = userBaseInfoService.findByUserId(borrowRepayLog.getUserId());
		Borrow borrow = clBorrowService.getById(borrowRepayLog.getBorrowId());
		BankCard bankCard = bankCardService.getBankCardByUserId(borrowRepayLog
				.getUserId());

		// 扣款失败无异步通知 故先查询订单是否已经在支付处理中
		Map<String, Object> payLogMap = new HashMap<String, Object>();
		payLogMap.put("userId", borrowRepayLog.getUserId());
		payLogMap.put("borrowId", borrowRepayLog.getBorrowId());
		payLogMap.put("type", PayLogModel.TYPE_COLLECT);
		payLogMap.put("scenes",PayLogModel.SCENES_DEDUCTION);
		PayLog deductionLog = payLogService.findLatestOne(payLogMap);
		// 订单存在并不是支付失败记录
		if (null != deductionLog
				&& !PayLogModel.STATE_PAYMENT_FAILED.equals(deductionLog.getState())) {
			RepaymentQueryVo vo = new RepaymentQueryVo();
			vo.setOrderNo(deductionLog.getOrderNo());
			vo.setPayPlatNo(deductionLog.getPayOrderNo());
			vo.setShareKey(deductionLog.getUserId());
			RepaymentQueryResponseVo responseVo = PayCommonUtil.queryOrder(vo);

			if (StringUtil.equals(responseVo.getCode(), PayConstant.QUERY_PAY_SUCCESS)) {
				// 更新订单状态
				deductionLog.setState(PayLogModel.STATE_PAYMENT_SUCCESS);
				deductionLog.setUpdateTime(DateUtil.getNow());
				payLogService.updateById(deductionLog);
			}
		}
		logger.info("进行补扣代扣" + amount);
		Date payReqTime = DateUtil.getNow();

		RepaymentReqVo vo = new RepaymentReqVo();
		if ("dev".equals(Global.getValue("app_environment"))) {
			vo.setAmount(0.01);
		} else {
			vo.setAmount(NumberUtils.toDouble(amount));
		}

		vo.setIp(IpUtil.getLocalIp());
		vo.setUserId(user.getUuid());
		vo.setProtocolNo(bankCard.getAgreeNo());
		vo.setBorrowOrderNo(borrow.getOrderNo());
		vo.setRemark("还款" + borrow.getOrderNo());
		vo.setRemark2("repayment_" + borrow.getOrderNo());
		vo.setTerminalId(UUID.randomUUID().toString());
		vo.setTerminalType("OTHER");
		vo.setShareKey(bankCard.getUserId());
		RepaymentResponseVo responseVo = PayCommonUtil.repayment(vo);

		String payMsg = "";
		String payOrderNo = "";
		if (PayCommonUtil.success(responseVo.getStatus())) {
			payOrderNo = responseVo.getOrderNo();
		}

		PayLog payLog = new PayLog();
		payLog.setOrderNo(responseVo.getOrderNo());
		if (StringUtil.isNotEmpty(payOrderNo)) {
			payLog.setPayOrderNo(payOrderNo);
		}
		payLog.setUserId(borrowRepay.getUserId());
		payLog.setBorrowId(borrowRepay.getBorrowId());
		payLog.setAmount(NumberUtil.getDouble(amount));
		payLog.setCardNo(bankCard.getCardNo());
		payLog.setBank(bankCard.getBank());
		payLog.setSource(PayLogModel.SOURCE_FUNDS_OWN);
		payLog.setType(PayLogModel.TYPE_COLLECT);
		payLog.setScenes(PayLogModel.SCENES_DEDUCTION);
		payLog.setState(PayLogModel.STATE_PAYMENT_WAIT);
		payLog.setRemark(payMsg);
		payLog.setPayReqTime(payReqTime);
		payLog.setCreateTime(DateUtil.getNow());
		payLogService.save(payLog);

		Map<String,Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, result);
	}
	
	
	/**
	 * 还款中检查
	 * @param borrowId
	 */
	@RequestMapping(value = "/modules/manage/borrow/repay/check.htm")
	@RequiresPermission(code = "modules:manage:borrow:repay:check", name = "还款记录列表")
	public void check(@RequestParam(value = "borrowId") String borrowId) {
		borrowRepayService.repayCheck(Long.parseLong(borrowId));
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, result);
	}
}
