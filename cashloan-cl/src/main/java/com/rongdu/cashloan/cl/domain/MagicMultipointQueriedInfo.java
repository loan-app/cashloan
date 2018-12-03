package com.rongdu.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 魔杖2.0报告-基础信息表实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:03
 */
 public class MagicMultipointQueriedInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 报告id
    */
    private String transId;

    /**
    * 查询日期
    */
    private String date;

    /**
    * 机构类型
    */
    private String orgType;

    /**
    * 是否本机构查询 0-否 1-是
    */
    private Integer isSelf;

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
    * 获取用户id
    *
    * @return 用户id
    */
    public Long getUserId(){
        return userId;
    }

    /**
    * 设置用户id
    * 
    * @param userId 要设置的用户id
    */
    public void setUserId(Long userId){
        this.userId = userId;
    }

    /**
    * 获取报告id
    *
    * @return 报告id
    */
    public String getTransId(){
        return transId;
    }

    /**
    * 设置报告id
    * 
    * @param transId 要设置的报告id
    */
    public void setTransId(String transId){
        this.transId = transId;
    }

    /**
    * 获取查询日期
    *
    * @return 查询日期
    */
    public String getDate(){
        return date;
    }

    /**
    * 设置查询日期
    * 
    * @param date 要设置的查询日期
    */
    public void setDate(String date){
        this.date = date;
    }

    /**
    * 获取机构类型
    *
    * @return 机构类型
    */
    public String getOrgType(){
        return orgType;
    }

    /**
    * 设置机构类型
    * 
    * @param orgType 要设置的机构类型
    */
    public void setOrgType(String orgType){
        this.orgType = orgType;
    }

    /**
    * 获取是否本机构查询 0-否 1-是
    *
    * @return 是否本机构查询 0-否 1-是
    */
    public Integer getIsSelf(){
        return isSelf;
    }

    /**
    * 设置是否本机构查询 0-否 1-是
    * 
    * @param isSelf 要设置的是否本机构查询 0-否 1-是
    */
    public void setIsSelf(Integer isSelf){
        this.isSelf = isSelf;
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