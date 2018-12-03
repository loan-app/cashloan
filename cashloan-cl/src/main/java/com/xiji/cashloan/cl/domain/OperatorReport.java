package com.xiji.cashloan.cl.domain;

import com.xiji.cashloan.core.common.util.DateUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * 运营商报告实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-11-27 10:29:41
 */
 public class OperatorReport implements Serializable {

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
    * 订单号
    */
    private String taskId;

    /**
    * 运营商报告内容
    */
    private String report;

    /**
    * 创建时间
    */
    private Date gmtCreate;

    /**
    * 修改时间
    */
    private Date gmtModified;

    public OperatorReport() {
        super();
    }

    public OperatorReport(Long reqLogId, String taskId, String report) {
        super();
        this.reqLogId = reqLogId;
        this.taskId = taskId;
        this.report = report;
        this.gmtCreate = DateUtil.getNow();
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
    * 
    * @param 要设置的主键Id
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
    * @param taskId 要设置的订单号
    */
    public void setTaskId(String taskId){
        this.taskId = taskId;
    }

    /**
    * 获取运营商报告内容
    *
    * @return 运营商报告内容
    */
    public String getReport(){
        return report;
    }

    /**
    * 设置运营商报告内容
    * 
    * @param report 要设置的运营商报告内容
    */
    public void setReport(String report){
        this.report = report;
    }

    /**
    * 获取创建时间
    *
    * @return 创建时间
    */
    public Date getGmtCreate(){
        return gmtCreate;
    }

    /**
    * 设置创建时间
    * 
    * @param gmtCreate 要设置的创建时间
    */
    public void setGmtCreate(Date gmtCreate){
        this.gmtCreate = gmtCreate;
    }

    /**
    * 获取修改时间
    *
    * @return 修改时间
    */
    public Date getGmtModified(){
        return gmtModified;
    }

    /**
    * 设置修改时间
    * 
    * @param gmtModified 要设置的修改时间
    */
    public void setGmtModified(Date gmtModified){
        this.gmtModified = gmtModified;
    }

}