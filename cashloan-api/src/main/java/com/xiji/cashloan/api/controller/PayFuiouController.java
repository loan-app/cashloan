package com.xiji.cashloan.api.controller;

import com.alibaba.fastjson.JSON;
import com.xiji.cashloan.api.controller.assist.PaymentNotifyAssist;
import com.xiji.cashloan.api.user.service.ContractService;
import com.xiji.cashloan.cl.domain.PayLog;
import com.xiji.cashloan.cl.domain.PayReqLog;
import com.xiji.cashloan.cl.domain.PayRespLog;
import com.xiji.cashloan.cl.model.PayLogModel;
import com.xiji.cashloan.cl.model.PayRespLogModel;
import com.xiji.cashloan.cl.model.pay.common.constant.PayConstant;
import com.xiji.cashloan.cl.model.pay.common.dto.RepaymentNotifyDto;
import com.xiji.cashloan.cl.model.pay.fuiou.payfor.PayforNotifyModel;
import com.xiji.cashloan.cl.model.pay.fuiou.payfor.PayforRefundNotifyModel;
import com.xiji.cashloan.cl.model.pay.helipay.constant.HelipayConstant;
import com.xiji.cashloan.cl.model.pay.helipay.util.HelipayUtil;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.HeliPayForPaymentNotifyVo;
import com.xiji.cashloan.cl.model.pay.kuaiqian.KuaiqianPayHelper;
import com.xiji.cashloan.cl.model.pay.kuaiqian.constant.KuaiqianPayConstant;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.notifymock.dto.NotifyRequest;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.notifymock.dto.Pay2bankNotify;
import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.notifymock.util.CCSUtil;
import com.xiji.cashloan.cl.model.pay.kuaiqian.util.KuaiqianPayUtil;
import com.xiji.cashloan.cl.service.PayLogService;
import com.xiji.cashloan.cl.service.PayReqLogService;
import com.xiji.cashloan.cl.service.PayRespLogService;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tool.util.DateUtil;
import tool.util.StringUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 实时付款(代付)异步通知
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
public class PayFuiouController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(PayFuiouController.class);

	@Resource
	private PayReqLogService payReqLogService;
	@Resource
	private PayRespLogService payRespLogService;
	@Resource
	private PayLogService payLogService;
	@Resource
    private ContractService contractService;

	@Autowired
	private PaymentNotifyAssist paymentNotifyAssist;

	@RequestMapping(value = "/test.htm")
	public void test(HttpServletRequest request) throws Exception {
		contractService.buildPdf("37");
	}
	/**
	 * 富有异步通知
	 * 代付（支付）- 成功通知接口 - 异步通知处理
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/pay/fuiou/paymentNotify.htm")
	public void payforNotify(PayforRefundNotifyModel model) throws Exception {
		doNotifyMessage(model,true);
	}
	/**
	 * 富有异步通知
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
		RepaymentNotifyDto dto = new RepaymentNotifyDto();
		dto.setPayPlatNo(model.getFuorderno());
		if (ifPaySuccess) {
			dto.setStatus(PayConstant.RESULT_SUCCESS);
		}else {
			dto.setStatus(PayConstant.STATUS_FAIL);
		}

		String message = "";
		if (PayLogModel.SCENES_LOANS.equals(payLog.getScenes())) {
			message = paymentNotifyAssist.doScenesLoans(dto,payLog);
		}else if (PayLogModel.SCENES_PROFIT.equals(payLog.getScenes())){
			message = paymentNotifyAssist.doScenesProfit(dto,payLog);
		}else if (PayLogModel.SCENES_REFUND.equals(payLog.getScenes())){
			message = paymentNotifyAssist.doScenesRefund(dto,payLog);
		}else {
			logger.error("没有合适的场景，异步通知处理失败" + model.getOrderno());
		}

		if (StringUtil.equals(message, PayConstant.RESULT_SUCCESS)) {
			writeResult(response, "1");
		}
	}

	/**
	 * 合利宝异步通知接口：
	 * 代付（支付）- 成功通知接口 - 异步通知处理
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/pay/helipay/paymentNotify.htm")
	public void helipayPaymentNotify(HeliPayForPaymentNotifyVo model) throws Exception {
		String params = JSON.toJSONString(model);
		logger.info("实时付款 - 异步通知:" + params);

		String orderNo = model.getRt5_orderId();
		if (!HelipayUtil.checkNotifySign(model)) {
			logger.error("验签失败" + orderNo);
			return;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("进入订单" + orderNo + "处理中.....");
		}

		PayReqLog payReqLog = payReqLogService.findByOrderNo(model.getRt5_orderId());

		if (payReqLog != null) {
			// 保存respLog
			PayRespLog payRespLog = new PayRespLog(orderNo,PayRespLogModel.RESP_LOG_TYPE_NOTIFY,params);
			payRespLogService.save(payRespLog);

			// 更新reqLog
			modifyPayReqLog(payReqLog,params);
		}

		PayLog payLog = payLogService.findByOrderNo(orderNo);

		if(null  == payLog ){
			logger.warn("未查询到对应的支付订单");
			return ;
		}
		RepaymentNotifyDto dto = new RepaymentNotifyDto();
		dto.setPayPlatNo(model.getRt6_serialNumber());
		if (StringUtil.equals(model.getRt2_retCode(), HelipayConstant.RESULT_CODE_SUCCESS)) {
			dto.setStatus(PayConstant.RESULT_SUCCESS);
		}else {
			dto.setStatus(PayConstant.STATUS_FAIL);
		}

		String message = "";
		if (PayLogModel.SCENES_LOANS.equals(payLog.getScenes())) {
			message = paymentNotifyAssist.doScenesLoans(dto,payLog);
		}else if (PayLogModel.SCENES_PROFIT.equals(payLog.getScenes())){
			message = paymentNotifyAssist.doScenesProfit(dto,payLog);
		}else if (PayLogModel.SCENES_REFUND.equals(payLog.getScenes())){
			message = paymentNotifyAssist.doScenesRefund(dto,payLog);
		}else {
			logger.error("没有合适的场景，异步通知处理失败" + orderNo);
		}

		if (StringUtil.equals(message, PayConstant.RESULT_SUCCESS)) {
			writeResult(response, "success");
		}
	}

	/**
	 * 快钱异步通知接口：
	 * 代付（支付）- 成功通知接口 - 异步通知处理
	 * @param httpRequest
	 * @param httpResponse
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/pay/kuaiqian/payforNotify.htm")
	public void payforNotify(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {

		KuaiqianPayHelper kuaiqianPayHelper = new KuaiqianPayHelper();
		long start = System.currentTimeMillis();
		logger.info("---------------------快钱实时付款 - 异步通知开始----------------------" );
		//获取客户端请求报文
		String requestXml = kuaiqianPayHelper.genRequestXml(httpRequest);
		NotifyRequest request = CCSUtil.converyToJavaBean(requestXml, NotifyRequest.class);
		String requestStr = kuaiqianPayHelper.unsealxml(request);//解密请求报文
		if (requestStr == null){
			logger.error("快钱异步请求参数异常  ==>"+requestXml);
			return;
		}
		//调用单笔快到银api2.0服务
		String responseXml = kuaiqianPayHelper.sealxml(request.getNotifyRequestBody().getSealDataType().getOriginalData());

		if (responseXml == null){
			logger.error("快钱异步通知响应参数异常  ==>"+request.getNotifyRequestBody().getSealDataType());
			return;
		}
		Pay2bankNotify pay2bankNotify = KuaiqianPayUtil.converyToJavaBean(requestStr, Pay2bankNotify.class);

		PayReqLog payReqLog = payReqLogService.findByOrderNo(pay2bankNotify.getMerchant_id());
		String params = JSON.toJSONString(pay2bankNotify);
		if (payReqLog != null) {
			// 保存respLog
			PayRespLog payRespLog = new PayRespLog(pay2bankNotify.getMerchant_id(),PayRespLogModel.RESP_LOG_TYPE_NOTIFY,params);
			payRespLogService.save(payRespLog);

			// 更新reqLog
			modifyPayReqLog(payReqLog,params);
		}

		PayLog payLog = payLogService.findByOrderNo(pay2bankNotify.getMerchant_id());

		if(null  == payLog ){
			logger.warn("未查询到对应的支付订单");
			return ;
		}

		RepaymentNotifyDto dto = new RepaymentNotifyDto();
		dto.setPayPlatNo(pay2bankNotify.getOrder_seq_id());
		if ("111".equals(pay2bankNotify.getStatus()) && KuaiqianPayConstant.RESPONSE_SUCCESS_CODE.equals(pay2bankNotify.getError_code())) {
			dto.setStatus(PayConstant.RESULT_SUCCESS);
		}else {
			dto.setStatus(PayConstant.STATUS_FAIL);
		}

		String message = "";
		if (PayLogModel.SCENES_LOANS.equals(payLog.getScenes())) {
			message = paymentNotifyAssist.doScenesLoans(dto,payLog);
		}else if (PayLogModel.SCENES_PROFIT.equals(payLog.getScenes())){
			message = paymentNotifyAssist.doScenesProfit(dto,payLog);
		}else if (PayLogModel.SCENES_REFUND.equals(payLog.getScenes())){
			message = paymentNotifyAssist.doScenesRefund(dto,payLog);
		}else {
			logger.error("没有合适的场景，异步通知处理失败" + pay2bankNotify.getMerchant_id());
		}

		//返回响应报文
		if (StringUtil.equals(message, PayConstant.RESULT_SUCCESS)) {
			httpResponse.setCharacterEncoding("utf-8");
			httpResponse.setContentType("utf-8");
			httpResponse.getWriter().write(responseXml);
			httpResponse.getWriter().flush();
		}
		long end = System.currentTimeMillis();
		logger.info("cost "+(end-start));
	}

	private void writeResult(HttpServletResponse response,String result)throws Exception {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf8");
		response.getOutputStream().write(result.getBytes("utf8"));
	}

	private void modifyPayReqLog (PayReqLog payReqLog,String params){
		payReqLog.setNotifyParams(params);
		payReqLog.setNotifyTime(DateUtil.getNow());
		payReqLogService.updateById(payReqLog);
	}
}