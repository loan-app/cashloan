package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 魔杖2.0报告-基础信息表实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:05
 */
 public class MagicSuspiciousOtherMobile implements Serializable {

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
    * 手机号
    */
    private String mobile;

    /**
    * 手机号归属地
    */
    private String otherMobileLocation;

    /**
    * 运营商
    */
    private String carrier;

    /**
    * 手机号是否命中黑灰名单 0-否 1-是
    */
    private Integer isblack;

    /**
    * 最后使用时间
    */
    private String latestUsedTime;

    /**
    * 存疑类型 idcard-身份证 device-设备
    */
    private String type;

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
    * 获取手机号
    *
    * @return 手机号
    */
    public String getMobile(){
        return mobile;
    }

    /**
    * 设置手机号
    * 
    * @param mobile 要设置的手机号
    */
    public void setMobile(String mobile){
        this.mobile = mobile;
    }

    /**
    * 获取手机号归属地
    *
    * @return 手机号归属地
    */
    public String getOtherMobileLocation(){
        return otherMobileLocation;
    }

    /**
    * 设置手机号归属地
    * 
    * @param otherMobileLocation 要设置的手机号归属地
    */
    public void setOtherMobileLocation(String otherMobileLocation){
        this.otherMobileLocation = otherMobileLocation;
    }

    /**
    * 获取运营商
    *
    * @return 运营商
    */
    public String getCarrier(){
        return carrier;
    }

    /**
    * 设置运营商
    * 
    * @param carrier 要设置的运营商
    */
    public void setCarrier(String carrier){
        this.carrier = carrier;
    }

    /**
    * 获取手机号是否命中黑灰名单 0-否 1-是
    *
    * @return 手机号是否命中黑灰名单 0-否 1-是
    */
    public Integer getIsblack(){
        return isblack;
    }

    /**
    * 设置手机号是否命中黑灰名单 0-否 1-是
    * 
    * @param isblack 要设置的手机号是否命中黑灰名单 0-否 1-是
    */
    public void setIsblack(Integer isblack){
        this.isblack = isblack;
    }

    /**
    * 获取最后使用时间
    *
    * @return 最后使用时间
    */
    public String getLatestUsedTime(){
        return latestUsedTime;
    }

    /**
    * 设置最后使用时间
    * 
    * @param latestUsedTime 要设置的最后使用时间
    */
    public void setLatestUsedTime(String latestUsedTime){
        this.latestUsedTime = latestUsedTime;
    }

    /**
    * 获取存疑类型 idcard-身份证 device-设备
    *
    * @return 存疑类型 idcard-身份证 device-设备
    */
    public String getType(){
        return type;
    }

    /**
    * 设置存疑类型 idcard-身份证 device-设备
    * 
    * @param type 要设置的存疑类型 idcard-身份证 device-设备
    */
    public void setType(String type){
        this.type = type;
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