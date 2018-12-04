package com.xiji.cashloan.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiji.cashloan.cl.domain.Channel;
import com.xiji.cashloan.cl.domain.ChannelApp;
import com.xiji.cashloan.cl.service.ChannelAppService;
import com.xiji.cashloan.cl.service.ChannelService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.web.controller.BaseController;

/**
 * app版本控制
 * @author wnb
 * @version 1.0
 * @date 2018年12月03日
 * 官方网站：www.xiji.com
 * 创新一部：rdc@xiji.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Scope("prototype")
@Controller
public class AppVersionController extends BaseController {
	
	@Resource
	private ChannelAppService channelAppService;

	@Resource
	private ChannelService channelService;
	
	@RequestMapping(value = "/api/app/checkVersion.htm", method = RequestMethod.POST)
	public void listVersion() {
		List<Channel> listChannel = channelService.listChannel();
		List<ChannelApp> listChannelApp = channelAppService.listChannelApp();
		Map<String, Object> data = new HashMap<String, Object>();
		for (Channel channel : listChannel) {
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			for (ChannelApp channelApp : listChannelApp) {
				if (channel.getId().equals(channelApp.getChannelId()) && channel.getState().equals("10")
						&& channelApp.getState().equals("10")) { //渠道和app信息都是启用状态
					Map<String,Object> map = new HashMap<String,Object>();
					ChannelApp appVersion =  new ChannelApp();
					if (channelApp.getAppType().equals("10")) {
						appVersion.setAppType(channelApp.getAppType());
						appVersion.setlatestVersion(channelApp.getlatestVersion());
						appVersion.setMinVersion(channelApp.getMinVersion());
						appVersion.setDownloadUrl(channelApp.getDownloadUrl());
						map.put("android", appVersion);
					} else if (channelApp.getAppType().equals("20")) {
						appVersion.setAppType(channelApp.getAppType());
						appVersion.setlatestVersion(channelApp.getlatestVersion());
						appVersion.setMinVersion(channelApp.getMinVersion());
						appVersion.setDownloadUrl(channelApp.getDownloadUrl());
						map.put("ios", appVersion);
					}
					list.add(map);
				}
			}
			data.put(channel.getCode(), list);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response, result);
	}
	
}
