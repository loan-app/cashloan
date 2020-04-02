package com.xiji.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 魔蝎运营商数据-基础信息类实体
 *
 * @author szb
 * @version 1.0.0
 * @date 2018-11-23 11:13:32
 */
public class OperatorBasic extends OperatorBase implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    private String name;

    /**
     * 证件号
     */
    private String idcard;

    /**
     * CHINA_MOBILE-移动; CHINA_UNICOM-联通; CHINA_TELECOM-电信
     */
    private String carrier;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 开卡时间
     */
    private Date openTime;

    /**
     * 星级
     */
    private String level;

    /**
     * 当前手机套餐名称
     */
    private String packageName;

    /**
     * 可用余额(单位分)
     */
    private Integer availableBalance;

    /**
     * 实际可用余额(单位分)
     */
    private Integer realBalance;

    /**
     * 本机号码状态
     */
    private String state;

    /**
     * 实名状态 -1:未知 0-未实名 1-已实名
     */
    private Integer reliability;

    /**
     * 获取姓名
     *
     * @return 姓名
     */
    public String getName(){
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 要设置的姓名
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * 获取证件号
     *
     * @return 证件号
     */
    public String getIdcard(){
        return idcard;
    }

    /**
     * 设置证件号
     *
     * @param idcard 要设置的证件号
     */
    public void setIdcard(String idcard){
        this.idcard = idcard;
    }

    /**
     * 获取CHINA_MOBILE-移动; CHINA_UNICOM-联通; CHINA_TELECOM-电信
     *
     * @return CHINA_MOBILE-移动; CHINA_UNICOM-联通; CHINA_TELECOM-电信
     */
    public String getCarrier(){
        return carrier;
    }

    /**
     * 设置CHINA_MOBILE-移动; CHINA_UNICOM-联通; CHINA_TELECOM-电信
     *
     * @param carrier 要设置的CHINA_MOBILE-移动; CHINA_UNICOM-联通; CHINA_TELECOM-电信
     */
    public void setCarrier(String carrier){
        this.carrier = carrier;
    }

    /**
     * 获取省份
     *
     * @return 省份
     */
    public String getProvince(){
        return province;
    }

    /**
     * 设置省份
     *
     * @param province 要设置的省份
     */
    public void setProvince(String province){
        this.province = province;
    }

    /**
     * 获取城市
     *
     * @return 城市
     */
    public String getCity(){
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 要设置的城市
     */
    public void setCity(String city){
        this.city = city;
    }

    /**
     * 获取开卡时间
     *
     * @return 开卡时间
     */
    public Date getOpenTime(){
        return openTime;
    }

    /**
     * 设置开卡时间
     *
     * @param openTime 要设置的开卡时间
     */
    public void setOpenTime(Date openTime){
        this.openTime = openTime;
    }

    /**
     * 获取星级
     *
     * @return 星级
     */
    public String getLevel(){
        return level;
    }

    /**
     * 设置星级
     *
     * @param level 要设置的星级
     */
    public void setLevel(String level){
        this.level = level;
    }

    /**
     * 获取当前手机套餐名称
     *
     * @return 当前手机套餐名称
     */
    public String getPackageName(){
        return packageName;
    }

    /**
     * 设置当前手机套餐名称
     *
     * @param packageName 要设置的当前手机套餐名称
     */
    public void setPackageName(String packageName){
        this.packageName = packageName;
    }

    /**
     * 获取可用余额(单位分)
     *
     * @return 可用余额(单位分)
     */
    public Integer getAvailableBalance(){
        return availableBalance;
    }

    /**
     * 设置可用余额(单位分)
     *
     * @param availableBalance 要设置的可用余额(单位分)
     */
    public void setAvailableBalance(Integer availableBalance){
        this.availableBalance = availableBalance;
    }

    /**
     * 获取实际可用余额(单位分)
     *
     * @return 实际可用余额(单位分)
     */
    public Integer getRealBalance(){
        return realBalance;
    }

    /**
     * 设置实际可用余额(单位分)
     *
     * @param realBalance 要设置的实际可用余额(单位分)
     */
    public void setRealBalance(Integer realBalance){
        this.realBalance = realBalance;
    }

    /**
     * 获取本机号码状态
     *
     * @return
     */
    public String getState(){
        return state;
    }

    /**
     * 设置
     *
     * @param state 要设置的
     */
    public void setState(String state){
        this.state = state;
    }

    /**
     * 获取实名状态 -1:未知 0-未实名 1-已实名
     *
     * @return 实名状态 -1:未知 0-未实名 1-已实名
     */
    public Integer getReliability(){
        return reliability;
    }

    /**
     * 设置实名状态 -1:未知 0-未实名 1-已实名
     *
     * @param reliability 要设置的实名状态 -1:未知 0-未实名 1-已实名
     */
    public void setReliability(Integer reliability){
        this.reliability = reliability;
    }

}