package com.xiji.cashloan.cl.domain;

import com.xiji.cashloan.core.common.util.DateUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * 运营商报告链接实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-04 20:18:44
 */
 public class OperatorReportLink implements Serializable {

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
    * 任务id
    */
    private String taskId;

    /**
    * 报告链接
    */
    private String message;

    /**
    * 创建时间
    */
    private Date gmtCreate;

    /**
    * 更新时间
    */
    private Date gmtModified;

    public OperatorReportLink() {
        super();
    }

    public OperatorReportLink(Long userId, String taskId, String message) {
        super();
        this.userId = userId;
        this.taskId = taskId;
        this.message = message;
        this.gmtCreate = DateUtil.getNow();
        this.gmtModified = DateUtil.getNow();
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
    * 获取任务id
    *
    * @return 任务id
    */
    public String getTaskId(){
        return taskId;
    }

    /**
    * 设置任务id
    * 
    * @param taskId 要设置的任务id
    */
    public void setTaskId(String taskId){
        this.taskId = taskId;
    }

    /**
    * 获取报告链接
    *
    * @return 报告链接
    */
    public String getMessage(){
        return message;
    }

    /**
    * 设置报告链接
    * 
    * @param message 要设置的报告链接
    */
    public void setMessage(String message){
        this.message = message;
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
    * 获取更新时间
    *
    * @return 更新时间
    */
    public Date getGmtModified(){
        return gmtModified;
    }

    /**
    * 设置更新时间
    * 
    * @param gmtModified 要设置的更新时间
    */
    public void setGmtModified(Date gmtModified){
        this.gmtModified = gmtModified;
    }

}