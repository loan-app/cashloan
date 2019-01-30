package com.xiji.cashloan.cl.model.dsdata.util;

import com.xiji.cashloan.cl.util.RSACoder;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.util.Base64;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import sun.misc.BASE64Encoder;
import tool.util.OrderNoUtil;
import tool.util.StringUtil;

/**
 * @Auther: king
 * @Date: 2019/1/30 14:38
 * @Description:
 */
public class FaceCheckUtil {

    public static final String model_face = "face";
    public static final String model_kFace = "kface";

    public static final String router_rate = "rate";
    public static final String router_face = "face";
    public static final String router_kface = "kface";
    public static String decodeBase64(String source) throws Exception {
        return Base64.encode(RSACoder.encryptByPrivateKey((getChannelNo()+ source).getBytes(), Base64.decode(getBase64Key())));
    }

    public static String getBase64Key() {
       return Global.getValue("k_ocr_base64key");
//        return "MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEAoQbUJfG8h63o2klN3InuK1qUetS71O0YINFlHyZzzKmRBCgNyvuDt8ZuCjB9Zrexk+FNOeUg2dGV8XSCZKwLmwIDAQABAkEAg/C3ddvMMZQi/nEf9juiRi2zCa4ztbULlyBb7hkwuxlL+HYHln8EhgvBTGAWb596BQTmmDET1iVgDm+pWEfd2QIhANk6cX7/H4AkKr9GLlc5KMJNm7+/tJzoMTw6uETwfL1HAiEAvcRuUYY4azGhBAJmsoxSy/S0DSGYZlohMN+FYjSRmQ0CIH+257GVx2xsVyGb3nTzqy4JuO9Ug5jYvtG9aEdH6N7TAiEAgoeV9l+jeSBHB/H63/+jiAUGwC2GnYiLYgmtvtI4ABUCIQC1BoDi3sip+YcY3gw6+SbChaRNAcfZVoeJK60ZM5+xww==";
    }
    public static String getChannelNo() {
       return Global.getValue("k_ocr_channelno");
//         return "CH13IVR8S";
    }
    public static String getCheckFaceUrl() {
       return Global.getValue("k_ocr_checkface_url");
//        return "http://rryqo.com/finance/v1/face/match?applyNo=";
    }

    public static String router(String idCard) {
        String kRouter = Global.getValue("k_ocr_checkface_router");
        if (StringUtil.isEmpty(kRouter)) {
           return model_face;
        }
        if (StringUtil.equalsIgnoreCase(kRouter,router_rate)){
            String last = idCard.substring(idCard.length() - 1);
            if (!StringUtil.equalsIgnoreCase(last, "1")) {
                return model_kFace;
            }
        }
        if (StringUtil.equalsIgnoreCase(kRouter,router_kface)){
            return model_kFace;
        }
        if (StringUtil.equalsIgnoreCase(kRouter,router_face)){
            return model_face;
        }
        return model_face;
    }

    public static String getSeqNumber() {
        return OrderNoUtil.getSerialNumber();
    }

    public static String fileToImageStr(String file) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(file);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
}
