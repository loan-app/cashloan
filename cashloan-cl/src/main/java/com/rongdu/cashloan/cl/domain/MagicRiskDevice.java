package com.rongdu.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 魔杖2.0报告-基础信息表实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:05
 */
 public class MagicRiskDevice implements Serializable {

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
    * 设备指纹对应的借贷APP数量
    */
    private Integer loanCnt;

    /**
    * 设备指纹对应的消费分期APP数量
    */
    private Integer consumptionCnt;

    /**
    * 设备指纹对应的彩票APP数量
    */
    private Integer lotteryCnt;

    /**
    * 设备指纹对应的借贷APP数量占比
    */
    private String loanCntRatio;

    /**
    * 设备指纹对应的消费分期APP数量占比
    */
    private String consumptionCntRatio;

    /**
    * 设备指纹对应的彩票APP数量占比
    */
    private String lotteryCntRatio;

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
    * 获取设备指纹对应的借贷APP数量
    *
    * @return 设备指纹对应的借贷APP数量
    */
    public Integer getLoanCnt(){
        return loanCnt;
    }

    /**
    * 设置设备指纹对应的借贷APP数量
    * 
    * @param loanCnt 要设置的设备指纹对应的借贷APP数量
    */
    public void setLoanCnt(Integer loanCnt){
        this.loanCnt = loanCnt;
    }

    /**
    * 获取设备指纹对应的消费分期APP数量
    *
    * @return 设备指纹对应的消费分期APP数量
    */
    public Integer getConsumptionCnt(){
        return consumptionCnt;
    }

    /**
    * 设置设备指纹对应的消费分期APP数量
    * 
    * @param consumptionCnt 要设置的设备指纹对应的消费分期APP数量
    */
    public void setConsumptionCnt(Integer consumptionCnt){
        this.consumptionCnt = consumptionCnt;
    }

    /**
    * 获取设备指纹对应的彩票APP数量
    *
    * @return 设备指纹对应的彩票APP数量
    */
    public Integer getLotteryCnt(){
        return lotteryCnt;
    }

    /**
    * 设置设备指纹对应的彩票APP数量
    * 
    * @param lotteryCnt 要设置的设备指纹对应的彩票APP数量
    */
    public void setLotteryCnt(Integer lotteryCnt){
        this.lotteryCnt = lotteryCnt;
    }

    /**
    * 获取设备指纹对应的借贷APP数量占比
    *
    * @return 设备指纹对应的借贷APP数量占比
    */
    public String getLoanCntRatio(){
        return loanCntRatio;
    }

    /**
    * 设置设备指纹对应的借贷APP数量占比
    * 
    * @param loanCntRatio 要设置的设备指纹对应的借贷APP数量占比
    */
    public void setLoanCntRatio(String loanCntRatio){
        this.loanCntRatio = loanCntRatio;
    }

    /**
    * 获取设备指纹对应的消费分期APP数量占比
    *
    * @return 设备指纹对应的消费分期APP数量占比
    */
    public String getConsumptionCntRatio(){
        return consumptionCntRatio;
    }

    /**
    * 设置设备指纹对应的消费分期APP数量占比
    * 
    * @param consumptionCntRatio 要设置的设备指纹对应的消费分期APP数量占比
    */
    public void setConsumptionCntRatio(String consumptionCntRatio){
        this.consumptionCntRatio = consumptionCntRatio;
    }

    /**
    * 获取设备指纹对应的彩票APP数量占比
    *
    * @return 设备指纹对应的彩票APP数量占比
    */
    public String getLotteryCntRatio(){
        return lotteryCntRatio;
    }

    /**
    * 设置设备指纹对应的彩票APP数量占比
    * 
    * @param lotteryCntRatio 要设置的设备指纹对应的彩票APP数量占比
    */
    public void setLotteryCntRatio(String lotteryCntRatio){
        this.lotteryCntRatio = lotteryCntRatio;
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