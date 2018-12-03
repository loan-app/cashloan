package com.rongdu.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lu on 2018/9/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Feature360dBean {
    /**
     * dd_jiedai_max_fail_days_m12 : string
     * dd_jiedai_sum_fill_d5_cnt_m12 : string
     * last_to_end_sure_due_all_pro_all_time_m12 : string
     * sum_sure_due_days_non_cdq_all_time_m12 : string
     * last_to_end_sure_due_non_cdq_all_time_m12 : string
     * max_due_cnt_all_pro_all_time_m12 : string
     * max_due_cnt_non_cdq_all_time_m12 : string
     * max_pay_amt_all_pro_all_time_m12 : string
     * sum_pay_cnt_all_pro_all_time_m12 : string
     */

    @JsonProperty("dd_jiedai_max_fail_days_m12")
    private String ddJiedaiMaxFailDaysM12;
    @JsonProperty("dd_jiedai_sum_fill_d5_cnt_m12")
    private String ddJiedaiSumFillD5CntM12;
    @JsonProperty("last_to_end_sure_due_all_pro_all_time_m12")
    private String lastToEndSureDueAllProAllTimeM12;
    @JsonProperty("sum_sure_due_days_non_cdq_all_time_m12")
    private String sumSureDueDaysNonCdqAllTimeM12;
    @JsonProperty("last_to_end_sure_due_non_cdq_all_time_m12")
    private String lastToEndSureDueNonCdqAllTimeM12;
    @JsonProperty("max_due_cnt_all_pro_all_time_m12")
    private String maxDueCntAllProAllTimeM12;
    @JsonProperty("max_due_cnt_non_cdq_all_time_m12")
    private String maxDueCntNonCdqAllTimeM12;
    @JsonProperty("max_pay_amt_all_pro_all_time_m12")
    private String maxPayAmtAllProAllTimeM12;
    @JsonProperty("sum_pay_cnt_all_pro_all_time_m12")
    private String sumPayCntAllProAllTimeM12;

    public String getDdJiedaiMaxFailDaysM12() {
        return ddJiedaiMaxFailDaysM12;
    }

    public void setDdJiedaiMaxFailDaysM12(String ddJiedaiMaxFailDaysM12) {
        this.ddJiedaiMaxFailDaysM12 = ddJiedaiMaxFailDaysM12;
    }

    public String getDdJiedaiSumFillD5CntM12() {
        return ddJiedaiSumFillD5CntM12;
    }

    public void setDdJiedaiSumFillD5CntM12(String ddJiedaiSumFillD5CntM12) {
        this.ddJiedaiSumFillD5CntM12 = ddJiedaiSumFillD5CntM12;
    }

    public String getLastToEndSureDueAllProAllTimeM12() {
        return lastToEndSureDueAllProAllTimeM12;
    }

    public void setLastToEndSureDueAllProAllTimeM12(String lastToEndSureDueAllProAllTimeM12) {
        this.lastToEndSureDueAllProAllTimeM12 = lastToEndSureDueAllProAllTimeM12;
    }

    public String getSumSureDueDaysNonCdqAllTimeM12() {
        return sumSureDueDaysNonCdqAllTimeM12;
    }

    public void setSumSureDueDaysNonCdqAllTimeM12(String sumSureDueDaysNonCdqAllTimeM12) {
        this.sumSureDueDaysNonCdqAllTimeM12 = sumSureDueDaysNonCdqAllTimeM12;
    }

    public String getLastToEndSureDueNonCdqAllTimeM12() {
        return lastToEndSureDueNonCdqAllTimeM12;
    }

    public void setLastToEndSureDueNonCdqAllTimeM12(String lastToEndSureDueNonCdqAllTimeM12) {
        this.lastToEndSureDueNonCdqAllTimeM12 = lastToEndSureDueNonCdqAllTimeM12;
    }

    public String getMaxDueCntAllProAllTimeM12() {
        return maxDueCntAllProAllTimeM12;
    }

    public void setMaxDueCntAllProAllTimeM12(String maxDueCntAllProAllTimeM12) {
        this.maxDueCntAllProAllTimeM12 = maxDueCntAllProAllTimeM12;
    }

    public String getMaxDueCntNonCdqAllTimeM12() {
        return maxDueCntNonCdqAllTimeM12;
    }

    public void setMaxDueCntNonCdqAllTimeM12(String maxDueCntNonCdqAllTimeM12) {
        this.maxDueCntNonCdqAllTimeM12 = maxDueCntNonCdqAllTimeM12;
    }

    public String getMaxPayAmtAllProAllTimeM12() {
        return maxPayAmtAllProAllTimeM12;
    }

    public void setMaxPayAmtAllProAllTimeM12(String maxPayAmtAllProAllTimeM12) {
        this.maxPayAmtAllProAllTimeM12 = maxPayAmtAllProAllTimeM12;
    }

    public String getSumPayCntAllProAllTimeM12() {
        return sumPayCntAllProAllTimeM12;
    }

    public void setSumPayCntAllProAllTimeM12(String sumPayCntAllProAllTimeM12) {
        this.sumPayCntAllProAllTimeM12 = sumPayCntAllProAllTimeM12;
    }
}
