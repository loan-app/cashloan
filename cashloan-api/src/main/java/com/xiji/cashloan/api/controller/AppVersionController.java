package com.xiji.cashloan.api.controller;

import com.xiji.cashloan.cl.domain.Channel;
import com.xiji.cashloan.cl.domain.ChannelApp;
import com.xiji.cashloan.cl.service.ChannelAppService;
import com.xiji.cashloan.cl.service.ChannelService;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.ServletUtils;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.common.web.controller.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * app版本控制
 * @author wnb
 * @version 1.0
 * @date 2018年12月03日
 *
 *
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

	/**
	 *
	 * 版本比较
	 * @param version
	 */
	@RequestMapping(value = "/api/app/confirmVersion.htm", method = RequestMethod.GET)
	public void confirmVersion(String version){
		String lastVersion = Global.getValue("last_version");
		String mandatoryUpdateVersion = Global.getValue("mandatory_update_version");
		Map<String,Object> data = new HashMap<>();
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int lastInt = StringUtil.compareVersion(version,lastVersion);
			String lastResult = lastInt == 0 ? "true":"false";
			int updateInt = StringUtil.compareVersion(version,mandatoryUpdateVersion);
			String updateResult = updateInt <= 0 ? "true":"false";
			data.put("lastResult",lastResult);
			data.put("updateResult",updateResult);
			data.put("downloadUrl",Global.getValue("last_version_download_url"));
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "对比成功");
		} catch (Exception e) {
			data.put("lastResult","");
			data.put("updateResult","");
			data.put("downloadUrl","");
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "对比失败");
		}
		result.put(Constant.RESPONSE_DATA, data);
		ServletUtils.writeToResponse(response, result);
	}
}
