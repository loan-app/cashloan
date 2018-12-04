package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

import tool.util.DateUtil;

/**
 * 运营商认证中间表实体
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2018/11/29
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 public class OperatorReqLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 上树运营商*/
	public static final String BUSINESS_TYPE_SS = "10";

	/** 发起请求 */
	public static final String STATE_INITIATE_REQ = "0";
	/** 任务创建成功 */
	public static final String STATE_CREATE_SUCCESS = "1";
	/** 授权登录成功 */
	public static final String STATE_LOGIN_SUCCESS = "2";
	/** 授权登录失败 */
	public static final String STATE_LOGIN_FAILED = "3";
	/** 采集成功 */
	public static final String STATE_COLLECT_SUCCESS = "4";
	/** 采集失败 */
	public static final String STATE_COLLECT_FAILED = "5";
	/** 报告生成成功 */
	public static final String STATE_REPORT_SUCCESS = "6";
	/** 报告生成失败 */
	public static final String STATE_REPORT_FAILED = "7";

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 用户标识
    */
    private Long userId;

    /**
    * 任务Id
    */
    private String taskId;
    
    /**
     * 认证状态
     */
    private String taskState;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
	 * 同步相应时间
	 */
	private Date respTime;

	/**
	 * 结果描述
	 */
	private String message;

    public OperatorReqLog(){
    	super();
    }

    public OperatorReqLog(long userId, String taskId, String taskState){
    	super();
    	this.userId = userId;
    	this.taskId = taskId;
    	this.taskState = taskState;
    	this.createTime = DateUtil.getNow();
    }

	/**
	 * 获取主键Id
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置主键Id
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取用户标识
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置用户标识
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取任务id
	 * @return taskId
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * 设置任务id
	 * @param taskId
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/**
	 * 获取状态码
	 * @return taskState
	 */
	public String getTaskState() {
		return taskState;
	}

	/**
	 * 设置状态码
	 * @param taskState
	 */
	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}

	/**
	 * 获取创建时间
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** 
	 * 获取同步回调时间
	 * @return respTime
	 */
	public Date getRespTime() {
		return respTime;
	}

	/** 
	 * 设置同步回调时间
	 * @param respTime
	 */
	public void setRespTime(Date respTime) {
		this.respTime = respTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}