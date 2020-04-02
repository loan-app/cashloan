package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 魔杖2.0报告-基础信息表实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:05
 */
 public class MagicMultipointQueriedAnalyze implements Serializable {

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
    * 机构类型
    */
    private String orgType;

    /**
    * 近15天贷款申请次数
    */
    private Integer loanCnt15d;

    /**
    * 近30天贷款申请次数
    */
    private Integer loanCnt30d;

    /**
    * 近90天贷款申请次数
    */
    private Integer loanCnt90d;

    /**
    * 近180天贷款申请次数
    */
    private Integer loanCnt180d;

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
    * 获取机构类型
    *
    * @return 机构类型
    */
    public String getOrgType(){
        return orgType;
    }

    /**
    * 设置机构类型
    * 
    * @param orgType 要设置的机构类型
    */
    public void setOrgType(String orgType){
        this.orgType = orgType;
    }

    /**
    * 获取近15天贷款申请次数
    *
    * @return 近15天贷款申请次数
    */
    public Integer getLoanCnt15d(){
        return loanCnt15d;
    }

    /**
    * 设置近15天贷款申请次数
    * 
    * @param loanCnt15d 要设置的近15天贷款申请次数
    */
    public void setLoanCnt15d(Integer loanCnt15d){
        this.loanCnt15d = loanCnt15d;
    }

    /**
    * 获取近30天贷款申请次数
    *
    * @return 近30天贷款申请次数
    */
    public Integer getLoanCnt30d(){
        return loanCnt30d;
    }

    /**
    * 设置近30天贷款申请次数
    * 
    * @param loanCnt30d 要设置的近30天贷款申请次数
    */
    public void setLoanCnt30d(Integer loanCnt30d){
        this.loanCnt30d = loanCnt30d;
    }

    /**
    * 获取近90天贷款申请次数
    *
    * @return 近90天贷款申请次数
    */
    public Integer getLoanCnt90d(){
        return loanCnt90d;
    }

    /**
    * 设置近90天贷款申请次数
    * 
    * @param loanCnt90d 要设置的近90天贷款申请次数
    */
    public void setLoanCnt90d(Integer loanCnt90d){
        this.loanCnt90d = loanCnt90d;
    }

    /**
    * 获取近180天贷款申请次数
    *
    * @return 近180天贷款申请次数
    */
    public Integer getLoanCnt180d(){
        return loanCnt180d;
    }

    /**
    * 设置近180天贷款申请次数
    * 
    * @param loanCnt180d 要设置的近180天贷款申请次数
    */
    public void setLoanCnt180d(Integer loanCnt180d){
        this.loanCnt180d = loanCnt180d;
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