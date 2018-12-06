package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 魔杖2.0请求记录实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-06 15:35:04
 */
 public class MagicReqLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 任务id
    */
    private String transId;

    /**
    * 借款标识
    */
    private Long borrowId;

    /**
    * 用户标识
    */
    private Long userId;

    /**
    * 添加时间
    */
    private Date createTime;

    /**
    * 回调返回码
    */
    private String respCode;

    /**
    * 同步响应结果
    */
    private String respParams;

    /**
    * 类型 2-魔杖反欺诈 3-魔杖多头 4-魔杖黑灰名单 5-魔杖贷后行为
    */
    private Integer type;

    /**
    * 同步响应时间
    */
    private Date respTime;


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
    * 获取任务id
    *
    * @return 任务id
    */
    public String getTransId(){
        return transId;
    }

    /**
    * 设置任务id
    * 
    * @param transId 要设置的任务id
    */
    public void setTransId(String transId){
        this.transId = transId;
    }

    /**
    * 获取借款标识
    *
    * @return 借款标识
    */
    public Long getBorrowId(){
        return borrowId;
    }

    /**
    * 设置借款标识
    * 
    * @param borrowId 要设置的借款标识
    */
    public void setBorrowId(Long borrowId){
        this.borrowId = borrowId;
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
    * 获取类型 2-魔杖反欺诈 3-魔杖多头 4-魔杖黑灰名单 5-魔杖贷后行为
    *
    * @return 类型 2-魔杖反欺诈 3-魔杖多头 4-魔杖黑灰名单 5-魔杖贷后行为
    */
    public Integer getType(){
        return type;
    }

    /**
    * 设置类型 2-魔杖反欺诈 3-魔杖多头 4-魔杖黑灰名单 5-魔杖贷后行为
    * 
    * @param type 要设置的类型 2-魔杖反欺诈 3-魔杖多头 4-魔杖黑灰名单 5-魔杖贷后行为
    */
    public void setType(Integer type){
        this.type = type;
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

}