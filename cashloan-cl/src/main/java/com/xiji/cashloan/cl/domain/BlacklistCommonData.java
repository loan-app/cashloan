package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 黑名单通用处理模型实体
 * 
 * @author king
 * @version 1.0.0
 * @date 2018-12-20 17:17:07
 */
 public class BlacklistCommonData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 借款id
    */
    private Long borrowId;

    /**
    * 用户的id
    */
    private Long userId;

    /**
    * 手机号
    */
    private String phone = "";

    /**
    * 姓名
    */
    private String name = "";

    /**
    * 身份证号
    */
    private String idCard = "";

    /**
    * 0-正常，1-黑名单，2-灰名单
    */
    private Integer blackType = 0;

    /**
    * 数据详情
    */
    private String dataMsg = "";

    /**
    * 黑名来源
    */
    private String source = "";

    /**
    * 备注
    */
    private String remark = "";

    /**
    * 
    */
    private Date createTime;

    /**
    * 
    */
    private Date lastModifyTime;


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
    * 获取借款id
    *
    * @return 借款id
    */
    public Long getBorrowId(){
        return borrowId;
    }

    /**
    * 设置借款id
    * 
    * @param borrowId 要设置的借款id
    */
    public void setBorrowId(Long borrowId){
        this.borrowId = borrowId;
    }

    /**
    * 获取用户的id
    *
    * @return 用户的id
    */
    public Long getUserId(){
        return userId;
    }

    /**
    * 设置用户的id
    * 
    * @param userId 要设置的用户的id
    */
    public void setUserId(Long userId){
        this.userId = userId;
    }

    /**
    * 获取手机号
    *
    * @return 手机号
    */
    public String getPhone(){
        return phone;
    }

    /**
    * 设置手机号
    * 
    * @param phone 要设置的手机号
    */
    public void setPhone(String phone){
        this.phone = phone;
    }

    /**
    * 获取姓名
    *
    * @return 姓名
    */
    public String getName(){
        return name;
    }

    /**
    * 设置姓名
    * 
    * @param name 要设置的姓名
    */
    public void setName(String name){
        this.name = name;
    }

    /**
    * 获取身份证号
    *
    * @return 身份证号
    */
    public String getIdCard(){
        return idCard;
    }

    /**
    * 设置身份证号
    * 
    * @param idCard 要设置的身份证号
    */
    public void setIdCard(String idCard){
        this.idCard = idCard;
    }

    /**
    * 获取0-正常，1-黑名单，2-灰名单
    *
    * @return 0-正常，1-黑名单，2-灰名单
    */
    public Integer getBlackType(){
        return blackType;
    }

    /**
    * 设置0-正常，1-黑名单，2-灰名单
    * 
    * @param blackType 要设置的0-正常，1-黑名单，2-灰名单
    */
    public void setBlackType(Integer blackType){
        this.blackType = blackType;
    }

    /**
    * 获取数据详情
    *
    * @return 数据详情
    */
    public String getDataMsg(){
        return dataMsg;
    }

    /**
    * 设置数据详情
    * 
    * @param dataMsg 要设置的数据详情
    */
    public void setDataMsg(String dataMsg){
        this.dataMsg = dataMsg;
    }

    /**
    * 获取黑名来源
    *
    * @return 黑名来源
    */
    public String getSource(){
        return source;
    }

    /**
    * 设置黑名来源
    * 
    * @param source 要设置的黑名来源
    */
    public void setSource(String source){
        this.source = source;
    }

    /**
    * 获取备注
    *
    * @return 备注
    */
    public String getRemark(){
        return remark;
    }

    /**
    * 设置备注
    * 
    * @param remark 要设置的备注
    */
    public void setRemark(String remark){
        this.remark = remark;
    }

    /**
    * 获取
    *
    * @return 
    */
    public Date getCreateTime(){
        return createTime;
    }

    /**
    * 设置
    * 
    * @param createTime 要设置的
    */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    /**
    * 获取
    *
    * @return 
    */
    public Date getLastModifyTime(){
        return lastModifyTime;
    }

    /**
    * 设置
    * 
    * @param lastModifyTime 要设置的
    */
    public void setLastModifyTime(Date lastModifyTime){
        this.lastModifyTime = lastModifyTime;
    }

}