package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 魔杖2.0报告-基础信息表实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:03
 */
 public class MagicMultipoint implements Serializable {

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
    * 第三方渠道注册机构数量
    */
    private Integer registerOtherCount;

    /**
    * 注册机构数量
    */
    private Integer registerOrgCount;

    /**
    * 注册机构类型
    */
    private String registerOrgTypes;

    /**
    * 机构查询次数
    */
    private Integer queriedDetailOrgCount;

    /**
    * 汇总借贷机构数
    */
    private Integer loanOrgCntAll;

    /**
    * 借贷机构数(去重)
    */
    private Integer loanOrgCnt;

    /**
    * 借贷次数
    */
    private Integer loanCnt;

    /**
    * 近15天贷款的机构数
    */
    private Integer loanOrgCnt15d;

    /**
    * 近30天贷款的机构数
    */
    private Integer loanOrgCnt30d;

    /**
    * 近90天贷款的机构数
    */
    private Integer loanOrgCnt90d;

    /**
    * 近180天贷款的机构数
    */
    private Integer loanOrgCnt180d;

    /**
    * 近15天贷款的次数
    */
    private Integer loanCnt15d;

    /**
    * 近30天贷款的次数
    */
    private Integer loanCnt30d;

    /**
    * 近90天贷款的次数
    */
    private Integer loanCnt90d;

    /**
    * 近180天贷款的次数
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
    * 获取第三方渠道注册机构数量
    *
    * @return 第三方渠道注册机构数量
    */
    public Integer getRegisterOtherCount(){
        return registerOtherCount;
    }

    /**
    * 设置第三方渠道注册机构数量
    * 
    * @param registerOtherCount 要设置的第三方渠道注册机构数量
    */
    public void setRegisterOtherCount(Integer registerOtherCount){
        this.registerOtherCount = registerOtherCount;
    }

    /**
    * 获取注册机构数量
    *
    * @return 注册机构数量
    */
    public Integer getRegisterOrgCount(){
        return registerOrgCount;
    }

    /**
    * 设置注册机构数量
    * 
    * @param registerOrgCount 要设置的注册机构数量
    */
    public void setRegisterOrgCount(Integer registerOrgCount){
        this.registerOrgCount = registerOrgCount;
    }

    /**
    * 获取注册机构类型
    *
    * @return 注册机构类型
    */
    public String getRegisterOrgTypes(){
        return registerOrgTypes;
    }

    /**
    * 设置注册机构类型
    * 
    * @param registerOrgTypes 要设置的注册机构类型
    */
    public void setRegisterOrgTypes(String registerOrgTypes){
        this.registerOrgTypes = registerOrgTypes;
    }

    /**
    * 获取机构查询次数
    *
    * @return 机构查询次数
    */
    public Integer getQueriedDetailOrgCount(){
        return queriedDetailOrgCount;
    }

    /**
    * 设置机构查询次数
    * 
    * @param queriedDetailOrgCount 要设置的机构查询次数
    */
    public void setQueriedDetailOrgCount(Integer queriedDetailOrgCount){
        this.queriedDetailOrgCount = queriedDetailOrgCount;
    }

    /**
    * 获取汇总借贷机构数
    *
    * @return 汇总借贷机构数
    */
    public Integer getLoanOrgCntAll(){
        return loanOrgCntAll;
    }

    /**
    * 设置汇总借贷机构数
    * 
    * @param loanOrgCntAll 要设置的汇总借贷机构数
    */
    public void setLoanOrgCntAll(Integer loanOrgCntAll){
        this.loanOrgCntAll = loanOrgCntAll;
    }

    /**
    * 获取借贷机构数(去重)
    *
    * @return 借贷机构数(去重)
    */
    public Integer getLoanOrgCnt(){
        return loanOrgCnt;
    }

    /**
    * 设置借贷机构数(去重)
    * 
    * @param loanOrgCnt 要设置的借贷机构数(去重)
    */
    public void setLoanOrgCnt(Integer loanOrgCnt){
        this.loanOrgCnt = loanOrgCnt;
    }

    /**
    * 获取借贷次数
    *
    * @return 借贷次数
    */
    public Integer getLoanCnt(){
        return loanCnt;
    }

    /**
    * 设置借贷次数
    * 
    * @param loanCnt 要设置的借贷次数
    */
    public void setLoanCnt(Integer loanCnt){
        this.loanCnt = loanCnt;
    }

    /**
    * 获取近15天贷款的机构数
    *
    * @return 近15天贷款的机构数
    */
    public Integer getLoanOrgCnt15d(){
        return loanOrgCnt15d;
    }

    /**
    * 设置近15天贷款的机构数
    * 
    * @param loanOrgCnt15d 要设置的近15天贷款的机构数
    */
    public void setLoanOrgCnt15d(Integer loanOrgCnt15d){
        this.loanOrgCnt15d = loanOrgCnt15d;
    }

    /**
    * 获取近30天贷款的机构数
    *
    * @return 近30天贷款的机构数
    */
    public Integer getLoanOrgCnt30d(){
        return loanOrgCnt30d;
    }

    /**
    * 设置近30天贷款的机构数
    * 
    * @param loanOrgCnt30d 要设置的近30天贷款的机构数
    */
    public void setLoanOrgCnt30d(Integer loanOrgCnt30d){
        this.loanOrgCnt30d = loanOrgCnt30d;
    }

    /**
    * 获取近90天贷款的机构数
    *
    * @return 近90天贷款的机构数
    */
    public Integer getLoanOrgCnt90d(){
        return loanOrgCnt90d;
    }

    /**
    * 设置近90天贷款的机构数
    * 
    * @param loanOrgCnt90d 要设置的近90天贷款的机构数
    */
    public void setLoanOrgCnt90d(Integer loanOrgCnt90d){
        this.loanOrgCnt90d = loanOrgCnt90d;
    }

    /**
    * 获取近180天贷款的机构数
    *
    * @return 近180天贷款的机构数
    */
    public Integer getLoanOrgCnt180d(){
        return loanOrgCnt180d;
    }

    /**
    * 设置近180天贷款的机构数
    * 
    * @param loanOrgCnt180d 要设置的近180天贷款的机构数
    */
    public void setLoanOrgCnt180d(Integer loanOrgCnt180d){
        this.loanOrgCnt180d = loanOrgCnt180d;
    }

    /**
    * 获取近15天贷款的次数
    *
    * @return 近15天贷款的次数
    */
    public Integer getLoanCnt15d(){
        return loanCnt15d;
    }

    /**
    * 设置近15天贷款的次数
    * 
    * @param loanCnt15d 要设置的近15天贷款的次数
    */
    public void setLoanCnt15d(Integer loanCnt15d){
        this.loanCnt15d = loanCnt15d;
    }

    /**
    * 获取近30天贷款的次数
    *
    * @return 近30天贷款的次数
    */
    public Integer getLoanCnt30d(){
        return loanCnt30d;
    }

    /**
    * 设置近30天贷款的次数
    * 
    * @param loanCnt30d 要设置的近30天贷款的次数
    */
    public void setLoanCnt30d(Integer loanCnt30d){
        this.loanCnt30d = loanCnt30d;
    }

    /**
    * 获取近90天贷款的次数
    *
    * @return 近90天贷款的次数
    */
    public Integer getLoanCnt90d(){
        return loanCnt90d;
    }

    /**
    * 设置近90天贷款的次数
    * 
    * @param loanCnt90d 要设置的近90天贷款的次数
    */
    public void setLoanCnt90d(Integer loanCnt90d){
        this.loanCnt90d = loanCnt90d;
    }

    /**
    * 获取近180天贷款的次数
    *
    * @return 近180天贷款的次数
    */
    public Integer getLoanCnt180d(){
        return loanCnt180d;
    }

    /**
    * 设置近180天贷款的次数
    * 
    * @param loanCnt180d 要设置的近180天贷款的次数
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