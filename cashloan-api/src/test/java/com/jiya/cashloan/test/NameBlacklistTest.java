package com.jiya.cashloan.test;

import com.alibaba.fastjson.JSON;
import com.xiji.cashloan.core.common.util.HttpsUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @Auther: king
 * @Date: 2018/12/10 17:37
 * @Description:
 */
public class NameBlacklistTest {

    public static void main(String[] args) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dimensionValue","360732198805300925");
        jsonObject.put("dimensionKey","01");
        jsonArray.put(jsonObject);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("dimensionValue","13024241111");
        jsonObject1.put("dimensionKey","02");
        jsonArray.put(jsonObject1);

        String url = "http://192.168.21.42:8011/api/black/apkie/test.htm";
        Map<String,String> param = new HashMap<String, String>();
        param.put("web","debug");
        param.put("input",jsonArray.toString());
//        param.put("source","tests");
        param.put("tableName","cl_name_whitelist");
        System.out.println(JSON.toJSON(param));
        String resp = HttpsUtil.postClient(url, param);
        System.out.println();
        System.out.println(resp);
    }
}
