package com.rongdu.cashloan.cl.util.token;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * Created by szb on 18/11/15.
 */
public class HttpRestUtils {
    private static ContentType CONTENT_TYPE = ContentType.create("application/json", "utf-8");

    private static Logger logger = LoggerFactory.getLogger(HttpRestUtils.class);

    public static String get(String url, Map<String, String> headers)
            throws Exception {
        CloseableHttpClient httpclient = createClient(url);
        //设置连接超时时间、请求获取数据超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000).setSocketTimeout(5000).build();
        HttpGet getRequest = new HttpGet(url);
        getRequest.setConfig(requestConfig);

        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                getRequest.setHeader(entry.getKey(), entry.getValue());
            }
        }
        logger.debug("executing request to " + url);
        HttpResponse httpResponse = httpclient.execute(getRequest);
        return getResult(httpResponse);
    }

    public static String post(String url, Map<String, String> headers, String jsonString)
            throws Exception {
        CloseableHttpClient httpclient = createClient(url);
        //设置连接超时时间、请求获取数据超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000).setSocketTimeout(5000).build();

        HttpPost postRequest = new HttpPost(url);
        postRequest.setConfig(requestConfig);
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
        return getResult(httpResponse);
    }

    public static boolean delete(String url, Map<String, String> headers)
            throws Exception {
        CloseableHttpClient httpclient = createClient(url);
        //设置连接超时时间、请求获取数据超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000).setSocketTimeout(5000).build();

        HttpDelete deleteRequest = new HttpDelete(url);
        deleteRequest.setConfig(requestConfig);
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                deleteRequest.setHeader(entry.getKey(), entry.getValue());
            }
        }
        logger.debug("executing request to " + url);
        HttpResponse httpResponse = httpclient.execute(deleteRequest);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode < 200 || statusCode >= 300) {
            logger.debug("请求返回CODE：" + statusCode);
            return false;
        }
        return true;
    }


    public static String multipartUpload(String url, Map<String, String> headers, Map<String, ContentBody> parts) throws Exception {
        HttpClient httpclient = createClient(url);
        HttpPost postRequest = new HttpPost(url);
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                postRequest.setHeader(entry.getKey(), entry.getValue());
            }
        }
        MultipartEntityBuilder part = MultipartEntityBuilder.create();
        part.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        part.setCharset(Charset.forName("UTF-8"));
        part.setBoundary(null);
        for (String key : parts.keySet()) {
            part.addPart(key, parts.get(key));
        }
        postRequest.setEntity(part.build());
        logger.debug("executing request to " + url);
        HttpResponse httpResponse = httpclient.execute(postRequest);
        return getResult(httpResponse);
    }

    public static InputStream openStream(String url, Map<String, String> headers) throws Exception {
        HttpClient client = createClient(url);
        HttpGet httpget = new HttpGet(url);
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpget.setHeader(entry.getKey(), entry.getValue());
            }
        }
        logger.debug("executing request to " + url);
        HttpResponse httpResponse = client.execute(httpget);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode < 200 || statusCode >= 300) {
            logger.debug("请求返回CODE：" + statusCode);
            return null;
        }
        HttpEntity entity = httpResponse.getEntity();
        return entity.getContent();
    }


    private static String getResult(HttpResponse httpResponse) throws Exception {
        String result = null;
        HttpEntity entity = httpResponse.getEntity();

        if (entity != null) {
            result = EntityUtils.toString(entity, "UTF-8");
        }

        int statusCode = httpResponse.getStatusLine().getStatusCode();
//        if (statusCode < 200 || statusCode >= 300) {
//            logger.debug("请求返回CODE：" + statusCode + "返回数据：" + result);
//            return null;
//        }
        return result;
    }

    public static CloseableHttpClient createClient(String url) {
        try {
            if (url.startsWith("https://")) {
                SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                    //信任所有
                    public boolean isTrusted(X509Certificate[] chain,
                                             String authType) throws CertificateException {
                        return true;
                    }

                }).build();
                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
                return HttpClientBuilder.create().setSSLSocketFactory(sslsf).build();
            }
        } catch (Exception e) {
            logger.warn("CertificateException", e);
        }
        return HttpClients.createDefault();
    }
}
