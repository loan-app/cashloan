package com.xiji.cashloan.cl.util.weijifen;

import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;
import java.util.TreeMap;

public class MainSign {
    /**
     * 签名算法
     */
    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    /**
     * 私钥加签名
     *
     * @param treeMap
     * @param privateStr
     * @return
     * @throws Exception
     */
    public static TreeMap<String, Object> assembleSign(TreeMap<String, Object> treeMap, String privateStr) throws Exception {
        //数据MD5处理
        String md5 = MainSign.toMD5(concatMap(treeMap));
        //私钥 对 MD5后的数据 加签
        String packetSignaTure = MainSign.sign(md5, privateStr);
        //增加签名
        treeMap.put("sign", packetSignaTure);
        return treeMap;
    }

    /**
     * 公钥校验签名
     *
     * @param resultTreeMap
     * @param pubStr
     * @return
     */
    public static boolean doSignCheck(TreeMap<String, Object> resultTreeMap, String pubStr) {
        String sign = resultTreeMap.get("sign").toString();
        resultTreeMap.remove("sign");
        try {
            String resMd5 = MainSign.toMD5(concatMap(resultTreeMap));
            return MainSign.doCheck(resMd5, sign, pubStr);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String concatMap(TreeMap<String, Object> resultTreeMap) {
        StringBuilder buff = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, Object> e : resultTreeMap.entrySet()) {
            if (first) first = false;
            else buff.append("&");
            buff.append(e.getKey()).append("=").append(e.getValue());
        }
        return buff.toString();
    }

    //MD5 32小
    public static String toMD5(String inStr) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(inStr.getBytes());
            byte b[] = md.digest();
            int i;
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }

    // 私钥加签
    public static String sign(String content, String privateKey) throws Exception {
        PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64Utils.decode(privateKey));
        KeyFactory keyf;
        keyf = KeyFactory.getInstance("RSA");
        PrivateKey priKey = keyf.generatePrivate(priPKCS8);
        java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
        signature.initSign(priKey);
        signature.update(content.getBytes());
        byte[] signed = signature.sign();
        return Base64Utils.encode(signed);
    }

    // 公钥验签
    public static boolean doCheck(String content, String sign, String publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64Utils.decode(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
            java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
            signature.initVerify(pubKey);
            signature.update(content.getBytes());
            boolean bverify = signature.verify(Base64Utils.decode(sign));
            return bverify;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
