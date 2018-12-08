package com.xiji.cashloan.cl.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.domain.*;
import com.xiji.cashloan.cl.mapper.*;
import com.xiji.cashloan.cl.model.BorrowRepayModel;
import com.xiji.cashloan.cl.model.dsdata.SmsTkCreditRequest;
import com.xiji.cashloan.cl.monitor.BusinessExceptionMonitor;
import com.xiji.cashloan.cl.service.ClSmsService;
import com.xiji.cashloan.cl.util.CallsOutSideFeeConstant;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.core.common.util.DateUtil;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.core.domain.User;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.mapper.UserBaseInfoMapper;
import com.xiji.cashloan.core.mapper.UserMapper;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 */
@Service("clSmsService")
public class ClSmsServiceImpl extends BaseServiceImpl<Sms, Long> implements ClSmsService {
	
	public static final Logger logger = LoggerFactory.getLogger(ClSmsServiceImpl.class);
	
    @Resource
    private SmsMapper smsMapper;
    @Resource
    private SmsTplMapper smsTplMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
	private BorrowRepayMapper borrowRepayMapper;
    @Resource
	private ClBorrowMapper clBorrowMapper;
    @Resource
    private UrgeRepayOrderMapper urgeRepayOrderMapper;
    @Resource
    private UserBaseInfoMapper userBaseInfoMapper;
    @Resource
    private BankCardMapper bankCardMapper;
	@Resource
    private CallsOutSideFeeMapper callsOutSideFeeMapper;
	@Override
	public BaseMapper<Sms, Long> getMapper() {
		return smsMapper;
	}
	
	@Override
	public long findTimeDifference(String phone, String type) {
		int countdown = Global.getInt("sms_countdown");
		Map<String,Object> data = new HashMap<>();
		data.put("phone", phone);
		data.put("smsType", type);
		Sms sms = smsMapper.findTimeMsg(data);
		long times = 0;
		if (sms != null) {
			Date d1 = sms.getSendTime();
			Date d2 = DateUtil.getNow();
			long diff = d2.getTime() - d1.getTime();
			if (diff < countdown*1000) {
				times = countdown-(diff/1000);
			}else {
				times = 0;
			}
		}
		return times;
	}
	
	@Override
	public int countDayTime(String phone, String type) {
		String mostTimes = Global.getValue("sms_day_most_times");
		int mostTime = JSONObject.parseObject(mostTimes).getIntValue(type);
		
		Map<String,Object> data = new HashMap<>();
		data.put("phone", phone);
		data.put("smsType", type);
		int times = smsMapper.countDayTime(data);
		
		return mostTime - times;
	}

	@Override
	public String sendSms(String phone, String type) {
		Map<String, Object> search = new HashMap<>();
		search.put("type", type);
		search.put("state", "10");
		SmsTpl tpl = smsTplMapper.findSelective(search);
		if (tpl != null) {
			Map<String, Object> payload = new HashMap<>();
			int vcode = (int) (Math.random() * 9000) + 1000;
			payload.put("mobile", phone);
			payload.put("message", change(type)+vcode);
			String result = sendCode(payload, tpl.getNumber());
			logger.debug("发送短信，phone：" + phone + "， type：" + type + "，同步响应结果：" + result);
			return result(result, phone, type, vcode);
		}
		logger.error("发送短信，phone：" + phone + "， type：" + type + "，没有获取到smsTpl");
		return null;
	}
	
