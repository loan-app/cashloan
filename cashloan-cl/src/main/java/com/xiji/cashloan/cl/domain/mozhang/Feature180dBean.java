package com.xiji.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author wnb
 * @date 2018/12/03
 * @version 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Feature180dBean {
    /**
     * jiedai_avg_defaultdays_m6 : string
     * dd_jiedai_avg_fail_days1_m6 : string
     * dd_jiedai_avg_fail_days_m6 : string
     * dd_jiedai_count_fail_mamberadd_m6 : string
     * jiedai4_count_fill_d3_cnt_m6 : string
     * cdq_dd_jiedai_avg_fail_days1_m6 : string
     * cdq_dd_jiedai_max_fail_days1_m6 : string
     * jiedai4_avg_succ_amt6 : string
     * jiedai4_count_fill_d5_cnt_m6 : string
     * sum_sure_due_days_non_cdq_all_time_m6 : string
     * max_sure_due_days_all_pro_all_time_m6 : string
     * avg_sure_due_days_all_pro_all_time_m6 : string
     * avg_sure_due_days_non_cdq_all_time_m6 : string
     * pct_pay_amt_cdq_pro_all_time_m6 : string
     */

    @JsonProperty("jiedai_avg_defaultdays_m6")
    private String jiedaiAvgDefaultdaysM6;
    @JsonProperty("dd_jiedai_avg_fail_days1_m6")
    private String ddJiedaiAvgFailDays1M6;
    @JsonProperty("dd_jiedai_avg_fail_days_m6")
    private String ddJiedaiAvgFailDaysM6;
    @JsonProperty("dd_jiedai_count_fail_mamberadd_m6")
    private String ddJiedaiCountFailMamberaddM6;
    @JsonProperty("jiedai4_count_fill_d3_cnt_m6")
    private String jiedai4CountFillD3CntM6;
    @JsonProperty("cdq_dd_jiedai_avg_fail_days1_m6")
    private String cdqDdJiedaiAvgFailDays1M6;
    @JsonProperty("cdq_dd_jiedai_max_fail_days1_m6")
    private String cdqDdJiedaiMaxFailDays1M6;
    @JsonProperty("jiedai4_avg_succ_amt6")
    private String jiedai4AvgSuccAmt6;
    @JsonProperty("jiedai4_count_fill_d5_cnt_m6")
    private String jiedai4CountFillD5CntM6;
    @JsonProperty("sum_sure_due_days_non_cdq_all_time_m6")
    private String sumSureDueDaysNonCdqAllTimeM6;
    @JsonProperty("max_sure_due_days_all_pro_all_time_m6")
    private String maxSureDueDaysAllProAllTimeM6;
    @JsonProperty("avg_sure_due_days_all_pro_all_time_m6")
    private String avgSureDueDaysAllProAllTimeM6;
    @JsonProperty("avg_sure_due_days_non_cdq_all_time_m6")
    private String avgSureDueDaysNonCdqAllTimeM6;
    @JsonProperty("pct_pay_amt_cdq_pro_all_time_m6")
    private String pctPayAmtCdqProAllTimeM6;

    public String getJiedaiAvgDefaultdaysM6() {
        return jiedaiAvgDefaultdaysM6;
    }

    public void setJiedaiAvgDefaultdaysM6(String jiedaiAvgDefaultdaysM6) {
        this.jiedaiAvgDefaultdaysM6 = jiedaiAvgDefaultdaysM6;
    }

    public String getDdJiedaiAvgFailDays1M6() {
        return ddJiedaiAvgFailDays1M6;
    }

    public void setDdJiedaiAvgFailDays1M6(String ddJiedaiAvgFailDays1M6) {
        this.ddJiedaiAvgFailDays1M6 = ddJiedaiAvgFailDays1M6;
    }

    public String getDdJiedaiAvgFailDaysM6() {
        return ddJiedaiAvgFailDaysM6;
    }

    public void setDdJiedaiAvgFailDaysM6(String ddJiedaiAvgFailDaysM6) {
        this.ddJiedaiAvgFailDaysM6 = ddJiedaiAvgFailDaysM6;
    }

    public String getDdJiedaiCountFailMamberaddM6() {
        return ddJiedaiCountFailMamberaddM6;
    }

    public void setDdJiedaiCountFailMamberaddM6(String ddJiedaiCountFailMamberaddM6) {
        this.ddJiedaiCountFailMamberaddM6 = ddJiedaiCountFailMamberaddM6;
    }

    public String getJiedai4CountFillD3CntM6() {
        return jiedai4CountFillD3CntM6;
    }

    public void setJiedai4CountFillD3CntM6(String jiedai4CountFillD3CntM6) {
        this.jiedai4CountFillD3CntM6 = jiedai4CountFillD3CntM6;
    }

    public String getCdqDdJiedaiAvgFailDays1M6() {
        return cdqDdJiedaiAvgFailDays1M6;
    }

    public void setCdqDdJiedaiAvgFailDays1M6(String cdqDdJiedaiAvgFailDays1M6) {
        this.cdqDdJiedaiAvgFailDays1M6 = cdqDdJiedaiAvgFailDays1M6;
    }

    public String getCdqDdJiedaiMaxFailDays1M6() {
        return cdqDdJiedaiMaxFailDays1M6;
    }

    public void setCdqDdJiedaiMaxFailDays1M6(String cdqDdJiedaiMaxFailDays1M6) {
        this.cdqDdJiedaiMaxFailDays1M6 = cdqDdJiedaiMaxFailDays1M6;
    }

    public String getJiedai4AvgSuccAmt6() {
        return jiedai4AvgSuccAmt6;
    }

    public void setJiedai4AvgSuccAmt6(String jiedai4AvgSuccAmt6) {
        this.jiedai4AvgSuccAmt6 = jiedai4AvgSuccAmt6;
    }

    public String getJiedai4CountFillD5CntM6() {
        return jiedai4CountFillD5CntM6;
    }

    public void setJiedai4CountFillD5CntM6(String jiedai4CountFillD5CntM6) {
        this.jiedai4CountFillD5CntM6 = jiedai4CountFillD5CntM6;
    }

    public String getSumSureDueDaysNonCdqAllTimeM6() {
        return sumSureDueDaysNonCdqAllTimeM6;
    }

    public void setSumSureDueDaysNonCdqAllTimeM6(String sumSureDueDaysNonCdqAllTimeM6) {
        this.sumSureDueDaysNonCdqAllTimeM6 = sumSureDueDaysNonCdqAllTimeM6;
    }

    public String getMaxSureDueDaysAllProAllTimeM6() {
        return maxSureDueDaysAllProAllTimeM6;
    }

    public void setMaxSureDueDaysAllProAllTimeM6(String maxSureDueDaysAllProAllTimeM6) {
        this.maxSureDueDaysAllProAllTimeM6 = maxSureDueDaysAllProAllTimeM6;
    }

    public String getAvgSureDueDaysAllProAllTimeM6() {
        return avgSureDueDaysAllProAllTimeM6;
    }

    public void setAvgSureDueDaysAllProAllTimeM6(String avgSureDueDaysAllProAllTimeM6) {
        this.avgSureDueDaysAllProAllTimeM6 = avgSureDueDaysAllProAllTimeM6;
    }

    public String getAvgSureDueDaysNonCdqAllTimeM6() {
        return avgSureDueDaysNonCdqAllTimeM6;
    }

    public void setAvgSureDueDaysNonCdqAllTimeM6(String avgSureDueDaysNonCdqAllTimeM6) {
        this.avgSureDueDaysNonCdqAllTimeM6 = avgSureDueDaysNonCdqAllTimeM6;
    }

    public String getPctPayAmtCdqProAllTimeM6() {
        return pctPayAmtCdqProAllTimeM6;
    }

    public void setPctPayAmtCdqProAllTimeM6(String pctPayAmtCdqProAllTimeM6) {
        this.pctPayAmtCdqProAllTimeM6 = pctPayAmtCdqProAllTimeM6;
    }
}
