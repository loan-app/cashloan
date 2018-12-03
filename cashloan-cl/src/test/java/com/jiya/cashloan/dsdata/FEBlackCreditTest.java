package com.jiya.cashloan.dsdata;

import com.jiya.cashloan.TestConstants;
import com.rongdu.cashloan.cl.model.dsdata.TKCreditRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 火眼黑名单测试类
 * Created by szb on 18/11/17.
 */
public class FEBlackCreditTest {
    public static void main(String[] args) throws Exception {
        String host = "https://api.dsdatas.com/blackData/fireEyesBlackv2";
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", TestConstants.NAME);
        payload.put("idCard", TestConstants.ID_CARD);
        payload.put("mobile", TestConstants.MOBILE);
        TKCreditRequest tkCreditRequest = new TKCreditRequest(host, payload);
        String result = tkCreditRequest.request();
        System.out.println("火眼黑名单返回结果: " + result);
    }
}
