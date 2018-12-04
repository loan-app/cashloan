package com.xiji.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author wnb
 * @date 2018/12/03
 * @version 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RiskQqgroupBean {
    /**
     * loan_groupcnt : 0
     * installment_groupcnt : 0
     * financial_management_groupcnt : 0
     * woolen_groupcnt : 0
     * gambling_groupcnt : int
     */

    @JsonProperty("loan_groupcnt")
    private int loanGroupcnt;
    @JsonProperty("installment_groupcnt")
    private int installmentGroupcnt;
    @JsonProperty("financial_management_groupcnt")
    private int financialManagementGroupcnt;
    @JsonProperty("woolen_groupcnt")
    private int woolenGroupcnt;
    @JsonProperty("gambling_groupcnt")
    private String gamblingGroupcnt;

    public int getLoanGroupcnt() {
        return loanGroupcnt;
    }

    public void setLoanGroupcnt(int loanGroupcnt) {
        this.loanGroupcnt = loanGroupcnt;
    }

    public int getInstallmentGroupcnt() {
        return installmentGroupcnt;
    }

    public void setInstallmentGroupcnt(int installmentGroupcnt) {
        this.installmentGroupcnt = installmentGroupcnt;
    }

    public int getFinancialManagementGroupcnt() {
        return financialManagementGroupcnt;
    }

    public void setFinancialManagementGroupcnt(int financialManagementGroupcnt) {
        this.financialManagementGroupcnt = financialManagementGroupcnt;
    }

    public int getWoolenGroupcnt() {
        return woolenGroupcnt;
    }

    public void setWoolenGroupcnt(int woolenGroupcnt) {
        this.woolenGroupcnt = woolenGroupcnt;
    }

    public String getGamblingGroupcnt() {
        return gamblingGroupcnt;
    }

    public void setGamblingGroupcnt(String gamblingGroupcnt) {
        this.gamblingGroupcnt = gamblingGroupcnt;
    }
}
