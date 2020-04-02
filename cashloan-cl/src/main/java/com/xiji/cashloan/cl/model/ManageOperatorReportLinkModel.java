package com.xiji.cashloan.cl.model;

import com.xiji.cashloan.cl.domain.OperatorReportLink;

/**
 * @Auther: king
 * @Date: 2018/12/5 15:35
 * @Description:
 */
public class ManageOperatorReportLinkModel extends OperatorReportLink {
    private String operateUrl;
    private String idNo;
    private String phone;
    private String realName;

    public String getOperateUrl() {
        return operateUrl;
    }

    public void setOperateUrl(String operateUrl) {
        this.operateUrl = operateUrl;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
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
}
