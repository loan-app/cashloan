package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 黑名单实体
 * 
 * @author king
 * @version 1.0.0
 * @date 2018-12-10 16:29:50
 */
 public class Blacklist implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 类别：01-身份证、02-手机号、等
    */
    private String dimensionkey;

    /**
    * 类别对应的值
    */
    private String dimensionvalue;

    /**
    * 来源
    */
    private String source;

    /**
    * 状态 0:正常，1:删除
    */
    private Integer status;

    /**
    * 
    */
    private Date createtime;

    /**
    * 
    */
    private Date lastmodifytime;


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
    * 获取类别：01-身份证、02-手机号、等
    *
    * @return 类别：01-身份证、02-手机号、等
    */
    public String getDimensionkey(){
        return dimensionkey;
    }

    /**
    * 设置类别：01-身份证、02-手机号、等
    * 
    * @param dimensionkey 要设置的类别：01-身份证、02-手机号、等
    */
    public void setDimensionkey(String dimensionkey){
        this.dimensionkey = dimensionkey;
    }

    /**
    * 获取类别对应的值
    *
    * @return 类别对应的值
    */
    public String getDimensionvalue(){
        return dimensionvalue;
    }

    /**
    * 设置类别对应的值
    * 
    * @param dimensionvalue 要设置的类别对应的值
    */
    public void setDimensionvalue(String dimensionvalue){
        this.dimensionvalue = dimensionvalue;
    }

    /**
    * 获取来源
    *
    * @return 来源
    */
    public String getSource(){
        return source;
    }

    /**
    * 设置来源
    * 
    * @param source 要设置的来源
    */
    public void setSource(String source){
        this.source = source;
    }

    /**
    * 获取状态 0:正常，1:删除
    *
    * @return 状态 0:正常，1:删除
    */
    public Integer getStatus(){
        return status;
    }

    /**
    * 设置状态 0:正常，1:删除
    * 
    * @param status 要设置的状态 0:正常，1:删除
    */
    public void setStatus(Integer status){
        this.status = status;
    }

    /**
    * 获取
    *
    * @return 
    */
    public Date getCreatetime(){
        return createtime;
    }

    /**
    * 设置
    * 
    * @param createtime 要设置的
    */
    public void setCreatetime(Date createtime){
        this.createtime = createtime;
    }

    /**
    * 获取
    *
    * @return 
    */
    public Date getLastmodifytime(){
        return lastmodifytime;
    }

    /**
    * 设置
    * 
    * @param lastmodifytime 要设置的
    */
    public void setLastmodifytime(Date lastmodifytime){
        this.lastmodifytime = lastmodifytime;
    }

}