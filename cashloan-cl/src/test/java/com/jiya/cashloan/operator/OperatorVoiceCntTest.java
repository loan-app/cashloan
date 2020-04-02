package com.jiya.cashloan.operator;

import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.cl.domain.OperatorVoiceCnt;
import com.xiji.cashloan.cl.domain.operator.OperatorVoiceCntMeta;
import com.xiji.cashloan.cl.util.MobileUtil;
import com.xiji.cashloan.cl.util.black.CollectionUtil;
import com.xiji.cashloan.core.common.util.ShardTableUtil;
import com.xiji.cashloan.core.common.util.StringUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 * @Auther: king
 * @Date: 2018/12/17 13:02
 * @Description:
 */
public class OperatorVoiceCntTest {

    public static void main(String[] args) {
        String res = "{\"report\":[{\"key\":\"data_type\",\"value\":\"运营商\"},{\"key\":\"source_name\",\"value\":\"chinamobilejx\"},{\"key\":\"source_name_zh\",\"value\":\"江西移动\"},{\"key\":\"data_gain_time\",\"value\":\"2018-12-13\"},{\"key\":\"task_id\",\"value\":\"ee1c4e80-fe88-11e8-9f12-00163e0f4b67\"},{\"key\":\"update_time\",\"value\":\"2018-12-13 11:41:09\"},{\"key\":\"version\",\"value\":\"1.0\"}],\"call_contact_detail\":[{\"city\":\"北京\",\"p_relation\":\"\",\"peer_num\":\"15801640229\",\"group_name\":\"未知\",\"company_name\":\"未知\",\"call_cnt_1w\":1,\"call_cnt_1m\":3,\"call_cnt_3m\":9,\"call_cnt_6m\":15,\"call_time_3m\":625,\"call_time_6m\":772,\"dial_cnt_3m\":4,\"dial_cnt_6m\":4,\"dial_time_3m\":152,\"dial_time_6m\":152,\"dialed_cnt_3m\":5,\"dialed_cnt_6m\":11,\"dialed_time_3m\":473,\"dialed_time_6m\":620,\"call_cnt_morning_3m\":3,\"call_cnt_morning_6m\":5,\"call_cnt_noon_3m\":0,\"call_cnt_noon_6m\":0,\"call_cnt_afternoon_3m\":3,\"call_cnt_afternoon_6m\":3,\"call_cnt_evening_3m\":3,\"call_cnt_evening_6m\":7,\"call_cnt_night_3m\":0,\"call_cnt_night_6m\":0,\"call_cnt_weekday_3m\":1,\"call_cnt_weekday_6m\":1,\"call_cnt_weekend_3m\":3,\"call_cnt_weekend_6m\":9,\"call_cnt_holiday_3m\":5,\"call_cnt_holiday_6m\":5,\"call_if_whole_day_3m\":false,\"call_if_whole_day_6m\":false,\"trans_start\":\"2018-07-14 22:11:47\",\"trans_end\":\"2018-12-09 18:19:42\"},{\"city\":\"南昌\",\"p_relation\":\"\",\"peer_num\":\"079110086\",\"group_name\":\"通信服务机构\",\"company_name\":\"中国移动客服热线\",\"call_cnt_1w\":0,\"call_cnt_1m\":0,\"call_cnt_3m\":0,\"call_cnt_6m\":4,\"call_time_3m\":0,\"call_time_6m\":302,\"dial_cnt_3m\":0,\"dial_cnt_6m\":4,\"dial_time_3m\":0,\"dial_time_6m\":302,\"dialed_cnt_3m\":0,\"dialed_cnt_6m\":0,\"dialed_time_3m\":0,\"dialed_time_6m\":0,\"call_cnt_morning_3m\":0,\"call_cnt_morning_6m\":1,\"call_cnt_noon_3m\":0,\"call_cnt_noon_6m\":0,\"call_cnt_afternoon_3m\":0,\"call_cnt_afternoon_6m\":2,\"call_cnt_evening_3m\":0,\"call_cnt_evening_6m\":1,\"call_cnt_night_3m\":0,\"call_cnt_night_6m\":0,\"call_cnt_weekday_3m\":0,\"call_cnt_weekday_6m\":3,\"call_cnt_weekend_3m\":0,\"call_cnt_weekend_6m\":1,\"call_cnt_holiday_3m\":0,\"call_cnt_holiday_6m\":0,\"call_if_whole_day_3m\":false,\"call_if_whole_day_6m\":false,\"trans_start\":\"2018-08-12 10:13:17\",\"trans_end\":\"2018-08-17 14:17:42\"},{\"city\":\"赣州\",\"p_relation\":\"\",\"peer_num\":\"15297848591\",\"group_name\":\"未知\",\"company_name\":\"未知\",\"call_cnt_1w\":0,\"call_cnt_1m\":0,\"call_cnt_3m\":2,\"call_cnt_6m\":4,\"call_time_3m\":44,\"call_time_6m\":145,\"dial_cnt_3m\":0,\"dial_cnt_6m\":2,\"dial_time_3m\":0,\"dial_time_6m\":101,\"dialed_cnt_3m\":2,\"dialed_cnt_6m\":2,\"dialed_time_3m\":44,\"dialed_time_6m\":44,\"call_cnt_morning_3m\":0,\"call_cnt_morning_6m\":2,\"call_cnt_noon_3m\":1,\"call_cnt_noon_6m\":1,\"call_cnt_afternoon_3m\":0,\"call_cnt_afternoon_6m\":0,\"call_cnt_evening_3m\":0,\"call_cnt_evening_6m\":0,\"call_cnt_night_3m\":1,\"call_cnt_night_6m\":1,\"call_cnt_weekday_3m\":1,\"call_cnt_weekday_6m\":3,\"call_cnt_weekend_3m\":1,\"call_cnt_weekend_6m\":1,\"call_cnt_holiday_3m\":0,\"call_cnt_holiday_6m\":0,\"call_if_whole_day_3m\":false,\"call_if_whole_day_6m\":false,\"trans_start\":\"2018-07-17 07:57:33\",\"trans_end\":\"2018-10-16 00:33:10\"},{\"city\":\"朔州\",\"p_relation\":\"\",\"peer_num\":\"17803485213\",\"group_name\":\"未知\",\"company_name\":\"未知\",\"call_cnt_1w\":1,\"call_cnt_1m\":1,\"call_cnt_3m\":1,\"call_cnt_6m\":1,\"call_time_3m\":1,\"call_time_6m\":1,\"dial_cnt_3m\":0,\"dial_cnt_6m\":0,\"dial_time_3m\":0,\"dial_time_6m\":0,\"dialed_cnt_3m\":1,\"dialed_cnt_6m\":1,\"dialed_time_3m\":1,\"dialed_time_6m\":1,\"call_cnt_morning_3m\":0,\"call_cnt_morning_6m\":0,\"call_cnt_noon_3m\":0,\"call_cnt_noon_6m\":0,\"call_cnt_afternoon_3m\":0,\"call_cnt_afternoon_6m\":0,\"call_cnt_evening_3m\":1,\"call_cnt_evening_6m\":1,\"call_cnt_night_3m\":0,\"call_cnt_night_6m\":0,\"call_cnt_weekday_3m\":1,\"call_cnt_weekday_6m\":1,\"call_cnt_weekend_3m\":0,\"call_cnt_weekend_6m\":0,\"call_cnt_holiday_3m\":0,\"call_cnt_holiday_6m\":0,\"call_if_whole_day_3m\":false,\"call_if_whole_day_6m\":false,\"trans_start\":\"2018-12-07 20:18:19\",\"trans_end\":\"2018-12-07 20:18:19\"}]}";
        long userId = 40000;
        long reqLogId = 3;
        Date creatime = new Date();
        paserReportDetail(res, userId, creatime, reqLogId);
    }

