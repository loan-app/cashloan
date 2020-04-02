package com.xiji.cashloan.cl.util.magic;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 魔蝎风控云url处理工具类
 * Created by szb on 18/12/3.
 */
public class MagicRiskUtils {
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
