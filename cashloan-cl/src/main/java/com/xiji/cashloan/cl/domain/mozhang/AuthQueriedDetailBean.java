package com.xiji.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by lu on 2018/9/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthQueriedDetailBean {
    /**
     * register_info : {"other_count":0,"org_count":0,"org_types":["string"]}
     * queried_detail_org_count : 0
     * queried_analyze : [{"org_type":"string","loan_cnt_15d":0,"loan_cnt_30d":0,"loan_cnt_90d":0,"loan_cnt_180d":"int"}]
     * queried_infos : [{"date":"string","org_type":"string","is_self":true}]
     * loan_org_cnt_all : 0
     * loan_info : {"loan_org_cnt":0,"loan_cnt":0,"loan_org_cnt_15d":0,"loan_org_cnt_30d":0,"loan_org_cnt_90d":0,"loan_org_cnt_180d":0,"loan_cnt_15d":0,"loan_cnt_30d":0,"loan_cnt_90d":0,"loan_cnt_180d":0}
     */

    @JsonProperty("register_info")
    private RegisterInfoBean registerInfo;
    @JsonProperty("queried_detail_org_count")
    private int queriedDetailOrgCount;
    @JsonProperty("loan_org_cnt_all")
    private int loanOrgCntAll;
    @JsonProperty("loan_info")
    private LoanInfoBean loanInfo;
    @JsonProperty("queried_analyze")
    private List<QueriedAnalyzeBean> queriedAnalyze;
    @JsonProperty("queried_infos")
    private List<QueriedInfosBean> queriedInfos;

    public RegisterInfoBean getRegisterInfo() {
        return registerInfo;
    }

    public void setRegisterInfo(RegisterInfoBean registerInfo) {
        this.registerInfo = registerInfo;
    }

    public int getQueriedDetailOrgCount() {
        return queriedDetailOrgCount;
    }

    public void setQueriedDetailOrgCount(int queriedDetailOrgCount) {
        this.queriedDetailOrgCount = queriedDetailOrgCount;
    }

    public int getLoanOrgCntAll() {
        return loanOrgCntAll;
    }

    public void setLoanOrgCntAll(int loanOrgCntAll) {
        this.loanOrgCntAll = loanOrgCntAll;
    }

    public LoanInfoBean getLoanInfo() {
        return loanInfo;
    }

    public void setLoanInfo(LoanInfoBean loanInfo) {
        this.loanInfo = loanInfo;
    }

    public List<QueriedAnalyzeBean> getQueriedAnalyze() {
        return queriedAnalyze;
    }

    public void setQueriedAnalyze(List<QueriedAnalyzeBean> queriedAnalyze) {
        this.queriedAnalyze = queriedAnalyze;
    }

    public List<QueriedInfosBean> getQueriedInfos() {
        return queriedInfos;
    }

    public void setQueriedInfos(List<QueriedInfosBean> queriedInfos) {
        this.queriedInfos = queriedInfos;
    }
}
