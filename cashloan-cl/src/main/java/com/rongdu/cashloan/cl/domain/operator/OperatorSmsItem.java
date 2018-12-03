package com.rongdu.cashloan.cl.domain.operator;

import java.io.Serializable;

/**
 * 短信明细
 * Created by szb on 18/11/23.
 */
public class OperatorSmsItem implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 收/发短信时间
     */
    private String time;

    /**
     * 对方号码
     */
    private String peerNumber;

    /**
     * 通话地(自己的)
     */
    private String location;

    /**
     * SEND-发送; RECEIVE-收取
     */
    private String sendType;

    /**
     * SMS-短信; MMS-彩信
     */
    private String msgType;

    /**
     * 业务名称. e.g. 点对点(网内)
     */
    private String serviceName;

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

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }
}
