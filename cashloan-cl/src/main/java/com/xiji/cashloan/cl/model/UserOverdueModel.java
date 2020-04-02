package com.xiji.cashloan.cl.model;

/**
 * 绿盟逾期黑名单参数
 */
public class UserOverdueModel {
    private String idcard;		//身份证号
    private String phone;		//手机号
    private String name;		//姓名
    private Integer type;		//黑名单类型：1.逾期超过3天

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
