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
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.util.KuaiqianPayUtil;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.util.ParseUtil;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.util.SignUtil;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo.AgreementNotifyResp;
import com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.vo.TransInfo;
import com.xiji.cashloan.cl.service.PayLogService;
import com.xiji.cashloan.cl.service.PayReqLogService;
import com.xiji.cashloan.cl.service.PayRespLogService;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tool.util.DateUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

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
			dto.setCardNo(model.getBankCard());
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

		if (StringUtil.equals(message, PayConstant.RESULT_SUCCESS)) {
			writeResult(response, "1");
		}
	}

	/**
	 * 合利宝 用户扣款异步通知接口，主动还款等异步通知接口
	 * @throws Exception
	 */
	@RequestMapping(value = "/pay/helipay/repaymentNotify.htm")
	public void heliBaoRepaymentNotify(BindCardPayResponseVo vo) throws Exception {
		String jsonMsg = JSON.toJSONString(vo);

		logger.info("协议支付 - 异步通知：" + jsonMsg);
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
			dto.setCardNo(vo.getRt11_bankId());
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

		if (StringUtil.equals(message, PayConstant.RESULT_SUCCESS)) {
			writeResult(response, "success");
		}
	}

	/**
	 * 快钱 用户扣款异步通知接口，主动还款等异步通知接口
	 * @throws Exception
	 */
	@RequestMapping(value = "api/pay/kuaiqian/agreementNotify.htm")
	public void agreementNotify() throws Exception {
		logger.info("----------------快钱协议支付 - 异步通知开始------------------------");
		TransInfo transInfo = new TransInfo();
		ParseUtil parseXML = new ParseUtil();

		// inputStream 流 转化成 String
		String reqData = KuaiqianPayUtil.streamToStr(request);
		logger.error("TR3信息 : "+reqData);

		if(SignUtil.veriSignForXml(reqData)) {
			logger.info("--------------------ReceiveTR3ToTR4:验签失败---------------------");
			response.setStatus(400);
			return;
		}else{
			logger.info("--------------------ReceiveTR3ToTR4:验签成功---------------------");

			//返回TR3后的第一个标志字段
			transInfo.setRecordeText_1("TxnMsgContent");
			//返回TR3后的错误标志字段
			transInfo.setRecordeText_2("ErrorMsgContent");
			//设置最后的解析方式
			transInfo.setFLAG(true);

			logger.info("********************开始接收TR3**************");
			//将获取的数据传入DOM解析函数中
			HashMap respXml=parseXML.parseXML(reqData, transInfo);
			AgreementNotifyResp notifyResp = new AgreementNotifyResp();
			if(respXml != null){
				// 设置接收参数
				notifyResp = this.parseParam(respXml);
				logger.info("********TR3接收完毕**************************** ");

				String notifyRespStr = JSON.toJSONString(notifyResp);
				logger.info("notifyResp ==> "+notifyRespStr);

				logger.info("进入订单" + notifyResp.getExternalRefNumber() + "处理中.....");
				PayReqLog payReqLog = payReqLogService.findByOrderNo(notifyResp.getExternalRefNumber());
				if (payReqLog != null) {
					// 保存respLog
					PayRespLog payRespLog = new PayRespLog(notifyResp.getExternalRefNumber(),PayRespLogModel.RESP_LOG_TYPE_NOTIFY,notifyRespStr);
					payRespLogService.save(payRespLog);

					// 更新reqLog
					modifyPayReqLog(payReqLog,notifyRespStr);
				}

				PayLog payLog = payLogService.findByOrderNo(notifyResp.getExternalRefNumber());
				if(null  == payLog ){
					logger.warn("未查询到对应的支付订单");
					response.setStatus(400);
					return;
				}
				RepaymentNotifyDto dto = new RepaymentNotifyDto();
				dto.setMessage(notifyResp.getResponseTextMessage());

				//当应答码responseCode的值为00时，交易成功 ,txnType :PUR是消费
				if("00".equals(notifyResp.getResponseCode())){
					dto.setStatus(PayConstant.RESULT_SUCCESS);
					dto.setCardNo(notifyResp.getStorableCardNo());
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
					logger.error("没有合适的场景，异步通知处理失败" + notifyResp.getExternalRefNumber());
					response.setStatus(400);
					return;
				}

				/*******************************输出TR4************************************/
				if (StringUtil.equals(message, PayConstant.RESULT_SUCCESS)){
					this.writeResponse(response,notifyResp);
					logger.info("----------------快钱协议支付回调成功且支付成功---------------");
				}
			}
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
	private void writeResult(HttpServletResponse response,String result)throws Exception {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf8");
		response.getOutputStream().write(result.getBytes("utf8"));
	}

	private void writeResponse(HttpServletResponse response,AgreementNotifyResp notifyResp) throws IOException {
		StringBuffer tr4XML=new StringBuffer();
		tr4XML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\"><version>1.0</version><TxnMsgContent>");
		tr4XML.append("<txnType>").append(notifyResp.getTxnType()).append("</txnType>");
		tr4XML.append("<interactiveStatus>TR4</interactiveStatus>");
		tr4XML.append("<merchantId>").append(notifyResp.getMerchantId()).append("</merchantId>");
		if(notifyResp.getSettleMerchantId() !=null && "".equals(notifyResp.getSettleMerchantId())){
			tr4XML.append("<settleMerchantId>").append(notifyResp.getSettleMerchantId()).append("</settleMerchantId>");
		}
		tr4XML.append("<terminalId>").append(notifyResp.getTerminalId()).append("</terminalId>");
		tr4XML.append("<refNumber>").append(notifyResp.getRefNumber()).append("</refNumber>");
		tr4XML.append("</TxnMsgContent></MasMessage>");
		System.out.println("TR3ToTR4信息："+tr4XML.toString());
		response.getOutputStream().write(new String(tr4XML).getBytes("UTF-8"));
	}

	/**
	 * 设置数据
	 * @param respXml
	 * @return
	 */
	private AgreementNotifyResp parseParam(HashMap respXml){
		AgreementNotifyResp agreementNotifyResp = new AgreementNotifyResp();
		//接口版本号（version）
		agreementNotifyResp.setVersion((String)respXml.get("version"));
		//交易类型编码（txnType）
		agreementNotifyResp.setTxnType((String)respXml.get("txnType"));
		//消息状态（interactiveStatus）
		agreementNotifyResp.setInteractiveStatus((String)respXml.get("interactiveStatus"));
		//交易金额（amount）
		agreementNotifyResp.setAmount((String)respXml.get("amount"));
		//商户编号
		agreementNotifyResp.setMerchantId((String)respXml.get("merchantId"));
		//商户编号
		agreementNotifyResp.setSettleMerchantId((String)respXml.get("settleMerchantId"));
		//终端编号（terminalId）
		agreementNotifyResp.setTerminalId((String)respXml.get("terminalId"));
		//外部检索参考号（externalRefNumber）
		agreementNotifyResp.setExternalRefNumber((String)respXml.get("externalRefNumber"));
		//客户号（customerId）
		agreementNotifyResp.setCustomerId((String)respXml.get("customerId"));
		//检索参考号（refNumber）
		agreementNotifyResp.setRefNumber((String)respXml.get("refNumber"));
        //应答码（responseCode）
		agreementNotifyResp.setResponseCode((String)respXml.get("responseCode"));
		// 交易传输时间
		agreementNotifyResp.setTransTime((String)respXml.get("transTime"));
		// 客户端交易时间
		agreementNotifyResp.setEntryTime((String)respXml.get("entryTime"));
		//发卡组织编号（cardOrg）
		agreementNotifyResp.setCardOrg((String)respXml.get("cardOrg"));

		//应答文本信息（responseTextMessage）
		//		String responseTextMessage=(String)respXml.get("responseTextMessage");

		//发卡银行名称（issuer）
		agreementNotifyResp.setIssuer((String)respXml.get("issuer"));
		//缩略卡号（storableCardNo）
		agreementNotifyResp.setStorableCardNo((String)respXml.get("storableCardNo"));
		//授权码（authorizationCode）
		agreementNotifyResp.setAuthorizationCode((String)respXml.get("authorizationCode"));
		//报文数字签名（signature）
		agreementNotifyResp.setSignature((String)respXml.get("signature"));
		return agreementNotifyResp;
	}
}
