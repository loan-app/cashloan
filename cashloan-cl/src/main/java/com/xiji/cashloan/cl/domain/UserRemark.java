package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户备注实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2019-01-17 14:29:29
 */
 public class UserRemark implements Serializable {

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
    * 操作人ID
    */
    private Long operateId;

    /**
    * 备注
    */
    private String remark;

    /**
    * 操作时间
    */
    private Date operateTime;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
     * 操作人名称
     */
    private String operateName;

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
    * @param id
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
    * 获取操作人ID
    *
    * @return 操作人ID
    */
    public Long getOperateId(){
        return operateId;
    }

    /**
    * 设置操作人ID
    * 
    * @param operateId 要设置的操作人ID
    */
    public void setOperateId(Long operateId){
        this.operateId = operateId;
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
    * 获取操作时间
    *
    * @return 操作时间
    */
    public Date getOperateTime(){
        return operateTime;
    }

    /**
    * 设置操作时间
    * 
    * @param operateTime 要设置的操作时间
    */
    public void setOperateTime(Date operateTime){
        this.operateTime = operateTime;
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


    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

}