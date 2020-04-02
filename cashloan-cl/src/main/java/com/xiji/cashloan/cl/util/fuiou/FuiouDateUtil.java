package com.xiji.cashloan.cl.util.fuiou;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Auther: king
 * @Date: 2018/11/29 11:52
 * @Description:
 */
public class FuiouDateUtil {
    public static String getDate(int i){
        SimpleDateFormat sf=new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        if(i==1){
            String str = sf.format(calendar.getTime());
            return str;
        }else{
            calendar.add(Calendar.DAY_OF_YEAR, -i);
            String str = sf.format(calendar.getTime());
            return str;
        }
    }
}
