package com.rongdu.cashloan.manage.controller;

import com.github.pagehelper.Page;
import com.rongdu.cashloan.cl.domain.BankCard;
import com.rongdu.cashloan.cl.domain.BorrowRepay;
import com.rongdu.cashloan.cl.domain.BorrowRepayLog;
import com.rongdu.cashloan.cl.domain.PayLog;
import com.rongdu.cashloan.cl.model.ManageBRepayLogModel;
import com.rongdu.cashloan.cl.model.PayLogModel;
import com.rongdu.cashloan.cl.model.pay.fuiou.agreement.OrderQryByMSsn;
import com.rongdu.cashloan.cl.model.pay.fuiou.agreement.OrderQryResp;
import com.rongdu.cashloan.cl.model.pay.fuiou.agreement.OrderXmlBeanReq;
import com.rongdu.cashloan.cl.model.pay.fuiou.agreement.OrderXmlBeanResp;
import com.rongdu.cashloan.cl.model.pay.fuiou.constant.FuiouConstant;
import com.rongdu.cashloan.cl.model.pay.fuiou.payfor.PayforreqModel;
import com.rongdu.cashloan.cl.model.pay.fuiou.payfor.PayforrspModel;
import com.rongdu.cashloan.cl.model.pay.fuiou.util.FuiouAgreementPayHelper;
import com.rongdu.cashloan.cl.model.pay.fuiou.util.FuiouHelper;
import com.rongdu.cashloan.cl.monitor.BusinessExceptionMonitor;
import com.rongdu.cashloan.cl.service.BankCardService;
import com.rongdu.cashloan.cl.service.BorrowRepayLogService;
import com.rongdu.cashloan.cl.service.BorrowRepayService;
import com.rongdu.cashloan.cl.service.ClBorrowService;
import com.rongdu.cashloan.cl.service.PayLogService;
import com.rongdu.cashloan.cl.util.fuiou.AmtUtil;
import com.rongdu.cashloan.core.common.context.Constant;
import com.rongdu.cashloan.core.common.context.Global;
import com.rongdu.cashloan.core.common.util.DateUtil;
import com.rongdu.cashloan.core.common.util.IpUtil;
import com.rongdu.cashloan.core.common.util.JsonUtil;
import com.rongdu.cashloan.core.common.util.OrderNoUtil;
import com.rongdu.cashloan.core.common.util.RdPage;
import com.rongdu.cashloan.core.common.util.ServletUtils;
import com.rongdu.cashloan.core.common.util.StringUtil;
import com.rongdu.cashloan.core.domain.Borrow;
import com.rongdu.cashloan.core.domain.User;
import com.rongdu.cashloan.core.domain.UserBaseInfo;
import com.rongdu.cashloan.core.service.CloanUserService;
import com.rongdu.cashloan.core.service.UserBaseInfoService;
import com.rongdu.cashloan.system.permission.annotation.RequiresPermission;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
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
 * @author gc
 * @version 1.0.0
 * @date 2017年3月30日 上午10:16:17
 * Copyright 杭州融都科技股份有限公司 All Rights Reserved
 * 官方网站：www.erongdu.com
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
		String orderNo = OrderNoUtil.getSerialNumber();
		