	@Override
	public int smsBatch(String id) {
		final long[] ids = StringUtil.toLongs(id.split(","));
		new Thread(){
			public void run() {
				Map<String, Object> search = new HashMap<>();
				search.put("type", "overdue");
				search.put("state", "10");
				String smsNo = smsTplMapper.findSelective(search).getNumber();
				if (smsNo!=null) {
					logger.debug("开始批量发送逾期短信。。");
					for (int i = 0; i < ids.length; i++) {
						UrgeRepayOrder uro = urgeRepayOrderMapper.findByPrimary(ids[i]);
						Map<String,Object> map = new HashMap<>();
						map.put("platform", uro.getBorrowTime());
						map.put("loan", uro.getAmount());
						map.put("time", uro.getRepayTime());
						map.put("overdueDay", uro.getPenaltyDay());
						map.put("amercement", uro.getPenaltyAmout());
						map.put("phone", uro.getPhone().subSequence(7, 11));	
						
						Map<String, Object> payload = new HashMap<>();
						payload.put("mobile", uro.getPhone());
				        payload.put("message", changeMessage("overdue",map));
				        String result = sendCode(payload, smsNo);
				        logger.debug("短信发送结果"+result);
				        result(result, uro.getPhone(), "overdue",0);
					}
				}else {
					return ;
				}
			}
		}.start();
		return 1;
	}
	
	
	@Override
	public int verifySms(String phone, String type, String code) {
		if ("dev".equals(Global.getValue("app_environment")) && "0000".equals(code)) {
			return 1;
		}
		
		if(StringUtil.isBlank(phone) || StringUtil.isBlank(type) || StringUtil.isBlank(code)){
			return 0;
		}
		
		
		if (!StringUtil.isPhone(phone)) {
			return 0;
		}
		
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("phone", phone);
		data.put("smsType", type);
		Sms sms = smsMapper.findTimeMsg(data);
		if (sms != null) {
			String mostTimes = Global.getValue("sms_day_most_times");
			int mostTime = JSONObject.parseObject(mostTimes).getIntValue("verifyTime");
			
			data = new HashMap<>();
			data.put("verifyTime", sms.getVerifyTime()+1);
			data.put("id", sms.getId());
			smsMapper.updateSelective(data);
			
			if (StringUtil.equals("40", sms.getState())||sms.getVerifyTime()+1>mostTime) {
				return 0;
			}
			
			long timeLimit = Long.parseLong(Global.getValue("sms_time_limit"));
			
			Date d1 = sms.getSendTime();
			Date d2 = DateUtil.getNow();
			long diff = d2.getTime() - d1.getTime();
			if (diff > timeLimit * 60 * 1000) {
				return -1;
			}
			if (sms.getCode().equals(code)) {
				Map<String,Object> map = new HashMap<>();
				map.put("id", sms.getId());
				map.put("state", "20");
				smsMapper.updateSelective(map);
				return 1;
			}
		}
		return 0;
	}
	
	protected String changeMessage(String smsType, Map<String,Object> map) {
		String message = "";
		if ("overdue".equals(smsType)) {
			message = ret(smsType);
			message = message
					.replace(
							"{$platform}",
							DateUtil.dateStr(DateUtil.valueOf(
									DateUtil.dateStr(DateUtil.valueOf(map.get("platform").toString()), DateUtil.DATEFORMAT_STR_002)),"M月dd日"))
					.replace("{$loan}", StringUtil.isNull(map.get("loan")))
					.replace(
							"{$time}",
							DateUtil.dateStr(DateUtil.valueOf(
									DateUtil.dateStr(DateUtil.valueOf(map.get("time").toString()), DateUtil.DATEFORMAT_STR_002)),"M月dd日"))
					.replace("{$overdueDay}",
							StringUtil.isNull(map.get("overdueDay")))
					.replace("{$amercement}",
							StringUtil.isNull(map.get("amercement")));
		}
		if ("loanInform".equals(smsType)) {
			message = ret(smsType);
			message = message.replace("{$time}",
					  DateUtil.dateStr(DateUtil.valueOf(
							DateUtil.dateStr(DateUtil.valueOf(map.get("time").toString()), DateUtil.DATEFORMAT_STR_002)),"M月dd日"));
		}
		if ("repayInform".equals(smsType)) {
			message = ret(smsType);
			message = message.replace("{$time}",
					  DateUtil.dateStr(DateUtil.valueOf(
							DateUtil.dateStr(DateUtil.valueOf(map.get("time").toString()), DateUtil.DATEFORMAT_STR_002)),"M月dd日"))
							.replace("{$loan}", StringUtil.isNull(map.get("loan")));
		}
		if("repayBefore".equals(smsType)){
			message = ret(smsType);
			message = message
					.replace("{$loan}", StringUtil.isNull(map.get("loan")))
					.replace(
							"{$time}",
							DateUtil.dateStr(DateUtil.valueOf(
									DateUtil.dateStr(DateUtil.valueOf(map.get("time").toString()), DateUtil.DATEFORMAT_STR_002)),"M月dd日"))
					.replace("{$name}", StringUtil.isNull(map.get("name")))
					.replace("{$cardNo}", StringUtil.isNull(map.get("cardNo")))
					.replace("{$bankCardNo}", StringUtil.isNull(map.get("bankCardNo")));
		}
		if("delayPlan".equals(smsType)){
			String appName = Global.getValue("appName");
			message = ret(smsType);
			message = message
				.replace("{$appName}", appName)
				.replace(
					"{$year}",
					DateUtil.dateStr(DateUtil.valueOf(
						DateUtil.dateStr(DateUtil.valueOf(map.get("time").toString()), DateUtil.DATEFORMAT_STR_002)),"yyyy"))
				.replace(
					"{$month}",
					DateUtil.dateStr(DateUtil.valueOf(
						DateUtil.dateStr(DateUtil.valueOf(map.get("time").toString()), DateUtil.DATEFORMAT_STR_002)),"M"))
				.replace(
					"{$day}",
					DateUtil.dateStr(DateUtil.valueOf(
						DateUtil.dateStr(DateUtil.valueOf(map.get("time").toString()), DateUtil.DATEFORMAT_STR_002)),"dd"));
		}
		return message;
	}
	
