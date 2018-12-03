package com.rongdu.cashloan.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rongdu.cashloan.cl.service.ClSmsService;
import com.rongdu.cashloan.core.common.context.Constant;
import com.rongdu.cashloan.core.common.context.Global;
import com.rongdu.cashloan.core.common.util.ServletUtils;
import com.rongdu.cashloan.core.common.util.StringUtil;
import com.rongdu.cashloan.core.common.web.controller.BaseController;
import com.rongdu.cashloan.core.service.CloanUserService;

 /**
 * 短信记录Controller
 * @author lyang
 * @version 1.0.0
 * @date 2017-03-09 14:48:24
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Scope("prototype")
@Controller
public class SmsController extends BaseController {
	
	public static final Logger logger = LoggerFactory.getLogger(SmsController.class);

	@Resource
	private ClSmsService clSmsService;
	@Resource
	private CloanUserService cloanUserService;
	
	/**
	 * 探测短信验证码是否可获取
	 * @param phone
	 * @param type
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/user/probeSms.htm")
	public void probeSms(@RequestParam(value="phone") String phone, @RequestParam(value="type") String type) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> data = new HashMap<String,Object>();
		if(StringUtil.isBlank(phone) || StringUtil.isBlank(type)){
			data.put("message", "参数不能为空");
			result.put(Constant.RESPONSE_DATA, data);
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "探测失败");
		} else if(!StringUtil.isPhone(phone)){
			data.put("message", "手机号码格式有误");
			result.put(Constant.RESPONSE_DATA, data);
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "探测失败");
		} else {
			long countDown = clSmsService.findTimeDifference(phone, type);
			data.put("countDown", countDown);
			if (countDown == 0) {
				data.put("state", 10);
			} else {
				data.put("state", 20);
			}
			result.put(Constant.RESPONSE_DATA, data);
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "探测成功");
		}
		ServletUtils.writeToResponse(response,result);
	}
	 
	/**
	 * 获取短信验证码
	 * @param phone
	 * @param type
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/user/sendSms.htm")
	public void sendSms(@RequestParam(value="phone") String phone, 
			@RequestParam(value="type") String type) throws Exception {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Map<String,Object> data = new HashMap<String,Object>();
		String message = this.check(phone, type);
		if (StringUtil.isBlank(message)) {
			long countDown = clSmsService.findTimeDifference(phone, type);
			if (countDown != 0) {
				data.put("countDown", countDown);
				data.put("state", "20");
				message = "获取短信验证码过于频繁，请稍后再试";
				logger.info("发送短信，phone：" + phone + "， type：" + type + "，发送前的校验结果message：" + message);
			} else {
				logger.info("发送短信，phone：" + phone + "， type：" + type + "，准备发送");
				String orderNo = clSmsService.sendSms(phone, type);
				if (StringUtil.isNotBlank(orderNo)) {
					clSmsService.getReportByOrderNo(orderNo, phone, type);
					
					data.put("state", "10");
					resultMap.put(Constant.RESPONSE_DATA, data);
					resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
					resultMap.put(Constant.RESPONSE_CODE_MSG, "已发送，请注意查收");
				} else {
					resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
					resultMap.put(Constant.RESPONSE_CODE_MSG, "发送失败");
				}
			}
		}
		if (StringUtil.isNotBlank(message)) {
			logger.info("发送短信，phone：" + phone + "， type：" + type + "，发送前的校验结果message：" + message);
			data.put("state", "20");
			data.put("message", message);
			resultMap.put(Constant.RESPONSE_DATA, data);
			resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			resultMap.put(Constant.RESPONSE_CODE_MSG, "发送失败");
		}
		
		
		ServletUtils.writeToResponse(response,resultMap);
	}
	
	/**
	 * 短信验证
	 * @param phone
	 * @param code
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/user/verifySms.htm")
	public void verifyMsg(
			@RequestParam(value="phone") String phone,
			@RequestParam(value="type") String type,
			@RequestParam(value="vcode") String code
			) throws Exception {
		int msg = clSmsService.verifySms(phone, type, code);
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> data = new HashMap<String,Object>();
		if (msg == 1) {
			data.put("state", "10");
			result.put(Constant.RESPONSE_DATA, data);
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "验证成功");
		} else if(msg == -1){
			data.put("message", "验证码已过期");
			data.put("state", "20");
			result.put(Constant.RESPONSE_DATA, data);
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "验证失败");
		} else {
			data.put("message", "手机号码或验证码错误");
			data.put("state", "20");
			result.put(Constant.RESPONSE_DATA, data);
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "验证失败");
		}
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 网页注册发送短信(含验证图片验证码)
	 */
	@RequestMapping(value = "/api/user/h5SendSms.htm")
	public void h5SendSms(){
		String code = request.getParameter("code");
		String phone = request.getParameter("phone");
		String type = request.getParameter("type");
		long countDown = 0;
		HttpSession session = request.getSession();  
		String sessionCode = (String) session.getAttribute("code"); 
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String result = null;
		if (StringUtil.isNotBlank(code) && code.length() == 4 && code.equals(sessionCode)) {
			session.removeAttribute("code");
			
			result = this.check(phone, type);
			
			if (result == null) {
				if (type.equals("register")) {
					countDown = clSmsService.findTimeDifference(phone, type);
					if (countDown != 0) {
						result = "获取短信验证码过于频繁，请稍后再试";
					} else {
						String orderNo = clSmsService.sendSms(phone, type);
						if (StringUtil.isNotBlank(orderNo)) {
							clSmsService.getReportByOrderNo(orderNo, phone, type);
							resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
							resultMap.put(Constant.RESPONSE_CODE_MSG, "已发送，请注意查收");
							resultMap.put("countDown", countDown);
							ServletUtils.writeToResponse(response,resultMap);
							return;
						} else {
							result = "发送失败";
							/*resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
							resultMap.put(Constant.RESPONSE_CODE_MSG, "发送失败");*/
						}
					}
				} else {
					result = "短信类型错误";
				}
			}
	        resultMap.put("countDown", countDown);
	        resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			resultMap.put(Constant.RESPONSE_CODE_MSG, result);
	        
			/*if (result.equals("10")) {
				resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				resultMap.put(Constant.RESPONSE_CODE_MSG, "短信发送成功");
			} else {
				resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				resultMap.put(Constant.RESPONSE_CODE_MSG, result);
			}*/
		} else {
			resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			resultMap.put(Constant.RESPONSE_CODE_MSG, "图片验证码错误");
		}
		
		ServletUtils.writeToResponse(response,resultMap);
	}
	
	private String check(String phone,String type){
		String message = null;
		if(StringUtil.isBlank(phone) || StringUtil.isBlank(type)){
			message = "参数不能为空";
		} else if(!StringUtil.isPhone(phone)){
			message = "手机号码格式有误";
		} else {
			if(StringUtil.equals("register", type)){
				// 当日最大注册用户数
				long todayCount = cloanUserService.todayCount();
	            String dayRegisterMax_ = Global.getValue("day_register_max");
	            if(StringUtil.isNotBlank(dayRegisterMax_)){
	            	int dayRegisterMax = Integer.parseInt(dayRegisterMax_);
	            	if(dayRegisterMax > 0 && todayCount >= dayRegisterMax){
						message = "今天注册人数已达到上限";
	            	}
	            }
	            
				if (clSmsService.findUser(phone)>0) {
					message = "该手机号已经注册";
				}
			}
			
			if (StringUtil.equals("findReg", type)) {
				if (clSmsService.findUser(phone)<1) {
					message = "该手机号不存在";
				}
			}
			
			if (message==null&&clSmsService.countDayTime(phone, type) <= 0) {
				message = "获取短信验证码过于频繁，请明日再试";
			}
		}	
		return message;
	}	
}
