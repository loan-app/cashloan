package com.xiji.cashloan.cl.model.moxie;

import com.xiji.cashloan.cl.util.HttpUtils;
import com.xiji.cashloan.cl.util.magic.GzipUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * 魔蝎接口调用
 * Created by szb on 18/11/21.
 */
public class MxCreditRequest {
    private static final int CONNECT_TIMEOUT = 8000;
    private static final int SOCKET_TIMEOUT = 20000;
    private static ContentType CONTENT_TYPE = ContentType.create("application/json", "utf-8");
    private static RequestConfig REQUEST_CONFIG;

    private static Logger logger = LoggerFactory.getLogger(MxCreditRequest.class);

    //解压缩
    public static String getCompress(String url, Map<String, String> headers)
            throws Exception {
        InputStream respIs = getIs(url, headers);
        return GzipUtil.uncompress(respIs);
    }

    public static String postCompress(String url, Map<String, String> headers, String jsonString)
            throws Exception {
        InputStream respIs = postIs(url, headers, jsonString);
        return GzipUtil.uncompress(respIs);
    }

    public static String get(String url, Map<String, String> headers)
            throws Exception {
        InputStream respIs = getIs(url, headers);
        return HttpUtils.convertStreamToString(respIs);
    }

    public static String post(String url, Map<String, String> headers, String jsonString)
            throws Exception{
        InputStream respIs = postIs(url, headers, jsonString);
        return HttpUtils.convertStreamToString(respIs);
    }

    private static InputStream getIs(String url, Map<String, String> headers)
            throws Exception {
        CloseableHttpClient httpclient = createClient(url);
        HttpGet getRequest = new HttpGet(url);
        getRequest.setConfig(REQUEST_CONFIG);

        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                getRequest.setHeader(entry.getKey(), entry.getValue());
            }
        }
        logger.debug("executing request to " + url);
        HttpResponse httpResponse = httpclient.execute(getRequest);
        InputStream respIs = httpResponse.getEntity().getContent();
        return respIs;
    }

    private static InputStream postIs(String url, Map<String, String> headers, String jsonString)
            throws Exception {
        CloseableHttpClient httpclient = createClient(url);
        HttpPost postRequest = new HttpPost(url);
        postRequest.setConfig(REQUEST_CONFIG);
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

        return respIs;
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

    static {
        REQUEST_CONFIG = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
    }

}
