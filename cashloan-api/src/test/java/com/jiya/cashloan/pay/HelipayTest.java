package com.jiya.cashloan.pay;

import com.alibaba.fastjson.JSON;
import com.xiji.cashloan.cl.model.pay.helipay.annotation.FieldEncrypt;
import com.xiji.cashloan.cl.model.pay.helipay.annotation.SignExclude;
import com.xiji.cashloan.cl.model.pay.helipay.util.AES;
import com.xiji.cashloan.cl.model.pay.helipay.util.ConfigureEncryptAndDecrypt;
import com.xiji.cashloan.cl.model.pay.helipay.util.HelipayUtil;
import com.xiji.cashloan.cl.model.pay.helipay.util.RSA;
import com.xiji.cashloan.cl.model.pay.helipay.vo.response.BindCardPayResponseVo;
import com.xiji.cashloan.cl.util.fuiou.MD5Util;
import com.xiji.cashloan.core.common.util.HttpsUtil;
import java.io.File;
import java.lang.reflect.Field;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Auther: king
 * @Date: 2018/11/29 16:46
 * @Description:
 */
public class HelipayTest {
    private static final Log log = LogFactory.getLog(HelipayTest.class);
    private static final String SPLIT = "&";
    private static final String SIGN = "sign";
    private static final String ENCRYPTION_KEY = "encryptionKey";

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
        String url = "http://192.168.21.42:8011/pay/helipay/repaymentNotify.htm";
        BindCardPayResponseVo vo = new BindCardPayResponseVo();
        vo.setRt1_bizType("rt1_bizType");
        vo.setRt2_retCode("0000");
        vo.setRt3_retMsg("rt3_retMsg");
        vo.setRt6_serialNumber("000033454458");
        vo.setRt5_orderId("14909408631788350725");

//        Map<String,String> param = new HashMap<String, String>();
//        param.put("rt1_bizType","03");
//        param.put("rt2_retCode","1.0");
//        param.put("rt3_retMsg","0002900F0022256");
//        param.put("rt4_customerNumber","1236985478");
//        param.put("rt5_orderId","0000");
//        param.put("rt6_serialNumber","成功");
//        param.put("rt7_completeDate","14909408631788350725");
//        param.put("rt8_orderAmount","000033454458");
//        param.put("rt9_orderStatus","200");
//        param.put("rt10_bindId","rt10_bindId");
//        param.put("rt11_bankId","rt11_bankId");
//        param.put("rt12_onlineCardType","rt12_onlineCardType");
//        param.put("rt13_cardAfterFour","rt13_cardAfterFour");
//        param.put("rt14_userId","rt14_userId");
//        String sign = getSign("03","1.0",
//            "0000","0002900F0022256",
//            "14909408631788350725","000033454458","200","6226090217436936","5old71wihg2tqjug9kkpxnhx9hiujoqj");
//        param.put("sign",sign);
        Map param = null;
        try {
            param = getReqestMap(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(JSON.toJSON(param));
        String resp = HttpsUtil.postClient(url, param);
        System.out.println();
        System.out.println(resp);
    }

    public static Map getReqestMap(Object bean) throws Exception {
        String property = System.getProperty("user.dir");
        String pfxpath = property + File.separator + "sign" + File.separator + "C1800000002.pfx";
        String cerPath = property + File.separator + "sign" + File.separator + "helipay.cer";
        String pwd = "qwer1234";
        Map retMap = new HashMap();

        boolean isEncrypt = false;
        String aesKey = AES.generateString(16);
        StringBuilder sb = new StringBuilder();

        Class clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String key = field.toString().substring(field.toString().lastIndexOf(".") + 1);
            String value = (String) field.get(bean);
            if (value == null) {
                value = "";
            }
            //查看是否有需要加密字段的注解,有则加密
            //这部分是将需要加密的字段先进行加密
            if (field.isAnnotationPresent(FieldEncrypt.class) && StringUtils.isNotEmpty(value)) {
                isEncrypt = true;
                value = AES.encryptToBase64(value, aesKey);
            }

            //字段没有@SignExclude注解的拼签名串
            //这部分是把需要参与签名的字段拼成一个待签名的字符串
            if (!field.isAnnotationPresent(SignExclude.class)) {
                sb.append(SPLIT);
                sb.append(value);
            }

            retMap.put(key, value);
        }

        //如果有加密的，需要用合利宝的公钥将AES加密的KEY进行加密使用BASE64编码上送
        if (isEncrypt) {
            PublicKey publicKey = RSA.getPublicKeyByCert(cerPath);
            String encrytionKey = RSA.encodeToBase64(aesKey, publicKey, ConfigureEncryptAndDecrypt.KEY_ALGORITHM);
            retMap.put(ENCRYPTION_KEY, encrytionKey);
        }

        if (HelipayUtil.isLogSign()) {
            log.info("原签名串：" + sb.toString());
        }

        //使用商户的私钥进行签名
        PrivateKey privateKey = RSA.getPrivateKey(pfxpath,pwd);
        String sign = RSA.sign(sb.toString(), privateKey);
        retMap.put(SIGN, sign);
        if (HelipayUtil.isLogSign()) {
            log.info("签名sign：" + sign);
        }
        return retMap;
    }
}
