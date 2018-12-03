package com.rongdu.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lu on 2018/9/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Feature30dBean {
    /**
     * jiedai_avg_defaultdays_m1 : string
     * dd_jiedai_max_fail_days1_m1 : string
     * jiedai4_sum_fail_cnt1 : string
     * dd_jiedai_max_fail_days_m1 : string
     * dd_jiedai_count_fail_mamberadd_m1 : string
     * jiedai4_count_fill_d3_cnt_m1 : string
     * dd_jiedai_min_fail_days1_m1 : string
     * jiedai_sum_fail_amt1 : string
     * cdq_dd_jiedai_max_fail_days1_m1 : string
     * jiedai4_count_fill_d5_cnt_m1 : string
     * jiedai4_avg_succ_amt1 : string
     * sum_sure_due_days_non_cdq_all_time_m1 : string
     * sum_sure_due_days_all_pro_all_time_m1 : string
     * avg_sure_due_days_non_cdq_all_time_m1 : string
     * pct_pay_amt_cdq_pro_all_time_m1 : string
     * max_pay_amt_all_pro_all_time_m1 : string
     */

    @JsonProperty("jiedai_avg_defaultdays_m1")
    private String jiedaiAvgDefaultdaysM1;
    @JsonProperty("dd_jiedai_max_fail_days1_m1")
    private String ddJiedaiMaxFailDays1M1;
    @JsonProperty("jiedai4_sum_fail_cnt1")
    private String jiedai4SumFailCnt1;
    @JsonProperty("dd_jiedai_max_fail_days_m1")
    private String ddJiedaiMaxFailDaysM1;
    @JsonProperty("dd_jiedai_count_fail_mamberadd_m1")
    private String ddJiedaiCountFailMamberaddM1;
    @JsonProperty("jiedai4_count_fill_d3_cnt_m1")
    private String jiedai4CountFillD3CntM1;
    @JsonProperty("dd_jiedai_min_fail_days1_m1")
    private String ddJiedaiMinFailDays1M1;
    @JsonProperty("jiedai_sum_fail_amt1")
    private String jiedaiSumFailAmt1;
    @JsonProperty("cdq_dd_jiedai_max_fail_days1_m1")
    private String cdqDdJiedaiMaxFailDays1M1;
    @JsonProperty("jiedai4_count_fill_d5_cnt_m1")
    private String jiedai4CountFillD5CntM1;
    @JsonProperty("jiedai4_avg_succ_amt1")
    private String jiedai4AvgSuccAmt1;
    @JsonProperty("sum_sure_due_days_non_cdq_all_time_m1")
    private String sumSureDueDaysNonCdqAllTimeM1;
    @JsonProperty("sum_sure_due_days_all_pro_all_time_m1")
    private String sumSureDueDaysAllProAllTimeM1;
    @JsonProperty("avg_sure_due_days_non_cdq_all_time_m1")
    private String avgSureDueDaysNonCdqAllTimeM1;
    @JsonProperty("pct_pay_amt_cdq_pro_all_time_m1")
    private String pctPayAmtCdqProAllTimeM1;
    @JsonProperty("max_pay_amt_all_pro_all_time_m1")
    private String maxPayAmtAllProAllTimeM1;

    public String getJiedaiAvgDefaultdaysM1() {
        return jiedaiAvgDefaultdaysM1;
    }

    public void setJiedaiAvgDefaultdaysM1(String jiedaiAvgDefaultdaysM1) {
        this.jiedaiAvgDefaultdaysM1 = jiedaiAvgDefaultdaysM1;
    }

    public String getDdJiedaiMaxFailDays1M1() {
        return ddJiedaiMaxFailDays1M1;
    }

    public void setDdJiedaiMaxFailDays1M1(String ddJiedaiMaxFailDays1M1) {
        this.ddJiedaiMaxFailDays1M1 = ddJiedaiMaxFailDays1M1;
    }

    public String getJiedai4SumFailCnt1() {
        return jiedai4SumFailCnt1;
    }

    public void setJiedai4SumFailCnt1(String jiedai4SumFailCnt1) {
        this.jiedai4SumFailCnt1 = jiedai4SumFailCnt1;
    }

    public String getDdJiedaiMaxFailDaysM1() {
        return ddJiedaiMaxFailDaysM1;
    }

    public void setDdJiedaiMaxFailDaysM1(String ddJiedaiMaxFailDaysM1) {
        this.ddJiedaiMaxFailDaysM1 = ddJiedaiMaxFailDaysM1;
    }

    public String getDdJiedaiCountFailMamberaddM1() {
        return ddJiedaiCountFailMamberaddM1;
    }

    public void setDdJiedaiCountFailMamberaddM1(String ddJiedaiCountFailMamberaddM1) {
        this.ddJiedaiCountFailMamberaddM1 = ddJiedaiCountFailMamberaddM1;
    }

    public String getJiedai4CountFillD3CntM1() {
        return jiedai4CountFillD3CntM1;
    }

    public void setJiedai4CountFillD3CntM1(String jiedai4CountFillD3CntM1) {
        this.jiedai4CountFillD3CntM1 = jiedai4CountFillD3CntM1;
    }

    public String getDdJiedaiMinFailDays1M1() {
        return ddJiedaiMinFailDays1M1;
    }

    public void setDdJiedaiMinFailDays1M1(String ddJiedaiMinFailDays1M1) {
        this.ddJiedaiMinFailDays1M1 = ddJiedaiMinFailDays1M1;
    }

    public String getJiedaiSumFailAmt1() {
        return jiedaiSumFailAmt1;
    }

    public void setJiedaiSumFailAmt1(String jiedaiSumFailAmt1) {
        this.jiedaiSumFailAmt1 = jiedaiSumFailAmt1;
    }

    public String getCdqDdJiedaiMaxFailDays1M1() {
        return cdqDdJiedaiMaxFailDays1M1;
    }

    public void setCdqDdJiedaiMaxFailDays1M1(String cdqDdJiedaiMaxFailDays1M1) {
        this.cdqDdJiedaiMaxFailDays1M1 = cdqDdJiedaiMaxFailDays1M1;
    }

    public String getJiedai4CountFillD5CntM1() {
        return jiedai4CountFillD5CntM1;
    }

    public void setJiedai4CountFillD5CntM1(String jiedai4CountFillD5CntM1) {
        this.jiedai4CountFillD5CntM1 = jiedai4CountFillD5CntM1;
    }

    public String getJiedai4AvgSuccAmt1() {
        return jiedai4AvgSuccAmt1;
    }

    public void setJiedai4AvgSuccAmt1(String jiedai4AvgSuccAmt1) {
        this.jiedai4AvgSuccAmt1 = jiedai4AvgSuccAmt1;
    }

    public String getSumSureDueDaysNonCdqAllTimeM1() {
        return sumSureDueDaysNonCdqAllTimeM1;
    }

    public void setSumSureDueDaysNonCdqAllTimeM1(String sumSureDueDaysNonCdqAllTimeM1) {
        this.sumSureDueDaysNonCdqAllTimeM1 = sumSureDueDaysNonCdqAllTimeM1;
    }

    public String getSumSureDueDaysAllProAllTimeM1() {
        return sumSureDueDaysAllProAllTimeM1;
    }

    public void setSumSureDueDaysAllProAllTimeM1(String sumSureDueDaysAllProAllTimeM1) {
        this.sumSureDueDaysAllProAllTimeM1 = sumSureDueDaysAllProAllTimeM1;
    }

    public String getAvgSureDueDaysNonCdqAllTimeM1() {
        return avgSureDueDaysNonCdqAllTimeM1;
    }

    public void setAvgSureDueDaysNonCdqAllTimeM1(String avgSureDueDaysNonCdqAllTimeM1) {
        this.avgSureDueDaysNonCdqAllTimeM1 = avgSureDueDaysNonCdqAllTimeM1;
    }

    public String getPctPayAmtCdqProAllTimeM1() {
        return pctPayAmtCdqProAllTimeM1;
    }

    public void setPctPayAmtCdqProAllTimeM1(String pctPayAmtCdqProAllTimeM1) {
        this.pctPayAmtCdqProAllTimeM1 = pctPayAmtCdqProAllTimeM1;
    }

    public String getMaxPayAmtAllProAllTimeM1() {
        return maxPayAmtAllProAllTimeM1;
    }

    public void setMaxPayAmtAllProAllTimeM1(String maxPayAmtAllProAllTimeM1) {
        this.maxPayAmtAllProAllTimeM1 = maxPayAmtAllProAllTimeM1;
    }
}
