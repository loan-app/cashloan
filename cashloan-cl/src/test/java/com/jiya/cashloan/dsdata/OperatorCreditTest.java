package com.jiya.cashloan.dsdata;

import com.jiya.cashloan.TestConstants;
import com.rongdu.cashloan.cl.model.dsdata.TKCreditRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 运营商报告授权测试
 * Created by szb on 18/11/17.
 */
public class OperatorCreditTest {
    public static void main(String[] args) throws Exception {
        String host = "https://api.dsdatas.com/s/getOperatorAuthUrl";
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", TestConstants.NAME);
        payload.put("idCard", TestConstants.ID_CARD);
        payload.put("phone", TestConstants.MOBILE);
        TKCreditRequest operatorTkCreditRequest = new TKCreditRequest(host, payload);
        String request = operatorTkCreditRequest.request();
        System.out.println("运营商查询返回结果:" + request);
    }
}
