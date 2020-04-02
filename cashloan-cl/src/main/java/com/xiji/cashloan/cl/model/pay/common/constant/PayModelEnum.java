package com.xiji.cashloan.cl.model.pay.common.constant;

/**
 * @Auther: king
 * @Date: 2019/1/31 13:23
 * @Description:
 */
public enum PayModelEnum {
    helipay,fuiou,kuaiqian,chanpay;

    public static String match(String ac) {
        for (PayModelEnum item : values()) {
            if (item.name().equalsIgnoreCase(ac)) {
                return item.name();
            }
        }
        return fuiou.name();
    }

    public static void main(String[] args) {
        System.out.println(match("chanpay"));
    }
}
