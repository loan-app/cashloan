package com.xiji.cashloan.cl.model.dsdata;

import com.alibaba.fastjson.JSON;
import com.xiji.cashloan.cl.util.token.TokenUtils;
import java.util.HashMap;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

/**
 * @Auther: king
 * @Date: 2018/11/16 11:47
 * @Description:
 */
public class SmsTkCreditRequest  extends DsTkCreditRequest {
    private String smsNo;
    public SmsTkCreditRequest(String host,String smsNo) {
        super(host);
        this.smsNo = smsNo;
    }
    @Override
    protected HttpEntity handle() throws Exception {
        HashMap paramMap = new HashMap();
        paramMap.put("timestamp", TokenUtils.getCurrentTime());
        paramMap.put("smsNo", this.smsNo);
        paramMap.put("mobile", this.payload.get("mobile"));
        paramMap.put("message", this.payload.get("message"));
        String body = JSON.toJSONString(paramMap);
        ContentType jsonType = ContentType.create("application/json", "utf-8");
        return new StringEntity(body, jsonType);
    }
}
