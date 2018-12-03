package com.rongdu.cashloan.api.controller;

import com.alibaba.fastjson.JSON;
import com.rongdu.cashloan.api.user.service.ContractService;
import com.rongdu.cashloan.cl.domain.BorrowProgress;
import com.rongdu.cashloan.cl.domain.BorrowRepayLog;
import com.rongdu.cashloan.cl.domain.PayLog;
import com.rongdu.cashloan.cl.domain.PayReqLog;
import com.rongdu.cashloan.cl.domain.PayRespLog;
import com.rongdu.cashloan.cl.model.PayLogModel;
import com.rongdu.cashloan.cl.model.PayRespLogModel;
import com.rongdu.cashloan.cl.model.pay.fuiou.payfor.PayforNotifyModel;
import com.rongdu.cashloan.cl.model.pay.fuiou.payfor.PayforRefundNotifyModel;
import com.rongdu.cashloan.cl.service.BorrowProgressService;
import com.rongdu.cashloan.cl.service.BorrowRepayLogService;
import com.rongdu.cashloan.cl.service.BorrowRepayService;
import com.rongdu.cashloan.cl.service.ClBorrowService;
import com.rongdu.cashloan.cl.service.PayLogService;
import com.rongdu.cashloan.cl.service.PayReqLogService;
import com.rongdu.cashloan.cl.service.PayRespLogService;
import com.rongdu.cashloan.cl.service.ProfitAmountService;
import com.rongdu.cashloan.core.common.context.Global;
import com.rongdu.cashloan.core.common.web.controller.BaseController;
import com.rongdu.cashloan.core.domain.Borrow;
import com.rongdu.cashloan.core.model.BorrowModel;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tool.util.DateUtil;

