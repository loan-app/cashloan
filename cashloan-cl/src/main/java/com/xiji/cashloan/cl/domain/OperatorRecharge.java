package com.xiji.cashloan.cl.domain;

import java.io.Serializable;

/**
 * Created by szb on 18/11/23.
 */
public class OperatorRecharge extends OperatorBase implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 充值时间(格式：yyyy-MM-dd HH:mm:ss)
     */
    private String rechargeTime;

    /**
     * 充值金额(单位:分)
     */
    private Integer amount;

    /**
     * 充值方式. e.g. 现金
     */
    private String type;

    public String getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(String rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
