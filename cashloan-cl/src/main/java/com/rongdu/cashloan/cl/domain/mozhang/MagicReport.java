package com.rongdu.cashloan.cl.domain.mozhang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by lu on 2018/9/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MagicReport {


    /**
     * success : true
     * code : string
     * msg : string
     * data : {"trans_id":"string","person_info":{"idcard":"string","idcard_location":"string","mobile":"string","carrier":"string","mobile_location":"string","name":"string","age":0,"gender":"string","email":"string"},"verification_info":{"has_carrier_data":true,"has_onlinebank_data":true},"black_info_detail":{"mobile_name_in_blacklist":true,"mobile_name_blacklist_updated_time":"string","idcard_name_in_blacklist":true,"idcard_name_blacklist_updated_time":"string","black_types":"string","blacklist_record":{"overdue_count":0,"overdue_amount":"string","overdue_status":"string"}},"gray_info_detail":{"mobile_name_in_gray":true,"mobile_name_gray_updated_time":"string","idcard_name_in_gray":true,"idcard_name_gray_updated_time":"string","gray_types":"string","graylist_record":{"overdue_count":0,"overdue_amount":"string","overdue_status":"string"}},"mobile_info":{"match_score":0,"mobile_contact_30d":{"contactnum":0,"auth_contactnum":0,"auth_contact_ratio":"string","black_contactnum":0,"black_contactnum_ratio":"string","contact_type":"string","auth_indirectnum":0,"auth_indirectnum_ratio":"string","black_indirectnum":0,"black_indirectnum_ratio":"string","black_indirect_peernum":0,"black_indirect_peernum_ratio":"string","auth_indirect_peernum":0,"auth_indirect_peernum_ratio":"string"},"intimate_contact_info_30d":{"intimatenum":0,"auth_intimatenum":0,"auth_intimatenum_ratio":"string","black_intimatenum":0,"black_intimatenum_ratio":"string","intimate_type":"string","auth_intimate_indirectnum":0,"auth_intimate_indirectnum_ratio":"string","black_intimate_indirectnum":0,"black_intimate_indirectnum_ratio":"string","black_intimate_indirect_peernum":0,"black_intimate_indirect_peernum_ratio":"string","auth_intimate_indirect_peernum":0,"auth_intimate_indirect_peernum_ratio":"string"},"mobile_contact_90d":{"contactnum":0,"auth_contactnum":0,"auth_contact_ratio":"string","black_contactnum":0,"black_contactnum_ratio":"string","contact_type":"string","auth_indirectnum":0,"auth_indirectnum_ratio":"string","black_indirectnum":0,"black_indirectnum_ratio":"string","black_indirect_peernum":0,"black_indirect_peernum_ratio":"string","auth_indirect_peernum":0,"auth_indirect_peernum_ratio":"string"},"intimate_contact_info_90d":{"intimatenum":0,"auth_intimatenum":0,"auth_intimatenum_ratio":"string","black_intimatenum":0,"black_intimatenum_ratio":"string","intimate_type":"string","auth_intimate_indirectnum":0,"auth_intimate_indirectnum_ratio":"string","black_intimate_indirectnum":0,"black_intimate_indirectnum_ratio":"string","black_intimate_indirect_peernum":0,"black_intimate_indirect_peernum_ratio":"string","auth_intimate_indirect_peernum":0,"auth_intimate_indirect_peernum_ratio":"string"},"mobile_contact_180d":{"contactnum":0,"auth_contactnum":0,"auth_contact_ratio":"string","black_contactnum":0,"black_contactnum_ratio":"string","contact_type":"string","auth_indirectnum":0,"auth_indirectnum_ratio":"string","black_indirectnum":0,"black_indirectnum_ratio":"string","black_indirect_peernum":0,"black_indirect_peernum_ratio":"string","auth_indirect_peernum":0,"auth_indirect_peernum_ratio":"string"},"intimate_contact_info_180d":{"intimatenum":0,"auth_intimatenum":0,"auth_intimatenum_ratio":"string","black_intimatenum":0,"black_intimatenum_ratio":"string","intimate_type":"string","auth_intimate_indirectnum":0,"auth_intimate_indirectnum_ratio":"string","black_intimate_indirectnum":0,"black_intimate_indirectnum_ratio":"string","black_intimate_indirect_peernum":0,"black_intimate_indirect_peernum_ratio":"string","auth_intimate_indirect_peernum":0,"auth_intimate_indirect_peernum_ratio":"string"}},"auth_queried_detail":{"register_info":{"other_count":0,"org_count":0,"org_types":["string"]},"queried_detail_org_count":0,"queried_analyze":[{"org_type":"string","loan_cnt_15d":0,"loan_cnt_30d":0,"loan_cnt_90d":0,"loan_cnt_180d":"int"}],"queried_infos":[{"date":"string","org_type":"string","is_self":true}],"loan_org_cnt_all":0,"loan_info":{"loan_org_cnt":0,"loan_cnt":0,"loan_org_cnt_15d":0,"loan_org_cnt_30d":0,"loan_org_cnt_90d":0,"loan_org_cnt_180d":0,"loan_cnt_15d":0,"loan_cnt_30d":0,"loan_cnt_90d":0,"loan_cnt_180d":0}},"untrusted_info":{"courtcase_cnt":0,"dishonest_cnt":0,"dishonest_detail_ino":[{"court_name":"string","area_name":"string","case_code":"string","publish_date":"string","gist_id":"string","duty":"string","performance":"string","disrupt_type_name":"string"}]},"suspicious_idcard":{"other_names_cnt":0,"other_mobiles_cnt":0,"other_mobiles_black_cnt":0,"information_sources_cnt":0,"other_names":[{"latest_used_time":"string","name":"string"}],"other_mobiles":[{"latest_used_time":"string","mobile":"string","carrier":"string","mobile_location":"string","isblack":true}],"information_sources":[{"latest_used_time":"string","org_type":"string"}]},"suspicious_mobile":{"other_names_cnt":0,"other_idcards_cnt":0,"other_idcards_black_cnt":0,"information_sources_cnt":0,"other_names":[{"latest_used_time":"string","name":"string"}],"other_idcards":[{"latest_used_time":"string","idcard":"string","isblack":true}],"information_sources":[{"latest_used_time":"string","org_type":"string"}]},"suspicious_device":{"other_devices_cnt":0,"mobile_other_devices_cnt":0,"idcard_other_devices_cnt":0,"information_sources_cnt":0,"other_names_cnt":0,"other_mobiles_cnt":0,"other_mobiles_black_cnt":0,"other_idcards_cnt":0,"other_idcards_black_cnt":0,"other_names":[{"latest_used_time":"string","name":"string"}]},"risk_qqgroup":{"loan_groupcnt":0,"installment_groupcnt":0,"financial_management_groupcnt":0,"woolen_groupcnt":0,"gambling_groupcnt":"int"}}
     * risk_device : [{"loan_cnt":0,"consumption_cnt":0,"lottery_cnt":0,"loan_cnt_ratio":"string","consumption_cnt_ratio":"string","lottery_cnt_ratio":"string"}]
     * credit_card : {"update_date":"string","bank_nums":0,"card_amount":0,"total_credit_limit":"string","max_credit_limit":"string","overdue_card":0,"bill_nums":0,"credit_overdue_item_12m":{"overdue_times":0,"overdue_months":0},"credit_overdue_item_6m":{"overdue_times":0,"overdue_months":0},"credit_overdue_item_3m":{"overdue_times":0,"overdue_months":0},"last_overdue_date":"string","max_overdue_money":"string","continue_overdue_times":0}
     * loan_behavior_analysis : {"defaultday_from_first_to_end":"string","feature_7d":{"jiedai4_sum_fail_cnt_d7":"string","jiedai_avg_defaultdays_d7":"string","jiedai4_count_fill_d3_cnt_d7":"string","jiedai_max_defaultdays_d7":"string","dd_jiedai_sum_fill_d5_cnt_d7":"string","dd_jiedai_count_fail_mamberadd_d7":"string","dd_jiedai_avg_fail_days1_d7":"string","jiedai_min_defaultdays_d7":"string","last_to_end_sure_due_all_pro_all_time_d7":"string","max_sure_due_days_non_cdq_all_time_d7":"string","sum_sure_due_days_all_pro_all_time_d7":"string","last_to_end_sure_due_non_cdq_all_time_d7":"string"},"feature_15d":{"jiedai_avg_defaultdays_d15":"string","jiedai_min_defaultdays_d15":"string","jiedai4_sum_fail_cnt_d15":"string","dd_jiedai_avg_fail_days1_d15":"string","jiedai4_count_fill_d3_cnt_d15":"string","jiedai4_count_fill_d5_cnt_d15":"string","dd_jiedai_count_fail_mamberadd_d15":"string","jiedai_sum_fail_amt_d15":"string","dd_jiedai_max_fail_days1_d15":"string","sum_sure_due_days_all_pro_all_time_d15":"string","last_to_end_sure_due_all_pro_all_time_d15":"string","max_sure_due_days_non_cdq_all_time_d15":"string","last_to_end_sure_due_non_cdq_all_time_d15":"string"},"feature_30d":{"jiedai_avg_defaultdays_m1":"string","dd_jiedai_max_fail_days1_m1":"string","jiedai4_sum_fail_cnt1":"string","dd_jiedai_max_fail_days_m1":"string","dd_jiedai_count_fail_mamberadd_m1":"string","jiedai4_count_fill_d3_cnt_m1":"string","dd_jiedai_min_fail_days1_m1":"string","jiedai_sum_fail_amt1":"string","cdq_dd_jiedai_max_fail_days1_m1":"string","jiedai4_count_fill_d5_cnt_m1":"string","jiedai4_avg_succ_amt1":"string","sum_sure_due_days_non_cdq_all_time_m1":"string","sum_sure_due_days_all_pro_all_time_m1":"string","avg_sure_due_days_non_cdq_all_time_m1":"string","pct_pay_amt_cdq_pro_all_time_m1":"string","max_pay_amt_all_pro_all_time_m1":"string"},"feature_90d":{"jiedai_avg_defaultdays_m3":"string","dd_jiedai_max_fail_days1_m3":"string","dd_jiedai_avg_fail_days_m3":"string","dd_jiedai_count_fail_mamberadd_m3":"string","jiedai4_count_fill_d3_cnt_m3":"string","jiedai4_count_fill_d5_cnt_m3":"string","cdq_dd_jiedai_avg_fail_days1_m3":"string","jiedai4_avg_succ_amt3":"string","jiedai_sum_fail_amt3":"string","dd_jiedai_min_fail_days1_m3":"string","sum_sure_due_days_all_pro_all_time_m3":"string","sum_sure_due_days_non_cdq_all_time_m3":"string","avg_sure_due_days_all_pro_all_time_m3":"string","max_due_cnt_non_cdq_all_time_m3":"string","avg_sure_due_days_non_cdq_all_time_m3":"string","pct_pay_amt_cdq_pro_all_time_m3":"string"},"feature_180d":{"jiedai_avg_defaultdays_m6":"string","dd_jiedai_avg_fail_days1_m6":"string","dd_jiedai_avg_fail_days_m6":"string","dd_jiedai_count_fail_mamberadd_m6":"string","jiedai4_count_fill_d3_cnt_m6":"string","cdq_dd_jiedai_avg_fail_days1_m6":"string","cdq_dd_jiedai_max_fail_days1_m6":"string","jiedai4_avg_succ_amt6":"string","jiedai4_count_fill_d5_cnt_m6":"string","sum_sure_due_days_non_cdq_all_time_m6":"string","max_sure_due_days_all_pro_all_time_m6":"string","avg_sure_due_days_all_pro_all_time_m6":"string","avg_sure_due_days_non_cdq_all_time_m6":"string","pct_pay_amt_cdq_pro_all_time_m6":"string"},"feature_360d":{"dd_jiedai_max_fail_days_m12":"string","dd_jiedai_sum_fill_d5_cnt_m12":"string","last_to_end_sure_due_all_pro_all_time_m12":"string","sum_sure_due_days_non_cdq_all_time_m12":"string","last_to_end_sure_due_non_cdq_all_time_m12":"string","max_due_cnt_all_pro_all_time_m12":"string","max_due_cnt_non_cdq_all_time_m12":"string","max_pay_amt_all_pro_all_time_m12":"string","sum_pay_cnt_all_pro_all_time_m12":"string"}}
     * fund_infos : [{"fund_basic":{"last_pay_date":"string","update_date":"string","open_date":"string","open_location":"string","account_status":"string","balance":"string","base_amount":"string","monthly_income":"string"},"fund_statistics":{"continuous_months":0,"repay_times":0,"total_companies":0,"total_months":"int"}}]
     * bank_infos : {"debit_card_info":{"update_date":"string","card_amount":0,"balance":"string","total_income":"string","total_salary_income":"string","total_loan_income":"string","total_outcome":"string","total_consume_outcome":"string","total_loan_outcome":"string"},"credit_card_info":{"update_date":"string","card_amount":0,"total_credit_limit":"string","total_credit_available":"string","max_credit_limit":"string","overdue_times":0,"overdue_months":"int"}}
     * fee : string
     */

    @JsonProperty("success")
    private boolean success;
    @JsonProperty("code")
    private String code;
    @JsonProperty("msg")
    private String msg;
    @JsonProperty("data")
    private DataBean data;

    @JsonProperty("fee")
    private String fee;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }
}
