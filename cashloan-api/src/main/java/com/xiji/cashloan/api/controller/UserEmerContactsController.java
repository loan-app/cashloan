package com.xiji.cashloan.api.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tool.util.DateUtil;

import com.xiji.cashloan.cl.domain.UserEmerContacts;
import com.xiji.cashloan.cl.domain.UserEquipmentInfo;
import com.xiji.cashloan.cl.model.UserAuthModel;
import com.xiji.cashloan.cl.service.UserAuthService;
import com.xiji.cashloan.cl.service.UserEmerContactsService;
import com.xiji.cashloan.cl.service.UserEquipmentInfoService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.service.UserBaseInfoService;


 /**
 * 紧急联系人Controller
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-02-14 11:24:05
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Scope("prototype")
@Controller
public class UserEmerContactsController extends BaseController {

	public static final Logger logger = LoggerFactory.getLogger(UserEmerContactsController.class);
	
	@Resource
	private UserBaseInfoService userBaseInfoService;
	@Resource
	private UserEmerContactsService userEmerContactsService;
	@Resource
	private UserAuthService userAuthService;
	@Resource
	private UserEquipmentInfoService userEquipmentInfoService;

	
	/**
	 * 保存联系人以及设备信息
	 * @param name
	 * @param phone
	 * @param relation
	 * @param type
	 * @param userId
	 * @param operatingSystem
	 * @param systemVersions
	 * @param phoneType
	 * @param phoneBrand
	 * @param phoneMark
	 * @param versionName
	 * @param versionCode
	 * @param mac
	 */
	@RequestMapping(value = "/api/act/mine/contact/saveOrUpdate.htm", method = RequestMethod.POST)
	public void saveOrUpdate(
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "phone", required = true) String phone,
			@RequestParam(value = "relation", required = true) String relation,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "operatingSystem", required = false) String operatingSystem,
			@RequestParam(value = "systemVersions", required = false) String systemVersions,
			@RequestParam(value = "phoneType", required = false) String phoneType,
			@RequestParam(value = "phoneBrand", required = false) String phoneBrand,
			@RequestParam(value = "phoneMark", required = false) String phoneMark,
			@RequestParam(value = "versionName", required = false) String versionName,
			@RequestParam(value = "versionCode", required = false) String versionCode,
			@RequestParam(value = "mac", required = false) String mac,
			@RequestParam(value = "appInstallTime", required = false) Date appInstallTime,
			@RequestParam(value = "appMarket", required = false) String appMarket
			){
		long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		UserBaseInfo userBaseInfo = userBaseInfoService.findByUserId(userId);
		Map<String, Object> result = new HashMap<String,Object>();
		result.put("userId", userId);
		List<UserEmerContacts> contacts = userEmerContactsService.getUserEmerContactsByUserId(result);
		result.clear();
		String[] names = name.split(",");
		String[] phones = phone.split(",");
		
		//判断手机号
		int phoneCount = 0;
		for (int i = 0; i < phones.length; i++) {
			phones[i] = phones[i].replaceAll("\\+86", "").replaceAll("[^0-9]", "");
			if(!StringUtil.isPhone(phones[i])){
				result.put(Constant.RESPONSE_CODE,Constant.FAIL_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "请选取正确的手机号码");
				ServletUtils.writeToResponse(response,result);
				return;
			}
			if(userBaseInfo.getPhone().equals(phones[i])){
				result.put(Constant.RESPONSE_CODE,Constant.FAIL_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "紧急联系人号码不能和自己的号码一致");
				ServletUtils.writeToResponse(response,result);
				return;
			}
			
			phoneCount++;
		}
		if (phoneCount >= 2) {
			if (phones[0].equals(phones[1])) {
				result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "请勿选择两个一样的手机号码");
				ServletUtils.writeToResponse(response, result);
				return;
			}
		} else {
			result.put(Constant.RESPONSE_CODE,Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "手机号码格式有误");
			ServletUtils.writeToResponse(response,result);
			return;
		}
		//判断手机号结束
		
		String[] relations = relation.split(",");
		String[] types = type.split(",");
		int count=0;
		if(contacts.size()==0){
			//新增
			for (int i = 0; i < names.length; i++) {
				UserEmerContacts info = new UserEmerContacts();
				info.setName(names[i]);
				info.setPhone(phones[i]);
				info.setRelation(relations[i]);
				info.setType(types[i]);
				info.setUserId(userId);
				count = userEmerContactsService.insert(info);
			}
			result.put("contactState", UserAuthModel.STATE_VERIFIED);
			result.put("contactTime", DateUtil.getNow());
			result.put("userId", userId);
			userAuthService.updateByUserId(result);
		} else {
			//更新
			for (int i = 0; i < names.length; i++) {
				if(contacts.get(i).getType().equals(types[i])){
					UserEmerContacts info=contacts.get(i);
					info.setName(names[i]);
					info.setPhone(phones[i]);
					info.setRelation(relations[i]);
					info.setType(types[i]);
					count=userEmerContactsService.updateById(info);
				} else {
					int j = i == 0 ? 1 : 0;
					UserEmerContacts info=contacts.get(i);
					info.setName(names[j]);
					info.setPhone(phones[j]);
					info.setRelation(relations[j]);
					info.setType(types[j]);
					count=userEmerContactsService.updateById(info);
				}
			}
			if(count>0){
				result.put("contactState", "30");
				result.put("userId", userId);
				userAuthService.updateByUserId(result);
			}
		}
		UserEquipmentInfo uei = new UserEquipmentInfo();
		uei.setAppInstallTime(appInstallTime);
		uei.setAppMarket(appMarket);
		uei.setUserId(userId);
		uei.setBlackBox("");
		uei.setOperatingSystem(operatingSystem);
		uei.setSystemVersions(systemVersions);
		uei.setPhoneType(phoneType);
		uei.setPhoneBrand(phoneBrand);
		uei.setPhoneMark(phoneMark);
		uei.setVersionName(versionName);
		uei.setVersionCode(versionCode);
		uei.setMac(mac);
		count = userEquipmentInfoService.saveOrUpdate(uei);
		result.put(Constant.RESPONSE_CODE,
				count > 0 ? Constant.SUCCEED_CODE_VALUE : Constant.FAIL_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, count > 0 ? "保存成功" : "保存失败");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * @description  获取单个用户的紧急联系人
	 * @param userId 用户的id
	 * @author chenxy
	 * @return void
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/api/act/mine/contact/getContactInfoList.htm", method = RequestMethod.GET)
	public void getContactInfoList(){
		long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("userId", userId);
		List<UserEmerContacts> userEmerContacts = userEmerContactsService.getUserEmerContactsByUserId(paramMap);
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> temp = new HashMap<String,Object>();
		temp.put("list", userEmerContacts);
		result.put(Constant.RESPONSE_CODE,Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_DATA, temp);
		result.put(Constant.RESPONSE_CODE_MSG,"获取成功");
		ServletUtils.writeToResponse(response,result);
	}
}
