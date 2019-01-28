package com.xiji.cashloan.cl.model.pay.common.constant;

/**
 * @Auther: king
 * @Date: 2018/11/21 20:29
 * @Description:
 */
public class PayConstant {
    public static final String PAY_MODE_FUIOU = "fuiou";
    public static final String PAY_MODE_HELIPAY = "helibao";
    /** 处理结果 - 成功 */
    public static final String RESULT_SUCCESS = "success";
    public static final String STATUS_NEED_CHECK = "needCheck";
    public static final String STATUS_ERROR = "error";
    public static final String STATUS_FAIL = "fail";
    public static final String STATUS_PAYQUERY_NO_REQ = "NOREQUEST";

    //请求接口返回异常
    public static final String REQ_ERROR_CODE_10 = "common_pay_error_10";

    //请求查询接口
    public static String QUERY_PAY_SUCCESS = "1";
    public static String QUERY_PAY_PROCESSING = "2";
    public static String QUERY_PAY_FAIL = "3";
    public static String QUERY_PAY_ERROR = "4";

}
