package com.jiya.cashloan;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Auther: king
 * @Date: 2018/11/21 12:37
 * @Description:
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Trans {
    private String merdt;
    private String orderNo;
    private String amt;

    public String getMerdt() {
        return merdt;
    }

    public void setMerdt(String merdt) {
        this.merdt = merdt;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }
}
