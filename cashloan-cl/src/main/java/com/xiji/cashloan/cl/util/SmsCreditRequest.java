package com.xiji.cashloan.cl.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import java.util.HashMap;

/**
 * Created by szb on 18/11/16.
 */
public class SmsCreditRequest extends BaseCreditRequest {
    private String smsNo;
    private String message;
    private String mobile;

    public SmsCreditRequest(String host, String authToken, long timestamp, String smsNo, String message, String mobile) {
        super(host, authToken, timestamp);
        this.smsNo = smsNo;
        this.message = message;
        this.mobile = mobile;
    }

    protected HttpEntity handle() throws Exception {
        HashMap paramMap = new HashMap();
        paramMap.put("smsNo", this.smsNo);
        paramMap.put("timestamp", this.timestamp);
        paramMap.put("mobile", this.mobile);
        paramMap.put("message", this.message);
        String body = JSON.toJSONString(paramMap);
        ContentType jsonType = ContentType.create("application/json", "utf-8");
        return new StringEntity(body, jsonType);
    }
}
