package com.rongdu.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

import tool.util.DateUtil;

/**
 * 运营商认证通知详情表实体
 * 
 * @author xx
 * @version 1.0.0
 * @date 2017-05-17 12:38:22
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 public class OperatorRespDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 请求记录标识
    */
    private Long reqLogId;

    /**
    * 任务id
    */
    private String taskId;

    /**
    * 运营商认证数据
    */
    private String operatorData;

    /**
    * 创建时间
    */
    private Date createTime;
    
    public OperatorRespDetail(){
    	super();
    }
    
    public OperatorRespDetail(long reqLogId, String taskId, String notifyParams){
    	super();
    	this.reqLogId = reqLogId;
    	this.taskId = taskId;
    	this.operatorData = notifyParams;
    	this.createTime = DateUtil.getNow();
    }


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
    */
    public void setId(Long id){
        this.id = id;
    }

    /**
    * 获取请求记录标识
    *
    * @return 请求记录标识
    */
    public Long getReqLogId(){
        return reqLogId;
    }

    /**
    * 设置请求记录标识
    * 
    * @param reqLogId 要设置的请求记录标识
    */
    public void setReqLogId(Long reqLogId){
        this.reqLogId = reqLogId;
    }

    /**
    * 获取订单号
    *
    * @return 订单号
    */
    public String getTaskId(){
        return taskId;
    }

    /**
    * 设置订单号
    * 
    * @param taskId
    */
    public void setTaskId(String taskId){
        this.taskId = taskId;
    }

    /**
    * 获取运营商认证数据
    *
    * @return 异步通知结果
    */
    public String getOperatorData(){
        return operatorData;
    }

    /**
    * 设置运营商认证数据
    * 
    * @param operatorData 要设置的运营商认证数据
    */
    public void setOperatorData(String operatorData){
        this.operatorData = operatorData;
    }

    /**
    * 获取创建时间
    *
    * @return 创建时间
    */
    public Date getCreateTime(){
        return createTime;
    }

    /**
    * 设置创建时间
    * 
    * @param createTime 要设置的创建时间
    */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

}