package com.xiji.cashloan.cl.service.impl;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.cl.domain.BorrowOperatorLog;
import com.xiji.cashloan.cl.domain.UserContacts;
import com.xiji.cashloan.cl.domain.UserEmerContacts;
import com.xiji.cashloan.cl.mapper.*;
import com.xiji.cashloan.core.common.util.ShardTableUtil;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.mapper.UserBaseInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.cl.domain.OperatorReport;
import com.xiji.cashloan.cl.service.OperatorReportService;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 运营商报告ServiceImpl
 *
 * @author szb
 * @version 1.0.0
 * @date 2018-11-27 10:29:41
 */

@Service("operatorReportService")
public class OperatorReportServiceImpl extends BaseServiceImpl<OperatorReport, Long> implements OperatorReportService {

    private static final Logger logger = LoggerFactory.getLogger(OperatorReportServiceImpl.class);

    @Resource
    private OperatorReportMapper operatorReportMapper;
    @Resource
    private UserEmerContactsMapper userEmerContactsMapper;
    @Resource
    private UserBaseInfoMapper userBaseInfoMapper;
    @Resource
    private UserContactsMapper userContactsMapper;
    @Resource
    private ClBorrowMapper clBorrowMapper;
    @Resource
    private BorrowOperatorLogMapper borrowOperatorLogMapper;

    private static final String BEHAVIOR_CHECK_TYPES = "regular_circle|phone_used_time|phone_silent|phone_power_off|contact_each_other|contact_macao|contact_110|contact_120|contact_lawyer|contact_court|contact_loan|contact_bank|contact_credit_card|contact_collection|contact_night";

    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public BaseMapper<OperatorReport, Long> getMapper() {
        return operatorReportMapper;
    }

    @Override
    public OperatorReport getByTaskId(String taskId) {
        return operatorReportMapper.getByTaskId(taskId);
    }

    @Override
    public int updateSelective(Map<String, Object> params) {
        return operatorReportMapper.updateSelective(params);
    }

    @Override
    public JSONObject getReportByUserId(Long userId) {
        UserBaseInfo userBaseInfo = userBaseInfoMapper.findByUserId(userId);
        OperatorReport operatorReport = operatorReportMapper.getLastRecord(userId);
        JSONObject json = getOperatorData(userBaseInfo, operatorReport);
        return json;
    }

    @Override
    public JSONObject getReportByBorrowId(Long borrowId) {
        Borrow borrow = clBorrowMapper.findByPrimary(borrowId);
        UserBaseInfo userBaseInfo = userBaseInfoMapper.findByUserId(borrow.getUserId());
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("borrowId", borrowId);
        BorrowOperatorLog borrowOperatorLog = borrowOperatorLogMapper.findSelective(queryMap);
        OperatorReport operatorReport = null;
        if(borrowOperatorLog != null) {
            queryMap.clear();
            queryMap.put("reqLogId", borrowOperatorLog.getReqLogId());
            operatorReport = operatorReportMapper.findSelective(queryMap);
        }
        JSONObject json = getOperatorData(userBaseInfo, operatorReport);
        return json;
    }

