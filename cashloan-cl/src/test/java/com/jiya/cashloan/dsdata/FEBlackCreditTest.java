package com.jiya.cashloan.dsdata;

import com.jiya.cashloan.TestConstants;
import com.xiji.cashloan.cl.model.dsdata.SmsTkCreditRequest;
import com.xiji.cashloan.cl.model.dsdata.TKCreditRequest;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.DateUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 火眼黑名单测试类
 * Created by szb on 18/11/17.
 */
public class FEBlackCreditTest {
    public static void main(String[] args) throws Exception {
//        String host = "https://api.dsdatas.com/blackData/fireEyesBlackv2";
//        Map<String, Object> payload = new HashMap<>();
//        payload.put("name", TestConstants.NAME);
//        payload.put("idCard", TestConstants.ID_CARD);
//        payload.put("mobile", TestConstants.MOBILE);
//        TKCreditRequest tkCreditRequest = new TKCreditRequest(host, payload);
//        String result = tkCreditRequest.request();
//        System.out.println("火眼黑名单返回结果: " + result);


        Map<String, Object> payload = new HashMap<>();
        payload.put("mobile", "18296134271");
        payload.put("message", "尊敬的用户,您在2月14日借款1000元,现已执行系统代扣还款成功,请知悉!");

        String result = dsSendSms(payload, "SMS1958954749");
        System.out.println(result);
    }

    private static String dsSendSms(Map<String, Object> payload, String smsNo){
        final String APIHOST = "https://api.dsdatas.com/movek/movekSimpleInfo";//发送地址

        SmsTkCreditRequest creditRequest = new SmsTkCreditRequest(APIHOST,smsNo);
        creditRequest.setPayload(payload);
        String result=null;
        try {
            result = creditRequest.request();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
