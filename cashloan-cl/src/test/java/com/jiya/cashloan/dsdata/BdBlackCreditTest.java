package com.jiya.cashloan.dsdata;

import com.jiya.cashloan.TestConstants;
import com.rongdu.cashloan.cl.model.dsdata.TKCreditRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 法眼名单测试类
 * Created by szb on 18/11/17.
 */
public class BdBlackCreditTest {
    public static void main(String[] args) throws Exception {
        String host = "https://api.dsdatas.com/bd/bdBlackInfo";
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", TestConstants.NAME);
        payload.put("idCard", TestConstants.ID_CARD);
        payload.put("mobile", TestConstants.MOBILE);
        TKCreditRequest tkCreditRequest = new TKCreditRequest(host, payload);
        String result = tkCreditRequest.request();
        System.out.println("法眼黑名单返回结果: " + result);
    }
}
