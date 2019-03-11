package com.xiji.cashloan.cl.model;

import com.xiji.cashloan.cl.domain.UserAuth;

import java.util.Date;

/**
 * @author wnb
 * @date 2018/11/30
 * @version 1.0.0
 */
public class UserAuthModel extends UserAuth {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	/** 认证状态 - 未认证/未完善 */
	public static final String STATE_NOT_CERTIFIED = "10";

	/** 认证状态 - 认证中/完善中*/
	public static final String STATE_ERTIFICATION = "20";

	/** 认证状态 - 已认证/已完善*/
	public static final String STATE_VERIFIED = "30";



	/**
	 * 登录名
	 */
	private String loginName;

	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 手机号码
	 */
	private String phone;

	/**
	 * 注册时间
	 */
	private Date registTime;

	/**
	 * 身份认证状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
	 */
	private String idStateStr;

	/**
	 * 紧急联系人状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
	 */
	private String contactStateStr;

	/**
	 * 银行卡状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
	 */
	private String bankCardStateStr;

	/**
	 * 手机运营商认证状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
	 */
	private String phoneStateStr;


	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public String getIdStateStr() {
		return idStateStr;
	}

	public void setIdStateStr(String idStateStr) {
		this.idStateStr = idStateStr;
	}

	public String getContactStateStr() {
		return contactStateStr;
	}

	public void setContactStateStr(String contactStateStr) {
		this.contactStateStr = contactStateStr;
	}

	public String getBankCardStateStr() {
		return bankCardStateStr;
	}

	public void setBankCardStateStr(String bankCardStateStr) {
		this.bankCardStateStr = bankCardStateStr;
	}

	public String getPhoneStateStr() {
		return phoneStateStr;
	}

	public void setPhoneStateStr(String phoneStateStr) {
		this.phoneStateStr = phoneStateStr;
	}
}
