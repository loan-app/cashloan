package com.rongdu.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lu on 2018/9/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanBehaviorAnalysisBean {
    /**
     * defaultday_from_first_to_end : string
     * feature_7d : {"jiedai4_sum_fail_cnt_d7":"string","jiedai_avg_defaultdays_d7":"string","jiedai4_count_fill_d3_cnt_d7":"string","jiedai_max_defaultdays_d7":"string","dd_jiedai_sum_fill_d5_cnt_d7":"string","dd_jiedai_count_fail_mamberadd_d7":"string","dd_jiedai_avg_fail_days1_d7":"string","jiedai_min_defaultdays_d7":"string","last_to_end_sure_due_all_pro_all_time_d7":"string","max_sure_due_days_non_cdq_all_time_d7":"string","sum_sure_due_days_all_pro_all_time_d7":"string","last_to_end_sure_due_non_cdq_all_time_d7":"string"}
     * feature_15d : {"jiedai_avg_defaultdays_d15":"string","jiedai_min_defaultdays_d15":"string","jiedai4_sum_fail_cnt_d15":"string","dd_jiedai_avg_fail_days1_d15":"string","jiedai4_count_fill_d3_cnt_d15":"string","jiedai4_count_fill_d5_cnt_d15":"string","dd_jiedai_count_fail_mamberadd_d15":"string","jiedai_sum_fail_amt_d15":"string","dd_jiedai_max_fail_days1_d15":"string","sum_sure_due_days_all_pro_all_time_d15":"string","last_to_end_sure_due_all_pro_all_time_d15":"string","max_sure_due_days_non_cdq_all_time_d15":"string","last_to_end_sure_due_non_cdq_all_time_d15":"string"}
     * feature_30d : {"jiedai_avg_defaultdays_m1":"string","dd_jiedai_max_fail_days1_m1":"string","jiedai4_sum_fail_cnt1":"string","dd_jiedai_max_fail_days_m1":"string","dd_jiedai_count_fail_mamberadd_m1":"string","jiedai4_count_fill_d3_cnt_m1":"string","dd_jiedai_min_fail_days1_m1":"string","jiedai_sum_fail_amt1":"string","cdq_dd_jiedai_max_fail_days1_m1":"string","jiedai4_count_fill_d5_cnt_m1":"string","jiedai4_avg_succ_amt1":"string","sum_sure_due_days_non_cdq_all_time_m1":"string","sum_sure_due_days_all_pro_all_time_m1":"string","avg_sure_due_days_non_cdq_all_time_m1":"string","pct_pay_amt_cdq_pro_all_time_m1":"string","max_pay_amt_all_pro_all_time_m1":"string"}
     * feature_90d : {"jiedai_avg_defaultdays_m3":"string","dd_jiedai_max_fail_days1_m3":"string","dd_jiedai_avg_fail_days_m3":"string","dd_jiedai_count_fail_mamberadd_m3":"string","jiedai4_count_fill_d3_cnt_m3":"string","jiedai4_count_fill_d5_cnt_m3":"string","cdq_dd_jiedai_avg_fail_days1_m3":"string","jiedai4_avg_succ_amt3":"string","jiedai_sum_fail_amt3":"string","dd_jiedai_min_fail_days1_m3":"string","sum_sure_due_days_all_pro_all_time_m3":"string","sum_sure_due_days_non_cdq_all_time_m3":"string","avg_sure_due_days_all_pro_all_time_m3":"string","max_due_cnt_non_cdq_all_time_m3":"string","avg_sure_due_days_non_cdq_all_time_m3":"string","pct_pay_amt_cdq_pro_all_time_m3":"string"}
     * feature_180d : {"jiedai_avg_defaultdays_m6":"string","dd_jiedai_avg_fail_days1_m6":"string","dd_jiedai_avg_fail_days_m6":"string","dd_jiedai_count_fail_mamberadd_m6":"string","jiedai4_count_fill_d3_cnt_m6":"string","cdq_dd_jiedai_avg_fail_days1_m6":"string","cdq_dd_jiedai_max_fail_days1_m6":"string","jiedai4_avg_succ_amt6":"string","jiedai4_count_fill_d5_cnt_m6":"string","sum_sure_due_days_non_cdq_all_time_m6":"string","max_sure_due_days_all_pro_all_time_m6":"string","avg_sure_due_days_all_pro_all_time_m6":"string","avg_sure_due_days_non_cdq_all_time_m6":"string","pct_pay_amt_cdq_pro_all_time_m6":"string"}
     * feature_360d : {"dd_jiedai_max_fail_days_m12":"string","dd_jiedai_sum_fill_d5_cnt_m12":"string","last_to_end_sure_due_all_pro_all_time_m12":"string","sum_sure_due_days_non_cdq_all_time_m12":"string","last_to_end_sure_due_non_cdq_all_time_m12":"string","max_due_cnt_all_pro_all_time_m12":"string","max_due_cnt_non_cdq_all_time_m12":"string","max_pay_amt_all_pro_all_time_m12":"string","sum_pay_cnt_all_pro_all_time_m12":"string"}
     */

    @JsonProperty("defaultday_from_first_to_end")
    private String defaultdayFromFirstToEnd;
    @JsonProperty("feature_7d")
    private Feature7dBean feature7d;
    @JsonProperty("feature_15d")
    private Feature15dBean feature15d;
    @JsonProperty("feature_30d")
    private Feature30dBean feature30d;
    @JsonProperty("feature_90d")
    private Feature90dBean feature90d;
    @JsonProperty("feature_180d")
    private Feature180dBean feature180d;
    @JsonProperty("feature_360d")
    private Feature360dBean feature360d;

    public String getDefaultdayFromFirstToEnd() {
        return defaultdayFromFirstToEnd;
    }

    public void setDefaultdayFromFirstToEnd(String defaultdayFromFirstToEnd) {
        this.defaultdayFromFirstToEnd = defaultdayFromFirstToEnd;
    }

    public Feature7dBean getFeature7d() {
        return feature7d;
    }

    public void setFeature7d(Feature7dBean feature7d) {
        this.feature7d = feature7d;
    }

    public Feature15dBean getFeature15d() {
        return feature15d;
    }

    public void setFeature15d(Feature15dBean feature15d) {
        this.feature15d = feature15d;
    }

    public Feature30dBean getFeature30d() {
        return feature30d;
    }

    public void setFeature30d(Feature30dBean feature30d) {
        this.feature30d = feature30d;
    }

    public Feature90dBean getFeature90d() {
        return feature90d;
    }

    public void setFeature90d(Feature90dBean feature90d) {
        this.feature90d = feature90d;
    }

    public Feature180dBean getFeature180d() {
        return feature180d;
    }

    public void setFeature180d(Feature180dBean feature180d) {
        this.feature180d = feature180d;
    }

    public Feature360dBean getFeature360d() {
        return feature360d;
    }

    public void setFeature360d(Feature360dBean feature360d) {
        this.feature360d = feature360d;
    }
}
