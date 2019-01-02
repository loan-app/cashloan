package com.xiji.cashloan.cl.util.token;

import com.xiji.cashloan.cl.util.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
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
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
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

}