	public String change(String code){
		String message = null;
		if ("register".equals(code)) {
			message = ret("register");
		}else if ("findReg".equals(code)) {
			message = ret("findReg");
		}else if ("bindCard".equals(code)) {
			message = ret("bindCard");
		}else if ("findPay".equals(code)) {
			message = ret("findPay");
		}else if ("overdue".equals(code)) {
			message = ret("overdue");
		}else if ("loanInform".equals(code)) {
			message = ret("loanInform");
		}else if ("repayInform".equals(code)) {
			message = ret("repayInform");
		}else if ("repayBefore".equals(code)) {
			message = ret("repayBefore");
		}
		return message;
	}
	
	public String ret(String type){
		Map<String, Object> search = new HashMap<>();
		search.put("type", type);
		SmsTpl tpl = smsTplMapper.findSelective(search);
		return tpl.getTpl();
	}
	
	private String result(String result, String phone, String type, int vcode){
		String msg = null;
		JSONObject resultJson = JSONObject.parseObject(result);

		Integer code;
		if (StringUtil.isNotBlank(resultJson)) {
			code = resultJson.getInteger("code");
			logger.debug("发送短信，phone：" + phone + "， type：" + type + "，保存sms时code：" + code);
			Date now = DateUtil.getNow();
			Sms sms = new Sms();
			sms.setPhone(phone);
			sms.setSendTime(now);
			sms.setRespTime(now);
			sms.setSmsType(type);
			sms.setVerifyTime(0);

			if (code == 200) {
				JSONObject resJson = JSONObject.parseObject(StringUtil.isNull(resultJson.get("res")));
				JSONObject tempJson = JSONObject.parseObject(StringUtil.isNull(resultJson.get("tempParame")));
				String orderNo = StringUtil.isNull(resultJson.get("orderNo"));
				Integer tempCode = tempJson.getInteger("code");
				sms.setContent(resJson.getString("result"));
				sms.setResp("短信发送中");
				sms.setCode(StringUtil.isNull(tempCode));
				sms.setOrderNo(orderNo);
				sms.setState("30");
				int ms = smsMapper.save(sms);
				if (ms > 0){
					msg = orderNo;
				}
                // 保存调用外部数据收费信息记录
				UserBaseInfo userBaseInfo = userBaseInfoMapper.getBaseUserByPhone(phone);
				Long userId = null;
				if (userBaseInfo != null && userBaseInfo.getUserId() != null){
					userId = userBaseInfo.getUserId();
				}
				CallsOutSideFee callsOutSideFee = new CallsOutSideFee(userId,orderNo, CallsOutSideFeeConstant.CALLS_TYPE_SEND_MSG,CallsOutSideFeeConstant.FEE_SEND_MSG);
				callsOutSideFeeMapper.save(callsOutSideFee);
			} else {
				String message = resultJson.getString("message");
				sms.setContent(message);
				sms.setResp("短信发送失败");
				sms.setCode("");
				sms.setOrderNo("");
				sms.setState("40");
				smsMapper.save(sms);
				BusinessExceptionMonitor.add(BusinessExceptionMonitor.TYPE_3, phone, message);
			}
		}
		return msg;
	}

