package com.rongdu.cashloan.cl.domain;

import java.io.Serializable;

/**
 * 运营商信息-通话记录实体
 *
 * @author caitt
 * @version 1.0.0
 * @date 2017-03-13 16:44:01
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * <p/>
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public class OperatorVoice extends OperatorBase implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 通话记录月份，格式 yyyy-MM
     */
    private String billMonth;

    /**
     * 通话时间，格式：yyyy-MM-dd HH:mm:ss
     */
    private String time;

    /**
     * 对方通话号码
     */
    private String peerNumber;

    /**
     * 通话地(自己的)
     */
    private String location;

    /**
     * 通话地类型. e.g.省内漫游
     */
    private String locationType;

    /**
     * 通话时长(单位:秒)
     */
    private Integer duration;

    /**
     * 呼叫类型. DIAL-主叫; DIALED-被叫
     */
    private String dialType;

    /**
     * 通话费(单位:分)
     */
    private Integer fee;

    public String getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(String billMonth) {
        this.billMonth = billMonth;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPeerNumber() {
        return peerNumber;
    }

    public void setPeerNumber(String peerNumber) {
        this.peerNumber = peerNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDialType() {
        return dialType;
    }

    public void setDialType(String dialType) {
        this.dialType = dialType;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }
}