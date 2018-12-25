package com.jiya.cashloan.yixin;

import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.cl.util.HttpUtils;
import com.xiji.cashloan.cl.util.token.HttpRestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by szb on 18/12/24.
 */
public class YixinTest {

    private static String USER_NAME = "jiya_testusr";
    private static String SIGN = "1aea54c5af220130";
    private static String API_NAME = "credit.evaluation.share.api";
    private static String QUERY_REASON = "LOAN_AUDIT";

    private static String API_HOST = "https://starapi.zhichengcredit.com/submit";

    public static void main(String[] args) throws Exception {
        query();
    }

    public static void query() throws Exception {

        CloseableHttpClient httpclient = HttpUtils.createClient(API_HOST);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000).setSocketTimeout(5000).build();

        HttpPost postRequest = new HttpPost(API_HOST);
        postRequest.setConfig(requestConfig);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_name", USER_NAME);
        paramMap.put("sign", SIGN);
        paramMap.put("api_name", API_NAME);
        paramMap.put("query_reason", QUERY_REASON);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id_no", "542301198001015308");
        jsonObject.put("name", "测试二");

        paramMap.put("params", jsonObject.toJSONString());


        String result = HttpRestUtils.postForm(API_HOST, null, paramMap);
        System.out.println(result);
    }
}
