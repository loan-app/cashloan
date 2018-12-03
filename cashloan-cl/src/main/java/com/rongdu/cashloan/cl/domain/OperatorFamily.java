package com.rongdu.cashloan.cl.domain;

import java.io.Serializable;

/**
 * 运营商信息-亲情网实体
 * Created by szb on 18/11/23.
 */
public class OperatorFamily extends OperatorBase implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 亲情网编号
     */
    private Integer familyNum;

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

    public Integer getFamilyNum() {
        return familyNum;
    }

    public void setFamilyNum(Integer familyNum) {
        this.familyNum = familyNum;
    }

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
