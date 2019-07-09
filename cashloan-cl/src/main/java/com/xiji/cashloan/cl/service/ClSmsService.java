package com.xiji.cashloan.cl.service;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.Sms;
import com.xiji.cashloan.core.common.service.BaseService;

import java.util.Date;
import java.util.Map;

/**
 * 短信记录Service
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface ClSmsService extends BaseService<Sms, Long>{
	
	int smsBatch(String id);

	/**
	 * 查询与最近一条短信的时间差（秒）
	 * @param phone
	 * @param type
	 * @return
	 */
	long findTimeDifference(String phone, String type);
	
	/**
	 * 根据手机号码、短信验证码类型查询今日可获取次数，防短信轰炸
	 * @param phone
	 * @param type
	 * @return
	 */
	int countDayTime(String phone, String type);

	/**
	 * 根据短信验证码类型查询多少时间内可获取次数，防短信轰炸
	 * @param type
	 * @return 返回0 不拦截 ，1拦截
	 */
	int countMinuteTime(String type);
	
	/**
	 * 发送短信
	 * @param phone
	 * @param type
	 * @return
	 */
	String sendSms(String phone, String type);

	/**
	 * 验证短信
	 * @param phone
	 * @param type
	 * @param code
	 * @param signMsg
	 * @return
	 */
	int verifySms(String phone, String type, String code);

	/**
	 *
	 * @param phone
	 * @param type
	 * @param code
	 * @return
	 */
	int verifyLoginSms(String phone, String type, String code);

	/**
	 * 查询用户
	 * @param phone 
	 * @return
	 */
	int findUser(String phone);
	
	/**
	 * 放款通知发送短信
	 * @param phone
	 * @param type
	 * @return
	 */
	String loanInform(long userId,long borrowId);
	
	/**
	 * 扣款通知发送短信
	 * @param phone
	 * @param type
	 * @return
	 */
	String repayInform(long userId,long borrowId);

	String delayPlan(long userId,long borrowId,Date date);

	/**
	 * 逾期通知短信
	 * @param parseLong
	 * @return
	 */
	int overdue(long parseLong);
	
	/**
	 * 还款提醒
	 * @param phone
	 * @param type
	 * @return
	 */
	String repayBefore(long userId,long borrowId);
	
	/**
	 * 短信列表查询
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<Sms> list(Map<String, Object> params, int currentPage, int pageSize);
	
	/**
	 * 获取短信报告结果
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	void getReportByOrderNo(String orderNo, String phone, String type);

	/**
	 * 绑卡时发送验证码之后，保存发送消息信息
	 * @param result
	 * @param phone
	 * @param type
	 * @param msg
	 */
	void saveSmsBankCard(boolean result, String phone, String type,String msg);
}
