package com.xiji.cashloan.cl.model.xindedata;

import java.util.Map;
import org.apache.commons.collections.map.HashedMap;
import tool.util.StringUtil;

/**
 * @Auther: king
 * @Date: 2018/12/19 16:59
 * @Description:
 */
public class XindeDataUtil {

    private static Map<String, String> cacheReason = new HashedMap();

    static {
        cacheReason.put("100","不法分子");
        cacheReason.put("101","多头借贷");
        cacheReason.put("102","高风险区域");
        cacheReason.put("103","涉嫌不法");
        cacheReason.put("104","身份规则限制");
        cacheReason.put("105","身份认证失败");
        cacheReason.put("106","失信名单");
        cacheReason.put("107","疑似恶意欺诈");
        cacheReason.put("108","信用评分不足");
    }
    public static String convertloanRefused(String refused) {
        return StringUtil.equals("true", refused) ? "是" : "否";
    }

    public static String convertOverdueAmountStr(int overdueAmount) {
        if (overdueAmount == 1) {
            return "[0,100]";
        } else if (overdueAmount == 2) {
            return "[100,500)";
        } else if (overdueAmount == 3) {
            return "[500,1000)";
        } else if (overdueAmount == 4) {
            return "[1000,2000)";
        } else if (overdueAmount == 5) {
            return "[2000,4000)";
        } else if (overdueAmount == 6) {
            return "[4000,6000)";
        } else if (overdueAmount == 7) {
            return "[6000,10000)";
        } else if (overdueAmount == 8) {
            return ">=10000";
        }
        return "";
    }

    public static String convertRefuseReasonStr(String code) {
        return cacheReason.get(code);
    }

}
