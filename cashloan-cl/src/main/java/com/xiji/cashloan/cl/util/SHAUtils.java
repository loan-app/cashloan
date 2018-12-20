package com.xiji.cashloan.cl.util;

import com.xiji.cashloan.core.common.util.StringUtil;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: king
 * @Date: 2018/12/19 10:45
 * @Description:
 */
public class SHAUtils {

    public static void main(String[] args) throws Exception {
        Map<String, Object> map=new HashMap<String,Object>();
        String key="1234567890";//key值
//        map.put("one","param_one");
//        map.put("two","param_two");
//        map.put("three","param_three");//所要加密的字符串
        map.put("appkey","1");//所要加密的字符串
        map.put("timestamp","2");//所要加密的字符串
        map.put("method","3");//所要加密的字符串
        map.put("sign_method","4");//所要加密的字符串
        //获取信息摘要 - 参数字典排序后字符串
        String preDecryptStr = getOrderByLexicographic(map,key);
        String preDecryptStrx = getOrderByLexicographic(map);
        System.out.println(preDecryptStr);
        System.out.println(preDecryptStrx);
    }

    public static String decrypt(String decrypt)  throws Exception{
        try {
            //指定sha1算法
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(decrypt.getBytes());
            //获取字节数组
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new DigestException("签名错误！");
        }
    }
    /**
     * 获取参数的字典排序
     * @param maps 参数key-value map集合
     * @return String 排序后的字符串
     */
    public static String getOrderByLexicographic(Map<String,Object> maps,String key){
        return splitParams(lexicographicOrder(getParamsName(maps)),maps,key);
    }
    public static String getOrderByLexicographic(Map<String,Object> maps){
        return splitParams(lexicographicOrder(getParamsName(maps)),maps);
    }
    /**
     * 获取参数名称 key
     * @param maps 参数key-value map集合
     * @return
     */
    public static List<String> getParamsName(Map<String,Object> maps){
        List<String> paramNames = new ArrayList<String>();
        for(Map.Entry<String,Object> entry : maps.entrySet()){
            paramNames.add(entry.getKey());
        }
        return paramNames;
    }

    /**
     * 参数名称按字典排序
     * @param paramNames 参数名称List集合
     * @return 排序后的参数名称List集合
     */
    public static List<String> lexicographicOrder(List<String> paramNames){
        Collections.sort(paramNames);
        return paramNames;
    }


    /**
     * 拼接排序好的参数名称和参数值
     * @param paramNames 排序后的参数名称集合
     * @param maps 参数key-value map集合
     * @return String 拼接后的字符串
     */
    private static String splitParams(List<String> paramNames,Map<String,Object> maps,String key){
        StringBuilder paramStr = new StringBuilder();
        paramStr.append(key);
        for(String paramName : paramNames){
            paramStr.append(paramName);
            for(Map.Entry<String,Object> entry : maps.entrySet()){
                if(StringUtil.equals(paramName,entry.getKey())){
                    paramStr.append(String.valueOf(entry.getValue()));
                }
            }
        }
        paramStr.append(key);
//        System.out.println(paramStr.toString());
        return paramStr.toString();
    }

    public static String splitParams(List<String> paramNames,Map<String,Object> maps){
        StringBuilder paramStr = new StringBuilder();
        for(String paramName : paramNames){
            paramStr.append(paramName);
            for(Map.Entry<String,Object> entry : maps.entrySet()){
                if(StringUtil.equals(paramName,entry.getKey())){
                    paramStr.append(String.valueOf(entry.getValue()));
                }
            }
        }
        return paramStr.toString();
    }
}
