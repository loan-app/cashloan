package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 魔杖2.0报告-基础信息表实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:04
 */
 public class MagicSuspiciousMobile implements Serializable {

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
    * 手机存疑姓名数
    */
    private Integer otherNamesCnt;

    /**
    * 手机存疑身份证数
    */
    private Integer otherIdcardsCnt;

    /**
    * 手机存疑触黑身份证数
    */
    private Integer otherIdcardsBlackCnt;

    /**
    * 提供数据的机构数
    */
    private Integer informationSourcesCnt;

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
    * 获取手机存疑姓名数
    *
    * @return 手机存疑姓名数
    */
    public Integer getOtherNamesCnt(){
        return otherNamesCnt;
    }

    /**
    * 设置手机存疑姓名数
    * 
    * @param otherNamesCnt 要设置的手机存疑姓名数
    */
    public void setOtherNamesCnt(Integer otherNamesCnt){
        this.otherNamesCnt = otherNamesCnt;
    }

    /**
    * 获取手机存疑身份证数
    *
    * @return 手机存疑身份证数
    */
    public Integer getOtherIdcardsCnt(){
        return otherIdcardsCnt;
    }

    /**
    * 设置手机存疑身份证数
    * 
    * @param otherIdcardsCnt 要设置的手机存疑身份证数
    */
    public void setOtherIdcardsCnt(Integer otherIdcardsCnt){
        this.otherIdcardsCnt = otherIdcardsCnt;
    }

    /**
    * 获取手机存疑触黑身份证数
    *
    * @return 手机存疑触黑身份证数
    */
    public Integer getOtherIdcardsBlackCnt(){
        return otherIdcardsBlackCnt;
    }

    /**
    * 设置手机存疑触黑身份证数
    * 
    * @param otherIdcardsBlackCnt 要设置的手机存疑触黑身份证数
    */
    public void setOtherIdcardsBlackCnt(Integer otherIdcardsBlackCnt){
        this.otherIdcardsBlackCnt = otherIdcardsBlackCnt;
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