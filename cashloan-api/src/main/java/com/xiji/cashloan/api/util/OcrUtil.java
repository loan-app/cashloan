package com.xiji.cashloan.api.util;

import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.core.common.util.StringUtil;
import org.apache.commons.lang.math.NumberUtils;

/**
 * @Auther: king
 * @Date: 2019/2/19 11:36
 * @Description:
 */
public class OcrUtil {

    private static final String youdun = "youdun";

    public static boolean ifYoudun(String ocrType) {
        return StringUtil.equalsIgnoreCase(ocrType, youdun);
    }

    public static boolean success(JSONObject ocrResultJson) {
        if (ocrResultJson == null) {
            return false;
        }
        return StringUtil.equalsIgnoreCase("000000",ocrResultJson.getString("ret_code"));
    }

    public static String getIdName(JSONObject ocrResultJson) {
        return ocrResultJson.getString("id_name");
    }
    public static String getIdCard(JSONObject ocrResultJson) {
        return ocrResultJson.getString("id_no");
    }
    public static String getAuth(JSONObject ocrResultJson) {
        return ocrResultJson.getString("result_auth");
    }

    public static double getScore(JSONObject ocrResultJson) {
        return NumberUtils.toDouble(ocrResultJson.getString("be_idcard"),0.0);
    }

    public static String getLivingPhoto(JSONObject ocrResultJson) {
        return ocrResultJson.getString("url_photoliving");
    }
    public static String getFrontcard(JSONObject ocrResultJson) {
        return ocrResultJson.getString("url_frontcard");
    }
    public static String getBackcard(JSONObject ocrResultJson) {
        return ocrResultJson.getString("url_backcard");
    }

}
