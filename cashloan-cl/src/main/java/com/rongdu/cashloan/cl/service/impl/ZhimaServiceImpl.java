package com.rongdu.cashloan.cl.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tool.util.DateUtil;

import com.rongdu.cashloan.cl.domain.Zhima;
import com.rongdu.cashloan.cl.mapper.BankCardMapper;
import com.rongdu.cashloan.cl.mapper.UserAuthMapper;
import com.rongdu.cashloan.cl.mapper.ZhimaMapper;
import com.rongdu.cashloan.cl.model.UserAuthModel;
import com.rongdu.cashloan.cl.model.zmxy.authorize.AuthCallBack;
import com.rongdu.cashloan.cl.model.zmxy.authorize.AuthCallBackResp;
import com.rongdu.cashloan.cl.model.zmxy.base.ZmQueryCreator;
import com.rongdu.cashloan.cl.model.zmxy.creditScore.ZmScoreQuery;
import com.rongdu.cashloan.cl.model.zmxy.creditScore.ZmScoreResp;
import com.rongdu.cashloan.cl.monitor.BusinessExceptionMonitor;
import com.rongdu.cashloan.cl.service.ZhimaService;
import com.rongdu.cashloan.core.common.context.Constant;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.service.impl.BaseServiceImpl;
import com.rongdu.cashloan.core.domain.User;
import com.rongdu.cashloan.core.mapper.UserBaseInfoMapper;

@Service("zhimaService")
public class ZhimaServiceImpl extends BaseServiceImpl<Zhima, Long> implements ZhimaService {

	private static final Logger logger = LoggerFactory.getLogger(ZhimaServiceImpl.class);
   
    @Resource
    private ZhimaMapper zhimaMapper;
    
    @Resource
    private UserAuthMapper userAuthMapper;
    
    @Resource
    private UserBaseInfoMapper userBaseinfoMapper;
    
    @Resource
    private BankCardMapper bankCardMapper;
    
	@Override
	public BaseMapper<Zhima, Long> getMapper() {
		return zhimaMapper;
	}

