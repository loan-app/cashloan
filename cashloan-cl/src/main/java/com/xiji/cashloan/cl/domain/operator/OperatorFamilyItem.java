package com.xiji.cashloan.cl.domain.operator;

import java.io.Serializable;

/**
 * 亲情号明细
 * Created by szb on 18/11/23.
 */
public class OperatorFamilyItem implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 亲情网手机号
     */
    private String longNumber;

    /**
     * 短号
     */
    private String shortNumber;

    /**
     * 成员类型. MASTER-家长, MEMBER-成员
     */
    private String memberType;

    /**
     * 加入日期 格式yyyy-MM-dd
     */
    private String joinDate;

    /**
     * 失效日期
     */
    private String expireDate;

    public String getLongNumber() {
        return longNumber;
    }

    public void setLongNumber(String longNumber) {
        this.longNumber = longNumber;
    }

    public String getShortNumber() {
        return shortNumber;
    }

    public void setShortNumber(String shortNumber) {
        this.shortNumber = shortNumber;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }
}
