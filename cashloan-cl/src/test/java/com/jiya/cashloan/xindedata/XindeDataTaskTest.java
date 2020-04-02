package com.jiya.cashloan.xindedata;

import com.alibaba.fastjson.JSON;
import com.xiji.cashloan.cl.model.xindedata.XindeResponse;
import com.xiji.cashloan.cl.util.SHAUtils;
import com.xiji.cashloan.core.common.util.HttpsUtil;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: king
 * @Date: 2018/12/19 11:02
 * @Description:
 */
public class XindeDataTaskTest {

    public static void main(String[] args) {
        try {
            testRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testRequest() throws Exception {
        String url = "https://api.xindedata.com/v1/task";
        String appId = "c614780e6f19c43e2d1af3f81bcfba9addb32922";
        String secret = "68753208ba1abf4a2c0bc2871ae644ea79cabca2";
        long time = System.currentTimeMillis()/1000;
        System.out.println(time);
        Map<String, Object> sc = new HashMap<>();
        sc.put("appid", appId);
        sc.put("time", time);

        Map<String, Object> param = new HashMap<>();
        param.put("type", "blackList");
        param.put("userID", "440883199211101977");
        param.put("phoneNo", "13554968975");

        String signature = SHAUtils.decrypt(SHAUtils.getOrderByLexicographic(sc,secret));

        url = url+"?appid=" + appId + "&time=" + time+"&signature=" + signature;
        System.out.println(url);
//        param.put("userID", "");//可选
        String resp = HttpsUtil.postStrClient(url, JSON.toJSONString(param));
        System.out.println(resp);
        XindeResponse response = (XindeResponse) JSON.parseObject(resp, XindeResponse.class);
        if (response != null) {
            System.out.println(response.getTid());
        }
    }
}
