package com.xiji.cashloan.cl.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.xiji.cashloan.cl.service.UserSdkLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.cl.domain.UserSdkLog;
import com.xiji.cashloan.cl.mapper.UserSdkLogMapper;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;

/**
 * sdk识别记录表ServiceImpl
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 创新一部：rdc@xiji.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("userSdkLogService")
public class UserSdkLogServiceImpl extends BaseServiceImpl<UserSdkLog, Long> implements UserSdkLogService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(UserSdkLogServiceImpl.class);
   
    @Resource
    private UserSdkLogMapper userSdkLogMapper;

	@Override
	public BaseMapper<UserSdkLog, Long> getMapper() {
		return userSdkLogMapper;
	}

	@Override
	public Map<String, Object> countDayTime(Map<String, Object> searchMap) {
		String sdk = Global.getValue("sdk_time");
		int faceTimes = JSONObject.parseObject(sdk).getIntValue("faceTime");
		int ocrTimes = JSONObject.parseObject(sdk).getIntValue("ocrTime");
		searchMap.put("timeType", "10");
		int faceTime = userSdkLogMapper.countDayTime(searchMap);
		searchMap.put("timeType", "20");
		int ocrTime = userSdkLogMapper.countDayTime(searchMap);
		Map<String, Object> times = new HashMap<String, Object>();
		times.put("faceTime", faceTimes-faceTime<0?0:faceTimes-faceTime);
		times.put("ocrTime", ocrTimes-ocrTime<0?0:ocrTimes-ocrTime);
		return times;
	}

	@Override
	public int save(UserSdkLog usl) {
		return userSdkLogMapper.save(usl);
	}
	
}