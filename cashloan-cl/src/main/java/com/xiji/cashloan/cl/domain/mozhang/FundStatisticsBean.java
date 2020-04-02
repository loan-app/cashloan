package com.xiji.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author wnb
 * @date 2018/12/03
 * @version 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FundStatisticsBean {
    /**
     * continuous_months : 0
     * repay_times : 0
     * total_companies : 0
     * total_months : int
     */

    @JsonProperty("continuous_months")
    private int continuousMonths;
    @JsonProperty("repay_times")
    private int repayTimes;
    @JsonProperty("total_companies")
    private int totalCompanies;
    @JsonProperty("total_months")
    private String totalMonths;

    public int getContinuousMonths() {
        return continuousMonths;
    }

    public void setContinuousMonths(int continuousMonths) {
        this.continuousMonths = continuousMonths;
    }

    public int getRepayTimes() {
        return repayTimes;
    }

    public void setRepayTimes(int repayTimes) {
        this.repayTimes = repayTimes;
    }

    public int getTotalCompanies() {
        return totalCompanies;
    }

    public void setTotalCompanies(int totalCompanies) {
        this.totalCompanies = totalCompanies;
    }

    public String getTotalMonths() {
        return totalMonths;
    }

    public void setTotalMonths(String totalMonths) {
        this.totalMonths = totalMonths;
    }
}
