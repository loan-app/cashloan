package com.xiji.cashloan.cl.util.weijifen;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;

import java.util.TreeMap;

public class RSASign {
    public static RiskRequestDto sign(RiskRequestDto req, String privateKey) throws Exception {
        SerializeConfig config = new SerializeConfig(); // 生产环境中，config要做singleton处理，要不然会存在性能问题
        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        JSONObject json = (JSONObject) JSON.toJSON(req, config);
        json.remove("data");
        json.remove("callback");
        json.remove("sign");
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.putAll(json);
        treeMap = MainSign.assembleSign(treeMap, privateKey);
        req.setSign((String)treeMap.get("sign"));
        return req;
    }

    public static boolean verify(RiskRequestDto req, String publicKey) {
        SerializeConfig config = new SerializeConfig(); // 生产环境中，config要做singleton处理，要不然会存在性能问题
        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        JSONObject json = (JSONObject) JSON.toJSON(req, config);
        json.remove("data");
        json.remove("callback");
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.putAll(json);
        return MainSign.doSignCheck(treeMap, publicKey);
    }
}
