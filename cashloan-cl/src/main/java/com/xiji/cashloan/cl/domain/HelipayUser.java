package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 合利宝用户注册信息实体
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2019-07-30 15:31:02
 */
 public class HelipayUser implements Serializable {

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
    * 合利宝用户编号
    */
    private String helipayUserId;

    /**
    * 用户状态： INIT-入网中,AUDITING-申请中,AVAILABLE-正常,REFUSED-拒绝,CANCELLED-注销
    */
    private String userStatus;

    /**
    * 用户身份证正面上传状态：UPLOADED-已上传，NOT_UPLOADED-未上传
    */
    private String frontCredentialStatus;

    /**
    * 用户身份证反面上传状态：UPLOADED-已上传，NOT_UPLOADED-未上传
    */
    private String backCredentialStatus;

    /**
    * 创建时间
    */
    private Date gmtCreate;

    /**
    * 修改时间
    */
    private Date gmtModified;


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
    * @param id
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
    * 获取合利宝用户编号
    *
    * @return 合利宝用户编号
    */
    public String getHelipayUserId(){
        return helipayUserId;
    }

    /**
    * 设置合利宝用户编号
    * 
    * @param helipayUserId 要设置的合利宝用户编号
    */
    public void setHelipayUserId(String helipayUserId){
        this.helipayUserId = helipayUserId;
    }

    /**
    * 获取用户状态： INIT-入网中,AUDITING-申请中,AVAILABLE-正常,REFUSED-拒绝,CANCELLED-注销
    *
    * @return 用户状态： INIT-入网中,AUDITING-申请中,AVAILABLE-正常,REFUSED-拒绝,CANCELLED-注销
    */
    public String getUserStatus(){
        return userStatus;
    }

    /**
    * 设置用户状态： INIT-入网中,AUDITING-申请中,AVAILABLE-正常,REFUSED-拒绝,CANCELLED-注销
    * 
    * @param userStatus 要设置的用户状态： INIT-入网中,AUDITING-申请中,AVAILABLE-正常,REFUSED-拒绝,CANCELLED-注销
    */
    public void setUserStatus(String userStatus){
        this.userStatus = userStatus;
    }

    /**
    * 获取用户身份证正面上传状态：UPLOADED-已上传，NOT_UPLOADED-未上传
    *
    * @return 用户身份证正面上传状态：UPLOADED-已上传，NOT_UPLOADED-未上传
    */
    public String getFrontCredentialStatus(){
        return frontCredentialStatus;
    }

    /**
    * 设置用户身份证正面上传状态：UPLOADED-已上传，NOT_UPLOADED-未上传
    * 
    * @param frontCredentialStatus 要设置的用户身份证正面上传状态：UPLOADED-已上传，NOT_UPLOADED-未上传
    */
    public void setFrontCredentialStatus(String frontCredentialStatus){
        this.frontCredentialStatus = frontCredentialStatus;
    }

    /**
    * 获取用户身份证反面上传状态：UPLOADED-已上传，NOT_UPLOADED-未上传
    *
    * @return 用户身份证反面上传状态：UPLOADED-已上传，NOT_UPLOADED-未上传
    */
    public String getBackCredentialStatus(){
        return backCredentialStatus;
    }

    /**
    * 设置用户身份证反面上传状态：UPLOADED-已上传，NOT_UPLOADED-未上传
    * 
    * @param backCredentialStatus 要设置的用户身份证反面上传状态：UPLOADED-已上传，NOT_UPLOADED-未上传
    */
    public void setBackCredentialStatus(String backCredentialStatus){
        this.backCredentialStatus = backCredentialStatus;
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
    * 获取修改时间
    *
    * @return 修改时间
    */
    public Date getGmtModified(){
        return gmtModified;
    }

    /**
    * 设置修改时间
    * 
    * @param gmtModified 要设置的修改时间
    */
    public void setGmtModified(Date gmtModified){
        this.gmtModified = gmtModified;
    }

}