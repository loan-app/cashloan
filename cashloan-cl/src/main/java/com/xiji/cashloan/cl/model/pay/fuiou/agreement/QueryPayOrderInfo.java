package com.xiji.cashloan.cl.model.pay.fuiou.agreement;

/**
 * @Auther: king
 * @Date: 2018/12/4 14:06
 * @Description:
 */
public class QueryPayOrderInfo {
    public static String PAY_SUCCESS = "1";
    public static String PAY_PROCESSING = "2";
    public static String PAY_FAIL = "3";
    public static String PAY_ERROR = "4";
    private String code = PAY_ERROR;//1支付成功、2支付处理中、3其他失败状态、4查询异常
    private String msg;
    private String orderNo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
