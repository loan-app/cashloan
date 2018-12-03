package com.jiya.cashloan.pay;

import com.alibaba.fastjson.JSON;
import com.rongdu.cashloan.cl.util.fuiou.MD5Util;
import com.rongdu.cashloan.core.common.util.HttpsUtil;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: king
 * @Date: 2018/11/29 16:46
 * @Description:
 */
public class AgreementPayNotifyTest {
    public static void main(String[] args) {
//        testRefund();
        testRepaymentNotify();
    }

    public static String getSign(String TYPE, String VERSION, String RESPONSECODE, String MCHNTCD, String MCHNTORDERID, String ORDERID, String AMT, String BANKCARD, String key) {
        //Md5(merid+”|”+pwd+”|”+orderno+”|”+merdt+”|”+accntno+”|”amt”)
        String str=TYPE+"|"+VERSION+"|"+RESPONSECODE+"|"+MCHNTCD+"|"+MCHNTORDERID+"|"+ORDERID+"|"+AMT+"|"+BANKCARD+"|" + key;
        return MD5Util.MD5Encode(str,"UTF-8");
    }

    public static void testRepaymentNotify() {
        String url = "http://192.168.21.42:8011/pay/fuiou/repaymentNotify.htm";
        Map<String,String> param = new HashMap<String, String>();
        param.put("TYPE","03");
        param.put("VERSION","1.0");
        param.put("MCHNTCD","0002900F0022256");
        param.put("USERID","1236985478");
        param.put("RESPONSECODE","0000");
        param.put("RESPONSEMSG","成功");
        param.put("MCHNTORDERID","14909408631788350725");
        param.put("ORDERID","000033454458");
        param.put("AMT","200");
        param.put("BANKCARD","6226090217436936");
        param.put("PROTOCOLNO","14907763938986631631");
        String sign = getSign("03","1.0",
            "0000","0002900F0022256",
            "14909408631788350725","000033454458","200","6226090217436936","5old71wihg2tqjug9kkpxnhx9hiujoqj");
        param.put("SIGN",sign);
        System.out.println(JSON.toJSON(param));
        String resp = HttpsUtil.postClient(url, param);
        System.out.println();
        System.out.println(resp);
    }
}
