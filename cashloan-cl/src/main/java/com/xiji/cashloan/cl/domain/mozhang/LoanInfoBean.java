package com.xiji.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lu on 2018/9/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanInfoBean {
    /**
     * loan_org_cnt : 0
     * loan_cnt : 0
     * loan_org_cnt_15d : 0
     * loan_org_cnt_30d : 0
     * loan_org_cnt_90d : 0
     * loan_org_cnt_180d : 0
     * loan_cnt_15d : 0
     * loan_cnt_30d : 0
     * loan_cnt_90d : 0
     * loan_cnt_180d : 0
     */

    @JsonProperty("loan_org_cnt")
    private int loanOrgCnt;
    @JsonProperty("loan_cnt")
    private int loanCnt;
    @JsonProperty("loan_org_cnt_15d")
    private int loanOrgCnt15d;
    @JsonProperty("loan_org_cnt_30d")
    private int loanOrgCnt30d;
    @JsonProperty("loan_org_cnt_90d")
    private int loanOrgCnt90d;
    @JsonProperty("loan_org_cnt_180d")
    private int loanOrgCnt180d;
    @JsonProperty("loan_cnt_15d")
    private int loanCnt15d;
    @JsonProperty("loan_cnt_30d")
    private int loanCnt30d;
    @JsonProperty("loan_cnt_90d")
    private int loanCnt90d;
    @JsonProperty("loan_cnt_180d")
    private int loanCnt180d;

    public int getLoanOrgCnt() {
        return loanOrgCnt;
    }

    public void setLoanOrgCnt(int loanOrgCnt) {
        this.loanOrgCnt = loanOrgCnt;
    }

    public int getLoanCnt() {
        return loanCnt;
    }

    public void setLoanCnt(int loanCnt) {
        this.loanCnt = loanCnt;
    }

    public int getLoanOrgCnt15d() {
        return loanOrgCnt15d;
    }

    public void setLoanOrgCnt15d(int loanOrgCnt15d) {
        this.loanOrgCnt15d = loanOrgCnt15d;
    }

    public int getLoanOrgCnt30d() {
        return loanOrgCnt30d;
    }

    public void setLoanOrgCnt30d(int loanOrgCnt30d) {
        this.loanOrgCnt30d = loanOrgCnt30d;
    }

    public int getLoanOrgCnt90d() {
        return loanOrgCnt90d;
    }

    public void setLoanOrgCnt90d(int loanOrgCnt90d) {
        this.loanOrgCnt90d = loanOrgCnt90d;
    }

    public int getLoanOrgCnt180d() {
        return loanOrgCnt180d;
    }

    public void setLoanOrgCnt180d(int loanOrgCnt180d) {
        this.loanOrgCnt180d = loanOrgCnt180d;
    }

    public int getLoanCnt15d() {
        return loanCnt15d;
    }

    public void setLoanCnt15d(int loanCnt15d) {
        this.loanCnt15d = loanCnt15d;
    }

    public int getLoanCnt30d() {
        return loanCnt30d;
    }

    public void setLoanCnt30d(int loanCnt30d) {
        this.loanCnt30d = loanCnt30d;
    }

    public int getLoanCnt90d() {
        return loanCnt90d;
    }

    public void setLoanCnt90d(int loanCnt90d) {
        this.loanCnt90d = loanCnt90d;
    }

    public int getLoanCnt180d() {
        return loanCnt180d;
    }

    public void setLoanCnt180d(int loanCnt180d) {
        this.loanCnt180d = loanCnt180d;
    }
}
