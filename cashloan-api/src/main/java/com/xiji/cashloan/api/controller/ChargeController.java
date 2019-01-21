package com.xiji.cashloan.api.controller;

import com.alibaba.fastjson.JSON;
import com.xiji.cashloan.cl.domain.BankCard;
import com.xiji.cashloan.cl.domain.BorrowRepay;
import com.xiji.cashloan.cl.domain.BorrowRepayLog;
import com.xiji.cashloan.cl.domain.PayLog;
import com.xiji.cashloan.cl.domain.PayReqLog;
import com.xiji.cashloan.cl.domain.PayRespLog;
import com.xiji.cashloan.cl.model.BorrowRepayLogModel;
import com.xiji.cashloan.cl.model.BorrowRepayModel;
import com.xiji.cashloan.cl.model.PayLogModel;
import com.xiji.cashloan.cl.model.PayRespLogModel;
import com.xiji.cashloan.cl.model.pay.fuiou.agreement.PaymentNotifyModel;
import com.xiji.cashloan.cl.service.BankCardService;
import com.xiji.cashloan.cl.service.BorrowRepayLogService;
import com.xiji.cashloan.cl.service.BorrowRepayService;
import com.xiji.cashloan.cl.service.ClSmsService;
import com.xiji.cashloan.cl.service.PayLogService;
import com.xiji.cashloan.cl.service.PayReqLogService;
import com.xiji.cashloan.cl.service.PayRespLogService;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import com.xiji.cashloan.core.model.BorrowModel;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tool.util.DateUtil;

