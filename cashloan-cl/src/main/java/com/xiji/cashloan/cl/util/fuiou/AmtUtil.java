package com.xiji.cashloan.cl.util.fuiou;

import java.math.BigDecimal;

/**
 * @Auther: king
 * @Date: 2018/11/27 11:56
 * @Description:
 */
public class AmtUtil {
    private static BigDecimal AMT_100 = new BigDecimal(100);

    /**
     * double 乘以 100，
     * @param amt
     * @return
     */
    public static String convertAmtToBranch(double amt) {
        BigDecimal decimal = new BigDecimal(amt+"");// 需要+"" ,处理精度问题
        return decimal.multiply(AMT_100).setScale(0,BigDecimal.ROUND_UNNECESSARY).toString();
    }
    /**
     * double 乘以 100，
     * @param amt
     * @return
     */
    public static String convertAmtToBranch(String amt) {
        BigDecimal decimal = new BigDecimal(amt+"");// 需要+"" ,处理精度问题
        return decimal.multiply(AMT_100).setScale(0,BigDecimal.ROUND_UNNECESSARY).toString();
    }

    /**
     * double 乘以 100，
     * @param amt
     * @return
     */
    public static String reConvertAmt(String amt) {
        BigDecimal decimal = new BigDecimal(amt);
        return decimal.divide(AMT_100).toString();
    }

    public static void main(String[] args) {
        System.out.println(convertAmtToBranch(1.2));
        System.out.println(reConvertAmt("231100"));
    }
}
