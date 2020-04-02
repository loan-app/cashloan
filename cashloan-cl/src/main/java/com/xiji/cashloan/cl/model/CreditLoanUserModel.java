package com.xiji.cashloan.cl.model;

import java.io.Serializable;

/**
 * Created by szb on 18/12/5.
 */
public class CreditLoanUserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;

    private String realName;

    private String phone;

    private String idNo;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
}
