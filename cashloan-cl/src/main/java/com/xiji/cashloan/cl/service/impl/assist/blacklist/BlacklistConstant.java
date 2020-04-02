package com.xiji.cashloan.cl.service.impl.assist.blacklist;

/**
 * @Auther: king
 * @Date: 2018/12/20 20:09
 * @Description: 黑名单常量类
 */
public class BlacklistConstant {
    public static String source_ppx = "paipaixin";//拍拍信
    public static String source_xdsj = "xindedata";//信德数聚
    public static String source_lvmeng = "lv_meng";//绿盟
    public static String SOURCE_IP ="IP";// 来源 IP
    public static String SOURCE_ADD ="ADD";// 来源ADD
    public static String DIMENSION_KEY_IDNO ="01";// 类别：01-身份证
    public static String DIMENSION_KEY_PHONE="02";// 类型 ：02-手机号
    public static int BLACK_LIST_STATUS_NORMAL = 0;// 状态 0:正常
    public static int BLACK_LIST_STATUS_DELETE = 1;//状态 1:删除
}
