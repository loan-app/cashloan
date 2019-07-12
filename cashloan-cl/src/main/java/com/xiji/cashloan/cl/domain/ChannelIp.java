package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 渠道ip记录表实体
 * 
 * @author wnb
 * @version 1.0.0
 * @date 2018/12/03
 *
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 public class ChannelIp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 渠道id
    */
    private Long channelId;


    /**
    * 统计日期
    */
    private Date createDate;

    /**
    * 请求ip
    */
    private String ip;


    
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
    * @param
    */
    public void setId(Long id){
    this.id = id;
    }

    /**
    * 获取渠道id
    *
    * @return 渠道id
    */
    public Long getChannelId(){
    return channelId;
    }

    /**
    * 设置渠道id
    * 
    * @param channelId 要设置的渠道id
    */
    public void setChannelId(Long channelId){
    this.channelId = channelId;
    }


   public static long getSerialVersionUID() {
      return serialVersionUID;
   }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}