    public static void paserReportDetail(String res, Long userId, Date createTime, Long reqLogId) {
        if (StringUtil.isNotBlank(res)) {
            JSONObject resJson = JSONObject.parseObject(res);
            String operatorVoices = String.valueOf(resJson.get("call_contact_detail"));
            if (StringUtil.isNotBlank(operatorVoices)) {
                List<OperatorVoiceCntMeta> cntMetas = JSONObject.parseArray(operatorVoices, OperatorVoiceCntMeta.class);
                if (CollectionUtil.isNotEmpty(cntMetas)) {
                    // 分表
                    String tableName = ShardTableUtil.generateTableNameById("cl_operator_voice_cnt", userId, 30000);
                    System.out.println(tableName);
//                    int countTable = operatorVoiceCntMapper.countTable(tableName);
//                    if (countTable == 0) {
//                        operatorVoiceCntMapper.createTable(tableName);
//                    }

                    String tableName1 = ShardTableUtil.generateTableNameById("cl_user_contacts", userId, 30000);
                    Map<String, Object> params = new HashMap<>();
                    params.put("userId", userId);
//                    List<UserContacts> contacts = userContactsMapper.listShardSelective1(tableName1, params);
                    HashMap<String, String> contactMap = new HashMap<>();
//                    for (UserContacts userCon : contacts) {
//                        if (userCon != null) {
//                            if (StringUtil.isNotEmpty(userCon.getPhone())) {
//                                contactMap.put(userCon.getPhone(),StringUtil.isEmpty(userCon.getName())?"":userCon.getName());
//                            }
//                        }
//                    }

                    for (OperatorVoiceCntMeta meta : cntMetas) {
                        OperatorVoiceCnt voiceCnt = new OperatorVoiceCnt();
                        voiceCnt.setCity(meta.getCity());
                        voiceCnt.setUserId(userId);
                        voiceCnt.setReqLogId(reqLogId);
                        voiceCnt.setPeerNum(meta.getPeerNum());
                        voiceCnt.setPeerName(MobileUtil.getMobileName(meta.getPeerNum()));
                        String contactName = StringUtils.trimToEmpty(contactMap.get(meta.getPeerNum()));
                        if (StringUtil.isNotEmpty(contactName)) {
                            voiceCnt.setContactPhone(meta.getPeerNum());
                            voiceCnt.setContactName(contactName);
                        }else {
                            voiceCnt.setContactPhone("无匹配");
                            voiceCnt.setContactName("无匹配");
                        }
                        voiceCnt.setCallCntNum(meta.getCallCnt6m()+"/"+meta.getCallTime6m()+"(秒)");
                        voiceCnt.setDialCntNum(meta.getDialCnt6m()+"/"+meta.getDialTime6m()+"(秒)");
                        voiceCnt.setDialedCntNum(meta.getDialedCnt6m()+"/"+meta.getDialedTime6m()+"(秒)");
                        voiceCnt.setCreatetime(createTime);
                    }
                }
            }
        }
    }
}
