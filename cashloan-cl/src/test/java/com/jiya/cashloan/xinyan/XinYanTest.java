package com.jiya.cashloan.xinyan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiji.cashloan.cl.model.xinyan.XinyanConstant;
import com.xiji.cashloan.cl.util.token.HttpRestUtils;
import com.xiji.cashloan.cl.util.xinyan.MD5Utils;
import com.xiji.cashloan.cl.util.xinyan.SecurityUtil;
import com.xiji.cashloan.cl.util.xinyan.UUIDGenerator;
import com.xiji.cashloan.cl.util.xinyan.rsa.RsaCodingUtil;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by szb on 18/12/10.
 */
public class XinYanTest {
    public static void main(String[] args) throws Exception {
//        testXEWD();
        testXWLDPreNo();
    }


    public static void testXEWD() throws Exception {
        /** 1、 商户号 **/
        String member_id = "8000013189";
        /** 2、终端号 **/
        String terminal_id = "8000013189";
        /** 3、请求地址 **/
        String url = "https://test.xinyan.com/product/integrity/v1/loans";
        Map<String, String> headers = new HashMap<>();

        String id_no = "533023198309151737";
        String id_name = "胡安良";
        String versions = "2.0.0";

        System.out.println(" 原始数据:id_no:" + id_no + ",id_name:" + id_name);

        id_no = MD5Utils.encryptMD5(id_no.trim());
        id_name = MD5Utils.encryptMD5(id_name.trim());

        System.out.println("32位小写MD5加密后数据:id_no:" + id_no + ",id_name:" + id_name);

        String trade_date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());// 订单日期
        String trans_id = UUIDGenerator.getUUID();// 必须入库 并且唯一 商户订单号

        String xmlOrJson = "";
        /** 组装参数 **/
        Map<Object, Object> arrayData = new HashMap<Object, Object>();
        arrayData.put("member_id", member_id);
        arrayData.put("terminal_id", terminal_id);
        arrayData.put("trade_date", trade_date);
        arrayData.put("trans_id", trans_id);
        arrayData.put("versions", versions);
        arrayData.put("industry_type", "A1");// 参照文档传自己公司对应的行业参数
        arrayData.put("id_no", id_no);
        arrayData.put("id_name", id_name);

        JSONObject jsonObjectFromMap = JSONObject.parseObject(JSON.toJSONString(arrayData));
        xmlOrJson = jsonObjectFromMap.toString();
        System.out.println("====请求明文:" + xmlOrJson);

        /** base64 编码 **/
        String base64str = SecurityUtil.Base64Encode(xmlOrJson);
        base64str = base64str.replaceAll("\r\n", "");//重要 避免出现换行空格符
        System.out.println("base64str:" + base64str);
        /** rsa加密 **/
        String property = System.getProperty("user.dir");
        String pfxpath = property + File.separator + "8000013189_pri.pfx";

        File pfxfile = new File(pfxpath);
        if (!pfxfile.exists()) {
            System.out.println("私钥文件不存在！");
            throw new RuntimeException("私钥文件不存在！");
        }
        String pfxpwd = "217526";// 私钥密码

        FileInputStream fileInputStream = new FileInputStream(pfxpath);
        String data_content = RsaCodingUtil.encryptByPriPfxFile(base64str, fileInputStream, pfxpwd);// 加密数据
        System.out.println("====加密串:" + data_content);

        System.out.println("url:" + url);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("member_id", member_id);
        params.put("terminal_id", terminal_id);
        params.put("data_type", "json");
        params.put("data_content", data_content);

        String result = HttpRestUtils.postForm(url, headers, params);
        System.out.println("请求返回：" + result);

        /** ================处理返回结果============= **/
        if (result.isEmpty()) {// 判断参数是否为空
            throw new RuntimeException("返回数据为空");
        }
    }

    public static void testXWLDPreNo() throws Exception {
        /** 1、 商户号 **/
        String member_id = "8000013189";
        /** 2、终端号 **/
        String terminal_id = "8000013189";
        /** 3、请求地址 **/
        String url = "https://test.xinyan.com/entry/sdk/preOrder";
        Map<String, String> headers = new HashMap<>();

        String trade_date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());// 订单日期
        String trans_id = UUIDGenerator.getUUID();// 必须入库 并且唯一 商户订单号

        String xmlOrJson = "";
        /** 组装参数 **/
        Map<Object, Object> arrayData = new HashMap<Object, Object>();
        arrayData.put("member_id", member_id);
        arrayData.put("terminal_id", terminal_id);
        arrayData.put("trade_date", trade_date);
        arrayData.put("trans_id", trans_id);
        arrayData.put("versions", XinyanConstant.XWLD_VERSION);
        arrayData.put("industry_type", "B18");// 参照文档传自己公司对应的行业参数
        arrayData.put("industry_type", XinyanConstant.INDUSTRY_TYPE);// 参照文档传自己公司对应的行业参数
        arrayData.put("product_type", "XWLDBG");
        arrayData.put("source", "SDK");

        JSONObject jsonObjectFromMap = JSONObject.parseObject(JSON.toJSONString(arrayData));
        xmlOrJson = jsonObjectFromMap.toString();
        System.out.println("====请求明文:" + xmlOrJson);

        /** base64 编码 **/
        String base64str = SecurityUtil.Base64Encode(xmlOrJson);
        base64str = base64str.replaceAll("\r\n", "");//重要 避免出现换行空格符
        System.out.println("base64str:" + base64str);
        /** rsa加密 **/
        String property = System.getProperty("user.dir");
        String pfxpath = property + File.separator + "8000013189_pri.pfx";

        File pfxfile = new File(pfxpath);
        if (!pfxfile.exists()) {
            System.out.println("私钥文件不存在！");
            throw new RuntimeException("私钥文件不存在！");
        }
        String pfxpwd = "217526";// 私钥密码

        FileInputStream fileInputStream = new FileInputStream(pfxpath);
        String data_content = RsaCodingUtil.encryptByPriPfxFile(base64str, fileInputStream, pfxpwd);// 加密数据
        System.out.println("====加密串:" + data_content);

        System.out.println("url:" + url);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("member_id", member_id);
        params.put("terminal_id", terminal_id);
        params.put("data_type", "json");
        params.put("data_content", data_content);

        String result = HttpRestUtils.postForm(url, headers, params);
        System.out.println("请求返回：" + result);

        /** ================处理返回结果============= **/
        if (result.isEmpty()) {// 判断参数是否为空
            throw new RuntimeException("返回数据为空");
        }
    }
}
