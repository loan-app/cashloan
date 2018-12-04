package com.xiji.cashloan.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.JsonUtil;
import com.xiji.cashloan.core.common.util.RdPage;
import com.xiji.cashloan.core.common.util.ServletUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.Channel;
import com.xiji.cashloan.cl.model.ChannelCountModel;
import com.xiji.cashloan.cl.model.ChannelModel;
import com.xiji.cashloan.cl.service.ChannelService;

/**
 * 渠道信息Controller
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/27
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Scope("prototype")
@Controller
@SuppressWarnings("unchecked")
public class ChannelController extends ManageBaseController {

	@Resource
	private ChannelService channelService;

	/**
	 * 保存
	 * @param code
	 * @param name
	 * @param linker
	 * @param phone
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/promotion/channel/save.htm", method = RequestMethod.POST)
	public void save(@RequestParam(value="code") String code,
			@RequestParam(value="name") String name,
			@RequestParam(value="linker") String linker,
			@RequestParam(value="phone") String phone) throws Exception {
		Channel channel=new Channel();
		channel.setCode(code);
		channel.setLinker(linker);
		channel.setName(name);
		channel.setPhone(phone);
		boolean flag = channelService.save(channel);

		Map<String, Object> result = new HashMap<String, Object>();
		if (flag) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
		}
		ServletUtils.writeToResponse(response, result);
	}

	/**
	 * 查询所有渠道信息
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/promotion/channel/listChannel.htm")
	public void listChannel() throws Exception {
		List<Channel> list = channelService.listChannel();
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, list);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response, result);
	}
	
	
	/**
	 * 渠道信息列表页查看
	 * 
	 * @param searchParams
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/promotion/channel/page.htm", method = {RequestMethod.POST,RequestMethod.GET})
	public void page(
			@RequestParam(value="searchParams",required=false) String searchParams,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String, Object> searchMap = new HashMap<>();
		if (!StringUtils.isEmpty(searchParams)) {
			searchMap = JsonUtil.parse(searchParams, Map.class);
		}

		Page<ChannelModel> page = channelService.page(current, pageSize,searchMap);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response, result);
	}
	
	/**
	 * 修改
	 * @param id
	 * @param code
	 * @param name
	 * @param linker
	 * @param phone
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/promotion/channel/update.htm", method = RequestMethod.POST)
	public void update(
			@RequestParam(value="id") Long id,
			@RequestParam(value="code") String code,
			@RequestParam(value="name") String name,
			@RequestParam(value="linker") String linker,
			@RequestParam(value="phone") String phone) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("code", code);
		paramMap.put("name", name);
		paramMap.put("linker", linker);
		paramMap.put("phone", phone);
		boolean flag = channelService.update(paramMap);
		Map<String, Object> result = new HashMap<String, Object>();
		if (flag) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
		}
		ServletUtils.writeToResponse(response, result);
	}

	
	/**
	 * 渠道信息修改状态
	 */
	@RequestMapping(value = "/modules/manage/promotion/channel/updateState.htm", method = RequestMethod.POST)
	public void updateState(@RequestParam(value="id") Long id,
					@RequestParam(value="state") String state) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("state", state);
		boolean flag = channelService.update(paramMap);
		Map<String, Object> result = new HashMap<String, Object>();
		if (flag) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
		}
		ServletUtils.writeToResponse(response, result);
	}
	
	/**
	 * 统计渠道用户信息
	 * 
	 * @param searchParams
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */ 
	@RequestMapping(value = "/modules/manage/promotion/channel/channelUserList.htm", method = {RequestMethod.POST,RequestMethod.GET})
	public void channelUserList(
			@RequestParam(value="searchParams",required=false) String searchParams,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String, Object> searchMap = new HashMap<>();
		if (!StringUtils.isEmpty(searchParams)) {
			searchMap = JsonUtil.parse(searchParams, Map.class);
		} 
		Page<ChannelCountModel> page = channelService.channelUserList(current, pageSize,searchMap);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response, result);
	}
	
	/**
	 * 统计渠道用户信息
	 * chenxy
	 * 2017年7月19日 20:49:47
	 */
	@RequestMapping(value = "/modules/manage/promotion/channel/channelUserCount.htm", method = {RequestMethod.POST,RequestMethod.GET})
	public void channelUserCount(
			@RequestParam(value="searchParams",required=false) String searchParams,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String, Object> searchMap = new HashMap<>();
		if (!StringUtils.isEmpty(searchParams)) {
			searchMap = JsonUtil.parse(searchParams, Map.class);
		}
		Page<Map<String,Object>> page = (Page<Map<String, Object>>) channelService.channelUserCount(searchMap,current,pageSize);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response, result);
	}
	
	@RequestMapping(value = "/modules/manage/promotion/channel/channelCount.htm", method = {RequestMethod.POST,RequestMethod.GET})
	public void channelCount(
			@RequestParam(value="searchParams",required=false) String searchParams) throws Exception {
		Map<String, Object> searchMap = new HashMap<>();
		if (!StringUtils.isEmpty(searchParams)) {
			searchMap = JsonUtil.parse(searchParams, Map.class);
		}
		List<Map<String,Object>> list = channelService.conversion(searchMap);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, list);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response, result);
	}
	
}
