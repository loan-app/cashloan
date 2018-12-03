package com.rongdu.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 魔杖2.0报告-基础信息表实体
 * 
 * @author szb
 * @version 1.0.0
 * @date 2018-12-01 10:56:05
 */
 public class MagicIntimateContact implements Serializable {

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
    * 近30天亲密直接联系人数
    */
    private Integer intimatenum30d;

    /**
    * 近30天授权过的亲密直接联系人个数
    */
    private Integer authIntimatenum30d;

    /**
    * 近30天授权过的亲密直接联系人个数占比
    */
    private String authIntimatenumRatio30d;

    /**
    * 近30天命中黑名单的亲密直接联系人个数
    */
    private Integer blackIntimatenum30d;

    /**
    * 近30天命中黑名单的亲密直接联系人个数个数占比
    */
    private String blackIntimatenumRatio30d;

    /**
    * 近30天亲密直接联系人命中黑名单的类型, 格式：逾期,欺诈
    */
    private String intimateType30d;

    /**
    * 近30天授权过的亲密间接联系人个数
    */
    private Integer authIntimateIndirectnum30d;

    /**
    * 近30天授权过的间接联系人个数占比
    */
    private String authIntimateIndirectnumRatio30d;

    /**
    * 近30天命中黑名单的间接联系人个数
    */
    private Integer blackIntimateIndirectnum30d;

    /**
    * 近30天命中黑名单的亲密间接联系人个数占比
    */
    private String blackIntimateIndirectnumRatio30d;

    /**
    * 近30天引起亲密间接联系人在黑名单的亲密直接联系人数
    */
    private Integer blackIntimateIndirectPeernum30d;

    /**
    * 近30天引起亲密间接联系人在黑名单的亲密直接联系人数占比
    */
    private String blackIntimateIndirectPeernumRatio30d;

    /**
    * 近30天引起亲密间接联系人授权的亲密直接联系人数
    */
    private Integer authIntimateIndirectPeernum30d;

    /**
    * 近30天引起亲密间接联系人授权的亲密直接联系人数占比
    */
    private String authIntimateIndirectPeernumRatio30d;

    /**
    * 近90天亲密直接联系人数
    */
    private Integer intimatenum90d;

    /**
    * 近90天授权过的亲密直接联系人个数
    */
    private Integer authIntimatenum90d;

    /**
    * 近90天授权过的亲密直接联系人个数占比
    */
    private String authIntimatenumRatio90d;

    /**
    * 近90天命中黑名单的亲密直接联系人个数
    */
    private Integer blackIntimatenum90d;

    /**
    * 近90天命中黑名单的亲密直接联系人个数个数占比
    */
    private String blackIntimatenumRatio90d;

    /**
    * 近90天亲密直接联系人命中黑名单的类型, 格式：逾期,欺诈
    */
    private String intimateType90d;

    /**
    * 近90天授权过的亲密间接联系人个数
    */
    private Integer authIntimateIndirectnum90d;

    /**
    * 近90天授权过的间接联系人个数占比
    */
    private String authIntimateIndirectnumRatio90d;

    /**
    * 近90天命中黑名单的间接联系人个数
    */
    private Integer blackIntimateIndirectnum90d;

    /**
    * 近90天命中黑名单的亲密间接联系人个数占比
    */
    private String blackIntimateIndirectnumRatio90d;

    /**
    * 近90天引起亲密间接联系人在黑名单的亲密直接联系人数
    */
    private Integer blackIntimateIndirectPeernum90d;

    /**
    * 近90天引起亲密间接联系人在黑名单的亲密直接联系人数占比
    */
    private String blackIntimateIndirectPeernumRatio90d;

    /**
    * 近90天引起亲密间接联系人授权的亲密直接联系人数
    */
    private Integer authIntimateIndirectPeernum90d;

    /**
    * 近90天引起亲密间接联系人授权的亲密直接联系人数占比
    */
    private String authIntimateIndirectPeernumRatio90d;

    /**
    * 近180天亲密直接联系人数
    */
    private Integer intimatenum180d;

    /**
    * 近180天授权过的亲密直接联系人个数
    */
    private Integer authIntimatenum180d;

    /**
    * 近180天授权过的亲密直接联系人个数占比
    */
    private String authIntimatenumRatio180d;

    /**
    * 近180天命中黑名单的亲密直接联系人个数
    */
    private Integer blackIntimatenum180d;

    /**
    * 近180天命中黑名单的亲密直接联系人个数个数占比
    */
    private String blackIntimatenumRatio180d;

    /**
    * 近180天亲密直接联系人命中黑名单的类型, 格式：逾期,欺诈
    */
    private String intimateType180d;

    /**
    * 近180天授权过的亲密间接联系人个数
    */
    private Integer authIntimateIndirectnum180d;

    /**
    * 近180天授权过的间接联系人个数占比
    */
    private String authIntimateIndirectnumRatio180d;

    /**
    * 近180天命中黑名单的间接联系人个数
    */
    private Integer blackIntimateIndirectnum180d;

    /**
    * 近180天命中黑名单的亲密间接联系人个数占比
    */
    private String blackIntimateIndirectnumRatio180d;

    /**
    * 近180天引起亲密间接联系人在黑名单的亲密直接联系人数
    */
    private Integer blackIntimateIndirectPeernum180d;

    /**
    * 近180天引起亲密间接联系人在黑名单的亲密直接联系人数占比
    */
    private String blackIntimateIndirectPeernumRatio180d;

    /**
    * 近180天引起亲密间接联系人授权的亲密直接联系人数
    */
    private Integer authIntimateIndirectPeernum180d;

    /**
    * 近180天引起亲密间接联系人授权的亲密直接联系人数占比
    */
    private String authIntimateIndirectPeernumRatio180d;

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
    * 获取近30天亲密直接联系人数
    *
    * @return 近30天亲密直接联系人数
    */
    public Integer getIntimatenum30d(){
        return intimatenum30d;
    }

    /**
    * 设置近30天亲密直接联系人数
    * 
    * @param intimatenum30d 要设置的近30天亲密直接联系人数
    */
    public void setIntimatenum30d(Integer intimatenum30d){
        this.intimatenum30d = intimatenum30d;
    }

    /**
    * 获取近30天授权过的亲密直接联系人个数
    *
    * @return 近30天授权过的亲密直接联系人个数
    */
    public Integer getAuthIntimatenum30d(){
        return authIntimatenum30d;
    }

    /**
    * 设置近30天授权过的亲密直接联系人个数
    * 
    * @param authIntimatenum30d 要设置的近30天授权过的亲密直接联系人个数
    */
    public void setAuthIntimatenum30d(Integer authIntimatenum30d){
        this.authIntimatenum30d = authIntimatenum30d;
    }

    /**
    * 获取近30天授权过的亲密直接联系人个数占比
    *
    * @return 近30天授权过的亲密直接联系人个数占比
    */
    public String getAuthIntimatenumRatio30d(){
        return authIntimatenumRatio30d;
    }

    /**
    * 设置近30天授权过的亲密直接联系人个数占比
    * 
    * @param authIntimatenumRatio30d 要设置的近30天授权过的亲密直接联系人个数占比
    */
    public void setAuthIntimatenumRatio30d(String authIntimatenumRatio30d){
        this.authIntimatenumRatio30d = authIntimatenumRatio30d;
    }

    /**
    * 获取近30天命中黑名单的亲密直接联系人个数
    *
    * @return 近30天命中黑名单的亲密直接联系人个数
    */
    public Integer getBlackIntimatenum30d(){
        return blackIntimatenum30d;
    }

    /**
    * 设置近30天命中黑名单的亲密直接联系人个数
    * 
    * @param blackIntimatenum30d 要设置的近30天命中黑名单的亲密直接联系人个数
    */
    public void setBlackIntimatenum30d(Integer blackIntimatenum30d){
        this.blackIntimatenum30d = blackIntimatenum30d;
    }

    /**
    * 获取近30天命中黑名单的亲密直接联系人个数个数占比
    *
    * @return 近30天命中黑名单的亲密直接联系人个数个数占比
    */
    public String getBlackIntimatenumRatio30d(){
        return blackIntimatenumRatio30d;
    }

    /**
    * 设置近30天命中黑名单的亲密直接联系人个数个数占比
    * 
    * @param blackIntimatenumRatio30d 要设置的近30天命中黑名单的亲密直接联系人个数个数占比
    */
    public void setBlackIntimatenumRatio30d(String blackIntimatenumRatio30d){
        this.blackIntimatenumRatio30d = blackIntimatenumRatio30d;
    }

    /**
    * 获取近30天亲密直接联系人命中黑名单的类型, 格式：逾期,欺诈
    *
    * @return 近30天亲密直接联系人命中黑名单的类型, 格式：逾期,欺诈
    */
    public String getIntimateType30d(){
        return intimateType30d;
    }

    /**
    * 设置近30天亲密直接联系人命中黑名单的类型, 格式：逾期,欺诈
    * 
    * @param intimateType30d 要设置的近30天亲密直接联系人命中黑名单的类型, 格式：逾期,欺诈
    */
    public void setIntimateType30d(String intimateType30d){
        this.intimateType30d = intimateType30d;
    }

    /**
    * 获取近30天授权过的亲密间接联系人个数
    *
    * @return 近30天授权过的亲密间接联系人个数
    */
    public Integer getAuthIntimateIndirectnum30d(){
        return authIntimateIndirectnum30d;
    }

    /**
    * 设置近30天授权过的亲密间接联系人个数
    * 
    * @param authIntimateIndirectnum30d 要设置的近30天授权过的亲密间接联系人个数
    */
    public void setAuthIntimateIndirectnum30d(Integer authIntimateIndirectnum30d){
        this.authIntimateIndirectnum30d = authIntimateIndirectnum30d;
    }

    /**
    * 获取近30天授权过的间接联系人个数占比
    *
    * @return 近30天授权过的间接联系人个数占比
    */
    public String getAuthIntimateIndirectnumRatio30d(){
        return authIntimateIndirectnumRatio30d;
    }

    /**
    * 设置近30天授权过的间接联系人个数占比
    * 
    * @param authIntimateIndirectnumRatio30d 要设置的近30天授权过的间接联系人个数占比
    */
    public void setAuthIntimateIndirectnumRatio30d(String authIntimateIndirectnumRatio30d){
        this.authIntimateIndirectnumRatio30d = authIntimateIndirectnumRatio30d;
    }

    /**
    * 获取近30天命中黑名单的间接联系人个数
    *
    * @return 近30天命中黑名单的间接联系人个数
    */
    public Integer getBlackIntimateIndirectnum30d(){
        return blackIntimateIndirectnum30d;
    }

    /**
    * 设置近30天命中黑名单的间接联系人个数
    * 
    * @param blackIntimateIndirectnum30d 要设置的近30天命中黑名单的间接联系人个数
    */
    public void setBlackIntimateIndirectnum30d(Integer blackIntimateIndirectnum30d){
        this.blackIntimateIndirectnum30d = blackIntimateIndirectnum30d;
    }

    /**
    * 获取近30天命中黑名单的亲密间接联系人个数占比
    *
    * @return 近30天命中黑名单的亲密间接联系人个数占比
    */
    public String getBlackIntimateIndirectnumRatio30d(){
        return blackIntimateIndirectnumRatio30d;
    }

    /**
    * 设置近30天命中黑名单的亲密间接联系人个数占比
    * 
    * @param blackIntimateIndirectnumRatio30d 要设置的近30天命中黑名单的亲密间接联系人个数占比
    */
    public void setBlackIntimateIndirectnumRatio30d(String blackIntimateIndirectnumRatio30d){
        this.blackIntimateIndirectnumRatio30d = blackIntimateIndirectnumRatio30d;
    }

    /**
    * 获取近30天引起亲密间接联系人在黑名单的亲密直接联系人数
    *
    * @return 近30天引起亲密间接联系人在黑名单的亲密直接联系人数
    */
    public Integer getBlackIntimateIndirectPeernum30d(){
        return blackIntimateIndirectPeernum30d;
    }

    /**
    * 设置近30天引起亲密间接联系人在黑名单的亲密直接联系人数
    * 
    * @param blackIntimateIndirectPeernum30d 要设置的近30天引起亲密间接联系人在黑名单的亲密直接联系人数
    */
    public void setBlackIntimateIndirectPeernum30d(Integer blackIntimateIndirectPeernum30d){
        this.blackIntimateIndirectPeernum30d = blackIntimateIndirectPeernum30d;
    }

    /**
    * 获取近30天引起亲密间接联系人在黑名单的亲密直接联系人数占比
    *
    * @return 近30天引起亲密间接联系人在黑名单的亲密直接联系人数占比
    */
    public String getBlackIntimateIndirectPeernumRatio30d(){
        return blackIntimateIndirectPeernumRatio30d;
    }

    /**
    * 设置近30天引起亲密间接联系人在黑名单的亲密直接联系人数占比
    * 
    * @param blackIntimateIndirectPeernumRatio30d 要设置的近30天引起亲密间接联系人在黑名单的亲密直接联系人数占比
    */
    public void setBlackIntimateIndirectPeernumRatio30d(String blackIntimateIndirectPeernumRatio30d){
        this.blackIntimateIndirectPeernumRatio30d = blackIntimateIndirectPeernumRatio30d;
    }

    /**
    * 获取近30天引起亲密间接联系人授权的亲密直接联系人数
    *
    * @return 近30天引起亲密间接联系人授权的亲密直接联系人数
    */
    public Integer getAuthIntimateIndirectPeernum30d(){
        return authIntimateIndirectPeernum30d;
    }

    /**
    * 设置近30天引起亲密间接联系人授权的亲密直接联系人数
    * 
    * @param authIntimateIndirectPeernum30d 要设置的近30天引起亲密间接联系人授权的亲密直接联系人数
    */
    public void setAuthIntimateIndirectPeernum30d(Integer authIntimateIndirectPeernum30d){
        this.authIntimateIndirectPeernum30d = authIntimateIndirectPeernum30d;
    }

    /**
    * 获取近30天引起亲密间接联系人授权的亲密直接联系人数占比
    *
    * @return 近30天引起亲密间接联系人授权的亲密直接联系人数占比
    */
    public String getAuthIntimateIndirectPeernumRatio30d(){
        return authIntimateIndirectPeernumRatio30d;
    }

    /**
    * 设置近30天引起亲密间接联系人授权的亲密直接联系人数占比
    * 
    * @param authIntimateIndirectPeernumRatio30d 要设置的近30天引起亲密间接联系人授权的亲密直接联系人数占比
    */
    public void setAuthIntimateIndirectPeernumRatio30d(String authIntimateIndirectPeernumRatio30d){
        this.authIntimateIndirectPeernumRatio30d = authIntimateIndirectPeernumRatio30d;
    }

    /**
    * 获取近90天亲密直接联系人数
    *
    * @return 近90天亲密直接联系人数
    */
    public Integer getIntimatenum90d(){
        return intimatenum90d;
    }

    /**
    * 设置近90天亲密直接联系人数
    * 
    * @param intimatenum90d 要设置的近90天亲密直接联系人数
    */
    public void setIntimatenum90d(Integer intimatenum90d){
        this.intimatenum90d = intimatenum90d;
    }

    /**
    * 获取近90天授权过的亲密直接联系人个数
    *
    * @return 近90天授权过的亲密直接联系人个数
    */
    public Integer getAuthIntimatenum90d(){
        return authIntimatenum90d;
    }

    /**
    * 设置近90天授权过的亲密直接联系人个数
    * 
    * @param authIntimatenum90d 要设置的近90天授权过的亲密直接联系人个数
    */
    public void setAuthIntimatenum90d(Integer authIntimatenum90d){
        this.authIntimatenum90d = authIntimatenum90d;
    }

    /**
    * 获取近90天授权过的亲密直接联系人个数占比
    *
    * @return 近90天授权过的亲密直接联系人个数占比
    */
    public String getAuthIntimatenumRatio90d(){
        return authIntimatenumRatio90d;
    }

    /**
    * 设置近90天授权过的亲密直接联系人个数占比
    * 
    * @param authIntimatenumRatio90d 要设置的近90天授权过的亲密直接联系人个数占比
    */
    public void setAuthIntimatenumRatio90d(String authIntimatenumRatio90d){
        this.authIntimatenumRatio90d = authIntimatenumRatio90d;
    }

    /**
    * 获取近90天命中黑名单的亲密直接联系人个数
    *
    * @return 近90天命中黑名单的亲密直接联系人个数
    */
    public Integer getBlackIntimatenum90d(){
        return blackIntimatenum90d;
    }

    /**
    * 设置近90天命中黑名单的亲密直接联系人个数
    * 
    * @param blackIntimatenum90d 要设置的近90天命中黑名单的亲密直接联系人个数
    */
    public void setBlackIntimatenum90d(Integer blackIntimatenum90d){
        this.blackIntimatenum90d = blackIntimatenum90d;
    }

    /**
    * 获取近90天命中黑名单的亲密直接联系人个数个数占比
    *
    * @return 近90天命中黑名单的亲密直接联系人个数个数占比
    */
    public String getBlackIntimatenumRatio90d(){
        return blackIntimatenumRatio90d;
    }

    /**
    * 设置近90天命中黑名单的亲密直接联系人个数个数占比
    * 
    * @param blackIntimatenumRatio90d 要设置的近90天命中黑名单的亲密直接联系人个数个数占比
    */
    public void setBlackIntimatenumRatio90d(String blackIntimatenumRatio90d){
        this.blackIntimatenumRatio90d = blackIntimatenumRatio90d;
    }

    /**
    * 获取近90天亲密直接联系人命中黑名单的类型, 格式：逾期,欺诈
    *
    * @return 近90天亲密直接联系人命中黑名单的类型, 格式：逾期,欺诈
    */
    public String getIntimateType90d(){
        return intimateType90d;
    }

    /**
    * 设置近90天亲密直接联系人命中黑名单的类型, 格式：逾期,欺诈
    * 
    * @param intimateType90d 要设置的近90天亲密直接联系人命中黑名单的类型, 格式：逾期,欺诈
    */
    public void setIntimateType90d(String intimateType90d){
        this.intimateType90d = intimateType90d;
    }

    /**
    * 获取近90天授权过的亲密间接联系人个数
    *
    * @return 近90天授权过的亲密间接联系人个数
    */
    public Integer getAuthIntimateIndirectnum90d(){
        return authIntimateIndirectnum90d;
    }

    /**
    * 设置近90天授权过的亲密间接联系人个数
    * 
    * @param authIntimateIndirectnum90d 要设置的近90天授权过的亲密间接联系人个数
    */
    public void setAuthIntimateIndirectnum90d(Integer authIntimateIndirectnum90d){
        this.authIntimateIndirectnum90d = authIntimateIndirectnum90d;
    }

    /**
    * 获取近90天授权过的间接联系人个数占比
    *
    * @return 近90天授权过的间接联系人个数占比
    */
    public String getAuthIntimateIndirectnumRatio90d(){
        return authIntimateIndirectnumRatio90d;
    }

    /**
    * 设置近90天授权过的间接联系人个数占比
    * 
    * @param authIntimateIndirectnumRatio90d 要设置的近90天授权过的间接联系人个数占比
    */
    public void setAuthIntimateIndirectnumRatio90d(String authIntimateIndirectnumRatio90d){
        this.authIntimateIndirectnumRatio90d = authIntimateIndirectnumRatio90d;
    }

    /**
    * 获取近90天命中黑名单的间接联系人个数
    *
    * @return 近90天命中黑名单的间接联系人个数
    */
    public Integer getBlackIntimateIndirectnum90d(){
        return blackIntimateIndirectnum90d;
    }

    /**
    * 设置近90天命中黑名单的间接联系人个数
    * 
    * @param blackIntimateIndirectnum90d 要设置的近90天命中黑名单的间接联系人个数
    */
    public void setBlackIntimateIndirectnum90d(Integer blackIntimateIndirectnum90d){
        this.blackIntimateIndirectnum90d = blackIntimateIndirectnum90d;
    }

    /**
    * 获取近90天命中黑名单的亲密间接联系人个数占比
    *
    * @return 近90天命中黑名单的亲密间接联系人个数占比
    */
    public String getBlackIntimateIndirectnumRatio90d(){
        return blackIntimateIndirectnumRatio90d;
    }

    /**
    * 设置近90天命中黑名单的亲密间接联系人个数占比
    * 
    * @param blackIntimateIndirectnumRatio90d 要设置的近90天命中黑名单的亲密间接联系人个数占比
    */
    public void setBlackIntimateIndirectnumRatio90d(String blackIntimateIndirectnumRatio90d){
        this.blackIntimateIndirectnumRatio90d = blackIntimateIndirectnumRatio90d;
    }

    /**
    * 获取近90天引起亲密间接联系人在黑名单的亲密直接联系人数
    *
    * @return 近90天引起亲密间接联系人在黑名单的亲密直接联系人数
    */
    public Integer getBlackIntimateIndirectPeernum90d(){
        return blackIntimateIndirectPeernum90d;
    }

    /**
    * 设置近90天引起亲密间接联系人在黑名单的亲密直接联系人数
    * 
    * @param blackIntimateIndirectPeernum90d 要设置的近90天引起亲密间接联系人在黑名单的亲密直接联系人数
    */
    public void setBlackIntimateIndirectPeernum90d(Integer blackIntimateIndirectPeernum90d){
        this.blackIntimateIndirectPeernum90d = blackIntimateIndirectPeernum90d;
    }

    /**
    * 获取近90天引起亲密间接联系人在黑名单的亲密直接联系人数占比
    *
    * @return 近90天引起亲密间接联系人在黑名单的亲密直接联系人数占比
    */
    public String getBlackIntimateIndirectPeernumRatio90d(){
        return blackIntimateIndirectPeernumRatio90d;
    }

    /**
    * 设置近90天引起亲密间接联系人在黑名单的亲密直接联系人数占比
    * 
    * @param blackIntimateIndirectPeernumRatio90d 要设置的近90天引起亲密间接联系人在黑名单的亲密直接联系人数占比
    */
    public void setBlackIntimateIndirectPeernumRatio90d(String blackIntimateIndirectPeernumRatio90d){
        this.blackIntimateIndirectPeernumRatio90d = blackIntimateIndirectPeernumRatio90d;
    }

    /**
    * 获取近90天引起亲密间接联系人授权的亲密直接联系人数
    *
    * @return 近90天引起亲密间接联系人授权的亲密直接联系人数
    */
    public Integer getAuthIntimateIndirectPeernum90d(){
        return authIntimateIndirectPeernum90d;
    }

    /**
    * 设置近90天引起亲密间接联系人授权的亲密直接联系人数
    * 
    * @param authIntimateIndirectPeernum90d 要设置的近90天引起亲密间接联系人授权的亲密直接联系人数
    */
    public void setAuthIntimateIndirectPeernum90d(Integer authIntimateIndirectPeernum90d){
        this.authIntimateIndirectPeernum90d = authIntimateIndirectPeernum90d;
    }

    /**
    * 获取近90天引起亲密间接联系人授权的亲密直接联系人数占比
    *
    * @return 近90天引起亲密间接联系人授权的亲密直接联系人数占比
    */
    public String getAuthIntimateIndirectPeernumRatio90d(){
        return authIntimateIndirectPeernumRatio90d;
    }

    /**
    * 设置近90天引起亲密间接联系人授权的亲密直接联系人数占比
    * 
    * @param authIntimateIndirectPeernumRatio90d 要设置的近90天引起亲密间接联系人授权的亲密直接联系人数占比
    */
    public void setAuthIntimateIndirectPeernumRatio90d(String authIntimateIndirectPeernumRatio90d){
        this.authIntimateIndirectPeernumRatio90d = authIntimateIndirectPeernumRatio90d;
    }

    /**
    * 获取近180天亲密直接联系人数
    *
    * @return 近180天亲密直接联系人数
    */
    public Integer getIntimatenum180d(){
        return intimatenum180d;
    }

    /**
    * 设置近180天亲密直接联系人数
    * 
    * @param intimatenum180d 要设置的近180天亲密直接联系人数
    */
    public void setIntimatenum180d(Integer intimatenum180d){
        this.intimatenum180d = intimatenum180d;
    }

    /**
    * 获取近180天授权过的亲密直接联系人个数
    *
    * @return 近180天授权过的亲密直接联系人个数
    */
    public Integer getAuthIntimatenum180d(){
        return authIntimatenum180d;
    }

    /**
    * 设置近180天授权过的亲密直接联系人个数
    * 
    * @param authIntimatenum180d 要设置的近180天授权过的亲密直接联系人个数
    */
    public void setAuthIntimatenum180d(Integer authIntimatenum180d){
        this.authIntimatenum180d = authIntimatenum180d;
    }

    /**
    * 获取近180天授权过的亲密直接联系人个数占比
    *
    * @return 近180天授权过的亲密直接联系人个数占比
    */
    public String getAuthIntimatenumRatio180d(){
        return authIntimatenumRatio180d;
    }

    /**
    * 设置近180天授权过的亲密直接联系人个数占比
    * 
    * @param authIntimatenumRatio180d 要设置的近180天授权过的亲密直接联系人个数占比
    */
    public void setAuthIntimatenumRatio180d(String authIntimatenumRatio180d){
        this.authIntimatenumRatio180d = authIntimatenumRatio180d;
    }

    /**
    * 获取近180天命中黑名单的亲密直接联系人个数
    *
    * @return 近180天命中黑名单的亲密直接联系人个数
    */
    public Integer getBlackIntimatenum180d(){
        return blackIntimatenum180d;
    }

    /**
    * 设置近180天命中黑名单的亲密直接联系人个数
    * 
    * @param blackIntimatenum180d 要设置的近180天命中黑名单的亲密直接联系人个数
    */
    public void setBlackIntimatenum180d(Integer blackIntimatenum180d){
        this.blackIntimatenum180d = blackIntimatenum180d;
    }

    /**
    * 获取近180天命中黑名单的亲密直接联系人个数个数占比
    *
    * @return 近180天命中黑名单的亲密直接联系人个数个数占比
    */
    public String getBlackIntimatenumRatio180d(){
        return blackIntimatenumRatio180d;
    }

    /**
    * 设置近180天命中黑名单的亲密直接联系人个数个数占比
    * 
    * @param blackIntimatenumRatio180d 要设置的近180天命中黑名单的亲密直接联系人个数个数占比
    */
    public void setBlackIntimatenumRatio180d(String blackIntimatenumRatio180d){
        this.blackIntimatenumRatio180d = blackIntimatenumRatio180d;
    }

    /**
    * 获取近180天亲密直接联系人命中黑名单的类型, 格式：逾期,欺诈
    *
    * @return 近180天亲密直接联系人命中黑名单的类型, 格式：逾期,欺诈
    */
    public String getIntimateType180d(){
        return intimateType180d;
    }

    /**
    * 设置近180天亲密直接联系人命中黑名单的类型, 格式：逾期,欺诈
    * 
    * @param intimateType180d 要设置的近180天亲密直接联系人命中黑名单的类型, 格式：逾期,欺诈
    */
    public void setIntimateType180d(String intimateType180d){
        this.intimateType180d = intimateType180d;
    }

    /**
    * 获取近180天授权过的亲密间接联系人个数
    *
    * @return 近180天授权过的亲密间接联系人个数
    */
    public Integer getAuthIntimateIndirectnum180d(){
        return authIntimateIndirectnum180d;
    }

    /**
    * 设置近180天授权过的亲密间接联系人个数
    * 
    * @param authIntimateIndirectnum180d 要设置的近180天授权过的亲密间接联系人个数
    */
    public void setAuthIntimateIndirectnum180d(Integer authIntimateIndirectnum180d){
        this.authIntimateIndirectnum180d = authIntimateIndirectnum180d;
    }

    /**
    * 获取近180天授权过的间接联系人个数占比
    *
    * @return 近180天授权过的间接联系人个数占比
    */
    public String getAuthIntimateIndirectnumRatio180d(){
        return authIntimateIndirectnumRatio180d;
    }

    /**
    * 设置近180天授权过的间接联系人个数占比
    * 
    * @param authIntimateIndirectnumRatio180d 要设置的近180天授权过的间接联系人个数占比
    */
    public void setAuthIntimateIndirectnumRatio180d(String authIntimateIndirectnumRatio180d){
        this.authIntimateIndirectnumRatio180d = authIntimateIndirectnumRatio180d;
    }

    /**
    * 获取近180天命中黑名单的间接联系人个数
    *
    * @return 近180天命中黑名单的间接联系人个数
    */
    public Integer getBlackIntimateIndirectnum180d(){
        return blackIntimateIndirectnum180d;
    }

    /**
    * 设置近180天命中黑名单的间接联系人个数
    * 
    * @param blackIntimateIndirectnum180d 要设置的近180天命中黑名单的间接联系人个数
    */
    public void setBlackIntimateIndirectnum180d(Integer blackIntimateIndirectnum180d){
        this.blackIntimateIndirectnum180d = blackIntimateIndirectnum180d;
    }

    /**
    * 获取近180天命中黑名单的亲密间接联系人个数占比
    *
    * @return 近180天命中黑名单的亲密间接联系人个数占比
    */
    public String getBlackIntimateIndirectnumRatio180d(){
        return blackIntimateIndirectnumRatio180d;
    }

    /**
    * 设置近180天命中黑名单的亲密间接联系人个数占比
    * 
    * @param blackIntimateIndirectnumRatio180d 要设置的近180天命中黑名单的亲密间接联系人个数占比
    */
    public void setBlackIntimateIndirectnumRatio180d(String blackIntimateIndirectnumRatio180d){
        this.blackIntimateIndirectnumRatio180d = blackIntimateIndirectnumRatio180d;
    }

    /**
    * 获取近180天引起亲密间接联系人在黑名单的亲密直接联系人数
    *
    * @return 近180天引起亲密间接联系人在黑名单的亲密直接联系人数
    */
    public Integer getBlackIntimateIndirectPeernum180d(){
        return blackIntimateIndirectPeernum180d;
    }

    /**
    * 设置近180天引起亲密间接联系人在黑名单的亲密直接联系人数
    * 
    * @param blackIntimateIndirectPeernum180d 要设置的近180天引起亲密间接联系人在黑名单的亲密直接联系人数
    */
    public void setBlackIntimateIndirectPeernum180d(Integer blackIntimateIndirectPeernum180d){
        this.blackIntimateIndirectPeernum180d = blackIntimateIndirectPeernum180d;
    }

    /**
    * 获取近180天引起亲密间接联系人在黑名单的亲密直接联系人数占比
    *
    * @return 近180天引起亲密间接联系人在黑名单的亲密直接联系人数占比
    */
    public String getBlackIntimateIndirectPeernumRatio180d(){
        return blackIntimateIndirectPeernumRatio180d;
    }

    /**
    * 设置近180天引起亲密间接联系人在黑名单的亲密直接联系人数占比
    * 
    * @param blackIntimateIndirectPeernumRatio180d 要设置的近180天引起亲密间接联系人在黑名单的亲密直接联系人数占比
    */
    public void setBlackIntimateIndirectPeernumRatio180d(String blackIntimateIndirectPeernumRatio180d){
        this.blackIntimateIndirectPeernumRatio180d = blackIntimateIndirectPeernumRatio180d;
    }

    /**
    * 获取近180天引起亲密间接联系人授权的亲密直接联系人数
    *
    * @return 近180天引起亲密间接联系人授权的亲密直接联系人数
    */
    public Integer getAuthIntimateIndirectPeernum180d(){
        return authIntimateIndirectPeernum180d;
    }

    /**
    * 设置近180天引起亲密间接联系人授权的亲密直接联系人数
    * 
    * @param authIntimateIndirectPeernum180d 要设置的近180天引起亲密间接联系人授权的亲密直接联系人数
    */
    public void setAuthIntimateIndirectPeernum180d(Integer authIntimateIndirectPeernum180d){
        this.authIntimateIndirectPeernum180d = authIntimateIndirectPeernum180d;
    }

    /**
    * 获取近180天引起亲密间接联系人授权的亲密直接联系人数占比
    *
    * @return 近180天引起亲密间接联系人授权的亲密直接联系人数占比
    */
    public String getAuthIntimateIndirectPeernumRatio180d(){
        return authIntimateIndirectPeernumRatio180d;
    }

    /**
    * 设置近180天引起亲密间接联系人授权的亲密直接联系人数占比
    * 
    * @param authIntimateIndirectPeernumRatio180d 要设置的近180天引起亲密间接联系人授权的亲密直接联系人数占比
    */
    public void setAuthIntimateIndirectPeernumRatio180d(String authIntimateIndirectPeernumRatio180d){
        this.authIntimateIndirectPeernumRatio180d = authIntimateIndirectPeernumRatio180d;
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