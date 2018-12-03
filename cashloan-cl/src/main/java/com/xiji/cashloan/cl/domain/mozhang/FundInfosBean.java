package com.xiji.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lu on 2018/9/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FundInfosBean {
    /**
     * fund_basic : {"last_pay_date":"string","update_date":"string","open_date":"string","open_location":"string","account_status":"string","balance":"string","base_amount":"string","monthly_income":"string"}
     * fund_statistics : {"continuous_months":0,"repay_times":0,"total_companies":0,"total_months":"int"}
     */

    @JsonProperty("fund_basic")
    private FundBasicBean fundBasic;
    @JsonProperty("fund_statistics")
    private FundStatisticsBean fundStatistics;

    public FundBasicBean getFundBasic() {
        return fundBasic;
    }

    public void setFundBasic(FundBasicBean fundBasic) {
        this.fundBasic = fundBasic;
    }

    public FundStatisticsBean getFundStatistics() {
        return fundStatistics;
    }

    public void setFundStatistics(FundStatisticsBean fundStatistics) {
        this.fundStatistics = fundStatistics;
    }
}
