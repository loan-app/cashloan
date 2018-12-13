package com.xiji.cashloan.cl.model.xinyan;

import com.xiji.cashloan.cl.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by szb on 18/12/10.
 */
public class XinyanRequest {

    private static Logger logger = LoggerFactory.getLogger(XinyanRequest.class);

    public static String post(String url, Map<String, String> headers, Map<String, Object> params) throws Exception {
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
