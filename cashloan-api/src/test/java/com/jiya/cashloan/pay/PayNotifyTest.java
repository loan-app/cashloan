package com.jiya.cashloan.pay;

import com.alibaba.fastjson.JSON;
import com.xiji.cashloan.cl.util.fuiou.MD5Util;
import com.xiji.cashloan.core.common.util.HttpsUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: king
 * @Date: 2018/11/29 15:10
 * @Description:
 */
public class PayNotifyTest {

    public static void main(String[] args) {
//        testRefund();
        testPaymentNotify();
//        System.out.println(getSign("0002900F0345178","123456","15434542288364662","20181129","6228480328744607875","100000"));
    }

    public static String getSign(String merid, String pwd, String orderno, String merdt, String accntno, String amt) {
        //Md5(merid+”|”+pwd+”|”+orderno+”|”+merdt+”|”+accntno+”|”amt”)
        String str=merid+"|"+pwd+"|"+orderno+"|"+merdt+"|"+accntno+"|"+amt;
        return MD5Util.MD5Encode(str,"UTF-8");
    }
    /**
     * {"accntnm":"陈纯洪","accntno":"6228480328744607875",
     * "amt":"100000","bankno":"0103","fuorderno":"690909545406",
     * "futporderno":"690909545406","mac":"b6dacdeda4e00e0867561e7207981b10",
     * "merdt":"20181129","orderno":"15434542288364662","reason":"自助退票",
     * "result":"渠道资金到账已复核,交易已发送","state":"1","tpmerdt":"20181129"}
     */
    public static void testRefund() {
        String url = "http://192.168.21.42:8011/pay/fuiou/refundNotify.htm";
        Map<String,String> param = new HashMap<String, String>();
        param.put("accntnm","陈纯洪");
        param.put("accntno","6228480328744607875");
        param.put("amt","100000");
        param.put("bankno","0103");
        param.put("fuorderno","690909545406");
        param.put("futporderno","690909545406");
        param.put("mac","b6dacdeda4e00e0867561e7207981b10");
        param.put("merdt","20181129");
        param.put("orderno","15434542288364662");
        param.put("reason","自助退票");
        param.put("result","渠道资金到账已复核,交易已发送");
        param.put("state","1");
        param.put("tpmerdt","20181129");
        System.out.println(JSON.toJSON(param));
        String resp = HttpsUtil.postClient(url, param);
        System.out.println();
        System.out.println(resp);
    }

    /**
     * 测试放款通知。付款成功。只需要改下orderNo 其他的可以不用改
     */
    public static void testPaymentNotify() {
        String url = "http://47.110.61.233:8080/pay/fuiou/paymentNotify.htm";
        //String url = "http://jy.xyhuigou.com/pay/fuiou/paymentNotify.htm";
        Map<String,String> param = new HashMap<String, String>();
        param.put("accntnm","陈纯洪");
        param.put("accntno","6228480328744607875");
        param.put("amt","100000");
        param.put("bankno","0103");
        param.put("fuorderno","690909545406");
//        param.put("futporderno","690909545406");
        String orderNo = "1904080656601560";
        String mac = getSign("0002900F0345178","123456",orderNo,"20181129","6228480328744607875","100000");
        param.put("mac",mac);
        param.put("merdt","20181129");
        param.put("orderno",orderNo);
        param.put("reason","自助退票");
        param.put("result","渠道资金到账已复核,交易已发送");
        param.put("state","1");
//        param.put("tpmerdt","20181129");
        System.out.println(JSON.toJSON(param));
        String resp = HttpsUtil.postClient(url, param);
        System.out.println();
        System.out.println(resp);
    }
}
