package com.rongdu.cashloan.cl.domain;

import java.io.Serializable;

/**
 * 运营商信息-流量详情实体
 * Created by szb on 18/11/23.
 */
public class OperatorNet extends OperatorBase implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 月份，格式 yyyy-MM
     */
    private String billMonth;

    /**
     * 流量使用时间
     */
    private String time;

    /**
     * 流量使用时长(单位:秒)
     */
    private Integer duration;


    /**
     * 流量使用地点
     */
    private String location;

    /**
     * 流量使用量，单位:KB
     */
    private Double subflow;

    /**
     * 网络类型
     */
    private String netType;

    /**
     * 业务名称(例如:无线宽带(3G/1X))
     */
    private String serviceName;

    /**
     * 通信费(单位:分)
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getSubflow() {
        return subflow;
    }

    public void setSubflow(Double subflow) {
        this.subflow = subflow;
    }

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType;
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
