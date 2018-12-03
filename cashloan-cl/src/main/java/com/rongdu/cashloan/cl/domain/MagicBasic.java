package com.rongdu.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 魔杖2.0报告-基础信息实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:26:56
 */
 public class MagicBasic implements Serializable {

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
    * 用户手机号
    */
    private String mobile;

    /**
    * 姓名
    */
    private String userName;

    /**
    * 身份证号
    */
    private String idcard;

    /**
    * 身份证归属地 省/市/区(县)
    */
    private String idcardLocation;

    /**
    * 报告id
    */
    private String transId;

    /**
    * 年龄
    */
    private Integer age;

    /**
    * 性别
    */
    private String gender;

    /**
    * 邮箱
    */
    private String email;

    /**
    * 学历 0-未知 1-专科 2-本科 3-硕士 4-博士 5-预科 6-夜大/电大/函大
    */
    private Integer educationLevel;

    /**
    * 是否毕业 0-未毕业 1-毕业
    */
    private Integer educationIsGraduation;

    /**
    * 是否导入过运营商数据 0-否 1-是
    */
    private Integer hasCarrierData;

    /**
    * 是否导入过网银信用卡数据 0-否 1-是
    */
    private Integer hasOnlinebankData;

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
    * 获取用户手机号
    *
    * @return 用户手机号
    */
    public String getMobile(){
        return mobile;
    }

    /**
    * 设置用户手机号
    * 
    * @param mobile 要设置的用户手机号
    */
    public void setMobile(String mobile){
        this.mobile = mobile;
    }

    /**
    * 获取姓名
    *
    * @return 姓名
    */
    public String getUserName(){
        return userName;
    }

    /**
    * 设置姓名
    * 
    * @param userName 要设置的姓名
    */
    public void setUserName(String userName){
        this.userName = userName;
    }

    /**
    * 获取身份证号
    *
    * @return 身份证号
    */
    public String getIdcard(){
        return idcard;
    }

    /**
    * 设置身份证号
    * 
    * @param idcard 要设置的身份证号
    */
    public void setIdcard(String idcard){
        this.idcard = idcard;
    }

    /**
    * 获取身份证归属地 省/市/区(县)
    *
    * @return 身份证归属地 省/市/区(县)
    */
    public String getIdcardLocation(){
        return idcardLocation;
    }

    /**
    * 设置身份证归属地 省/市/区(县)
    * 
    * @param idcardLocation 要设置的身份证归属地 省/市/区(县)
    */
    public void setIdcardLocation(String idcardLocation){
        this.idcardLocation = idcardLocation;
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
    * 获取年龄
    *
    * @return 年龄
    */
    public Integer getAge(){
        return age;
    }

    /**
    * 设置年龄
    * 
    * @param age 要设置的年龄
    */
    public void setAge(Integer age){
        this.age = age;
    }

    /**
    * 获取性别
    *
    * @return 性别
    */
    public String getGender(){
        return gender;
    }

    /**
    * 设置性别
    * 
    * @param gender 要设置的性别
    */
    public void setGender(String gender){
        this.gender = gender;
    }

    /**
    * 获取邮箱
    *
    * @return 邮箱
    */
    public String getEmail(){
        return email;
    }

    /**
    * 设置邮箱
    * 
    * @param email 要设置的邮箱
    */
    public void setEmail(String email){
        this.email = email;
    }

    /**
    * 获取学历 0-未知 1-专科 2-本科 3-硕士 4-博士 5-预科 6-夜大/电大/函大
    *
    * @return 学历 0-未知 1-专科 2-本科 3-硕士 4-博士 5-预科 6-夜大/电大/函大
    */
    public Integer getEducationLevel(){
        return educationLevel;
    }

    /**
    * 设置学历 0-未知 1-专科 2-本科 3-硕士 4-博士 5-预科 6-夜大/电大/函大
    * 
    * @param educationLevel 要设置的学历 0-未知 1-专科 2-本科 3-硕士 4-博士 5-预科 6-夜大/电大/函大
    */
    public void setEducationLevel(Integer educationLevel){
        this.educationLevel = educationLevel;
    }

    /**
    * 获取是否毕业 0-未毕业 1-毕业
    *
    * @return 是否毕业 0-未毕业 1-毕业
    */
    public Integer getEducationIsGraduation(){
        return educationIsGraduation;
    }

    /**
    * 设置是否毕业 0-未毕业 1-毕业
    * 
    * @param educationIsGraduation 要设置的是否毕业 0-未毕业 1-毕业
    */
    public void setEducationIsGraduation(Integer educationIsGraduation){
        this.educationIsGraduation = educationIsGraduation;
    }

    /**
    * 获取是否导入过运营商数据 0-否 1-是
    *
    * @return 是否导入过运营商数据 0-否 1-是
    */
    public Integer getHasCarrierData(){
        return hasCarrierData;
    }

    /**
    * 设置是否导入过运营商数据 0-否 1-是
    * 
    * @param hasCarrierData 要设置的是否导入过运营商数据 0-否 1-是
    */
    public void setHasCarrierData(Integer hasCarrierData){
        this.hasCarrierData = hasCarrierData;
    }

    /**
    * 获取是否导入过网银信用卡数据 0-否 1-是
    *
    * @return 是否导入过网银信用卡数据 0-否 1-是
    */
    public Integer getHasOnlinebankData(){
        return hasOnlinebankData;
    }

    /**
    * 设置是否导入过网银信用卡数据 0-否 1-是
    * 
    * @param hasOnlinebankData 要设置的是否导入过网银信用卡数据 0-否 1-是
    */
    public void setHasOnlinebankData(Integer hasOnlinebankData){
        this.hasOnlinebankData = hasOnlinebankData;
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