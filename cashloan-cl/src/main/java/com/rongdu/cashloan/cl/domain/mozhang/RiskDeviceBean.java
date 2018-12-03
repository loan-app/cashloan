package com.rongdu.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lu on 2018/9/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RiskDeviceBean {
    /**
     * loan_cnt : 0
     * consumption_cnt : 0
     * lottery_cnt : 0
     * loan_cnt_ratio : string
     * consumption_cnt_ratio : string
     * lottery_cnt_ratio : string
     */

    @JsonProperty("loan_cnt")
    private int loanCnt;
    @JsonProperty("consumption_cnt")
    private int consumptionCnt;
    @JsonProperty("lottery_cnt")
    private int lotteryCnt;
    @JsonProperty("loan_cnt_ratio")
    private String loanCntRatio;
    @JsonProperty("consumption_cnt_ratio")
    private String consumptionCntRatio;
    @JsonProperty("lottery_cnt_ratio")
    private String lotteryCntRatio;

    public int getLoanCnt() {
        return loanCnt;
    }

    public void setLoanCnt(int loanCnt) {
        this.loanCnt = loanCnt;
    }

    public int getConsumptionCnt() {
        return consumptionCnt;
    }

    public void setConsumptionCnt(int consumptionCnt) {
        this.consumptionCnt = consumptionCnt;
    }

    public int getLotteryCnt() {
        return lotteryCnt;
    }

    public void setLotteryCnt(int lotteryCnt) {
        this.lotteryCnt = lotteryCnt;
    }

    public String getLoanCntRatio() {
        return loanCntRatio;
    }

    public void setLoanCntRatio(String loanCntRatio) {
        this.loanCntRatio = loanCntRatio;
    }

    public String getConsumptionCntRatio() {
        return consumptionCntRatio;
    }

    public void setConsumptionCntRatio(String consumptionCntRatio) {
        this.consumptionCntRatio = consumptionCntRatio;
    }

    public String getLotteryCntRatio() {
        return lotteryCntRatio;
    }

    public void setLotteryCntRatio(String lotteryCntRatio) {
        this.lotteryCntRatio = lotteryCntRatio;
    }
}
