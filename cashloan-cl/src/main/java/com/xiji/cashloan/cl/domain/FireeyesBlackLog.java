package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 火眼黑名单实体
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2018/12/03
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 public class FireeyesBlackLog implements Serializable {

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
    private String phone;

    /**
    * 姓名
    */
    private String userName;

    /**
    * 身份证号
    */
    private String userCard;

    /**
    * 订单编号
    */
    private String orderNo;

    /**
    * 同步响应返回是否黑名单内容
    */
    private String isBlack;

    /**
    * 响应码
    */
    private String respCode;

    /**
    * 同步响应结果
    */
    private String respParams;

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
    public String getPhone(){
        return phone;
    }

    /**
    * 设置用户手机号
    * 
    * @param phone 要设置的用户手机号
    */
    public void setPhone(String phone){
        this.phone = phone;
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
    public String getUserCard(){
        return userCard;
    }

    /**
    * 设置身份证号
    * 
    * @param userCard 要设置的身份证号
    */
    public void setUserCard(String userCard){
        this.userCard = userCard;
    }

    /**
    * 获取订单编号
    *
    * @return 订单编号
    */
    public String getOrderNo(){
        return orderNo;
    }

    /**
    * 设置订单编号
    * 
    * @param orderNo 要设置的订单编号
    */
    public void setOrderNo(String orderNo){
        this.orderNo = orderNo;
    }

    /**
    * 获取同步响应返回是否黑名单内容
    *
    * @return 同步响应返回是否黑名单内容
    */
    public String getIsBlack(){
        return isBlack;
    }

    /**
    * 设置同步响应返回是否黑名单内容
    * 
    * @param isBlack 要设置的同步响应返回是否黑名单内容
    */
    public void setIsBlack(String isBlack){
        this.isBlack = isBlack;
    }

    /**
    * 获取响应码
    *
    * @return 响应码
    */
    public String getRespCode(){
        return respCode;
    }

    /**
    * 设置响应码
    * 
    * @param respCode 要设置的响应码
    */
    public void setRespCode(String respCode){
        this.respCode = respCode;
    }

    /**
    * 获取同步响应结果
    *
    * @return 同步响应结果
    */
    public String getRespParams(){
        return respParams;
    }

    /**
    * 设置同步响应结果
    * 
    * @param respParams 要设置的同步响应结果
    */
    public void setRespParams(String respParams){
        this.respParams = respParams;
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