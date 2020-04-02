package com.xiji.cashloan.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 卡bin实体
 * 
 * @author wjs
 * @version 1.0.0
 * @date 2019-01-25 17:50:22
 */
 public class BankCardBin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 分行名称
    */
    private String name;

    /**
    * 银行编码
    */
    private String bankCode;

    /**
    * 银行名称
    */
    private String bankName;

    /**
    * 卡名称
    */
    private String cardName;

    /**
    * 卡类型，借记卡，准贷记卡，贷记卡等
    */
    private String cardType;

    /**
    * 卡bin
    */
    private String cardBin;

    /**
    * 卡bin长度
    */
    private Integer binLen;

    /**
    * 卡长度
    */
    private Integer cardLen;

    /**
    * 
    */
    private Date createTime;

    /**
    * 
    */
    private Date updateTime;

    /**
    * 是否有效：1-有效;0-无效
    */
    private Integer yn;


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
    * 获取分行名称
    *
    * @return 分行名称
    */
    public String getName(){
        return name;
    }

    /**
    * 设置分行名称
    * 
    * @param name 要设置的分行名称
    */
    public void setName(String name){
        this.name = name;
    }

    /**
    * 获取银行编码
    *
    * @return 银行编码
    */
    public String getBankCode(){
        return bankCode;
    }

    /**
    * 设置银行编码
    * 
    * @param bankCode 要设置的银行编码
    */
    public void setBankCode(String bankCode){
        this.bankCode = bankCode;
    }

    /**
    * 获取银行名称
    *
    * @return 银行名称
    */
    public String getBankName(){
        return bankName;
    }

    /**
    * 设置银行名称
    * 
    * @param bankName 要设置的银行名称
    */
    public void setBankName(String bankName){
        this.bankName = bankName;
    }

    /**
    * 获取卡名称
    *
    * @return 卡名称
    */
    public String getCardName(){
        return cardName;
    }

    /**
    * 设置卡名称
    * 
    * @param cardName 要设置的卡名称
    */
    public void setCardName(String cardName){
        this.cardName = cardName;
    }

    /**
    * 获取卡类型，借记卡，准贷记卡，贷记卡等
    *
    * @return 卡类型，借记卡，准贷记卡，贷记卡等
    */
    public String getCardType(){
        return cardType;
    }

    /**
    * 设置卡类型，借记卡，准贷记卡，贷记卡等
    * 
    * @param cardType 要设置的卡类型，借记卡，准贷记卡，贷记卡等
    */
    public void setCardType(String cardType){
        this.cardType = cardType;
    }

    /**
    * 获取卡bin
    *
    * @return 卡bin
    */
    public String getCardBin(){
        return cardBin;
    }

    /**
    * 设置卡bin
    * 
    * @param cardBin 要设置的卡bin
    */
    public void setCardBin(String cardBin){
        this.cardBin = cardBin;
    }

    /**
    * 获取卡bin长度
    *
    * @return 卡bin长度
    */
    public Integer getBinLen(){
        return binLen;
    }

    /**
    * 设置卡bin长度
    * 
    * @param binLen 要设置的卡bin长度
    */
    public void setBinLen(Integer binLen){
        this.binLen = binLen;
    }

    /**
    * 获取卡长度
    *
    * @return 卡长度
    */
    public Integer getCardLen(){
        return cardLen;
    }

    /**
    * 设置卡长度
    * 
    * @param cardLen 要设置的卡长度
    */
    public void setCardLen(Integer cardLen){
        this.cardLen = cardLen;
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
    public Date getUpdateTime(){
        return updateTime;
    }

    /**
    * 设置
    * 
    * @param updateTime 要设置的
    */
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    /**
    * 获取是否有效：1-有效;0-无效
    *
    * @return 是否有效：1-有效;0-无效
    */
    public Integer getYn(){
        return yn;
    }

    /**
    * 设置是否有效：1-有效;0-无效
    * 
    * @param yn 要设置的是否有效：1-有效;0-无效
    */
    public void setYn(Integer yn){
        this.yn = yn;
    }

}