/**
 * 连连实时付款(代付)异步通知
 * 
 * @author gc
 * @version 1.0.0
 * @date 2017年3月24日 下午2:49:56
 * Copyright 杭州融都科技股份有限公司 All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Controller
@Scope("prototype")
public class PayFuiouController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(PayFuiouController.class);

	@Resource
	private PayReqLogService payReqLogService;
	@Resource
	private PayRespLogService payRespLogService;
	@Resource
	private PayLogService payLogService;
	@Resource
	private ClBorrowService clBorrowService;
	@Resource
	private BorrowProgressService borrowProgressService;
	@Resource
	private BorrowRepayService borrowRepayService;
	@Resource
	private BorrowRepayLogService borrowRepayLogService;
	@Resource
	private ProfitAmountService profitAmountService;
	@Resource
    private ContractService contractService;
	
	@RequestMapping(value = "/test.htm")
	public void test(HttpServletRequest request) throws Exception {
		contractService.buildPdf("37");
	}
	/**
	 * 代付（支付）- 成功通知接口 - 异步通知处理
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/pay/fuiou/paymentNotify.htm")
	public void payforNotify(PayforRefundNotifyModel model) throws Exception {
		doNotifyMessage(model,true);
	}
	/**
	 * 代付（支付）业务 - 失败退票 - 异步通知处理
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/pay/fuiou/refundNotify.htm")
	public void refundNotify(PayforRefundNotifyModel model) throws Exception {
		doNotifyMessage(model,false);
	}

	private void doNotifyMessage(PayforNotifyModel model, boolean ifPaySuccess) throws Exception {
		String params = JSON.toJSONString(model);
		logger.info("实时付款 - 异步通知:" + params);

		String merid = Global.getValue("fuiou_merid");
		String pwd = Global.getValue("fuiou_pwd");
		boolean verifySignFlag = model.checkSign(merid,pwd);

		if (!verifySignFlag) {
			logger.error("验签失败" + model.getOrderno());
			return;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("进入订单" + model.getOrderno() + "处理中.....");
		}

		PayReqLog payReqLog = payReqLogService.findByOrderNo(model.getOrderno());

		if (payReqLog != null) {
			// 保存respLog
			PayRespLog payRespLog = new PayRespLog(model.getOrderno(),PayRespLogModel.RESP_LOG_TYPE_NOTIFY,params);
			payRespLogService.save(payRespLog);

			// 更新reqLog
			modifyPayReqLog(payReqLog,params);
		}

		PayLog payLog = payLogService.findByOrderNo(model.getOrderno());

		if(null  == payLog ){
			logger.warn("未查询到对应的支付订单");
			return ;
		}
		if (PayLogModel.SCENES_LOANS.equals(payLog.getScenes())) {
			doScenesLoans(model,ifPaySuccess,payLog);
		}else if (PayLogModel.SCENES_PROFIT.equals(payLog.getScenes())){
			doScenesProfit(model,ifPaySuccess,payLog);
		}else if (PayLogModel.SCENES_REFUND.equals(payLog.getScenes())){
			doScenesRefund(model,ifPaySuccess,payLog);
		}else {
			logger.error("没有合适的场景，异步通知处理失败" + model.getOrderno());
		}
	}

	/**
	 * 代付，放款异步通知处理
	 * @param model
	 * @param ifPaySuccess
	 * @param payLog
	 * @throws Exception
	 */
	private void doScenesLoans(PayforNotifyModel model, boolean ifPaySuccess,PayLog payLog)throws Exception {
		logger.info("实时付款(放款) - 异步通知-支付状态是：" +ifPaySuccess);
		if (PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState())
			|| PayLogModel.STATE_AUDIT_PASSED.equals(payLog.getState())|| PayLogModel.STATE_PENDING_REVIEW.equals(payLog.getState())) {

			// 代付成功，更新借款状态及支付订单 ，否则只更新订单状态
			if (ifPaySuccess) {
				// 修改借款状态
				Map<String, Object> map = new HashMap<>();
				map.put("id", payLog.getBorrowId());
				map.put("state", BorrowModel.STATE_REPAY);
				clBorrowService.updatePayState(map);

				// 放款进度添加
				BorrowProgress bp = new BorrowProgress();
				bp.setUserId(payLog.getUserId());
				bp.setBorrowId(payLog.getBorrowId());
				bp.setState(BorrowModel.STATE_REPAY);
				bp.setRemark(BorrowModel.convertBorrowRemark(bp.getState()));
				bp.setCreateTime(DateUtil.getNow());
				borrowProgressService.save(bp);

				final Borrow borrow = clBorrowService.getById(payLog.getBorrowId());

				// 生成还款计划并授权
				borrowRepayService.genRepayPlan(borrow);
				// 更新订单状态
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("state", PayLogModel.STATE_PAYMENT_SUCCESS);
				paramMap.put("updateTime",DateUtil.getNow());
				paramMap.put("id",payLog.getId());
				payLogService.updateSelective(paramMap);

				// 生成pdf合同文件
				new Thread(new Runnable() {
					@Override
					public void run() {
						contractService.buildPdf(borrow.getId().toString());
					}
				}).start();

			}else{
				Map<String, Object> borrowMap = new HashMap<String, Object>();
				borrowMap.put("id", payLog.getBorrowId());
				borrowMap.put("state", BorrowModel.STATE_REPAY_FAIL);
				clBorrowService.updatePayState(borrowMap);

				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("state", PayLogModel.STATE_PAYMENT_FAILED);
				paramMap.put("updateTime",DateUtil.getNow());
//				paramMap.put("remark",model.getInfo_order());
				paramMap.put("id",payLog.getId());
				payLogService.updateSelective(paramMap);

			}
		}else{
			logger.info("订单" + payLog.getOrderNo() + "已处理");
		}
		writeResult(response, "1");
	}

	private void writeResult(HttpServletResponse response,String result)throws Exception {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf8");
		response.getOutputStream().write(result.getBytes("utf8"));
	}
	/**
	 * 取现（分润），放款异步通知处理
	 * @param model
	 * @param ifPaySuccess
	 * @param payLog
	 * @throws Exception
	 */
	private void doScenesProfit(PayforNotifyModel model, boolean ifPaySuccess,PayLog payLog)throws Exception {
		logger.info("实时付款(取现) - 异步通知-支付状态是：" +ifPaySuccess);

		if (PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState())
			|| PayLogModel.STATE_AUDIT_PASSED.equals(payLog.getState())|| PayLogModel.STATE_PENDING_REVIEW.equals(payLog.getState())) {

			// 代付成功，更新借款状态及支付订单 ，否则只更新订单状态
			if (ifPaySuccess) {
				// 更新取现金额， 添加取现记录
				profitAmountService.cash(payLog.getUserId(), payLog.getAmount());

				// 更新订单状态
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("state", PayLogModel.STATE_PAYMENT_SUCCESS);
				paramMap.put("updateTime",DateUtil.getNow());
				paramMap.put("id",payLog.getId());
				payLogService.updateSelective(paramMap);

			}else{
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("state", PayLogModel.STATE_PAYMENT_FAILED);
				paramMap.put("updateTime",DateUtil.getNow());
				paramMap.put("id",payLog.getId());
				payLogService.updateSelective(paramMap);
			}
		}else{
			logger.info("订单" + payLog.getOrderNo() + "已处理");

		}
		writeResult(response, "1");
	}
	/**
	 * 退还，异步通知处理
	 * @param model
	 * @param ifPaySuccess
	 * @param payLog
	 * @throws Exception
	 */
	private void doScenesRefund(PayforNotifyModel model, boolean ifPaySuccess,PayLog payLog)throws Exception {
		logger.info("实时付款(退还) - 异步通知-支付状态是：" +ifPaySuccess);
		if (PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState())
			|| PayLogModel.STATE_AUDIT_PASSED.equals(payLog.getState())|| PayLogModel.STATE_PENDING_REVIEW.equals(payLog.getState())) {

			//代付成功 ，否则只更新订单状态
			if (ifPaySuccess){
				// 查询还款记录
				Map<String, Object> repayLogMap =  new HashMap<String, Object>();
				repayLogMap.put("borrowId", payLog.getBorrowId());
				repayLogMap.put("userId", payLog.getUserId());
				BorrowRepayLog repayLog = borrowRepayLogService.findSelective(repayLogMap);

				// 更新还款记录
				Map<String, Object> refundDeductionMap = new HashMap<String, Object>();
				refundDeductionMap.put("id", repayLog.getId());
				refundDeductionMap.put("refundDeduction", - payLog.getAmount());
				refundDeductionMap.put("payTime", DateUtil.getNow());
				borrowRepayLogService.refundDeduction(refundDeductionMap);

				// 更新订单状态
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("state", PayLogModel.STATE_PAYMENT_SUCCESS);
				paramMap.put("updateTime",DateUtil.getNow());
				paramMap.put("id",payLog.getId());
				payLogService.updateSelective(paramMap);
			}else {
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("state", PayLogModel.STATE_PAYMENT_FAILED);
				paramMap.put("updateTime",DateUtil.getNow());
				paramMap.put("id",payLog.getId());
				payLogService.updateSelective(paramMap);
			}
		}else{
			logger.info("订单" + payLog.getOrderNo() + "已处理");
		}
		writeResult(response, "1");
	}

	private void modifyPayReqLog (PayReqLog payReqLog,String params){
		payReqLog.setNotifyParams(params);
		payReqLog.setNotifyTime(DateUtil.getNow());
		payReqLogService.updateById(payReqLog);
	}
}