package com.xiji.cashloan.cl.util.token;

import com.xiji.cashloan.cl.util.HttpUtils;
import com.xiji.cashloan.cl.util.magic.GzipUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by szb on 18/11/15.
 */
public class HttpRestUtils {
    private static ContentType CONTENT_TYPE = ContentType.create("application/json", "utf-8");

    private static Logger logger = LoggerFactory.getLogger(HttpRestUtils.class);

    public static String postForm(String url, Map<String, String> headers, Map<String, Object> params) throws Exception {
        CloseableHttpClient httpclient = HttpUtils.createClient(url);
        HttpPost postRequest = new HttpPost(url);
        postRequest.setConfig(HttpUtils.getRequestConfig());
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                postRequest.setHeader(entry.getKey(), entry.getValue());
            }
        }
        List<NameValuePair> pairList = new ArrayList<>(params.size());
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
            pairList.add(pair);
        }
        postRequest.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
        logger.debug("executing request to " + url);
        HttpResponse httpResponse = httpclient.execute(postRequest);
        InputStream respIs = httpResponse.getEntity().getContent();

        return HttpUtils.convertStreamToString(respIs);
    }

    public static String pinganRequest(String url, MultipartEntityBuilder builder) throws Exception {
        CloseableHttpClient httpclient = HttpUtils.createClient(url);
        HttpPost postRequest = new HttpPost(url);
        postRequest.setEntity(builder.build());
        HttpResponse httpResponse = httpclient.execute(postRequest);
        InputStream respIs = httpResponse.getEntity().getContent();

        return HttpUtils.convertStreamToString(respIs);
    }


    public static String postRequest(String url, Map<String, String> headers, String jsonString)
            throws Exception {
        CloseableHttpClient httpclient = HttpUtils.createClient(url);
        HttpPost postRequest = new HttpPost(url);
        postRequest.setConfig(HttpUtils.getRequestConfig());
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                postRequest.setHeader(entry.getKey(), entry.getValue());
            }
        }
        if (!StringUtils.isBlank(jsonString)) {
            logger.debug("body: " + jsonString);
            StringEntity entity = new StringEntity(jsonString, CONTENT_TYPE);
            postRequest.setEntity(entity);
        }
        logger.debug("executing request to " + url);
        HttpResponse httpResponse = httpclient.execute(postRequest);
        InputStream respIs = httpResponse.getEntity().getContent();
        String result = HttpUtils.convertStreamToString(respIs);
        return result;
    }


    public static String getRequest(String url, Map<String, String> headers)
            throws Exception {
        CloseableHttpClient httpclient = HttpUtils.createClient(url);
        HttpGet getRequest = new HttpGet(url);
        getRequest.setConfig(HttpUtils.getRequestConfig());

        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                getRequest.setHeader(entry.getKey(), entry.getValue());
            }
        }
        logger.debug("executing request to " + url);
        HttpResponse httpResponse = httpclient.execute(getRequest);
        InputStream respIs = httpResponse.getEntity().getContent();
        String result = HttpUtils.convertStreamToString(respIs);
        return result;
    }

    public static String getRandomNumber(String prefix){
        return prefix+System.currentTimeMillis()+(int)((Math.random() * 9+1) * 10000);
    }

    public static String zmRequest(String url, String data) throws Exception {
        CloseableHttpClient httpclient = HttpUtils.createClient(url);
        HttpPost postRequest = new HttpPost(url);
        postRequest.setEntity(new ByteArrayEntity(GzipUtil.gzip(data)));
        HttpResponse httpResponse = httpclient.execute(postRequest);
        InputStream respIs = httpResponse.getEntity().getContent();
        String result = HttpUtils.convertStreamToString(respIs);
        return result;
    }
}
