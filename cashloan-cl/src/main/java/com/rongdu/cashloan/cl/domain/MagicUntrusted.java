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
 public class MagicUntrusted implements Serializable {

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
    * 法院执行人次数
    */
    private Integer courtcaseCnt;

    /**
    * 失信未执行次数
    */
    private Integer dishonestCnt;

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
    * 获取法院执行人次数
    *
    * @return 法院执行人次数
    */
    public Integer getCourtcaseCnt(){
        return courtcaseCnt;
    }

    /**
    * 设置法院执行人次数
    * 
    * @param courtcaseCnt 要设置的法院执行人次数
    */
    public void setCourtcaseCnt(Integer courtcaseCnt){
        this.courtcaseCnt = courtcaseCnt;
    }

    /**
    * 获取失信未执行次数
    *
    * @return 失信未执行次数
    */
    public Integer getDishonestCnt(){
        return dishonestCnt;
    }

    /**
    * 设置失信未执行次数
    * 
    * @param dishonestCnt 要设置的失信未执行次数
    */
    public void setDishonestCnt(Integer dishonestCnt){
        this.dishonestCnt = dishonestCnt;
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