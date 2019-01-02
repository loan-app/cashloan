package com.xiji.cashloan.cl.model;

/**
 * 借贷数据共享给宜信实体
 * Created by szb on 18/12/25.
 */
public class YixinShareModel {

    private String approvalStatus;
    private String idNo;
    private String loanAmount;
    private String loanDate;
    private String loanStatus;
    private String loanType;
    private String name;
    private String overdueAmount;
    private Integer overdueM3;
    private Integer overdueM6;
    private String overdueStatus;
    private Integer overdueTotal;
    private Integer periods;

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverdueAmount() {
        return overdueAmount;
    }

    public void setOverdueAmount(String overdueAmount) {
        this.overdueAmount = overdueAmount;
    }

    public Integer getOverdueM3() {
        return overdueM3;
    }

    public void setOverdueM3(Integer overdueM3) {
        this.overdueM3 = overdueM3;
    }

    public Integer getOverdueM6() {
        return overdueM6;
    }

    public void setOverdueM6(Integer overdueM6) {
        this.overdueM6 = overdueM6;
    }

    public String getOverdueStatus() {
        return overdueStatus;
    }

    public void setOverdueStatus(String overdueStatus) {
        this.overdueStatus = overdueStatus;
    }

    public Integer getOverdueTotal() {
        return overdueTotal;
    }

    public void setOverdueTotal(Integer overdueTotal) {
        this.overdueTotal = overdueTotal;
    }

    public Integer getPeriods() {
        return periods;
    }

    public void setPeriods(Integer periods) {
        this.periods = periods;
    }
}
