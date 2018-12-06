package com.xiji.cashloan.cl.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.xiji.cashloan.cl.domain.UserEquipmentInfo;
import com.xiji.cashloan.cl.service.UserEquipmentInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tool.util.DateUtil;

import com.xiji.cashloan.cl.mapper.UserEquipmentInfoMapper;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.core.domain.User;
import com.xiji.cashloan.core.mapper.UserMapper;

/**
 * 用户设备信息表ServiceImpl
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("userEquipmentInfoService")
public class UserEquipmentInfoServiceImpl extends BaseServiceImpl<UserEquipmentInfo, Long> implements UserEquipmentInfoService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(UserEquipmentInfoServiceImpl.class);
   
    @Resource
    private UserEquipmentInfoMapper userEquipmentInfoMapper;
    @Resource
	private UserMapper userMapper;




	@Override
	public BaseMapper<UserEquipmentInfo, Long> getMapper() {
		return userEquipmentInfoMapper;
	}




	@Override
	public int saveOrUpdate(UserEquipmentInfo uei) {
		Map<String,Object> map = new HashMap<>();
		map.put("userId", uei.getUserId());
		UserEquipmentInfo uinfo = userEquipmentInfoMapper.findSelective(map);
		if (uinfo==null) {
			return userEquipmentInfoMapper.save(uei);
		}
		map.put("id", uinfo.getId());
		map.put("operatingSystem", uei.getOperatingSystem());
		map.put("systemVersions", uei.getSystemVersions());
		map.put("phoneType", uei.getPhoneType());
		map.put("phoneBrand", uei.getPhoneBrand());
		map.put("phoneMark", uei.getPhoneMark());
		map.put("versionName", uei.getVersionName());
		map.put("versionCode", uei.getVersionCode());
		map.put("mac", uei.getMac());
		map.put("appInstallTime", uei.getAppInstallTime());
		map.put("appMarket", uei.getAppMarket());
		return userEquipmentInfoMapper.updateSelective(map);
	}




	@Override
	public UserEquipmentInfo findSelective(long userId) {
		Map<String,Object> map = new HashMap<>();
		map.put("userId", userId);
		return userEquipmentInfoMapper.findSelective(map);
	}




	@Override
	public void save(String loginName, String blackBox) {
		if (blackBox==null) {
			return;
		}
		Map<String,Object> map = new HashMap<>();
		map.put("loginName", loginName);
		User user = userMapper.findSelective(map);
		map = new HashMap<>();
		map.put("userId", user.getId());
		UserEquipmentInfo uinfo = userEquipmentInfoMapper.findSelective(map);
		if (user!=null) {
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("id", user.getId());
			paramMap.put("loginTime", DateUtil.getNow());
			userMapper.updateSelective(paramMap);
			if (uinfo==null) {
				UserEquipmentInfo uei = new UserEquipmentInfo();
				uei.setUserId(user.getId());
				uei.setBlackBox(blackBox);
				userEquipmentInfoMapper.save(uei);
			}else {
				map.put("blackBox", blackBox);
				map.put("id", uinfo.getId());
				userEquipmentInfoMapper.updateSelective(map);
			}
		}
	}
	
}