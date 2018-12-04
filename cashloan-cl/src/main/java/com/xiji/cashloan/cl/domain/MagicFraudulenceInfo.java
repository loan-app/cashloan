package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 魔杖2.0-欺诈风险信息实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-05 00:52:58
 */
 public class MagicFraudulenceInfo implements Serializable {

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
    * 是否命中 0-否 1-是
    */
    private Integer isHit;

    /**
    * 命中欺诈类型
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
    * 获取是否命中 0-否 1-是
    *
    * @return 是否命中 0-否 1-是
    */
    public Integer getIsHit(){
        return isHit;
    }

    /**
    * 设置是否命中 0-否 1-是
    * 
    * @param isHit 要设置的是否命中 0-否 1-是
    */
    public void setIsHit(Integer isHit){
        this.isHit = isHit;
    }

    /**
    * 获取命中欺诈类型
    *
    * @return 命中欺诈类型
    */
    public String getType(){
        return type;
    }

    /**
    * 设置命中欺诈类型
    * 
    * @param type 要设置的命中欺诈类型
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