    @Override
	public void saveSmsBankCard(boolean result, String phone, String type,String msg){
		if (logger.isDebugEnabled()) {
			logger.debug("发送短信，phone：" + phone + "， type：" + type);
		}
		Date now = DateUtil.getNow();
		Sms sms = new Sms();
		sms.setPhone(phone);
		sms.setSendTime(now);
		sms.setRespTime(now);
		sms.setSmsType(type);
		sms.setVerifyTime(0);

		if (result) {
			sms.setContent(msg);
			sms.setResp("短信发送中");
			sms.setCode("");
			sms.setOrderNo("");
			sms.setState("30");
			smsMapper.save(sms);
		} else {
			sms.setContent("失败");
			sms.setResp("短信发送失败");
			sms.setCode("");
			sms.setOrderNo("");
			sms.setState("40");
			smsMapper.save(sms);
			BusinessExceptionMonitor.add(BusinessExceptionMonitor.TYPE_12, phone, msg);
		}
	}

	/**
	 * 判断短信发送渠道，默认大圣数据短信渠道
	 */
	private String sendCode(Map<String, Object> payload, String smsNo){
		String sms_passageway = Global.getValue("sms_passageway");
		if (StringUtil.isBlank(sms_passageway)){
			sms_passageway = "10";
		}
		
		if ("10".equals(sms_passageway)) {
			return dsSendSms(payload, smsNo);
		}
		return "";
	}
	
	//大圣短信
	private String dsSendSms(Map<String, Object> payload, String smsNo){
		final String APIHOST = Global.getValue("sms_apihost");//发送地址

		SmsTkCreditRequest creditRequest = new SmsTkCreditRequest(APIHOST,smsNo);
        creditRequest.setPayload(payload);
        String result=null;
		try {
			result = creditRequest.request();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return result;
	}

	@Override
	public int findUser(String phone) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loginName", phone);
		User user =  userMapper.findSelective(paramMap);
		if (StringUtil.isNotBlank(user)) {
			return 1;
		}
		return 0;
	}

	@Override
	public String loanInform(long userId,long borrowId) {
		Map<String, Object> search = new HashMap<>();
		User user = userMapper.findByPrimary(userId);
		search.put("borrowId", borrowId);
		Borrow br = clBorrowMapper.findByPrimary(borrowId);
		if (user!=null&&br!=null) {
			search.put("type", "loanInform");
			search.put("state", "10");
			SmsTpl tpl = smsTplMapper.findSelective(search);
			if (tpl!=null) {
				search = new HashMap<>();
				search.put("time", DateUtil.dateStr(br.getCreateTime(),DateUtil.DATEFORMAT_STR_001));
				Map<String, Object> payload = new HashMap<>();
				payload.put("mobile", user.getLoginName());
				payload.put("message", changeMessage("loanInform",search));
				String result = sendCode(payload,tpl.getNumber());
				logger.debug("短信发送结果"+result);
				return result(result, user.getLoginName(), "loanInform",0);
			}
		}
		return null;
	}

