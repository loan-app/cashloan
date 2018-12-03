package com.rongdu.cashloan.cl.model.moxie;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * 签名
 * Created by szb on 18/11/22.
 */
public class SignatureUtils {

    public static String base64Hmac256(String secret, String message) {
        try {
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256Hmac.init(secretKey);
            return Base64.encodeBase64String(sha256Hmac.doFinal(message.getBytes()));
        } catch (Exception ignored) {
            return "";
        }
    }
}