	@Override
	public Zhima getZhima(Map<String, Object> paramMap) {
		return zhimaMapper.findSelective(paramMap);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Map<String, Object> authCallBack(String params, User user) throws Exception {
		Map<String,Object> result=new HashMap<String,Object>();
		ZmQueryCreator zmQueryCreator = new ZmQueryCreator();
		ZmScoreQuery zmScoreQuery = zmQueryCreator.create(ZmScoreQuery.class);
		AuthCallBack authCallBack = zmQueryCreator.create(AuthCallBack.class);
		AuthCallBackResp authCallBackResp = authCallBack.decryptedParam(params);
		if (authCallBackResp.isSuccess()) {
			Long userId = Long.valueOf(authCallBackResp.getKey());
			// 修改认证表
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userId", userId);
			paramMap.put("zhimaState", UserAuthModel.STATE_VERIFIED);
			paramMap.put("zhimaTime", DateUtil.getNow());
			int updateCount  = userAuthMapper.updateByUserId(paramMap);
			if (updateCount != 1) {
				logger.error("用户"+userId+"认证信息更新出错");
				result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "认证信息更新出错");	
				return result;
			}
			String zmScore="0";	
			paramMap.clear();
			paramMap.put("userId", userId);
			// 新增芝麻信用表
			if (zhimaMapper.findSelective(paramMap) == null) {
				Zhima zhima = new Zhima();
				zhima.setBind("20");
				zhima.setBindTime(new Date(System.currentTimeMillis()));
				zhima.setUserId(userId);
				zhima.setScore(0.00);
				zhima.setOpenId(authCallBackResp.getOpenId());
				int count  = zhimaMapper.save(zhima);
				if (count != 1) {
					logger.error("用户"+userId+"芝麻信用信息保存出错");
					result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
					result.put(Constant.RESPONSE_CODE_MSG, "芝麻信用信息保存出错");	
					return result;
				}
			}
			
			//授权成功之后执行
			//validateRcZhimaAntiFraud(String.valueOf(userId));
			//validateRcZhimaIndustry(String.valueOf(userId));
			
			String time = Long.toString(Calendar.getInstance().getTimeInMillis());
			String transactionId = DateUtil.dateStr(DateUtil.getNow(), "yyyyMMddHHmmssSSS") + time;
			ZmScoreResp zmScoreResp = zmScoreQuery.queryScore(authCallBackResp.getOpenId(), transactionId);
			if (zmScoreResp.isSuccess()) {
				paramMap.clear();
				paramMap.put("userId", userId);
				//修改芝麻信用表
				Zhima zhima = zhimaMapper.findSelective(paramMap);
				zhima.setBindTime(new Date(System.currentTimeMillis()));
				zhima.setScore(Double.valueOf(zmScoreResp.getZmScore()));
				if (zhimaMapper.update(zhima) != 1) {
					logger.error("用户"+userId+"芝麻信用信息更新出错");
					//result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
					//result.put(Constant.RESPONSE_CODE_MSG, "授权成功,芝麻积分更新出错");	
					//return result;
				}
				zmScore=zmScoreResp.getZmScore();
			} else {
				logger.error("用户"+userId+"获取积分出错:"+zmScoreResp.getErrorMessage());
				//result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				//result.put(Constant.RESPONSE_CODE_MSG,"授权成功,获取积分出错:"+zmScoreResp.getErrorMessage());	
				//return result;
			}
			result.put("zmScore",zmScore);
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "授权成功");
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, authCallBackResp.getErrorMessage());	
			logger.error("获取芝麻信息出错"+authCallBackResp.getErrorMessage());
		}
		return result;
	}

	public int updateZhimaScore(Long userId){
		int update = 0;
		Map<String,Object> searchMap=new HashMap<String,Object>();
		searchMap.put("userId", userId);
		Zhima zm=zhimaMapper.findSelective(searchMap);
		if(zm!=null){
			String time = Long.toString(Calendar.getInstance().getTimeInMillis());
			String transactionId = DateUtil.dateStr(DateUtil.getNow(), "yyyyMMddHHmmssSSS") + time;
			ZmQueryCreator zmQueryCreator = new ZmQueryCreator();
			ZmScoreQuery zmScoreQuery = zmQueryCreator.create(ZmScoreQuery.class);
			logger.info("征信数据查询 - 芝麻分，用户："+userId+"，openId："+zm.getOpenId());
			ZmScoreResp zmScoreResp = zmScoreQuery.queryScore(zm.getOpenId(), transactionId);
		
			if (zmScoreResp != null &&  zmScoreResp.isSuccess()) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("score", Double.valueOf(zmScoreResp.getZmScore()));
				paramMap.put("bindTime", new Date(System.currentTimeMillis()));
				paramMap.put("id", zm.getId());
				update = zhimaMapper.updateSelective(paramMap);
				logger.info("征信数据获取-查询芝麻分userId:" + userId + "，更新数据条数:" + update);
			} else if (zmScoreResp != null && ZmScoreResp.AUTHENTICATION_FAIL.equals(zmScoreResp.getErrorCode())) {
				
				String message = zmScoreResp.getErrorMessage();
				BusinessExceptionMonitor.add(BusinessExceptionMonitor.TYPE_1, userId, message);
				
				logger.info("征信数据获取-查询芝麻分userId:" + userId + "，获取积分出错:" + message);
				// 获取芝麻分 鉴权失败  将芝麻分置为350 ，并将芝麻认证状态设为未认证(10)
				Map<String, Object> userAuthMap = new HashMap<String, Object>();
				userAuthMap.put("userId", userId);
				userAuthMap.put("zhimaState", UserAuthModel.STATE_NOT_CERTIFIED);
				userAuthMapper.updateByUserId(userAuthMap);
				
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("score", 350);
				paramMap.put("bindTime", new Date(System.currentTimeMillis()));
				paramMap.put("id", zm.getId());
				update = zhimaMapper.updateSelective(paramMap);
			} else {
				String message = zmScoreResp.getErrorMessage();
				BusinessExceptionMonitor.add(BusinessExceptionMonitor.TYPE_1, userId, message);
				
				logger.info("征信数据获取-查询芝麻分userId:" + userId + "，获取积分出错:" + message);
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("bindTime", new Date(System.currentTimeMillis()));
				paramMap.put("id", zm.getId());
				update = zhimaMapper.updateSelective(paramMap);
			}
			
		}
		return update;
	}
	
	@Override
	public Zhima findByUserId(Long userId) {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("userId", userId);
		return zhimaMapper.findSelective(paramMap);
	}
	
}
