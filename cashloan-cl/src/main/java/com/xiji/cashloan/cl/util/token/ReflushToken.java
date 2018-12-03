package com.xiji.cashloan.cl.util.token;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by szb on 18/11/15.
 */
public class ReflushToken {
    public static void main(String[] args) throws Exception {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("apiKey", "690abc5009eda69f786c380cef51336a");
        paramMap.put("securityKey", "ea108d5b1dc792680172d0e925a69470598ed54d");
        //强制刷新
//        paramMap.put("refresh", "1");
        String body = JSON.toJSONString(paramMap);
        //token:NzIxYmY5ZTc5MjlmODNjMmVjYTkyOWJk

        Map<String, String> headMap = new HashMap<>();
        headMap.put("apiKey", "hzjieyat");
        String url = "https://api.dsdatas.com/credit/api/token";
        String result = HttpRestUtils.post(url, headMap, body);
        System.out.println("调用大圣返回token:" + result);
    }
}