    private JSONObject getOperatorData(UserBaseInfo userBaseInfo, OperatorReport operatorReport) {
        JSONObject json = new JSONObject();
        JSONObject basic = new JSONObject();
        basic.put("name", userBaseInfo.getRealName());
        basic.put("idNo", userBaseInfo.getIdNo());
        basic.put("gender", userBaseInfo.getSex());
        basic.put("age", userBaseInfo.getAge());
        basic.put("phone", userBaseInfo.getPhone());
        if (operatorReport != null) {
            String report = operatorReport.getReport();
            if (StringUtil.isNotBlank(report)) {
                JSONObject reportJson = JSONObject.parseObject(report);
                //处理user_basic
                JSONArray userBasicArray = reportJson.getJSONArray("user_basic");
                for (Object obj : userBasicArray) {
                    JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(obj));
                    if ("native_place".equals(jsonObject.getString("key"))) {
                        String native_place = jsonObject.getString("value");
                        basic.put("native_place", native_place);
                    }
                }

                //处理report
                JSONArray reportArray = reportJson.getJSONArray("report");
                for (Object obj : reportArray) {
                    JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(obj));
                    if ("source_name_zh".equals(jsonObject.getString("key"))) {
                        String operator_name = jsonObject.getString("value");
                        basic.put("operator_name", operator_name);
                    }
                }
                //处理cell_phone
                JSONArray cellPhoneArray = reportJson.getJSONArray("cell_phone");
                for (Object obj : cellPhoneArray) {
                    JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(obj));
                    if ("reliability".equals(jsonObject.getString("key"))) {
                        String reliability = jsonObject.getString("value");
                        if("实名认证".equals(reliability)) {
                            basic.put("reliability", Boolean.TRUE);
                        } else {
                            basic.put("reliability", Boolean.FALSE);
                        }
                    }
                }
                //处理basic_check_items
                JSONArray basicCheckItems = reportJson.getJSONArray("basic_check_items");
                for (Object obj : basicCheckItems) {
                    JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(obj));
                    if ("name_match".equals(jsonObject.getString("check_item")) || "idcard_match".equals(jsonObject.getString("check_item"))) {
                        String result = jsonObject.getString("result");
                        basic.put(jsonObject.getString("check_item"), result);
                    } else if ("is_name_and_idcard_in_court_black".equals(jsonObject.getString("check_item"))) {
                        String result = jsonObject.getString("result");
                        if("否".equals(result)) {
                            basic.put("in_court_black", Boolean.FALSE);
                        } else {
                            basic.put("in_court_black", Boolean.TRUE);
                        }
                    } else if ("is_name_and_idcard_in_finance_black".equals(jsonObject.getString("check_item"))) {
                        String result = jsonObject.getString("result");
                        if("否".equals(result)) {
                            basic.put("in_finance_black", Boolean.FALSE);
                        } else {
                            basic.put("in_finance_black", Boolean.TRUE);
                        }
                    } else if ("is_name_and_mobile_in_finance_black".equals(jsonObject.getString("check_item"))) {
                        String result = jsonObject.getString("result");
                        if("否".equals(result)) {
                            basic.put("phone_in_finance_black", Boolean.FALSE);
                        } else {
                            basic.put("phone_in_finance_black", Boolean.TRUE);
                        }
                    }
                }

                //判断亲密联系人是否为小号
                String tableName1 = ShardTableUtil.generateTableNameById("cl_user_contacts", userBaseInfo.getUserId(), 30000);
                JSONArray emerArray = new JSONArray();
                Map<String, Object> params = new HashMap<>();
                params.put("userId", userBaseInfo.getUserId());
                List<UserEmerContacts> userEmerContactses = userEmerContactsMapper.listSelective(params);
                for (UserEmerContacts userEmerContactse : userEmerContactses) {
                    JSONObject emerJson = new JSONObject();
                    emerJson.put("relation", userEmerContactse.getRelation());
                    emerJson.put("name", userEmerContactse.getName());
                    emerJson.put("phone", userEmerContactse.getPhone());
                    emerJson.put("is_temp_num", Boolean.TRUE);

                    params.put("phone", userEmerContactse.getPhone());
                    List<UserContacts> contacts = userContactsMapper.listShardSelective1(tableName1, params);
                    if(contacts != null && contacts.size() > 0) {
                        if(contacts.get(0).getSumDuration() != null && contacts.get(0).getSumDuration() > 0) {
                            emerJson.put("is_temp_num", Boolean.FALSE);
                        }
                    }
                    emerArray.add(emerJson);
                }
                basic.put("emer_contacts", emerArray);

                //处理用户行为检测
                JSONArray behaviorChecks = new JSONArray();
                List<String> checkTypes = Arrays.asList(BEHAVIOR_CHECK_TYPES.split("\\|"));
                JSONArray behaviorCheckArray = reportJson.getJSONArray("behavior_check");
                for (Object obj : behaviorCheckArray) {
                    JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(obj));
                    if(checkTypes.contains(jsonObject.getString("check_point"))) {
                        JSONObject behavior = new JSONObject();
                        behavior.put("check_point_cn", jsonObject.getString("check_point_cn"));
                        behavior.put("result", jsonObject.getString("result"));
                        behavior.put("evidence", jsonObject.getString("evidence"));
                        behaviorChecks.add(behavior);
                    }
                }

                //处理运营商消费数据
                JSONArray consumBehaviorArray = new JSONArray();
                JSONArray cellBehaviorArray = reportJson.getJSONArray("cell_behavior");
                for (Object obj : cellBehaviorArray) {
                    JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(obj));
                    if(userBaseInfo.getPhone().equals(jsonObject.getString("phone_num"))) {
                        JSONArray beArray = jsonObject.getJSONArray("behavior");
                        if(beArray != null) {
                            for (Object be : beArray) {
                                JSONObject consumJson = new JSONObject();
                                JSONObject beJson = JSONObject.parseObject(JSON.toJSONString(be));
                                consumJson.put("cell_operator", beJson.getString("cell_operator_zh"));
                                consumJson.put("cell_phone_num", beJson.getString("cell_phone_num"));
                                consumJson.put("cell_loc", beJson.getString("cell_loc"));
                                consumJson.put("cell_mth", beJson.getString("cell_mth"));
                                consumJson.put("call_cnt", beJson.getInteger("call_cnt"));
                                consumJson.put("dial_cnt", beJson.getInteger("dial_cnt"));
                                Integer dial_time = beJson.getInteger("dial_time");
                                double dialTime = 0.00;
                                if(dial_time != null) {
                                    dialTime =  Double.valueOf(df.format((double)dial_time / 60));
                                }
                                consumJson.put("dial_time", dialTime);
                                consumJson.put("dialed_cnt", beJson.getInteger("dialed_cnt"));
                                Integer dialed_time = beJson.getInteger("dialed_time");
                                double dialedTime = 0.00;
                                if(dial_time != null) {
                                    dialedTime =  Double.valueOf(df.format((double)dialed_time / 60));
                                }
                                consumJson.put("dialed_time", dialedTime);
                                consumJson.put("sms_cnt", beJson.getInteger("sms_cnt"));
                                Integer total_amount = beJson.getInteger("total_amount");
                                double totalAmount = 0.00;
                                if(total_amount != null) {
                                    totalAmount =  Double.valueOf(df.format((double)total_amount / 100));
                                }
                                consumJson.put("total_amount", totalAmount);

                                consumBehaviorArray.add(consumJson);
                            }
                        }
                    }
                }

                //处理用户查询信息检测 用户黑名单信息检测
                JSONObject searchInfoJson = new JSONObject();
                JSONObject blackInfoJson = new JSONObject();
                JSONArray infoCheckArray = reportJson.getJSONArray("user_info_check");
                if(infoCheckArray != null) {
                    JSONObject infoCheckJson = JSONObject.parseObject(JSON.toJSONString(infoCheckArray.get(0)));
                    searchInfoJson = infoCheckJson.getJSONObject("check_search_info");
                    blackInfoJson = infoCheckJson.getJSONObject("check_black_info");
                }

                //用户出行及漫游分析
                JSONArray tripInfoArray = new JSONArray();
                if (reportJson.getJSONArray("trip_info") != null) {
                    tripInfoArray = reportJson.getJSONArray("trip_info");
                }

                //用户漫游详情统计
                JSONArray roamDetailArray = new JSONArray();
                if (reportJson.getJSONArray("roam_detail") != null) {
                    roamDetailArray = reportJson.getJSONArray("roam_detail");
                }

                //联系人区域汇总
                JSONArray contactRegion = new JSONArray();
                JSONArray contactRegionArray = reportJson.getJSONArray("contact_region");
                for (Object obj : contactRegionArray) {
                    JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(obj));
                    if("contact_region_6m".equals(jsonObject.getString("key"))) {
                        JSONArray regionArray = jsonObject.getJSONArray("region_list");
                        if(regionArray != null) {
                            contactRegion = regionArray;
                        }
                    }
                }

                json.put("behavior_check", behaviorChecks);
                json.put("consum_behavior", consumBehaviorArray);
                json.put("check_search_info", searchInfoJson);
                json.put("check_black_info", blackInfoJson);
                json.put("trip_info", tripInfoArray);
                json.put("roam_detail", roamDetailArray);
                json.put("contact_region", contactRegion);

            }
        }
        json.put("basic", basic);
        return json;
    }
}