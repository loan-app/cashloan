package com.rongdu.cashloan.cl.domain.operator;

import java.io.Serializable;

/**
 * 通话记录明细
 * Created by szb on 18/11/23.
 */
public class OperatorVoiceItem implements Serializable {

    private static final long serialVersionUID = 1L;

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
