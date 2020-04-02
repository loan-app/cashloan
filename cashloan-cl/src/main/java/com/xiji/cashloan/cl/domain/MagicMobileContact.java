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
 public class MagicMobileContact implements Serializable {

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
    * 涉黑评分 取值0-100, 分值越低涉黑程度越深
    */
    private Integer matchScore;

    /**
    * 近30天直接联系人数
    */
    private Integer contactnum30d;

    /**
    * 近30天授权过的直接联系人个数
    */
    private Integer authContactnum30d;

    /**
    * 近30天授权过的直接联系人个数占比
    */
    private String authContactRatio30d;

    /**
    * 近30天命中黑名单的直接联系人个数
    */
    private Integer blackContactnum30d;

    /**
    * 近30天命中黑名单的直接联系人个数个数占比
    */
    private String blackContactnumRatio30d;

    /**
    * 近30天直接联系人命中黑名单的类型, 格式：逾期,欺诈
    */
    private String contactType30d;

    /**
    * 近30天授权过的间接联系人个数
    */
    private Integer authIndirectnum30d;

    /**
    * 近30天授权过的间接联系人个数占比
    */
    private String authIndirectnumRatio30d;

    /**
    * 近30天命中黑名单的间接联系人个数
    */
    private Integer blackIndirectnum30d;

    /**
    * 近30天命中黑名单的间接联系人个数占比
    */
    private String blackIndirectnumRatio30d;

    /**
    * 近30天引起间接联系人在黑名单的直接联系人数
    */
    private Integer blackIndirectPeernum30d;

    /**
    * 近30天引起间接联系人在黑名单的直接联系人数占比
    */
    private String blackIndirectPeernumRatio30d;

    /**
    * 近30天引起间接联系人授权的直接联系人数
    */
    private Integer authIndirectPeernum30d;

    /**
    * 近30天引起间接联系人授权的直接联系人数占比
    */
    private String authIndirectPeernumRatio30d;

    /**
    * 近90天直接联系人数
    */
    private Integer contactnum90d;

    /**
    * 近90天授权过的直接联系人个数
    */
    private Integer authContactnum90d;

    /**
    * 近90天授权过的直接联系人个数占比
    */
    private String authContactRatio90d;

    /**
    * 近90天命中黑名单的直接联系人个数
    */
    private Integer blackContactnum90d;

    /**
    * 近90天命中黑名单的直接联系人个数个数占比
    */
    private String blackContactnumRatio90d;

    /**
    * 近90天直接联系人命中黑名单的类型, 格式：逾期,欺诈
    */
    private String contactType90d;

    /**
    * 近90天授权过的间接联系人个数
    */
    private Integer authIndirectnum90d;

    /**
    * 近90天授权过的间接联系人个数占比
    */
    private String authIndirectnumRatio90d;

    /**
    * 近90天命中黑名单的间接联系人个数
    */
    private Integer blackIndirectnum90d;

    /**
    * 近90天命中黑名单的间接联系人个数占比
    */
    private String blackIndirectnumRatio90d;

    /**
    * 近90天引起间接联系人在黑名单的直接联系人数
    */
    private Integer blackIndirectPeernum90d;

    /**
    * 近90天引起间接联系人在黑名单的直接联系人数占比
    */
    private String blackIndirectPeernumRatio90d;

    /**
    * 近90天引起间接联系人授权的直接联系人数
    */
    private Integer authIndirectPeernum90d;

    /**
    * 近90天引起间接联系人授权的直接联系人数占比
    */
    private String authIndirectPeernumRatio90d;

    /**
    * 近180天直接联系人数
    */
    private Integer contactnum180d;

    /**
    * 近180天授权过的直接联系人个数
    */
    private Integer authContactnum180d;

    /**
    * 近180天授权过的直接联系人个数占比
    */
    private String authContactRatio180d;

    /**
    * 近180天命中黑名单的直接联系人个数
    */
    private Integer blackContactnum180d;

    /**
    * 近180天命中黑名单的直接联系人个数个数占比
    */
    private String blackContactnumRatio180d;

    /**
    * 近180天直接联系人命中黑名单的类型, 格式：逾期,欺诈
    */
    private String contactType180d;

    /**
    * 近180天授权过的间接联系人个数
    */
    private Integer authIndirectnum180d;

    /**
    * 近180天授权过的间接联系人个数占比
    */
    private String authIndirectnumRatio180d;

    /**
    * 近180天命中黑名单的间接联系人个数
    */
    private Integer blackIndirectnum180d;

    /**
    * 近180天命中黑名单的间接联系人个数占比
    */
    private String blackIndirectnumRatio180d;

    /**
    * 近180天引起间接联系人在黑名单的直接联系人数
    */
    private Integer blackIndirectPeernum180d;

    /**
    * 近180天引起间接联系人在黑名单的直接联系人数占比
    */
    private String blackIndirectPeernumRatio180d;

    /**
    * 近180天引起间接联系人授权的直接联系人数
    */
    private Integer authIndirectPeernum180d;

    /**
    * 近180天引起间接联系人授权的直接联系人数占比
    */
    private String authIndirectPeernumRatio180d;

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
    * 获取涉黑评分 取值0-100, 分值越低涉黑程度越深
    *
    * @return 涉黑评分 取值0-100, 分值越低涉黑程度越深
    */
    public Integer getMatchScore(){
        return matchScore;
    }

    /**
    * 设置涉黑评分 取值0-100, 分值越低涉黑程度越深
    * 
    * @param matchScore 要设置的涉黑评分 取值0-100, 分值越低涉黑程度越深
    */
    public void setMatchScore(Integer matchScore){
        this.matchScore = matchScore;
    }

    /**
    * 获取近30天直接联系人数
    *
    * @return 近30天直接联系人数
    */
    public Integer getContactnum30d(){
        return contactnum30d;
    }

    /**
    * 设置近30天直接联系人数
    * 
    * @param contactnum30d 要设置的近30天直接联系人数
    */
    public void setContactnum30d(Integer contactnum30d){
        this.contactnum30d = contactnum30d;
    }

    /**
    * 获取近30天授权过的直接联系人个数
    *
    * @return 近30天授权过的直接联系人个数
    */
    public Integer getAuthContactnum30d(){
        return authContactnum30d;
    }

    /**
    * 设置近30天授权过的直接联系人个数
    * 
    * @param authContactnum30d 要设置的近30天授权过的直接联系人个数
    */
    public void setAuthContactnum30d(Integer authContactnum30d){
        this.authContactnum30d = authContactnum30d;
    }

    /**
    * 获取近30天授权过的直接联系人个数占比
    *
    * @return 近30天授权过的直接联系人个数占比
    */
    public String getAuthContactRatio30d(){
        return authContactRatio30d;
    }

    /**
    * 设置近30天授权过的直接联系人个数占比
    * 
    * @param authContactRatio30d 要设置的近30天授权过的直接联系人个数占比
    */
    public void setAuthContactRatio30d(String authContactRatio30d){
        this.authContactRatio30d = authContactRatio30d;
    }

    /**
    * 获取近30天命中黑名单的直接联系人个数
    *
    * @return 近30天命中黑名单的直接联系人个数
    */
    public Integer getBlackContactnum30d(){
        return blackContactnum30d;
    }

    /**
    * 设置近30天命中黑名单的直接联系人个数
    * 
    * @param blackContactnum30d 要设置的近30天命中黑名单的直接联系人个数
    */
    public void setBlackContactnum30d(Integer blackContactnum30d){
        this.blackContactnum30d = blackContactnum30d;
    }

    /**
    * 获取近30天命中黑名单的直接联系人个数个数占比
    *
    * @return 近30天命中黑名单的直接联系人个数个数占比
    */
    public String getBlackContactnumRatio30d(){
        return blackContactnumRatio30d;
    }

    /**
    * 设置近30天命中黑名单的直接联系人个数个数占比
    * 
    * @param blackContactnumRatio30d 要设置的近30天命中黑名单的直接联系人个数个数占比
    */
    public void setBlackContactnumRatio30d(String blackContactnumRatio30d){
        this.blackContactnumRatio30d = blackContactnumRatio30d;
    }

    /**
    * 获取近30天直接联系人命中黑名单的类型, 格式：逾期,欺诈
    *
    * @return 近30天直接联系人命中黑名单的类型, 格式：逾期,欺诈
    */
    public String getContactType30d(){
        return contactType30d;
    }

    /**
    * 设置近30天直接联系人命中黑名单的类型, 格式：逾期,欺诈
    * 
    * @param contactType30d 要设置的近30天直接联系人命中黑名单的类型, 格式：逾期,欺诈
    */
    public void setContactType30d(String contactType30d){
        this.contactType30d = contactType30d;
    }

    /**
    * 获取近30天授权过的间接联系人个数
    *
    * @return 近30天授权过的间接联系人个数
    */
    public Integer getAuthIndirectnum30d(){
        return authIndirectnum30d;
    }

    /**
    * 设置近30天授权过的间接联系人个数
    * 
    * @param authIndirectnum30d 要设置的近30天授权过的间接联系人个数
    */
    public void setAuthIndirectnum30d(Integer authIndirectnum30d){
        this.authIndirectnum30d = authIndirectnum30d;
    }

    /**
    * 获取近30天授权过的间接联系人个数占比
    *
    * @return 近30天授权过的间接联系人个数占比
    */
    public String getAuthIndirectnumRatio30d(){
        return authIndirectnumRatio30d;
    }

    /**
    * 设置近30天授权过的间接联系人个数占比
    * 
    * @param authIndirectnumRatio30d 要设置的近30天授权过的间接联系人个数占比
    */
    public void setAuthIndirectnumRatio30d(String authIndirectnumRatio30d){
        this.authIndirectnumRatio30d = authIndirectnumRatio30d;
    }

    /**
    * 获取近30天命中黑名单的间接联系人个数
    *
    * @return 近30天命中黑名单的间接联系人个数
    */
    public Integer getBlackIndirectnum30d(){
        return blackIndirectnum30d;
    }

    /**
    * 设置近30天命中黑名单的间接联系人个数
    * 
    * @param blackIndirectnum30d 要设置的近30天命中黑名单的间接联系人个数
    */
    public void setBlackIndirectnum30d(Integer blackIndirectnum30d){
        this.blackIndirectnum30d = blackIndirectnum30d;
    }

    /**
    * 获取近30天命中黑名单的间接联系人个数占比
    *
    * @return 近30天命中黑名单的间接联系人个数占比
    */
    public String getBlackIndirectnumRatio30d(){
        return blackIndirectnumRatio30d;
    }

    /**
    * 设置近30天命中黑名单的间接联系人个数占比
    * 
    * @param blackIndirectnumRatio30d 要设置的近30天命中黑名单的间接联系人个数占比
    */
    public void setBlackIndirectnumRatio30d(String blackIndirectnumRatio30d){
        this.blackIndirectnumRatio30d = blackIndirectnumRatio30d;
    }

    /**
    * 获取近30天引起间接联系人在黑名单的直接联系人数
    *
    * @return 近30天引起间接联系人在黑名单的直接联系人数
    */
    public Integer getBlackIndirectPeernum30d(){
        return blackIndirectPeernum30d;
    }

    /**
    * 设置近30天引起间接联系人在黑名单的直接联系人数
    * 
    * @param blackIndirectPeernum30d 要设置的近30天引起间接联系人在黑名单的直接联系人数
    */
    public void setBlackIndirectPeernum30d(Integer blackIndirectPeernum30d){
        this.blackIndirectPeernum30d = blackIndirectPeernum30d;
    }

    /**
    * 获取近30天引起间接联系人在黑名单的直接联系人数占比
    *
    * @return 近30天引起间接联系人在黑名单的直接联系人数占比
    */
    public String getBlackIndirectPeernumRatio30d(){
        return blackIndirectPeernumRatio30d;
    }

    /**
    * 设置近30天引起间接联系人在黑名单的直接联系人数占比
    * 
    * @param blackIndirectPeernumRatio30d 要设置的近30天引起间接联系人在黑名单的直接联系人数占比
    */
    public void setBlackIndirectPeernumRatio30d(String blackIndirectPeernumRatio30d){
        this.blackIndirectPeernumRatio30d = blackIndirectPeernumRatio30d;
    }

    /**
    * 获取近30天引起间接联系人授权的直接联系人数
    *
    * @return 近30天引起间接联系人授权的直接联系人数
    */
    public Integer getAuthIndirectPeernum30d(){
        return authIndirectPeernum30d;
    }

    /**
    * 设置近30天引起间接联系人授权的直接联系人数
    * 
    * @param authIndirectPeernum30d 要设置的近30天引起间接联系人授权的直接联系人数
    */
    public void setAuthIndirectPeernum30d(Integer authIndirectPeernum30d){
        this.authIndirectPeernum30d = authIndirectPeernum30d;
    }

    /**
    * 获取近30天引起间接联系人授权的直接联系人数占比
    *
    * @return 近30天引起间接联系人授权的直接联系人数占比
    */
    public String getAuthIndirectPeernumRatio30d(){
        return authIndirectPeernumRatio30d;
    }

    /**
    * 设置近30天引起间接联系人授权的直接联系人数占比
    * 
    * @param authIndirectPeernumRatio30d 要设置的近30天引起间接联系人授权的直接联系人数占比
    */
    public void setAuthIndirectPeernumRatio30d(String authIndirectPeernumRatio30d){
        this.authIndirectPeernumRatio30d = authIndirectPeernumRatio30d;
    }

    /**
    * 获取近90天直接联系人数
    *
    * @return 近90天直接联系人数
    */
    public Integer getContactnum90d(){
        return contactnum90d;
    }

    /**
    * 设置近90天直接联系人数
    * 
    * @param contactnum90d 要设置的近90天直接联系人数
    */
    public void setContactnum90d(Integer contactnum90d){
        this.contactnum90d = contactnum90d;
    }

    /**
    * 获取近90天授权过的直接联系人个数
    *
    * @return 近90天授权过的直接联系人个数
    */
    public Integer getAuthContactnum90d(){
        return authContactnum90d;
    }

    /**
    * 设置近90天授权过的直接联系人个数
    * 
    * @param authContactnum90d 要设置的近90天授权过的直接联系人个数
    */
    public void setAuthContactnum90d(Integer authContactnum90d){
        this.authContactnum90d = authContactnum90d;
    }

    /**
    * 获取近90天授权过的直接联系人个数占比
    *
    * @return 近90天授权过的直接联系人个数占比
    */
    public String getAuthContactRatio90d(){
        return authContactRatio90d;
    }

    /**
    * 设置近90天授权过的直接联系人个数占比
    * 
    * @param authContactRatio90d 要设置的近90天授权过的直接联系人个数占比
    */
    public void setAuthContactRatio90d(String authContactRatio90d){
        this.authContactRatio90d = authContactRatio90d;
    }

    /**
    * 获取近90天命中黑名单的直接联系人个数
    *
    * @return 近90天命中黑名单的直接联系人个数
    */
    public Integer getBlackContactnum90d(){
        return blackContactnum90d;
    }

    /**
    * 设置近90天命中黑名单的直接联系人个数
    * 
    * @param blackContactnum90d 要设置的近90天命中黑名单的直接联系人个数
    */
    public void setBlackContactnum90d(Integer blackContactnum90d){
        this.blackContactnum90d = blackContactnum90d;
    }

    /**
    * 获取近90天命中黑名单的直接联系人个数个数占比
    *
    * @return 近90天命中黑名单的直接联系人个数个数占比
    */
    public String getBlackContactnumRatio90d(){
        return blackContactnumRatio90d;
    }

    /**
    * 设置近90天命中黑名单的直接联系人个数个数占比
    * 
    * @param blackContactnumRatio90d 要设置的近90天命中黑名单的直接联系人个数个数占比
    */
    public void setBlackContactnumRatio90d(String blackContactnumRatio90d){
        this.blackContactnumRatio90d = blackContactnumRatio90d;
    }

    /**
    * 获取近90天直接联系人命中黑名单的类型, 格式：逾期,欺诈
    *
    * @return 近90天直接联系人命中黑名单的类型, 格式：逾期,欺诈
    */
    public String getContactType90d(){
        return contactType90d;
    }

    /**
    * 设置近90天直接联系人命中黑名单的类型, 格式：逾期,欺诈
    * 
    * @param contactType90d 要设置的近90天直接联系人命中黑名单的类型, 格式：逾期,欺诈
    */
    public void setContactType90d(String contactType90d){
        this.contactType90d = contactType90d;
    }

    /**
    * 获取近90天授权过的间接联系人个数
    *
    * @return 近90天授权过的间接联系人个数
    */
    public Integer getAuthIndirectnum90d(){
        return authIndirectnum90d;
    }

    /**
    * 设置近90天授权过的间接联系人个数
    * 
    * @param authIndirectnum90d 要设置的近90天授权过的间接联系人个数
    */
    public void setAuthIndirectnum90d(Integer authIndirectnum90d){
        this.authIndirectnum90d = authIndirectnum90d;
    }

    /**
    * 获取近90天授权过的间接联系人个数占比
    *
    * @return 近90天授权过的间接联系人个数占比
    */
    public String getAuthIndirectnumRatio90d(){
        return authIndirectnumRatio90d;
    }

    /**
    * 设置近90天授权过的间接联系人个数占比
    * 
    * @param authIndirectnumRatio90d 要设置的近90天授权过的间接联系人个数占比
    */
    public void setAuthIndirectnumRatio90d(String authIndirectnumRatio90d){
        this.authIndirectnumRatio90d = authIndirectnumRatio90d;
    }

    /**
    * 获取近90天命中黑名单的间接联系人个数
    *
    * @return 近90天命中黑名单的间接联系人个数
    */
    public Integer getBlackIndirectnum90d(){
        return blackIndirectnum90d;
    }

    /**
    * 设置近90天命中黑名单的间接联系人个数
    * 
    * @param blackIndirectnum90d 要设置的近90天命中黑名单的间接联系人个数
    */
    public void setBlackIndirectnum90d(Integer blackIndirectnum90d){
        this.blackIndirectnum90d = blackIndirectnum90d;
    }

    /**
    * 获取近90天命中黑名单的间接联系人个数占比
    *
    * @return 近90天命中黑名单的间接联系人个数占比
    */
    public String getBlackIndirectnumRatio90d(){
        return blackIndirectnumRatio90d;
    }

    /**
    * 设置近90天命中黑名单的间接联系人个数占比
    * 
    * @param blackIndirectnumRatio90d 要设置的近90天命中黑名单的间接联系人个数占比
    */
    public void setBlackIndirectnumRatio90d(String blackIndirectnumRatio90d){
        this.blackIndirectnumRatio90d = blackIndirectnumRatio90d;
    }

    /**
    * 获取近90天引起间接联系人在黑名单的直接联系人数
    *
    * @return 近90天引起间接联系人在黑名单的直接联系人数
    */
    public Integer getBlackIndirectPeernum90d(){
        return blackIndirectPeernum90d;
    }

    /**
    * 设置近90天引起间接联系人在黑名单的直接联系人数
    * 
    * @param blackIndirectPeernum90d 要设置的近90天引起间接联系人在黑名单的直接联系人数
    */
    public void setBlackIndirectPeernum90d(Integer blackIndirectPeernum90d){
        this.blackIndirectPeernum90d = blackIndirectPeernum90d;
    }

    /**
    * 获取近90天引起间接联系人在黑名单的直接联系人数占比
    *
    * @return 近90天引起间接联系人在黑名单的直接联系人数占比
    */
    public String getBlackIndirectPeernumRatio90d(){
        return blackIndirectPeernumRatio90d;
    }

    /**
    * 设置近90天引起间接联系人在黑名单的直接联系人数占比
    * 
    * @param blackIndirectPeernumRatio90d 要设置的近90天引起间接联系人在黑名单的直接联系人数占比
    */
    public void setBlackIndirectPeernumRatio90d(String blackIndirectPeernumRatio90d){
        this.blackIndirectPeernumRatio90d = blackIndirectPeernumRatio90d;
    }

    /**
    * 获取近90天引起间接联系人授权的直接联系人数
    *
    * @return 近90天引起间接联系人授权的直接联系人数
    */
    public Integer getAuthIndirectPeernum90d(){
        return authIndirectPeernum90d;
    }

    /**
    * 设置近90天引起间接联系人授权的直接联系人数
    * 
    * @param authIndirectPeernum90d 要设置的近90天引起间接联系人授权的直接联系人数
    */
    public void setAuthIndirectPeernum90d(Integer authIndirectPeernum90d){
        this.authIndirectPeernum90d = authIndirectPeernum90d;
    }

    /**
    * 获取近90天引起间接联系人授权的直接联系人数占比
    *
    * @return 近90天引起间接联系人授权的直接联系人数占比
    */
    public String getAuthIndirectPeernumRatio90d(){
        return authIndirectPeernumRatio90d;
    }

    /**
    * 设置近90天引起间接联系人授权的直接联系人数占比
    * 
    * @param authIndirectPeernumRatio90d 要设置的近90天引起间接联系人授权的直接联系人数占比
    */
    public void setAuthIndirectPeernumRatio90d(String authIndirectPeernumRatio90d){
        this.authIndirectPeernumRatio90d = authIndirectPeernumRatio90d;
    }

    /**
    * 获取近180天直接联系人数
    *
    * @return 近180天直接联系人数
    */
    public Integer getContactnum180d(){
        return contactnum180d;
    }

    /**
    * 设置近180天直接联系人数
    * 
    * @param contactnum180d 要设置的近180天直接联系人数
    */
    public void setContactnum180d(Integer contactnum180d){
        this.contactnum180d = contactnum180d;
    }

    /**
    * 获取近180天授权过的直接联系人个数
    *
    * @return 近180天授权过的直接联系人个数
    */
    public Integer getAuthContactnum180d(){
        return authContactnum180d;
    }

    /**
    * 设置近180天授权过的直接联系人个数
    * 
    * @param authContactnum180d 要设置的近180天授权过的直接联系人个数
    */
    public void setAuthContactnum180d(Integer authContactnum180d){
        this.authContactnum180d = authContactnum180d;
    }

    /**
    * 获取近180天授权过的直接联系人个数占比
    *
    * @return 近180天授权过的直接联系人个数占比
    */
    public String getAuthContactRatio180d(){
        return authContactRatio180d;
    }

    /**
    * 设置近180天授权过的直接联系人个数占比
    * 
    * @param authContactRatio180d 要设置的近180天授权过的直接联系人个数占比
    */
    public void setAuthContactRatio180d(String authContactRatio180d){
        this.authContactRatio180d = authContactRatio180d;
    }

    /**
    * 获取近180天命中黑名单的直接联系人个数
    *
    * @return 近180天命中黑名单的直接联系人个数
    */
    public Integer getBlackContactnum180d(){
        return blackContactnum180d;
    }

    /**
    * 设置近180天命中黑名单的直接联系人个数
    * 
    * @param blackContactnum180d 要设置的近180天命中黑名单的直接联系人个数
    */
    public void setBlackContactnum180d(Integer blackContactnum180d){
        this.blackContactnum180d = blackContactnum180d;
    }

    /**
    * 获取近180天命中黑名单的直接联系人个数个数占比
    *
    * @return 近180天命中黑名单的直接联系人个数个数占比
    */
    public String getBlackContactnumRatio180d(){
        return blackContactnumRatio180d;
    }

    /**
    * 设置近180天命中黑名单的直接联系人个数个数占比
    * 
    * @param blackContactnumRatio180d 要设置的近180天命中黑名单的直接联系人个数个数占比
    */
    public void setBlackContactnumRatio180d(String blackContactnumRatio180d){
        this.blackContactnumRatio180d = blackContactnumRatio180d;
    }

    /**
    * 获取近180天直接联系人命中黑名单的类型, 格式：逾期,欺诈
    *
    * @return 近180天直接联系人命中黑名单的类型, 格式：逾期,欺诈
    */
    public String getContactType180d(){
        return contactType180d;
    }

    /**
    * 设置近180天直接联系人命中黑名单的类型, 格式：逾期,欺诈
    * 
    * @param contactType180d 要设置的近180天直接联系人命中黑名单的类型, 格式：逾期,欺诈
    */
    public void setContactType180d(String contactType180d){
        this.contactType180d = contactType180d;
    }

    /**
    * 获取近180天授权过的间接联系人个数
    *
    * @return 近180天授权过的间接联系人个数
    */
    public Integer getAuthIndirectnum180d(){
        return authIndirectnum180d;
    }

    /**
    * 设置近180天授权过的间接联系人个数
    * 
    * @param authIndirectnum180d 要设置的近180天授权过的间接联系人个数
    */
    public void setAuthIndirectnum180d(Integer authIndirectnum180d){
        this.authIndirectnum180d = authIndirectnum180d;
    }

    /**
    * 获取近180天授权过的间接联系人个数占比
    *
    * @return 近180天授权过的间接联系人个数占比
    */
    public String getAuthIndirectnumRatio180d(){
        return authIndirectnumRatio180d;
    }

    /**
    * 设置近180天授权过的间接联系人个数占比
    * 
    * @param authIndirectnumRatio180d 要设置的近180天授权过的间接联系人个数占比
    */
    public void setAuthIndirectnumRatio180d(String authIndirectnumRatio180d){
        this.authIndirectnumRatio180d = authIndirectnumRatio180d;
    }

    /**
    * 获取近180天命中黑名单的间接联系人个数
    *
    * @return 近180天命中黑名单的间接联系人个数
    */
    public Integer getBlackIndirectnum180d(){
        return blackIndirectnum180d;
    }

    /**
    * 设置近180天命中黑名单的间接联系人个数
    * 
    * @param blackIndirectnum180d 要设置的近180天命中黑名单的间接联系人个数
    */
    public void setBlackIndirectnum180d(Integer blackIndirectnum180d){
        this.blackIndirectnum180d = blackIndirectnum180d;
    }

    /**
    * 获取近180天命中黑名单的间接联系人个数占比
    *
    * @return 近180天命中黑名单的间接联系人个数占比
    */
    public String getBlackIndirectnumRatio180d(){
        return blackIndirectnumRatio180d;
    }

    /**
    * 设置近180天命中黑名单的间接联系人个数占比
    * 
    * @param blackIndirectnumRatio180d 要设置的近180天命中黑名单的间接联系人个数占比
    */
    public void setBlackIndirectnumRatio180d(String blackIndirectnumRatio180d){
        this.blackIndirectnumRatio180d = blackIndirectnumRatio180d;
    }

    /**
    * 获取近180天引起间接联系人在黑名单的直接联系人数
    *
    * @return 近180天引起间接联系人在黑名单的直接联系人数
    */
    public Integer getBlackIndirectPeernum180d(){
        return blackIndirectPeernum180d;
    }

    /**
    * 设置近180天引起间接联系人在黑名单的直接联系人数
    * 
    * @param blackIndirectPeernum180d 要设置的近180天引起间接联系人在黑名单的直接联系人数
    */
    public void setBlackIndirectPeernum180d(Integer blackIndirectPeernum180d){
        this.blackIndirectPeernum180d = blackIndirectPeernum180d;
    }

    /**
    * 获取近180天引起间接联系人在黑名单的直接联系人数占比
    *
    * @return 近180天引起间接联系人在黑名单的直接联系人数占比
    */
    public String getBlackIndirectPeernumRatio180d(){
        return blackIndirectPeernumRatio180d;
    }

    /**
    * 设置近180天引起间接联系人在黑名单的直接联系人数占比
    * 
    * @param blackIndirectPeernumRatio180d 要设置的近180天引起间接联系人在黑名单的直接联系人数占比
    */
    public void setBlackIndirectPeernumRatio180d(String blackIndirectPeernumRatio180d){
        this.blackIndirectPeernumRatio180d = blackIndirectPeernumRatio180d;
    }

    /**
    * 获取近180天引起间接联系人授权的直接联系人数
    *
    * @return 近180天引起间接联系人授权的直接联系人数
    */
    public Integer getAuthIndirectPeernum180d(){
        return authIndirectPeernum180d;
    }

    /**
    * 设置近180天引起间接联系人授权的直接联系人数
    * 
    * @param authIndirectPeernum180d 要设置的近180天引起间接联系人授权的直接联系人数
    */
    public void setAuthIndirectPeernum180d(Integer authIndirectPeernum180d){
        this.authIndirectPeernum180d = authIndirectPeernum180d;
    }

    /**
    * 获取近180天引起间接联系人授权的直接联系人数占比
    *
    * @return 近180天引起间接联系人授权的直接联系人数占比
    */
    public String getAuthIndirectPeernumRatio180d(){
        return authIndirectPeernumRatio180d;
    }

    /**
    * 设置近180天引起间接联系人授权的直接联系人数占比
    * 
    * @param authIndirectPeernumRatio180d 要设置的近180天引起间接联系人授权的直接联系人数占比
    */
    public void setAuthIndirectPeernumRatio180d(String authIndirectPeernumRatio180d){
        this.authIndirectPeernumRatio180d = authIndirectPeernumRatio180d;
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