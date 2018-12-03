package com.xiji.cashloan.cl.model.dsdata;

import com.alibaba.fastjson.JSON;
import com.xiji.cashloan.cl.util.token.TokenUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by szb on 18/11/17.
 */
public class TKCreditRequest extends DsTkCreditRequest{

    public TKCreditRequest(String host, Map<String, Object> payload) {
        super(host, payload);
    }

    @Override
    protected HttpEntity handle() throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("timestamp", TokenUtils.getCurrentTime());
        paramMap.putAll(this.payload);
        String body = JSON.toJSONString(paramMap);
        ContentType contentType = ContentType.create("application/json", "utf-8");
        return new StringEntity(body, contentType);
    }
}
