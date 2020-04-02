package com.xiji.cashloan.cl.util.model;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CarrierMxUtils {
    /*
     * 解析运营商报告的标准数据
     */
    public static JSONObject parse(String data) {
        JSONObject model = null;
        try {
            if (StringUtils.isNotEmpty(data)) {
                model = new JSONObject();
                JSONObject reportData = JSONObject.parseObject(data);
                JSONObject friend_circle = reportData.getJSONObject("friend_circle");
                JSONArray user_info_check = reportData.getJSONArray("user_info_check");
                JSONArray call_time_details = reportData.getJSONArray("call_time_detail");
                JSONArray call_risk_analysis = reportData.getJSONArray("call_risk_analysis");
                JSONArray call_family_details = reportData.getJSONArray("call_family_detail");
                JSONArray call_service_analysiss = reportData.getJSONArray("call_service_analysis");
                JSONArray call_duration_detail = reportData.getJSONArray("call_duration_detail");
                JSONArray user_basic = reportData.getJSONArray("user_basic");
                JSONArray active_degree = reportData.getJSONArray("active_degree");
                JSONArray basic_check_items = reportData.getJSONArray("basic_check_items");
                JSONArray behavior_checks = reportData.getJSONArray("behavior_check");
                JSONArray consumption_details = reportData.getJSONArray("consumption_detail");
                JSONArray summarys = friend_circle.getJSONArray("summary");
                JSONObject user_info_check_index_0 = user_info_check.getJSONObject(0);
                JSONObject check_search_info = user_info_check_index_0.getJSONObject("check_search_info");
                JSONObject check_black_info = user_info_check_index_0.getJSONObject("check_black_info");
                model.put("searched_org_cnt", check_search_info.getString("searched_org_cnt"));
                model.put("contacts_class2_blacklist_cnt", check_black_info.getString("contacts_class2_blacklist_cnt"));
                model.put("contacts_class1_blacklist_cnt", check_black_info.getString("contacts_class1_blacklist_cnt"));
                model.put("contacts_class1_cnt", check_black_info.getString("contacts_class1_cnt"));
                model.put("contacts_router_cnt", check_black_info.getString("contacts_router_cnt"));
                model.put("phone_gray_score", check_black_info.getString("phone_gray_score"));
                model.put("contacts_router_ratio", check_black_info.getString("contacts_router_ratio"));
                for (int i = 0; i < user_basic.size(); i++) {
                    JSONObject user = user_basic.getJSONObject(i);
                    if ("age".equals(user.getString("key"))) {
                        model.put("age", user.getString("value"));
                    } else if ("gender".equals(user.getString("key"))) {
                        model.put("gender", "男".equals(user.getString("value")) ? 0 : 1);
                    }
                }
                for (int i = 0; i < basic_check_items.size(); i++) {
                    JSONObject basic_check_item = basic_check_items.getJSONObject(i);
                    if ("arrearage_risk_6m".equals(basic_check_item.getString("check_item"))) {
                        model.put("arrearage_risk_6m", basic_check_item.getString("result").contains("月无") ? -1 : basic_check_item.getIntValue("result"));
                    } else if ("mobile_silence_3m".equals(basic_check_item.getString("check_item"))) {
                        model.put("mobile_silence_3m", basic_check_item.getString("result"));
                    }
                }
                for (int i = 0; i < behavior_checks.size(); i++) {
                    JSONObject behavior_check = behavior_checks.getJSONObject(i);
                    if ("contact_loan".equals(behavior_check.getString("check_point"))) {
                        model.put("contact_loan_score", behavior_check.getString("score"));
                    }
                }
                for (int i = 0; i < summarys.size(); i++) {
                    JSONObject summary = summarys.getJSONObject(i);
                    if ("inter_peer_num_6m".equals(summary.getString("key"))) {
                        model.put("inter_peer_num_6m", summary.getString("value"));
                    } else if ("good_friend_num_6m".equals(summary.getString("key"))) {
                        model.put("good_friend_num_6m", summary.getString("value"));
                    } else if ("inter_peer_num_3m".equals(summary.getString("key"))) {
                        model.put("inter_peer_num_3m", summary.getString("value"));
                    } else if ("good_friend_num_3m".equals(summary.getString("key"))) {
                        model.put("good_friend_num_3m", summary.getString("value"));
                    } else if ("friend_num_6m".equals(summary.getString("key"))) {
                        model.put("friend_num_6m", summary.getString("value"));
                    } else if ("friend_num_3m".equals(summary.getString("key"))) {
                        model.put("friend_num_3m", summary.getString("value"));
                    } else if ("is_city_match_friend_city_center_3m".equals(summary.getString("key"))) {
                        model.put("is_city_match_friend_city_center_3m", "是".equals(summary.getString("value")) ? 1 : 0);
                    }
                }
                for (int i = 0; i < call_family_details.size(); i++) {
                    JSONObject call_family_detail = call_family_details.getJSONObject(i);
                    if ("continue_recharge_month_cnt".equals(call_family_detail.getString("app_point"))) {
                        model.put("continue_recharge_month_cnt_item_6m", call_family_detail.getJSONObject("item").getString("item_6m"));
                    } else if ("live_month_cnt".equals(call_family_detail.getString("app_point"))) {
                        model.put("live_month_cnt_item_6m", call_family_detail.getJSONObject("item").getString("item_6m"));
                    } else if ("unpaid_month_cnt".equals(call_family_detail.getString("app_point"))) {
                        model.put("unpaid_month_cnt_item_3m", call_family_detail.getJSONObject("item").getString("item_3m"));
                        model.put("unpaid_month_cnt_item_6m", call_family_detail.getJSONObject("item").getString("item_6m"));
                    } else if ("continue_recharge_month_cnt".equals(call_family_detail.getString("app_point"))) {
                        model.put("continue_recharge_month_cnt_item_1m", call_family_detail.getJSONObject("item").getString("item_1m"));
                        model.put("continue_recharge_month_cnt_item_3m", call_family_detail.getJSONObject("item").getString("item_3m"));
                    } else if ("live_month_cnt".equals(call_family_detail.getString("app_point"))) {
                        model.put("live_month_cnt_item_3m", call_family_detail.getJSONObject("item").getString("item_3m"));
                    }
                }
                for (int i = 0; i < consumption_details.size(); i++) {
                    JSONObject consumption_detail = consumption_details.getJSONObject(i);
                    if ("total_fee".equals(consumption_detail.getString("app_point"))) {
                        model.put("total_fee_avg_item_3m", consumption_detail.getJSONObject("item").getString("avg_item_3m"));
                        model.put("total_fee_avg_item_6m", consumption_detail.getJSONObject("item").getString("avg_item_6m"));
                        model.put("total_fee_item_1m", consumption_detail.getJSONObject("item").getString("item_1m"));
                        model.put("total_fee_item_3m", consumption_detail.getJSONObject("item").getString("item_3m"));
                        model.put("total_fee_item_6m", consumption_detail.getJSONObject("item").getString("item_6m"));
                    } else if ("recharge_amount".equals(consumption_detail.getString("app_point"))) {
                        model.put("recharge_amount_avg_item_3m", consumption_detail.getJSONObject("item").getString("avg_item_3m"));
                        model.put("recharge_amount_avg_item_6m", consumption_detail.getJSONObject("item").getString("avg_item_6m"));
                        model.put("recharge_amount_item_1m", consumption_detail.getJSONObject("item").getString("item_1m"));
                        model.put("recharge_amount_item_3m", consumption_detail.getJSONObject("item").getString("item_3m"));
                        model.put("recharge_amount_item_6m", consumption_detail.getJSONObject("item").getString("item_6m"));
                    } else if ("sms_fee".equals(consumption_detail.getString("app_point"))) {
                        model.put("sms_fee_avg_item_3m", consumption_detail.getJSONObject("item").getString("avg_item_3m"));
                        model.put("sms_fee_avg_item_6m", consumption_detail.getJSONObject("item").getString("avg_item_6m"));
                        model.put("sms_fee_item_1m", consumption_detail.getJSONObject("item").getString("item_1m"));
                        model.put("sms_fee_item_3m", consumption_detail.getJSONObject("item").getString("item_3m"));
                        model.put("sms_fee_item_6m", consumption_detail.getJSONObject("item").getString("item_6m"));
                    } else if ("net_fee".equals(consumption_detail.getString("app_point"))) {
                        model.put("net_fee_avg_item_3m", consumption_detail.getJSONObject("item").getString("avg_item_3m"));
                        model.put("net_fee_avg_item_6m", consumption_detail.getJSONObject("item").getString("avg_item_6m"));
                        model.put("net_fee_item_1m", consumption_detail.getJSONObject("item").getString("item_1m"));
                        model.put("net_fee_item_3m", consumption_detail.getJSONObject("item").getString("item_3m"));
                        model.put("net_fee_item_6m", consumption_detail.getJSONObject("item").getString("item_6m"));
                    } else if ("vas_fee".equals(consumption_detail.getString("app_point"))) {
                        model.put("vas_fee_item_1m", consumption_detail.getJSONObject("item").getString("item_1m"));
                        model.put("vas_fee_item_3m", consumption_detail.getJSONObject("item").getString("item_3m"));
                        model.put("vas_fee_item_6m", consumption_detail.getJSONObject("item").getString("item_6m"));
                        model.put("vas_fee_avg_item_3m", consumption_detail.getJSONObject("item").getString("avg_item_3m"));
                        model.put("vas_fee_avg_item_6m", consumption_detail.getJSONObject("item").getString("avg_item_6m"));
                    } else if ("voice_fee".equals(consumption_detail.getString("app_point"))) {
                        model.put("voice_fee_item_1m", consumption_detail.getJSONObject("item").getString("item_1m"));
                        model.put("voice_fee_item_3m", consumption_detail.getJSONObject("item").getString("item_3m"));
                        model.put("voice_fee_item_6m", consumption_detail.getJSONObject("item").getString("item_6m"));
                        model.put("voice_fee_avg_item_3m", consumption_detail.getJSONObject("item").getString("avg_item_3m"));
                        model.put("voice_fee_avg_item_6m", consumption_detail.getJSONObject("item").getString("avg_item_6m"));
                    } else if ("max_single_recharge".equals(consumption_detail.getString("app_point"))) {
                        model.put("max_single_recharge_item_1m", consumption_detail.getJSONObject("item").getString("item_1m"));
                        model.put("max_single_recharge_item_3m", consumption_detail.getJSONObject("item").getString("item_3m"));
                        model.put("max_single_recharge_item_6m", consumption_detail.getJSONObject("item").getString("item_6m"));
                    } else if ("extra_fee".equals(consumption_detail.getString("app_point"))) {
                        model.put("extra_fee_avg_item_3m", consumption_detail.getJSONObject("item").getString("avg_item_3m"));
                        model.put("extra_fee_avg_item_6m", consumption_detail.getJSONObject("item").getString("avg_item_6m"));
                        model.put("extra_fee_item_1m", consumption_detail.getJSONObject("item").getString("item_1m"));
                        model.put("extra_fee_item_3m", consumption_detail.getJSONObject("item").getString("item_3m"));
                        model.put("extra_fee_item_6m", consumption_detail.getJSONObject("item").getString("item_6m"));
                    }
                }
                for (int i = 0; i < call_time_details.size(); i++) {
                    JSONObject call_time_detail = call_time_details.getJSONObject(i);
                    if ("night_time".equals(call_time_detail.getString("app_point"))) {
                        model.put("night_time_item_1m", call_time_detail.getJSONObject("item").getString("item_1m"));
                        model.put("night_time_item_3m", call_time_detail.getJSONObject("item").getString("item_3m"));
                        model.put("night_time_item_6m", call_time_detail.getJSONObject("item").getString("item_6m"));
                        model.put("night_time_avg_item_3m", call_time_detail.getJSONObject("item").getString("avg_item_3m"));
                        model.put("night_time_avg_item_6m", call_time_detail.getJSONObject("item").getString("avg_item_6m"));
                    } else if ("max_single_time".equals(call_time_detail.getString("app_point"))) {
                        model.put("max_single_time_item_1m", call_time_detail.getJSONObject("item").getString("item_1m"));
                        model.put("max_single_time_item_3m", call_time_detail.getJSONObject("item").getString("item_3m"));
                        model.put("max_single_time_item_6m", call_time_detail.getJSONObject("item").getString("item_6m"));
                    } else if ("cnt_1min_within".equals(call_time_detail.getString("app_point"))) {
                        model.put("cnt_1min_within_avg_item_3m", call_time_detail.getJSONObject("item").getString("avg_item_3m"));
                        model.put("cnt_1min_within_avg_item_6m", call_time_detail.getJSONObject("item").getString("avg_item_6m"));
                        model.put("cnt_1min_within_item_1m", call_time_detail.getJSONObject("item").getString("item_1m"));
                        model.put("cnt_1min_within_item_3m", call_time_detail.getJSONObject("item").getString("item_3m"));
                        model.put("cnt_1min_within_item_6m", call_time_detail.getJSONObject("item").getString("item_6m"));
                    } else if ("remote_time".equals(call_time_detail.getString("app_point"))) {
                        model.put("remote_time_avg_item_3m", call_time_detail.getJSONObject("item").getString("avg_item_3m"));
                        model.put("remote_time_avg_item_6m", call_time_detail.getJSONObject("item").getString("avg_item_6m"));
                        model.put("remote_time_item_1m", call_time_detail.getJSONObject("item").getString("item_1m"));
                        model.put("remote_time_item_3m", call_time_detail.getJSONObject("item").getString("item_3m"));
                        model.put("remote_time_item_6m", call_time_detail.getJSONObject("item").getString("item_6m"));
                    } else if ("cnt_1min_5min".equals(call_time_detail.getString("app_point"))) {
                        model.put("cnt_1min_5min_avg_item_3m", call_time_detail.getJSONObject("item").getString("avg_item_3m"));
                        model.put("cnt_1min_5min_avg_item_6m", call_time_detail.getJSONObject("item").getString("avg_item_6m"));
                        model.put("cnt_1min_5min_item_1m", call_time_detail.getJSONObject("item").getString("item_1m"));
                        model.put("cnt_1min_5min_item_3m", call_time_detail.getJSONObject("item").getString("item_3m"));
                        model.put("cnt_1min_5min_item_6m", call_time_detail.getJSONObject("item").getString("item_6m"));
                    } else if ("night_cnt".equals(call_time_detail.getString("app_point"))) {
                        model.put("night_cnt_avg_item_3m", call_time_detail.getJSONObject("item").getString("avg_item_3m"));
                        model.put("night_cnt_avg_item_6m", call_time_detail.getJSONObject("item").getString("avg_item_6m"));
                        model.put("night_cnt_item_1m", call_time_detail.getJSONObject("item").getString("item_1m"));
                        model.put("night_cnt_item_3m", call_time_detail.getJSONObject("item").getString("item_3m"));
                        model.put("night_cnt_item_6m", call_time_detail.getJSONObject("item").getString("item_6m"));
                    } else if ("cnt_5min_10min".equals(call_time_detail.getString("app_point"))) {
                        model.put("cnt_5min_10min_avg_item_3m", call_time_detail.getJSONObject("item").getString("avg_item_3m"));
                        model.put("cnt_5min_10min_avg_item_6m", call_time_detail.getJSONObject("item").getString("avg_item_6m"));
                        model.put("cnt_5min_10min_item_1m", call_time_detail.getJSONObject("item").getString("item_1m"));
                        model.put("cnt_5min_10min_item_3m", call_time_detail.getJSONObject("item").getString("item_3m"));
                        model.put("cnt_5min_10min_item_6m", call_time_detail.getJSONObject("item").getString("item_6m"));
                    } else if ("local_cnt".equals(call_time_detail.getString("app_point"))) {
                        model.put("local_cnt_avg_item_3m", call_time_detail.getJSONObject("item").getString("avg_item_3m"));
                        model.put("local_cnt_avg_item_6m", call_time_detail.getJSONObject("item").getString("avg_item_6m"));
                        model.put("local_cnt_item_1m", call_time_detail.getJSONObject("item").getString("item_1m"));
                        model.put("local_cnt_item_3m", call_time_detail.getJSONObject("item").getString("item_3m"));
                        model.put("local_cnt_item_6m", call_time_detail.getJSONObject("item").getString("item_6m"));
                    } else if ("remote_cnt".equals(call_time_detail.getString("app_point"))) {
                        model.put("remote_cnt_avg_item_3m", call_time_detail.getJSONObject("item").getString("avg_item_3m"));
                        model.put("remote_cnt_avg_item_6m", call_time_detail.getJSONObject("item").getString("avg_item_6m"));
                        model.put("remote_cnt_item_1m", call_time_detail.getJSONObject("item").getString("item_1m"));
                        model.put("remote_cnt_item_3m", call_time_detail.getJSONObject("item").getString("item_3m"));
                        model.put("remote_cnt_item_6m", call_time_detail.getJSONObject("item").getString("item_6m"));
                    } else if ("cnt_10min_over".equals(call_time_detail.getString("app_point"))) {
                        model.put("cnt_10min_over_avg_item_3m", call_time_detail.getJSONObject("item").getString("avg_item_3m"));
                        model.put("cnt_10min_over_avg_item_6m", call_time_detail.getJSONObject("item").getString("avg_item_6m"));
                        model.put("cnt_10min_over_item_1m", call_time_detail.getJSONObject("item").getString("item_1m"));
                        model.put("cnt_10min_over_item_3m", call_time_detail.getJSONObject("item").getString("item_3m"));
                        model.put("cnt_10min_over_item_6m", call_time_detail.getJSONObject("item").getString("item_6m"));
                    } else if ("local_time".equals(call_time_detail.getString("app_point"))) {
                        model.put("local_time_avg_item_3m", call_time_detail.getJSONObject("item").getString("avg_item_3m"));
                        model.put("local_time_avg_item_6m", call_time_detail.getJSONObject("item").getString("avg_item_6m"));
                        model.put("local_time_item_1m", call_time_detail.getJSONObject("item").getString("item_1m"));
                        model.put("local_time_item_3m", call_time_detail.getJSONObject("item").getString("item_3m"));
                        model.put("local_time_item_6m", call_time_detail.getJSONObject("item").getString("item_6m"));
                    } else if ("daytime_time".equals(call_time_detail.getString("app_point"))) {
                        model.put("daytime_time_item_1m", call_time_detail.getJSONObject("item").getString("item_1m"));
                        model.put("daytime_time_item_3m", call_time_detail.getJSONObject("item").getString("item_3m"));
                        model.put("daytime_time_item_6m", call_time_detail.getJSONObject("item").getString("item_6m"));
                    } else if ("min_single_time".equals(call_time_detail.getString("app_point"))) {
                        model.put("min_single_time_item_1m", call_time_detail.getJSONObject("item").getString("item_1m"));
                        model.put("min_single_time_item_3m", call_time_detail.getJSONObject("item").getString("item_3m"));
                        model.put("min_single_time_item_6m", call_time_detail.getJSONObject("item").getString("item_6m"));
                    } else if ("daytime_cnt".equals(call_time_detail.getString("app_point"))) {
                        model.put("daytime_cnt_avg_item_6m", call_time_detail.getJSONObject("item").getString("avg_item_6m"));
                        model.put("daytime_cnt_item_1m", call_time_detail.getJSONObject("item").getString("item_1m"));
                        model.put("daytime_cnt_item_6m", call_time_detail.getJSONObject("item").getString("item_6m"));
                    }
                }
                for (int i = 0; i < call_duration_detail.size(); i++) {
                    JSONObject call_duration = call_duration_detail.getJSONObject(i);
                    if ("call_duration_detail_6m".equals(call_duration.getString("key"))) {
                        JSONArray duration_list = call_duration.getJSONArray("duration_list");
                        for (int j = 0; j < duration_list.size(); j++) {
                            JSONObject duration = duration_list.getJSONObject(j);
                            if ("forenoon".equals(duration.getString("time_step"))) {
                                model.put("call_duration_detail_6m_forenoon_total_time", duration.getJSONObject("item").getString("total_time"));
                                model.put("call_duration_detail_6m_forenoon_uniq_num_cnt", duration.getJSONObject("item").getString("uniq_num_cnt"));
                                model.put("call_duration_detail_6m_forenoon_dialed_cnt", duration.getJSONObject("item").getString("dialed_cnt"));
                                model.put("call_duration_detail_6m_forenoon_dial_cnt", duration.getJSONObject("item").getString("dial_cnt"));
                                model.put("call_duration_detail_6m_forenoon_dialed_time", duration.getJSONObject("item").getString("dialed_time"));
                                model.put("call_duration_detail_6m_forenoon_dial_time", duration.getJSONObject("item").getString("dial_time"));
                                model.put("call_duration_detail_6m_forenoon_total_cnt", duration.getJSONObject("item").getString("total_cnt"));
                            } else if ("afternoon".equals(duration.getString("time_step"))) {
                                model.put("call_duration_detail_6m_afternoon_uniq_num_cnt", duration.getJSONObject("item").getString("uniq_num_cnt"));
                                model.put("call_duration_detail_6m_afternoon_dialed_time", duration.getJSONObject("item").getString("dialed_time"));
                                model.put("call_duration_detail_6m_afternoon_dialed_cnt", duration.getJSONObject("item").getString("dialed_cnt"));
                                model.put("call_duration_detail_6m_afternoon_dial_time", duration.getJSONObject("item").getString("dial_time"));
                                model.put("call_duration_detail_6m_afternoon_total_time", duration.getJSONObject("item").getString("total_time"));
                                model.put("call_duration_detail_6m_afternoon_dial_cnt", duration.getJSONObject("item").getString("dial_cnt"));
                                model.put("call_duration_detail_6m_afternoon_total_cnt", duration.getJSONObject("item").getString("total_cnt"));
                            } else if ("morning".equals(duration.getString("time_step"))) {
                                model.put("call_duration_detail_6m_morning_dialed_time", duration.getJSONObject("item").getString("dialed_time"));
                                model.put("call_duration_detail_6m_morning_uniq_num_cnt", duration.getJSONObject("item").getString("uniq_num_cnt"));
                                model.put("call_duration_detail_6m_morning_dial_time", duration.getJSONObject("item").getString("dial_time"));
                                model.put("call_duration_detail_6m_morning_dial_cnt", duration.getJSONObject("item").getString("dial_cnt"));
                                model.put("call_duration_detail_6m_morning_total_time", duration.getJSONObject("item").getString("total_time"));
                                model.put("call_duration_detail_6m_morning_total_cnt", duration.getJSONObject("item").getString("total_cnt"));
                                model.put("call_duration_detail_6m_morning_dialed_cnt", duration.getJSONObject("item").getString("dialed_cnt"));
                            } else if ("noon".equals(duration.getString("time_step"))) {
                                model.put("call_duration_detail_6m_noon_uniq_num_cnt", duration.getJSONObject("item").getString("uniq_num_cnt"));
                                model.put("call_duration_detail_6m_noon_dial_cnt", duration.getJSONObject("item").getString("dial_cnt"));
                                model.put("call_duration_detail_6m_noon_total_time", duration.getJSONObject("item").getString("total_time"));
                                model.put("call_duration_detail_6m_noon_dial_time", duration.getJSONObject("item").getString("dial_time"));
                                model.put("call_duration_detail_6m_noon_dialed_cnt", duration.getJSONObject("item").getString("dialed_cnt"));
                                model.put("call_duration_detail_6m_noon_dialed_time", duration.getJSONObject("item").getString("dialed_time"));
                                model.put("call_duration_detail_6m_noon_total_cnt", duration.getJSONObject("item").getString("total_cnt"));
                            } else if ("dusk".equals(duration.getString("time_step"))) {
                                model.put("call_duration_detail_6m_dusk_dial_time", duration.getJSONObject("item").getString("dial_time"));
                                model.put("call_duration_detail_6m_dusk_dialed_time", duration.getJSONObject("item").getString("dialed_time"));
                                model.put("call_duration_detail_6m_dusk_dialed_cnt", duration.getJSONObject("item").getString("dialed_cnt"));
                                model.put("call_duration_detail_6m_dusk_total_time", duration.getJSONObject("item").getString("total_time"));
                                model.put("call_duration_detail_6m_dusk_uniq_num_cnt", duration.getJSONObject("item").getString("uniq_num_cnt"));
                                model.put("call_duration_detail_6m_dusk_total_cnt", duration.getJSONObject("item").getString("total_cnt"));
                                model.put("call_duration_detail_6m_dusk_dial_cnt", duration.getJSONObject("item").getString("dial_cnt"));
                            } else if ("evening".equals(duration.getString("time_step"))) {
                                model.put("call_duration_detail_6m_evening_dial_cnt", duration.getJSONObject("item").getString("dial_cnt"));
                                model.put("call_duration_detail_6m_evening_uniq_num_cnt", duration.getJSONObject("item").getString("uniq_num_cnt"));
                                model.put("call_duration_detail_6m_evening_dial_time", duration.getJSONObject("item").getString("dial_time"));
                                model.put("call_duration_detail_6m_evening_dialed_time", duration.getJSONObject("item").getString("dialed_time"));
                                model.put("call_duration_detail_6m_evening_total_time", duration.getJSONObject("item").getString("total_time"));
                                model.put("call_duration_detail_6m_evening_dialed_cnt", duration.getJSONObject("item").getString("dialed_cnt"));
                                model.put("call_duration_detail_6m_evening_total_cnt", duration.getJSONObject("item").getString("total_cnt"));
                            } else if ("daybreak".equals(duration.getString("time_step"))) {
                                model.put("call_duration_detail_6m_daybreak_dialed_time", duration.getJSONObject("item").getString("dialed_time"));
                                model.put("call_duration_detail_6m_daybreak_dial_time", duration.getJSONObject("item").getString("dial_time"));
                                model.put("call_duration_detail_6m_daybreak_total_time", duration.getJSONObject("item").getString("total_time"));
                                model.put("call_duration_detail_6m_daybreak_uniq_num_cnt", duration.getJSONObject("item").getString("uniq_num_cnt"));
                                model.put("call_duration_detail_6m_daybreak_dial_cnt", duration.getJSONObject("item").getString("dial_cnt"));
                                model.put("call_duration_detail_6m_daybreak_dialed_cnt", duration.getJSONObject("item").getString("dialed_cnt"));
                                model.put("call_duration_detail_6m_daybreak_total_cnt", duration.getJSONObject("item").getString("total_cnt"));
                            } else if ("midnight".equals(duration.getString("time_step"))) {
                                model.put("call_duration_detail_6m_midnight_dialed_time", duration.getJSONObject("item").getString("dialed_time"));
                                model.put("call_duration_detail_6m_midnight_total_time", duration.getJSONObject("item").getString("total_time"));
                                model.put("call_duration_detail_6m_midnight_dial_time", duration.getJSONObject("item").getString("dial_time"));
                                model.put("call_duration_detail_6m_midnight_uniq_num_cnt", duration.getJSONObject("item").getString("uniq_num_cnt"));
                                model.put("call_duration_detail_6m_midnight_dialed_cnt", duration.getJSONObject("item").getString("dialed_cnt"));
                                model.put("call_duration_detail_6m_midnight_total_cnt", duration.getJSONObject("item").getString("total_cnt"));
                                model.put("call_duration_detail_6m_midnight_dial_cnt", duration.getJSONObject("item").getString("dial_cnt"));
                            }
                        }
                    } else if ("call_duration_detail_3m".equals(call_duration.getString("key"))) {
                        JSONArray duration_list = call_duration.getJSONArray("duration_list");
                        for (int j = 0; j < duration_list.size(); j++) {
                            JSONObject duration = duration_list.getJSONObject(j);
                            if ("forenoon".equals(duration.getString("time_step"))) {
                                model.put("call_duration_detail_3m_forenoon_dialed_time", duration.getJSONObject("item").getString("dialed_time"));
                                model.put("call_duration_detail_3m_forenoon_dial_time", duration.getJSONObject("item").getString("dial_time"));
                                model.put("call_duration_detail_3m_forenoon_uniq_num_cnt", duration.getJSONObject("item").getString("uniq_num_cnt"));
                                model.put("call_duration_detail_3m_forenoon_dialed_cnt", duration.getJSONObject("item").getString("dialed_cnt"));
                                model.put("call_duration_detail_3m_forenoon_dial_cnt", duration.getJSONObject("item").getString("dial_cnt"));
                                model.put("call_duration_detail_3m_forenoon_total_cnt", duration.getJSONObject("item").getString("total_cnt"));
                                model.put("call_duration_detail_3m_forenoon_total_time", duration.getJSONObject("item").getString("total_time"));
                            } else if ("evening".equals(duration.getString("time_step"))) {
                                model.put("call_duration_detail_3m_evening_dialed_time", duration.getJSONObject("item").getString("dialed_time"));
                                model.put("call_duration_detail_3m_evening_total_cnt", duration.getJSONObject("item").getString("total_cnt"));
                                model.put("call_duration_detail_3m_evening_total_time", duration.getJSONObject("item").getString("total_time"));
                                model.put("call_duration_detail_3m_evening_dial_time", duration.getJSONObject("item").getString("dial_time"));
                                model.put("call_duration_detail_3m_evening_uniq_num_cnt", duration.getJSONObject("item").getString("uniq_num_cnt"));
                                model.put("call_duration_detail_3m_evening_dialed_cnt", duration.getJSONObject("item").getString("dialed_cnt"));
                                model.put("call_duration_detail_3m_evening_dial_cnt", duration.getJSONObject("item").getString("dial_cnt"));
                            } else if ("morning".equals(duration.getString("time_step"))) {
                                model.put("call_duration_detail_3m_morning_dialed_cnt", duration.getJSONObject("item").getString("dialed_cnt"));
                                model.put("call_duration_detail_3m_morning_dialed_time", duration.getJSONObject("item").getString("dialed_time"));
                                model.put("call_duration_detail_3m_morning_total_time", duration.getJSONObject("item").getString("total_time"));
                                model.put("call_duration_detail_3m_morning_dial_time", duration.getJSONObject("item").getString("dial_time"));
                                model.put("call_duration_detail_3m_morning_uniq_num_cnt", duration.getJSONObject("item").getString("uniq_num_cnt"));
                                model.put("call_duration_detail_3m_morning_total_cnt", duration.getJSONObject("item").getString("total_cnt"));
                                model.put("call_duration_detail_3m_morning_dial_cnt", duration.getJSONObject("item").getString("dial_cnt"));
                            } else if ("afternoon".equals(duration.getString("time_step"))) {
                                model.put("call_duration_detail_3m_afternoon_dialed_cnt", duration.getJSONObject("item").getString("dialed_cnt"));
                                model.put("call_duration_detail_3m_afternoon_uniq_num_cnt", duration.getJSONObject("item").getString("uniq_num_cnt"));
                                model.put("call_duration_detail_3m_afternoon_dialed_time", duration.getJSONObject("item").getString("dialed_time"));
                                model.put("call_duration_detail_3m_afternoon_dial_time", duration.getJSONObject("item").getString("dial_time"));
                                model.put("call_duration_detail_3m_afternoon_total_cnt", duration.getJSONObject("item").getString("total_cnt"));
                                model.put("call_duration_detail_3m_afternoon_dial_cnt", duration.getJSONObject("item").getString("dial_cnt"));
                                model.put("call_duration_detail_3m_afternoon_total_time", duration.getJSONObject("item").getString("total_time"));
                            } else if ("noon".equals(duration.getString("time_step"))) {
                                model.put("call_duration_detail_3m_noon_dialed_cnt", duration.getJSONObject("item").getString("dialed_cnt"));
                                model.put("call_duration_detail_3m_noon_dial_time", duration.getJSONObject("item").getString("dial_time"));
                                model.put("call_duration_detail_3m_noon_dialed_time", duration.getJSONObject("item").getString("dialed_time"));
                                model.put("call_duration_detail_3m_noon_uniq_num_cnt", duration.getJSONObject("item").getString("uniq_num_cnt"));
                                model.put("call_duration_detail_3m_noon_total_cnt", duration.getJSONObject("item").getString("total_cnt"));
                                model.put("call_duration_detail_3m_noon_total_time", duration.getJSONObject("item").getString("total_time"));
                                model.put("call_duration_detail_3m_noon_dial_cnt", duration.getJSONObject("item").getString("dial_cnt"));
                            } else if ("dusk".equals(duration.getString("time_step"))) {
                                model.put("call_duration_detail_3m_dusk_total_cnt", duration.getJSONObject("item").getString("total_cnt"));
                                model.put("call_duration_detail_3m_dusk_dialed_cnt", duration.getJSONObject("item").getString("dialed_cnt"));
                                model.put("call_duration_detail_3m_dusk_dialed_time", duration.getJSONObject("item").getString("dialed_time"));
                                model.put("call_duration_detail_3m_dusk_total_time", duration.getJSONObject("item").getString("total_time"));
                                model.put("call_duration_detail_3m_dusk_uniq_num_cnt", duration.getJSONObject("item").getString("uniq_num_cnt"));
                                model.put("call_duration_detail_3m_dusk_dial_time", duration.getJSONObject("item").getString("dial_time"));
                                model.put("call_duration_detail_3m_dusk_dial_cnt", duration.getJSONObject("item").getString("dial_cnt"));
                            } else if ("daybreak".equals(duration.getString("time_step"))) {
                                model.put("call_duration_detail_3m_daybreak_dial_time", duration.getJSONObject("item").getString("dial_time"));
                                model.put("call_duration_detail_3m_daybreak_total_time", duration.getJSONObject("item").getString("total_time"));
                                model.put("call_duration_detail_3m_daybreak_dialed_time", duration.getJSONObject("item").getString("dialed_time"));
                                model.put("call_duration_detail_3m_daybreak_dialed_cnt", duration.getJSONObject("item").getString("dialed_cnt"));
                                model.put("call_duration_detail_3m_daybreak_total_cnt", duration.getJSONObject("item").getString("total_cnt"));
                                model.put("call_duration_detail_3m_daybreak_uniq_num_cnt", duration.getJSONObject("item").getString("uniq_num_cnt"));
                                model.put("call_duration_detail_3m_daybreak_dial_cnt", duration.getJSONObject("item").getString("dial_cnt"));
                            } else if ("midnight".equals(duration.getString("time_step"))) {
                                model.put("call_duration_detail_3m_midnight_dialed_time", duration.getJSONObject("item").getString("dialed_time"));
                                model.put("call_duration_detail_3m_midnight_total_time", duration.getJSONObject("item").getString("total_time"));
                                model.put("call_duration_detail_3m_midnight_dial_time", duration.getJSONObject("item").getString("dial_time"));
                                model.put("call_duration_detail_3m_midnight_total_cnt", duration.getJSONObject("item").getString("total_cnt"));
                                model.put("call_duration_detail_3m_midnight_dial_cnt", duration.getJSONObject("item").getString("dial_cnt"));
                                model.put("call_duration_detail_3m_midnight_dialed_cnt", duration.getJSONObject("item").getString("dialed_cnt"));
                                model.put("call_duration_detail_3m_midnight_uniq_num_cnt", duration.getJSONObject("item").getString("uniq_num_cnt"));
                            }
                        }
                    }
                }
                for (int i = 0; i < call_risk_analysis.size(); i++) {
                    JSONObject call_risk = call_risk_analysis.getJSONObject(i);
                    JSONObject analysis_point = call_risk.getJSONObject("analysis_point");
                    JSONObject call_analysis_dialed_point = analysis_point.getJSONObject("call_analysis_dialed_point");
                    JSONObject call_analysis_dial_point = analysis_point.getJSONObject("call_analysis_dial_point");
                    if ("nuisance".equals(call_risk.getString("analysis_item"))) {
                        model.put("nuisance_call_time_1m", analysis_point.getString("call_time_1m"));
                        model.put("nuisance_call_time_3m", analysis_point.getString("call_time_3m"));
                        model.put("nuisance_call_time_6m", analysis_point.getString("call_time_6m"));
                        model.put("nuisance_call_cnt_1m", analysis_point.getString("call_cnt_1m"));
                        model.put("nuisance_call_cnt_3m", analysis_point.getString("call_cnt_3m"));
                        model.put("nuisance_call_cnt_6m", analysis_point.getString("call_cnt_6m"));
                        model.put("nuisance_avg_call_time_3m", analysis_point.getString("avg_call_time_3m"));
                        model.put("nuisance_avg_call_time_6m", analysis_point.getString("avg_call_time_6m"));
                        model.put("nuisance_avg_call_cnt_3m", analysis_point.getString("avg_call_cnt_3m"));

                        model.put("nuisance_call_dial_cnt_1m", call_analysis_dial_point.getString("call_dial_cnt_1m"));
                        model.put("nuisance_call_dial_cnt_3m", call_analysis_dial_point.getString("call_dial_cnt_3m"));
                        model.put("nuisance_call_dial_cnt_6m", call_analysis_dial_point.getString("call_dial_cnt_6m"));
                        model.put("nuisance_call_dial_time_1m", call_analysis_dial_point.getString("call_dial_time_1m"));
                        model.put("nuisance_call_dial_time_3m", call_analysis_dial_point.getString("call_dial_time_3m"));
                        model.put("nuisance_call_dial_time_6m", call_analysis_dial_point.getString("call_dial_time_6m"));
                        model.put("nuisance_avg_call_dial_time_6m", call_analysis_dial_point.getString("avg_call_dial_time_6m"));

                        model.put("nuisance_call_dialed_cnt_1m", call_analysis_dialed_point.getString("call_dialed_cnt_1m"));
                        model.put("nuisance_call_dialed_cnt_3m", call_analysis_dialed_point.getString("call_dialed_cnt_3m"));
                        model.put("nuisance_call_dialed_cnt_6m", call_analysis_dialed_point.getString("call_dialed_cnt_6m"));
                        model.put("nuisance_call_dialed_time_1m", call_analysis_dialed_point.getString("call_dialed_time_1m"));
                        model.put("nuisance_call_dialed_time_3m", call_analysis_dialed_point.getString("call_dialed_time_3m"));
                        model.put("nuisance_call_dialed_time_6m", call_analysis_dialed_point.getString("call_dialed_time_6m"));
                        model.put("nuisance_avg_call_dialed_cnt_3m", call_analysis_dialed_point.getString("avg_call_dialed_cnt_3m"));
                        model.put("nuisance_avg_call_dialed_time_3m", call_analysis_dialed_point.getString("avg_call_dialed_time_3m"));
                        model.put("nuisance_avg_call_dialed_time_6m", call_analysis_dialed_point.getString("avg_call_dialed_time_6m"));
                    } else if ("credit_card".equals(call_risk.getString("analysis_item"))) {
                        model.put("credit_card_call_cnt_1m", analysis_point.getString("call_cnt_1m"));
                        model.put("credit_card_call_cnt_3m", analysis_point.getString("call_cnt_3m"));
                        model.put("credit_card_call_cnt_6m", analysis_point.getString("call_cnt_6m"));
                        model.put("credit_card_call_time_1m", analysis_point.getString("call_time_1m"));
                        model.put("credit_card_call_time_3m", analysis_point.getString("call_time_3m"));
                        model.put("credit_card_call_time_6m", analysis_point.getString("call_time_6m"));
                        model.put("credit_card_avg_call_cnt_3m", analysis_point.getString("avg_call_cnt_3m"));
                        model.put("credit_card_avg_call_time_3m", analysis_point.getString("avg_call_time_3m"));
                        model.put("credit_card_avg_call_time_6m", analysis_point.getString("avg_call_time_6m"));

                        model.put("credit_card_avg_call_dial_time_3m", call_analysis_dial_point.getString("avg_call_dial_time_3m"));
                        model.put("credit_card_avg_call_dial_time_6m", call_analysis_dial_point.getString("avg_call_dial_time_6m"));
                        model.put("credit_card_call_dial_time_1m", call_analysis_dial_point.getString("call_dial_time_1m"));
                        model.put("credit_card_call_dial_time_3m", call_analysis_dial_point.getString("call_dial_time_3m"));
                        model.put("credit_card_call_dial_time_6m", call_analysis_dial_point.getString("call_dial_time_6m"));
                        model.put("credit_card_call_dial_cnt_1m", call_analysis_dial_point.getString("call_dial_cnt_1m"));
                        model.put("credit_card_call_dial_cnt_3m", call_analysis_dial_point.getString("call_dial_cnt_3m"));
                        model.put("credit_card_call_dial_cnt_6m", call_analysis_dial_point.getString("call_dial_cnt_6m"));

                        model.put("credit_card_call_dialed_time_1m", call_analysis_dialed_point.getString("call_dialed_time_1m"));
                        model.put("credit_card_call_dialed_time_3m", call_analysis_dialed_point.getString("call_dialed_time_3m"));
                        model.put("credit_card_call_dialed_time_6m", call_analysis_dialed_point.getString("call_dialed_time_6m"));
                        model.put("credit_card_call_dialed_cnt_1m", call_analysis_dialed_point.getString("call_dialed_cnt_1m"));
                        model.put("credit_card_call_dialed_cnt_3m", call_analysis_dialed_point.getString("call_dialed_cnt_3m"));
                        model.put("credit_card_call_dialed_cnt_6m", call_analysis_dialed_point.getString("call_dialed_cnt_6m"));
                        model.put("credit_card_avg_call_dialed_time_3m", call_analysis_dialed_point.getString("avg_call_dialed_time_3m"));
                        model.put("credit_card_avg_call_dialed_time_6m", call_analysis_dialed_point.getString("avg_call_dialed_time_6m"));
                    } else if ("loan".equals(call_risk.getString("analysis_item"))) {
                        model.put("loan_call_time_1m", analysis_point.getString("call_time_1m"));
                        model.put("loan_call_time_3m", analysis_point.getString("call_time_3m"));
                        model.put("loan_call_time_6m", analysis_point.getString("call_time_6m"));
                        model.put("loan_call_cnt_1m", analysis_point.getString("call_cnt_1m"));
                        model.put("loan_call_cnt_3m", analysis_point.getString("call_cnt_3m"));
                        model.put("loan_call_cnt_6m", analysis_point.getString("call_cnt_6m"));
                        model.put("loan_avg_call_time_3m", analysis_point.getString("avg_call_time_3m"));
                        model.put("loan_avg_call_time_6m", analysis_point.getString("avg_call_time_6m"));
                        model.put("loan_avg_call_cnt_3m", analysis_point.getString("avg_call_cnt_3m"));

                        model.put("loan_call_dial_cnt_1m", call_analysis_dial_point.getString("call_dial_cnt_1m"));
                        model.put("loan_call_dial_cnt_3m", call_analysis_dial_point.getString("call_dial_cnt_3m"));
                        model.put("loan_call_dial_cnt_6m", call_analysis_dial_point.getString("call_dial_cnt_6m"));
                        model.put("loan_call_dial_time_1m", call_analysis_dial_point.getString("call_dial_time_1m"));
                        model.put("loan_call_dial_time_3m", call_analysis_dial_point.getString("call_dial_time_3m"));
                        model.put("loan_call_dial_time_6m", call_analysis_dial_point.getString("call_dial_time_6m"));
                        model.put("loan_avg_call_dial_time_3m", call_analysis_dial_point.getString("avg_call_dial_time_3m"));
                        model.put("loan_avg_call_dial_time_6m", call_analysis_dial_point.getString("avg_call_dial_time_6m"));

                        model.put("loan_call_dialed_time_1m", call_analysis_dialed_point.getString("call_dialed_time_1m"));
                        model.put("loan_call_dialed_time_3m", call_analysis_dialed_point.getString("call_dialed_time_3m"));
                        model.put("loan_call_dialed_time_6m", call_analysis_dialed_point.getString("call_dialed_time_6m"));
                        model.put("loan_call_dialed_cnt_1m", call_analysis_dialed_point.getString("call_dialed_cnt_1m"));
                        model.put("loan_call_dialed_cnt_3m", call_analysis_dialed_point.getString("call_dialed_cnt_3m"));
                        model.put("loan_call_dialed_cnt_6m", call_analysis_dialed_point.getString("call_dialed_cnt_6m"));
                        model.put("loan_avg_call_dialed_time_3m", call_analysis_dialed_point.getString("avg_call_dialed_time_3m"));
                        model.put("loan_avg_call_dialed_time_6m", call_analysis_dialed_point.getString("avg_call_dialed_time_6m"));
                    } else if ("agency".equals(call_risk.getString("analysis_item"))) {
                        model.put("agency_call_time_1m", analysis_point.getString("call_time_1m"));
                        model.put("agency_call_time_3m", analysis_point.getString("call_time_3m"));
                        model.put("agency_call_time_6m", analysis_point.getString("call_time_6m"));
                        model.put("agency_call_cnt_1m", analysis_point.getString("call_cnt_1m"));
                        model.put("agency_call_cnt_3m", analysis_point.getString("call_cnt_3m"));
                        model.put("agency_call_cnt_6m", analysis_point.getString("call_cnt_6m"));
                        model.put("agency_avg_call_time_3m", analysis_point.getString("avg_call_time_3m"));
                        model.put("agency_avg_call_time_6m", analysis_point.getString("avg_call_time_6m"));

                        model.put("agency_call_dial_cnt_6m", call_analysis_dial_point.getString("call_dial_cnt_6m"));
                        model.put("agency_call_dial_time_1m", call_analysis_dial_point.getString("call_dial_time_1m"));
                        model.put("agency_call_dial_time_3m", call_analysis_dial_point.getString("call_dial_time_3m"));
                        model.put("agency_call_dial_time_6m", call_analysis_dial_point.getString("call_dial_time_6m"));
                        model.put("agency_avg_call_dial_time_3m", call_analysis_dial_point.getString("avg_call_dial_time_3m"));

                        model.put("agency_call_dialed_time_1m", call_analysis_dialed_point.getString("call_dialed_time_1m"));
                        model.put("agency_call_dialed_time_3m", call_analysis_dialed_point.getString("call_dialed_time_3m"));
                        model.put("agency_call_dialed_time_6m", call_analysis_dialed_point.getString("call_dialed_time_6m"));
                        model.put("agency_call_dialed_cnt_1m", call_analysis_dialed_point.getString("call_dialed_cnt_1m"));
                        model.put("agency_call_dialed_cnt_3m", call_analysis_dialed_point.getString("call_dialed_cnt_3m"));
                        model.put("agency_call_dialed_cnt_6m", call_analysis_dialed_point.getString("call_dialed_cnt_6m"));
                        model.put("agency_avg_call_dialed_time_3m", call_analysis_dialed_point.getString("avg_call_dialed_time_3m"));
                        model.put("agency_avg_call_dialed_time_6m", call_analysis_dialed_point.getString("avg_call_dialed_time_6m"));
                    } else if ("110".equals(call_risk.getString("analysis_item"))) {
                        model.put("110_call_cnt_3m", analysis_point.getString("call_cnt_3m"));
                        model.put("110_call_time_3m", analysis_point.getString("call_time_3m"));
                        model.put("110_call_time_6m", analysis_point.getString("call_time_6m"));
                    } else if ("120".equals(call_risk.getString("analysis_item"))) {
                        model.put("120_call_time_6m", analysis_point.getString("call_time_6m"));
                    } else if ("fraud".equals(call_risk.getString("analysis_item"))) {
                        model.put("fraud_call_time_1m", analysis_point.getString("call_time_1m"));
                        model.put("fraud_call_time_6m", analysis_point.getString("call_time_6m"));
                        model.put("fraud_call_dialed_time_6m", call_analysis_dialed_point.getString("call_dialed_time_6m"));
                    } else if ("lawyer".equals(call_risk.getString("analysis_item"))) {
                        model.put("lawyer_call_dialed_time_6m", call_analysis_dialed_point.getString("call_dialed_time_6m"));
                    }
                }
                for (int i = 0; i < call_service_analysiss.size(); i++) {
                    JSONObject call_service_analysis = call_service_analysiss.getJSONObject(i);
                    JSONObject analysis_point = call_service_analysis.getJSONObject("analysis_point");
                    JSONObject call_analysis_dialed_point = analysis_point.getJSONObject("call_analysis_dialed_point");
                    JSONObject call_analysis_dial_point = analysis_point.getJSONObject("call_analysis_dial_point");
                    if ("express".equals(call_service_analysis.getString("analysis_item"))) {
                        model.put("express_call_time_1m", analysis_point.getString("call_time_1m"));
                        model.put("express_call_time_3m", analysis_point.getString("call_time_3m"));
                        model.put("express_call_time_6m", analysis_point.getString("call_time_6m"));
                        model.put("express_call_cnt_1m", analysis_point.getString("call_cnt_1m"));
                        model.put("express_call_cnt_3m", analysis_point.getString("call_cnt_3m"));
                        model.put("express_call_cnt_6m", analysis_point.getString("call_cnt_6m"));
                        model.put("express_avg_call_time_3m", analysis_point.getString("avg_call_time_3m"));
                        model.put("express_avg_call_time_6m", analysis_point.getString("avg_call_time_6m"));

                        model.put("express_call_dial_cnt_1m", call_analysis_dial_point.getString("call_dial_cnt_1m"));
                        model.put("express_call_dial_cnt_3m", call_analysis_dial_point.getString("call_dial_cnt_3m"));
                        model.put("express_call_dial_cnt_6m", call_analysis_dial_point.getString("call_dial_cnt_6m"));
                        model.put("express_call_dial_time_1m", call_analysis_dial_point.getString("call_dial_time_1m"));
                        model.put("express_call_dial_time_3m", call_analysis_dial_point.getString("call_dial_time_3m"));
                        model.put("express_call_dial_time_6m", call_analysis_dial_point.getString("call_dial_time_6m"));
                        model.put("express_avg_call_dial_time_6m", call_analysis_dial_point.getString("avg_call_dial_time_6m"));

                        model.put("express_call_dialed_cnt_1m", call_analysis_dialed_point.getString("call_dialed_cnt_1m"));
                        model.put("express_call_dialed_cnt_3m", call_analysis_dialed_point.getString("call_dialed_cnt_3m"));
                        model.put("express_call_dialed_cnt_6m", call_analysis_dialed_point.getString("call_dialed_cnt_6m"));
                        model.put("express_call_dialed_time_1m", call_analysis_dialed_point.getString("call_dialed_time_1m"));
                        model.put("express_call_dialed_time_3m", call_analysis_dialed_point.getString("call_dialed_time_3m"));
                        model.put("express_call_dialed_time_6m", call_analysis_dialed_point.getString("call_dialed_time_6m"));
                        model.put("express_avg_call_dialed_cnt_6m", call_analysis_dialed_point.getString("avg_call_dialed_cnt_6m"));
                        model.put("express_avg_call_dialed_time_3m", call_analysis_dialed_point.getString("avg_call_dialed_time_3m"));
                        model.put("express_avg_call_dialed_time_6m", call_analysis_dialed_point.getString("avg_call_dialed_time_6m"));

                    } else if ("bank".equals(call_service_analysis.getString("analysis_item"))) {
                        model.put("bank_call_time_1m", analysis_point.getString("call_time_1m"));
                        model.put("bank_call_time_3m", analysis_point.getString("call_time_3m"));
                        model.put("bank_call_time_6m", analysis_point.getString("call_time_6m"));
                        model.put("bank_call_cnt_1m", analysis_point.getString("call_cnt_1m"));
                        model.put("bank_call_cnt_3m", analysis_point.getString("call_cnt_3m"));
                        model.put("bank_call_cnt_6m", analysis_point.getString("call_cnt_6m"));
                        model.put("bank_avg_call_time_3m", analysis_point.getString("avg_call_time_3m"));
                        model.put("bank_avg_call_time_6m", analysis_point.getString("avg_call_time_6m"));


                        model.put("bank_call_dial_cnt_1m", call_analysis_dial_point.getString("call_dial_cnt_1m"));
                        model.put("bank_call_dial_cnt_3m", call_analysis_dial_point.getString("call_dial_cnt_3m"));
                        model.put("bank_call_dial_cnt_6m", call_analysis_dial_point.getString("call_dial_cnt_6m"));
                        model.put("bank_call_dial_time_1m", call_analysis_dial_point.getString("call_dial_time_1m"));
                        model.put("bank_call_dial_time_3m", call_analysis_dial_point.getString("call_dial_time_3m"));
                        model.put("bank_call_dial_time_6m", call_analysis_dial_point.getString("call_dial_time_6m"));
                        model.put("bank_avg_call_dial_time_3m", call_analysis_dial_point.getString("avg_call_dial_time_3m"));
                        model.put("bank_avg_call_dial_time_6m", call_analysis_dial_point.getString("avg_call_dial_time_6m"));

                        model.put("bank_call_dialed_time_1m", call_analysis_dialed_point.getString("call_dialed_time_1m"));
                        model.put("bank_call_dialed_time_3m", call_analysis_dialed_point.getString("call_dialed_time_3m"));
                        model.put("bank_call_dialed_time_6m", call_analysis_dialed_point.getString("call_dialed_time_6m"));
                        model.put("bank_call_dialed_cnt_1m", call_analysis_dialed_point.getString("call_dialed_cnt_1m"));
                        model.put("bank_call_dialed_cnt_3m", call_analysis_dialed_point.getString("call_dialed_cnt_3m"));
                        model.put("bank_call_dialed_cnt_6m", call_analysis_dialed_point.getString("call_dialed_cnt_6m"));
                        model.put("bank_avg_call_dialed_time_3m", call_analysis_dialed_point.getString("avg_call_dialed_time_3m"));
                        model.put("bank_avg_call_dialed_time_6m", call_analysis_dialed_point.getString("avg_call_dialed_time_6m"));
                    } else if ("ins_fin".equals(call_service_analysis.getString("analysis_item"))) {
                        model.put("ins_fin_avg_call_time_3m", analysis_point.getString("avg_call_time_3m"));
                        model.put("ins_fin_avg_call_time_6m", analysis_point.getString("avg_call_time_6m"));
                        model.put("ins_fin_call_time_1m", analysis_point.getString("call_time_1m"));
                        model.put("ins_fin_call_time_3m", analysis_point.getString("call_time_3m"));
                        model.put("ins_fin_call_time_6m", analysis_point.getString("call_time_6m"));
                        model.put("ins_fin_call_cnt_1m", analysis_point.getString("call_cnt_1m"));
                        model.put("ins_fin_call_cnt_3m", analysis_point.getString("call_cnt_3m"));
                        model.put("ins_fin_call_cnt_6m", analysis_point.getString("call_cnt_6m"));

                        model.put("ins_fin_call_dial_time_1m", call_analysis_dial_point.getString("call_dial_time_1m"));
                        model.put("ins_fin_call_dial_time_3m", call_analysis_dial_point.getString("call_dial_time_3m"));
                        model.put("ins_fin_call_dial_time_6m", call_analysis_dial_point.getString("call_dial_time_6m"));
                        model.put("ins_fin_call_dial_cnt_3m", call_analysis_dial_point.getString("call_dial_cnt_3m"));
                        model.put("ins_fin_call_dial_cnt_6m", call_analysis_dial_point.getString("call_dial_cnt_6m"));

                        model.put("ins_fin_call_dialed_time_1m", call_analysis_dialed_point.getString("call_dialed_time_1m"));
                        model.put("ins_fin_call_dialed_time_3m", call_analysis_dialed_point.getString("call_dialed_time_3m"));
                        model.put("ins_fin_call_dialed_time_6m", call_analysis_dialed_point.getString("call_dialed_time_6m"));
                        model.put("ins_fin_call_dialed_cnt_1m", call_analysis_dialed_point.getString("call_dialed_cnt_1m"));
                        model.put("ins_fin_call_dialed_cnt_3m", call_analysis_dialed_point.getString("call_dialed_cnt_3m"));
                        model.put("ins_fin_call_dialed_cnt_6m", call_analysis_dialed_point.getString("call_dialed_cnt_6m"));
                        model.put("ins_fin_avg_call_dialed_time_3m", call_analysis_dialed_point.getString("avg_call_dialed_time_3m"));
                        model.put("ins_fin_avg_call_dialed_time_6m", call_analysis_dialed_point.getString("avg_call_dialed_time_6m"));
                    } else if ("carrier".equals(call_service_analysis.getString("analysis_item"))) {
                        model.put("carrier_call_cnt_1m", analysis_point.getString("call_cnt_1m"));
                        model.put("carrier_call_cnt_3m", analysis_point.getString("call_cnt_3m"));
                        model.put("carrier_call_cnt_6m", analysis_point.getString("call_cnt_6m"));
                        model.put("carrier_call_time_1m", analysis_point.getString("call_time_1m"));
                        model.put("carrier_call_time_3m", analysis_point.getString("call_time_3m"));
                        model.put("carrier_call_time_6m", analysis_point.getString("call_time_6m"));
                        model.put("carrier_avg_call_time_3m", analysis_point.getString("avg_call_time_3m"));
                        model.put("carrier_avg_call_time_6m", analysis_point.getString("avg_call_time_6m"));

                        model.put("carrier_call_dial_time_6m", call_analysis_dial_point.getString("call_dial_time_6m"));

                        model.put("carrier_call_dialed_time_1m", call_analysis_dialed_point.getString("call_dialed_time_1m"));
                        model.put("carrier_call_dialed_time_3m", call_analysis_dialed_point.getString("call_dialed_time_3m"));
                        model.put("carrier_call_dialed_time_6m", call_analysis_dialed_point.getString("call_dialed_time_6m"));
                        model.put("carrier_call_dialed_cnt_3m", call_analysis_dialed_point.getString("call_dialed_cnt_3m"));
                        model.put("carrier_call_dialed_cnt_6m", call_analysis_dialed_point.getString("call_dialed_cnt_6m"));
                        model.put("carrier_avg_call_dialed_time_3m", call_analysis_dialed_point.getString("avg_call_dialed_time_3m"));
                        model.put("carrier_avg_call_dialed_time_6m", call_analysis_dialed_point.getString("avg_call_dialed_time_6m"));
                    } else if ("car_firm".equals(call_service_analysis.getString("analysis_item"))) {
                        model.put("car_firm_call_cnt_1m", analysis_point.getString("call_cnt_1m"));
                        model.put("car_firm_call_cnt_3m", analysis_point.getString("call_cnt_3m"));
                        model.put("car_firm_call_cnt_6m", analysis_point.getString("call_cnt_6m"));
                        model.put("car_firm_call_time_1m", analysis_point.getString("call_time_1m"));
                        model.put("car_firm_call_time_3m", analysis_point.getString("call_time_3m"));
                        model.put("car_firm_call_time_6m", analysis_point.getString("call_time_6m"));

                        model.put("car_firm_call_dial_time_3m", call_analysis_dial_point.getString("call_dial_time_3m"));
                        model.put("car_firm_call_dial_time_6m", call_analysis_dial_point.getString("call_dial_time_6m"));
                        model.put("car_firm_call_dial_cnt_3m", call_analysis_dial_point.getString("call_dial_cnt_3m"));
                        model.put("car_firm_call_dial_cnt_6m", call_analysis_dial_point.getString("call_dial_cnt_6m"));
                        model.put("car_firm_avg_call_dial_time_6m", call_analysis_dial_point.getString("avg_call_dial_time_6m"));

                        model.put("car_firm_call_dialed_cnt_6m", call_analysis_dialed_point.getString("call_dialed_cnt_6m"));
                        model.put("car_firm_call_dialed_time_3m", call_analysis_dialed_point.getString("call_dialed_time_3m"));
                        model.put("car_firm_call_dialed_time_6m", call_analysis_dialed_point.getString("call_dialed_time_6m"));
                    } else if ("railway_airway".equals(call_service_analysis.getString("analysis_item"))) {
                        model.put("railway_airway_call_time_6m", analysis_point.getString("call_time_6m"));
                    } else if ("special_service".equals(call_service_analysis.getString("analysis_item"))) {
                        model.put("special_service_call_time_1m", analysis_point.getString("call_time_1m"));
                        model.put("special_service_call_time_3m", analysis_point.getString("call_time_3m"));
                        model.put("special_service_call_time_6m", analysis_point.getString("call_time_6m"));

                    }
                }
                for (int i = 0; i < active_degree.size(); i++) {
                    JSONObject active = active_degree.getJSONObject(i);
                    if ("peer_loc_cnt".equals(active.getString("app_point"))) {
                        model.put("peer_loc_cnt_item_1m", active.getJSONObject("item").getString("item_1m"));
                        model.put("peer_loc_cnt_item_3m", active.getJSONObject("item").getString("item_3m"));
                        model.put("peer_loc_cnt_item_6m", active.getJSONObject("item").getString("item_6m"));
                        model.put("peer_loc_cnt_avg_item_3m", active.getJSONObject("item").getString("avg_item_3m"));
                        model.put("peer_loc_cnt_avg_item_6m", active.getJSONObject("item").getString("avg_item_6m"));
                    } else if ("peer_num_cnt".equals(active.getString("app_point"))) {
                        model.put("peer_num_cnt_item_1m", active.getJSONObject("item").getString("item_1m"));
                        model.put("peer_num_cnt_item_3m", active.getJSONObject("item").getString("item_3m"));
                        model.put("peer_num_cnt_item_6m", active.getJSONObject("item").getString("item_6m"));
                        model.put("peer_num_cnt_avg_item_3m", active.getJSONObject("item").getString("avg_item_3m"));
                        model.put("peer_num_cnt_avg_item_6m", active.getJSONObject("item").getString("avg_item_6m"));
                    } else if ("net_used".equals(active.getString("app_point"))) {
                        model.put("net_used_item_1m", active.getJSONObject("item").getString("item_1m"));
                        model.put("net_used_item_3m", active.getJSONObject("item").getString("item_3m"));
                        model.put("net_used_item_6m", active.getJSONObject("item").getString("item_6m"));
                        model.put("net_used_avg_item_3m", active.getJSONObject("item").getString("avg_item_3m"));
                        model.put("net_used_avg_item_6m", active.getJSONObject("item").getString("avg_item_6m"));
                    } else if ("sms_cnt".equals(active.getString("app_point"))) {
                        model.put("sms_cnt_avg_item_3m", active.getJSONObject("item").getString("avg_item_3m"));
                        model.put("sms_cnt_avg_item_6m", active.getJSONObject("item").getString("avg_item_6m"));
                        model.put("sms_cnt_item_1m", active.getJSONObject("item").getString("item_1m"));
                        model.put("sms_cnt_item_3m", active.getJSONObject("item").getString("item_3m"));
                        model.put("sms_cnt_item_6m", active.getJSONObject("item").getString("item_6m"));
                    } else if ("no_dial_day".equals(active.getString("app_point"))) {
                        model.put("no_dial_day_avg_item_3m", active.getJSONObject("item").getString("avg_item_3m"));
                        model.put("no_dial_day_avg_item_6m", active.getJSONObject("item").getString("avg_item_6m"));
                        model.put("no_dial_day_item_1m", active.getJSONObject("item").getString("item_1m"));
                        model.put("no_dial_day_item_3m", active.getJSONObject("item").getString("item_3m"));
                        model.put("no_dial_day_item_6m", active.getJSONObject("item").getString("item_6m"));
                    } else if ("no_call_day".equals(active.getString("app_point"))) {
                        model.put("no_call_day_avg_item_3m", active.getJSONObject("item").getString("avg_item_3m"));
                        model.put("no_call_day_avg_item_6m", active.getJSONObject("item").getString("avg_item_6m"));
                        model.put("no_call_day_item_1m", active.getJSONObject("item").getString("item_1m"));
                        model.put("no_call_day_item_3m", active.getJSONObject("item").getString("item_3m"));
                        model.put("no_call_day_item_6m", active.getJSONObject("item").getString("item_6m"));
                    } else if ("call_time".equals(active.getString("app_point"))) {
                        model.put("call_time_avg_item_3m", active.getJSONObject("item").getString("avg_item_3m"));
                        model.put("call_time_avg_item_6m", active.getJSONObject("item").getString("avg_item_6m"));
                        model.put("call_time_item_1m", active.getJSONObject("item").getString("item_1m"));
                        model.put("call_time_item_3m", active.getJSONObject("item").getString("item_3m"));
                        model.put("call_time_item_6m", active.getJSONObject("item").getString("item_6m"));
                    } else if ("dial_time".equals(active.getString("app_point"))) {
                        model.put("dial_time_avg_item_3m", active.getJSONObject("item").getString("avg_item_3m"));
                        model.put("dial_time_avg_item_6m", active.getJSONObject("item").getString("avg_item_6m"));
                        model.put("dial_time_item_1m", active.getJSONObject("item").getString("item_1m"));
                        model.put("dial_time_item_3m", active.getJSONObject("item").getString("item_3m"));
                        model.put("dial_time_item_6m", active.getJSONObject("item").getString("item_6m"));
                    } else if ("call_cnt".equals(active.getString("app_point"))) {
                        model.put("call_cnt_avg_item_3m", active.getJSONObject("item").getString("avg_item_3m"));
                        model.put("call_cnt_avg_item_6m", active.getJSONObject("item").getString("avg_item_6m"));
                        model.put("call_cnt_item_1m", active.getJSONObject("item").getString("item_1m"));
                        model.put("call_cnt_item_3m", active.getJSONObject("item").getString("item_3m"));
                        model.put("call_cnt_item_6m", active.getJSONObject("item").getString("item_6m"));
                    } else if ("power_off_day".equals(active.getString("app_point"))) {
                        model.put("power_off_day_item_1m", active.getJSONObject("item").getString("item_1m"));
                        model.put("power_off_day_item_3m", active.getJSONObject("item").getString("item_3m"));
                        model.put("power_off_day_item_6m", active.getJSONObject("item").getString("item_6m"));
                        model.put("power_off_day_avg_item_3m", active.getJSONObject("item").getString("avg_item_3m"));
                        model.put("power_off_day_avg_item_6m", active.getJSONObject("item").getString("avg_item_6m"));
                    } else if ("dial_cnt".equals(active.getString("app_point"))) {
                        model.put("dial_cnt_avg_item_3m", active.getJSONObject("item").getString("avg_item_3m"));
                        model.put("dial_cnt_avg_item_6m", active.getJSONObject("item").getString("avg_item_6m"));
                        model.put("dial_cnt_item_1m", active.getJSONObject("item").getString("item_1m"));
                        model.put("dial_cnt_item_3m", active.getJSONObject("item").getString("item_3m"));
                        model.put("dial_cnt_item_6m", active.getJSONObject("item").getString("item_6m"));
                    } else if ("max_power_on_day".equals(active.getString("app_point"))) {
                        model.put("max_power_on_day_item_1m", active.getJSONObject("item").getString("item_1m"));
                        model.put("max_power_on_day_item_3m", active.getJSONObject("item").getString("item_3m"));
                        model.put("max_power_on_day_item_6m", active.getJSONObject("item").getString("item_6m"));
                    } else if ("continue_power_off_cnt".equals(active.getString("app_point"))) {
                        model.put("continue_power_off_cnt_item_3m", active.getJSONObject("item").getString("item_3m"));
                        model.put("continue_power_off_cnt_item_6m", active.getJSONObject("item").getString("item_6m"));
                    } else if ("call_day".equals(active.getString("app_point"))) {
                        model.put("call_day_item_1m", active.getJSONObject("item").getString("item_1m"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    public static void main(String[] args) throws IOException {
        FileInputStream is = new FileInputStream(new File("/Users/a51/Desktop/运营商报告CASE.txt"));
        String data = IOUtils.toString(is, "UTF-8");
        long start = System.currentTimeMillis();
        JSONObject reportData = CarrierMxUtils.parse(data);
        long end = System.currentTimeMillis();
        System.out.println("parse result: " + reportData + "\nparse time: " + (end - start) + "ms");
    }


}
