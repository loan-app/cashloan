package com.rongdu.cashloan.cl.model.pay.lianlian.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import tool.util.BeanUtil;
import tool.util.DateUtil;
import tool.util.IPUtil;
import tool.util.StringUtil;

import com.alibaba.fastjson.JSONObject;
import com.rongdu.cashloan.cl.domain.PayReqLog;
import com.rongdu.cashloan.cl.model.pay.lianlian.AuthApplyModel;
import com.rongdu.cashloan.cl.model.pay.lianlian.BasePaymentModel;
import com.rongdu.cashloan.cl.model.pay.lianlian.CancelAuthSignModel;
import com.rongdu.cashloan.cl.model.pay.lianlian.CertifiedPayModel;
import com.rongdu.cashloan.cl.model.pay.lianlian.ConfirmPaymentModel;
import com.rongdu.cashloan.cl.model.pay.lianlian.PaymentModel;
import com.rongdu.cashloan.cl.model.pay.lianlian.QueryAuthSignModel;
import com.rongdu.cashloan.cl.model.pay.lianlian.QueryBankCardModel;
import com.rongdu.cashloan.cl.model.pay.lianlian.QueryPaymentModel;
import com.rongdu.cashloan.cl.model.pay.lianlian.QueryRepaymentModel;
import com.rongdu.cashloan.cl.model.pay.lianlian.RepaymentModel;
import com.rongdu.cashloan.cl.model.pay.lianlian.RepaymentPlanModel;
import com.rongdu.cashloan.cl.model.pay.lianlian.RiskItems;
import com.rongdu.cashloan.cl.model.pay.lianlian.constant.LianLianConstant;
import com.rongdu.cashloan.cl.service.PayReqLogService;
import com.rongdu.cashloan.core.common.context.Global;
import com.rongdu.cashloan.core.common.util.HttpsUtil;
import com.rongdu.cashloan.core.common.util.JsonUtil;
import com.rongdu.cashloan.core.common.util.ReflectUtil;
import com.rongdu.cashloan.core.domain.UserBaseInfo;

/**
 * 连连支付
 * 
 * @author gc
 * @version 1.0.0
 * @date 2017年3月6日 下午4:36:16
 * Copyright 杭州融都科技股份有限公司 All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */

public class LianLianHelper {

	public static final Logger logger = LoggerFactory.getLogger(LianLianHelper.class);
	
	private BasePaymentModel doSubmit(BasePaymentModel model) {
		// 保存请求记录
		saveReqLog(model);

		Map<String, String> map = ReflectUtil.fieldValueToMap(model,model.reqParamNames());
		
		String jsonStr = JSONObject.toJSONString(map);
		String resp = null;
		try {
			// 获取系统参数中连连支付启用状态
			String lianlianSwitch = Global.getValue("lianlian_switch");

			if (StringUtil.isNotBlank(lianlianSwitch) && "1".equals(lianlianSwitch)) {
				logger.debug("请求地址：" + model.getSubUrl());
				resp = HttpsUtil.postStrClient(model.getSubUrl(), jsonStr);
			} else {
				logger.debug("关闭连连支付,模拟返回结果");
				resp = "{\"ret_code\":\"0000\",\"ret_msg\":\"模拟交易成功\"}";
			}

			model.response(resp);
			 
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		// 更新请求记录
		modifyReqLog(model, resp);
		return model;
	}
	
	/**
	 * 连连支付 实时付款 - 付款交易
	 * 
	 * @param model
	 * @return
	 */
	public BasePaymentModel payment(PaymentModel model) {
		model.sign();
		doSubmit(model);
		return model;
	}

	/**
	 * 连连支付 实时付款 - 确认付款
	 * 
	 * @param model
	 * @return
	 */
	public BasePaymentModel confirmPayment(ConfirmPaymentModel model) {
		model.sign();
		doSubmit(model);
		return model;
	}

	/**
	 * 连连支付 实时付款 - 查询付款交易
	 * 
	 * @param model
	 * @return
	 */
	public BasePaymentModel queryPayment(QueryPaymentModel model) {
		model.sign();
		doSubmit(model);
		return model;
	}

	

	/**
	 * 连连支付 分期付 - 授权申请
	 * 
	 * @param paramMap
	 * @return
	 */
	public BasePaymentModel authApply(AuthApplyModel model) {
		model.sign();
		doSubmit(model);
		return model;
	}

	/**
	 * 查询签约结果
	 * @param model
	 * @return
	 */
	public BasePaymentModel queryAuthSign(QueryAuthSignModel model){
		model.sign();
		doSubmit(model);
		return model;
	}
	
	/**
	 * 取消签约授权
	 * @param model
	 * @return
	 */
	public BasePaymentModel cancelAuthSign(CancelAuthSignModel model){
		model.sign();
		doSubmit(model);
		return model;
	}
	

	/**
	 * 连连支付 分期付 - 还款计划变更
	 * 
	 * @param paramMap
	 * @return
	 */
	public BasePaymentModel repaymentPlanChange(RepaymentPlanModel model) {
		model.sign();
		doSubmit(model);
		return model;
	}

	/**
	 * 连连支付 分期付 - 银行还款扣款
	 * 
	 * @param paramMap
	 * @return
	 */
	public BasePaymentModel repayment(RepaymentModel model) {
		model.sign();
		doSubmit(model);
		return model;
	}

	/**
	 * 连连支付 分期付 - 银行还款扣款查询
	 * 
	 * @param paramMap
	 * @return
	 */
	public BasePaymentModel queryRepayment(QueryRepaymentModel model) {
		model.sign();
		doSubmit(model);
		return model;
	}
	
	
	/**
	 * 连连支付 - 卡Bin查询
	 * 
	 * @param model
	 * @return
	 */
	public BasePaymentModel queryBankCard(QueryBankCardModel model) {
		model.sign();
		doSubmit(model);
		return model;
	}
	
	/**
	 * 保存请求记录
	 * 
	 * @param model
	 */
	private void saveReqLog(BasePaymentModel model) {
		PayReqLogService payReqLogService = (PayReqLogService) BeanUtil.getBean("payReqLogService");
		PayReqLog payReqLog = new PayReqLog();
		payReqLog.setOrderNo(model.getOrderNo());
		payReqLog.setService(model.getService());
		payReqLog.setCreateTime(DateUtil.getNow());
		payReqLog.setParams(ReflectUtil.fieldValueToJson(model,model.signParamNames()));
		payReqLog.setReqDetailParams(ReflectUtil.fieldValueToJson(model,model.reqParamNames()));
		
		if (null != ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())){
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			String ip = IPUtil.getRemortIP(request);
			payReqLog.setIp(ip);
		}
		payReqLogService.save(payReqLog);
	}

