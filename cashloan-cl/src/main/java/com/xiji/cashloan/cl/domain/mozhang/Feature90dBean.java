package com.xiji.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author wnb
 * @date 2018/12/03
 * @version 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Feature90dBean {
    /**
     * jiedai_avg_defaultdays_m3 : string
     * dd_jiedai_max_fail_days1_m3 : string
     * dd_jiedai_avg_fail_days_m3 : string
     * dd_jiedai_count_fail_mamberadd_m3 : string
     * jiedai4_count_fill_d3_cnt_m3 : string
     * jiedai4_count_fill_d5_cnt_m3 : string
     * cdq_dd_jiedai_avg_fail_days1_m3 : string
     * jiedai4_avg_succ_amt3 : string
     * jiedai_sum_fail_amt3 : string
     * dd_jiedai_min_fail_days1_m3 : string
     * sum_sure_due_days_all_pro_all_time_m3 : string
     * sum_sure_due_days_non_cdq_all_time_m3 : string
     * avg_sure_due_days_all_pro_all_time_m3 : string
     * max_due_cnt_non_cdq_all_time_m3 : string
     * avg_sure_due_days_non_cdq_all_time_m3 : string
     * pct_pay_amt_cdq_pro_all_time_m3 : string
     */

    @JsonProperty("jiedai_avg_defaultdays_m3")
    private String jiedaiAvgDefaultdaysM3;
    @JsonProperty("dd_jiedai_max_fail_days1_m3")
    private String ddJiedaiMaxFailDays1M3;
    @JsonProperty("dd_jiedai_avg_fail_days_m3")
    private String ddJiedaiAvgFailDaysM3;
    @JsonProperty("dd_jiedai_count_fail_mamberadd_m3")
    private String ddJiedaiCountFailMamberaddM3;
    @JsonProperty("jiedai4_count_fill_d3_cnt_m3")
    private String jiedai4CountFillD3CntM3;
    @JsonProperty("jiedai4_count_fill_d5_cnt_m3")
    private String jiedai4CountFillD5CntM3;
    @JsonProperty("cdq_dd_jiedai_avg_fail_days1_m3")
    private String cdqDdJiedaiAvgFailDays1M3;
    @JsonProperty("jiedai4_avg_succ_amt3")
    private String jiedai4AvgSuccAmt3;
    @JsonProperty("jiedai_sum_fail_amt3")
    private String jiedaiSumFailAmt3;
    @JsonProperty("dd_jiedai_min_fail_days1_m3")
    private String ddJiedaiMinFailDays1M3;
    @JsonProperty("sum_sure_due_days_all_pro_all_time_m3")
    private String sumSureDueDaysAllProAllTimeM3;
    @JsonProperty("sum_sure_due_days_non_cdq_all_time_m3")
    private String sumSureDueDaysNonCdqAllTimeM3;
    @JsonProperty("avg_sure_due_days_all_pro_all_time_m3")
    private String avgSureDueDaysAllProAllTimeM3;
    @JsonProperty("max_due_cnt_non_cdq_all_time_m3")
    private String maxDueCntNonCdqAllTimeM3;
    @JsonProperty("avg_sure_due_days_non_cdq_all_time_m3")
    private String avgSureDueDaysNonCdqAllTimeM3;
    @JsonProperty("pct_pay_amt_cdq_pro_all_time_m3")
    private String pctPayAmtCdqProAllTimeM3;

    public String getJiedaiAvgDefaultdaysM3() {
        return jiedaiAvgDefaultdaysM3;
    }

    public void setJiedaiAvgDefaultdaysM3(String jiedaiAvgDefaultdaysM3) {
        this.jiedaiAvgDefaultdaysM3 = jiedaiAvgDefaultdaysM3;
    }

    public String getDdJiedaiMaxFailDays1M3() {
        return ddJiedaiMaxFailDays1M3;
    }

    public void setDdJiedaiMaxFailDays1M3(String ddJiedaiMaxFailDays1M3) {
        this.ddJiedaiMaxFailDays1M3 = ddJiedaiMaxFailDays1M3;
    }

    public String getDdJiedaiAvgFailDaysM3() {
        return ddJiedaiAvgFailDaysM3;
    }

    public void setDdJiedaiAvgFailDaysM3(String ddJiedaiAvgFailDaysM3) {
        this.ddJiedaiAvgFailDaysM3 = ddJiedaiAvgFailDaysM3;
    }

    public String getDdJiedaiCountFailMamberaddM3() {
        return ddJiedaiCountFailMamberaddM3;
    }

    public void setDdJiedaiCountFailMamberaddM3(String ddJiedaiCountFailMamberaddM3) {
        this.ddJiedaiCountFailMamberaddM3 = ddJiedaiCountFailMamberaddM3;
    }

    public String getJiedai4CountFillD3CntM3() {
        return jiedai4CountFillD3CntM3;
    }

    public void setJiedai4CountFillD3CntM3(String jiedai4CountFillD3CntM3) {
        this.jiedai4CountFillD3CntM3 = jiedai4CountFillD3CntM3;
    }

    public String getJiedai4CountFillD5CntM3() {
        return jiedai4CountFillD5CntM3;
    }

    public void setJiedai4CountFillD5CntM3(String jiedai4CountFillD5CntM3) {
        this.jiedai4CountFillD5CntM3 = jiedai4CountFillD5CntM3;
    }

    public String getCdqDdJiedaiAvgFailDays1M3() {
        return cdqDdJiedaiAvgFailDays1M3;
    }

    public void setCdqDdJiedaiAvgFailDays1M3(String cdqDdJiedaiAvgFailDays1M3) {
        this.cdqDdJiedaiAvgFailDays1M3 = cdqDdJiedaiAvgFailDays1M3;
    }

    public String getJiedai4AvgSuccAmt3() {
        return jiedai4AvgSuccAmt3;
    }

    public void setJiedai4AvgSuccAmt3(String jiedai4AvgSuccAmt3) {
        this.jiedai4AvgSuccAmt3 = jiedai4AvgSuccAmt3;
    }

    public String getJiedaiSumFailAmt3() {
        return jiedaiSumFailAmt3;
    }

    public void setJiedaiSumFailAmt3(String jiedaiSumFailAmt3) {
        this.jiedaiSumFailAmt3 = jiedaiSumFailAmt3;
    }

    public String getDdJiedaiMinFailDays1M3() {
        return ddJiedaiMinFailDays1M3;
    }

    public void setDdJiedaiMinFailDays1M3(String ddJiedaiMinFailDays1M3) {
        this.ddJiedaiMinFailDays1M3 = ddJiedaiMinFailDays1M3;
    }

    public String getSumSureDueDaysAllProAllTimeM3() {
        return sumSureDueDaysAllProAllTimeM3;
    }

    public void setSumSureDueDaysAllProAllTimeM3(String sumSureDueDaysAllProAllTimeM3) {
        this.sumSureDueDaysAllProAllTimeM3 = sumSureDueDaysAllProAllTimeM3;
    }

    public String getSumSureDueDaysNonCdqAllTimeM3() {
        return sumSureDueDaysNonCdqAllTimeM3;
    }

    public void setSumSureDueDaysNonCdqAllTimeM3(String sumSureDueDaysNonCdqAllTimeM3) {
        this.sumSureDueDaysNonCdqAllTimeM3 = sumSureDueDaysNonCdqAllTimeM3;
    }

    public String getAvgSureDueDaysAllProAllTimeM3() {
        return avgSureDueDaysAllProAllTimeM3;
    }

    public void setAvgSureDueDaysAllProAllTimeM3(String avgSureDueDaysAllProAllTimeM3) {
        this.avgSureDueDaysAllProAllTimeM3 = avgSureDueDaysAllProAllTimeM3;
    }

    public String getMaxDueCntNonCdqAllTimeM3() {
        return maxDueCntNonCdqAllTimeM3;
    }

    public void setMaxDueCntNonCdqAllTimeM3(String maxDueCntNonCdqAllTimeM3) {
        this.maxDueCntNonCdqAllTimeM3 = maxDueCntNonCdqAllTimeM3;
    }

    public String getAvgSureDueDaysNonCdqAllTimeM3() {
        return avgSureDueDaysNonCdqAllTimeM3;
    }

    public void setAvgSureDueDaysNonCdqAllTimeM3(String avgSureDueDaysNonCdqAllTimeM3) {
        this.avgSureDueDaysNonCdqAllTimeM3 = avgSureDueDaysNonCdqAllTimeM3;
    }

    public String getPctPayAmtCdqProAllTimeM3() {
        return pctPayAmtCdqProAllTimeM3;
    }

    public void setPctPayAmtCdqProAllTimeM3(String pctPayAmtCdqProAllTimeM3) {
        this.pctPayAmtCdqProAllTimeM3 = pctPayAmtCdqProAllTimeM3;
    }
}
