package com.xiji.cashloan.cl.util;

/**
 * 调用外部数据费用信息常量
 * Created by szb on 18/12/4.
 */
public class CallsOutSideFeeConstant {
    //魔蝎运营商
    public static int CALLS_TYPE_OPERATOR = 1;
    //魔杖反欺诈
    public static int CALLS_TYPE_ANTI_FRAUD = 2;
    //魔杖申请准入
    public static int CALLS_TYPE_APPLY = 3;
    //魔杖多头
//    public static int CALLS_TYPE_MULTI_INFO = 3;
    //魔杖黑灰名单
//    public static int CALLS_TYPE_BLACK_GRAY = 4;
    //魔杖贷后行为
    public static int CALLS_TYPE_POST_LOAD = 5;
    //发送短信
    public static int CALLS_TYPE_SEND_MSG = 6;
    //人脸识别
    public static int CALLS_TYPE_FACE_DETECT = 7;
    //新颜小额网贷报告
    public static int CALLS_TYPE_XINYAN_LOAN = 8;
    //宜信阿福风险评估
    public static int CALLS_TYPE_YIXIN_RISK = 9;

    //魔蝎运营商费用
    public static double FEE_OPERATOR = -0.7;
    //魔杖反欺诈费用
    public static double FEE_ANTI_FRAUD = -0.4;
    //魔杖申请准入费用
    public static double FEE_APPLY = -0.6;
//    //魔杖多头费用
//    public static double FEE_MULTI_INFO = 0.3;
//    //魔杖黑灰名单费用
//    public static double FEE_BLACK_GRAY = 0.2;
    //魔杖贷后行为费用
    public static double FEE_POST_LOAD = -0.5;
    //发送短信费用
    public static double FEE_SEND_MSG = -0.06;
    //人脸识别费用
    public static double FEE_FACE_DETECT = -0.8;
    //新颜小额网贷费用
    public static double FEE_XINYAN_LOAN = -1;
    //宜信阿福风险评估费用
    public static double FEE_YIXIN_RISK = -0.2;

    /**
     * 消费
     */
    public static Integer CAST_TYPE_CONSUME = 0;

    /**
     * 充值
     */
    public static Integer CAST_TYPE_RECHARGE = 1;

}
