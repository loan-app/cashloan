package com.rongdu.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 魔杖2.0报告-基础信息表实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:03
 */
 public class MagicUntrustedDetail implements Serializable {

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
    * 执行法院
    */
    private String courtName;

    /**
    * 省份
    */
    private String areaName;

    /**
    * 执行依据文号
    */
    private String caseCode;

    /**
    * 立案时间. 格式yyyy年MM月dd日
    */
    private String publishDate;

    /**
    * 案号
    */
    private String gistId;

    /**
    * 生效法律文书确定的义务
    */
    private String duty;

    /**
    * 被执行人的履行性质
    */
    private String performance;

    /**
    * 失信被执行人行为具体情形
    */
    private String disruptTypeName;

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
    * 获取执行法院
    *
    * @return 执行法院
    */
    public String getCourtName(){
        return courtName;
    }

    /**
    * 设置执行法院
    * 
    * @param courtName 要设置的执行法院
    */
    public void setCourtName(String courtName){
        this.courtName = courtName;
    }

    /**
    * 获取省份
    *
    * @return 省份
    */
    public String getAreaName(){
        return areaName;
    }

    /**
    * 设置省份
    * 
    * @param areaName 要设置的省份
    */
    public void setAreaName(String areaName){
        this.areaName = areaName;
    }

    /**
    * 获取执行依据文号
    *
    * @return 执行依据文号
    */
    public String getCaseCode(){
        return caseCode;
    }

    /**
    * 设置执行依据文号
    * 
    * @param caseCode 要设置的执行依据文号
    */
    public void setCaseCode(String caseCode){
        this.caseCode = caseCode;
    }

    /**
    * 获取立案时间. 格式yyyy年MM月dd日
    *
    * @return 立案时间. 格式yyyy年MM月dd日
    */
    public String getPublishDate(){
        return publishDate;
    }

    /**
    * 设置立案时间. 格式yyyy年MM月dd日
    * 
    * @param publishDate 要设置的立案时间. 格式yyyy年MM月dd日
    */
    public void setPublishDate(String publishDate){
        this.publishDate = publishDate;
    }

    /**
    * 获取案号
    *
    * @return 案号
    */
    public String getGistId(){
        return gistId;
    }

    /**
    * 设置案号
    * 
    * @param gistId 要设置的案号
    */
    public void setGistId(String gistId){
        this.gistId = gistId;
    }

    /**
    * 获取生效法律文书确定的义务
    *
    * @return 生效法律文书确定的义务
    */
    public String getDuty(){
        return duty;
    }

    /**
    * 设置生效法律文书确定的义务
    * 
    * @param duty 要设置的生效法律文书确定的义务
    */
    public void setDuty(String duty){
        this.duty = duty;
    }

    /**
    * 获取被执行人的履行性质
    *
    * @return 被执行人的履行性质
    */
    public String getPerformance(){
        return performance;
    }

    /**
    * 设置被执行人的履行性质
    * 
    * @param performance 要设置的被执行人的履行性质
    */
    public void setPerformance(String performance){
        this.performance = performance;
    }

    /**
    * 获取失信被执行人行为具体情形
    *
    * @return 失信被执行人行为具体情形
    */
    public String getDisruptTypeName(){
        return disruptTypeName;
    }

    /**
    * 设置失信被执行人行为具体情形
    * 
    * @param disruptTypeName 要设置的失信被执行人行为具体情形
    */
    public void setDisruptTypeName(String disruptTypeName){
        this.disruptTypeName = disruptTypeName;
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