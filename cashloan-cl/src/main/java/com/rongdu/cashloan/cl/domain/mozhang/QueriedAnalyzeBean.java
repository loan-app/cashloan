package com.rongdu.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lu on 2018/9/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueriedAnalyzeBean {
    /**
     * org_type : string
     * loan_cnt_15d : 0
     * loan_cnt_30d : 0
     * loan_cnt_90d : 0
     * loan_cnt_180d : int
     */

    @JsonProperty("org_type")
    private String orgType;
    @JsonProperty("loan_cnt_15d")
    private int loanCnt15d;
    @JsonProperty("loan_cnt_30d")
    private int loanCnt30d;
    @JsonProperty("loan_cnt_90d")
    private int loanCnt90d;
    @JsonProperty("loan_cnt_180d")
    private String loanCnt180d;

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
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

    public String getLoanCnt180d() {
        return loanCnt180d;
    }

    public void setLoanCnt180d(String loanCnt180d) {
        this.loanCnt180d = loanCnt180d;
    }
}
