package com.xiji.cashloan.cl.model.dsdata;

import com.alibaba.fastjson.JSON;
import com.xiji.cashloan.cl.model.dsdata.util.FaceCheckUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

/**
 * @Auther: king
 * @Date: 2019/1/30 14:29
 * @Description:
 */
public class FaceCheckIdCardRequest extends DsTkCreditRequest {
    private String reqNumber ;
    private String idCardImage ;

    public FaceCheckIdCardRequest(String idCardImage,String reqNumber) {
        super("");
        this.reqNumber = reqNumber;
        this.idCardImage = idCardImage;
    }

    @Override
    protected HttpEntity handle() throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("image", FaceCheckUtil.fileToByte(idCardImage));
        paramMap.put("idCardSide", 1);
        String body = JSON.toJSONString(paramMap);
        ContentType contentType = ContentType.create("application/json", "utf-8");
        return new StringEntity(body, contentType);
    }

    @Override
    public String request() throws Exception {
        HttpPost httpPost = new HttpPost(FaceCheckUtil.getCheckFaceIDCardUrl()+reqNumber);
        String responseStr = null;
        CloseableHttpResponse resp = null;

        try {
            HttpEntity e = this.handle();
            httpPost.setHeader("channelNo", FaceCheckUtil.getChannelNo());
            httpPost.setHeader("signStr", FaceCheckUtil.decodeBase64(reqNumber));
            httpPost.setConfig(getRequestConfig());
            httpPost.setEntity(e);
            resp = getHttpClient().execute(httpPost);
            InputStream respIs = resp.getEntity().getContent();
            responseStr = super.convertStreamToString(respIs);
        } finally {
            if(resp != null) {
                try {
                    EntityUtils.consume(resp.getEntity());
                } catch (IOException var11) {
                    var11.printStackTrace();
                }
            }

        }

        return responseStr;
    }
}
