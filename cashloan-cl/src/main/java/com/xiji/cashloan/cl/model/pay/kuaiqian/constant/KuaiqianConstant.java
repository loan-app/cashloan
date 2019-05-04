package com.xiji.cashloan.cl.model.pay.kuaiqian.constant;

/**
 * @auther : wnb
 * @date : 2019/5/3
 * @describe : 快钱常量
 */
public class KuaiqianConstant {

    /** 接口类型 - 代付 */
    public static final String BTYPE_PAY_MOCK = "PayMock";
    /**
     * 表明交易申请成功
     */
    public static final String RESPONSE_SUCCESS_CODE = "0000";
    /**
     * 验签解密失败
     */
    public static final String RESPONSE_CHECK_FAIL ="0004";
    /** 接口类型 - 代付单查询 */
    public static final String BTYPE_QUERY_MOCK = "QueryMock";
    /**
     * 默认查询目标页数
     */
    public static final String QUERY_TARGETPAGE ="1";
    /**
     * 默认查询每页条数
     */
    public static final String QUERY_PGAE_SIZE = "20";
    /**
     *  策略编码，固定值 F41
      */
    public static String  FETURE_CODE ="F41";
    /**
     * 字符编码
     */
    private static String ENCODEING = "UTF-8";
}
