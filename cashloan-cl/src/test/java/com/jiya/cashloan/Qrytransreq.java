package com.jiya.cashloan;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @Auther: king
 * @Date: 2018/11/21 12:36
 * @Description:
 */
@XmlRootElement // 必须要标明这个元素
@XmlAccessorType(XmlAccessType.FIELD)
public class Qrytransreq {
    private String version;
    private String busicd;
    private String orderNo;
    private String started;
    private Trans trans;
    @XmlTransient
    private String test;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBusicd() {
        return busicd;
    }

    public void setBusicd(String busicd) {
        this.busicd = busicd;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getStarted() {
        return started;
    }

    public void setStarted(String started) {
        this.started = started;
    }

    public Trans getTrans() {
        return trans;
    }

    public void setTrans(Trans trans) {
        this.trans = trans;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
