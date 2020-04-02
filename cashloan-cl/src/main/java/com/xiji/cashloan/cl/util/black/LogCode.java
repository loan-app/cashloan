package com.xiji.cashloan.cl.util.black;

import tool.util.StringUtil;

public class LogCode {

    public static int NORMAL_CODE = 100;       //正常
    public static int LIMIT_CODE = 102;        //限流
    public static int DEFAULT_CODE = 101;      //默认
    public static int NO_EXISTENT_CODE = 103;  //不存在
    public static int ERROR_CODE = 104;        //错误
    public static int IS_NULL_CODE = 105;      //空值
    public static int TIMEOUT_CODE = 106;      //超时
    public static int RPC_ERROR_CODE = 107;    //RPC调用出错
    public static int INPUT_FORMAT_ERROR_CODE = 108; //传入数据字段格式出错

    public static String RESULT_CODE = "result_code";
    public static final String ALL_SOURCE = "all";
    /**
     * 黑名单表名
     */
    public static final String table_black_list = "cl_name_blacklist";

    public static boolean isAllSource(String source){
        return StringUtil.equalsIgnoreCase(ALL_SOURCE, source);
    }

}
