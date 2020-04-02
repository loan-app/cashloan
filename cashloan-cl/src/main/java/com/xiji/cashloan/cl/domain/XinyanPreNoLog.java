package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 新颜获取预订单号记录实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2019-01-16 20:56:41
 */
 public class XinyanPreNoLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 申请订单号
    */
    private String transId;

    /**
    * 预订单号
    */
    private String preOrderNo;

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
    * 接口响应错误码
    */
    private String respCode;

    /**
    * 接口响应错误描述
    */
    private String respParams;

    /**
    * 同步响应时间
    */
    private Date respTime;

    /**
    * 类型 1-行为雷达
    */
    private Integer type;

    /**
    * data响应码 0-查询成功 1-失败 9-其他异常
    */
    private String dataCode;

    /**
    * 添加时间
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
    * 获取申请订单号
    *
    * @return 申请订单号
    */
    public String getTransId(){
        return transId;
    }

    /**
    * 设置申请订单号
    * 
    * @param transId 要设置的申请订单号
    */
    public void setTransId(String transId){
        this.transId = transId;
    }

    /**
    * 获取预订单号
    *
    * @return 预订单号
    */
    public String getPreOrderNo(){
        return preOrderNo;
    }

    /**
    * 设置预订单号
    * 
    * @param preOrderNo 要设置的预订单号
    */
    public void setPreOrderNo(String preOrderNo){
        this.preOrderNo = preOrderNo;
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
    * 获取接口响应错误码
    *
    * @return 接口响应错误码
    */
    public String getRespCode(){
        return respCode;
    }

    /**
    * 设置接口响应错误码
    * 
    * @param respCode 要设置的接口响应错误码
    */
    public void setRespCode(String respCode){
        this.respCode = respCode;
    }

    /**
    * 获取接口响应错误描述
    *
    * @return 接口响应错误描述
    */
    public String getRespParams(){
        return respParams;
    }

    /**
    * 设置接口响应错误描述
    * 
    * @param respParams 要设置的接口响应错误描述
    */
    public void setRespParams(String respParams){
        this.respParams = respParams;
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
    * 获取类型 1-行为雷达
    *
    * @return 类型 1-行为雷达
    */
    public Integer getType(){
        return type;
    }

    /**
    * 设置类型 1-行为雷达
    * 
    * @param type 要设置的类型 1-行为雷达
    */
    public void setType(Integer type){
        this.type = type;
    }

    /**
    * 获取data响应码 0-查询成功 1-失败 9-其他异常
    *
    * @return data响应码 0-查询成功 1-失败 9-其他异常
    */
    public String getDataCode(){
        return dataCode;
    }

    /**
    * 设置data响应码 0-查询成功 1-失败 9-其他异常
    * 
    * @param dataCode 要设置的data响应码 0-查询成功 1-失败 9-其他异常
    */
    public void setDataCode(String dataCode){
        this.dataCode = dataCode;
    }

    /**
    * 获取添加时间
    *
    * @return 添加时间
    */
    public Date getCreateTime(){
        return createTime;
    }

    /**
    * 设置添加时间
    * 
    * @param createTime 要设置的添加时间
    */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

}