/**
 * 连连分期付(代扣)异步通知
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2018/12/03
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Controller
@Scope("prototype")
public class ChargeController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(ChargeController.class);
	
	@Resource
	private PayReqLogService payReqLogService;
	@Resource
	private PayRespLogService payRespLogService;
	@Resource
	private PayLogService payLogService;
	@Resource	
	private BorrowRepayService borrowRepayService;
	@Resource
	private BorrowRepayLogService borrowRepayLogService;
	@Resource
	private BankCardService bankCardService;
	@Resource
	private ClSmsService clSmsService;

	/**
	 * 补扣 - 异步通知处理
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/pay/fuiou/repaymentNotify.htm")
	public void paymentNotify(HttpServletRequest request) throws Exception {
		PaymentNotifyModel model = parseParam(request);

		String jsonMsg = JSON.toJSONString(model);
		if (logger.isDebugEnabled()) {
			logger.debug("代扣 - 异步通知：" + jsonMsg);
		}

		String orderNo = model.getMchntOrderId();
		if (!model.checkSign()) {
			logger.error("验签失败" + orderNo);
			//需要抛异常，否则服务的返回http响应吗是200，则支付调用方认为服务调用成功
			response.setStatus(400);
			return;
		}
		logger.info("进入订单" + orderNo + "处理中.....");

		PayReqLog payReqLog = payReqLogService.findByOrderNo(orderNo);
		if (payReqLog != null) {
			// 保存respLog
			PayRespLog payRespLog = new PayRespLog(orderNo,PayRespLogModel.RESP_LOG_TYPE_NOTIFY,jsonMsg);
			payRespLogService.save(payRespLog);

			// 更新reqLog
			modifyPayReqLog(payReqLog,jsonMsg);
		}

		PayLog payLog = payLogService.findByOrderNo(orderNo);
		if(null  == payLog ){
			logger.warn("未查询到对应的支付订单");
			response.setStatus(400);
			return;
		}

		if (PayLogModel.SCENES_REPAYMENT.equals(payLog.getScenes())) {
			doScenesRepayment(model,payLog);
		}else if (PayLogModel.SCENES_DEDUCTION.equals(payLog.getScenes())){
			doScenesDeduction(model,payLog);
		}else if (PayLogModel.SCENES_ACTIVE_REPAYMENT.equals(payLog.getScenes())){
			doScenesActiveRepayment(model,payLog);
		}else if (PayLogModel.SCENES_ACTIVE_DELAYPAY.equals(payLog.getScenes())){
			doScenesActiveDelay(model,payLog);
		}else {
			logger.error("没有合适的场景，异步通知处理失败" + orderNo);
			response.setStatus(400);
			return;
		}
	}

	private void doScenesRepayment(PaymentNotifyModel model,PayLog payLog)throws Exception {
		logger.info("分期付 (还款)- 异步通知：-支付状态是：" + model.getResponseCode());
		if (PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState())
			|| PayLogModel.STATE_PENDING_REVIEW.equals(payLog.getState())) {

			// 分期付扣款成功，更新借款状态及支付订单 ，否则只更新订单状态
			if (model.checkReturn()) {

				// 查找对应的还款计划
				Map<String, Object> repayMap = new HashMap<String, Object>();
				repayMap.put("userId", payLog.getUserId());
				repayMap.put("borrowId", payLog.getBorrowId());
				BorrowRepay borrowRepay = borrowRepayService.findSelective(repayMap);
				BankCard bankCard = bankCardService.getBankCardByUserId(payLog.getUserId());

				if (borrowRepay != null) {
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("id", borrowRepay.getId());
					//param.put("repayTime", DateUtil.dateStr4(DateUtil.getNow()));
					param.put("repayTime", DateUtil.getNow());
					param.put("repayWay", BorrowRepayLogModel.REPAY_WAY_CHARGE);
					param.put("repayAccount", bankCard.getCardNo());
					param.put("amount", payLog.getAmount());
					param.put("serialNumber", payLog.getOrderNo());
					param.put("penaltyAmout", borrowRepay.getPenaltyAmout());
					param.put("state", "10");
					if (!borrowRepay.getState().equals(BorrowRepayModel.STATE_REPAY_YES)) {
						borrowRepayService.confirmRepay(param);
					}
				}

				// 更新订单状态
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("state", PayLogModel.STATE_PAYMENT_SUCCESS);
				paramMap.put("updateTime",DateUtil.getNow());
				paramMap.put("id",payLog.getId());
				payLogService.updateSelective(paramMap);
				//发送扣款成功短信
				clSmsService.repayInform(borrowRepay.getUserId(), borrowRepay.getBorrowId());

			} else {
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("state", PayLogModel.STATE_PAYMENT_FAILED);
				paramMap.put("updateTime",DateUtil.getNow());
				paramMap.put("id",payLog.getId());
				payLogService.updateSelective(paramMap);
			}
		}else{
			logger.info("订单"+ payLog.getOrderNo() +"已处理");
		}
		ServletUtils.writeToResponse(response, "1");
	}
	/**
	 * 主动扣款
	 * @param model
	 * @param payLog
	 * @throws Exception
	 */
	private void doScenesActiveRepayment(PaymentNotifyModel model,PayLog payLog)throws Exception {
		logger.info("主动扣款 - 异步通知：-支付状态是：" + model.getResponseCode());
		if (PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState())
			|| PayLogModel.STATE_PENDING_REVIEW.equals(payLog.getState())) {

			//付扣款成功，更新借款状态及支付订单 ，否则只更新订单状态
			// 分期付扣款成功，更新借款状态及支付订单 ，否则只更新订单状态
			if (model.checkReturn()) {

				// 查找对应的还款计划
				Map<String, Object> repayMap = new HashMap<String, Object>();
				repayMap.put("userId", payLog.getUserId());
				repayMap.put("borrowId", payLog.getBorrowId());
				BorrowRepay borrowRepay = borrowRepayService.findSelective(repayMap);
				BankCard bankCard = bankCardService.getBankCardByUserId(payLog.getUserId());

				if (borrowRepay != null) {
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("id", borrowRepay.getId());
					//param.put("repayTime", DateUtil.dateStr4(DateUtil.getNow()));
					param.put("repayTime", DateUtil.getNow());
					param.put("repayWay", BorrowRepayLogModel.REPAY_WAY_CHARGE);
					param.put("repayAccount", bankCard.getCardNo());
					param.put("amount", payLog.getAmount());
					param.put("serialNumber", payLog.getOrderNo());
					param.put("penaltyAmout", borrowRepay.getPenaltyAmout());
					param.put("state", "10");
					if (!borrowRepay.getState().equals(BorrowRepayModel.STATE_REPAY_YES)) {
						borrowRepayService.confirmRepay(param);
					}
				}

				// 更新订单状态
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("state", PayLogModel.STATE_PAYMENT_SUCCESS);
				paramMap.put("updateTime",DateUtil.getNow());
				paramMap.put("id",payLog.getId());
				payLogService.updateSelective(paramMap);
				//发送扣款成功短信
				clSmsService.repayInform(borrowRepay.getUserId(), borrowRepay.getBorrowId());

			} else {
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("state", PayLogModel.STATE_PAYMENT_FAILED);
				paramMap.put("updateTime",DateUtil.getNow());
				paramMap.put("id",payLog.getId());
				payLogService.updateSelective(paramMap);
			}
		}else{
			logger.info("订单" + payLog.getOrderNo() + "已处理");
		}
		ServletUtils.writeToResponse(response, "1");
	}
	/**
	 * 主动展期，
	 * 展期和主动代扣不一样，扣款的金额不一样，扣款金额为服务费
	 * @param model
	 * @param payLog
	 * @throws Exception
	 */
	private void doScenesActiveDelay(PaymentNotifyModel model,PayLog payLog)throws Exception {
		logger.info("主动展期 - 异步通知：-支付状态是：" + model.getResponseCode());
		if (PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState())
			|| PayLogModel.STATE_PENDING_REVIEW.equals(payLog.getState())) {

			// 分期付扣款成功，更新借款状态及支付订单 ，否则只更新订单状态
			if (model.checkReturn()) {
				// 查找对应的还款计划
				Map<String, Object> repayMap = new HashMap<String, Object>();
				repayMap.put("userId", payLog.getUserId());
				repayMap.put("borrowId", payLog.getBorrowId());
				repayMap.put("state", BorrowRepayModel.STATE_REPAY_NO);
				BorrowRepay borrowRepay = borrowRepayService.findSelective(repayMap);
				BankCard bankCard = bankCardService.getBankCardByUserId(payLog.getUserId());
				Date repayTime = null;
				if (borrowRepay != null) {
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("id", borrowRepay.getId());
					param.put("state", BorrowModel.STATE_DELAY_PAY);
					param.put("amount", payLog.getAmount());
					param.put("repayWay", BorrowRepayLogModel.REPAY_WAY_CHARGE);
					param.put("repayAccount", bankCard.getCardNo());
					param.put("serialNumber", payLog.getOrderNo());
					if (!borrowRepay.getState().equals(BorrowRepayModel.STATE_REPAY_YES)) {
						Map<String, Object> delayPayMap = borrowRepayService.confirmDelayPay(param);
						if (delayPayMap != null) {
							repayTime = (Date) delayPayMap.get("repayTime");
						}
					}
				}
				// 更新订单状态
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("state", PayLogModel.STATE_PAYMENT_SUCCESS);
				paramMap.put("updateTime",DateUtil.getNow());
				paramMap.put("id",payLog.getId());
				payLogService.updateSelective(paramMap);
				//发送展期成功短信
				clSmsService.delayPlan(payLog.getUserId(), payLog.getBorrowId(),repayTime);
			} else {
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("state", PayLogModel.STATE_PAYMENT_FAILED);
				paramMap.put("updateTime",DateUtil.getNow());
				paramMap.put("id",payLog.getId());
				payLogService.updateSelective(paramMap);
			}
		}else{
			logger.info("订单" + payLog.getOrderNo() + "已处理");
		}
		ServletUtils.writeToResponse(response, "1");
	}
	/**
	 * 扣减
	 * @param model
	 * @param payLog
	 * @throws Exception
	 */
	private void doScenesDeduction(PaymentNotifyModel model,PayLog payLog)throws Exception {
		logger.info("分期付 (补扣)- 异步通知：-支付状态是：" + model.getResponseCode());
		if (PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState())
			|| PayLogModel.STATE_PENDING_REVIEW.equals(payLog.getState())) {

			// 分期付扣款成功，更新借款状态及支付订单 ，否则只更新订单状态
			if (model.checkReturn()) {
				// 查询还款记录
				Map<String, Object> repayLogMap =  new HashMap<String, Object>();
				repayLogMap.put("borrowId", payLog.getBorrowId());
				repayLogMap.put("userId", payLog.getUserId());
				BorrowRepayLog repayLog = borrowRepayLogService.findSelective(repayLogMap);

				// 更新还款记录
				Map<String, Object> refundDeductionMap = new HashMap<String, Object>();
				refundDeductionMap.put("id", repayLog.getId());
				refundDeductionMap.put("refundDeduction", payLog.getAmount());
				refundDeductionMap.put("payTime", DateUtil.getNow());
				borrowRepayLogService.refundDeduction(refundDeductionMap);

				// 更新订单状态
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("state", PayLogModel.STATE_PAYMENT_SUCCESS);
				paramMap.put("updateTime",DateUtil.getNow());
				paramMap.put("id",payLog.getId());
				payLogService.updateSelective(paramMap);
				//发送扣款成功短信
				clSmsService.repayInform(payLog.getUserId(), payLog.getBorrowId());
			} else {
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("state", PayLogModel.STATE_PAYMENT_FAILED);
				paramMap.put("updateTime",DateUtil.getNow());
				paramMap.put("id",payLog.getId());
				payLogService.updateSelective(paramMap);
			}
		}else{
			logger.info("订单" + payLog.getOrderNo() + "已处理");
		}
		ServletUtils.writeToResponse(response, "1");
	}

	private void modifyPayReqLog (PayReqLog payReqLog,String params){
		payReqLog.setNotifyParams(params);
		payReqLog.setNotifyTime(DateUtil.getNow());
		payReqLogService.updateById(payReqLog);
	}

	public PaymentNotifyModel parseParam(HttpServletRequest request) {
		PaymentNotifyModel model = new PaymentNotifyModel();
		model.setVersion(request.getParameter("VERSION"));
		model.setType(request.getParameter("TYPE"));
		model.setMchntCd(request.getParameter("MCHNTCD"));
		model.setUserId(request.getParameter("USERID"));
		model.setResponseCode(request.getParameter("RESPONSECODE"));
		model.setResponseMsg(request.getParameter("RESPONSEMSG"));
		model.setOrderId(request.getParameter("ORDERID"));
		model.setMchntOrderId(request.getParameter("MCHNTORDERID"));
		model.setAmt(request.getParameter("AMT"));
		model.setBankCard(request.getParameter("BANKCARD"));
		model.setProtocolNo(request.getParameter("PROTOCOLNO"));
		model.setSign(request.getParameter("SIGN"));
		return model;
	}
	
}
