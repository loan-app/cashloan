package com.jiya.cashloan.moxie;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rongdu.cashloan.cl.domain.mozhang.DataBean;
import com.rongdu.cashloan.cl.model.moxie.MxCreditRequest;
import com.rongdu.cashloan.cl.util.magic.MoxieSignUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 魔蝎风控测试调用
 * Created by szb on 18/11/29.
 */
public class MxRiskTest {

    public static void main(String[] args) throws Exception {
        demoToRiskGateway();
    }

    /**
     * 发送请求到魔蝎 风控网关 demo
     * @throws Exception
     */
    public static void demoToRiskGateway()throws Exception{
        /** app_id */
        String appId = "1c083a407db749959ab0011911d42398";
        //黑灰名单
//        String method = Method.MagicWand2BlackGray.getMethod();
        //魔杖
        String method = Method.MagicWand2.getMethod();
        /** 客户私钥 */
        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCYhUelDx060oB1Z6Go0QkzxyKtcVTZYMJtJtsKzwQjvYqHTGRZUkg1wKSehTb1kZFSa+uA6bM7xP5CAVnOL5QHZWy1a5kENIXXl8gRbvb35gSOTVhgaoEWUA7fRSj6Gm8c9g1MwGVihtVY8Bl6khidnqnDrooLopWvif7kajLBg2byoy8nsjLv9uMDVyjuYtiAJvaBW/7LcUfqSFjxWULkWXv6O5yYUIW3nY0uAGxPMbsrcFbtZ4jFsLVnb/Prny6jVEvTHAyXXxMKzf9JVPq0JLIkYzjArdSPuT2MzIVqfXAvVCaMrY5OfxIpogq7v7IzExWRALK6YQFNSFFQRm81AgMBAAECggEAJkAmu6qSthHczZTz4haqrCWg+MaXdPzjReScwpUwgJYki9IIyK3uFiz+HBNkuSmMvJL8ORRjMvYwnfUgxKkCAujY3pbhhUB24J7cOiMOJRW2xsl3BJcxUJs7X5tEr5S5kRXUOA94XcFa+Dce7LJ/IeiMwtqfHFPmHmQJXNeVaxpZhKYdJ9UUbwRpEawpnNEWlK8VMfJuItFBtmqQxWR+75OTcjEnETN8LIBOk+jk1F5ZTqe6QnsHxMjziQxrUaozMwEsawVwuo84pumZO8JsdB/6jklmXaH7tKfRO1zyXg/G+7Wpi/1vEHUed5nH9Sqi1XbtUzvPvJ92V+UBm/rd8QKBgQDeZ/2RnoUZVIv2rwKx9dP1s+ydKdWugFxwUGKAk798xdk5cBvAusTXN1y6JJevpaQhz7s6ieANHq2oUOc3XkVOUoQ2GE80zYmTeip8xk11b2GEBBq1IkyqbgO7Oc+WKh466GXAl0BPIU/PHCzJEfxWyDQIFq0MvnKK0aWhdKLC/wKBgQCvjvOW9rslIap4Wjq+ulai6QxHQhk4mVevLA37AZ9wNAS60VOcx2N5qzv6qFm16fyIhV8emOb1FRa70JHDvytSrxICeapU1D0+bQdwvAqR8EYZCFwhElGRczUzBrI1C6F7QCFXiogtFFK6iX97H0l+bgVzHxk5kkeCRKH6ax0xywKBgQCsqo9yPl+XCsye98Bf6Tplxwor+g6rK3yYtc/DgvFnLTM4JJFnT5kCfTjASyh4dWC33068Y85OYULxKd5YXhfwdp+uX7EejXQByxaxSENaqN5MX+NcPQTITCEZeghvzMMreMGGg+apiHMtl/ItKIl/1m6O2QwAmNrUa27rqmGygQKBgGWJ58eL4wgB5cgAX/PKcKt+4RQh3daHkliFwXxJHv9VhtrKkXKfPCC18lXyoxh9QgtwIGjhBMwBq4lrg7NU0WjcSy1VUzgHHXkKtH/2sqJf9xspY6fZIYZfvQhLOi1GEdroZ6BrRoHUPFkZh7sdiHNwjQRdm0haG7PzoD/nThZrAoGAVw4yyk6WtOQrqHLbfVKM4VZg7gJl3K58fYWYZpin1ADXqbeHrzUd3aeMhTZKnzqNZ9WcAvmxDzizhJPUUTutfEnXcJBiL3PpkmGHMv9IqSycq/8D2/iIZzHXT4KK69FsqH/FYYW9r8tmeCZuEoFM29qLHUV9Zy1OHIziDSzwPHQ=";

        /** 请求参数 */
        Map<String,String> reqParams = new HashMap<>();
        reqParams.put(ReqCommonParams.METHOD,method);
        reqParams.put(ReqCommonParams.APP_ID,appId);
        reqParams.put(ReqCommonParams.VERSION,ReqCommonParamsValue.VERSION);
        reqParams.put(ReqCommonParams.FORMAT,ReqCommonParamsValue.FORMAT);
        reqParams.put(ReqCommonParams.SIGN_TYPE,ReqCommonParamsValue.SIGN_TYPE);
        reqParams.put(ReqCommonParams.TIMESTAMP,String.valueOf(System.currentTimeMillis()));

        /** 业务参数 */
        Map<String,String> bizParams = new HashMap<>();
        bizParams.put("name","石振波");
        bizParams.put("mobile","15958189557");
        bizParams.put("idcard","362330199408051135");
        bizParams.put("qq","523798422");
        String bizContent = new ObjectMapper().writeValueAsString(bizParams);

        reqParams.put(ReqCommonParams.BIZ_CONTENT,bizContent);

        //签名
        String sign = MoxieSignUtils.signSHA1WithRSA(reqParams,privateKey);

        reqParams.put(ReqCommonParams.SIGN,sign);

        String getURL = getWholeGetURL(API_URL, reqParams);
//        String resContent = MxCreditRequest.get(getURL,null);

        String resContent = "{\"trans_id\":\"ef037660-f515-11e8-bfec-00163e0ed28c\",\"person_info\":{\"idcard\":\"36233019940805****\",\"idcard_location\":\"江西省/上饶市/波阳县\",\"mobile\":\"1595818****\",\"carrier\":\"中国移动\",\"mobile_location\":\"浙江/杭州\",\"name\":\"石**\",\"age\":24,\"gender\":\"男\",\"email\":\"\",\"education_info\":{\"level\":0,\"is_graduation\":false}},\"verification_info\":{\"has_carrier_data\":true,\"has_onlinebank_data\":false},\"black_info_detail\":{\"mobile_name_in_blacklist\":false,\"mobile_name_blacklist_updated_time\":\"\",\"idcard_name_in_blacklist\":false,\"idcard_name_blacklist_updated_time\":\"\",\"black_types\":\"\",\"blacklist_record\":{\"overdue_count\":0,\"overdue_amount\":\"\",\"overdue_status\":\"\"}},\"gray_info_detail\":{\"mobile_name_in_gray\":false,\"mobile_name_gray_updated_time\":\"\",\"idcard_name_in_gray\":false,\"idcard_name_gray_updated_time\":\"\",\"gray_types\":\"\",\"graylist_record\":{\"overdue_count\":0,\"overdue_amount\":\"\",\"overdue_status\":\"\"}},\"mobile_info\":{\"match_score\":77,\"mobile_contact_30d\":{\"contactnum\":33,\"auth_contactnum\":0,\"auth_contact_ratio\":\"0.00\",\"black_contactnum\":0,\"black_contactnum_ratio\":\"0.00\",\"contact_type\":\"\",\"auth_indirectnum\":0,\"auth_indirectnum_ratio\":\"0.00\",\"black_indirectnum\":0,\"black_indirectnum_ratio\":\"0.00\",\"black_indirect_peernum\":0,\"black_indirect_peernum_ratio\":\"0.00\",\"auth_indirect_peernum\":0,\"auth_indirect_peernum_ratio\":\"0.00\"},\"intimate_contact_info_30d\":{\"intimatenum\":7,\"auth_intimatenum\":0,\"auth_intimatenum_ratio\":\"0.00\",\"black_intimatenum\":0,\"black_intimatenum_ratio\":\"0.00\",\"intimate_type\":\"\",\"auth_intimate_indirectnum\":0,\"auth_intimate_indirectnum_ratio\":\"0.00\",\"black_intimate_indirectnum\":0,\"black_intimate_indirectnum_ratio\":\"0.00\",\"black_intimate_indirect_peernum\":0,\"black_intimate_indirect_peernum_ratio\":\"0.00\",\"auth_intimate_indirect_peernum\":0,\"auth_intimate_indirect_peernum_ratio\":\"0.00\"},\"mobile_contact_90d\":{\"contactnum\":60,\"auth_contactnum\":0,\"auth_contact_ratio\":\"0.00\",\"black_contactnum\":0,\"black_contactnum_ratio\":\"0.00\",\"contact_type\":\"\",\"auth_indirectnum\":0,\"auth_indirectnum_ratio\":\"0.00\",\"black_indirectnum\":0,\"black_indirectnum_ratio\":\"0.00\",\"black_indirect_peernum\":0,\"black_indirect_peernum_ratio\":\"0.00\",\"auth_indirect_peernum\":0,\"auth_indirect_peernum_ratio\":\"0.00\"},\"intimate_contact_info_90d\":{\"intimatenum\":14,\"auth_intimatenum\":0,\"auth_intimatenum_ratio\":\"0.00\",\"black_intimatenum\":0,\"black_intimatenum_ratio\":\"0.00\",\"intimate_type\":\"\",\"auth_intimate_indirectnum\":0,\"auth_intimate_indirectnum_ratio\":\"0.00\",\"black_intimate_indirectnum\":0,\"black_intimate_indirectnum_ratio\":\"0.00\",\"black_intimate_indirect_peernum\":0,\"black_intimate_indirect_peernum_ratio\":\"0.00\",\"auth_intimate_indirect_peernum\":0,\"auth_intimate_indirect_peernum_ratio\":\"0.00\"},\"mobile_contact_180d\":{\"contactnum\":93,\"auth_contactnum\":4,\"auth_contact_ratio\":\"0.04\",\"black_contactnum\":0,\"black_contactnum_ratio\":\"0.00\",\"contact_type\":\"\",\"auth_indirectnum\":426,\"auth_indirectnum_ratio\":\"1.00\",\"black_indirectnum\":23,\"black_indirectnum_ratio\":\"0.05\",\"black_indirect_peernum\":1,\"black_indirect_peernum_ratio\":\"0.01\",\"auth_indirect_peernum\":4,\"auth_indirect_peernum_ratio\":\"0.04\"},\"intimate_contact_info_180d\":{\"intimatenum\":14,\"auth_intimatenum\":0,\"auth_intimatenum_ratio\":\"0.00\",\"black_intimatenum\":0,\"black_intimatenum_ratio\":\"0.00\",\"intimate_type\":\"\",\"auth_intimate_indirectnum\":0,\"auth_intimate_indirectnum_ratio\":\"0.00\",\"black_intimate_indirectnum\":0,\"black_intimate_indirectnum_ratio\":\"0.00\",\"black_intimate_indirect_peernum\":0,\"black_intimate_indirect_peernum_ratio\":\"0.00\",\"auth_intimate_indirect_peernum\":0,\"auth_intimate_indirect_peernum_ratio\":\"0.00\"}},\"auth_queried_detail\":{\"register_info\":{\"other_count\":0,\"org_count\":0,\"org_types\":[]},\"queried_detail_org_count\":0,\"queried_analyze\":[],\"queried_infos\":[],\"loan_org_cnt_all\":0,\"loan_info\":{\"loan_org_cnt\":0,\"loan_cnt\":0,\"loan_org_cnt_15d\":0,\"loan_org_cnt_30d\":0,\"loan_org_cnt_90d\":0,\"loan_org_cnt_180d\":0,\"loan_cnt_15d\":0,\"loan_cnt_30d\":0,\"loan_cnt_90d\":0,\"loan_cnt_180d\":0}},\"untrusted_info\":{\"courtcase_cnt\":0,\"dishonest_cnt\":0,\"dishonest_detail_info\":[]},\"suspicious_idcard\":{\"other_names_cnt\":0,\"other_mobiles_cnt\":0,\"other_mobiles_black_cnt\":0,\"information_sources_cnt\":0,\"other_names\":[],\"other_mobiles\":[],\"information_sources\":[]},\"suspicious_mobile\":{\"other_names_cnt\":0,\"other_idcards_cnt\":0,\"other_idcards_black_cnt\":0,\"information_sources_cnt\":0,\"other_names\":[],\"other_idcards\":[],\"information_sources\":[]},\"suspicious_device\":{\"other_devices_cnt\":0,\"mobile_other_devices_cnt\":0,\"idcard_other_devices_cnt\":0,\"information_sources_cnt\":0,\"other_names_cnt\":0,\"other_mobiles_cnt\":0,\"other_mobiles_black_cnt\":0,\"other_idcards_cnt\":0,\"other_idcards_black_cnt\":0,\"other_names\":[],\"other_mobiles\":[],\"other_idcards\":[],\"information_sources\":[]},\"risk_qqgroup\":{\"loan_groupcnt\":0,\"installment_groupcnt\":0,\"financial_management_groupcnt\":0,\"woolen_groupcnt\":0,\"gambling_groupcnt\":0},\"risk_device\":[],\"credit_card\":{\"update_date\":\"\",\"bank_nums\":0,\"card_amount\":0,\"total_credit_limit\":\"\",\"max_credit_limit\":\"\",\"overdue_card\":0,\"bill_nums\":0,\"credit_overdue_item_12m\":{\"overdue_times\":0,\"overdue_months\":0},\"credit_overdue_item_6m\":{\"overdue_times\":0,\"overdue_months\":0},\"credit_overdue_item_3m\":{\"overdue_times\":0,\"overdue_months\":0},\"last_overdue_date\":\"\",\"max_overdue_money\":\"\",\"continue_overdue_times\":0},\"loan_behavior_analysis\":{\"defaultday_from_first_to_end\":\"\",\"feature_7d\":{\"jiedai4_sum_fail_cnt_d7\":\"\",\"jiedai_avg_defaultdays_d7\":\"\",\"jiedai4_count_fill_d3_cnt_d7\":\"\",\"jiedai_max_defaultdays_d7\":\"\",\"dd_jiedai_sum_fill_d5_cnt_d7\":\"\",\"dd_jiedai_count_fail_mamberadd_d7\":\"\",\"dd_jiedai_avg_fail_days1_d7\":\"\",\"jiedai_min_defaultdays_d7\":\"\",\"last_to_end_sure_due_all_pro_all_time_d7\":\"\",\"max_sure_due_days_non_cdq_all_time_d7\":\"\",\"sum_sure_due_days_all_pro_all_time_d7\":\"\",\"last_to_end_sure_due_non_cdq_all_time_d7\":\"\"},\"feature_15d\":{\"jiedai_avg_defaultdays_d15\":\"\",\"jiedai_min_defaultdays_d15\":\"\",\"jiedai4_sum_fail_cnt_d15\":\"\",\"dd_jiedai_avg_fail_days1_d15\":\"\",\"jiedai4_count_fill_d3_cnt_d15\":\"\",\"jiedai4_count_fill_d5_cnt_d15\":\"\",\"dd_jiedai_count_fail_mamberadd_d15\":\"\",\"jiedai_sum_fail_amt_d15\":\"\",\"dd_jiedai_max_fail_days1_d15\":\"\",\"sum_sure_due_days_all_pro_all_time_d15\":\"\",\"last_to_end_sure_due_all_pro_all_time_d15\":\"\",\"max_sure_due_days_non_cdq_all_time_d15\":\"\",\"last_to_end_sure_due_non_cdq_all_time_d15\":\"\"},\"feature_30d\":{\"jiedai_avg_defaultdays_m1\":\"\",\"dd_jiedai_max_fail_days1_m1\":\"\",\"jiedai4_sum_fail_cnt1\":\"\",\"dd_jiedai_max_fail_days_m1\":\"\",\"dd_jiedai_count_fail_mamberadd_m1\":\"\",\"jiedai4_count_fill_d3_cnt_m1\":\"\",\"dd_jiedai_min_fail_days1_m1\":\"\",\"jiedai_sum_fail_amt1\":\"\",\"cdq_dd_jiedai_max_fail_days1_m1\":\"\",\"jiedai4_count_fill_d5_cnt_m1\":\"\",\"jiedai4_avg_succ_amt1\":\"\",\"sum_sure_due_days_non_cdq_all_time_m1\":\"\",\"sum_sure_due_days_all_pro_all_time_m1\":\"\",\"avg_sure_due_days_non_cdq_all_time_m1\":\"\",\"pct_pay_amt_cdq_pro_all_time_m1\":\"\",\"max_pay_amt_all_pro_all_time_m1\":\"\"},\"feature_90d\":{\"jiedai_avg_defaultdays_m3\":\"\",\"dd_jiedai_max_fail_days1_m3\":\"\",\"dd_jiedai_avg_fail_days_m3\":\"\",\"dd_jiedai_count_fail_mamberadd_m3\":\"\",\"jiedai4_count_fill_d3_cnt_m3\":\"\",\"jiedai4_count_fill_d5_cnt_m3\":\"\",\"cdq_dd_jiedai_avg_fail_days1_m3\":\"\",\"jiedai4_avg_succ_amt3\":\"\",\"jiedai_sum_fail_amt3\":\"\",\"dd_jiedai_min_fail_days1_m3\":\"\",\"sum_sure_due_days_all_pro_all_time_m3\":\"\",\"sum_sure_due_days_non_cdq_all_time_m3\":\"\",\"avg_sure_due_days_all_pro_all_time_m3\":\"\",\"max_due_cnt_non_cdq_all_time_m3\":\"\",\"avg_sure_due_days_non_cdq_all_time_m3\":\"\",\"pct_pay_amt_cdq_pro_all_time_m3\":\"\"},\"feature_180d\":{\"jiedai_avg_defaultdays_m6\":\"\",\"dd_jiedai_avg_fail_days1_m6\":\"\",\"dd_jiedai_avg_fail_days_m6\":\"\",\"dd_jiedai_count_fail_mamberadd_m6\":\"\",\"jiedai4_count_fill_d3_cnt_m6\":\"\",\"cdq_dd_jiedai_avg_fail_days1_m6\":\"\",\"cdq_dd_jiedai_max_fail_days1_m6\":\"\",\"jiedai4_avg_succ_amt6\":\"\",\"jiedai4_count_fill_d5_cnt_m6\":\"\",\"sum_sure_due_days_non_cdq_all_time_m6\":\"\",\"max_sure_due_days_all_pro_all_time_m6\":\"\",\"avg_sure_due_days_all_pro_all_time_m6\":\"\",\"avg_sure_due_days_non_cdq_all_time_m6\":\"\",\"pct_pay_amt_cdq_pro_all_time_m6\":\"\"},\"feature_360d\":{\"dd_jiedai_max_fail_days_m12\":\"\",\"dd_jiedai_sum_fill_d5_cnt_m12\":\"\",\"last_to_end_sure_due_all_pro_all_time_m12\":\"\",\"sum_sure_due_days_non_cdq_all_time_m12\":\"\",\"last_to_end_sure_due_non_cdq_all_time_m12\":\"\",\"max_due_cnt_all_pro_all_time_m12\":\"\",\"max_due_cnt_non_cdq_all_time_m12\":\"\",\"max_pay_amt_all_pro_all_time_m12\":\"\",\"sum_pay_cnt_all_pro_all_time_m12\":\"\"}},\"fund_infos\":[],\"bank_infos\":{\"debit_card_info\":{\"update_date\":\"\",\"card_amount\":0,\"balance\":\"\",\"total_income\":\"\",\"total_salary_income\":\"\",\"total_loan_income\":\"\",\"total_outcome\":\"\",\"total_consume_outcome\":\"\",\"total_loan_outcome\":\"\"},\"credit_card_info\":{\"update_date\":\"\",\"card_amount\":0,\"total_credit_limit\":\"\",\"total_credit_available\":\"\",\"max_credit_limit\":\"\",\"overdue_times\":0,\"overdue_months\":0}},\"qualification_info\":{\"zm_score_info\":{\"last_modify_time\":\"2018-11-20\",\"zm_score\":\"优秀\"},\"huabei_info\":{\"last_modify_time\":\"2018-11-20 19:42:43\",\"huabai_limit\":\"4000~6000\"},\"jiebei_info\":{\"last_modify_time\":\"2018-11-20\",\"credit_amt\":\"20000~40000\"}},\"fraudulence_info\":{\"is_hit\":false,\"type\":\"\"}}";
        DataBean dataBean = JSONObject.parseObject(resContent, DataBean.class);
        System.out.println("resContent:\n"+resContent);
    }

