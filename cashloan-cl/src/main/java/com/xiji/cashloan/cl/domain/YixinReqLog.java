package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 宜信请求记录实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-25 16:25:46
 */
 public class YixinReqLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 同步响应流水号
    */
    private String flowId;

    /**
    * 用户标识
    */
    private Long userId;

    /**
    * 借款订单id
    */
    private Long borrowId;

    /**
    * 请求是否成功 0-失败 1-成功
    */
    private Integer isSuccess;

    /**
    * 回调返回码
    */
    private String respCode;

    /**
    * 同步响应结果
    */
    private String respMsg;

    /**
    * 同步响应时间
    */
    private Date respTime;

    /**
    * 是否收费 0-不收费 1-收费
    */
    private Integer isFee;

    /**
    * 类型 1-风险评估
    */
    private Integer type;

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
    * 获取同步响应流水号
    *
    * @return 同步响应流水号
    */
    public String getFlowId(){
        return flowId;
    }

    /**
    * 设置同步响应流水号
    * 
    * @param flowId 要设置的同步响应流水号
    */
    public void setFlowId(String flowId){
        this.flowId = flowId;
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
    * 获取借款订单id
    *
    * @return 借款订单id
    */
    public Long getBorrowId(){
        return borrowId;
    }

    /**
    * 设置借款订单id
    * 
    * @param borrowId 要设置的借款订单id
    */
    public void setBorrowId(Long borrowId){
        this.borrowId = borrowId;
    }

    /**
    * 获取请求是否成功 0-失败 1-成功
    *
    * @return 请求是否成功 0-失败 1-成功
    */
    public Integer getIsSuccess(){
        return isSuccess;
    }

    /**
    * 设置请求是否成功 0-失败 1-成功
    * 
    * @param isSuccess 要设置的请求是否成功 0-失败 1-成功
    */
    public void setIsSuccess(Integer isSuccess){
        this.isSuccess = isSuccess;
    }

    /**
    * 获取回调返回码
    *
    * @return 回调返回码
    */
    public String getRespCode(){
        return respCode;
    }

    /**
    * 设置回调返回码
    * 
    * @param respCode 要设置的回调返回码
    */
    public void setRespCode(String respCode){
        this.respCode = respCode;
    }

    /**
    * 获取同步响应结果
    *
    * @return 同步响应结果
    */
    public String getRespMsg(){
        return respMsg;
    }

    /**
    * 设置同步响应结果
    * 
    * @param respMsg 要设置的同步响应结果
    */
    public void setRespMsg(String respMsg){
        this.respMsg = respMsg;
    }

    /**
    * 获取同步响应时间
    *
    * @return 同步响应时间
    */
    public Date getRespTime(){
        return respTime;
    }

    /**
    * 设置同步响应时间
    * 
    * @param respTime 要设置的同步响应时间
    */
    public void setRespTime(Date respTime){
        this.respTime = respTime;
    }

    /**
    * 获取是否收费 0-不收费 1-收费
    *
    * @return 是否收费 0-不收费 1-收费
    */
    public Integer getIsFee(){
        return isFee;
    }

    /**
    * 设置是否收费 0-不收费 1-收费
    * 
    * @param isFee 要设置的是否收费 0-不收费 1-收费
    */
    public void setIsFee(Integer isFee){
        this.isFee = isFee;
    }

    /**
    * 获取类型 1-风险评估
    *
    * @return 类型 1-风险评估
    */
    public Integer getType(){
        return type;
    }

    /**
    * 设置类型 1-风险评估
    * 
    * @param type 要设置的类型 1-风险评估
    */
    public void setType(Integer type){
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