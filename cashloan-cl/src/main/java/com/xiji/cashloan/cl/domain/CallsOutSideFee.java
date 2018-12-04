package com.xiji.cashloan.cl.domain;

import com.xiji.cashloan.core.common.util.DateUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * 调用外部数据收费信息实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-04 19:20:29
 */
 public class CallsOutSideFee implements Serializable {

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
    * 调用类型 1-运营商 2-魔杖反欺诈 3-魔杖多头 4-魔杖黑灰名单 5-魔杖贷后行为
    */
    private Integer type;

    /**
    * 收取费用
    */
    private Double fee;

    /**
    * 创建时间
    */
    private Date gmtCreate;

    /**
    * 更新时间
    */
    private Date gmtModified;

    public CallsOutSideFee() {
        super();
    }

    public CallsOutSideFee(Long userId, String taskId, Integer type, Double fee) {
        super();
        this.userId = userId;
        this.taskId = taskId;
        this.type = type;
        this.fee = fee;
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
    * 获取调用类型 1-运营商 2-魔杖反欺诈 3-魔杖多头 4-魔杖黑灰名单 5-魔杖贷后行为
    *
    * @return 调用类型 1-运营商 2-魔杖反欺诈 3-魔杖多头 4-魔杖黑灰名单 5-魔杖贷后行为
    */
    public Integer getType(){
        return type;
    }

    /**
    * 设置调用类型 1-运营商 2-魔杖反欺诈 3-魔杖多头 4-魔杖黑灰名单 5-魔杖贷后行为
    * 
    * @param type 要设置的调用类型 1-运营商 2-魔杖反欺诈 3-魔杖多头 4-魔杖黑灰名单 5-魔杖贷后行为
    */
    public void setType(Integer type){
        this.type = type;
    }

    /**
    * 获取收取费用
    *
    * @return 收取费用
    */
    public Double getFee(){
        return fee;
    }

    /**
    * 设置收取费用
    * 
    * @param fee 要设置的收取费用
    */
    public void setFee(Double fee){
        this.fee = fee;
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