    private final static String API_URL = "https://api.51datakey.com/risk-gateway/api/gateway";

    public enum Method{
        MagicWand2("moxie.api.risk.magicwand2","魔杖2.0"),
        MagicScore("moxie.api.risk.magicscore","魔分"),
        MagiccueTags("moxie.api.risk.magiccube.tags","魔方标签"),
        MagicWand2BlackGray("moxie.api.risk.magicwand2.black-gray","黑灰名单");
        private String method;
        private String desc;
        Method(String method,String desc){
            this.method = method;
            this.desc = desc;
        }
        public String getMethod(){
            return this.method;
        }
    }

    public interface ReqCommonParams{
        String METHOD = "method";
        String APP_ID = "app_id";
        String VERSION = "version";
        String FORMAT = "format";
        String SIGN_TYPE = "sign_type";
        String TIMESTAMP = "timestamp";
        String BIZ_CONTENT = "biz_content";
        String SIGN = "sign";
    }

    public interface ReqCommonParamsValue{
        String VERSION = "1.0";
        String FORMAT = "JSON";
        String SIGN_TYPE = "RSA";
    }

    public static String getWholeGetURL(String baseUrl,Map<String,String> data){
        if(data!=null && !data.isEmpty()){
            String params = mapToKeyPairString(data);
            if(baseUrl.indexOf("?")>0){
                baseUrl = baseUrl + "&"+ params;
            }else{
                baseUrl = baseUrl + "?"+ params;
            }
        }
        return baseUrl;
    }

    public static String mapToKeyPairString(Map<String,String> data){
        if(data!=null && !data.isEmpty()){
            StringBuffer dataStr = new StringBuffer();
            for(String key:data.keySet()){
                String value = data.get(key);
                dataStr.append(key).append("=").append(getEncodeParam(value)).append("&");
            }
            return dataStr.substring(0,dataStr.length()-1);
        }
        return "";
    }

    public static String getEncodeParam(String value){
        try {
            return URLEncoder.encode(value,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return value;
        }
    }
}
