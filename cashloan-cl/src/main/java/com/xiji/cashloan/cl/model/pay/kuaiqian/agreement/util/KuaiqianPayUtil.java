package com.xiji.cashloan.cl.model.pay.kuaiqian.agreement.util;

import com.xiji.cashloan.core.common.util.OrderNoUtil;

public class KuaiqianPayUtil {


    public static String getOrderId() {
        return "xjkq" + OrderNoUtil.getSerialNumber();
    }
}
