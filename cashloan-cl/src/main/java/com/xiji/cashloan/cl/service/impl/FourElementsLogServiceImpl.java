package com.xiji.cashloan.cl.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tool.util.DateUtil;

import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.mapper.UserBaseInfoMapper;
import com.xiji.cashloan.cl.mapper.FourElementsLogMapper;
import com.xiji.cashloan.cl.domain.FourElementsLog;
import com.xiji.cashloan.cl.service.FourElementsLogService;

import credit.CreditRequest;
import credit.Header;


/**
 * 四要素认证记录ServiceImpl
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */

@Service("fourElementsLogService")	
public class FourElementsLogServiceImpl extends BaseServiceImpl<FourElementsLog, Long> implements FourElementsLogService {

	private static final Logger logger = LoggerFactory.getLogger(FourElementsLogServiceImpl.class);

	@Resource
	private FourElementsLogMapper fourElementsLogMapper;

	@Resource
	private UserBaseInfoMapper userBaseInfoMapper;

	@Override
	public BaseMapper<FourElementsLog, Long> getMapper() {
		return fourElementsLogMapper;
	}

	@Override
	public int verifyTime(String userId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("createTime", DateUtil.dateStr(DateUtil.getNow(), DateUtil.DATEFORMAT_STR_002) );
		int verityTime = fourElementsLogMapper.count(paramMap);
		if (verityTime < Integer.valueOf(Global.getValue("four_elements_verity_most_time"))) {
			return 10;
		} else {
			return 20;
		}
	}

	@Override
	public JSONObject fourElementsVerify(String userId, UserBaseInfo baseInfo, String cardNo) {
		
		
		// 校验4要素 idNo、realName、phone、bankCardNo
		final String APIKEY =  Global.getValue("apikey");
		final String SECRETKEY = Global.getValue("secretkey");
		final String TX_CHANGENO = Global.getValue("tx_channelNo");
		final String INTERFACE_NAME = Global.getValue("four_elements_interfaceName");
		final String API_HOST = Global.getValue("tx_bankCard_four");

		long timestamp = DateUtil.getNowTime();
		Header header = new Header(APIKEY, TX_CHANGENO, INTERFACE_NAME, timestamp);
		CreditRequest creditRequest = new CreditRequest(API_HOST, header);
		Map<String, Object> payload = new HashMap<String, Object>();
		payload.put("name", baseInfo.getRealName());
		payload.put("idCard", baseInfo.getIdNo());
		payload.put("accountNO", cardNo);
		payload.put("bankPreMobile", baseInfo.getPhone());

		creditRequest.setPayload(payload);
		creditRequest.signByKey(SECRETKEY);

		String resultByInterface = null;
		try {
			resultByInterface = creditRequest.request();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("testInfo:"+resultByInterface);
		JSONObject array = JSONObject.parseObject(resultByInterface);
		JSONObject res = array.getJSONObject("res");
		
		//保存四要素认证结果
		FourElementsLog fourElementsLog = new FourElementsLog();
		fourElementsLog.setUserId(Long.valueOf(userId));
		fourElementsLog.setCardNo(cardNo);
		fourElementsLog.setIdCard(baseInfo.getIdNo());
		fourElementsLog.setUserName(baseInfo.getRealName());
		fourElementsLog.setPhone(baseInfo.getPhone());
		if(null!=res){
			fourElementsLog.setResult(res.getString("result"));
			fourElementsLog.setCheckStatus(res.getString("checkStatus"));
		}
		
		fourElementsLog.setCode(array.getString("code"));
		
		fourElementsLog.setCreateTime(DateUtil.getNow());
		insert(fourElementsLog);
		
		return array;
	}
	
	@Override
	public FourElementsLog findSelective(Map<String, Object> paramMap) {
		return fourElementsLogMapper.findSelective(paramMap);
	}
}