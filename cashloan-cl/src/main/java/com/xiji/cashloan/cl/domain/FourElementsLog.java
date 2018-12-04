package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 四要素认证记录实体
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2018/12/03
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.xiji.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 public class FourElementsLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 用户标识
    */
    private Long userId;

    /**
    * 用户姓名
    */
    private String userName;

    /**
    * 身份证号
    */
    private String idCard;

    /**
    * 银行卡号
    */
    private String cardNo;

    /**
    * 预留手机号
    */
    private String phone;

    /**
    * 结果
    */
    private String result;

    /**
    * 返回码 200 为请求成功，并获取相关数据，400 为某些原因导请求失败，如参数格式错误、无相关数据等，500 为服务器内部错误，401 为无权限请求。
    */
    private String code;
    
    /**
     *验证状态   SAME 验证通过, DIFFERENT 验证不通过, ACCOUNTNO_UNABLE_VERITY 无法验证
     */
    private String checkStatus;
    
    /**
     * 创建时间
     */
    private Date createTime;


    /**
    * 获取主键Id
    *
    * @return id
    */
    public Long getId(){
        return id;
    }

    /**
    * 设置主键Id
    * 
    * @param 要设置的主键Id
    */
    public void setId(Long id){
        this.id = id;
    }

    /**
    * 获取用户标识
    *
    * @return 用户标识
    */
    public Long getUserId(){
        return userId;
    }

    /**
    * 设置用户标识
    * 
    * @param userId 要设置的用户标识
    */
    public void setUserId(Long userId){
        this.userId = userId;
    }

    /**
    * 获取用户姓名
    *
    * @return 用户姓名
    */
    public String getUserName(){
        return userName;
    }

    /**
    * 设置用户姓名
    * 
    * @param userName 要设置的用户姓名
    */
    public void setUserName(String userName){
        this.userName = userName;
    }

    /**
    * 获取身份证号
    *
    * @return 身份证号
    */
    public String getIdCard(){
        return idCard;
    }

    /**
    * 设置身份证号
    * 
    * @param idCard 要设置的身份证号
    */
    public void setIdCard(String idCard){
        this.idCard = idCard;
    }

    /**
    * 获取银行卡号
    *
    * @return 银行卡号
    */
    public String getCardNo(){
        return cardNo;
    }

    /**
    * 设置银行卡号
    * 
    * @param cardNo 要设置的银行卡号
    */
    public void setCardNo(String cardNo){
        this.cardNo = cardNo;
    }

    /**
    * 获取预留手机号
    *
    * @return 预留手机号
    */
    public String getPhone(){
        return phone;
    }

    /**
    * 设置预留手机号
    * 
    * @param phone 要设置的预留手机号
    */
    public void setPhone(String phone){
        this.phone = phone;
    }

    /**
    * 获取结果
    *
    * @return 结果
    */
    public String getResult(){
        return result;
    }

    /**
    * 设置结果
    * 
    * @param result 要设置的结果
    */
    public void setResult(String result){
        this.result = result;
    }

    /**
    * 获取返回码 200 为请求成功，并获取相关数据，400 为某些原因导请求失败，如参数格式错误、无相关数据等，500 为服务器内部错误，401 为无权限请求。
    *
    * @return 返回码 200 为请求成功，并获取相关数据，400 为某些原因导请求失败，如参数格式错误、无相关数据等，500 为服务器内部错误，401 为无权限请求。
    */
    public String getCode(){
        return code;
    }

    /**
    * 设置返回码 200 为请求成功，并获取相关数据，400 为某些原因导请求失败，如参数格式错误、无相关数据等，500 为服务器内部错误，401 为无权限请求。
    * 
    * @param checkStatus 要设置的返回码 200 为请求成功，并获取相关数据，400 为某些原因导请求失败，如参数格式错误、无相关数据等，500 为服务器内部错误，401 为无权限请求。
    */
    public void setCode(String code){
        this.code = code;
    }

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
}