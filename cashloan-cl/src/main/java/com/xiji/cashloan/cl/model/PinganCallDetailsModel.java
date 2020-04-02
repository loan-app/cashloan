package com.xiji.cashloan.cl.model;

/**
 * 请求凭安通话详单实体
 * Created by szb on 18/12/27.
 */
public class PinganCallDetailsModel {

    private String duration;

    private String contact_addr;

    private String called_type;

    private String cost;

    private String contact;

    private String call_time;

    private String call_model;

    private String call_addr;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getContact_addr() {
        return contact_addr;
    }

    public void setContact_addr(String contact_addr) {
        this.contact_addr = contact_addr;
    }

    public String getCalled_type() {
        return called_type;
    }

    public void setCalled_type(String called_type) {
        this.called_type = called_type;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCall_time() {
        return call_time;
    }

    public void setCall_time(String call_time) {
        this.call_time = call_time;
    }

    public String getCall_model() {
        return call_model;
    }

    public void setCall_model(String call_model) {
        this.call_model = call_model;
    }

    public String getCall_addr() {
        return call_addr;
    }

    public void setCall_addr(String call_addr) {
        this.call_addr = call_addr;
    }
}
