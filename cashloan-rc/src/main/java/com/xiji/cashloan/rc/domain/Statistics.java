package com.xiji.cashloan.rc.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 风控数据统计分类实体
 * 
 * @author caitt
 * @version 1.0.0
 * @date 2017-04-13 17:52:52
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.xiji.com
 * 创新一部：rdc@xiji.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 public class Statistics implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 第三方名称
    */
    private String name;

    /**
    * 第三方简称
    */
    private String nid;

    /**
    * 第三方参数扩展字段
    */
    private String extend;

    /**
    * 状态 10，启用；20，禁用
    */
    private String state;

    /**
    * 添加时间
    */
    private Date addTime;


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
    * 获取第三方名称
    *
    * @return 第三方名称
    */
    public String getName(){
    return name;
    }

    /**
    * 设置第三方名称
    * 
    * @param name 要设置的第三方名称
    */
    public void setName(String name){
    this.name = name;
    }

    /**
    * 获取第三方简称
    *
    * @return 第三方简称
    */
    public String getNid(){
    return nid;
    }

    /**
    * 设置第三方简称
    * 
    * @param nid 要设置的第三方简称
    */
    public void setNid(String nid){
    this.nid = nid;
    }

    /**
    * 获取第三方参数扩展字段
    *
    * @return 第三方参数扩展字段
    */
    public String getExtend(){
    return extend;
    }

    /**
    * 设置第三方参数扩展字段
    * 
    * @param extend 要设置的第三方参数扩展字段
    */
    public void setExtend(String extend){
    this.extend = extend;
    }

    /**
    * 获取状态 10，启用；20，禁用
    *
    * @return 状态 10，启用；20，禁用
    */
    public String getState(){
    return state;
    }

    /**
    * 设置状态 10，启用；20，禁用
    * 
    * @param state 要设置的状态 10，启用；20，禁用
    */
    public void setState(String state){
    this.state = state;
    }

    /**
    * 获取添加时间
    *
    * @return 添加时间
    */
    public Date getAddTime(){
    return addTime;
    }

    /**
    * 设置添加时间
    * 
    * @param addTime 要设置的添加时间
    */
    public void setAddTime(Date addTime){
    this.addTime = addTime;
    }

}