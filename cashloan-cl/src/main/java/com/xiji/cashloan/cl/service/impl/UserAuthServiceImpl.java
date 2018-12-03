package com.xiji.cashloan.cl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiji.cashloan.cl.model.moxie.MxCreditRequest;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.mapper.UserBaseInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.domain.OperatorReqLog;
import com.xiji.cashloan.cl.domain.OperatorRespDetail;
import com.xiji.cashloan.cl.domain.UserAuth;
import com.xiji.cashloan.cl.mapper.UserAuthMapper;
import com.xiji.cashloan.cl.model.UserAuthModel;
import com.xiji.cashloan.cl.service.OperatorReqLogService;
import com.xiji.cashloan.cl.service.OperatorRespDetailService;
import com.xiji.cashloan.cl.service.OperatorService;
import com.xiji.cashloan.cl.service.UserAuthService;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.core.common.util.DateUtil;

/**
 * 用户认证信息表ServiceImpl
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-02-14 11:18:17
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("userAuthService")
public class UserAuthServiceImpl extends BaseServiceImpl<UserAuth, Long> implements UserAuthService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserAuthServiceImpl.class);
   
    @Resource
    private UserAuthMapper userAuthMapper;
    @Resource
	private OperatorReqLogService operatorReqLogService;
    @Resource
    private OperatorRespDetailService operatorRespDetailService;
    @Resource
    private OperatorService operatorService;
	@Resource
	private UserBaseInfoMapper userBaseInfoMapper;

	@Override
	public BaseMapper<UserAuth, Long> getMapper() {
		return userAuthMapper;
	}

	@Override
	public UserAuth getUserAuth(Map<String, Object> paramMap) {
		UserAuth userAuth = userAuthMapper.findSelective(paramMap);
		String phoneState = userAuth.getPhoneState();
		OperatorReqLog operatorReqLog = operatorReqLogService.findLastRecord(paramMap);
		
		if (UserAuthModel.STATE_ERTIFICATION.equals(userAuth.getPhoneState()) &&
				null != operatorReqLog) {
			int resetTime = 10;
			int diffTime = DateUtil.minuteBetween(operatorReqLog.getCreateTime(),DateUtil.getNow());
			//判断是否超过10分钟。
			if (resetTime <= diffTime) {
				Map<String,Object> params = new HashMap<String, Object>();
				params.put("userId", userAuth.getUserId());
				UserBaseInfo baseInfo = userBaseInfoMapper.findSelective(params);

				String host = Global.getValue("mx_operator_mxdata");
				final String token = Global.getValue("mx_token");
				Map<String, String> headMap = new HashMap<>();

				headMap.put("Authorization", "token" + " " + token);
				host = host.replace("{mobile}", String.valueOf(baseInfo.getPhone()));
				host += "?task_id=" + operatorReqLog.getTaskId();
				String result = null;
				try {
					result = MxCreditRequest.get(host, headMap);
				} catch (Exception e) {
					logger.error(e.getMessage(),e);
				}

				if(StringUtil.isNotBlank(result)) {
					JSONObject json = JSON.parseObject(result);
					String code = json.getString("code");
					if("0".equals(code)){
						phoneState = UserAuthModel.STATE_VERIFIED;
						OperatorRespDetail oldDetail = operatorRespDetailService.getByTaskId(operatorReqLog.getTaskId());
						if(oldDetail == null) {
							OperatorRespDetail operatorRespDetail = new OperatorRespDetail(operatorReqLog.getId(), operatorReqLog.getTaskId(), result);
							operatorRespDetailService.insert(operatorRespDetail);
						} else {
							OperatorRespDetail updateDetail = new OperatorRespDetail();
							updateDetail.setId(oldDetail.getId());
							updateDetail.setOperatorData(result);
							operatorRespDetailService.updateById(updateDetail);
						}
						operatorService.saveOperatorInfos(result, userAuth.getUserId(),DateUtil.getNow(), baseInfo.getPhone(), operatorReqLog.getId());
					} else {
						phoneState = UserAuthModel.STATE_NOT_CERTIFIED;
					}
				}
				Map<String, Object> modifyMap = new HashMap<String, Object>();
				modifyMap.put("userId", userAuth.getUserId());
				modifyMap.put("phoneState", phoneState);
				this.updateByUserId(modifyMap);
			} 
		}	
		userAuth.setPhoneState(phoneState);
		return userAuth;
	}

	@Override
	public Integer updateByUserId(Map<String, Object> paramMap) {
		return userAuthMapper.updateByUserId(paramMap);
	}
	
	@Override
	public Page<UserAuthModel> listUserAuth(Map<String, Object> params,
			int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<UserAuthModel> list = userAuthMapper.listUserAuthModel(params);
		return (Page<UserAuthModel>) list;
	}

	@Override
	public UserAuth findSelective(long userId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		return userAuthMapper.findSelective(map);
	}

	@Override
	public Map<String, Object> getAuthState(Map<String, Object> paramMap) {
		//定义需要的变量
		String resultSql="";//result返回值中的语句
		String qualifiedSql="";//qualified返回值中的语句
		int qualifiedCount=4;//基础必填项数量
		//芝麻信用sql语句拼接，需要sys_config表里设置的zhima_auth属性，10-去除 20-选填 30-必填
		String zhima_auth=Global.getValue("zhima_auth");
		if("30".equals(zhima_auth)){
			resultSql="+IF (zhima_state = 30, 1, 0)";
			qualifiedSql="+IF (zhima_state = 30, 1, 0)";
			qualifiedCount++;
		}else if("20".equals(zhima_auth)){
			resultSql="+IF (zhima_state = 30, 1, 0)";
		}
		//芝麻信用sql拼接结束
		//拼接整个sql
		String sql="	SELECT ("
				+ "IF (id_state = 30, 1, 0) +"
				+ "IF (phone_state = 30, 1, 0) +"
				+ "IF (contact_state = 30, 1, 0) +"
				+ "IF (bank_card_state = 30, 1, 0) +"
				+ "IF (work_info_state = 30, 1, 0) +"
				+ "IF (other_info_state = 30, 1, 0)"
				+ resultSql
				+ ") AS result,"
				+ Global.getValue("auth_total")+" AS total,"
				+ "IF ("
				+ "(IF (id_state = 30, 1, 0) +"
				+ "IF (phone_state = 30, 1, 0) +"
				+ "IF (contact_state = 30, 1, 0) +"
				+ "IF (bank_card_state = 30, 1, 0) "
				+ qualifiedSql
				+ ") = "
				+ qualifiedCount
				+ ",1,0) AS qualified "
				+ "FROM cl_user_auth "
				+ "WHERE user_id = "+paramMap.get("userId");
		paramMap=new HashMap<String,Object>();
		paramMap.put("sqlstring", sql);
		return userAuthMapper.executeSql(paramMap);
	}

	@Override
	public int updatePhoneState(Map<String, Object> userAuth) {
		return userAuthMapper.updatePhoneState(userAuth);
	}
	
}