	@Override
	public String repayInform(long userId,long borrowId) {
		Map<String, Object> search = new HashMap<>();
		User user = userMapper.findByPrimary(userId);
		search.put("borrowId", borrowId);
		Borrow br = clBorrowMapper.findByPrimary(borrowId);
		if (user!=null&&br!=null) {
			search.put("type", "repayInform");
			search.put("state", "10");
			SmsTpl tpl = smsTplMapper.findSelective(search);
			if (tpl!=null) {
				search = new HashMap<>();
				search.put("time", DateUtil.dateStr(br.getCreateTime(),DateUtil.DATEFORMAT_STR_001));
				search.put("loan", br.getAmount());
				Map<String, Object> payload = new HashMap<>();
				payload.put("mobile", user.getLoginName());
				payload.put("message", changeMessage("repayInform",search));
				String result = sendCode(payload,tpl.getNumber());
				logger.debug("短信发送结果"+result);
				return result(result, user.getLoginName(), "repayInform",0);
			}
		}
        return null;
	}

	@Override
	public String delayPlan(long userId,long borrowId,Date date) {
		Map<String, Object> search = new HashMap<>();
		User user = userMapper.findByPrimary(userId);
		search.put("borrowId", borrowId);
		Borrow br = clBorrowMapper.findByPrimary(borrowId);
		if (user!=null&&br!=null) {
			search.put("type", "delayPlan");
			search.put("state", "10");
			SmsTpl tpl = smsTplMapper.findSelective(search);
			if (tpl!=null) {
				search = new HashMap<>();
				search.put("time", DateUtil.dateStr(date,DateUtil.DATEFORMAT_STR_001));
				Map<String, Object> payload = new HashMap<>();
				payload.put("mobile", user.getLoginName());
				payload.put("message", changeMessage("delayPlan",search));
				String result = sendCode(payload,tpl.getNumber());
				logger.debug("短信发送结果"+result);
				return result(result, user.getLoginName(), "delayPlan",0);
			}
		}
		return null;
	}
	
	@Override
	public int overdue(long borrowId){
		Map<String, Object> search = new HashMap<>();
		search.put("type", "overdue");
		search.put("state", "10");
		String smsNo = smsTplMapper.findSelective(search).getNumber();
		if (smsNo != null) {
				BorrowRepayModel brm = borrowRepayMapper.findOverdue(borrowId);
				Map<String,Object> map = new HashMap<>();
				map.put("phone", brm.getPhone());
				map.put("platform", DateUtil.dateStr(brm.getCreateTime(),DateUtil.DATEFORMAT_STR_001));
				map.put("loan", brm.getAmount());
				map.put("time",  DateUtil.dateStr(brm.getRepayTime(),DateUtil.DATEFORMAT_STR_001));
				map.put("overdueDay", brm.getPenaltyDay());
				map.put("amercement", brm.getPenaltyAmout());
				Map<String, Object> payload = new HashMap<>();
				payload.put("mobile", brm.getPhone());
		        payload.put("message", changeMessage("overdue",map));
		        String result = sendCode(payload, smsNo);
		        logger.debug("短信发送结果"+result);
		        result(result, brm.getPhone(), "overdue",0);
		}else {
			return 0;
		}
		return 1;
	}

	@Override
	public String repayBefore(long userId, long borrowId) {
		Map<String, Object> search = new HashMap<>();
		search.put("borrowId", borrowId);
		BorrowRepay repay=borrowRepayMapper.findSelective(search);
		UserBaseInfo baseInfo=userBaseInfoMapper.findByUserId(userId);
		search.clear();
		search.put("userId", userId);
		BankCard bankCard = bankCardMapper.findSelective(search);
		if (baseInfo != null && repay != null && bankCard != null) {
			search.clear();
			search.put("type", "repayBefore");
			search.put("state", "10");
			SmsTpl tpl = smsTplMapper.findSelective(search);
			if (tpl!=null) {
				search = new HashMap<>();
				search.put("time", DateUtil.dateStr(repay.getRepayTime(),DateUtil.DATEFORMAT_STR_001));//还款时间
				search.put("cardNo", baseInfo.getPhone().substring(7, 11));//手机尾号
				search.put("name", baseInfo.getRealName());//姓名
				search.put("loan", repay.getAmount());//待还款金额
				int len=bankCard.getCardNo().length();
				search.put("bankCardNo", bankCard.getCardNo().substring(len-4, len));//银行卡号
				Map<String, Object> payload = new HashMap<>();
				payload.put("mobile",baseInfo.getPhone());
				payload.put("message", changeMessage("repayBefore",search));
				String result = sendCode(payload,tpl.getNumber());
				logger.debug("短信发送结果"+result);
				return result(result, baseInfo.getPhone(), "repayBefore",0);
			}
		}
        return null;
	}
	
