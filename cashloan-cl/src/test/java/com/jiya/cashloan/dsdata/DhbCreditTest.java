package com.jiya.cashloan.dsdata;

import com.jiya.cashloan.TestConstants;
import com.rongdu.cashloan.cl.model.dsdata.TKCreditRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 在线风控(贷后邦反欺诈)测试类
 * Created by szb on 18/11/19.
 */
public class DhbCreditTest {
    //FIXED
    public static void main(String[] args) throws Exception {
        String host = "https://api.dsdatas.com/d/dhbGetSauronC";
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", TestConstants.NAME);
        payload.put("idCard", TestConstants.ID_CARD);
        payload.put("phone", TestConstants.MOBILE);
        TKCreditRequest operatorTkCreditRequest = new TKCreditRequest(host, payload);
        String request = operatorTkCreditRequest.request();
        System.out.println("贷后邦反欺诈返回结果:" + request);
    }
}
