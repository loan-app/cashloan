package com.xiji.cashloan.cl.util.black;

import java.text.NumberFormat;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @Auther: king
 * @Date: 2018/12/10 15:53
 * @Description:
 */
public class StringSplit {
    public static String stirngSplitZero(String doubleString) {
        if (StringUtils.isBlank(doubleString) || NumberUtils.toDouble(doubleString, 0) == 0) {
            return "";
        }
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        String rs = nf.format(NumberUtils.toDouble(doubleString, 0));
        String[] doubleArr = StringUtils.split(rs, ".");
        if (doubleArr.length == 2) {
            try {
                int d = Integer.parseInt(doubleArr[1]);
                if (d > 0) {
                    return rs;
                } else {
                    return doubleArr[0];
                }
            } catch (Exception e) {
                return rs;
            }
        }
        return rs;
    }
}
