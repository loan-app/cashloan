package com.jiya.cashloan.test;

import com.xiji.cashloan.core.common.util.DateUtil;
import com.xiji.cashloan.core.common.util.OrderNoUtil;
import java.util.Date;
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
        Date date = new Date();
        System.out.println(DateUtil.dateStr(DateUtil.valueOf(
            DateUtil.dateStr(date, DateUtil.DATEFORMAT_STR_002)),"yyyy"));
    }
}
