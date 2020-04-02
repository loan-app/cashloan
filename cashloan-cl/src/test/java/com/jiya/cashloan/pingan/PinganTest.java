package com.jiya.cashloan.pingan;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.cl.util.token.HttpRestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by szb on 18/12/26.
 */
public class PinganTest {

    private static final String VKEY = "120181225001";
    private static final String API_HOST = "https://jrapi.pacra.cn/service?t=grayscale_stat";

    private static final String PHONE = "15958189557";
    private static final String NAME = "石振波";
    private static final String IDCARD = "362330199408051135";

    public static void main(String[] args) throws Exception {
        testQuery();
    }

    public static void testQuery() throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("phone", PHONE);
        paramMap.put("name", NAME);
        paramMap.put("idcard", IDCARD);
        paramMap.put("vkey", VKEY);
        paramMap.put("call_detail_type", 0);

        JSONArray jsonArray = new JSONArray();
        JSONObject json1 = new JSONObject();
        json1.put("call_time", "2018-11-29 18:23:53");
        json1.put("duration", "124");
        json1.put("call_model", "主叫");
        json1.put("contact", "057110086");
        json1.put("call_addr", "杭州");
//        json1.put("contact_addr", "2018-11-29 18:23:53")
        json1.put("called_type", "国内通话");
        jsonArray.add(json1);

        JSONObject json2 = new JSONObject();
        json2.put("call_time", "2018-11-29 18:23:53");
        json2.put("duration", "124");
        json2.put("call_model", "主叫");
        json2.put("contact", "18070494026");
        json2.put("call_addr", "杭州");
//        json2.put("contact_addr", "2018-11-29 18:23:53")
        json2.put("called_type", "国内通话");
        jsonArray.add(json2);

        paramMap.put("call_details", jsonArray.toJSONString());


        String result = HttpRestUtils.postForm(API_HOST, null, paramMap);
        System.out.println(result);
    }
}
