package com.rongdu.cashloan.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tool.util.DateUtil;

import com.rongdu.cashloan.cl.model.UserAuthModel;
import com.rongdu.cashloan.cl.service.UserAuthService;
import com.rongdu.cashloan.core.common.context.Constant;
import com.rongdu.cashloan.core.common.util.ServletUtils;
import com.rongdu.cashloan.core.common.util.StringUtil;
import com.rongdu.cashloan.core.common.web.controller.BaseController;
import com.rongdu.cashloan.core.domain.UserOtherInfo;
import com.rongdu.cashloan.core.service.UserOtherInfoService;

 /**
 * 用户更多信息Controller
 * 
 * @author gc
 * @version 1.0.0
 * @date 2017-03-14 19:37:25
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Scope("prototype")
@Controller
public class UserOtherInfoController extends BaseController {

	@Resource
	private UserOtherInfoService userOtherInfoService;
	
	@Resource
	private UserAuthService userAuthService;
	/**
	 * 保存或修改用户其他信息
	 * @param userId
	 * @param taobao
	 * @param email
	 * @param qq
	 * @param wechat
	 */
	@RequestMapping(value = "/api/act/mine/other/saveOrUpdate.htm", method = RequestMethod.POST)
	public void saveOrUpdate(
			@RequestParam(value = "userId", required = true) Long userId,
			@RequestParam(value = "taobao", required = false) String taobao,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "qq", required = false) String qq,
			@RequestParam(value = "wechat", required = false) String wechat) {
		
		if(StringUtil.isBlank(taobao) && StringUtil.isBlank(email) && StringUtil.isBlank(qq) && StringUtil.isBlank(wechat)){
			Map<String, Object> result = new HashMap<String, Object>();
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "请填写信息");
			ServletUtils.writeToResponse(response, result);
			return ;
		}
		
		if(!StringUtil.isMail(email)){
			Map<String, Object> result = new HashMap<String, Object>();
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "邮箱格式错误");
			ServletUtils.writeToResponse(response, result);
			return ;
		}
		
		UserOtherInfo info = userOtherInfoService.getInfoByUserId(userId);
		boolean flag;
		if (null != info) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", info.getId());
			if(StringUtil.isNotBlank(taobao)){
				paramMap.put("taobao", taobao);
			}
			if(StringUtil.isNotBlank(email)){
				paramMap.put("email", email);
			}
			if(StringUtil.isNotBlank(qq)){
				paramMap.put("qq", qq);
			}
			if(StringUtil.isNotBlank(wechat)){
				paramMap.put("wechat", wechat);
			}
			flag = userOtherInfoService.updateSelective(paramMap);
		} else {
			UserOtherInfo otherInfo = new UserOtherInfo();
			otherInfo.setUserId(userId);
			otherInfo.setTaobao(taobao);
			otherInfo.setEmail(email);
			otherInfo.setQq(qq);
			otherInfo.setWechat(wechat);
			flag = userOtherInfoService.save(otherInfo);
		}

		if(flag){
			Map<String,Object> authMap=new HashMap<String,Object>();
			authMap.put("userId",userId);
			authMap.put("otherInfoState",UserAuthModel.STATE_VERIFIED);
			authMap.put("otherInfoTime",DateUtil.getNow());
			userAuthService.updateByUserId(authMap);
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		if (flag) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "保存成功");
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "保存失败");
		}

		ServletUtils.writeToResponse(response, result);
	}

	/**
	 * 保存或修改用户其他信息
	 * 
	 * @param userId
	 * @param taobao
	 * @param email
	 * @param qq
	 * @param wechat
	 */
	@RequestMapping(value = "/api/act/mine/other/findDetail.htm", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public void findDetail(
			@RequestParam(value = "userId", required = true) Long userId) {
		UserOtherInfo info = userOtherInfoService.getInfoByUserId(userId);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, info);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response, result);
	}

}
