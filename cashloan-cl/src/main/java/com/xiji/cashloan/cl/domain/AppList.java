package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 运营商报告实体
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2018-12-20 11:26:07
 */
 public class AppList implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 用户标识(关联客户主键)
    */
    private Long userId;

    /**
    * 应用列表
    */
    private String appList;

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
    * @param
    */
    public void setId(Long id){
        this.id = id;
    }

    /**
    * 获取用户标识(关联客户主键)
    *
    * @return 用户标识(关联客户主键)
    */
    public Long getUserId(){
        return userId;
    }

    /**
    * 设置用户标识(关联客户主键)
    * 
    * @param userId 要设置的用户标识(关联客户主键)
    */
    public void setUserId(Long userId){
        this.userId = userId;
    }

    /**
    * 获取应用名称
    *
    * @return 应用名称
    */
    public String getAppList(){
        return appList;
    }

    /**
    * 设置应用列表
    * 
    * @param appList 要设置的应用列表
    */
    public void setAppList(String appList){
        this.appList = appList;
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