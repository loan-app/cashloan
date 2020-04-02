package com.jiya.cashloan.moxie;

import com.xiji.cashloan.cl.model.moxie.MxCreditRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by szb on 18/11/21.
 */
public class OperatorTest {
    private static String MOBILE = "15958189557";
    private static String TOKEN = "1c083a407db749959ab0011911d42398";
    private static String APIKEY = "253d35f12bd14d4d9150b99a4e179453";

    public static void main(String[] args) throws Exception {
        operatorData();
        operatorReportStatus();
        operatorReport();
    }

    /**
     * 运营商原始数据增强版
     * @throws Exception
     */
    private static void operatorData() throws Exception {
        String host = "https://api.51datakey.com/carrier/v3/mobiles/{mobile}/mxdata-ex";
        String taskId = "fc589270-ecbc-11e8-90da-00163e13f173";
        Map<String, String> headMap = new HashMap<>();

        headMap.put("Authorization", "token" + " " + TOKEN);
        host = host.replace("{mobile}", MOBILE);
        host += "?task_id=" + taskId;
        String result = MxCreditRequest.get(host, headMap);
        System.out.println("魔蝎查询运营商数据:" + result);
    }

    /**
     * 运营商报告生成状态
     * @throws Exception
     */
    private static void operatorReportStatus() throws Exception {
        String host = "https://api.51datakey.com/report/status/v1/carrier/status";
        host += "?userName=" + MOBILE;
        Map<String, String> headMap = new HashMap<>();
        headMap.put("Authorization", "apikey" + " " + APIKEY);
        String result = MxCreditRequest.get(host, headMap);
        System.out.println("魔蝎获取运营商报告状态:" + result);
    }

    /**
     * 运营商报告
     * @throws Exception
     */
    private static void operatorReport() throws Exception {
        String host = "https://api.51datakey.com/carrier/v3/mobiles/{mobile}/mxreport";
        Map<String, String> headMap = new HashMap<>();

        headMap.put("Authorization", "token" + " " + TOKEN);
        host = host.replace("{mobile}", MOBILE);
        String result = MxCreditRequest.get(host, headMap);
        System.out.println("魔蝎查询运营商报告返回结果:" + result);
    }
}