	@Override
	public Page<Sms> list(Map<String, Object> params, int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<Sms> list = smsMapper.listSelective(params);
		if (list != null && list.size() > 0) {
			int flag = 0;
			for (int i = 0; i < list.size(); i++) {
				if ("30".equals(list.get(i).getState())) {
					flag++;
					getReportByOrderNo(list.get(i).getOrderNo(),list.get(i)
							.getPhone(), "");	
				}	
			}
			if (flag != 0) {
				PageHelper.startPage(currentPage, pageSize);
				list = smsMapper.listSelective(params);
			} 
		}
		return (Page<Sms>) list;
	}
	

	@SuppressWarnings("unused")
	@Override
	public void getReportByOrderNo(final String orderNo, final String userPhone, final String type) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				Integer code = 201;
				int i =0;
				while (code == 201 && i < 6) {//轮询60秒，每10秒查询一次，直到code不等于201
					i++;
					// 获取报告前休眠10秒
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					// 获取短信报告
					final String APIHOST = Global.getValue("sms_apihost_report");// 发送地址
					HttpGet request = new HttpGet(APIHOST + "/" + orderNo);
					CloseableHttpClient httpClient = HttpClientBuilder.create().build();
					HttpClients.createDefault();
					HttpResponse httpResponse;
					String result = null;
					try {
						httpResponse = httpClient.execute(request);
						if (httpResponse.getStatusLine().getStatusCode() == 200) {
							HttpEntity httpEntity = httpResponse.getEntity();
							result = EntityUtils.toString(httpEntity);
						}
						httpClient.close();
					} catch (ClientProtocolException e) {
						logger.error(e.getMessage(), e);
					} catch (IOException e) {
						logger.error(e.getMessage(), e);
					}
					JSONObject resultJson = JSONObject.parseObject(result);
					logger.info("短信发送结果" + resultJson);
					int status = 0;
					if (StringUtil.isNotBlank(resultJson)) {
						code = resultJson.getInteger("code");
						logger.info("code:" + code);
						if (code == 200) {
							JSONArray array = JSON.parseArray(StringUtil.isNull(resultJson.get("data")));
							for (Object json : array) {
								JSONObject dataJson = JSONObject.parseObject(json.toString());
								Date reporyTime = dataJson.getDate("reportTime");
								status = dataJson.getIntValue("status");
								String reportMessage = dataJson.getString("reportMessage");
								String phone = dataJson.getString("mobile");
								Map<String, Object> paramMap = new HashMap<>();
								if (status == 1) {
									paramMap.put("orderNo", orderNo);
									paramMap.put("resp", "短信已发送");
									paramMap.put("state", "10");
									smsMapper.updateByOrderNo(paramMap);
//									UserBaseInfo userBaseInfo = userBaseInfoMapper.getBaseUserByPhone(phone);
//									Long userId = null;
//									if (userBaseInfo != null && userBaseInfo.getUserId() != null){
//										userId = userBaseInfo.getUserId();
//									}
//									CallsOutSideFee callsOutSideFee = new CallsOutSideFee(userId,orderNo, CallsOutSideFeeConstant.CALLS_TYPE_SEND_MSG,CallsOutSideFeeConstant.FEE_SEND_MSG);
//									callsOutSideFeeMapper.save(callsOutSideFee);
									logger.error("发送短信，phone：" + userPhone + "， type：" + type + "，发送成功");
								} else {
									paramMap.put("orderNo", orderNo);
									paramMap.put("resp", "短信发送失败");
									paramMap.put("state", "40");
									smsMapper.updateByOrderNo(paramMap);
									logger.error("发送短信，phone：" + userPhone + "， type：" + type + "，发送失败");
								}

							}
						} else {
							if (code == 201) {
								logger.info("短信报告获取结果：短信报告尚未获取成功");
							}
						}
					}
				}
			}

		});
		t.start();

	}

}
