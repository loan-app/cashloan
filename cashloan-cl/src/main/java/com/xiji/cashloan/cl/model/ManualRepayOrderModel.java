package com.xiji.cashloan.cl.model;

import com.xiji.cashloan.cl.domain.ManualRepayOrder;
import com.xiji.cashloan.core.model.BorrowModel;
import tool.util.BigDecimalUtil;

import java.util.Date;

/**
 * Created by szb on 19/3/8.
 */
public class ManualRepayOrderModel extends ManualRepayOrder{
    /**
     * 10未分配;20已分配
     */

    /** 未分配*/
    public static final String STATE_ORDER_NO_ALLOT = "10";
    /** 已分配 */
    public static final String STATE_ORDER_ALLOT = "20";

    /**
     * 还款金额
     */
    private Double repayAmount;

    /**
     * 还款时间
     */
    private Date repayTime;

    /**
     * 还款状态 10-已还款 20-未还款
     */
    private String state;

    /**
     * 借款金额
     */
    private Double borrowAmount;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 真实姓名
     */
    private String realName;

    public Double getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(Double repayAmount) {
        this.repayAmount = repayAmount;
    }

    public Date getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getBorrowAmount() {
        return borrowAmount;
    }

    public void setBorrowAmount(Double borrowAmount) {
        this.borrowAmount = borrowAmount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
