package com.xiji.cashloan.api.controller;

import com.alibaba.fastjson.JSON;
import com.xiji.cashloan.api.controller.assist.RepaymentNotifyAssist;
import com.xiji.cashloan.cl.domain.PayLog;
import com.xiji.cashloan.cl.domain.PayReqLog;
import com.xiji.cashloan.cl.domain.PayRespLog;
import com.xiji.cashloan.cl.model.PayLogModel;
import com.xiji.cashloan.cl.model.PayRespLogModel;
import com.xiji.cashloan.cl.model.pay.common.constant.PayConstant;
import com.xiji.cashloan.cl.model.pay.common.dto.RepaymentNotifyDto;
import com.xiji.cashloan.cl.model.pay.fuiou.agreement.PaymentNotifyModel;
import com.xiji.cashloan.cl.model.pay.helipay.constant.HelipayConstant;
import com.xiji.cashloan.cl.model.pay.helipay.util.MessageHandle;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.BindCardPayResponseVo;
import com.xiji.cashloan.cl.service.PayLogService;
import com.xiji.cashloan.cl.service.PayReqLogService;
import com.xiji.cashloan.cl.service.PayRespLogService;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private RepaymentNotifyAssist repaymentNotifyAssist;

	/**
	 * 补扣 - 异步通知处理
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/pay/fuiou/repaymentNotify.htm")
	public void fuiouRepaymentNotify(HttpServletRequest request) throws Exception {
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
		RepaymentNotifyDto dto = new RepaymentNotifyDto();
		dto.setMessage(model.getResponseMsg());
		if (model.checkReturn()) {
			dto.setStatus(PayConstant.RESULT_SUCCESS);
		}
		String message = "";
		if (PayLogModel.SCENES_REPAYMENT.equals(payLog.getScenes())) {
			message = repaymentNotifyAssist.doScenesRepayment(dto,payLog);
		}else if (PayLogModel.SCENES_DEDUCTION.equals(payLog.getScenes())){
			message = repaymentNotifyAssist.doScenesDeduction(dto,payLog);
		}else if (PayLogModel.SCENES_ACTIVE_REPAYMENT.equals(payLog.getScenes())){
			message = repaymentNotifyAssist.doScenesActiveRepayment(dto,payLog);
		}else if (PayLogModel.SCENES_ACTIVE_DELAYPAY.equals(payLog.getScenes())){
			message = repaymentNotifyAssist.doScenesActiveDelay(dto,payLog);
		}else {
			logger.error("没有合适的场景，异步通知处理失败" + orderNo);
			response.setStatus(400);
			return;
		}

		if (StringUtil.isNotEmpty(message)) {
			ServletUtils.writeToResponse(response, "1");
		}
	}

	/**
	 * 合利宝 用户扣款异步通知接口，主动还款等异步通知接口
	 * @throws Exception
	 */
	@RequestMapping(value = "/pay/helipay/repaymentNotify.htm")
	public void heliBaoRepaymentNotify(BindCardPayResponseVo vo) throws Exception {
		String jsonMsg = JSON.toJSONString(vo);
		if (logger.isDebugEnabled()) {
			logger.debug("代扣 - 异步通知：" + jsonMsg);
		}

		String orderNo = vo.getRt5_orderId();
		if (!MessageHandle.checkSign(vo)) {
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
		RepaymentNotifyDto dto = new RepaymentNotifyDto();
		dto.setMessage(vo.getRt3_retMsg());
		if (StringUtil.equals(vo.getRt2_retCode(), HelipayConstant.RESULT_CODE_SUCCESS)) {
			dto.setStatus(PayConstant.RESULT_SUCCESS);
		}
		String message = "";
		if (PayLogModel.SCENES_REPAYMENT.equals(payLog.getScenes())) {
			message = repaymentNotifyAssist.doScenesRepayment(dto,payLog);
		}else if (PayLogModel.SCENES_DEDUCTION.equals(payLog.getScenes())){
			message = repaymentNotifyAssist.doScenesDeduction(dto,payLog);
		}else if (PayLogModel.SCENES_ACTIVE_REPAYMENT.equals(payLog.getScenes())){
			message = repaymentNotifyAssist.doScenesActiveRepayment(dto,payLog);
		}else if (PayLogModel.SCENES_ACTIVE_DELAYPAY.equals(payLog.getScenes())){
			message = repaymentNotifyAssist.doScenesActiveDelay(dto,payLog);
		}else {
			logger.error("没有合适的场景，异步通知处理失败" + orderNo);
			response.setStatus(400);
			return;
		}

		if (StringUtil.equals(message,PayConstant.RESULT_SUCCESS)) {
			ServletUtils.writeToResponse(response, "success");
		}
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
