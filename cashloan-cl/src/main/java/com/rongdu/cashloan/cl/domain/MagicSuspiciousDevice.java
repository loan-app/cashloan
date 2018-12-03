package com.rongdu.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 魔杖2.0报告-基础信息表实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:04
 */
 public class MagicSuspiciousDevice implements Serializable {

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
    * 使用过的设备数
    */
    private Integer otherDevicesCnt;

    /**
    * 手机号码使用过的设备数
    */
    private Integer mobileOtherDevicesCnt;

    /**
    * 身份证号码使用过的设备数
    */
    private Integer idcardOtherDevicesCnt;

    /**
    * 提供数据的机构数
    */
    private Integer informationSourcesCnt;

    /**
    * 使用过的设备上登陆的其他姓名数
    */
    private Integer otherNamesCnt;

    /**
    * 使用过的设备上登陆的其他手机号码数
    */
    private Integer otherMobilesCnt;

    /**
    * 使用过的设备上登陆的其他触黑手机号码数
    */
    private Integer otherMobilesBlackCnt;

    /**
    * 使用过的设备上登陆的其他身份证号码数
    */
    private Integer otherIdcardsCnt;

    /**
    * 使用过的设备上登陆的其他触黑身份证号码数
    */
    private Integer otherIdcardsBlackCnt;

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
    * 获取使用过的设备数
    *
    * @return 使用过的设备数
    */
    public Integer getOtherDevicesCnt(){
        return otherDevicesCnt;
    }

    /**
    * 设置使用过的设备数
    * 
    * @param otherDevicesCnt 要设置的使用过的设备数
    */
    public void setOtherDevicesCnt(Integer otherDevicesCnt){
        this.otherDevicesCnt = otherDevicesCnt;
    }

    /**
    * 获取手机号码使用过的设备数
    *
    * @return 手机号码使用过的设备数
    */
    public Integer getMobileOtherDevicesCnt(){
        return mobileOtherDevicesCnt;
    }

    /**
    * 设置手机号码使用过的设备数
    * 
    * @param mobileOtherDevicesCnt 要设置的手机号码使用过的设备数
    */
    public void setMobileOtherDevicesCnt(Integer mobileOtherDevicesCnt){
        this.mobileOtherDevicesCnt = mobileOtherDevicesCnt;
    }

    /**
    * 获取身份证号码使用过的设备数
    *
    * @return 身份证号码使用过的设备数
    */
    public Integer getIdcardOtherDevicesCnt(){
        return idcardOtherDevicesCnt;
    }

    /**
    * 设置身份证号码使用过的设备数
    * 
    * @param idcardOtherDevicesCnt 要设置的身份证号码使用过的设备数
    */
    public void setIdcardOtherDevicesCnt(Integer idcardOtherDevicesCnt){
        this.idcardOtherDevicesCnt = idcardOtherDevicesCnt;
    }

    /**
    * 获取提供数据的机构数
    *
    * @return 提供数据的机构数
    */
    public Integer getInformationSourcesCnt(){
        return informationSourcesCnt;
    }

    /**
    * 设置提供数据的机构数
    * 
    * @param informationSourcesCnt 要设置的提供数据的机构数
    */
    public void setInformationSourcesCnt(Integer informationSourcesCnt){
        this.informationSourcesCnt = informationSourcesCnt;
    }

    /**
    * 获取使用过的设备上登陆的其他姓名数
    *
    * @return 使用过的设备上登陆的其他姓名数
    */
    public Integer getOtherNamesCnt(){
        return otherNamesCnt;
    }

    /**
    * 设置使用过的设备上登陆的其他姓名数
    * 
    * @param otherNamesCnt 要设置的使用过的设备上登陆的其他姓名数
    */
    public void setOtherNamesCnt(Integer otherNamesCnt){
        this.otherNamesCnt = otherNamesCnt;
    }

    /**
    * 获取使用过的设备上登陆的其他手机号码数
    *
    * @return 使用过的设备上登陆的其他手机号码数
    */
    public Integer getOtherMobilesCnt(){
        return otherMobilesCnt;
    }

    /**
    * 设置使用过的设备上登陆的其他手机号码数
    * 
    * @param otherMobilesCnt 要设置的使用过的设备上登陆的其他手机号码数
    */
    public void setOtherMobilesCnt(Integer otherMobilesCnt){
        this.otherMobilesCnt = otherMobilesCnt;
    }

    /**
    * 获取使用过的设备上登陆的其他触黑手机号码数
    *
    * @return 使用过的设备上登陆的其他触黑手机号码数
    */
    public Integer getOtherMobilesBlackCnt(){
        return otherMobilesBlackCnt;
    }

    /**
    * 设置使用过的设备上登陆的其他触黑手机号码数
    * 
    * @param otherMobilesBlackCnt 要设置的使用过的设备上登陆的其他触黑手机号码数
    */
    public void setOtherMobilesBlackCnt(Integer otherMobilesBlackCnt){
        this.otherMobilesBlackCnt = otherMobilesBlackCnt;
    }

    /**
    * 获取使用过的设备上登陆的其他身份证号码数
    *
    * @return 使用过的设备上登陆的其他身份证号码数
    */
    public Integer getOtherIdcardsCnt(){
        return otherIdcardsCnt;
    }

    /**
    * 设置使用过的设备上登陆的其他身份证号码数
    * 
    * @param otherIdcardsCnt 要设置的使用过的设备上登陆的其他身份证号码数
    */
    public void setOtherIdcardsCnt(Integer otherIdcardsCnt){
        this.otherIdcardsCnt = otherIdcardsCnt;
    }

    /**
    * 获取使用过的设备上登陆的其他触黑身份证号码数
    *
    * @return 使用过的设备上登陆的其他触黑身份证号码数
    */
    public Integer getOtherIdcardsBlackCnt(){
        return otherIdcardsBlackCnt;
    }

    /**
    * 设置使用过的设备上登陆的其他触黑身份证号码数
    * 
    * @param otherIdcardsBlackCnt 要设置的使用过的设备上登陆的其他触黑身份证号码数
    */
    public void setOtherIdcardsBlackCnt(Integer otherIdcardsBlackCnt){
        this.otherIdcardsBlackCnt = otherIdcardsBlackCnt;
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