	/**
	 * 更新返回数据
	 * 
	 * @param upsModel
	 * @param txBaseResponse
	 */
	private void modifyReqLog(BasePaymentModel model, String resp) {
		PayReqLogService payReqLogService = (PayReqLogService) BeanUtil.getBean("payReqLogService");
		PayReqLog reqLog = payReqLogService.findByOrderNo(model.getOrderNo());
		if (null == reqLog) {
			return;
		}
		reqLog.setReturnParams(resp);
		reqLog.setReturnTime(DateUtil.getNow());
		payReqLogService.updateById(reqLog);
	}
	
	
	
	/**
	 * 连连支付 - 认证支付
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String certifiedPay(String uuid, String registerTime, UserBaseInfo userBaseInfo,
			CertifiedPayModel certifiedPay, double amount) {
		Date payReqTime = DateUtil.getNow();
		certifiedPay.setBusi_partner(LianLianConstant.GOODS_VIRTUAL);
		certifiedPay.setDt_order(DateUtil.dateStr3(payReqTime));
		certifiedPay.setName_goods("还款"+certifiedPay.getOrderNo());
		certifiedPay.setInfo_order("");

		if ("dev".equals(Global.getValue("app_environment"))) {
			certifiedPay.setMoney_order("0.01");
		} else {
			certifiedPay.setMoney_order(String.valueOf(amount));
		}

		RiskItems riskItems = new RiskItems();
		riskItems.setFrms_ware_category("2010");
		riskItems.setUser_info_mercht_userno(uuid);
		riskItems.setUser_info_bind_phone(userBaseInfo.getPhone());
		riskItems.setUser_info_dt_register(registerTime);
		riskItems.setUser_info_full_name(userBaseInfo.getRealName());
		riskItems.setUser_info_id_no(userBaseInfo.getIdNo());
		riskItems.setUser_info_identify_type("1");
		riskItems.setUser_info_identify_state("1");
		String riskItem = JSONObject.toJSONString(riskItems);

		certifiedPay.setRisk_item(riskItem);
		certifiedPay.setUser_id(uuid);
		certifiedPay.setId_type("0"); // 0 表示身份证
		certifiedPay.setId_no(userBaseInfo.getIdNo());
		certifiedPay.setAcct_name(userBaseInfo.getRealName());
		certifiedPay.sign();

		Map<String, String> map = ReflectUtil.fieldValueToMap(certifiedPay, certifiedPay.reqParamNames());
		String certifiedPayData = JsonUtil.toString(map);
		return certifiedPayData;
	}
}

