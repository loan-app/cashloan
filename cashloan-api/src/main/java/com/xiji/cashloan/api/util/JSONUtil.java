package com.xiji.cashloan.api.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtil {

    public static Map<String, String> jsonToMap(String content){
        Map<String, String> results = new HashMap<String, String>();
        if(content != null){
            if(isJsonArray(content)){
                results.put("input", content);
            }else{
                jsonMap(content, results);
            }
        }
        return results;
    }
    
    public static void jsonMap(String content, Map<String, String> results){
        if (isJsonObject(content)) {
            JSONObject jsonObject = new JSONObject(content);
            Set<String> keys = jsonObject.keySet();
            Map<String, Object> tmps = new HashMap<String, Object>();
            for(String key : keys){
                Object obj = jsonObject.opt(key);
                if(isJsonObject(String.valueOf(obj))){
                    jsonMap(String.valueOf(obj), results);
                }else{
                    tmps.put(key, String.valueOf(obj));
                }
            }
            
            for(Entry<String, Object> tmp : tmps.entrySet()){
                String key = tmp.getKey();
                Object value = tmp.getValue();
                if(results.containsKey(key)){
                    Object tvalue = results.get(key);
                    if(tvalue != null && !"".equals(String.valueOf(tvalue))){
                        continue;
                    }
                }
                results.put(key, String.valueOf(value));
            }
        }
    }
    
    public static boolean isJsonArray(String jsonStr){
        if (jsonStr == null){
            return false;
        }
        try {
            new JSONArray(jsonStr);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }
    
    public static boolean isJsonObject(String jsonStr) {
        if (jsonStr == null){
            return false;
        }
        try {
            new JSONObject(jsonStr);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }
    
    public static void main(String[] args){
        JSONObject obj = new JSONObject();
        obj.put("tel", "010");
        JSONArray array =  new JSONArray();
        array.put("135");
        array.put("136");
        array.put("137");
        obj.put("phones", array.toString());
        
        String temp = obj.toString();
        
        Map<String, String> results = JSONUtil.jsonToMap(temp);
        for(Entry<String, String> result : results.entrySet()){
            System.out.println(result.getKey() + ":" + result.getValue());
        }
        
        
    }
}
