package com.jiya.cashloan.test;

import com.rongdu.cashloan.core.common.util.OrderNoUtil;
import java.util.UUID;

/**
 * @Auther: king
 * @Date: 2018/11/16 15:37
 * @Description:
 */
public class OrderNoUtilTest {

    public static void main(String[] args) {
        System.out.println(OrderNoUtil.getSerialNumber());
        System.out.println(UUID.randomUUID().toString().hashCode());
    }
}
