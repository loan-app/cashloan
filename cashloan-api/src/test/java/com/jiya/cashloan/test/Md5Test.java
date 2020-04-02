package com.jiya.cashloan.test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Test {

    public static void main(String[] args) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(("zhuang").getBytes());

            String jiami = new BigInteger(1, md5.digest()).toString(16);
            System.out.println("MD5加密：" + jiami);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
