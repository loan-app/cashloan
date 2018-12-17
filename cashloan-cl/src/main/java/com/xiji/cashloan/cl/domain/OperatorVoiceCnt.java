package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 通话详情统计实体
 * 
 * @author king
 * @version 1.0.0
 * @date 2018-12-17 11:31:50
 */
 public class OperatorVoiceCnt implements Serializable {

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
     * 请求记录id
     */
    private Long reqLogId;

    /**
    * 运营商号码
    */
    private String peerNum;

    /**
    * 运营商
    */
    private String peerName;

    /**
    * 号码归属地
    */
    private String city;

    /**
    * 通讯录联系号码
    */
    private String contactPhone;

    /**
    * 通讯录联系人姓名
    */
    private String contactName;

    /**
    * 联系次数/时间(秒)
    */
    private String callCntNum;

    /**
    * 主叫次数/时间(秒)
    */
    private String dialCntNum;

    /**
    * 被叫次数/时间(秒)
    */
    private String dialedCntNum;

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
    * 获取运营商号码
    *
    * @return 运营商号码
    */
    public String getPeerNum(){
        return peerNum;
    }

    /**
    * 设置运营商号码
    * 
    * @param peerNum 要设置的运营商号码
    */
    public void setPeerNum(String peerNum){
        this.peerNum = peerNum;
    }

    /**
    * 获取运营商
    *
    * @return 运营商
    */
    public String getPeerName(){
        return peerName;
    }

    /**
    * 设置运营商
    * 
    * @param peerName 要设置的运营商
    */
    public void setPeerName(String peerName){
        this.peerName = peerName;
    }

    /**
    * 获取号码归属地
    *
    * @return 号码归属地
    */
    public String getCity(){
        return city;
    }

    /**
    * 设置号码归属地
    * 
    * @param city 要设置的号码归属地
    */
    public void setCity(String city){
        this.city = city;
    }

    /**
    * 获取通讯录联系号码
    *
    * @return 通讯录联系号码
    */
    public String getContactPhone(){
        return contactPhone;
    }

    /**
    * 设置通讯录联系号码
    * 
    * @param contactPhone 要设置的通讯录联系号码
    */
    public void setContactPhone(String contactPhone){
        this.contactPhone = contactPhone;
    }

    /**
    * 获取通讯录联系人姓名
    *
    * @return 通讯录联系人姓名
    */
    public String getContactName(){
        return contactName;
    }

    /**
    * 设置通讯录联系人姓名
    * 
    * @param contactName 要设置的通讯录联系人姓名
    */
    public void setContactName(String contactName){
        this.contactName = contactName;
    }

    /**
    * 获取联系次数/时间(秒)
    *
    * @return 联系次数/时间(秒)
    */
    public String getCallCntNum(){
        return callCntNum;
    }

    /**
    * 设置联系次数/时间(秒)
    * 
    * @param callCntNum 要设置的联系次数/时间(秒)
    */
    public void setCallCntNum(String callCntNum){
        this.callCntNum = callCntNum;
    }

    /**
    * 获取主叫次数/时间(秒)
    *
    * @return 主叫次数/时间(秒)
    */
    public String getDialCntNum(){
        return dialCntNum;
    }

    /**
    * 设置主叫次数/时间(秒)
    * 
    * @param dialCntNum 要设置的主叫次数/时间(秒)
    */
    public void setDialCntNum(String dialCntNum){
        this.dialCntNum = dialCntNum;
    }

    /**
    * 获取被叫次数/时间(秒)
    *
    * @return 被叫次数/时间(秒)
    */
    public String getDialedCntNum(){
        return dialedCntNum;
    }

    /**
    * 设置被叫次数/时间(秒)
    * 
    * @param dialedCntNum 要设置的被叫次数/时间(秒)
    */
    public void setDialedCntNum(String dialedCntNum){
        this.dialedCntNum = dialedCntNum;
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

    public Long getReqLogId() {
        return reqLogId;
    }

    public void setReqLogId(Long reqLogId) {
        this.reqLogId = reqLogId;
    }
}