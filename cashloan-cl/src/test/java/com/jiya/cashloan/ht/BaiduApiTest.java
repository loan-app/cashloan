package com.jiya.cashloan.ht;

import com.alibaba.fastjson.JSON;
import com.xiji.cashloan.cl.util.RSACoder;
import com.xiji.cashloan.core.common.util.Base64;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import sun.misc.BASE64Encoder;

/**
 * @Auther: king
 * @Date: 2019/1/30 11:53
 * @Description:
 */
public class BaiduApiTest {

    public static void main(String[] args) throws Exception {
//        apiTest();
        testImage();
    }

//    public static void apiTest() {
//        String requrl = "http://rryqo.com/finance/v1/face/match?applyNo=124";
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("image1", fileToImage("/Users/51NB/Desktop/wxbIdcard.png"));
//        jsonObject.put("image2", fileToImage("/Users/51NB/Desktop/wxbIdcard.png"));
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
//
//        String signStr = Base64.encode(RSACoder.encryptByPrivateKey("CH13IVR8S124".getBytes(), Base64.decode("")));
//        System.out.println(signStr);
//
//        Request request = new Request.Builder().url(requrl)
//            .addHeader("channelNo", "CH13IVR8S")
//            .addHeader("signStr", signStr)
//            .post(requestBody).build();
//        OkHttpClient okHttpClient = new OkHttpClient();
//        okHttpClient.setReadTimeout(10, TimeUnit.SECONDS);
//        try {
//            ResponseBody result = okHttpClient.newCall(request).execute().body();
//            JSONObject jsonObject2 = JSON.parseObject(result.string());
//            System.out.println(jsonObject2.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        String signStr = Base64.encode(RSACoder.encryptByPrivateKey("CH13IVR8S124".getBytes(), Base64.decode("MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEAoQbUJfG8h63o2klN3InuK1qUetS71O0YINFlHyZzzKmRBCgNyvuDt8ZuCjB9Zrexk+FNOeUg2dGV8XSCZKwLmwIDAQABAkEAg/C3ddvMMZQi/nEf9juiRi2zCa4ztbULlyBb7hkwuxlL+HYHln8EhgvBTGAWb596BQTmmDET1iVgDm+pWEfd2QIhANk6cX7/H4AkKr9GLlc5KMJNm7+/tJzoMTw6uETwfL1HAiEAvcRuUYY4azGhBAJmsoxSy/S0DSGYZlohMN+FYjSRmQ0CIH+257GVx2xsVyGb3nTzqy4JuO9Ug5jYvtG9aEdH6N7TAiEAgoeV9l+jeSBHB/H63/+jiAUGwC2GnYiLYgmtvtI4ABUCIQC1BoDi3sip+YcY3gw6+SbChaRNAcfZVoeJK60ZM5+xww==")));
//    }

    public static void testImage() throws Exception {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
            .addPart("image1", new FileBody(new File("/unit/word/self/WechatIMG4.jpeg")))
            .addPart("image2", new FileBody(new File("/unit/word/self/WechatIMG4.jpeg")));

        String signStr = Base64.encode(RSACoder.encryptByPrivateKey("CH13IVR8S124".getBytes(), Base64.decode("MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEAoQbUJfG8h63o2klN3InuK1qUetS71O0YINFlHyZzzKmRBCgNyvuDt8ZuCjB9Zrexk+FNOeUg2dGV8XSCZKwLmwIDAQABAkEAg/C3ddvMMZQi/nEf9juiRi2zCa4ztbULlyBb7hkwuxlL+HYHln8EhgvBTGAWb596BQTmmDET1iVgDm+pWEfd2QIhANk6cX7/H4AkKr9GLlc5KMJNm7+/tJzoMTw6uETwfL1HAiEAvcRuUYY4azGhBAJmsoxSy/S0DSGYZlohMN+FYjSRmQ0CIH+257GVx2xsVyGb3nTzqy4JuO9Ug5jYvtG9aEdH6N7TAiEAgoeV9l+jeSBHB/H63/+jiAUGwC2GnYiLYgmtvtI4ABUCIQC1BoDi3sip+YcY3gw6+SbChaRNAcfZVoeJK60ZM5+xww==")));
        HttpPost httpPost = new HttpPost("http://rryqo.com/finance/v1/face/match?applyNo=125");
        httpPost.setEntity(builder.build());
        httpPost.setHeader("channelNo","CH13IVR8S");
        httpPost.setHeader("signStr", signStr);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("image1", getImageStr("/unit/word/self/WechatIMG3.jpeg"));
        paramMap.put("image2", getImageStr("/unit/word/self/WechatIMG4.jpeg"));
        String body = JSON.toJSONString(paramMap);
        ContentType contentType = ContentType.create("application/json", "utf-8");
        httpPost.setEntity(new StringEntity(body, contentType));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            CloseableHttpResponse res = httpClient.execute(httpPost);

            System.out.println(convertStreamToString(res.getEntity().getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getImageStr(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException var14) {
            var14.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException var13) {
                var13.printStackTrace();
            }

        }

        return sb.toString();
    }

}
