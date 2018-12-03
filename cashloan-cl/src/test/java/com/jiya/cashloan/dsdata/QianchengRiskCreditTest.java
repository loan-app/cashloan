package com.jiya.cashloan.dsdata;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jiya.cashloan.TestConstants;
import com.xiji.cashloan.cl.model.dsdata.TKCreditRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 浅橙贷前接口测试类
 * Created by szb on 18/11/19.
 */
public class QianchengRiskCreditTest {
    public static void main(String[] args) throws Exception {
        String host = "https://api.dsdatas.com/c/riskCheck";
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", TestConstants.NAME);
        payload.put("idCard", TestConstants.ID_CARD);
        payload.put("mobile", TestConstants.MOBILE);
        JSONObject data = new JSONObject();
        //用户基础信息
        JSONObject basicInfo = new JSONObject();
        basicInfo.put("cur_address", "杭州市西湖区星洲花园");
        basicInfo.put("perm_address", "江西省上饶市鄱阳县");
        basicInfo.put("company_name", "吉亚网络科技有限公司");
        basicInfo.put("company_phone", "0571-88812345");
        basicInfo.put("company_address", "杭州市西湖区西港新界");
        JSONArray userContact = new JSONArray();
        JSONObject contact1 = new JSONObject();
        contact1.put("name", "张三");
        contact1.put("relation", 3);
        contact1.put("mobile", "18301600687");
        JSONObject contact2 = new JSONObject();
        contact2.put("name", "李四");
        contact2.put("relation", 4);
        contact2.put("mobile", "18301600688");
        userContact.add(contact1);
        userContact.add(contact2);
        basicInfo.put("user_contact", userContact);
        data.put("basic_info", basicInfo);
        //运营商数据
        JSONObject telecomData = new JSONObject();
        data.put("telecom_data", telecomData);
        //借款订单信息
        JSONObject orderDetail = new JSONObject();
        orderDetail.put("order_id", 12345678);
        orderDetail.put("amount", "100000");
        orderDetail.put("loan_term", 7);
        orderDetail.put("order_time", "2018-11-19 12:00:28");
        data.put("order_detail", orderDetail);
        payload.put("data", data);
        TKCreditRequest tkCreditRequest = new TKCreditRequest(host, payload);
        String result = tkCreditRequest.request();
        System.out.println("浅橙贷前接口返回结果: " + result);
    }
}
