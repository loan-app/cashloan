package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 魔杖2.0-黑灰名单记录表实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-02 15:31:57
 */
 public class MagicBlackGray implements Serializable {

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
    * 黑名单类型列表
    */
    private String blackTypes;

    /**
    * 手机和姓名是否在黑名单 0-否 1-是
    */
    private Integer blackMobileNameInBlacklist;

    /**
    * 手机和姓名黑名单信息更新时间
    */
    private String blackMobileNameUpdatedTime;

    /**
    * 身份证和姓名是否在黑名单 0-否 1-是
    */
    private Integer blackIdcardNameInBlacklist;

    /**
    * 身份证和姓名黑名单信息更新时间
    */
    private String blackIdcardNameUpdatedTime;

    /**
    * 黑名单最大逾期金额
    */
    private String blackOverdueAmount;

    /**
    * 黑名单逾期次数
    */
    private Integer blackOverdueCount;

    /**
    * 黑名单最大逾期天数 M0:0-15天 M1:16-30天 M2:31-60天 M3:61-90天 M4:91-120天 M5:121-150天 M6:151-180天 M6+:大于180天
    */
    private String blackOverdueStatus;

    /**
    * 灰名单类型列表
    */
    private String grayTypes;

    /**
    * 手机和姓名是否在灰名单 0-否 1-是
    */
    private Integer grayMobileNameInBlacklist;

    /**
    * 手机和姓名灰名单信息更新时间
    */
    private String grayMobileNameBlackUpdatedTime;

    /**
    * 身份证和姓名是否在灰名单 0-否 1-是
    */
    private Integer grayIdcardNameInBlacklist;

    /**
    * 身份证和姓名灰名单信息更新时间
    */
    private String grayIdcardNameBlackUpdatedTime;

    /**
    * 灰名单最大逾期金额
    */
    private String grayOverdueAmount;

    /**
    * 灰名单逾期次数
    */
    private Integer grayOverdueCount;

    /**
    * 灰名单最大逾期天数 M0:0-15天 M1:16-30天 M2:31-60天 M3:61-90天 M4:91-120天 M5:121-150天 M6:151-180天 M6+:大于180天
    */
    private String grayOverdueStatus;

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
    * 获取黑名单类型列表
    *
    * @return 黑名单类型列表
    */
    public String getBlackTypes(){
        return blackTypes;
    }

    /**
    * 设置黑名单类型列表
    * 
    * @param blackTypes 要设置的黑名单类型列表
    */
    public void setBlackTypes(String blackTypes){
        this.blackTypes = blackTypes;
    }

    /**
    * 获取手机和姓名是否在黑名单 0-否 1-是
    *
    * @return 手机和姓名是否在黑名单 0-否 1-是
    */
    public Integer getBlackMobileNameInBlacklist(){
        return blackMobileNameInBlacklist;
    }

    /**
    * 设置手机和姓名是否在黑名单 0-否 1-是
    * 
    * @param blackMobileNameInBlacklist 要设置的手机和姓名是否在黑名单 0-否 1-是
    */
    public void setBlackMobileNameInBlacklist(Integer blackMobileNameInBlacklist){
        this.blackMobileNameInBlacklist = blackMobileNameInBlacklist;
    }

    /**
    * 获取手机和姓名黑名单信息更新时间
    *
    * @return 手机和姓名黑名单信息更新时间
    */
    public String getBlackMobileNameUpdatedTime(){
        return blackMobileNameUpdatedTime;
    }

    /**
    * 设置手机和姓名黑名单信息更新时间
    * 
    * @param blackMobileNameUpdatedTime 要设置的手机和姓名黑名单信息更新时间
    */
    public void setBlackMobileNameUpdatedTime(String blackMobileNameUpdatedTime){
        this.blackMobileNameUpdatedTime = blackMobileNameUpdatedTime;
    }

    /**
    * 获取身份证和姓名是否在黑名单 0-否 1-是
    *
    * @return 身份证和姓名是否在黑名单 0-否 1-是
    */
    public Integer getBlackIdcardNameInBlacklist(){
        return blackIdcardNameInBlacklist;
    }

    /**
    * 设置身份证和姓名是否在黑名单 0-否 1-是
    * 
    * @param blackIdcardNameInBlacklist 要设置的身份证和姓名是否在黑名单 0-否 1-是
    */
    public void setBlackIdcardNameInBlacklist(Integer blackIdcardNameInBlacklist){
        this.blackIdcardNameInBlacklist = blackIdcardNameInBlacklist;
    }

    /**
    * 获取身份证和姓名黑名单信息更新时间
    *
    * @return 身份证和姓名黑名单信息更新时间
    */
    public String getBlackIdcardNameUpdatedTime(){
        return blackIdcardNameUpdatedTime;
    }

    /**
    * 设置身份证和姓名黑名单信息更新时间
    * 
    * @param blackIdcardNameUpdatedTime 要设置的身份证和姓名黑名单信息更新时间
    */
    public void setBlackIdcardNameUpdatedTime(String blackIdcardNameUpdatedTime){
        this.blackIdcardNameUpdatedTime = blackIdcardNameUpdatedTime;
    }

    /**
    * 获取黑名单最大逾期金额
    *
    * @return 黑名单最大逾期金额
    */
    public String getBlackOverdueAmount(){
        return blackOverdueAmount;
    }

    /**
    * 设置黑名单最大逾期金额
    * 
    * @param blackOverdueAmount 要设置的黑名单最大逾期金额
    */
    public void setBlackOverdueAmount(String blackOverdueAmount){
        this.blackOverdueAmount = blackOverdueAmount;
    }

    /**
    * 获取黑名单逾期次数
    *
    * @return 黑名单逾期次数
    */
    public Integer getBlackOverdueCount(){
        return blackOverdueCount;
    }

    /**
    * 设置黑名单逾期次数
    * 
    * @param blackOverdueCount 要设置的黑名单逾期次数
    */
    public void setBlackOverdueCount(Integer blackOverdueCount){
        this.blackOverdueCount = blackOverdueCount;
    }

    /**
    * 获取黑名单最大逾期天数 M0:0-15天 M1:16-30天 M2:31-60天 M3:61-90天 M4:91-120天 M5:121-150天 M6:151-180天 M6+:大于180天
    *
    * @return 黑名单最大逾期天数 M0:0-15天 M1:16-30天 M2:31-60天 M3:61-90天 M4:91-120天 M5:121-150天 M6:151-180天 M6+:大于180天
    */
    public String getBlackOverdueStatus(){
        return blackOverdueStatus;
    }

    /**
    * 设置黑名单最大逾期天数 M0:0-15天 M1:16-30天 M2:31-60天 M3:61-90天 M4:91-120天 M5:121-150天 M6:151-180天 M6+:大于180天
    * 
    * @param blackOverdueStatus 要设置的黑名单最大逾期天数 M0:0-15天 M1:16-30天 M2:31-60天 M3:61-90天 M4:91-120天 M5:121-150天 M6:151-180天 M6+:大于180天
    */
    public void setBlackOverdueStatus(String blackOverdueStatus){
        this.blackOverdueStatus = blackOverdueStatus;
    }

    /**
    * 获取灰名单类型列表
    *
    * @return 灰名单类型列表
    */
    public String getGrayTypes(){
        return grayTypes;
    }

    /**
    * 设置灰名单类型列表
    * 
    * @param grayTypes 要设置的灰名单类型列表
    */
    public void setGrayTypes(String grayTypes){
        this.grayTypes = grayTypes;
    }

    /**
    * 获取手机和姓名是否在灰名单 0-否 1-是
    *
    * @return 手机和姓名是否在灰名单 0-否 1-是
    */
    public Integer getGrayMobileNameInBlacklist(){
        return grayMobileNameInBlacklist;
    }

    /**
    * 设置手机和姓名是否在灰名单 0-否 1-是
    * 
    * @param grayMobileNameInBlacklist 要设置的手机和姓名是否在灰名单 0-否 1-是
    */
    public void setGrayMobileNameInBlacklist(Integer grayMobileNameInBlacklist){
        this.grayMobileNameInBlacklist = grayMobileNameInBlacklist;
    }

    /**
    * 获取手机和姓名灰名单信息更新时间
    *
    * @return 手机和姓名灰名单信息更新时间
    */
    public String getGrayMobileNameBlackUpdatedTime(){
        return grayMobileNameBlackUpdatedTime;
    }

    /**
    * 设置手机和姓名灰名单信息更新时间
    * 
    * @param grayMobileNameBlackUpdatedTime 要设置的手机和姓名灰名单信息更新时间
    */
    public void setGrayMobileNameBlackUpdatedTime(String grayMobileNameBlackUpdatedTime){
        this.grayMobileNameBlackUpdatedTime = grayMobileNameBlackUpdatedTime;
    }

    /**
    * 获取身份证和姓名是否在灰名单 0-否 1-是
    *
    * @return 身份证和姓名是否在灰名单 0-否 1-是
    */
    public Integer getGrayIdcardNameInBlacklist(){
        return grayIdcardNameInBlacklist;
    }

    /**
    * 设置身份证和姓名是否在灰名单 0-否 1-是
    * 
    * @param grayIdcardNameInBlacklist 要设置的身份证和姓名是否在灰名单 0-否 1-是
    */
    public void setGrayIdcardNameInBlacklist(Integer grayIdcardNameInBlacklist){
        this.grayIdcardNameInBlacklist = grayIdcardNameInBlacklist;
    }

    /**
    * 获取身份证和姓名灰名单信息更新时间
    *
    * @return 身份证和姓名灰名单信息更新时间
    */
    public String getGrayIdcardNameBlackUpdatedTime(){
        return grayIdcardNameBlackUpdatedTime;
    }

    /**
    * 设置身份证和姓名灰名单信息更新时间
    * 
    * @param grayIdcardNameBlackUpdatedTime 要设置的身份证和姓名灰名单信息更新时间
    */
    public void setGrayIdcardNameBlackUpdatedTime(String grayIdcardNameBlackUpdatedTime){
        this.grayIdcardNameBlackUpdatedTime = grayIdcardNameBlackUpdatedTime;
    }

    /**
    * 获取灰名单最大逾期金额
    *
    * @return 灰名单最大逾期金额
    */
    public String getGrayOverdueAmount(){
        return grayOverdueAmount;
    }

    /**
    * 设置灰名单最大逾期金额
    * 
    * @param grayOverdueAmount 要设置的灰名单最大逾期金额
    */
    public void setGrayOverdueAmount(String grayOverdueAmount){
        this.grayOverdueAmount = grayOverdueAmount;
    }

    /**
    * 获取灰名单逾期次数
    *
    * @return 灰名单逾期次数
    */
    public Integer getGrayOverdueCount(){
        return grayOverdueCount;
    }

    /**
    * 设置灰名单逾期次数
    * 
    * @param grayOverdueCount 要设置的灰名单逾期次数
    */
    public void setGrayOverdueCount(Integer grayOverdueCount){
        this.grayOverdueCount = grayOverdueCount;
    }

    /**
    * 获取灰名单最大逾期天数 M0:0-15天 M1:16-30天 M2:31-60天 M3:61-90天 M4:91-120天 M5:121-150天 M6:151-180天 M6+:大于180天
    *
    * @return 灰名单最大逾期天数 M0:0-15天 M1:16-30天 M2:31-60天 M3:61-90天 M4:91-120天 M5:121-150天 M6:151-180天 M6+:大于180天
    */
    public String getGrayOverdueStatus(){
        return grayOverdueStatus;
    }

    /**
    * 设置灰名单最大逾期天数 M0:0-15天 M1:16-30天 M2:31-60天 M3:61-90天 M4:91-120天 M5:121-150天 M6:151-180天 M6+:大于180天
    * 
    * @param grayOverdueStatus 要设置的灰名单最大逾期天数 M0:0-15天 M1:16-30天 M2:31-60天 M3:61-90天 M4:91-120天 M5:121-150天 M6:151-180天 M6+:大于180天
    */
    public void setGrayOverdueStatus(String grayOverdueStatus){
        this.grayOverdueStatus = grayOverdueStatus;
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