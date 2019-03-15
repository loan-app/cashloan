package com.xiji.cashloan.cl.model.dsdata;

import com.xiji.cashloan.core.common.context.Global;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.Asserts;
import org.apache.http.util.CharsetUtils;

/**
 * OCR
 * Created by szb on 18/11/16.
 */
public class LinkfaceIDTkOcrRequest extends DsTkCreditRequest {
    private File ocrFrontImg;
    private File ocrBackImg;

    public LinkfaceIDTkOcrRequest(String host, File ocrFrontImg, File ocrBackImg) {
        super(host);
        this.ocrFrontImg = ocrFrontImg;
        this.ocrBackImg = ocrBackImg;
    }
    @Override
    protected HttpEntity handle() throws Exception {
        Asserts.notNull(this.ocrFrontImg, "ocrFrontImg");

        MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .addPart("image", new FileBody(this.ocrFrontImg))
                .addPart("api_key", new StringBody(Global.getValue("face_api_key"), ContentType.MULTIPART_FORM_DATA))
                .addPart("api_secret", new StringBody(Global.getValue("face_api_secret"), ContentType.MULTIPART_FORM_DATA))
                .addPart("return_portrait", new StringBody("0", ContentType.MULTIPART_FORM_DATA));
        //    if (this.ocrBackImg != null) {
        //        FileBody mulitpartEntity = new FileBody(this.ocrBackImg);
        //        builder.addPart("ocrBackImg", mulitpartEntity);
        //    }

        HttpEntity mulitpartEntity1 = builder.setCharset(CharsetUtils.get("UTF-8")).build();
        return mulitpartEntity1;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        //    test1(); // ocr
            test2(); //有源比对
    }

    private static void test2() throws UnsupportedEncodingException {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .addPart("image", new FileBody(new File("/unit/word/self/WechatIMG4.jpeg")))
                .addPart("api_key", new StringBody("c3_OTRm4Jhhl6hbDllq-Nqj5nheHlSiQ", ContentType.MULTIPART_FORM_DATA))
                .addPart("api_secret", new StringBody("44rXoUZghpR-lDr6BFVsuSC6L962yQc6", ContentType.MULTIPART_FORM_DATA))
                .addPart("comparison_type", new StringBody("1", ContentType.MULTIPART_FORM_DATA))
                .addPart("face_image_type", new StringBody("raw_image", ContentType.MULTIPART_FORM_DATA))
                .addPart("idcard_name", new StringBody("周子杨", Charset.forName("utf-8")))
                .addPart("idcard_number", new StringBody("220284198302181149", ContentType.MULTIPART_FORM_DATA));


        HttpPost httpPost = new HttpPost("https://api.megvii.com/faceid/v2/verify");
        httpPost.setEntity(builder.build());

        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            CloseableHttpResponse res = httpClient.execute(httpPost);

            System.out.println(convertToString(res.getEntity().getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test1() {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .addPart("image", new FileBody(new File("/Users/51NB/Desktop/idcar.jpeg")))
                .addPart("api_key", new StringBody("c3_OTRm4Jhhl6hbDllq-Nqj5nheHlSiQ", ContentType.MULTIPART_FORM_DATA))
                .addPart("api_secret", new StringBody("44rXoUZghpR-lDr6BFVsuSC6L962yQc6", ContentType.MULTIPART_FORM_DATA))
                .addPart("return_portrait", new StringBody("0", ContentType.MULTIPART_FORM_DATA));


        HttpPost httpPost = new HttpPost("https://api.megvii.com/faceid/v3/ocridcard");
        httpPost.setEntity(builder.build());

        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            CloseableHttpResponse res = httpClient.execute(httpPost);

            System.out.println(convertToString(res.getEntity().getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String convertToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;

        try {
            while((line = reader.readLine()) != null) {
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
