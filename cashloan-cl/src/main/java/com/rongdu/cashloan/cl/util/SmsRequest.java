package com.rongdu.cashloan.cl.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rongdu.cashloan.cl.domain.Sms;
import com.rongdu.cashloan.cl.domain.SmsTpl;
import com.rongdu.cashloan.cl.monitor.BusinessExceptionMonitor;
import com.rongdu.cashloan.cl.util.token.HttpRestUtils;
import com.rongdu.cashloan.core.common.util.DateUtil;
import com.rongdu.cashloan.core.common.util.StringUtil;
import com.rongdu.cashloan.core.domain.Borrow;
import com.rongdu.cashloan.core.domain.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by szb on 18/11/16.
 */
public class SmsRequest {
    private static String URL = "https://api.dsdatas.com/movek/movekSimpleInfo";
    private static String TOKEN = "NzIxYmY5ZTc5MjlmODNjMmVjYTkyOWJk";
    private static String MOBILE= "15958189557";
    private static String SMS_NO = "SMS1829478820";

    public static void main(String[] args) throws Exception {
        Map<String,Object> search = new HashMap<>();
        search.put("time", DateUtil.dateStr(new Date(),DateUtil.DATEFORMAT_STR_001));
        search.put("loan", 30000);
        String message = changeMessage("repayInform",search);
        System.out.println(message);
        SmsCreditRequest smsCreditRequest = new SmsCreditRequest(URL, TOKEN, new Date().getTime(), SMS_NO, message, MOBILE);
        String result = smsCreditRequest.request();
        System.out.println(result);

    }

    protected static String changeMessage(String smsType, Map<String,Object> map) {
        String message = "";
        if ("loanInform".equals(smsType)) {
            message = "尊敬的用户,您在{$time}的借款申请已放款成功,请查看您的收款银行卡!";
            message = message.replace("{$time}",
                    DateUtil.dateStr(DateUtil.valueOf(
                            DateUtil.dateStr(DateUtil.valueOf(map.get("time").toString()), DateUtil.DATEFORMAT_STR_002)),"M月dd日"));
        }
        if ("repayInform".equals(smsType)) {
            message = "尊敬的用户,您在{$time}借款{$loan}元,现已执行系统代扣还款成功,请知悉!";
            message = message.replace("{$time}",
                    DateUtil.dateStr(DateUtil.valueOf(
                            DateUtil.dateStr(DateUtil.valueOf(map.get("time").toString()), DateUtil.DATEFORMAT_STR_002)),"M月dd日"))
                    .replace("{$loan}", StringUtil.isNull(map.get("loan")));
        }
        return message;
    }

}
