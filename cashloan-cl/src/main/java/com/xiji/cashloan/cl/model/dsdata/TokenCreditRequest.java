package com.xiji.cashloan.cl.model.dsdata;

import com.alibaba.fastjson.JSON;
import credit.DsCreditRequest;
import credit.Header;
import java.util.HashMap;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

/**
 * @Auther: king
 * @Date: 2018/11/15 14:14
 * @Description:
 */
public class TokenCreditRequest extends DsCreditRequest  {
    private String securityKey;
    public TokenCreditRequest(String host, Header header,String securityKey) {
        super(host, header);
        this.securityKey = securityKey;
    }

    @Override
    protected HttpEntity handle() throws Exception {
        HashMap paramMap = new HashMap();
        paramMap.put("apiKey", this.header.getApiKey());
        paramMap.put("securityKey", this.securityKey);
        String body = JSON.toJSONString(paramMap);
        ContentType jsonType = ContentType.create("application/json", "utf-8");
        return new StringEntity(body, jsonType);
    }
}
