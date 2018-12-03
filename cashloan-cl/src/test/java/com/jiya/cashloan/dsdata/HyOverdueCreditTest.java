package com.jiya.cashloan.dsdata;

import com.jiya.cashloan.TestConstants;
import com.xiji.cashloan.cl.model.dsdata.TKCreditRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 逾期宝查询测试类
 * Created by szb on 18/11/17.
 */
public class HyOverdueCreditTest {
    public static void main(String[] args) throws Exception {
        String host = "https://api.dsdatas.com/blackData/hyOverdue";
        Map<String, Object> payload = new HashMap<>();
        payload.put("queryName", TestConstants.NAME);
        payload.put("idCard", TestConstants.ID_CARD);
        payload.put("mobilePhone", TestConstants.MOBILE);
        TKCreditRequest tkCreditRequest = new TKCreditRequest(host, payload);
        String result = tkCreditRequest.request();
        System.out.println("逾期宝查询返回结果:" + result);
    }
}