//		PaymentModel payment = new PaymentModel(orderNo);
//		payment.setDt_order(DateUtil.dateStr3(date));
//		if ("dev".equals(Global.getValue("app_environment"))) {
//			payment.setMoney_order("0.01");
//		} else {
//			payment.setMoney_order(amount);
//		}
//		payment.setAmount(NumberUtil.getDouble(amount));
//
//		payment.setCard_no(bankCard.getCardNo());
//		payment.setAcct_name(baseInfo.getRealName());
//		payment.setInfo_order(borrow.getOrderNo() + "付款");
//		payment.setMemo(borrow.getOrderNo() + "付款");
//		payment.setNotify_url(Global.getValue("server_host") + "/pay/lianlian/refundNotify.htm");
//		LianLianHelper helper = new LianLianHelper();
//		payment = (PaymentModel) helper.payment(payment);

		PayforreqModel model = new PayforreqModel();
		model.setMerdt(tool.util.DateUtil.dateStr7(date));
		model.setOrderno(orderNo);
		model.setAccntno(bankCard.getCardNo());
		model.setAccntnm(baseInfo.getRealName());
		if ("dev".equals(Global.getValue("app_environment"))) {
			model.setAmt(AmtUtil.convertAmtToBranch(3.0));
		} else {
			model.setAmt(AmtUtil.convertAmtToBranch(amount));
		}
		model.setMobile(baseInfo.getPhone());
		model.setEntseq(borrow.getOrderNo());//借款号
		model.setMemo(borrow.getOrderNo() + "付款");
		model.setAddDesc(FuiouConstant.DAIFU_PAYFOR_ADDDESC);
		FuiouHelper fuiouHelper = new FuiouHelper();
		PayforrspModel payResult = fuiouHelper.payment(model);

		PayLog payLog = new PayLog();
		payLog.setOrderNo(orderNo);
		payLog.setUserId(borrow.getUserId());
		payLog.setBorrowId(borrow.getId());
		payLog.setAmount(NumberUtil.getDouble(amount));
		payLog.setCardNo(bankCard.getCardNo());
		payLog.setBank(bankCard.getBank());
		payLog.setSource(PayLogModel.SOURCE_FUNDS_OWN);
		payLog.setType(PayLogModel.TYPE_PAYMENT);
		payLog.setScenes(PayLogModel.SCENES_REFUND);

		if (payResult.acceptSuccess() || payResult.success()) { //受理成功
			payLog.setState(PayLogModel.STATE_PAYMENT_WAIT);
		} else if (payResult.error()) { // 疑似重复订单，待人工审核
			payLog.setState(PayLogModel.STATE_PENDING_REVIEW);
//					payLog.setConfirmCode(payment.getConfirm_code());
			payLog.setUpdateTime(DateUtil.getNow());
		} else {
			BusinessExceptionMonitor.add(BusinessExceptionMonitor.TYPE_11, payLog.getOrderNo(), fuiouHelper.getRemark(payResult));
			payLog.setState(PayLogModel.STATE_PAYMENT_FAILED);
			payLog.setUpdateTime(DateUtil.getNow());
		}

		payLog.setCode(payResult.getRet());
		payLog.setRemark(fuiouHelper.getRemark(payResult));
		payLog.setPayReqTime(date);
		payLog.setCreateTime(DateUtil.getNow());
		payLogService.save(payLog);
		
		Map<String,Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, result);
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
		FuiouAgreementPayHelper payHelper = new FuiouAgreementPayHelper();
		// 订单存在并不是支付失败记录
		if (null != deductionLog
				&& !PayLogModel.STATE_PAYMENT_FAILED.equals(deductionLog.getState())) {
			OrderQryByMSsn beanreq = new OrderQryByMSsn();
			beanreq.setMchntOrderId(deductionLog.getOrderNo());
			OrderQryResp resp = payHelper.checkResult(beanreq);
			String key = Global.getValue("protocol_mchntcd_key");
			if (resp.checkReturn() && resp.checkSign(key)) {
				// 更新订单状态
				deductionLog.setState(PayLogModel.STATE_PAYMENT_SUCCESS);
				deductionLog.setUpdateTime(DateUtil.getNow());
				payLogService.updateById(deductionLog);
			}
		}
		logger.info("进行补扣代扣" + amount);
		Date payReqTime = DateUtil.getNow();
		String orderNo = OrderNoUtil.getSerialNumber();
		OrderXmlBeanReq beanReq = new OrderXmlBeanReq();
		beanReq.setUserId(user.getUuid());
		beanReq.setUserIp(IpUtil.getLocalIp());
		beanReq.setType("03");
		beanReq.setMchntOrderId(orderNo);
		if ("dev".equals(Global.getValue("app_environment"))) {
			beanReq.setAmt(AmtUtil.convertAmtToBranch("0.01"));
		} else {
			beanReq.setAmt(AmtUtil.convertAmtToBranch(amount));
		}

		beanReq.setProtocolNo(bankCard.getAgreeNo());
		beanReq.setNeedSendMsg("0");
		beanReq.setBackUrl(Global.getValue("server_host")+ "/pay/fuiou/repaymentNotify.htm");
		beanReq.setRem1("还款" + borrow.getOrderNo());
		beanReq.setRem2("repayment_" + borrow.getOrderNo());
		String key = Global.getValue("protocol_mchntcd_key");
		OrderXmlBeanResp resp = payHelper.repayment(beanReq);
		String payMsg = "";
		if (resp.checkSign(key)) {
			payMsg = resp.getResponseMsg();
			if (resp.checkReturn() && StringUtil.isNotEmpty(resp.getOrderId())) {
				payMsg = resp.getOrderId()+"|" + payMsg;
			}
		}

		PayLog payLog = new PayLog();
		payLog.setOrderNo(orderNo);
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
