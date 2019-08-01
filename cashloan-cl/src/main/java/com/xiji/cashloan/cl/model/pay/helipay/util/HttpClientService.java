package com.xiji.cashloan.cl.model.pay.helipay.util;

import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.*;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.time.StopWatch;

public class HttpClientService {



    private static OkHttpClient client =new OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build();

    private static final MediaType MEDIA_TYPE_JPG = MediaType.parse("image/jpg");


    public static Map<String, Object> getHttpResp(Map<String, String> reqMap, String httpUrl, File file) {
        RequestBody requestBody = null;
        if (null == file) {
            FormBody.Builder builder = new FormBody.Builder();
            for (Iterator<String> iterator = reqMap.keySet().iterator(); iterator.hasNext(); ) {
                String key = (String) iterator.next();
                builder.add(key, reqMap.get(key));
            }
            requestBody = builder.build();
        } else {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            for (Iterator<String> iterator = reqMap.keySet().iterator(); iterator.hasNext(); ) {
                String key = (String) iterator.next();
                builder.addFormDataPart(key, reqMap.get(key));
            }
            requestBody = builder.setType(MultipartBody.FORM)
                    .addFormDataPart("file", file.getName(), RequestBody.create(MEDIA_TYPE_JPG, file))
                    .build();
        }

        Request request = new Request.Builder() // okHttp post
                .url(httpUrl)
                .post(requestBody)
                .build();

        StopWatch watch = new StopWatch();
        watch.start();
        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            if (body == null) {
                throw new RuntimeException("响应 body 体为空");
            }
            String str = body.string();
            Map<String, Object> mp = new HashedMap();
            mp.put("statusCode", response.code());
            mp.put("response", str);
            return mp;
        } catch (IOException e) {
            throw new RuntimeException("请求出错", e);
        }
    }


    public static Map<String, Object> getHttpResp(Map<String, String> reqMap, String httpUrl) {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(httpUrl);
        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, new Integer(300000));
        String response = "";


        Map<String, Object> mp = new HashMap<String, Object>();

        try {
            NameValuePair[] nvps = getNameValuePair(reqMap);
            method.setRequestBody(nvps);
            int rescode = client.executeMethod(method);
            mp.put("statusCode", rescode);

            System.out.println("rescode:" + rescode);
            if (rescode == HttpStatus.SC_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), "UTF-8"));
                String curline = "";
                while ((curline = reader.readLine()) != null) {
                    response += curline;
                }
                System.out.println("request:" + response);
                mp.put("response", response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }
        return mp;
    }
    public static String httpSend(Map<String, String> reqMap, String httpUrl) {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(httpUrl);
        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, new Integer(300000));
        String response = "";

        try {
            NameValuePair[] nvps = getNameValuePair(reqMap);
            method.setRequestBody(nvps);
            int rescode = client.executeMethod(method);

            System.out.println("rescode:" + rescode);
            if (rescode == HttpStatus.SC_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), "UTF-8"));
                String curline = "";
                while ((curline = reader.readLine()) != null) {
                    response += curline;
                }
                System.out.println("request:" + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }
        return response;
    }

    public static NameValuePair[] getNameValuePair(Map<String, String> bean) {
        List<NameValuePair> x = new ArrayList<NameValuePair>();
        for (Iterator<String> iterator = bean.keySet().iterator(); iterator.hasNext(); ) {
            String type = (String) iterator.next();
            x.add(new NameValuePair(type, String.valueOf(bean.get(type))));
        }
        Object[] y = x.toArray();
        NameValuePair[] n = new NameValuePair[y.length];
        System.arraycopy(y, 0, n, 0, y.length);
        return n;
    }

    public static JSONObject sendJson(JSONObject json, String httpUrl) {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(httpUrl);
        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, new Integer(5000));
        String response = "";

        JSONObject retJson = new JSONObject();

        try {
            RequestEntity entity = new StringRequestEntity(json.toJSONString(),"application/json","UTF-8");
            method.setRequestEntity(entity);
            method.setRequestHeader("Content-Type","application/json;charset=UTF-8");
            int rescode = client.executeMethod(method);

            if (rescode == HttpStatus.SC_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), "UTF-8"));
                String curline = "";
                while ((curline = reader.readLine()) != null) {
                    response += curline;
                }
               retJson = JSONObject.parseObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }
        return retJson;
    }

}
