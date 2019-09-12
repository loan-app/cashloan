package com.xiji.cashloan.cl.util;

import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.core.common.context.Global;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;



public class SmsUtils {

    private final static String sign = "【" + Global.getValue("appName")+ "】";

    /**
     * 放款还款到期短信
     *
     * @param mobile
     * @param msg
     * @return
     * @throws Exception
     */
    public static String sendQdzSms(String mobile, String msg) throws Exception {
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod("http://api.xunbd.com/sms/single_send");
        postMethod.getParams().setContentCharset("UTF-8");
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

        String accesskey = "GizLJRU74PXWHeVj"; //用户开发key
        String accessSecret = "dcWIm6qV0EIqbrUQI8RILlIjdB2s1REb"; //用户开发秘钥

        NameValuePair[] data = {
                new NameValuePair("accesskey", accesskey),
                new NameValuePair("secret", accessSecret),
                new NameValuePair("sign", sign),
                new NameValuePair("phone", mobile),
                new NameValuePair("msg", msg)
        };
        postMethod.setRequestBody(data);

        int statusCode = httpClient.executeMethod(postMethod);
        System.out.println("statusCode: " + statusCode + ", body: "
                + postMethod.getResponseBodyAsString());
        JSONObject responseJSON = JSONObject.parseObject(postMethod.getResponseBodyAsString());
        return responseJSON.toJSONString();
    }

    /**
     * 验证码短信
     *
     * @param mobile
     * @param msg
     * @return
     * @throws Exception
     */
    public static String sendBeeSms(String mobile, String msg) throws Exception {
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod("http://api.xunbd.com/sms/single_send");
        postMethod.getParams().setContentCharset("UTF-8");
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

        String accesskey = "OvGdVSOV2IzQsmkO"; //用户开发key
        String accessSecret = "hFPZbjZ2CE2D0QqEhDCCIO358ZrIll0M"; //用户开发秘钥

        NameValuePair[] data = {
                new NameValuePair("accesskey", accesskey),
                new NameValuePair("secret", accessSecret),
                new NameValuePair("sign", sign),
                new NameValuePair("phone", mobile),
                new NameValuePair("msg", msg)
        };
        postMethod.setRequestBody(data);

        int statusCode = httpClient.executeMethod(postMethod);
        System.out.println("statusCode: " + statusCode + ", body: "
                + postMethod.getResponseBodyAsString());
        JSONObject responseJSON = JSONObject.parseObject(postMethod.getResponseBodyAsString());
        return responseJSON.toJSONString();
    }


    public static void main(String[] args) throws Exception {
        SmsUtils.sendQdzSms("18727479010","尊敬的用户,您在2019-09-12借款1元,现已执行系统代扣还款成功,请知悉!");
    }
}
