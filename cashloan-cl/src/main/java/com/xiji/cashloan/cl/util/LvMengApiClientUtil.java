package com.xiji.cashloan.cl.util;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Slf4j
public class LvMengApiClientUtil {

    private static String appId = "T006";									//机构appId
    private static String signKey = "HiXRSfHguyRlkgDHzgfvjcXkpL3106";		//签名密钥
    private static String secert = "QlJyJO9QZ3";							//加密密钥
    private static String URL = "http://list.yichunruirun.com/api/v1";		//接口地址

    /**
     * 	提交黑名单
     * 	单次最多提交100条黑名单，请分页提交
     */
    public static void subBlack(List<Black> blackList) {
        JSONObject json = new JSONObject();
        json.put("list", blackList);
        JSONObject ret = submit("subBlack", json);
        log.info("subBlack:"+ret.toString());
    }

    /**
     * 	用户最新详情
     * 	ret.body.flag=2为命中黑名单，其他为未命中
     */
    public static Boolean userDetail(String idcard, String phone, String name) {
        JSONObject json = new JSONObject();
        json.put("idcard", idcard);
        json.put("phone", phone);
        json.put("name", name);
        JSONObject ret = submit("userDetail", json);
        log.info("userDetail:{}",ret.toJSONString());
        if("2".equals(ret.getString("body"))) {
            return true;
        }
        return false;
    }


    /**
     * 黑名单
     */
    @Data
    public static class Black {
        private String idcard;		//身份证号
        private String phone;		//手机号
        private String name;		//姓名
        private Integer type;		//黑名单类型：1.逾期超过3天
    }

    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    /**
     * Http Post Json
     *
     * @param call
     * @param bizObj	业务对象
     * @return
     */
    public static JSONObject submit(String call, Object bizObj) {
        String jsonStr = genJsonStr(call, JSONObject.parseObject(JSONObject.toJSONString(bizObj)), appId, signKey, secert);

        Request.Builder request = new Request.Builder();
        long start = System.currentTimeMillis();
        try {
            Response response = client.newCall(request.url(URL)
                    .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonStr))
                    .build()).execute();
            log.info(String.format("【调用第三方耗时】：use=%s", System.currentTimeMillis() - start));

            JSONObject ret = JSONObject.parseObject(response.body().string());
            JSONObject body = JSONObject.parseObject(aesDecrypt((String)ret.getString("body"), secert));
            ret.put("body", body);
            return ret;
        } catch (Exception e) {
            log.error(String.format("【调用第三方异常】：use=%s, %s", System.currentTimeMillis() - start, e.getMessage()),e);
        }
        return null;
    }

    /**
     * 生成请求json
     *
     * @param call		接口名称
     * @param biz		业务字段Json
     * @param appId		appId
     * @param signKey	签名密钥
     * @param secert	业务加解密密钥
     * @return
     */
    public static String genJsonStr(String call, JSON biz, String appId, String signKey, String secert) {

        TreeMap<String, Object> sortedParams = new TreeMap<String, Object>();
        sortedParams.put("appId", appId);		//appId
        sortedParams.put("signKey", signKey);	//
        sortedParams.put("call", call);			//接口名称
        sortedParams.put("biz", biz.toJSONString());				//
        sortedParams.put("timestamp", System.currentTimeMillis()+"");	//
        sortedParams.put("signType", "MD5");						//
        sortedParams.put("version", "1");							//
        String sign = genSign(sortedParams);						//生成签名

        //请求json
        JSONObject json = new JSONObject();
        JSONObject info = new JSONObject(sortedParams);
        json.put("info", aesEncrypt(info.toString(), secert));		//info加密字段
        json.put("sign", sign);										//签名字段
        return json.toString();
    }

    /**
     * 生成签名
     *
     * MD5(自然升序(key=value&key=value&..))
     */
    public static String genSign(TreeMap<String, Object> sortedParams) {
        StringBuffer buffer = new StringBuffer();
        for (Map.Entry<String, Object> entry : sortedParams.entrySet()) {
            String key = entry.getKey();
            String value = (String)entry.getValue();
            if (null == value) {
                continue;
            }
            buffer.append(key).append("=").append(value).append("&");
        }
        int length = buffer.length();
        String content = buffer.delete(length - 1, length).toString();
        String sign = getMD5(content);
        log.info("genSign:"+sign);
        return sign;
    }

    /**
     * AES加密
     *
     * @param content		明文内容
     * @param key			密钥
     * @return
     * @throws Exception
     */
    public static String aesEncrypt(String content, String key) {
        if(StringUtils.isEmpty(content)) {
            return "";
        }
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes("UTF-8"));
            keyGen.init(128, secureRandom);
            SecretKey secretKey = keyGen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec keySepc = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.ENCRYPT_MODE, keySepc);// 初始化
            byte[] buffer = cipher.doFinal(content.getBytes("UTF-8"));

            return byte2HexString(buffer);
        } catch (Exception e) {
            log.error("aesEncrypt error, "+e.getMessage(), e);
        }
        return "";
    }

    /**
     * AES解密
     *
     * @param content		加密内容
     * @param key			密钥
     */
    public static String aesDecrypt(String content, String key) {
        if(StringUtils.isEmpty(content)) {
            return "";
        }
        byte[] contentBs = hexStr2Byte(content);
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes("UTF-8"));
            keyGen.init(128, secureRandom);
            SecretKey secretKey = keyGen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, keySpec);// 初始化
            byte[] result = cipher.doFinal(contentBs);
            return new String(result, "UTF-8");
        } catch (Exception e) {
            log.error("getMD5 error,"+e.getMessage(), e);
        }
        return "";
    }


    /**
     * 生成md5
     *
     * @param message
     * @return
     */
    private static String getMD5(String message) {
        StringBuilder md5str = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] input = message.getBytes("UTF-8");
            byte[] bytes = md.digest(input);

            // 把数组每一字节换成16进制连成md5字符串
            int digital;
            for (int i = 0; i < bytes.length; i++) {
                digital = bytes[i];
                if (digital < 0) {
                    digital += 256;
                }
                if (digital < 16) {
                    md5str.append("0");
                }
                md5str.append(Integer.toHexString(digital));
            }
            return md5str.toString().toLowerCase();
        } catch (Exception e) {
            log.error("getMD5 error,"+e.getMessage(), e);
        }
        return "";
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buffer
     * @return
     */
    private static String byte2HexString(byte buffer[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buffer.length; i++) {
            String hex = Integer.toHexString(buffer[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexString
     * @return
     */
    private static byte[] hexStr2Byte(String hexString) {
        if (hexString.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexString.length() / 2];
        for (int i = 0; i < hexString.length() / 2; i++) {
            int high = Integer.parseInt(hexString.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexString.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
