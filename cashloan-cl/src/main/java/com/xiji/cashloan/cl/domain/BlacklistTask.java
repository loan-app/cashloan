package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 黑名单任务实体
 * 
 * @author king
 * @version 1.0.0
 * @date 2018-12-20 15:44:09
 */
 public class BlacklistTask implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 任务名称
    */
    private String taskName;

    /**
    * 任务类型
    */
    private String taskType;

    /**
    * 任务版本
    */
    private Integer taskVersion;

    /**
    * 任务代码数据
    */
    private String taskData;

    /**
    * 备注
    */
    private String remark;

    /**
    * 是否有效：1-有效;2-无效
    */
    private Integer yn;

    /**
    * 
    */
    private Date createTime;

    /**
    * 
    */
    private Date lastModifyTime;


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
    * 获取任务名称
    *
    * @return 任务名称
    */
    public String getTaskName(){
        return taskName;
    }

    /**
    * 设置任务名称
    * 
    * @param taskName 要设置的任务名称
    */
    public void setTaskName(String taskName){
        this.taskName = taskName;
    }

    /**
    * 获取任务类型
    *
    * @return 任务类型
    */
    public String getTaskType(){
        return taskType;
    }

    /**
    * 设置任务类型
    * 
    * @param taskType 要设置的任务类型
    */
    public void setTaskType(String taskType){
        this.taskType = taskType;
    }

    /**
    * 获取任务版本
    *
    * @return 任务版本
    */
    public Integer getTaskVersion(){
        return taskVersion;
    }

    /**
    * 设置任务版本
    * 
    * @param taskVersion 要设置的任务版本
    */
    public void setTaskVersion(Integer taskVersion){
        this.taskVersion = taskVersion;
    }

    /**
    * 获取任务代码数据
    *
    * @return 任务代码数据
    */
    public String getTaskData(){
        return taskData;
    }

    /**
    * 设置任务代码数据
    * 
    * @param taskData 要设置的任务代码数据
    */
    public void setTaskData(String taskData){
        this.taskData = taskData;
    }

    /**
    * 获取备注
    *
    * @return 备注
    */
    public String getRemark(){
        return remark;
    }

    /**
    * 设置备注
    * 
    * @param remark 要设置的备注
    */
    public void setRemark(String remark){
        this.remark = remark;
    }

    /**
    * 获取是否有效：1-有效;0-无效
    *
    * @return 是否有效：1-有效;0-无效
    */
    public Integer getYn(){
        return yn;
    }

    /**
    * 设置是否有效：1-有效;0-无效
    * 
    * @param yn 要设置的是否有效：1-有效;0-无效
    */
    public void setYn(Integer yn){
        this.yn = yn;
    }

    /**
    * 获取
    *
    * @return 
    */
    public Date getCreateTime(){
        return createTime;
    }

    /**
    * 设置
    * 
    * @param createTime 要设置的
    */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    /**
    * 获取
    *
    * @return 
    */
    public Date getLastModifyTime(){
        return lastModifyTime;
    }

    /**
    * 设置
    * 
    * @param lastModifyTime 要设置的
    */
    public void setLastModifyTime(Date lastModifyTime){
        this.lastModifyTime = lastModifyTime;
    }

}