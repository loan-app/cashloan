package com.jiya.cashloan.paipaixin;

import com.xiji.cashloan.cl.model.paipaixin.VerifyUtil;
import com.xiji.cashloan.core.common.util.HttpsUtil;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: king
 * @Date: 2018/12/20 12:10
 * @Description:
 */
public class BlackApiTest {
    public static void main(String[] args) {
        try {
            testRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testRequest() throws Exception {
        String url = "https://api.ppcredit.com/router/rest";
        String appId = "key2cf89a6900644d80b36fe25ccc67968e";
        String secret = "nqw2bTruB5311OIytF8qyv5LSDy4shO5g89eebQ4zAXSIYV7L2kKuMs9f6OfEmL0";
        long time = System.currentTimeMillis()/1000;
        System.out.println(time);

        String method = "ppc.risklist.query.v1";
        Map<String, String> param = new HashMap<>();
        param.put("method", method);
//        param.put("name", URLEncoder.encode("张荣","UTF-8"));
//        param.put("idNumber", "440883199211101977");
//        param.put("mobile", "18320890902");
        param.put("name", URLEncoder.encode("郭艳","UTF-8"));
        param.put("idNumber", "320924199406206865");
        param.put("mobile", "15705258191");

        Map<String, String> headerMap = VerifyUtil.signinMap(method, appId, secret);
        param.putAll(headerMap);

        System.out.println(url);
        String resp = HttpsUtil.postClient(url, param);
        System.out.println(resp);
    }
}
